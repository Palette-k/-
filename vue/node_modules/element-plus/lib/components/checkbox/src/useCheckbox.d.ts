import type { ExtractPropTypes } from 'vue';
import type { ElFormContext, ElFormItemContext } from 'element-plus/es/tokens';
import type { ICheckboxGroupInstance } from './checkbox.type';
export declare const useCheckboxProps: {
    modelValue: {
        type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
        default: () => undefined;
    };
    label: {
        type: (BooleanConstructor | ObjectConstructor | StringConstructor | NumberConstructor)[];
    };
    indeterminate: BooleanConstructor;
    disabled: BooleanConstructor;
    checked: BooleanConstructor;
    name: {
        type: StringConstructor;
        default: undefined;
    };
    trueLabel: {
        type: (StringConstructor | NumberConstructor)[];
        default: undefined;
    };
    falseLabel: {
        type: (StringConstructor | NumberConstructor)[];
        default: undefined;
    };
    tabindex: (StringConstructor | NumberConstructor)[];
    size: StringConstructor;
};
export declare type IUseCheckboxProps = ExtractPropTypes<typeof useCheckboxProps>;
export declare const useCheckboxGroup: () => {
    isGroup: import("vue").ComputedRef<boolean>;
    checkboxGroup: ICheckboxGroupInstance;
    elForm: ElFormContext;
    elFormItemSize: import("vue").ComputedRef<"default" | "small" | "large" | undefined>;
    elFormItem: ElFormItemContext;
};
export declare const useCheckbox: (props: IUseCheckboxProps) => {
    isChecked: import("vue").ComputedRef<boolean>;
    isDisabled: import("vue").ComputedRef<boolean>;
    checkboxSize: import("vue").ComputedRef<"default" | "small" | "large">;
    model: import("vue").WritableComputedRef<any>;
    handleChange: (e: InputEvent) => void;
    focus: import("vue").Ref<boolean>;
    size: import("vue").ComputedRef<"default" | "small" | "large">;
};
