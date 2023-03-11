<template>
  <Layout>

    <Title title="年终奖制定"></Title>
    <el-button type="primary" size="medium" @click="initBonus">初始化年终奖</el-button>
    <el-button type="primary" size="medium" @click="addList= true">制定年终奖</el-button>
<!--    <el-button type="primary" size="medium" @click="addList  = true">初始化年终奖</el-button>-->
    <div style="margin-top: 10px">
      <el-table
          :data="bonusList"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="staffId"
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
            prop="year"
            label="年份"
            width="110">
        </el-table-column>
        <el-table-column
            prop="bonus"
            label="年终奖"
            width="110">
        </el-table-column>

<!--                <el-table-column-->
<!--                    label="操作">-->
<!--                  <template slot-scope="scope">-->
<!--                    <el-button-->
<!--                        @click.native.prevent="editInfo(scope.row.id)"-->
<!--                        type="text"-->
<!--                        size="small">-->
<!--                      修改年终奖-->
<!--                    </el-button>-->
<!--                  </template>-->
<!--                </el-table-column>-->
      </el-table>
    </div>

    <el-dialog
        title="制定工资单"
        :visible.sync="addList"
        width="40%"
        :before-close="handleClose">
      <div style="width: 90%; margin: 0 auto">
        <el-select v-model="bonusForm.staffId"
                   placeholder="请选择员工"
                   style="width: 51%; top:20px;"
        >
          <el-option
              v-for="(item) in bonusList"
              :key="item.staffId"
              :label=" '员工编号:'+ item.staffId+' ' +'员工姓名:' +item.staffName"
              :value="item.staffId">
          </el-option>
        </el-select>
        <el-input type="number" v-model="bonusForm.bonus" placeholder="请输入年终奖" style="margin-top: 30px" ></el-input>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click = "updateBonus()" style="top: 50px">确认</el-button>
      </span>
    </el-dialog>

  </Layout>
</template>

<script>
import {financeSalaryCreate, financeFindAllSalary, financeFindSalaryByState,yearBonusFind,yearBonusGet,yearBonusUpdate} from "../../network/finance/Finance";
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";export default {
  name: 'FinanceYearBonus',
  components: {
    Layout,
    Title
  },
  data() {
    return {
      date:'',
      bonusList: [],
      addList : false,
      bonusForm:{
        staffId:"",
        staffName:'',
        staffBankCard:'',
        originSalary:'',
        tax:'',
        absenceDeduction:'',
        actualSalary: '',
        year:'',
        bonus: "",
      }
    }
  },
  async mounted() {
    yearBonusGet().then(_res=>{
      this.bonusList = this.bonusList.concat(_res.result)
      console.log(_res.result)
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
    initBonus(){
      yearBonusFind().then(_res=>{
        this.$message("初始化成功")
      })
    },

    updateBonus() {
      yearBonusUpdate(this.bonusForm).then(_res=>{
        if (_res.msg === 'Success') {
          this.$message.success('创功!')
          this.addList = false
        }
      })
    }
  }
}
</script>

<style>

</style>