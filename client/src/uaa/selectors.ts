import { Selector } from "@com.mgmtp.a12.client/client-core/lib/core/store";

interface UaaState {
    uaa?: {
        locale?: UaaStateLocale;
    };
}
interface UaaStateLocale {
    language: string;
    country: string;
}

export function UaaLocaleSelected(): Selector<UaaStateLocale | undefined> {
    return (state: UaaState) => {
        if (state.uaa !== undefined && state.uaa.locale !== undefined) {
            return state.uaa.locale;
        }
        return undefined;
    };
}
