'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var vue = require('vue');
require('../../../tokens/index.js');
require('../../../hooks/index.js');
var breadcrumb = require('./breadcrumb.js');
var pluginVue_exportHelper = require('../../../_virtual/plugin-vue_export-helper.js');
var index = require('../../../hooks/use-namespace/index.js');
var breadcrumb$1 = require('../../../tokens/breadcrumb.js');

const _sfc_main = vue.defineComponent({
  name: "ElBreadcrumb",
  props: breadcrumb.breadcrumbProps,
  setup(props) {
    const ns = index.useNamespace("breadcrumb");
    const breadcrumb = vue.ref();
    vue.provide(breadcrumb$1.elBreadcrumbKey, props);
    vue.onMounted(() => {
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
  return vue.openBlock(), vue.createElementBlock("div", {
    ref: "breadcrumb",
    class: vue.normalizeClass(_ctx.ns.b()),
    "aria-label": "Breadcrumb",
    role: "navigation"
  }, [
    vue.renderSlot(_ctx.$slots, "default")
  ], 2);
}
var Breadcrumb = /* @__PURE__ */ pluginVue_exportHelper["default"](_sfc_main, [["render", _sfc_render]]);

exports["default"] = Breadcrumb;
//# sourceMappingURL=breadcrumb2.js.map
