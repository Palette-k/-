import { defineComponent, resolveComponent, openBlock, createElementBlock, normalizeClass, createBlock, withCtx, resolveDynamicComponent, createCommentVNode, renderSlot } from 'vue';
import { ElIcon } from '../../icon/index.mjs';
import '../../../hooks/index.mjs';
import { linkProps, linkEmits } from './link.mjs';
import _export_sfc from '../../../_virtual/plugin-vue_export-helper.mjs';
import { useNamespace } from '../../../hooks/use-namespace/index.mjs';

const _sfc_main = defineComponent({
  name: "ElLink",
  components: { ElIcon },
  props: linkProps,
  emits: linkEmits,
  setup(props, { emit }) {
    const ns = useNamespace("link");
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
  const _component_el_icon = resolveComponent("el-icon");
  return openBlock(), createElementBlock("a", {
    class: normalizeClass([
      _ctx.ns.b(),
      _ctx.type ? _ctx.ns.m(_ctx.type) : "",
      _ctx.ns.is("disabled", _ctx.disabled),
      _ctx.ns.is("underline", _ctx.underline && !_ctx.disabled)
    ]),
    href: _ctx.disabled || !_ctx.href ? void 0 : _ctx.href,
    onClick: _cache[0] || (_cache[0] = (...args) => _ctx.handleClick && _ctx.handleClick(...args))
  }, [
    _ctx.icon ? (openBlock(), createBlock(_component_el_icon, { key: 0 }, {
      default: withCtx(() => [
        (openBlock(), createBlock(resolveDynamicComponent(_ctx.icon)))
      ]),
      _: 1
    })) : createCommentVNode("v-if", true),
    _ctx.$slots.default ? (openBlock(), createElementBlock("span", {
      key: 1,
      class: normalizeClass(_ctx.ns.m("inner"))
    }, [
      renderSlot(_ctx.$slots, "default")
    ], 2)) : createCommentVNode("v-if", true),
    _ctx.$slots.icon ? renderSlot(_ctx.$slots, "icon", { key: 2 }) : createCommentVNode("v-if", true)
  ], 10, _hoisted_1);
}
var Link = /* @__PURE__ */ _export_sfc(_sfc_main, [["render", _sfc_render]]);

export { Link as default };
//# sourceMappingURL=link2.mjs.map
