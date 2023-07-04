import { LocalizationKeyTreeType } from "../keys";

export const de_DE: LocalizationKeyTreeType = {
    application: {
        title: "training",
        header: {
            userinfo: {
                labels: {
                    loggedInAs: "Angemeldet als",
                    logoutButton: "Ausloggen"
                }
            }
        }
    },
    veto: {
        title: "Vorgang abbrechen",
        message:
            "Sind Sie sicher, dass Sie den Vorgang abbrechen möchten? Bitte beachten Sie, dass getätigte Eingaben verworfen werden.",
        buttonDiscard: "Ja, Vorgang abbrechen",
        buttonAbort: "Nein, zurück zum Formular",
        unsavedOrLockedActivties: ""
    },
    error: {
        INTERNAL_CLIENT_ERROR: "Interner Client Fehler",
        VALIDATION_ERROR: "Validierungsfehler",
        PAGE_NOT_FOUND_ERROR: "Seite nicht gefunden",
        SAVING_FAILED_ERROR: "Speichern fehlgeschlagen",
        HTTP_ERROR: "HTTP Fehler",
        UNKNOWN_ERROR: "Interner Fehler"
    },
    externalEnumeration: {
        EmailBetreff: {
            "0": {
                label: "Warten auf BiFi-Abrechnung"
            },
            "1": {
                label: "Informationen angefordert"
            }
        }
    },
    features: {
        notification: {
            title: {
                success: "Erfolg",
                info: "Info",
                warning: "Warnung",
                error: "Fehler"
            },
            content: "$message$"
        }
    }
};
