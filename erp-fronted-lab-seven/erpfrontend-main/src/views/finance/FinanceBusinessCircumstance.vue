<template>
  <Layout>

    <Title title="经营情况表"></Title>
    <el-button type="primary" size="medium" @click="addList  = true">查看经营情况表</el-button>
    <download-excel
        :data="financeBusinessCircumstanceList"
        name="经营情况表.xls"
        style="display: inline-block;margin-left:10px"
    >
      <el-button type="primary">导出excel</el-button>
    </download-excel>
    <div style="margin-top: 10px">
      <el-table
          :data="financeBusinessCircumstanceList"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="beginDate"
            label= "开始日期"
            width="100">
        </el-table-column>
        <el-table-column
            prop="endDate"
            label= "结束日期"
            width="100">
        </el-table-column>
        <el-table-column
            prop="discounts"
            label="折让金额(元)"
            width="120">
        </el-table-column>
        <el-table-column
            prop="actualIncoming"
            label="折让后收入(元)"
            width="120">
        </el-table-column>
        <el-table-column
            prop="outgoings"
            label="支出(元)"
            width="100">
        </el-table-column>
        <el-table-column
            prop="profits"
            label="利润(元)"
            width="100">
        </el-table-column>
      </el-table>
    </div>

    <el-dialog
        title=""
        :visible.sync="addList"
        width="40%"
        :before-close="handleClose">
      <el-form :label-width="'100px'" size="mini">
        <el-date-picker
            type="date"
            placeholder="选择开始日期"
            value-format="yyyy-MM-dd"
            v-model="financeBusinessCircumstanceForm.beginDate"
            style="width: 40%"
        ></el-date-picker>
        <el-date-picker
            type="date"
            placeholder="选择结束日期"
            value-format="yyyy-MM-dd"
            v-model="financeBusinessCircumstanceForm.endDate"
            style="width: 40% ;left:15px"
        ></el-date-picker>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" style="top: 10px" @click="findSaleDetailTable()">查询</el-button>
      </div>
    </el-dialog>

  </Layout>
</template>

<script>
import { businessCircumstance} from "@/network/finance/Finance";
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";


export default {
  name: 'FinanceBusinessCircumstance',
  components: {
    Layout,
    Title
  },
  data() {
    return {
      date:'',
      commodityList: [],
      financeBusinessCircumstanceForm:{
        beginDate:'',
        endDate:'',
        actualIncoming:'',
        discounts:'',
        outgoings:'',
        profits:'',

      },
      customerList:[],
      addList : false,
      accountList:[],
      financeBusinessCircumstanceList:[],
      staffList:[],
    }
  },
  async mounted() {

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

    findSaleDetailTable(){
      if (this.financeBusinessCircumstanceForm.endDate<=this.financeBusinessCircumstanceForm.beginDate){
        alert("结束日期应大于开始日期")
      }else {
        console.log(this.financeBusinessCircumstanceForm)
        businessCircumstance(this.financeBusinessCircumstanceForm).then(_res => {
          if (_res.msg === 'Success') {
            this.financeBusinessCircumstanceList = this.financeBusinessCircumstanceList.concat(_res.result)
            console.log(_res.result)
            this.$message.success('创建成功!')
            this.addList = false
          }
        })
      }
    },







  }
}
</script>

<style>

</style>