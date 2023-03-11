<template>
  <Layout>

    <Title title="工资单审批"></Title>
    <div style="margin-top: 10px">
      <el-table
          :data="salaryList"
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
            prop="staffID"
            label="员工编号"
            width="80">
        </el-table-column>
        <el-table-column
            prop="staffName"
            label="姓名"
            width="100"
        >
        </el-table-column>
        <el-table-column
            prop="staffBankCard"
            label="银行账户"
            width="120">
        </el-table-column>
        <el-table-column
            prop="originSalary"
            label="应发工资(元)"
            width="120">
        </el-table-column>
        <el-table-column
            prop="tax"
            label="扣除税款(元)"
            width="120">
        </el-table-column>
        <el-table-column
            prop="absenceDeduction"
            label="缺勤扣除(元)"
            width="120">
        </el-table-column>
        <el-table-column
            prop="actualSalary"
            label="实发金额(元)"
            width="120">
        </el-table-column>
        <el-table-column
            prop="state"
            label="单据状态"
            width="120">
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
import {financeFindSalaryByState,approvalSalary} from "@/network/finance/Finance";
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
      salaryList: [],
      addList : false,
    }
  },
  async mounted() {
    financeFindSalaryByState({params:{state:"待审批"}}).then(_res=>{
      this.salaryList = this.salaryList.concat(_res.result)
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
        let config ={
          params:{
            salarySheetId:id,state:"审批完成"
          }
        }
        console.log(config)
        approvalSalary(config).then(_res=>{
          this.$message("操作成功")
          this.$router.go(0)
        })
    },

    approvalFailure(id){
      approvalSalary({params:{salarySheetId:id,state:"审批失败"}}).then(_res=>{
        this.$message("操作成功")
        this.$router.go(0)
      })
    },




  }
}
</script>

<style>

</style>