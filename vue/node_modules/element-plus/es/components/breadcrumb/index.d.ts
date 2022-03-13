export declare const ElBreadcrumb: import("element-plus/es/utils").SFCWithInstall<import("vue").DefineComponent<{
    readonly separator: import("element-plus/es/utils").BuildPropReturn<StringConstructor, "/", unknown, unknown, unknown>;
    readonly separatorIcon: import("element-plus/es/utils").BuildPropReturn<import("element-plus/es/utils").PropWrapper<string | import("vue").Component<any, any, any, import("vue").ComputedOptions, import("vue").MethodOptions>>, "", unknown, unknown, unknown>;
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
    breadcrumb: import("vue").Ref<HTMLDivElement | undefined>;
}, unknown, {}, {}, import("vue").ComponentOptionsMixin, import("vue").ComponentOptionsMixin, Record<string, any>, string, import("vue").VNodeProps & import("vue").AllowedComponentProps & import("vue").ComponentCustomProps, Readonly<import("vue").ExtractPropTypes<{
    readonly separator: import("element-plus/es/utils").BuildPropReturn<StringConstructor, "/", unknown, unknown, unknown>;
    readonly separatorIcon: import("element-plus/es/utils").BuildPropReturn<import("element-plus/es/utils").PropWrapper<string | import("vue").Component<any, any, any, import("vue").ComputedOptions, import("vue").MethodOptions>>, "", unknown, unknown, unknown>;
}>>, {
    separator: string;
    separatorIcon: import("element-plus/es/utils").BuildPropType<import("element-plus/es/utils").PropWrapper<string | import("vue").Component<any, any, any, import("vue").ComputedOptions, import("vue").MethodOptions>>, unknown, unknown>;
}>> & {
    BreadcrumbItem: import("vue").DefineComponent<{
        readonly to: import("element-plus/es/utils").BuildPropReturn<import("element-plus/es/utils").PropWrapper<import("vue-router").RouteLocationRaw>, "", unknown, unknown, unknown>;
        readonly replace: import("element-plus/es/utils").BuildPropReturn<BooleanConstructor, false, unknown, unknown, unknown>;
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
        separatorIcon: import("element-plus/es/utils").BuildPropType<import("element-plus/es/utils").PropWrapper<string | import("vue").Component<any, any, any, import("vue").ComputedOptions, import("vue").MethodOptions>>, unknown, unknown> | undefined;
    }, unknown, {}, {}, import("vue").ComponentOptionsMixin, import("vue").ComponentOptionsMixin, Record<string, any>, string, import("vue").VNodeProps & import("vue").AllowedComponentProps & import("vue").ComponentCustomProps, Readonly<import("vue").ExtractPropTypes<{
        readonly to: import("element-plus/es/utils").BuildPropReturn<import("element-plus/es/utils").PropWrapper<import("vue-router").RouteLocationRaw>, "", unknown, unknown, unknown>;
        readonly replace: import("element-plus/es/utils").BuildPropReturn<BooleanConstructor, false, unknown, unknown, unknown>;
    }>>, {
        replace: import("element-plus/es/utils").BuildPropType<BooleanConstructor, unknown, unknown>;
        to: import("element-plus/es/utils").BuildPropType<import("element-plus/es/utils").PropWrapper<import("vue-router").RouteLocationRaw>, unknown, unknown>;
    }>;
};
export declare const ElBreadcrumbItem: import("element-plus/es/utils").SFCWithInstall<import("vue").DefineComponent<{
    readonly to: import("element-plus/es/utils").BuildPropReturn<import("element-plus/es/utils").PropWrapper<import("vue-router").RouteLocationRaw>, "", unknown, unknown, unknown>;
    readonly replace: import("element-plus/es/utils").BuildPropReturn<BooleanConstructor, false, unknown, unknown, unknown>;
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
    separatorIcon: import("element-plus/es/utils").BuildPropType<import("element-plus/es/utils").PropWrapper<string | import("vue").Component<any, any, any, import("vue").ComputedOptions, import("vue").MethodOptions>>, unknown, unknown> | undefined;
}, unknown, {}, {}, import("vue").ComponentOptionsMixin, import("vue").ComponentOptionsMixin, Record<string, any>, string, import("vue").VNodeProps & import("vue").AllowedComponentProps & import("vue").ComponentCustomProps, Readonly<import("vue").ExtractPropTypes<{
    readonly to: import("element-plus/es/utils").BuildPropReturn<import("element-plus/es/utils").PropWrapper<import("vue-router").RouteLocationRaw>, "", unknown, unknown, unknown>;
    readonly replace: import("element-plus/es/utils").BuildPropReturn<BooleanConstructor, false, unknown, unknown, unknown>;
}>>, {
    replace: import("element-plus/es/utils").BuildPropType<BooleanConstructor, unknown, unknown>;
    to: import("element-plus/es/utils").BuildPropType<import("element-plus/es/utils").PropWrapper<import("vue-router").RouteLocationRaw>, unknown, unknown>;
}>>;
export default ElBreadcrumb;
export * from './src/breadcrumb';
export * from './src/breadcrumb-item';
