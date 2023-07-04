import * as React from "react";

import { FrameFactories, FrameViews } from "@com.mgmtp.a12.client/client-core/lib/core/frame";
import { UaaClient } from "@com.mgmtp.a12.uaa/uaa-authentication-client";

import { RESOURCE_KEYS, useLocalizer } from "../localization";

export const customLayoutProvider: FrameViews.LayoutProvider = (name: string) => {
    // "ApplicationFrame" is hardcoded layout name taken from app model and client defaults
    return name === "ApplicationFrame"
        ? { component: CustomApplicationFrameLayout }
        : FrameFactories.layoutProvider(name);
};

function CustomApplicationFrameLayout(props: FrameViews.ApplicationFrameLayoutProps): JSX.Element {
    const localizer = useLocalizer();
    const uaaClient = UaaClient.getOidcClient();

    return (
        <FrameViews.ApplicationFrameLayout
            {...props}
            additionalHeaderItems={[
                {
                    item: (
                        <uaaClient.components.UserInfoHeader
                            mobileMode={false}
                            loggedInAsLabel={localizer(RESOURCE_KEYS.application.header.userinfo.labels.loggedInAs)}
                            logoutButtonLabel={localizer(RESOURCE_KEYS.application.header.userinfo.labels.logoutButton)}
                        />
                    ),
                    orientation: "rightSlots-left"
                }
            ]}
        />
    );
}
