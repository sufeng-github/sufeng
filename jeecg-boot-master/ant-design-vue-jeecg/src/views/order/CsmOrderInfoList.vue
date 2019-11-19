<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :md="6" :sm="8">
            <a-form-item label="手术类型">
              <a-input placeholder="请输入手术类型" v-model="queryParam.opeId"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="12" :sm="16">
            <a-form-item label="日期">
              <j-date placeholder="请选择开始日期" class="query-group-cust" v-model="queryParam.date_begin"></j-date>
              <span class="query-group-split-cust"></span>
              <j-date placeholder="请选择结束日期" class="query-group-cust" v-model="queryParam.date_end"></j-date>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :md="6" :sm="8">
              <a-form-item label="工具箱">
                <a-input placeholder="请输入工具箱" v-model="queryParam.toolId"></a-input>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="螺钉架">
                <a-input placeholder="请输入螺钉架" v-model="queryParam.scrId"></a-input>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="医院">
                <a-input placeholder="请输入医院" v-model="queryParam.hospitalId"></a-input>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="患者">
                <a-input placeholder="请输入患者" v-model="queryParam.patient"></a-input>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="编号">
                <a-input placeholder="请输入编号" v-model="queryParam.code"></a-input>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="病床号">
                <a-input placeholder="请输入病床号" v-model="queryParam.bednum"></a-input>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="销售公司">
                <m-dict-select-tag placeholder="请输入销售公司" v-model="queryParam.status" dictCode="csm_company"/>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="客户">
                <a-input placeholder="请输入客户" v-model="queryParam.customerId"></a-input>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="发票号">
                <a-input placeholder="请输入发票号" v-model="queryParam.invoices"></a-input>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="状态">
                <j-dict-select-tag placeholder="请选择状态" v-model="queryParam.status" dictCode="orderStatus"/>
              </a-form-item>
            </a-col>
          </template>
          <a-col :md="6" :sm="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->
    
    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('csm_order_info')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">
        
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无此图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="图片不存在" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无此文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="uploadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <csmOrderInfo-modal ref="modalForm" @ok="modalFormOk"></csmOrderInfo-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import CsmOrderInfoModal from './modules/CsmOrderInfoModal'
  import JDictSelectTag from '@/components/dict/JDictSelectTag.vue'
  import MDictSelectTag from '@/componentsmy/combo/JDictSelectTag.vue'
  import JDate from '@/components/jeecg/JDate.vue'
  import {initDictOptions, filterMultiDictText} from '@/components/dict/JDictSelectUtil'
  import {initDictOptionsmy} from '@/componentsmy/combo/JDictSelectUtil'
  export default {
    name: "CsmOrderInfoList",
    mixins:[JeecgListMixin],
    components: {
      JDictSelectTag,
      MDictSelectTag,
      JDate,
      CsmOrderInfoModal
    },
    data () {
      return {
        description: 'csm_order_info管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'手术类型',
            align:"center",
            dataIndex: 'opeId',
            customRender:(text)=>{
              if(!text){
                return ''
              }else{
                return filterMultiDictText(this.dictOptions['opeId'], text+"")
              }
            }
          },
          {
            title:'日期',
            align:"center",
            dataIndex: 'date',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'工具箱',
            align:"center",
            dataIndex: 'toolId',
            customRender:(text)=>{
              if(!text){
                return ''
              }else{
                return filterMultiDictText(this.dictOptions['toolId'], text+"")
              }
            }
          },
          {
            title:'螺钉架',
            align:"center",
            dataIndex: 'scrId',
            customRender:(text)=>{
              if(!text){
                return ''
              }else{
                return filterMultiDictText(this.dictOptions['scrId'], text+"")
              }
            }
          },
          {
            title:'医院',
            align:"center",
            dataIndex: 'hospitalId',
            customRender:(text)=>{
              if(!text){
                return ''
              }else{
                return filterMultiDictText(this.dictOptions['hospitalId'], text+"")
              }
            }
          },
          {
            title:'患者',
            align:"center",
            dataIndex: 'patient'
          },
          {
            title:'编号',
            align:"center",
            dataIndex: 'code'
          },
          {
            title:'病床号',
            align:"center",
            dataIndex: 'bednum'
          },
          {
            title:'销售公司',
            align:"center",
            dataIndex: 'companyId',
            customRender:(text)=>{
              if(!text){
                return ''
              }else{
                return filterMultiDictText(this.dictOptions['companyId'], text+"")
              }
            }
          },
          {
            title:'客户',
            align:"center",
            dataIndex: 'customerId',
            customRender:(text)=>{
              if(!text){
                return ''
              }else{
                return filterMultiDictText(this.dictOptions['customerId'], text+"")
              }
            }
          },
          {
            title:'发票号',
            align:"center",
            dataIndex: 'invoices'
          },
          {
            title:'状态',
            align:"center",
            dataIndex: 'status',
            customRender:(text)=>{
              if(!text){
                return ''
              }else{
                return filterMultiDictText(this.dictOptions['status'], text+"")
              }
            }
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
        url: {
          list: "/order/csmOrderInfo/list",
          delete: "/order/csmOrderInfo/delete",
          deleteBatch: "/order/csmOrderInfo/deleteBatch",
          exportXlsUrl: "/order/csmOrderInfo/exportXls",
          importExcelUrl: "order/csmOrderInfo/importExcel",
        },
        dictOptions:{
         opeId:[],
         toolId:[],
         scrId:[],
         hospitalId:[],
         companyId:[],
         customerId:[],
         status:[],
        } 
      }
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      }
    },
    methods: {
      initDictConfig(){
        initDictOptions('').then((res) => {
          if (res.success) {
            this.$set(this.dictOptions, 'opeId', res.result)
          }
        })
        initDictOptions('').then((res) => {
          if (res.success) {
            this.$set(this.dictOptions, 'toolId', res.result)
          }
        })
        initDictOptions('').then((res) => {
          if (res.success) {
            this.$set(this.dictOptions, 'scrId', res.result)
          }
        })
        initDictOptions('').then((res) => {
          if (res.success) {
            this.$set(this.dictOptions, 'hospitalId', res.result)
          }
        })
        initDictOptionsmy('csm_company').then((res) => {
          if (res.success) {
            this.$set(this.dictOptions, 'companyId', res.result)
          }
        })
        initDictOptions('').then((res) => {
          if (res.success) {
            this.$set(this.dictOptions, 'customerId', res.result)
          }
        })
        initDictOptions('orderStatus').then((res) => {
          if (res.success) {
            this.$set(this.dictOptions, 'status', res.result)
          }
        })
      }
       
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>