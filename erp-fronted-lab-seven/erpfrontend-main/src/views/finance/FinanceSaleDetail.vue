<template>
  <Layout>

    <Title title="销售明细表"></Title>
    <el-button type="primary" size="medium" @click="addList  = true">查看销售明细表</el-button>
      <download-excel
          :data="saleDetailList"
          :fields="json_fields"
          name="销售明细表.xls"
          style="display: inline-block;margin-left:10px"
      >
        <el-button type="primary">导出excel</el-button>
      </download-excel>

    <div style="margin-top: 10px">
      <el-table
          :data="saleDetailList"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="time"
            label= "时间"
            width="100"
            :formatter= dateFormat>
        </el-table-column>
        <el-table-column
            prop="productName"
            label="商品名"
            width="100">
        </el-table-column>
        <el-table-column
            prop="type"
            label="型号"
            width="200">
        </el-table-column>
        <el-table-column
            prop="quantity"
            label="数量"
            width="100">
        </el-table-column>
        <el-table-column
            prop="unitPrice"
            label="单价(元)"
            width="100">
        </el-table-column>
        <el-table-column
            prop="totalPrice"
            label="总额(元)"
            width="150">
        </el-table-column>
      </el-table>
    </div>

    <el-dialog
        title="制定收款单"
        :visible.sync="addList"
        width="40%"
        :before-close="handleClose">
      <el-form :label-width="'100px'" size="mini">
          <el-date-picker
              type="date"
              placeholder="选择开始日期"
              value-format="yyyy-MM-dd"
              v-model="saleDetailForm.beginTime"
              style="width: 40%"
          ></el-date-picker>
         <el-date-picker
             type="date"
             placeholder="选择结束日期"
             value-format="yyyy-MM-dd"
             v-model="saleDetailForm.endTime"
             style="width: 40% ;left:15px"
         ></el-date-picker>
        <el-select v-model="saleDetailForm.productName"
                 placeholder="请选择商品名"
                   style="width: 51%;top: 10px"
      >
        <el-option
            v-for="(item) in commodityList"
            :key="item.name"
            :label=" '商品名: '+ item.name"
            :value="item.name">
        </el-option>
        </el-select>
      <el-select v-model="saleDetailForm.customer"
                 placeholder="请选择客户"
                 style="width: 51%; top:20px;"
      >
        <el-option
            v-for="(item) in customerList"
            :key="item.id"
            :label=" '客户编号:'+ item.id+' ' +'姓名:' +item.name"
            :value="item.id">
        </el-option>
      </el-select>
      <el-select v-model="saleDetailForm.operator"
                 placeholder="请选择业务员"
                 style="width: 51%; top:28px;"
      >
        <el-option
            v-for="(item) in staffList"
            :key="item.name"
            :label="  '员工姓名:' +item.name"
            :value="item.name">
        </el-option>
      </el-select>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" style="top: 10px" @click="findSaleDetailTable()">查询</el-button>
      </div>
    </el-dialog>

  </Layout>
</template>

<script>
import {financeCreditCreate, financeFindAllSalary, accountFindAll,financeFindCreditByState,financeFindSaleDetail} from "@/network/finance/Finance";
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import {getAllCustomer} from "@/network/purchase";
import {getAllCommodity, getAllCommodityClassification} from "@/network/commodity";
import {staff_find_all} from "@/network/staff/staff";
// import  from "xlsx";

export default {
  name: 'FinanceSaleDetail',
  components: {
    Layout,
    Title
  },
  data() {
    return {
      date:'',
      commodityList: [],
      salaryList: [],
      saleDetailForm:{
        beginTime:'',
        endTime:'',
        productName:'',
        customer:'',
        operator:'',
        warehouse:'',

      },
      customerList:[],
      addList : false,
      accountList:[],
      saleDetailList:[],
      staffList:[],
      json_fields: {
        "时间": "time",
        "商品名": 'productName',
        "型号": 'type',
        "数量": 'quantity',
        "单价(元)": 'unitPrice',
        "总额(元)": 'totalPrice',
      },


    }
  },
  async mounted() {
    await getAllCustomer({ params : { type:'供应商' } }).then(_res => {
      this.customerList = this.customerList.concat(_res.result)
    })
    await getAllCustomer({ params : { type:'销售商' } }).then(_res => {
      this.customerList = this.customerList.concat(_res.result)
    })
    accountFindAll().then(_res => {
      this.accountList = this.accountList.concat(_res.result)
    })
    getAllCommodity({}).then(_res => {
      this.commodityList = _res.result;
    })
    staff_find_all({}).then(_res => {
      this.staffList = this.staffList.concat(_res.result)
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

    findSaleDetailTable(){
      if (this.saleDetailForm.endTime<=this.saleDetailForm.beginTime){
        alert("结束日期应大于开始日期")
      }else {
        financeFindSaleDetail(this.saleDetailForm).then(_res => {
          if (_res.msg === 'Success') {
            this.saleDetailList = this.saleDetailList.concat(_res.result)
            console.log(this.saleDetailList)
            this.$message.success('创建成功!')
            this.addList = false
          }
        })
      }
    },
    dateFormat:function (row){
      return row.time.substring(0,10)
    },



  }
}
</script>

<style>

</style>