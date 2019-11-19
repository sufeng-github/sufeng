<template>
  <a-drawer
    :title="title"
    :width="width"
    placement="right"
    :closable="false"
    @close="close"
    :visible="visible">
  
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="手术类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['opeId']" :trigger-change="true" dictCode="" placeholder="请选择手术类型"/>
        </a-form-item>
        <a-form-item label="日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择日期" v-decorator="[ 'date', validatorRules.date]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="工具箱" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['toolId']" :trigger-change="true" dictCode="" placeholder="请选择工具箱"/>
        </a-form-item>
        <a-form-item label="螺钉架" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['scrId']" :trigger-change="true" dictCode="" placeholder="请选择螺钉架"/>
        </a-form-item>
        <a-form-item label="医院" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['hospitalId']" :trigger-change="true" dictCode="" placeholder="请选择医院"/>
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
          <j-dict-select-tag type="list" v-decorator="['companyId']" :trigger-change="true" dictCode="" placeholder="请选择销售公司"/>
        </a-form-item>
        <a-form-item label="客户" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['customerId']" :trigger-change="true" dictCode="" placeholder="请选择客户"/>
        </a-form-item>
        <a-form-item label="发票号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'invoices', validatorRules.invoices]" placeholder="请输入发票号"></a-input>
        </a-form-item>
        <a-form-item label="状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['status']" :trigger-change="true" dictCode="orderStatus" placeholder="请选择状态"/>
        </a-form-item>
        
      </a-form>
    </a-spin>
    <a-button type="primary" @click="handleOk">确定</a-button>
    <a-button type="primary" @click="handleCancel">取消</a-button>
  </a-drawer>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import JDate from '@/components/jeecg/JDate'  
  import JDictSelectTag from "@/components/dict/JDictSelectTag"
  
  export default {
    name: "CsmOrderInfoModal",
    components: { 
      JDate,
      JDictSelectTag,
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },

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
        customerId:{},
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
          this.form.setFieldsValue(pick(this.model,'opeId','date','toolId','scrId','hospitalId','patient','code','bednum','companyId','customerId','invoices','status'))
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
        this.form.setFieldsValue(pick(row,'opeId','date','toolId','scrId','hospitalId','patient','code','bednum','companyId','customerId','invoices','status'))
      }
      
    }
  }
</script>

<style lang="less" scoped>
/** Button按钮间距 */
  .ant-btn {
    margin-left: 30px;
    margin-bottom: 30px;
    float: right;
  }
</style>