<template>
  <a-modal
    :title="title"
    :width="900"
    :visible="visible"
    :maskClosable="false"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
  >
    <a-spin :spinning="confirmLoading">
      <!-- 查询区域 -->
      <div class="table-page-search-wrapper searchWrapper smallSelectTag">
        <a-form layout="inline">
          <a-row :gutter="24">
            <a-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <a-form-item label="物料编码">
                <a-input placeholder="请输入物料编码" v-model="queryParamShow.code" @change="mohuSearch($event,'code')"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <a-form-item label="物料名称" class="ssetMaxWidth">
                <a-input placeholder="请输入物料名称" v-model="queryParamShow.name" @change="mohuSearch($event,'name')"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
                <a-button type="primary" @click="searchQuery" icon="search" size="small">查询</a-button>
                <!-- <a-button
                  type="primary"
                  @click="searchReset"
                  icon="reload"
                  style="margin-left: 8px"
                  size="small"
                >重置</a-button> -->
              </span>
            </a-col>
          </a-row>
        </a-form>
      </div>

      <!-- table区域-begin -->
      <div>
        <div class="ant-alert ant-alert-info">
          <i class="anticon anticon-info-circle ant-alert-icon"></i>
          <span>已选择</span>
          <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>
          <span>项</span>
          <a style="margin-left: 24px" @click="onClearSelected">清空</a>
        </div>

        <a-table
          ref="table"
          size="middle"
          bordered
          rowKey="id"
          :columns="columns"
          :dataSource="dataSource" :pagination="ipagination" :loading="loading"
          :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange,type:'radio'}"
          @change="handleTableChange"
        ></a-table>
      </div>
      <!-- table区域-end -->
    </a-spin>
  </a-modal>
</template>

<script>
import { JeecgListMixin } from '@/mixins/JeecgListMixin'

export default {
  name: 'AssociationFormModal',
  mixins: [JeecgListMixin],

  data() {
    return {
      description: '',
      // 表头
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
          title: '物料编码',
          align: 'center',
          dataIndex: 'code',
          sorter: (a, b) => a.code - b.code
        },
        {
          title: '物料名称',
          align: 'center',
          dataIndex: 'name',
          sorter: (a, b) => a.name - b.name
        },
        {
          title: '物料描述',
          align: 'center',
          dataIndex: 'remark',
          sorter: (a, b) => a.remark - b.remark
        },
        {
          title: '单位',
          align: 'center',
          dataIndex: 'unit',
          sorter: (a, b) => a.unit - b.unit
        }
      ],
      // 请求参数
      url: {
        list: '/rd/rdMaterialsInfo/list' //获取物料信息列表
      },
      visible: false,
      title: '物料信息',
      confirmLoading: false,

    }
  },
  computed: {},
  created() {},
  methods: {
    initDictConfig() {},
    handleOk() {
      if(this.selectionRows.length==0){
        this.$message.warning("请选择一条物料信息");
      }else{
        this.$emit('ok', this.selectionRows);
        this.close();
      }
    },
    handleCancel() {
      this.close();
    },
    close() {
      this.$emit('close');
      this.visible = false;
      this.onClearSelected();
      this.searchReset();
      this.queryParam = {};
      this.queryParamShow={};
    }
  }
}
</script>
<style scoped>
@import '~@assets/less/common.less';

/* .searchWrapper .ant-form-inline .ant-form-item {
    margin-bottom: 5px;
}
.searchWrapper .table-page-search-submitButtons {
    margin-bottom: 5px;
}
.ant-table-body table {
    margin-top: 5px!important;
}
.ant-alert {
    padding: 2px 15px 2px 33px;
} */
</style>