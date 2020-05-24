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

        <a-form-item label="序列号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'serialnum', validatorRules.serialnum]" placeholder="请输入序列号"></a-input>
        </a-form-item>
        <a-form-item label="日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择日期" v-decorator="[ 'date', validatorRules.date]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'quantity', validatorRules.quantity]" placeholder="请输入数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="批号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'lotnum', validatorRules.lotnum]" placeholder="请输入批号"></a-input>
        </a-form-item>
        <a-form-item label="有效日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择有效日期" v-decorator="[ 'valuedate', validatorRules.valuedate]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="生产日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择生产日期" v-decorator="[ 'pd', validatorRules.pd]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="灭菌号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'mjn', validatorRules.mjn]" placeholder="请输入灭菌号"></a-input>
        </a-form-item>
        <a-form-item label="仓库名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'stockname', validatorRules.stockname]" placeholder="请输入仓库名称"></a-input>
        </a-form-item>
        <a-form-item label="库位" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'located', validatorRules.located]" placeholder="请输入库位"></a-input>
        </a-form-item>
        <a-form-item label="安全库存" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'safequantity', validatorRules.safequantity]" placeholder="请输入安全库存" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['status']" :trigger-change="true" dictCode="stockStatus" placeholder="请选择状态"/>
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
    name: "CsmStockModal",
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
        serialnum:{},
        date:{},
        quantity:{},
        lotnum:{},
        valuedate:{},
        pd:{},
        mjn:{},
        stockname:{},
        located:{},
        safequantity:{},
        status:{},
        },
        url: {
          add: "/stock/csmStock/add",
          edit: "/stock/csmStock/edit",
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
          this.form.setFieldsValue(pick(this.model,'serialnum','date','quantity','lotnum','valuedate','pd','mjn','stockname','located','safequantity','status'))
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
        this.form.setFieldsValue(pick(row,'serialnum','date','quantity','lotnum','valuedate','pd','mjn','stockname','located','safequantity','status'))
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