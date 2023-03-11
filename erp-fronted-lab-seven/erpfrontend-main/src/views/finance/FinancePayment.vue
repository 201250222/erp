<template>
  <Layout>

    <Title title="付款单制定"></Title>
    <el-button type="primary" size="medium" @click="addList  = true">制定收款单</el-button>
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
            prop="accountName"
            label="账户名"
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
            label="详情"
            width="100"
        >
          <template slot-scope="scope">
            <el-button
                @click.native.prevent="detail(scope.row.paymentSheetContent)"
                type="text"
                size="small">
              查看
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-dialog
        title="制定收款单"
        :visible.sync="addList"
        width="40%"
        :before-close="handleClose">
      <span slot="footer" class="dialog-footer">

        <div style="width: 100%; margin: 0 auto">
        <el-form :model="paymentForm" label-width="80px" ref="receipt" >
          <el-form-item label="账户名称: " prop="supplier">
            <el-select v-model="paymentForm.accountName"
                       placeholder="请选择银行账户"
            >
              <el-option
                  v-for="(item) in accountList"
                  :key="item.name"
                  :label=" item.name"
                  :value="item.name">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="客户: " prop="supplier">
          <el-select v-model="paymentForm.customer"
                     placeholder="请选择客户"
          >
              <el-option
                  v-for="(item) in customerList"
                  :key="item.id"
                  :label=" '客户编号:'+ item.id+' ' +'姓名:' +item.name"
                  :value="item.id">
              </el-option>
            </el-select>
            </el-form-item>
          <el-form-item
              v-for="(item, index) in paymentForm.paymentSheetContent"
              :key="index"
              :label="'条目清单' + index">
            <div>
              <el-input v-model="item.itemName" style="width: 60%; margin-right: 5%" placeholder="请输入条目名称"></el-input>
              <el-input v-model="item.amount" style="width: 45%; margin-right: 5%;top: 6px" placeholder="请输入金额"></el-input>
              <el-input v-model="item.remark" style="width: 45%;top: 6px" placeholder="请输入备注"></el-input>
            </div>
            <div style="margin-top: 10px">
              <el-button type="text" size="small" @click="addProduct" v-if="index === paymentForm.paymentSheetContent.length - 1">添加</el-button>
              <el-button type="text" size="small" @click.prevent="removeProduct(item)" v-if="index !== 0">删除</el-button>
            </div>
          </el-form-item>
        </el-form>
      </div>


        <el-button type="primary" @click = "createPaymentTable()" >立即创建</el-button>
      </span>
    </el-dialog>

    <el-dialog
        title="付款单详情"
        :visible.sync="seeDetail"
        width="40%"
        :before-close="handleClose">
      <el-table
          :data="this.detailContent"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="id"
            label= "编号"
            width="100">
        </el-table-column>
        <el-table-column
            prop="customer"
            label="客户编号"
            width="100">
        </el-table-column>
        <el-table-column
            prop="amount"
            label="金额"
            width="120"
        >
        </el-table-column>
        <el-table-column
            prop="remark"
            label="备注"
            width="120"
        >
        </el-table-column>

      </el-table>

    </el-dialog>
  </Layout>
</template>

<script>
import {financeSalaryCreate,financeFindPaymentByState,financeCreditCreate, financePaymentCreate,financeFindAllSalary, accountFindAll,financeFindCreditByState} from "@/network/finance/Finance";
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import {getAllCustomer} from "@/network/purchase";

export default {
  name: 'FinanceCredit',
  components: {
    Layout,
    Title
  },
  data() {
    return {
      date:'',
      salaryList: [],
      paymentForm:{
        id:'',
        operator:sessionStorage.getItem("name"),
        total_amount:'',
        accountName:'',
        creditSheetState:'待审批',
        createTime: '',
        paymentSheetContent:[ {
          id:'',
          purchaseSheetId:'',
          itemName:'',
          amount:'',
          remark:''
        }],
      },
      addList : false,
      accountList:[],
      customerList:[],
      paymentList:[],
      detailContent:[],
      seeDetail:false,
    }
  },
  async mounted() {
    await getAllCustomer({ params : { type:'SUPPLIER' } }).then(_res => {
      this.customerList = this.customerList.concat(_res.result)
    })
    await getAllCustomer({ params : { type:'SELLER' } }).then(_res => {
      this.customerList = this.customerList.concat(_res.result)
    })
    accountFindAll().then(_res => {
      this.accountList = this.accountList.concat(_res.result)
    })
    financeFindPaymentByState({params:{state:""}}).then(_res=>{
      this.paymentList = this.paymentList.concat(_res.result)
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

    createPaymentTable(){
      console.log(this.paymentForm)
      financePaymentCreate(this.paymentForm).then(_res=>{
        if (_res.msg === 'Success') {
          this.$message.success('创建成功!')
          this.addList = false
        }
      })
    },

    addProduct() {
      this.paymentForm.paymentSheetContent.push({
        id:'',
        purchaseSheetId:'',
        accountName:'',
        amount:'',
        remark:''});
    },
    removeProduct(item) {
      let index = this.paymentForm.paymentSheetContent.indexOf(item)
      if (index !== -1) {
        this.paymentForm.paymentSheetContent.splice(index, 1)
      }
    },
    detail(content){
      this.detailContent = content
      this.seeDetail = true
    }






  }
}
</script>

<style>

</style>