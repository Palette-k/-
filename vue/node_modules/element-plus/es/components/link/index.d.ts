export declare const ElLink: import("element-plus/es/utils").SFCWithInstall<import("vue").DefineComponent<{
    readonly type: import("element-plus/es/utils").BuildPropReturn<StringConstructor, "default", unknown, "default" | "primary" | "success" | "warning" | "info" | "danger", unknown>;
    readonly underline: import("element-plus/es/utils").BuildPropReturn<BooleanConstructor, true, unknown, unknown, unknown>;
    readonly disabled: import("element-plus/es/utils").BuildPropReturn<BooleanConstructor, false, unknown, unknown, unknown>;
    readonly href: import("element-plus/es/utils").BuildPropReturn<StringConstructor, "", unknown, unknown, unknown>;
    readonly icon: import("element-plus/es/utils").BuildPropReturn<import("element-plus/es/utils").PropWrapper<string | import("vue").Component<any, any, any, import("vue").ComputedOptions, import("vue").MethodOptions>>, "", unknown, unknown, unknown>;
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
    handleClick: (event: MouseEvent) => void;
}, unknown, {}, {}, import("vue").ComponentOptionsMixin, import("vue").ComponentOptionsMixin, {
    click: (evt: MouseEvent) => boolean;
}, string, import("vue").VNodeProps & import("vue").AllowedComponentProps & import("vue").ComponentCustomProps, Readonly<import("vue").ExtractPropTypes<{
    readonly type: import("element-plus/es/utils").BuildPropReturn<StringConstructor, "default", unknown, "default" | "primary" | "success" | "warning" | "info" | "danger", unknown>;
    readonly underline: import("element-plus/es/utils").BuildPropReturn<BooleanConstructor, true, unknown, unknown, unknown>;
    readonly disabled: import("element-plus/es/utils").BuildPropReturn<BooleanConstructor, false, unknown, unknown, unknown>;
    readonly href: import("element-plus/es/utils").BuildPropReturn<StringConstructor, "", unknown, unknown, unknown>;
    readonly icon: import("element-plus/es/utils").BuildPropReturn<import("element-plus/es/utils").PropWrapper<string | import("vue").Component<any, any, any, import("vue").ComputedOptions, import("vue").MethodOptions>>, "", unknown, unknown, unknown>;
}>> & {
    onClick?: ((evt: MouseEvent) => any) | undefined;
}, {
    type: import("element-plus/es/utils").BuildPropType<StringConstructor, "default" | "primary" | "success" | "warning" | "info" | "danger", unknown>;
    icon: import("element-plus/es/utils").BuildPropType<import("element-plus/es/utils").PropWrapper<string | import("vue").Component<any, any, any, import("vue").ComputedOptions, import("vue").MethodOptions>>, unknown, unknown>;
    disabled: import("element-plus/es/utils").BuildPropType<BooleanConstructor, unknown, unknown>;
    underline: import("element-plus/es/utils").BuildPropType<BooleanConstructor, unknown, unknown>;
    href: string;
}>> & Record<string, any>;
export default ElLink;
export * from './src/link';
