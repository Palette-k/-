declare const _default: import("vue").DefineComponent<{
    readonly to: import("../../../utils").BuildPropReturn<import("../../../utils").PropWrapper<import("vue-router").RouteLocationRaw>, "", unknown, unknown, unknown>;
    readonly replace: import("../../../utils").BuildPropReturn<BooleanConstructor, false, unknown, unknown, unknown>;
}, {
    ns: {
        namespace: import("vue").ComputedRef<string>;
        b: (blockSuffix?: string) => string;
        e: (element?: string | undefined) => string;
        m: (modifier?: string | undefined) => string;
        be: (blockSuffix?: string | undefined, element?: string | undefined) => string;
        em: (element?: string | undefined, modifier?: string | undefined) => string;
        bm: (blockSuffix?: string | undefined, modifier?: string | undefined) => string;
        bem: (blockSuffix?: string | undefined, element?: string | undefined, modifier?: string | undefined) => string;
        is: {
            (name: string, state: boolean | undefined): string;
            (name: string): string;
        };
    };
    link: import("vue").Ref<HTMLSpanElement | undefined>;
    separator: string | undefined;
    separatorIcon: import("../../../utils").BuildPropType<import("../../../utils").PropWrapper<string | import("vue").Component<any, any, any, import("vue").ComputedOptions, import("vue").MethodOptions>>, unknown, unknown> | undefined;
}, unknown, {}, {}, import("vue").ComponentOptionsMixin, import("vue").ComponentOptionsMixin, Record<string, any>, string, import("vue").VNodeProps & import("vue").AllowedComponentProps & import("vue").ComponentCustomProps, Readonly<import("vue").ExtractPropTypes<{
    readonly to: import("../../../utils").BuildPropReturn<import("../../../utils").PropWrapper<import("vue-router").RouteLocationRaw>, "", unknown, unknown, unknown>;
    readonly replace: import("../../../utils").BuildPropReturn<BooleanConstructor, false, unknown, unknown, unknown>;
}>>, {
    replace: import("../../../utils").BuildPropType<BooleanConstructor, unknown, unknown>;
    to: import("../../../utils").BuildPropType<import("../../../utils").PropWrapper<import("vue-router").RouteLocationRaw>, unknown, unknown>;
}>;
export default _default;
