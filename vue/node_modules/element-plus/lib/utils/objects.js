'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var shared = require('@vue/shared');
var error = require('./error.js');

const SCOPE = "UtilV2/objects";
const keysOf = (arr) => Object.keys(arr);
const entriesOf = (arr) => Object.entries(arr);
function getPropByPath(obj, path, strict) {
  let tempObj = obj;
  let key, value;
  if (obj && shared.hasOwn(obj, path)) {
    key = path;
    value = tempObj == null ? void 0 : tempObj[path];
  } else {
    path = path.replace(/\[(\w+)\]/g, ".$1");
    path = path.replace(/^\./, "");
    const keyArr = path.split(".");
    let i = 0;
    for (i; i < keyArr.length - 1; i++) {
      if (!tempObj && !strict)
        break;
      const key2 = keyArr[i];
      if (key2 in tempObj) {
        tempObj = tempObj[key2];
      } else {
        if (strict) {
          error.throwError(SCOPE, "Please transfer a valid prop path to form item!");
        }
        break;
      }
    }
    key = keyArr[i];
    value = tempObj == null ? void 0 : tempObj[keyArr[i]];
  }
  return {
    o: tempObj,
    k: key,
    v: value
  };
}

Object.defineProperty(exports, 'hasOwn', {
  enumerable: true,
  get: function () { return shared.hasOwn; }
});
exports.entriesOf = entriesOf;
exports.getPropByPath = getPropByPath;
exports.keysOf = keysOf;
//# sourceMappingURL=objects.js.map
