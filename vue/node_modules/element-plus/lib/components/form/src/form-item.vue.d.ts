import type { PropType, CSSProperties } from 'vue';
import type { ElFormContext } from 'element-plus/es/tokens';
import type { FormItemRule } from './form.type';
declare const _default: import("vue").DefineComponent<{
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
    rules: PropType<FormItemRule | FormItemRule[]>;
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
        type: PropType<"default" | "small" | "large">;
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
    elForm: ElFormContext;
    labelStyle: import("vue").ComputedRef<CSSProperties>;
    contentStyle: import("vue").ComputedRef<CSSProperties>;
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
    rules: PropType<FormItemRule | FormItemRule[]>;
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
        type: PropType<"default" | "small" | "large">;
        validator: (val: string) => val is "" | "default" | "small" | "large";
    };
}>>, {
    required: boolean;
    labelWidth: string | number;
    inlineMessage: string | boolean;
    showMessage: boolean;
}>;
export default _default;
