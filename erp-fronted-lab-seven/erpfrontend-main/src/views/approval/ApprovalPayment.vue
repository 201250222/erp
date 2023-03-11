<template>
  <Layout>

    <Title title="付款单审批"></Title>
    <div style="margin-top: 10px">
      <el-table
          :data="paymentList"
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
            label="客户编号"
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
            prop="paymentSheetState"
            label="单据状态"
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
import {financeFindPaymentByState, approvalPayment
} from "@/network/finance/Finance";
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
export default {
  name: 'ApprovalPayment',
  components: {
    Layout,
    Title
  },
  data() {
    return {
      date:'',
      addList : false,
      paymentList:[],
    }
  },
  async mounted() {
    financeFindPaymentByState({params:{state:"待审批"}}).then(_res=>{
      this.paymentList = this.paymentList.concat(_res.result)
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
      approvalPayment({params:{id:id,state:"审批完成"}}).then(_res=>{
        this.$message("操作成功")
        this.$router.go(0)
      })
    },

    approvalFailure(id){
      approvalPayment({params:{id:id,state:"审批失败"}}).then(_res=>{
        this.$message("操作成功")
        this.$router.go(0)
      })
    },




  }
}
</script>

<style>

</style>