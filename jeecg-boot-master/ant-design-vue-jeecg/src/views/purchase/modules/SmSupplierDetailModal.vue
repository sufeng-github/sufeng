<template>
  <a-modal
    :title="title"
    :width="600"
    :visible="visible"
    :maskClosable="false"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    :okButtonProps="{ props: {disabled : ModalDisabled} }"
  >
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
        <a-row>

          <a-col :span="12" :gutter="8" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="所属供应商">
              <a-input :disabled="true" placeholder="" v-model="supplierName" />
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8" :xs="24" :sm="24" :md="24" :lg="24" :xl="24" v-if="!ModalDisabled">
            <a-row>
              <a-col :span="12" :gutter="8" :xs="24" :sm="6" :md="6" :lg="6" :xl="6" >
                <div class="textalignR requiredDiv"><span class="redFont">*</span>物料:</div>
              </a-col>
              <a-col :span="12" :gutter="8" :xs="24" :sm="18" :md="18" :lg="18" :xl="18">
                <a-button type="dashed" icon="file-search" class="requiredBtn" @click="goAssociation()">点击选择物料</a-button>
              </a-col>
            </a-row>
          </a-col>
          <a-form-item v-show="false">
            <a-input v-decorator="['id', {}]"/>
          </a-form-item>
          <a-form-item v-show="false">
            <a-input v-decorator="['materialsId', {}]"/>
          </a-form-item>
          <a-col :span="12" :gutter="8" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="物料编码">
              <a-input disabled placeholder="请选择物料" v-decorator="['materialsCode', {rules: [{ required: true, message: '请选择物料' }]}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="物料名称">
              <a-input disabled placeholder="请选择物料" v-decorator="['materialsName', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="物料描述">
              <a-input disabled placeholder="请选择物料" v-decorator="['materialsDesc', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="单位">
              <a-input disabled placeholder="请选择物料" v-decorator="['unit', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="报价">
              <a-input-number :min="0" :step="0.10" :disabled="ModalDisabled" placeholder="请输入报价" v-decorator="['price', {rules: [{ required: true, message: '请输入报价' }]}]" style="width:100%;"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="是否首选">
              <a-radio-group :disabled="ModalDisabled" v-decorator="['isDefault', {rules: [{ required: true, message: '请选择是否首选项' }]}]">
                <a-radio value="是">是</a-radio>
                <a-radio value="否">否</a-radio>
              </a-radio-group>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>

    </a-spin>

    <!-- 物料弹窗 -->
    <association-form-modal ref="AssociationForm" @ok="AssociationFormOk" />

  </a-modal>
</template>

<script>    
  // import { FormTypes } from '@/utils/JEditableTableUtil'
  // import moment from 'moment'
  import pick from 'lodash.pick'
  import AssociationFormModal from './AssociationFormModal'
  // import { readExcelModal } from '@/mixins/readExcelModal'
  import { getAction,httpAction,postAction } from '@/api/manage'


  export default {
    name: 'SmSupplierDetilModal',
    mixins: [],
    components: {
      AssociationFormModal
    },
    data() {
      return {

        title: '操作',
        visible: false,
        form: this.$form.createForm(this),
        validatorRules:{},
        confirmLoading: false,
        model: {},
        
        labelCol: {
          xs: { span: 24 },
          sm: { span: 6 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 18 },
        },

        ModalDisabled:false,
        modalStatus:'',
        supplierName:'',
        supplierId:null,//从第二个弹窗传过来的供应商id
        // url: {
        //   add: '/sm/smSupplier/add',
        //   edit: '/sm/smSupplier/edit',
        // },
        // 表头
      }
    },
    methods: {
      add(){
        this.edit ({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'id','materialsId','materialsCode','materialsName','materialsDesc','unit','price',"isDefault"));
        });
      },

      handleOk(){
        var that=this;
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let formData = Object.assign(this.model, values);
            //时间格式化
            
            // console.log(formData)
            let info={
              "id":formData.id,
              "materialsId":formData.materialsId,
              "isDefault":formData.isDefault,
              "price":formData.price,
            }
            let supplierRdMaterialsInfoVo=info;
            let url="/sm/smSupplier/addOrEditDetail?supplierId="+this.supplierId;
            httpAction(url,supplierRdMaterialsInfoVo,'post').then((res)=>{
              that.confirmLoading = false;
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
                that.close();
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
       
            })
          }
        })
      },


      goAssociation() {
        this.$refs.AssociationForm.visible = true
      },
    //选择完物料后，
      AssociationFormOk(data) {
        //data[0]才是单选所选中的数据
        this.form.setFieldsValue({
          materialsName: data[0].name,
          materialsCode: data[0].code,
          materialsId: data[0].id,
          materialsDesc: data[0].remark,
          unit: data[0].unit
        })
      },

      close() {
        this.visible = false;
        this.$emit('close')
      },
      //第二个弹窗关闭按钮点击事件
      handleCancel() {
        this.close()
      },


    }
  }
</script>

<style scoped>
</style>