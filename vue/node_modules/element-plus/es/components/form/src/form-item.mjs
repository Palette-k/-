import { defineComponent, inject, ref, getCurrentInstance, computed, watch, nextTick, reactive, toRefs, onMounted, onBeforeUnmount, provide, resolveComponent, openBlock, createElementBlock, normalizeClass, createVNode, withCtx, normalizeStyle, renderSlot, createTextVNode, toDisplayString, createCommentVNode, createElementVNode, Transition } from 'vue';
import { NOOP } from '@vue/shared';
import AsyncValidator from 'async-validator';
import '../../../utils/index.mjs';
import '../../../tokens/index.mjs';
import '../../../hooks/index.mjs';
import LabelWrap from './label-wrap.mjs';
import _export_sfc from '../../../_virtual/plugin-vue_export-helper.mjs';
import { isValidComponentSize } from '../../../utils/vue/validator.mjs';
import { elFormKey, elFormItemKey } from '../../../tokens/form.mjs';
import { addUnit } from '../../../utils/vue/style.mjs';
import { getPropByPath } from '../../../utils/objects.mjs';
import { useSize } from '../../../hooks/use-common-props/index.mjs';

const _sfc_main = defineComponent({
  name: "ElFormItem",
  componentName: "ElFormItem",
  components: {
    LabelWrap
  },
  props: {
    label: String,
    labelWidth: {
      type: [String, Number],
      default: ""
    },
    prop: String,
    required: {
      type: Boolean,
      default: void 0
    },
    rules: [Object, Array],
    error: String,
    validateStatus: String,
    for: String,
    inlineMessage: {
      type: [String, Boolean],
      default: ""
    },
    showMessage: {
      type: Boolean,
      default: true
    },
    size: {
      type: String,
      validator: isValidComponentSize
    }
  },
  setup(props, { slots }) {
    const elForm = inject(elFormKey, {});
    const validateState = ref("");
    const validateMessage = ref("");
    const isValidationEnabled = ref(false);
    const computedLabelWidth = ref("");
    const formItemRef = ref();
    const vm = getCurrentInstance();
    const isNested = computed(() => {
      let parent = vm.parent;
      while (parent && parent.type.name !== "ElForm") {
        if (parent.type.name === "ElFormItem") {
          return true;
        }
        parent = parent.parent;
      }
      return false;
    });
    let initialValue = void 0;
    watch(() => props.error, (val) => {
      validateMessage.value = val;
      validateState.value = val ? "error" : "";
    }, {
      immediate: true
    });
    watch(() => props.validateStatus, (val) => {
      validateState.value = val;
    });
    const labelFor = computed(() => props.for || props.prop);
    const labelStyle = computed(() => {
      const ret = {};
      if (elForm.labelPosition === "top")
        return ret;
      const labelWidth = addUnit(props.labelWidth || elForm.labelWidth);
      if (labelWidth) {
        ret.width = labelWidth;
      }
      return ret;
    });
    const contentStyle = computed(() => {
      const ret = {};
      if (elForm.labelPosition === "top" || elForm.inline) {
        return ret;
      }
      if (!props.label && !props.labelWidth && isNested.value) {
        return ret;
      }
      const labelWidth = addUnit(props.labelWidth || elForm.labelWidth);
      if (!props.label && !slots.label) {
        ret.marginLeft = labelWidth;
      }
      return ret;
    });
    const fieldValue = computed(() => {
      const model = elForm.model;
      if (!model || !props.prop) {
        return;
      }
      let path = props.prop;
      if (path.indexOf(":") !== -1) {
        path = path.replace(/:/, ".");
      }
      return getPropByPath(model, path, true).v;
    });
    const isRequired = computed(() => {
      const rules = getRules();
      let required = false;
      if (rules && rules.length) {
        rules.every((rule) => {
          if (rule.required) {
            required = true;
            return false;
          }
          return true;
        });
      }
      return required;
    });
    const sizeClass = useSize(void 0, { formItem: false });
    const validate = (trigger, callback = NOOP) => {
      if (!isValidationEnabled.value) {
        callback();
        return;
      }
      const rules = getFilteredRule(trigger);
      if ((!rules || rules.length === 0) && props.required === void 0) {
        callback();
        return;
      }
      validateState.value = "validating";
      const descriptor = {};
      if (rules && rules.length > 0) {
        rules.forEach((rule) => {
          delete rule.trigger;
        });
      }
      descriptor[props.prop] = rules;
      const validator = new AsyncValidator(descriptor);
      const model = {};
      model[props.prop] = fieldValue.value;
      validator.validate(model, { firstFields: true }, (errors, fields) => {
        var _a;
        validateState.value = !errors ? "success" : "error";
        validateMessage.value = errors ? errors[0].message || `${props.prop} is required` : "";
        callback(validateMessage.value, errors ? fields : {});
        (_a = elForm.emit) == null ? void 0 : _a.call(elForm, "validate", props.prop, !errors, validateMessage.value || null);
      });
    };
    const clearValidate = () => {
      validateState.value = "";
      validateMessage.value = "";
    };
    const resetField = () => {
      const model = elForm.model;
      const value = fieldValue.value;
      let path = props.prop;
      if (path.indexOf(":") !== -1) {
        path = path.replace(/:/, ".");
      }
      const prop = getPropByPath(model, path, true);
      if (Array.isArray(value)) {
        prop.o[prop.k] = [].concat(initialValue);
      } else {
        prop.o[prop.k] = initialValue;
      }
      nextTick(() => {
        clearValidate();
      });
    };
    const getRules = () => {
      const formRules = elForm.rules;
      const selfRules = props.rules;
      const requiredRule = props.required !== void 0 ? { required: !!props.required } : [];
      const prop = getPropByPath(formRules, props.prop || "", false);
      const normalizedRule = formRules ? prop.o[props.prop || ""] || prop.v : [];
      return [].concat(selfRules || normalizedRule || []).concat(requiredRule);
    };
    const getFilteredRule = (trigger) => {
      const rules = getRules();
      return rules.filter((rule) => {
        if (!rule.trigger || trigger === "")
          return true;
        if (Array.isArray(rule.trigger)) {
          return rule.trigger.indexOf(trigger) > -1;
        } else {
          return rule.trigger === trigger;
        }
      }).map((rule) => ({ ...rule }));
    };
    const evaluateValidationEnabled = () => {
      var _a;
      isValidationEnabled.value = !!((_a = getRules()) == null ? void 0 : _a.length);
    };
    const updateComputedLabelWidth = (width) => {
      computedLabelWidth.value = width ? `${width}px` : "";
    };
    const elFormItem = reactive({
      ...toRefs(props),
      size: sizeClass,
      validateState,
      $el: formItemRef,
      evaluateValidationEnabled,
      resetField,
      clearValidate,
      validate,
      updateComputedLabelWidth
    });
    onMounted(() => {
      if (props.prop) {
        elForm == null ? void 0 : elForm.addField(elFormItem);
        const value = fieldValue.value;
        initialValue = Array.isArray(value) ? [...value] : value;
        evaluateValidationEnabled();
      }
    });
    onBeforeUnmount(() => {
      elForm == null ? void 0 : elForm.removeField(elFormItem);
    });
    provide(elFormItemKey, elFormItem);
    const formItemClass = computed(() => [
      {
        "el-form-item--feedback": elForm.statusIcon,
        "is-error": validateState.value === "error",
        "is-validating": validateState.value === "validating",
        "is-success": validateState.value === "success",
        "is-required": isRequired.value || props.required,
        "is-no-asterisk": elForm.hideRequiredAsterisk
      },
      sizeClass.value ? `el-form-item--${sizeClass.value}` : ""
    ]);
    const shouldShowError = computed(() => {
      return validateState.value === "error" && props.showMessage && elForm.showMessage;
    });
    const currentLabel = computed(() => (props.label || "") + (elForm.labelSuffix || ""));
    return {
      formItemRef,
      formItemClass,
      shouldShowError,
      elForm,
      labelStyle,
      contentStyle,
      validateMessage,
      labelFor,
      resetField,
      clearValidate,
      currentLabel
    };
  }
});
const _hoisted_1 = ["for"];
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  const _component_LabelWrap = resolveComponent("LabelWrap");
  return openBlock(), createElementBlock("div", {
    ref: "formItemRef",
    class: normalizeClass(["el-form-item", _ctx.formItemClass])
  }, [
    createVNode(_component_LabelWrap, {
      "is-auto-width": _ctx.labelStyle.width === "auto",
      "update-all": _ctx.elForm.labelWidth === "auto"
    }, {
      default: withCtx(() => [
        _ctx.label || _ctx.$slots.label ? (openBlock(), createElementBlock("label", {
          key: 0,
          for: _ctx.labelFor,
          class: "el-form-item__label",
          style: normalizeStyle(_ctx.labelStyle)
        }, [
          renderSlot(_ctx.$slots, "label", { label: _ctx.currentLabel }, () => [
            createTextVNode(toDisplayString(_ctx.currentLabel), 1)
          ])
        ], 12, _hoisted_1)) : createCommentVNode("v-if", true)
      ]),
      _: 3
    }, 8, ["is-auto-width", "update-all"]),
    createElementVNode("div", {
      class: "el-form-item__content",
      style: normalizeStyle(_ctx.contentStyle)
    }, [
      renderSlot(_ctx.$slots, "default"),
      createVNode(Transition, { name: "el-zoom-in-top" }, {
        default: withCtx(() => [
          _ctx.shouldShowError ? renderSlot(_ctx.$slots, "error", {
            key: 0,
            error: _ctx.validateMessage
          }, () => [
            createElementVNode("div", {
              class: normalizeClass(["el-form-item__error", {
                "el-form-item__error--inline": typeof _ctx.inlineMessage === "boolean" ? _ctx.inlineMessage : _ctx.elForm.inlineMessage || false
              }])
            }, toDisplayString(_ctx.validateMessage), 3)
          ]) : createCommentVNode("v-if", true)
        ]),
        _: 3
      })
    ], 4)
  ], 2);
}
var FormItem = /* @__PURE__ */ _export_sfc(_sfc_main, [["render", _sfc_render]]);

export { FormItem as default };
//# sourceMappingURL=form-item.mjs.map
