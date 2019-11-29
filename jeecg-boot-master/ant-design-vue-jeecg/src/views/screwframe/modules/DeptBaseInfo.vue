<template>
  <a-card :visible="visible">
    <a-form :form="form">
      <a-form-item
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
        label="机构名称">
        <a-input style="border:0px;" placeholder="" v-decorator="['name', {}]"/>
      </a-form-item>

      <a-form-item
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
        label="机构编码">
        <a-input style="border:0px;" placeholder="" v-decorator="['code', {}]"/>
      </a-form-item>
      <a-form-item
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
        label="排序">
        <a-input-number style="border:0px;" v-decorator="[ 'status',{}]"/>
      </a-form-item>

      <a-form-item
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
        label="地址">
        <a-input style="border:0px;" placeholder="" v-decorator="['located', {}]"/>
      </a-form-item>

    </a-form>
<!--    <div class="anty-form-btn">
      <a-button @click="emptyCurrForm" type="default" htmlType="button" icon="sync">重置</a-button>
      <a-button @click="submitCurrForm" type="primary" htmlType="button" icon="form">修改并保存</a-button>
    </div>-->
  </a-card>
</template>
<script>
  import pick from 'lodash.pick'
  import {queryIdTree} from '@/api/api'

  export default {
    name: 'DeptBaseInfo',
    components: {},
    data() {
      return {
        departTree: [],
        id: '',
        model: {},
        visible: false,
        disable: true,
        treeData: [],
        form: this.$form.createForm(this),
        labelCol: {
          xs: {span: 24},
          sm: {span: 3}
        },
        wrapperCol: {
          xs: {span: 24},
          sm: {span: 16}
        },
      }
    },
    methods: {
      loadTreeData() {
        queryIdTree().then((res) => {
          if (res.success) {
            for (let i = 0; i < res.result.length; i++) {
              let temp = res.result[i];
              this.treeData.push(temp);
            }
          }

        })
      },
      open(record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        console.log("record:");
        console.log(record);
        this.$nextTick(() => {
/*delflag;
createBy;
createTime;
updateBy;
 updateTime;
sysOrgCode;*/
          this.form.setFieldsValue(pick(record, 'name', 'code', 'status', 'located'));
        });
      },
      clearForm() {
        this.form.resetFields();
        this.treeData = [];
      },
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>