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
 * A catalog of dependencies accessible via the `a12Libs` extension.
*/
@NonNullApi
public class LibrariesForA12Libs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final BaseLibraryAccessors laccForBaseLibraryAccessors = new BaseLibraryAccessors(owner);
    private final DataservicesLibraryAccessors laccForDataservicesLibraryAccessors = new DataservicesLibraryAccessors(owner);
    private final FormLibraryAccessors laccForFormLibraryAccessors = new FormLibraryAccessors(owner);
    private final KernelLibraryAccessors laccForKernelLibraryAccessors = new KernelLibraryAccessors(owner);
    private final UaaLibraryAccessors laccForUaaLibraryAccessors = new UaaLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForA12Libs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Returns the group of libraries at base
     */
    public BaseLibraryAccessors getBase() { return laccForBaseLibraryAccessors; }

    /**
     * Returns the group of libraries at dataservices
     */
    public DataservicesLibraryAccessors getDataservices() { return laccForDataservicesLibraryAccessors; }

    /**
     * Returns the group of libraries at form
     */
    public FormLibraryAccessors getForm() { return laccForFormLibraryAccessors; }

    /**
     * Returns the group of libraries at kernel
     */
    public KernelLibraryAccessors getKernel() { return laccForKernelLibraryAccessors; }

    /**
     * Returns the group of libraries at uaa
     */
    public UaaLibraryAccessors getUaa() { return laccForUaaLibraryAccessors; }

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

    public static class BaseLibraryAccessors extends SubDependencyFactory {

        public BaseLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for bom (com.mgmtp.a12.base:base-virtual-bom)
             * This dependency was declared in settings file 'settings.gradle'
             */
            public Provider<MinimalExternalModuleDependency> getBom() { return create("base.bom"); }

    }

    public static class DataservicesLibraryAccessors extends SubDependencyFactory {
        private final DataservicesServerLibraryAccessors laccForDataservicesServerLibraryAccessors = new DataservicesServerLibraryAccessors(owner);

        public DataservicesLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for bom (com.mgmtp.a12.dataservices:dataservices-parent)
             * This dependency was declared in settings file 'settings.gradle'
             */
            public Provider<MinimalExternalModuleDependency> getBom() { return create("dataservices.bom"); }

        /**
         * Returns the group of libraries at dataservices.server
         */
        public DataservicesServerLibraryAccessors getServer() { return laccForDataservicesServerLibraryAccessors; }

    }

    public static class DataservicesServerLibraryAccessors extends SubDependencyFactory {
        private final DataservicesServerInitLibraryAccessors laccForDataservicesServerInitLibraryAccessors = new DataservicesServerInitLibraryAccessors(owner);

        public DataservicesServerLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for app (com.mgmtp.a12.dataservices:dataservices-server-app)
             * This dependency was declared in settings file 'settings.gradle'
             */
            public Provider<MinimalExternalModuleDependency> getApp() { return create("dataservices.server.app"); }

        /**
         * Returns the group of libraries at dataservices.server.init
         */
        public DataservicesServerInitLibraryAccessors getInit() { return laccForDataservicesServerInitLibraryAccessors; }

    }

    public static class DataservicesServerInitLibraryAccessors extends SubDependencyFactory {

        public DataservicesServerInitLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for app (com.mgmtp.a12.dataservices:dataservices-server-init-app)
             * This dependency was declared in settings file 'settings.gradle'
             */
            public Provider<MinimalExternalModuleDependency> getApp() { return create("dataservices.server.init.app"); }

    }

    public static class FormLibraryAccessors extends SubDependencyFactory {
        private final FormEngineLibraryAccessors laccForFormEngineLibraryAccessors = new FormEngineLibraryAccessors(owner);

        public FormLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at form.engine
         */
        public FormEngineLibraryAccessors getEngine() { return laccForFormEngineLibraryAccessors; }

    }

    public static class FormEngineLibraryAccessors extends SubDependencyFactory {

        public FormEngineLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for bom (com.mgmtp.a12.formengine:formengine-virtual-bom)
             * This dependency was declared in settings file 'settings.gradle'
             */
            public Provider<MinimalExternalModuleDependency> getBom() { return create("form.engine.bom"); }

    }

    public static class KernelLibraryAccessors extends SubDependencyFactory {

        public KernelLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for bom (com.mgmtp.a12.kernel:kernel-bom)
             * This dependency was declared in settings file 'settings.gradle'
             */
            public Provider<MinimalExternalModuleDependency> getBom() { return create("kernel.bom"); }

    }

    public static class UaaLibraryAccessors extends SubDependencyFactory {
        private final UaaAuthenticationLibraryAccessors laccForUaaAuthenticationLibraryAccessors = new UaaAuthenticationLibraryAccessors(owner);

        public UaaLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for bom (com.mgmtp.a12.uaa:uaa-bom)
             * This dependency was declared in settings file 'settings.gradle'
             */
            public Provider<MinimalExternalModuleDependency> getBom() { return create("uaa.bom"); }

        /**
         * Returns the group of libraries at uaa.authentication
         */
        public UaaAuthenticationLibraryAccessors getAuthentication() { return laccForUaaAuthenticationLibraryAccessors; }

    }

    public static class UaaAuthenticationLibraryAccessors extends SubDependencyFactory {
        private final UaaAuthenticationUserLibraryAccessors laccForUaaAuthenticationUserLibraryAccessors = new UaaAuthenticationUserLibraryAccessors(owner);

        public UaaAuthenticationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at uaa.authentication.user
         */
        public UaaAuthenticationUserLibraryAccessors getUser() { return laccForUaaAuthenticationUserLibraryAccessors; }

    }

    public static class UaaAuthenticationUserLibraryAccessors extends SubDependencyFactory {
        private final UaaAuthenticationUserExtensionLibraryAccessors laccForUaaAuthenticationUserExtensionLibraryAccessors = new UaaAuthenticationUserExtensionLibraryAccessors(owner);

        public UaaAuthenticationUserLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at uaa.authentication.user.extension
         */
        public UaaAuthenticationUserExtensionLibraryAccessors getExtension() { return laccForUaaAuthenticationUserExtensionLibraryAccessors; }

    }

    public static class UaaAuthenticationUserExtensionLibraryAccessors extends SubDependencyFactory {
        private final UaaAuthenticationUserExtensionSpringLibraryAccessors laccForUaaAuthenticationUserExtensionSpringLibraryAccessors = new UaaAuthenticationUserExtensionSpringLibraryAccessors(owner);

        public UaaAuthenticationUserExtensionLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at uaa.authentication.user.extension.spring
         */
        public UaaAuthenticationUserExtensionSpringLibraryAccessors getSpring() { return laccForUaaAuthenticationUserExtensionSpringLibraryAccessors; }

    }

    public static class UaaAuthenticationUserExtensionSpringLibraryAccessors extends SubDependencyFactory {
        private final UaaAuthenticationUserExtensionSpringBootLibraryAccessors laccForUaaAuthenticationUserExtensionSpringBootLibraryAccessors = new UaaAuthenticationUserExtensionSpringBootLibraryAccessors(owner);

        public UaaAuthenticationUserExtensionSpringLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at uaa.authentication.user.extension.spring.boot
         */
        public UaaAuthenticationUserExtensionSpringBootLibraryAccessors getBoot() { return laccForUaaAuthenticationUserExtensionSpringBootLibraryAccessors; }

    }

    public static class UaaAuthenticationUserExtensionSpringBootLibraryAccessors extends SubDependencyFactory {

        public UaaAuthenticationUserExtensionSpringBootLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for autoconfigure (com.mgmtp.a12.uaa:uaa-authentication-user-extension-spring-boot-autoconfigure)
             * This dependency was declared in settings file 'settings.gradle'
             */
            public Provider<MinimalExternalModuleDependency> getAutoconfigure() { return create("uaa.authentication.user.extension.spring.boot.autoconfigure"); }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final FormVersionAccessors vaccForFormVersionAccessors = new FormVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: base (26.3.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in settings file 'settings.gradle'
             */
            public Provider<String> getBase() { return getVersion("base"); }

            /**
             * Returns the version associated to this alias: dataservices (35.0.5)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in settings file 'settings.gradle'
             */
            public Provider<String> getDataservices() { return getVersion("dataservices"); }

            /**
             * Returns the version associated to this alias: kernel (27.5.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in settings file 'settings.gradle'
             */
            public Provider<String> getKernel() { return getVersion("kernel"); }

            /**
             * Returns the version associated to this alias: uaa (6.1.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in settings file 'settings.gradle'
             */
            public Provider<String> getUaa() { return getVersion("uaa"); }

        /**
         * Returns the group of versions at versions.form
         */
        public FormVersionAccessors getForm() { return vaccForFormVersionAccessors; }

    }

    public static class FormVersionAccessors extends VersionFactory  {

        public FormVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: form.engine (35.1.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in settings file 'settings.gradle'
             */
            public Provider<String> getEngine() { return getVersion("form.engine"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

    }

}
