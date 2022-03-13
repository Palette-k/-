'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var vue = require('vue');
var index = require('../../icon/index.js');
require('../../../hooks/index.js');
var link = require('./link.js');
var pluginVue_exportHelper = require('../../../_virtual/plugin-vue_export-helper.js');
var index$1 = require('../../../hooks/use-namespace/index.js');

const _sfc_main = vue.defineComponent({
  name: "ElLink",
  components: { ElIcon: index.ElIcon },
  props: link.linkProps,
  emits: link.linkEmits,
  setup(props, { emit }) {
    const ns = index$1.useNamespace("link");
    function handleClick(event) {
      if (!props.disabled)
        emit("click", event);
    }
    return {
      ns,
      handleClick
    };
  }
});
const _hoisted_1 = ["href"];
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  const _component_el_icon = vue.resolveComponent("el-icon");
  return vue.openBlock(), vue.createElementBlock("a", {
    class: vue.normalizeClass([
      _ctx.ns.b(),
      _ctx.type ? _ctx.ns.m(_ctx.type) : "",
      _ctx.ns.is("disabled", _ctx.disabled),
      _ctx.ns.is("underline", _ctx.underline && !_ctx.disabled)
    ]),
    href: _ctx.disabled || !_ctx.href ? void 0 : _ctx.href,
    onClick: _cache[0] || (_cache[0] = (...args) => _ctx.handleClick && _ctx.handleClick(...args))
  }, [
    _ctx.icon ? (vue.openBlock(), vue.createBlock(_component_el_icon, { key: 0 }, {
      default: vue.withCtx(() => [
        (vue.openBlock(), vue.createBlock(vue.resolveDynamicComponent(_ctx.icon)))
      ]),
      _: 1
    })) : vue.createCommentVNode("v-if", true),
    _ctx.$slots.default ? (vue.openBlock(), vue.createElementBlock("span", {
      key: 1,
      class: vue.normalizeClass(_ctx.ns.m("inner"))
    }, [
      vue.renderSlot(_ctx.$slots, "default")
    ], 2)) : vue.createCommentVNode("v-if", true),
    _ctx.$slots.icon ? vue.renderSlot(_ctx.$slots, "icon", { key: 2 }) : vue.createCommentVNode("v-if", true)
  ], 10, _hoisted_1);
}
var Link = /* @__PURE__ */ pluginVue_exportHelper["default"](_sfc_main, [["render", _sfc_render]]);

exports["default"] = Link;
//# sourceMappingURL=link2.js.map
