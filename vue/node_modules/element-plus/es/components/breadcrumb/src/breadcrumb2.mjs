import { defineComponent, ref, provide, onMounted, openBlock, createElementBlock, normalizeClass, renderSlot } from 'vue';
import '../../../tokens/index.mjs';
import '../../../hooks/index.mjs';
import { breadcrumbProps } from './breadcrumb.mjs';
import _export_sfc from '../../../_virtual/plugin-vue_export-helper.mjs';
import { useNamespace } from '../../../hooks/use-namespace/index.mjs';
import { elBreadcrumbKey } from '../../../tokens/breadcrumb.mjs';

const _sfc_main = defineComponent({
  name: "ElBreadcrumb",
  props: breadcrumbProps,
  setup(props) {
    const ns = useNamespace("breadcrumb");
    const breadcrumb = ref();
    provide(elBreadcrumbKey, props);
    onMounted(() => {
      const items = breadcrumb.value.querySelectorAll(`.${ns.e("item")}`);
      if (items.length) {
        items[items.length - 1].setAttribute("aria-current", "page");
      }
    });
    return {
      ns,
      breadcrumb
    };
  }
});
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return openBlock(), createElementBlock("div", {
    ref: "breadcrumb",
    class: normalizeClass(_ctx.ns.b()),
    "aria-label": "Breadcrumb",
    role: "navigation"
  }, [
    renderSlot(_ctx.$slots, "default")
  ], 2);
}
var Breadcrumb = /* @__PURE__ */ _export_sfc(_sfc_main, [["render", _sfc_render]]);

export { Breadcrumb as default };
//# sourceMappingURL=breadcrumb2.mjs.map
