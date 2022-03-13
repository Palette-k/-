'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var vue = require('vue');
var index$1 = require('../../scrollbar/index.js');
require('../../../hooks/index.js');
require('../../../utils/index.js');
var iconsVue = require('@element-plus/icons-vue');
var index = require('../../icon/index.js');
var node = require('./node2.js');
var types = require('./types.js');
var pluginVue_exportHelper = require('../../../_virtual/plugin-vue_export-helper.js');
var index$2 = require('../../../hooks/use-locale/index.js');
var rand = require('../../../utils/rand.js');

const _sfc_main = vue.defineComponent({
  name: "ElCascaderMenu",
  components: {
    Loading: iconsVue.Loading,
    ElIcon: index.ElIcon,
    ElScrollbar: index$1.ElScrollbar,
    ElCascaderNode: node["default"]
  },
  props: {
    nodes: {
      type: Array,
      required: true
    },
    index: {
      type: Number,
      required: true
    }
  },
  setup(props) {
    const instance = vue.getCurrentInstance();
    const { t } = index$2.useLocale();
    const id = rand.generateId();
    let activeNode = null;
    let hoverTimer = null;
    const panel = vue.inject(types.CASCADER_PANEL_INJECTION_KEY);
    const hoverZone = vue.ref(null);
    const isEmpty = vue.computed(() => !props.nodes.length);
    const isLoading = vue.computed(() => !panel.initialLoaded);
    const menuId = vue.computed(() => `cascader-menu-${id}-${props.index}`);
    const handleExpand = (e) => {
      activeNode = e.target;
    };
    const handleMouseMove = (e) => {
      if (!panel.isHoverMenu || !activeNode || !hoverZone.value)
        return;
      if (activeNode.contains(e.target)) {
        clearHoverTimer();
        const el = instance.vnode.el;
        const { left } = el.getBoundingClientRect();
        const { offsetWidth, offsetHeight } = el;
        const startX = e.clientX - left;
        const top = activeNode.offsetTop;
        const bottom = top + activeNode.offsetHeight;
        hoverZone.value.innerHTML = `
          <path style="pointer-events: auto;" fill="transparent" d="M${startX} ${top} L${offsetWidth} 0 V${top} Z" />
          <path style="pointer-events: auto;" fill="transparent" d="M${startX} ${bottom} L${offsetWidth} ${offsetHeight} V${bottom} Z" />
        `;
      } else if (!hoverTimer) {
        hoverTimer = window.setTimeout(clearHoverZone, panel.config.hoverThreshold);
      }
    };
    const clearHoverTimer = () => {
      if (!hoverTimer)
        return;
      clearTimeout(hoverTimer);
      hoverTimer = null;
    };
    const clearHoverZone = () => {
      if (!hoverZone.value)
        return;
      hoverZone.value.innerHTML = "";
      clearHoverTimer();
    };
    return {
      panel,
      hoverZone,
      isEmpty,
      isLoading,
      menuId,
      t,
      handleExpand,
      handleMouseMove,
      clearHoverZone
    };
  }
});
const _hoisted_1 = {
  key: 0,
  class: "el-cascader-menu__empty-text"
};
const _hoisted_2 = {
  key: 1,
  class: "el-cascader-menu__empty-text"
};
const _hoisted_3 = {
  key: 2,
  ref: "hoverZone",
  class: "el-cascader-menu__hover-zone"
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  const _component_el_cascader_node = vue.resolveComponent("el-cascader-node");
  const _component_loading = vue.resolveComponent("loading");
  const _component_el_icon = vue.resolveComponent("el-icon");
  const _component_el_scrollbar = vue.resolveComponent("el-scrollbar");
  return vue.openBlock(), vue.createBlock(_component_el_scrollbar, {
    key: _ctx.menuId,
    tag: "ul",
    role: "menu",
    class: "el-cascader-menu",
    "wrap-class": "el-cascader-menu__wrap",
    "view-class": ["el-cascader-menu__list", _ctx.isEmpty && "is-empty"],
    onMousemove: _ctx.handleMouseMove,
    onMouseleave: _ctx.clearHoverZone
  }, {
    default: vue.withCtx(() => {
      var _a;
      return [
        (vue.openBlock(true), vue.createElementBlock(vue.Fragment, null, vue.renderList(_ctx.nodes, (node) => {
          return vue.openBlock(), vue.createBlock(_component_el_cascader_node, {
            key: node.uid,
            node,
            "menu-id": _ctx.menuId,
            onExpand: _ctx.handleExpand
          }, null, 8, ["node", "menu-id", "onExpand"]);
        }), 128)),
        _ctx.isLoading ? (vue.openBlock(), vue.createElementBlock("div", _hoisted_1, [
          vue.createVNode(_component_el_icon, {
            size: "14",
            class: "is-loading"
          }, {
            default: vue.withCtx(() => [
              vue.createVNode(_component_loading)
            ]),
            _: 1
          }),
          vue.createTextVNode(" " + vue.toDisplayString(_ctx.t("el.cascader.loading")), 1)
        ])) : _ctx.isEmpty ? (vue.openBlock(), vue.createElementBlock("div", _hoisted_2, vue.toDisplayString(_ctx.t("el.cascader.noData")), 1)) : ((_a = _ctx.panel) == null ? void 0 : _a.isHoverMenu) ? (vue.openBlock(), vue.createElementBlock("svg", _hoisted_3, null, 512)) : vue.createCommentVNode("v-if", true)
      ];
    }),
    _: 1
  }, 8, ["view-class", "onMousemove", "onMouseleave"]);
}
var ElCascaderMenu = /* @__PURE__ */ pluginVue_exportHelper["default"](_sfc_main, [["render", _sfc_render]]);

exports["default"] = ElCascaderMenu;
//# sourceMappingURL=menu.js.map
