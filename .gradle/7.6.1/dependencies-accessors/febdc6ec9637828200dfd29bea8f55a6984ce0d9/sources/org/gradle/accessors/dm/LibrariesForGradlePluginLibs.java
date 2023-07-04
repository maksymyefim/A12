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
 * A catalog of dependencies accessible via the `gradlePluginLibs` extension.
*/
@NonNullApi
public class LibrariesForGradlePluginLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForGradlePluginLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

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

    public static class VersionAccessors extends VersionFactory  {

        private final DockerVersionAccessors vaccForDockerVersionAccessors = new DockerVersionAccessors(providers, config);
        private final NodeVersionAccessors vaccForNodeVersionAccessors = new NodeVersionAccessors(providers, config);
        private final SpringVersionAccessors vaccForSpringVersionAccessors = new SpringVersionAccessors(providers, config);
        private final TestVersionAccessors vaccForTestVersionAccessors = new TestVersionAccessors(providers, config);
        private final VersionsVersionAccessors vaccForVersionsVersionAccessors = new VersionsVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.docker
         */
        public DockerVersionAccessors getDocker() { return vaccForDockerVersionAccessors; }

        /**
         * Returns the group of versions at versions.node
         */
        public NodeVersionAccessors getNode() { return vaccForNodeVersionAccessors; }

        /**
         * Returns the group of versions at versions.spring
         */
        public SpringVersionAccessors getSpring() { return vaccForSpringVersionAccessors; }

        /**
         * Returns the group of versions at versions.test
         */
        public TestVersionAccessors getTest() { return vaccForTestVersionAccessors; }

        /**
         * Returns the group of versions at versions.versions
         */
        public VersionsVersionAccessors getVersions() { return vaccForVersionsVersionAccessors; }

    }

    public static class DockerVersionAccessors extends VersionFactory  {

        private final DockerRemoteVersionAccessors vaccForDockerRemoteVersionAccessors = new DockerRemoteVersionAccessors(providers, config);
        public DockerVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: docker.compose (0.16.8)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in settings file 'settings.gradle'
             */
            public Provider<String> getCompose() { return getVersion("docker.compose"); }

        /**
         * Returns the group of versions at versions.docker.remote
         */
        public DockerRemoteVersionAccessors getRemote() { return vaccForDockerRemoteVersionAccessors; }

    }

    public static class DockerRemoteVersionAccessors extends VersionFactory  {

        public DockerRemoteVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: docker.remote.api (7.0.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in settings file 'settings.gradle'
             */
            public Provider<String> getApi() { return getVersion("docker.remote.api"); }

    }

    public static class NodeVersionAccessors extends VersionFactory  {

        public NodeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: node.gradle (3.4.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in settings file 'settings.gradle'
             */
            public Provider<String> getGradle() { return getVersion("node.gradle"); }

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

    public static class TestVersionAccessors extends VersionFactory  {

        public TestVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: test.logger (3.2.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in settings file 'settings.gradle'
             */
            public Provider<String> getLogger() { return getVersion("test.logger"); }

    }

    public static class VersionsVersionAccessors extends VersionFactory  {

        public VersionsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: versions.update (0.42.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in settings file 'settings.gradle'
             */
            public Provider<String> getUpdate() { return getVersion("versions.update"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {
        private final DockerPluginAccessors paccForDockerPluginAccessors = new DockerPluginAccessors(providers, config);
        private final NodePluginAccessors paccForNodePluginAccessors = new NodePluginAccessors(providers, config);
        private final SpringPluginAccessors paccForSpringPluginAccessors = new SpringPluginAccessors(providers, config);
        private final TestPluginAccessors paccForTestPluginAccessors = new TestPluginAccessors(providers, config);
        private final VersionsPluginAccessors paccForVersionsPluginAccessors = new VersionsPluginAccessors(providers, config);

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of plugins at plugins.docker
         */
        public DockerPluginAccessors getDocker() { return paccForDockerPluginAccessors; }

        /**
         * Returns the group of plugins at plugins.node
         */
        public NodePluginAccessors getNode() { return paccForNodePluginAccessors; }

        /**
         * Returns the group of plugins at plugins.spring
         */
        public SpringPluginAccessors getSpring() { return paccForSpringPluginAccessors; }

        /**
         * Returns the group of plugins at plugins.test
         */
        public TestPluginAccessors getTest() { return paccForTestPluginAccessors; }

        /**
         * Returns the group of plugins at plugins.versions
         */
        public VersionsPluginAccessors getVersions() { return paccForVersionsPluginAccessors; }

    }

    public static class DockerPluginAccessors extends PluginFactory {
        private final DockerRemotePluginAccessors paccForDockerRemotePluginAccessors = new DockerRemotePluginAccessors(providers, config);

        public DockerPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for docker.compose to the plugin id 'com.avast.gradle.docker-compose'
             * This plugin was declared in settings file 'settings.gradle'
             */
            public Provider<PluginDependency> getCompose() { return createPlugin("docker.compose"); }

        /**
         * Returns the group of plugins at plugins.docker.remote
         */
        public DockerRemotePluginAccessors getRemote() { return paccForDockerRemotePluginAccessors; }

    }

    public static class DockerRemotePluginAccessors extends PluginFactory {

        public DockerRemotePluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for docker.remote.api to the plugin id 'com.bmuschko.docker-remote-api'
             * This plugin was declared in settings file 'settings.gradle'
             */
            public Provider<PluginDependency> getApi() { return createPlugin("docker.remote.api"); }

    }

    public static class NodePluginAccessors extends PluginFactory {

        public NodePluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for node.gradle to the plugin id 'com.github.node-gradle.node'
             * This plugin was declared in settings file 'settings.gradle'
             */
            public Provider<PluginDependency> getGradle() { return createPlugin("node.gradle"); }

    }

    public static class SpringPluginAccessors extends PluginFactory {

        public SpringPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for spring.boot to the plugin id 'org.springframework.boot'
             * This plugin was declared in settings file 'settings.gradle'
             */
            public Provider<PluginDependency> getBoot() { return createPlugin("spring.boot"); }

    }

    public static class TestPluginAccessors extends PluginFactory {

        public TestPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for test.logger to the plugin id 'com.adarshr.test-logger'
             * This plugin was declared in settings file 'settings.gradle'
             */
            public Provider<PluginDependency> getLogger() { return createPlugin("test.logger"); }

    }

    public static class VersionsPluginAccessors extends PluginFactory {

        public VersionsPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for versions.update to the plugin id 'com.github.ben-manes.versions'
             * This plugin was declared in settings file 'settings.gradle'
             */
            public Provider<PluginDependency> getUpdate() { return createPlugin("versions.update"); }

    }

}
