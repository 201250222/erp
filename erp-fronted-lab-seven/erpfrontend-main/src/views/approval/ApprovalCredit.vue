<template>
  <Layout>

    <Title title="收款单审批"></Title>
    <div style="margin-top: 10px">
      <el-table
          :data="creditList"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="id"
            label= "单据编号"
            width="140">
        </el-table-column>
        <el-table-column
            prop="customer"
            label="客户"
            width="100">
        </el-table-column>
        <el-table-column
            prop="operator"
            label="操作员"
            width="100"
        >
        </el-table-column>
        <el-table-column
            prop="totalAmount"
            label="总额汇总"
            width="150">
        </el-table-column>
        <el-table-column
            prop="creditSheetState"
            label="单据状态"
            width="150">
        </el-table-column>
        <el-table-column
            prop="createTime"
            label="制定日期"
            width="150">
        </el-table-column>
        <el-table-column
            label="审批">
          <template slot-scope="scope">
            <el-button
                @click.native.prevent="approvalSuccess(scope.row.id)"
                type="text"
                size="small">
              通过
            </el-button>
          </template>
        </el-table-column>
        <el-table-column
            label="审批">
          <template slot-scope="scope">
            <el-button
                @click.native.prevent="approvalFailure(scope.row.id)"
                type="text"
                size="small">
              拒绝
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>


  </Layout>
</template>

<script>
import {
  financeFindCreditByState, approvalCredit
} from "@/network/finance/Finance";
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";export default {
  name: 'ApprovalSalary',
  components: {
    Layout,
    Title
  },
  data() {
    return {
      date:'',
      creditList: [],
      addList : false,
    }
  },
  async mounted() {
    financeFindCreditByState({params:{state:'待审批'}}).then(_res=>{
      this.creditList = this.creditList.concat(_res.result)
      console.log(this.creditList)
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
    approvalSuccess(id){
      console.log(id)
      approvalCredit({params:{id:id,state:"审批完成"}}).then(_res=>{
        this.$message("操作成功")
        this.$router.go(0)
      })
    },

    approvalFailure(id){
      approvalCredit({params:{id:id,state:"审批失败"}}).then(_res=>{
        this.$message("操作成功")
        this.$router.go(0)
      })
    },




  }
}
</script>

<style>

</style>