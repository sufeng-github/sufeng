/**
 * 字典 util
 * author: scott
 * date: 20190109
 */

import {ajaxGetDictItems,ajaxGetTextById, ajaxGetForeignKeys} from '@/api/apimy'
import {getAction} from '@/api/manage'

/**
 * 获取字典数组
 * @param dictCode 字典Code
 * @return List<Map>
 */
export async function initDictOptionsmy(dictCode) {
  if (!dictCode) {
    return '字典Code不能为空!';
  }
  //获取字典数组
  let res = await ajaxGetDictItems(dictCode);
  return res;
}

export async function initForeignKey(db,fk) {
  if (!fk) {
    return '字典Code不能为空!';
  }

  //获取字典数组
  let res = await ajaxGetForeignKeys(db,fk);
  return res;
}

export async function getTextById(dictOptions, id) {
  if(!id){
    return ""
  }
  let res =  await ajaxGetTextById(dictOptions,  {id: id});
 // console.log(res.result.name);
 // alert(res.result.name);
  return res;

}

export function filterMultiIdText(dictOptions, text) {
  if(!text){
    return ""
  }
  let re = "";
  dictOptions.forEach(function (option) {
    if(text == option.id){
      re = option
    }
  });
  if(re==""){
    return "";
  }
  return re;
}