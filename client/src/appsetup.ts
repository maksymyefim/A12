import "./config";

import {
    UaaActions,
    UaaClientConfiguration,
    UaaMiddlewares,
    UaaReducer
} from "@com.mgmtp.a12.uaa/uaa-authentication-client";
import { ActivitySelectors } from "@com.mgmtp.a12.client/client-core/lib/core/activity";
import { ApplicationFactories, ApplicationSetup } from "@com.mgmtp.a12.client/client-core/lib/core/application";
import { DataHandlers } from "@com.mgmtp.a12.client/client-core/lib/core/data";
import { LocaleSelectors } from "@com.mgmtp.a12.client/client-core/lib/core/locale";
import { StorageFactories } from "@com.mgmtp.a12.client/client-core/lib/devtools/storage";
import {
    formEngineDataReducers,
    formEngineSagas,
    createFormEngineMiddlewares
} from "@com.mgmtp.a12.client/client-core/lib/extensions/form-engine";
import { CRUDFactories } from "@com.mgmtp.a12.client/client-core/lib/extensions/crud";
import { DirtyHandlingFactories } from "@com.mgmtp.a12.client/client-core/lib/extensions/dirtyHandling";
import { configure } from "@com.mgmtp.a12.client/client-core/lib/extensions/platform-server-connectors";
import { DeepLinkingFactories } from "@com.mgmtp.a12.client/client-core/lib/extensions/deep-linking";

import * as appModel from "./appmodel.json";
import { registerModulesOnLoginMiddleware, unregisterModulesOnLogoutMiddleware } from "./modules";
import { setLanguageSelectedInLoginForm } from "./uaa/sagas";
import { isProduction } from "./config";
import { uaaIntegration } from "./uaa/integration";
import { devToolMiddleware, enableReduxDevTools } from "./config/devtools";
import { setLocaleKeycloakOnLoginMiddleware } from "./middlewares";

let config: ApplicationSetup;

export const platformServerConnectors = configure({
    localeProvider: () => LocaleSelectors.locale()(config.store.getState())
});

const sessionStorage = StorageFactories.createStorage("session");
const languagePersistingMiddleware = StorageFactories.createLanguagePersistingMiddleware(sessionStorage);

export function setup(): {
    config: ApplicationSetup;
    initialStoreActions(): Promise<void>;
} {
    const dataHandlers: DataHandlers = {
        dataEditors: [],
        dataLoaders: platformServerConnectors.loaders.dataLoaders,
        dataProviders: [ApplicationFactories.createEmptyDocumentDataProvider()]
    };

    config = ApplicationFactories.createApplicationSetup({
        model: appModel,
        modelLoaders: platformServerConnectors.loaders.modelLoaders,
        applicationBusyTriggers: {
            start: [UaaActions.loggingInLocal],
            end: [UaaActions.loggedIn, UaaActions.loginFailed]
        },
        applicationResetTriggers: {
            resetRequested: [UaaActions.logoutRequested],
            resetConfirmed: UaaActions.loggingOut(),
            reset: [UaaActions.loggedOut]
        },
        dataHandlers,
        overridePlatformSagas: [...DirtyHandlingFactories.createSagas()],
        customSagas: [
            ...CRUDFactories.createSagas(),
            ...formEngineSagas,
            setLanguageSelectedInLoginForm,
            DeepLinkingFactories.createWelcomePageSaga({ applyTriggers: [UaaActions.loggedIn] })
        ],
        preComputeNewDocuments: true,
        composeEnhancer: isProduction ? undefined : enableReduxDevTools(),
        additionalMiddlewares: [
            ...createFormEngineMiddlewares(),
            CRUDFactories.createCRUDMiddleware(),
            languagePersistingMiddleware,
            registerModulesOnLoginMiddleware,
            unregisterModulesOnLogoutMiddleware,
            ...platformServerConnectors.middlewares,
            setLocaleKeycloakOnLoginMiddleware,
            devToolMiddleware(),
            ...UaaMiddlewares()
        ],
        dataReducers: [...formEngineDataReducers],
        reducerMap: {
            uaa: UaaReducer
        }
    });
    const clientConfiguration: UaaClientConfiguration = {
        serverURL: "/api",
        automaticallyLogin: true,
        store: config.store
    };
    /*
     * Listen to the window.onbeforeunload event to trigger a dialog
     * if there are dirty or locked activities when the application gets closed.
     */
    window.onbeforeunload = () => {
        // Show the dialog if there are dirty or locked activities.
        const dirtySubTree = ActivitySelectors.allDirtyOrLockedActivities()(config.store.getState());
        if (dirtySubTree.length > 0) {
            /* This string will not be shown in most modern browser versions,
             * instead a browser specific message will be shown:
             * https://developer.mozilla.org/en-US/docs/Web/API/WindowEventHandlers/onbeforeunload#Browser_compatibility
             *
             * Current Firefox (version 100.0.x) and Chromium (version 101.0.x) display a browser-specific alert box.
             */
            return "Changes you made may not be saved.";
        } else {
            return undefined;
        }
    };
    return {
        config,
        initialStoreActions: async () => {
            await uaaIntegration(clientConfiguration);
        }
    };
}
