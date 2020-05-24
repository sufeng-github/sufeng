<template>
  <a-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="手术类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <m-dict-select-tag type="list" v-decorator="['opeId']" :trigger-change="true" dictCode="csm_operations_info" placeholder="请选择手术类型"/>
        </a-form-item>
          
        <a-form-item label="日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择日期" v-decorator="[ 'date', validatorRules.date]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
          
        <a-form-item label="工具箱" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <m-dict-select-tag type="list" v-decorator="['toolId']" :trigger-change="true" dictCode="csm_toolbox_info" placeholder="请选择工具箱"/>
        </a-form-item>
          
        <a-form-item label="螺钉架" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <m-dict-select-tag type="list" v-decorator="['scrId']" :trigger-change="true" dictCode="csm_screw_info" placeholder="请选择螺钉架"/>
        </a-form-item>
          
        <a-form-item label="医院" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <m-dict-select-tag type="list" v-decorator="['hospitalId']" :trigger-change="true" dictCode="csm_hospital" placeholder="请选择医院"/>
        </a-form-item>
          
        <a-form-item label="患者" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'patient', validatorRules.patient]" placeholder="请输入患者"></a-input>
        </a-form-item>
          
        <a-form-item label="编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'code', validatorRules.code]" placeholder="请输入编号"></a-input>
        </a-form-item>
          
        <a-form-item label="病床号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'bednum', validatorRules.bednum]" placeholder="请输入病床号"></a-input>
        </a-form-item>
          
        <a-form-item label="销售公司" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <m-dict-select-tag type="list" v-decorator="['companyId']" :trigger-change="true" dictCode="csm_company" placeholder="请选择销售公司"/>
        </a-form-item>
          
        <a-form-item label="客户" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'customer', validatorRules.customer]" placeholder="请选择客户"></a-input>
         <!-- <m-dict-select-tag type="list" v-decorator="['customerId']" :trigger-change="true" dictCode="csm_company" placeholder="请选择客户"/>-->
        </a-form-item>
          
        <a-form-item label="发票号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'invoices', validatorRules.invoices]" placeholder="请输入发票号"></a-input>
        </a-form-item>
          
        <a-form-item label="状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['status']" :trigger-change="true" dictCode="orderStatus" placeholder="请选择状态"/>
        </a-form-item>
          
        
      </a-form>
    </a-spin>
   <!-- <a-collapse v-model="activeKey_collapse" class="modal_myCollapse" :bordered="false">
      <a-collapse-panel header="工作单元" key="1" :forceRender="true">
        <div v-if="!ModalDisabled" class="btnRow table-operator marginB10">
          <a-button @click="addOrEditDetail(1)" type="primary" icon="plus" size="small">新增</a-button>
          <a-button type="primary" icon="download" @click="handleExportXls('供应商资料')" size="small">导出</a-button>
          <a-upload name="file" :showUploadList="false" :multiple="false" >
            <a-button type="primary" icon="import" size="small">导入</a-button>
          </a-upload>
        </div>

        <a-table
          ref="table"
          size="middle"
          bordered
          rowKey="id"
          :columns="columns"
          :dataSource="dataSource"
          :loading="loading"
        >
              <span slot="action" slot-scope="text, record" class="tableActionNoBorder">
                <a-tooltip>
                  <template slot="title">编辑工作单元名称</template>
                  <a
                    @click="editUnitName(record.id,record.name);"
                    v-if="permitCtrl.isCanEdit && !ModalDisabled "
                  >
                    <a-icon type="edit" />
                  </a>
                </a-tooltip>

                <a-tooltip>
                  <template slot="title">编辑设备</template>
                  <a
                    @click="editDevice(record.id,record.name);"
                    v-if="permitCtrl.isCanEdit && !ModalDisabled "
                  >
                    <a-icon type="ordered-list" />
                  </a>
                </a-tooltip>

                <a-popconfirm title="确定删除吗?" @confirm="() => dateleDetail(1,record.id)">
                  <a v-if="permitCtrl.isCanEdit && !ModalDisabled ">
                    <a-icon type="delete" />
                  </a>
                </a-popconfirm>
              </span>
        </a-table>
      </a-collapse-panel>
    </a-collapse>-->
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import JDate from '@/components/jeecg/JDate'  
  import JDictSelectTag from "@/components/dict/JDictSelectTag"
  import MDictSelectTag from '@/componentsmy/combo/JDictSelectTag.vue'
  export default {
    name: "CsmOrderInfoModal",
    components: { 
      JDate,
      JDictSelectTag,
      MDictSelectTag,
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
        visible: false,
        ModalDisabled: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        columns: [
          {
            title: '#',
            dataIndex: '',
            key: 'rowIndex',
            width: 60,
            align: 'center',
            customRender: function(t, r, index) {
              return parseInt(index) + 1
            }
          },
          {
            title: '供应商编码',
            align: 'center',
            dataIndex: 'code'

          },
          {
            title: '供应商名称',
            align: 'center',
            dataIndex: 'name'

          },
          {
            title: '负责人',
            align: 'center',
            dataIndex: 'leader'

          },

          {
            title: '负责人电话',
            align: 'center',
            dataIndex: 'telephone'

          },
          {
            title: '操作',
            dataIndex: 'action',
            align: 'center',
            scopedSlots: { customRender: 'action' }
          }
        ],
        dataSource: [],
        loading: false,
        confirmLoading: false,
        validatorRules:{
        opeId:{},
        date:{},
        toolId:{},
        scrId:{},
        hospitalId:{},
        patient:{},
        code:{},
        bednum:{},
        companyId:{},
        customer:{},
        invoices:{},
        status:{},
        },
        url: {
          add: "/order/csmOrderInfo/add",
          edit: "/order/csmOrderInfo/edit",
        }
     
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'opeId','date','toolId','scrId','hospitalId','patient','code','bednum','companyId','customer','invoices','status'))
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }
         
        })
      },
      handleCancel () {
        this.close()
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'opeId','date','toolId','scrId','hospitalId','patient','code','bednum','companyId','customer','invoices','status'))
      }
      
    }
  }
</script>