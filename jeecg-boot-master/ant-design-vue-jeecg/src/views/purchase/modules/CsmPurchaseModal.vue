<template>
  <a-modal
    :title="title"
    :width="900"
    :visible="visible"
    :maskClosable="false"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    :footer="null"
    :okButtonProps="{ props: {disabled : ModalDisabled} }">

    <!--<a-row class="marginB20" v-show="modalStatus != 'look'">
      <div class="fromSteps">
        <a-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
          <div class="textalignM">
            <a-steps size="small" :current="addCurrent" v-show="modalStatus == 'add'">
              <a-step title="工作站信息" description />
              <a-step title="工艺明细" description class="canNotPoiter" />
            </a-steps>
            <a-steps
              size="small"
              :current="editCurrent"
              v-show="modalStatus == 'edit'"
              class="canPoiter"
            >
              <a-step title="工作站信息" description @click="goMain" />
              <a-step title="工艺明细" description @click="goDetail" />
            </a-steps>
          </div>
        </a-col>
      </div>
    </a-row>-->
    
    <a-row class="marginB20" v-show="modalStatus != 'look'">
      <div class="fromSteps">
        <a-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
          <div class="textalignM">
            <a-steps size="small" :current="addCurrent" v-show="modalStatus == 'add'">
              <a-step title="供应商信息" description />
              <a-step title="物料明细" description class="canNotPoiter"/>
            </a-steps>
            <a-steps size="small" :current="editCurrent" v-show="modalStatus == 'edit'" class="canPoiter">
              <a-step title="供应商信息" description @click="goMain" />
              <a-step title="物料明细" description @click="goDetail" />
            </a-steps>
          </div>
        </a-col>
      </div>
    </a-row>
    <div v-show="tabIndex == 0">
      <a-spin :spinning="confirmLoading">
        <a-form :form="form" style="margin-top:20px;">
          <a-col :span="12" :gutter="8" :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
            <a-form-item label="采购日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择采购日期" v-decorator="[ 'date', validatorRules.date]" :trigger-change="true" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8" :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
            <a-form-item label="采购单号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="[ 'code', validatorRules.code]" placeholder="请输入采购单号"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8" :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
            <a-form-item label="采购金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="[ 'amount', validatorRules.amount]" placeholder="请输入采购金额" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8" :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
            <a-form-item label="状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag type="list" v-decorator="['status']" :trigger-change="true" dictCode="purchaseStatus" placeholder="请选择状态"/>
            </a-form-item>
          </a-col>

        </a-form>
        <a-row>
          <a-col :span="24" class="textalignR" style="margin-top:20px;">
            <div>
              <a-button type="primary" @click="saveTable">保存</a-button>
            </div>
          </a-col>
        </a-row>
      </a-spin>
    </div>
    <div v-show="tabIndex == 1">
      <a-spin :spinning="confirmLoading">
        <a-form :form="form" class="marginB20 showDisabledInpu
        t">
          <a-row>
            <a-col :span="12" :gutter="8" :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
              <a-form-item label="采购日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
                <a-input disabled v-decorator="['date', {}]" />
              </a-form-item>
            </a-col>
            <a-col :span="12" :gutter="8" :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
              <a-form-item label="采购单号" :labelCol="labelCol" :wrapperCol="wrapperCol">
                <a-input disabled v-decorator="['code',  {}]" />
              </a-form-item>
            </a-col>
            <a-col :span="12" :gutter="8" :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
              <a-form-item label="采购金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
                <a-input disabled v-decorator="['amount',  {}]" />
              </a-form-item>
            </a-col>
            <a-col :span="12" :gutter="8" :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
              <a-form-item label="状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
                <a-input disabled v-decorator="['productivity', {}]" />
              </a-form-item>
            </a-col>
          </a-row>
        </a-form>
      </a-spin>
      <a-collapse v-model="activeKey_collapse" class="modal_myCollapse" :bordered="false">
        <a-collapse-panel header="工作单元" key="1" :forceRender="true">
          <div v-if="!ModalDisabled" class="btnRow table-operator marginB10">
            <a-button @click="addOrEditDetail(1)" type="primary" icon="plus" size="small">新增</a-button>
          </div>

          <a-table
            ref="table"
            size="middle"
            bordered
            rowKey="id"
            :columns="columns1"
            :dataSource="dataSource1"
            :loading="loading1"
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
      </a-collapse>
    </div>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import JDate from '@/components/jeecg/JDate'
  import JDictSelectTag from "@/components/dict/JDictSelectTag"
  import { setModal } from '@/mixins/setModal'
  export default {
    name: "CsmPurchaseModal",
    mixins: [setModal],
    components: {
      JDate,
      JDictSelectTag,
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title:"操作",
        ModalDisabled: false, //控制可否编辑
        modalStatus: '', //模态框状态
        activeKey_collapse: ['1', '2'],
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
        date:{},
        code:{},
        amount:{},
        status:{},
        },
        url: {
          add: "/purchase/csmPurchase/add",
          edit: "/purchase/csmPurchase/edit",
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
          this.form.setFieldsValue(pick(this.model,'date','code','amount','status'))
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
        this.form.setFieldsValue(pick(row,'date','code','amount','status'))
      }

    }
  }
</script>