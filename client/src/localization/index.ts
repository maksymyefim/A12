import { useContext } from "react";

import {
    DataFormats,
    Locale,
    LocalizableArgs,
    localizableFromLocalizationTreeMap,
    LocalizationTreeMap,
    Localizer
} from "@com.mgmtp.a12.utils/utils-localization/lib/main";
import { LocalizerContext } from "@com.mgmtp.a12.utils/utils-localization-react/lib/main";

import { en_US } from "./resources/en_US";
import { de_DE } from "./resources/de_DE";

export { RESOURCE_KEYS } from "./keys";

/**
 * Export L10Context for class based components
 */
export interface L10nContext {
    readonly dataFormats: DataFormats;
    readonly locale: Locale;
    readonly localizer: Localizer;
}

export const supportedLocales = [Locale.fromString("en_US"), Locale.fromString("de_DE")];
export const DEFAULT_TRANSLATIONS: LocalizationTreeMap = {
    en: en_US,
    de: de_DE
};

/**
 * Apply default translations to the Localizer and return new Localizer function,
 * which expects only localization key instead of the whole localizable object.
 */
export const applyDefaultTranslations = (localizer: Localizer) => {
    return (key: string, args?: LocalizableArgs) =>
        localizer(localizableFromLocalizationTreeMap(key, DEFAULT_TRANSLATIONS, args)) ?? "";
};

/**
 * Localizer hook, which returns Localizer with applied default translations.
 */
export const useLocalizer = () => {
    const { localizer } = useContext(LocalizerContext);

    return applyDefaultTranslations(localizer);
};
