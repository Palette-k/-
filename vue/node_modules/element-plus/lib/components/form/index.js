'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

require('../../utils/index.js');
var form = require('./src/form.js');
var formItem = require('./src/form-item.js');
var install = require('../../utils/vue/install.js');

const ElForm = install.withInstall(form["default"], {
  FormItem: formItem["default"]
});
const ElFormItem = install.withNoopInstall(formItem["default"]);

exports.ElForm = ElForm;
exports.ElFormItem = ElFormItem;
exports["default"] = ElForm;
//# sourceMappingURL=index.js.map
