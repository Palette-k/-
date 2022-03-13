export declare const ElForm: import("element-plus/es/utils").SFCWithInstall<import("vue").DefineComponent<{
    model: ObjectConstructor;
    rules: import("vue").PropType<Partial<Record<string, import("./src/form.type").FormItemRule | import("./src/form.type").FormItemRule[]>>>;
    labelPosition: StringConstructor;
    labelWidth: {
        type: (StringConstructor | NumberConstructor)[];
        default: string;
    };
    labelSuffix: {
        type: StringConstructor;
        default: string;
    };
    inline: BooleanConstructor;
    inlineMessage: BooleanConstructor;
    statusIcon: BooleanConstructor;
    showMessage: {
        type: BooleanConstructor;
        default: boolean;
    };
    size: import("vue").PropType<"default" | "small" | "large">;
    disabled: BooleanConstructor;
    validateOnRuleChange: {
        type: BooleanConstructor;
        default: boolean;
    };
    hideRequiredAsterisk: {
        type: BooleanConstructor;
        default: boolean;
    };
    scrollToError: BooleanConstructor;
}, {
    formKls: import("vue").ComputedRef<string[]>;
    validate: (callback?: import("./src/form.vue").Callback | undefined) => Promise<boolean> | undefined;
    resetFields: () => void;
    clearValidate: (props?: string | string[]) => void;
    validateField: (props: string | string[], cb: import("../..").ValidateFieldCallback) => void;
    scrollToField: (prop: string) => void;
}, unknown, {}, {}, import("vue").ComponentOptionsMixin, import("vue").ComponentOptionsMixin, "validate"[], "validate", import("vue").VNodeProps & import("vue").AllowedComponentProps & import("vue").ComponentCustomProps, Readonly<import("vue").ExtractPropTypes<{
    model: ObjectConstructor;
    rules: import("vue").PropType<Partial<Record<string, import("./src/form.type").FormItemRule | import("./src/form.type").FormItemRule[]>>>;
    labelPosition: StringConstructor;
    labelWidth: {
        type: (StringConstructor | NumberConstructor)[];
        default: string;
    };
    labelSuffix: {
        type: StringConstructor;
        default: string;
    };
    inline: BooleanConstructor;
    inlineMessage: BooleanConstructor;
    statusIcon: BooleanConstructor;
    showMessage: {
        type: BooleanConstructor;
        default: boolean;
    };
    size: import("vue").PropType<"default" | "small" | "large">;
    disabled: BooleanConstructor;
    validateOnRuleChange: {
        type: BooleanConstructor;
        default: boolean;
    };
    hideRequiredAsterisk: {
        type: BooleanConstructor;
        default: boolean;
    };
    scrollToError: BooleanConstructor;
}>> & {
    onValidate?: ((...args: any[]) => any) | undefined;
}, {
    disabled: boolean;
    inline: boolean;
    labelWidth: string | number;
    labelSuffix: string;
    inlineMessage: boolean;
    statusIcon: boolean;
    showMessage: boolean;
    validateOnRuleChange: boolean;
    hideRequiredAsterisk: boolean;
    scrollToError: boolean;
}>> & {
    FormItem: import("vue").DefineComponent<{
        label: StringConstructor;
        labelWidth: {
            type: (StringConstructor | NumberConstructor)[];
            default: string;
        };
        prop: StringConstructor;
        required: {
            type: BooleanConstructor;
            default: undefined;
        };
        rules: import("vue").PropType<import("./src/form.type").FormItemRule | import("./src/form.type").FormItemRule[]>;
        error: StringConstructor;
        validateStatus: StringConstructor;
        for: StringConstructor;
        inlineMessage: {
            type: (BooleanConstructor | StringConstructor)[];
            default: string;
        };
        showMessage: {
            type: BooleanConstructor;
            default: boolean;
        };
        size: {
            type: import("vue").PropType<"default" | "small" | "large">;
            validator: (val: string) => val is "" | "default" | "small" | "large";
        };
    }, {
        formItemRef: import("vue").Ref<HTMLDivElement | undefined>;
        formItemClass: import("vue").ComputedRef<(string | {
            'el-form-item--feedback': boolean | undefined;
            'is-error': boolean;
            'is-validating': boolean;
            'is-success': boolean;
            'is-required': boolean | undefined;
            'is-no-asterisk': boolean | undefined;
        })[]>;
        shouldShowError: import("vue").ComputedRef<boolean | undefined>;
        elForm: import("../..").ElFormContext;
        labelStyle: import("vue").ComputedRef<import("vue").CSSProperties>;
        contentStyle: import("vue").ComputedRef<import("vue").CSSProperties>;
        validateMessage: import("vue").Ref<string>;
        labelFor: import("vue").ComputedRef<string | undefined>;
        resetField: () => void;
        clearValidate: () => void;
        currentLabel: import("vue").ComputedRef<string>;
    }, unknown, {}, {}, import("vue").ComponentOptionsMixin, import("vue").ComponentOptionsMixin, Record<string, any>, string, import("vue").VNodeProps & import("vue").AllowedComponentProps & import("vue").ComponentCustomProps, Readonly<import("vue").ExtractPropTypes<{
        label: StringConstructor;
        labelWidth: {
            type: (StringConstructor | NumberConstructor)[];
            default: string;
        };
        prop: StringConstructor;
        required: {
            type: BooleanConstructor;
            default: undefined;
        };
        rules: import("vue").PropType<import("./src/form.type").FormItemRule | import("./src/form.type").FormItemRule[]>;
        error: StringConstructor;
        validateStatus: StringConstructor;
        for: StringConstructor;
        inlineMessage: {
            type: (BooleanConstructor | StringConstructor)[];
            default: string;
        };
        showMessage: {
            type: BooleanConstructor;
            default: boolean;
        };
        size: {
            type: import("vue").PropType<"default" | "small" | "large">;
            validator: (val: string) => val is "" | "default" | "small" | "large";
        };
    }>>, {
        required: boolean;
        labelWidth: string | number;
        inlineMessage: string | boolean;
        showMessage: boolean;
    }>;
};
export default ElForm;
export declare const ElFormItem: import("element-plus/es/utils").SFCWithInstall<import("vue").DefineComponent<{
    label: StringConstructor;
    labelWidth: {
        type: (StringConstructor | NumberConstructor)[];
        default: string;
    };
    prop: StringConstructor;
    required: {
        type: BooleanConstructor;
        default: undefined;
    };
    rules: import("vue").PropType<import("./src/form.type").FormItemRule | import("./src/form.type").FormItemRule[]>;
    error: StringConstructor;
    validateStatus: StringConstructor;
    for: StringConstructor;
    inlineMessage: {
        type: (BooleanConstructor | StringConstructor)[];
        default: string;
    };
    showMessage: {
        type: BooleanConstructor;
        default: boolean;
    };
    size: {
        type: import("vue").PropType<"default" | "small" | "large">;
        validator: (val: string) => val is "" | "default" | "small" | "large";
    };
}, {
    formItemRef: import("vue").Ref<HTMLDivElement | undefined>;
    formItemClass: import("vue").ComputedRef<(string | {
        'el-form-item--feedback': boolean | undefined;
        'is-error': boolean;
        'is-validating': boolean;
        'is-success': boolean;
        'is-required': boolean | undefined;
        'is-no-asterisk': boolean | undefined;
    })[]>;
    shouldShowError: import("vue").ComputedRef<boolean | undefined>;
    elForm: import("../..").ElFormContext;
    labelStyle: import("vue").ComputedRef<import("vue").CSSProperties>;
    contentStyle: import("vue").ComputedRef<import("vue").CSSProperties>;
    validateMessage: import("vue").Ref<string>;
    labelFor: import("vue").ComputedRef<string | undefined>;
    resetField: () => void;
    clearValidate: () => void;
    currentLabel: import("vue").ComputedRef<string>;
}, unknown, {}, {}, import("vue").ComponentOptionsMixin, import("vue").ComponentOptionsMixin, Record<string, any>, string, import("vue").VNodeProps & import("vue").AllowedComponentProps & import("vue").ComponentCustomProps, Readonly<import("vue").ExtractPropTypes<{
    label: StringConstructor;
    labelWidth: {
        type: (StringConstructor | NumberConstructor)[];
        default: string;
    };
    prop: StringConstructor;
    required: {
        type: BooleanConstructor;
        default: undefined;
    };
    rules: import("vue").PropType<import("./src/form.type").FormItemRule | import("./src/form.type").FormItemRule[]>;
    error: StringConstructor;
    validateStatus: StringConstructor;
    for: StringConstructor;
    inlineMessage: {
        type: (BooleanConstructor | StringConstructor)[];
        default: string;
    };
    showMessage: {
        type: BooleanConstructor;
        default: boolean;
    };
    size: {
        type: import("vue").PropType<"default" | "small" | "large">;
        validator: (val: string) => val is "" | "default" | "small" | "large";
    };
}>>, {
    required: boolean;
    labelWidth: string | number;
    inlineMessage: string | boolean;
    showMessage: boolean;
}>>;
