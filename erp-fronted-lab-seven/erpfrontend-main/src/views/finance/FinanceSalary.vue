<template>
  <Layout>

    <Title title="工资单制定"></Title>
    <el-button type="primary" size="medium" @click="addList  = true">制定工资单</el-button>
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
            width="125">
        </el-table-column>
        <el-table-column
            prop="staffID"
            label="员工编号"
            width="80">
        </el-table-column>
        <el-table-column
            prop="staffName"
            label="姓名"
            width="80"
        >
        </el-table-column>
        <el-table-column
            prop="staffBankCard"
            label="银行账户"
            width="100">
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
            width="110">
        </el-table-column>

        <!--        <el-table-column-->
        <!--            label="操作">-->
        <!--          <template slot-scope="scope">-->
        <!--            <el-button-->
        <!--                @click.native.prevent="editInfo(scope.row.id)"-->
        <!--                type="text"-->
        <!--                size="small">-->
        <!--              删除-->
        <!--            </el-button>-->
        <!--          </template>-->
        <!--        </el-table-column>-->
      </el-table>
    </div>

    <el-dialog
        title="制定工资单"
        :visible.sync="addList"
        width="40%"
        :before-close="handleClose">
      <div style="width: 90%; margin: 0 auto">
        <el-date-picker
            type="date"
            placeholder="选择日期"
            value-format="yyyy-MM-dd"
            v-model="date"
        ></el-date-picker>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click = "createSalaryTable()" >立即创建</el-button>
      </span>
    </el-dialog>

  </Layout>
</template>

<script>
import {financeSalaryCreate, financeFindAllSalary, financeFindSalaryByState} from "../../network/finance/Finance";
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";export default {
  name: 'FinanceSalary',
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
    financeFindSalaryByState({params:{state:""}}).then(_res=>{
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

    createSalaryTable(){
      console.log(this.date)
       if (this.date.toString().substring(8)!=="01"){
          alert("未到工资发放日期")
      }else {
         financeSalaryCreate({params:{date:this.date}}).then(_res=>{
                   this.$message.success('创建成功!')
                   this.addList = false
             this.salaryList = _res.result

         })
       }
    },
  }
}
</script>

<style>

</style>