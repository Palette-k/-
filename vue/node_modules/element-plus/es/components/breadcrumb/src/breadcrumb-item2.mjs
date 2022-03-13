import { defineComponent, getCurrentInstance, inject, ref, onMounted, resolveComponent, openBlock, createElementBlock, normalizeClass, createElementVNode, renderSlot, createBlock, withCtx, resolveDynamicComponent, toDisplayString } from 'vue';
import { ElIcon } from '../../icon/index.mjs';
import '../../../tokens/index.mjs';
import '../../../hooks/index.mjs';
import { breadcrumbItemProps } from './breadcrumb-item.mjs';
import _export_sfc from '../../../_virtual/plugin-vue_export-helper.mjs';
import { elBreadcrumbKey } from '../../../tokens/breadcrumb.mjs';
import { useNamespace } from '../../../hooks/use-namespace/index.mjs';

const COMPONENT_NAME = "ElBreadcrumbItem";
const _sfc_main = defineComponent({
  name: COMPONENT_NAME,
  components: {
    ElIcon
  },
  props: breadcrumbItemProps,
  setup(props) {
    const instance = getCurrentInstance();
    const router = instance.appContext.config.globalProperties.$router;
    const parent = inject(elBreadcrumbKey, void 0);
    const ns = useNamespace("breadcrumb");
    const link = ref();
    onMounted(() => {
      link.value.setAttribute("role", "link");
      link.value.addEventListener("click", () => {
        if (!props.to || !router)
          return;
        props.replace ? router.replace(props.to) : router.push(props.to);
      });
    });
    return {
      ns,
      link,
      separator: parent == null ? void 0 : parent.separator,
      separatorIcon: parent == null ? void 0 : parent.separatorIcon
    };
  }
});
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  const _component_el_icon = resolveComponent("el-icon");
  return openBlock(), createElementBlock("span", {
    class: normalizeClass(_ctx.ns.e("item"))
  }, [
    createElementVNode("span", {
      ref: "link",
      class: normalizeClass([_ctx.ns.e("inner"), _ctx.ns.is("link", !!_ctx.to)]),
      role: "link"
    }, [
      renderSlot(_ctx.$slots, "default")
    ], 2),
    _ctx.separatorIcon ? (openBlock(), createBlock(_component_el_icon, {
      key: 0,
      class: normalizeClass(_ctx.ns.e("separator"))
    }, {
      default: withCtx(() => [
        (openBlock(), createBlock(resolveDynamicComponent(_ctx.separatorIcon)))
      ]),
      _: 1
    }, 8, ["class"])) : (openBlock(), createElementBlock("span", {
      key: 1,
      class: normalizeClass(_ctx.ns.e("separator")),
      role: "presentation"
    }, toDisplayString(_ctx.separator), 3))
  ], 2);
}
var BreadcrumbItem = /* @__PURE__ */ _export_sfc(_sfc_main, [["render", _sfc_render]]);

export { BreadcrumbItem as default };
//# sourceMappingURL=breadcrumb-item2.mjs.map
