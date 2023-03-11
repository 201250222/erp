<template>
  <Layout>
    <Title title="账户管理"></Title>
    <el-button type="primary" size="medium" @click="addList  = true">新增账户</el-button>
    <div style="margin-top: 10px">
      <el-table
          :data="accountList"
          stripe
          style="width: 75%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="name"
            label="名称"
            width="140">
        </el-table-column>
        <el-table-column
            prop="cardNum"
            label="银行卡号"
            width="140">
        </el-table-column>
        <el-table-column
            prop="balance"
            label="余额(元)"
            width="140">
        </el-table-column>
        <el-table-column
            label="修改名称"
            width="140">
          <template slot-scope="scope">
            <el-button
                @click.native.prevent="modifyBegin(scope.row.name)"
                type="text"
                size="small">
              修改
            </el-button>
          </template>
        </el-table-column>
        <el-table-column
            label="删除账户"
        width="140">
          <template slot-scope="scope">
            <el-button
                @click.native.prevent="removeAccount(scope.row.name)"
                type="text"
                size="small">
              删除
            </el-button>
          </template>
        </el-table-column>

      </el-table>
    </div>

    <el-dialog
        title="增加客户"
        :visible.sync="addList"
        width="40%"
        :before-close="handleClose">
      <div style="width: 90%; margin: 0 auto">
        <el-input type="text" v-model="accountVO.name" placeholder="请输入账户名称" ></el-input>
        <el-input type="number" v-model="accountVO.cardNum" placeholder="请输入账户银行卡号" ></el-input>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click = "createAccount()" >立即创建</el-button>
      </span>
    </el-dialog>
    <el-dialog
        title="增加客户"
        :visible.sync="amend"
        width="40%"
        :before-close="handleClose">
      <div style="width: 90%; margin: 0 auto">
        <el-input type="text" v-model="newName" placeholder="请输入新的名称" ></el-input>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click = "modifyAccount()" >确认修改</el-button>
      </span>
    </el-dialog>

  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import {accountModify,accountRemove,accountCreate,accountFindAll} from "@/network/finance/Finance";
export default {
  name: 'FinanceAccount',
  components: {
    Layout,
    Title
  },
  data() {
    return {
      accountVO: {
        name: '',
        cardNum: '',
      },
      accountList: [],
      addList : false,
      newName:'',
      amend:false,
      oldName:'',
    }
  },
  async mounted() {
      accountFindAll().then(_res => {
        this.accountList = this.accountList.concat(_res.result)
      })
  },
  methods: {
    filterTag(value, row) {
      return row.type === value
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            this.addList = false
            done();
          })
          .catch(_ => {});
    },

    createAccount(){
      accountCreate(this.accountVO).then(_res=>{
        this.$message("创建成功")
        this.addList = false
        this.accountVO.cardNum = ''
        this.accountVO.name = ''
        this.$router.go(0)
      })
    },
    removeAccount(presentName){
      console.log(presentName)
      accountRemove({params:{accountName:presentName}}).then(_res=>{
        this.$message("删除成功")
        this.$router.go(0)
      })
    },
    modifyBegin(oldName){
      this.oldName = oldName
      this.amend = true
    },
    modifyAccount(){
      accountModify({params:{oldName:this.oldName,newName:this.newName}}).then(_res=>{
        this.$message("修改成功")
        this.amend = false
        this.oldName = ''
        this.newName = ''
        this.$router.go(0)
      })
    },



    resetForm() {

    },



    editInfo(id) {
      // deleteCustomer({ params : { id:id } } ).then(_res => {
      //   console.log(id)
      //   if (_res.msg === 'Success') {
      //     // this.$message.success('创建成功!')
      //     // this.dialogVisible = false
      //   } else {
      //     // this.$message.error('Error!');
      //   }
      // })
      // alert("删除成功")
    }
  }
}
</script>

<style>

</style>