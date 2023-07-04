import {
    AuthenticationState,
    UaaClient,
    UaaClientConfiguration,
    UaaSelectors
} from "@com.mgmtp.a12.uaa/uaa-authentication-client";

export async function uaaIntegration(clientConfiguration: UaaClientConfiguration) {
    await UaaClient.init(clientConfiguration);
    if (window.location.href.indexOf("state") !== -1) {
        console.log("Uaa Process Callback.");
        UaaClient.getOidcClient().initConnector();
        await UaaClient.getOidcClient().processLoginCallback();
        const baseUrl = window.location.href.split("?")[0];
        window.history.pushState("name", "", baseUrl);
    } else {
        console.log("Start trigger UAA process for login.");
        const authenticatedState = UaaSelectors.state(clientConfiguration.store?.getState());
        const isNotAuthenticated = authenticatedState === AuthenticationState.NOT_AUTHENTICATED;
        if (isNotAuthenticated) {
            UaaClient.getOidcClient().login();
        }
    }
}
