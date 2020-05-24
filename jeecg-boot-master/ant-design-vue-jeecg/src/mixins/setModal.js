import { filterObj } from '@/utils/util';
import { deleteAction, getAction,downFile ,httpAction} from '@/api/manage'
import Vue from 'vue'
import { ACCESS_TOKEN } from "@/store/mutation-types"
export const setModal = {
  components: {
    
  },
  data() {
    return {
      title: '操作',
      repeatForm: this.$form.createForm(this),
      //步骤条参数，展示的表单
      addCurrent: 0,
      editCurrent: 0,
      tabIndex: 0


    }
  },
  created() {
    //初始化
    this.initPage();
  },
  methods: {

    initPage(){

    },

    //下载模板
    downloadTemplateFunc(){
      window.location.href=this.url.downloadTemplate;
      this.loadData();
    },

    //去明细tab
    //如果当前弹窗是新增，控制新增的参数，
    //如果当前弹窗是编辑，控制新增的参数，
    //查看没有步骤条，无需控制
    goDetail() {
      if(this.modalStatus == 'add'){
        this.addCurrent = 1;
        this.tabIndex = 1;
      }
      if(this.modalStatus == 'edit'){
        this.editCurrent = 1;
        this.tabIndex = 1;
      }
    },
    //只有编辑可以返回主表修改
    goMain() {
      this.editCurrent = 0
      this.tabIndex = 0
    },



    
  }
}