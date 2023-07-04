import * as React from "react";

import { AttachmentHandler } from "@com.mgmtp.a12.dataservices/dataservices-access";
import { FrameFactories } from "@com.mgmtp.a12.client/client-core/lib/core/frame";
import { View } from "@com.mgmtp.a12.client/client-core/lib/core/view";
import { CRUDViews } from "@com.mgmtp.a12.client/client-core/lib/extensions/crud";
import { ModuleRegistryProvider } from "@com.mgmtp.a12.client/client-core/lib/core/application";
import { StaticPageFactories } from "@com.mgmtp.a12.client/client-core/lib/extensions/static-page";

import { store } from "..";

/**
 * Create Application view provider.
 */
export function createViewProvider(handler?: AttachmentHandler): View.Provider {
    const staticPageComponentProvider = StaticPageFactories.createViewProvider();
    const enginesViewProvider = createEnginesViewProvider(handler);

    function chainedViewProvider(componentName: string): React.ComponentType<View> {
        return (
            staticPageComponentProvider(componentName) ||
            enginesViewProvider(componentName) ||
            FrameFactories.viewProvider(componentName) ||
            Placeholder
        );
    }

    return ModuleRegistryProvider.getViewProvider(store.getState(), chainedViewProvider);
}

/**
 * Create View provider for Overview and Form engine.
 */
function createEnginesViewProvider(
    attachmentHandler?: AttachmentHandler
): (componentName: string) => React.ComponentType<View> | undefined {
    const components: { [name: string]: React.ComponentType<View> | undefined } = {
        FormCRUD(props) {
            return <CRUDViews.FormEngineView {...props} attachmentHandler={attachmentHandler} />;
        },
        OverviewCRUD(props) {
            return <CRUDViews.OverviewEngineView {...props} attachmentHandler={attachmentHandler} />;
        }
    };
    return name => {
        return components[name];
    };
}

function Placeholder(props: View): JSX.Element {
    return <div>No view renderer found: `{props.name}`</div>;
}
