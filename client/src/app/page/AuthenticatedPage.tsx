import React from "react";

import { FrameFactories } from "@com.mgmtp.a12.client/client-core/lib/core/frame";

import { platformServerConnectors } from "../../appsetup";

import { createViewProvider } from "../viewProvider";
import { customLayoutProvider } from "../layoutProvider";

export const AuthenticatedPage = (): JSX.Element => {
    const rootRegionRef = React.useMemo(() => [], []);
    const RegionUi = React.useMemo(() => FrameFactories.regionUiProvider(rootRegionRef), [rootRegionRef]);
    const viewProvider = React.useMemo(() => createViewProvider(platformServerConnectors.attachmentHandler), []);
    const progressComponentProvider = React.useMemo(() => FrameFactories.createProgressComponentProvider(), []);

    return (
        <RegionUi
            key="region"
            regionReference={rootRegionRef}
            layoutProvider={customLayoutProvider}
            regionUiProvider={FrameFactories.regionUiProvider}
            viewProvider={viewProvider}
            progressComponentProvider={progressComponentProvider}
        />
    );
};
