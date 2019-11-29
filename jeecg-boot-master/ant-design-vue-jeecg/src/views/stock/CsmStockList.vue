<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :md="6" :sm="8">
            <a-form-item label="序列号">
              <a-input placeholder="请输入序列号" v-model="queryParam.serialnum"></a-input>
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
              <a-form-item label="批号">
                <a-input placeholder="请输入批号" v-model="queryParam.lotnum"></a-input>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="有效日期">
                <j-date placeholder="请选择有效日期" v-model="queryParam.valuedate"></j-date>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="生产日期">
                <j-date placeholder="请选择生产日期" v-model="queryParam.pd"></j-date>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="灭菌号">
                <a-input placeholder="请输入灭菌号" v-model="queryParam.mjn"></a-input>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="bomId">
                <a-input placeholder="请输入bomId" v-model="queryParam.bomId"></a-input>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="名称">
                <a-input placeholder="请输入名称" v-model="assistParam.name"></a-input>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="规格">
                <a-input placeholder="请输入规格" v-model="assistParam.species"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('csm_stock')">导出</a-button>
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

    <csmStock-modal ref="modalForm" @ok="modalFormOk"></csmStock-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import CsmStockModal from './modules/CsmStockModal'
  import JDate from '@/components/jeecg/JDate.vue'
  import {initDictOptionsmy} from '@/componentsmy/combo/JDictSelectUtil'
  import { getTextById } from '@/componentsmy/combo/JDictSelectUtil'
  import { getAction, httpAction, postAction,putAction } from '@/api/manage'
  import {initForeignKey,filterMultiIdText} from '@/componentsmy/combo/JDictSelectUtil'
  import {initDictOptions, filterMultiDictText} from '@/components/dict/JDictSelectUtil'
  export default {
    name: "CsmStockList",
    mixins:[JeecgListMixin],
    components: {
      JDate,
      CsmStockModal
    },
    data () {
      return {
        description: 'csm_stock管理页面',
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
            title:'名称',
            align:"center",
            customRender: (t,r) =>{
              if(!r.bomId){
                return ''
              }else{
                let res = filterMultiIdText(this.foreignKey.bomId, r.bomId+"")
                return res.name;
              }
            }
          },
          {
            title:'规格',
            align:"center",
            customRender: (t,r) =>{
              if(!r.bomId){
                return ''
              }else{
                let res = filterMultiIdText(this.foreignKey.bomId, r.bomId+"")
                return res.species;
              }
            }
          },

          {
            title:'序列号',
            align:"center",
            dataIndex: '


            '
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
            title:'数量',
            align:"center",
            dataIndex: 'quantity'
          },
          {
            title:'批号',
            align:"center",
            dataIndex: 'lotnum'
          },
          {
            title:'有效日期',
            align:"center",
            dataIndex: 'valuedate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'生产日期',
            align:"center",
            dataIndex: 'pd',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'灭菌号',
            align:"center",
            dataIndex: 'mjn'
          },
          {
            title:'安全库存',
            align:"center",
            dataIndex: 'safequantity'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
        url: {
          list: "/stock/csmStock/list",
          delete: "/stock/csmStock/delete",
          deleteBatch: "/stock/csmStock/deleteBatch",
          exportXlsUrl: "/stock/csmStock/exportXls",
          importExcelUrl: "stock/csmStock/importExcel",
        },
        dictOptions: {

        },
        /*辅助参数*/
        assistParam: {
          bomId:[],
        },
        foreignKey:{
          bomId:[],

        },

      }
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      }
    },
    methods: {
      initDictConfig(){

        let fk={fk:'bomId'};
        //var that=this;
        initForeignKey('csm_stock', fk).then((res) => {
          if (res.success) {
            //this.$set(that.dictOptions, 'bomId', res.result);
            //console.log(res.result);
            this.foreignKey.bomId = res.result

          }
        })

      }

    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>