import { getAction,deleteAction,putAction,postAction} from '@/api/manage'

////根路径
// const doMian = "/jeecg-boot/";
////图片预览请求地址
// const imgView = "http://localhost:8080/jeecg-boot/sys/common/view/";


//字典标签专用（通过code获取字典数组）
export const ajaxGetDictItems = (code, params)=>getAction(`/system/csmSysdict/getDictItems/${code}`,params);

export const ajaxGetTextById = (code, params)=>getAction(`${code}`,params);
const queryScrewTreeList = (params)=>getAction("/screwframe/csmScrewframe/queryTreeList",params);
const ajaxGetForeignKeys = (code, params)=>getAction(`/system/csmSysdict/getForeignKeys/${code}`,params);

export {
  // imgView,
  // doMian,
  queryScrewTreeList,
  ajaxGetForeignKeys
}


