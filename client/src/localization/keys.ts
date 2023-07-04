import { initializeKeys } from "@com.mgmtp.a12.utils/utils-localization/lib/main";
import { DIRTY_HANDLING_RESOURCE_KEYS } from "@com.mgmtp.a12.client/client-core/lib/extensions/dirtyHandling";
import { CDM_RESOURCE_KEYS } from "@com.mgmtp.a12.client/client-core/lib/extensions/cdm";
import { CRUD_RESOURCE_KEYS } from "@com.mgmtp.a12.client/client-core/lib/extensions/crud";
import { FRAME_RESOURCE_KEYS, LOCALE_SELECT_RESOURCE_KEYS } from "@com.mgmtp.a12.client/client-core/lib/core/frame";
import { HETEROGENEITY_RESOURCE_KEYS } from "@com.mgmtp.a12.client/client-core/lib/extensions/heterogeneity";
import { LOCALE_RESOURCE_KEYS } from "@com.mgmtp.a12.client/client-core/lib/core/locale";
import { RELATIONSHIP_RESOURCE_KEYS } from "@com.mgmtp.a12.client/client-core/lib/extensions/relationship";
import { RESOURCE_KEYS as FORMENGINE_RESOURCE_KEYS } from "@com.mgmtp.a12.formengine/formengine-core/lib/back-end/localization";
import { RESOURCE_KEYS as OVERVIEWENGINE_RESOURCE_KEYS } from "@com.mgmtp.a12.overviewengine/overviewengine-core/lib/main/services/localization";

/** This map should only contain custom keys. */
export const RESOURCE_KEYS = {
    application: {
        header: {
            userinfo: {
                labels: {
                    loggedInAs: "",
                    logoutButton: ""
                }
            }
        }
    },
    error: {
        INTERNAL_CLIENT_ERROR: "",
        VALIDATION_ERROR: "",
        PAGE_NOT_FOUND_ERROR: "",
        SAVING_FAILED_ERROR: "",
        HTTP_ERROR: "",
        UNKNOWN_ERROR: ""
    },
    externalEnumeration: {
        EmailBetreff: {
            0: {
                label: ""
            },
            1: {
                label: ""
            }
        }
    },
    features: {
        notification: {
            title: {
                info: "",
                error: "",
                success: "",
                warning: ""
            },
            content: ""
        }
    }
};

initializeKeys(RESOURCE_KEYS);

type DeepPartial<T> = T extends object ? { [P in keyof T]?: DeepPartial<T[P]> } : T;

/** Creates a typing for a localization tree map with A12 localizable keys. */
export type LocalizationKeyTreeType = typeof RESOURCE_KEYS &
    DeepPartial<typeof CDM_RESOURCE_KEYS> &
    DeepPartial<typeof CRUD_RESOURCE_KEYS> &
    DeepPartial<typeof DIRTY_HANDLING_RESOURCE_KEYS> &
    DeepPartial<typeof FRAME_RESOURCE_KEYS> &
    DeepPartial<typeof HETEROGENEITY_RESOURCE_KEYS> &
    DeepPartial<typeof LOCALE_RESOURCE_KEYS> &
    DeepPartial<typeof LOCALE_SELECT_RESOURCE_KEYS> &
    DeepPartial<typeof RELATIONSHIP_RESOURCE_KEYS> &
    DeepPartial<typeof FORMENGINE_RESOURCE_KEYS> &
    DeepPartial<typeof OVERVIEWENGINE_RESOURCE_KEYS>;
