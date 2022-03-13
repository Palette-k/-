'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var vue = require('vue');
var index = require('../../icon/index.js');
require('../../../tokens/index.js');
require('../../../hooks/index.js');
var breadcrumbItem = require('./breadcrumb-item.js');
var pluginVue_exportHelper = require('../../../_virtual/plugin-vue_export-helper.js');
var breadcrumb = require('../../../tokens/breadcrumb.js');
var index$1 = require('../../../hooks/use-namespace/index.js');

const COMPONENT_NAME = "ElBreadcrumbItem";
const _sfc_main = vue.defineComponent({
  name: COMPONENT_NAME,
  components: {
    ElIcon: index.ElIcon
  },
  props: breadcrumbItem.breadcrumbItemProps,
  setup(props) {
    const instance = vue.getCurrentInstance();
    const router = instance.appContext.config.globalProperties.$router;
    const parent = vue.inject(breadcrumb.elBreadcrumbKey, void 0);
    const ns = index$1.useNamespace("breadcrumb");
    const link = vue.ref();
    vue.onMounted(() => {
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
  const _component_el_icon = vue.resolveComponent("el-icon");
  return vue.openBlock(), vue.createElementBlock("span", {
    class: vue.normalizeClass(_ctx.ns.e("item"))
  }, [
    vue.createElementVNode("span", {
      ref: "link",
      class: vue.normalizeClass([_ctx.ns.e("inner"), _ctx.ns.is("link", !!_ctx.to)]),
      role: "link"
    }, [
      vue.renderSlot(_ctx.$slots, "default")
    ], 2),
    _ctx.separatorIcon ? (vue.openBlock(), vue.createBlock(_component_el_icon, {
      key: 0,
      class: vue.normalizeClass(_ctx.ns.e("separator"))
    }, {
      default: vue.withCtx(() => [
        (vue.openBlock(), vue.createBlock(vue.resolveDynamicComponent(_ctx.separatorIcon)))
      ]),
      _: 1
    }, 8, ["class"])) : (vue.openBlock(), vue.createElementBlock("span", {
      key: 1,
      class: vue.normalizeClass(_ctx.ns.e("separator")),
      role: "presentation"
    }, vue.toDisplayString(_ctx.separator), 3))
  ], 2);
}
var BreadcrumbItem = /* @__PURE__ */ pluginVue_exportHelper["default"](_sfc_main, [["render", _sfc_render]]);

exports["default"] = BreadcrumbItem;
//# sourceMappingURL=breadcrumb-item2.js.map
