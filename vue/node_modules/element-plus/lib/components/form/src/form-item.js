'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var vue = require('vue');
var shared = require('@vue/shared');
var AsyncValidator = require('async-validator');
require('../../../utils/index.js');
require('../../../tokens/index.js');
require('../../../hooks/index.js');
var labelWrap = require('./label-wrap.js');
var pluginVue_exportHelper = require('../../../_virtual/plugin-vue_export-helper.js');
var validator = require('../../../utils/vue/validator.js');
var form = require('../../../tokens/form.js');
var style = require('../../../utils/vue/style.js');
var objects = require('../../../utils/objects.js');
var index = require('../../../hooks/use-common-props/index.js');

function _interopDefaultLegacy (e) { return e && typeof e === 'object' && 'default' in e ? e : { 'default': e }; }

var AsyncValidator__default = /*#__PURE__*/_interopDefaultLegacy(AsyncValidator);

const _sfc_main = vue.defineComponent({
  name: "ElFormItem",
  componentName: "ElFormItem",
  components: {
    LabelWrap: labelWrap["default"]
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
      validator: validator.isValidComponentSize
    }
  },
  setup(props, { slots }) {
    const elForm = vue.inject(form.elFormKey, {});
    const validateState = vue.ref("");
    const validateMessage = vue.ref("");
    const isValidationEnabled = vue.ref(false);
    const computedLabelWidth = vue.ref("");
    const formItemRef = vue.ref();
    const vm = vue.getCurrentInstance();
    const isNested = vue.computed(() => {
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
    vue.watch(() => props.error, (val) => {
      validateMessage.value = val;
      validateState.value = val ? "error" : "";
    }, {
      immediate: true
    });
    vue.watch(() => props.validateStatus, (val) => {
      validateState.value = val;
    });
    const labelFor = vue.computed(() => props.for || props.prop);
    const labelStyle = vue.computed(() => {
      const ret = {};
      if (elForm.labelPosition === "top")
        return ret;
      const labelWidth = style.addUnit(props.labelWidth || elForm.labelWidth);
      if (labelWidth) {
        ret.width = labelWidth;
      }
      return ret;
    });
    const contentStyle = vue.computed(() => {
      const ret = {};
      if (elForm.labelPosition === "top" || elForm.inline) {
        return ret;
      }
      if (!props.label && !props.labelWidth && isNested.value) {
        return ret;
      }
      const labelWidth = style.addUnit(props.labelWidth || elForm.labelWidth);
      if (!props.label && !slots.label) {
        ret.marginLeft = labelWidth;
      }
      return ret;
    });
    const fieldValue = vue.computed(() => {
      const model = elForm.model;
      if (!model || !props.prop) {
        return;
      }
      let path = props.prop;
      if (path.indexOf(":") !== -1) {
        path = path.replace(/:/, ".");
      }
      return objects.getPropByPath(model, path, true).v;
    });
    const isRequired = vue.computed(() => {
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
    const sizeClass = index.useSize(void 0, { formItem: false });
    const validate = (trigger, callback = shared.NOOP) => {
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
      const validator = new AsyncValidator__default["default"](descriptor);
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
      const prop = objects.getPropByPath(model, path, true);
      if (Array.isArray(value)) {
        prop.o[prop.k] = [].concat(initialValue);
      } else {
        prop.o[prop.k] = initialValue;
      }
      vue.nextTick(() => {
        clearValidate();
      });
    };
    const getRules = () => {
      const formRules = elForm.rules;
      const selfRules = props.rules;
      const requiredRule = props.required !== void 0 ? { required: !!props.required } : [];
      const prop = objects.getPropByPath(formRules, props.prop || "", false);
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
    const elFormItem = vue.reactive({
      ...vue.toRefs(props),
      size: sizeClass,
      validateState,
      $el: formItemRef,
      evaluateValidationEnabled,
      resetField,
      clearValidate,
      validate,
      updateComputedLabelWidth
    });
    vue.onMounted(() => {
      if (props.prop) {
        elForm == null ? void 0 : elForm.addField(elFormItem);
        const value = fieldValue.value;
        initialValue = Array.isArray(value) ? [...value] : value;
        evaluateValidationEnabled();
      }
    });
    vue.onBeforeUnmount(() => {
      elForm == null ? void 0 : elForm.removeField(elFormItem);
    });
    vue.provide(form.elFormItemKey, elFormItem);
    const formItemClass = vue.computed(() => [
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
    const shouldShowError = vue.computed(() => {
      return validateState.value === "error" && props.showMessage && elForm.showMessage;
    });
    const currentLabel = vue.computed(() => (props.label || "") + (elForm.labelSuffix || ""));
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
  const _component_LabelWrap = vue.resolveComponent("LabelWrap");
  return vue.openBlock(), vue.createElementBlock("div", {
    ref: "formItemRef",
    class: vue.normalizeClass(["el-form-item", _ctx.formItemClass])
  }, [
    vue.createVNode(_component_LabelWrap, {
      "is-auto-width": _ctx.labelStyle.width === "auto",
      "update-all": _ctx.elForm.labelWidth === "auto"
    }, {
      default: vue.withCtx(() => [
        _ctx.label || _ctx.$slots.label ? (vue.openBlock(), vue.createElementBlock("label", {
          key: 0,
          for: _ctx.labelFor,
          class: "el-form-item__label",
          style: vue.normalizeStyle(_ctx.labelStyle)
        }, [
          vue.renderSlot(_ctx.$slots, "label", { label: _ctx.currentLabel }, () => [
            vue.createTextVNode(vue.toDisplayString(_ctx.currentLabel), 1)
          ])
        ], 12, _hoisted_1)) : vue.createCommentVNode("v-if", true)
      ]),
      _: 3
    }, 8, ["is-auto-width", "update-all"]),
    vue.createElementVNode("div", {
      class: "el-form-item__content",
      style: vue.normalizeStyle(_ctx.contentStyle)
    }, [
      vue.renderSlot(_ctx.$slots, "default"),
      vue.createVNode(vue.Transition, { name: "el-zoom-in-top" }, {
        default: vue.withCtx(() => [
          _ctx.shouldShowError ? vue.renderSlot(_ctx.$slots, "error", {
            key: 0,
            error: _ctx.validateMessage
          }, () => [
            vue.createElementVNode("div", {
              class: vue.normalizeClass(["el-form-item__error", {
                "el-form-item__error--inline": typeof _ctx.inlineMessage === "boolean" ? _ctx.inlineMessage : _ctx.elForm.inlineMessage || false
              }])
            }, vue.toDisplayString(_ctx.validateMessage), 3)
          ]) : vue.createCommentVNode("v-if", true)
        ]),
        _: 3
      })
    ], 4)
  ], 2);
}
var FormItem = /* @__PURE__ */ pluginVue_exportHelper["default"](_sfc_main, [["render", _sfc_render]]);

exports["default"] = FormItem;
//# sourceMappingURL=form-item.js.map
