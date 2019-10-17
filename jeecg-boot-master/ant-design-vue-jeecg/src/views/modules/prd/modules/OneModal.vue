<template>
  <a-modal
    :title="title"
    :width="1200"
    :visible="visible"
    :maskClosable="false"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel">
    <a-spin :spinning="confirmLoading">
      <!-- 主表单区域 -->
      <a-form :form="form">
        <a-row>

          <a-col :span="12">
            <a-form-item label="所属部门" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="[ 'sysOrgCode', validatorRules.sysOrgCode]" placeholder="请输入所属部门"></a-input>
            </a-form-item>
          </a-col>
        
          <a-col :span="12">
            <a-form-item label="主表年龄" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="[ 'mainage', validatorRules.mainage]" placeholder="请输入主表年龄"></a-input>
            </a-form-item>
          </a-col>
        
          <a-col :span="12">
            <a-form-item label="姓别" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="[ 'sex', validatorRules.sex]" placeholder="请输入姓别"></a-input>
            </a-form-item>
          </a-col>
        
        </a-row>
      </a-form>

      <!-- 子表单区域 -->
      <a-tabs v-model="activeKey" @change="handleChangeTabs">
        <a-tab-pane tab="多" :key="refKeys[0]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[0]"
            :loading="mulTable.loading"
            :columns="mulTable.columns"
            :dataSource="mulTable.dataSource"
            :maxHeight="300"
            :rowNumber="true"
            :rowSelection="true"
            :actionButton="true"/>
        </a-tab-pane>
        
      </a-tabs>

    </a-spin>
  </a-modal>
</template>

<script>

  import pick from 'lodash.pick'
  import { FormTypes,getRefPromise } from '@/utils/JEditableTableUtil'
  import { JEditableTableMixin } from '@/mixins/JEditableTableMixin'

  export default {
    name: 'OneModal',
    mixins: [JEditableTableMixin],
    components: {
    },
    data() {
      return {
        labelCol: {
          span: 6
        },
        wrapperCol: {
          span: 16
        },
        labelCol2: {
          span: 3
        },
        wrapperCol2: {
          span: 20
        },
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
          sysOrgCode:{},
          mainage:{},
          sex:{},
        },
        refKeys: ['mul', ],
        tableKeys:['mul', ],
        activeKey: 'mul',
        // 多
        mulTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '名字',
              key: 'oneName',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '年龄',
              key: 'oneAge',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
          ]
        },
        url: {
          add: "/prd/one/add",
          edit: "/prd/one/edit",
          mul: {
            list: '/prd/one/queryMulByMainId'
          },
        }
      }
    },
    methods: {
      getAllTable() {
        let values = this.tableKeys.map(key => getRefPromise(this, key))
        return Promise.all(values)
      },
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        let fieldval = pick(this.model,'sysOrgCode','mainage','sex')
        this.$nextTick(() => {
          this.form.setFieldsValue(fieldval)
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.mul.list, params, this.mulTable)
        }
      },
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)

        return {
          ...main, // 展开
          mulList: allValues.tablesValue[0].values,
        }
      },
      validateError(msg){
        this.$message.error(msg)
      }
      
      
    }
  }
</script>

<style scoped>
</style>