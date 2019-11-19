/**
 * 字典 util
 * author: scott
 * date: 20190109
 */

import {ajaxGetDictItems} from '@/api/apimy'
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
