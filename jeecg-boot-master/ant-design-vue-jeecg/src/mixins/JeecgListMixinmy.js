/**
 * 新增修改完成调用 modalFormOk方法 编辑弹框组件ref定义为modalForm
 * 高级查询按钮调用 superQuery方法  高级查询组件ref定义为superQueryModal
 * data中url定义 list为查询列表  delete为删除单条记录  deleteBatch为批量删除
 */


export const JeecgListMixinmy = {
  data(){
    return {

      /* 查询条件-请不要在queryParam中声明非字符串值的属性 */
      assistParam: {}

      }

  },

  methods:{

    getAssistParams() {
      //获取查询条件

    }

  }

}