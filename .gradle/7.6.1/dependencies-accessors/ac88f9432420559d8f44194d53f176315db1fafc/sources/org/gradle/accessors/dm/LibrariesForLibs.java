package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
*/
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final SpringLibraryAccessors laccForSpringLibraryAccessors = new SpringLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

        /**
         * Creates a dependency provider for jaxws (com.sun.xml.ws:jaxws-ri)
         * This dependency was declared in settings file 'settings.gradle'
         */
        public Provider<MinimalExternalModuleDependency> getJaxws() { return create("jaxws"); }

        /**
         * Creates a dependency provider for postgresql (org.postgresql:postgresql)
         * This dependency was declared in settings file 'settings.gradle'
         */
        public Provider<MinimalExternalModuleDependency> getPostgresql() { return create("postgresql"); }

    /**
     * Returns the group of libraries at spring
     */
    public SpringLibraryAccessors getSpring() { return laccForSpringLibraryAccessors; }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() { return vaccForVersionAccessors; }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() { return baccForBundleAccessors; }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() { return paccForPluginAccessors; }

    public static class SpringLibraryAccessors extends SubDependencyFactory {
        private final SpringBootLibraryAccessors laccForSpringBootLibraryAccessors = new SpringBootLibraryAccessors(owner);

        public SpringLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at spring.boot
         */
        public SpringBootLibraryAccessors getBoot() { return laccForSpringBootLibraryAccessors; }

    }

    public static class SpringBootLibraryAccessors extends SubDependencyFactory {
        private final SpringBootConfigurationLibraryAccessors laccForSpringBootConfigurationLibraryAccessors = new SpringBootConfigurationLibraryAccessors(owner);
        private final SpringBootStarterLibraryAccessors laccForSpringBootStarterLibraryAccessors = new SpringBootStarterLibraryAccessors(owner);

        public SpringBootLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for devtools (org.springframework.boot:spring-boot-devtools)
             * This dependency was declared in settings file 'settings.gradle'
             */
            public Provider<MinimalExternalModuleDependency> getDevtools() { return create("spring.boot.devtools"); }

        /**
         * Returns the group of libraries at spring.boot.configuration
         */
        public SpringBootConfigurationLibraryAccessors getConfiguration() { return laccForSpringBootConfigurationLibraryAccessors; }

        /**
         * Returns the group of libraries at spring.boot.starter
         */
        public SpringBootStarterLibraryAccessors getStarter() { return laccForSpringBootStarterLibraryAccessors; }

    }

    public static class SpringBootConfigurationLibraryAccessors extends SubDependencyFactory {

        public SpringBootConfigurationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for processor (org.springframework.boot:spring-boot-configuration-processor)
             * This dependency was declared in settings file 'settings.gradle'
             */
            public Provider<MinimalExternalModuleDependency> getProcessor() { return create("spring.boot.configuration.processor"); }

    }

    public static class SpringBootStarterLibraryAccessors extends SubDependencyFactory {

        public SpringBootStarterLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for web (org.springframework.boot:spring-boot-starter-web)
             * This dependency was declared in settings file 'settings.gradle'
             */
            public Provider<MinimalExternalModuleDependency> getWeb() { return create("spring.boot.starter.web"); }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final SpringVersionAccessors vaccForSpringVersionAccessors = new SpringVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: jaxws (2.3.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in settings file 'settings.gradle'
             */
            public Provider<String> getJaxws() { return getVersion("jaxws"); }

            /**
             * Returns the version associated to this alias: postgresql (42.5.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in settings file 'settings.gradle'
             */
            public Provider<String> getPostgresql() { return getVersion("postgresql"); }

        /**
         * Returns the group of versions at versions.spring
         */
        public SpringVersionAccessors getSpring() { return vaccForSpringVersionAccessors; }

    }

    public static class SpringVersionAccessors extends VersionFactory  {

        public SpringVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: spring.boot (2.7.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in settings file 'settings.gradle'
             */
            public Provider<String> getBoot() { return getVersion("spring.boot"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

    }

}
