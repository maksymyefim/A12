import { LocalizationKeyTreeType } from "../keys";

export const en_US: LocalizationKeyTreeType = {
    application: {
        title: "training",
        header: {
            userinfo: {
                labels: {
                    loggedInAs: "Logged in as",
                    logoutButton: "Logout"
                }
            }
        }
    },
    veto: {
        title: "Cancel Process",
        message: "Are you want to cancel the process? Please be aware that your changes will be discarded",
        buttonDiscard: "Discard",
        buttonAbort: "Cancel",
        unsavedOrLockedActivties: ""
    },
    error: {
        INTERNAL_CLIENT_ERROR: "Internal Client Error",
        VALIDATION_ERROR: "Validation Error",
        PAGE_NOT_FOUND_ERROR: "Page not found",
        SAVING_FAILED_ERROR: "Save failed",
        HTTP_ERROR: "HTTP Error",
        UNKNOWN_ERROR: "Internal Error"
    },
    externalEnumeration: {
        EmailBetreff: {
            "0": {
                label: "Waiting for BiFi-Billing"
            },
            "1": {
                label: "Information requested"
            }
        }
    },
    features: {
        notification: {
            title: {
                info: "Info",
                success: "Success",
                warning: "Warning",
                error: "Error"
            },
            content: "$message$"
        }
    }
};
