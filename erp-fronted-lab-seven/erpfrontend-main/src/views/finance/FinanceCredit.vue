<template>
  <Layout>

    <Title title="收款单制定"></Title>
    <el-button type="primary" size="medium" @click="addList  = true">制定收款单</el-button>
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
            width="120"
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
            width="150"
        :formatter="dateFormat">
        </el-table-column>
        <el-table-column
            label="详情"
            width="100"
           >
          <template slot-scope="scope">
            <el-button
                @click.native.prevent="detail(scope.row.creditSheetContent)"
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
        <el-form :model="receipt" label-width="80px" ref="receipt" >
          <el-form-item label="客户: " prop="supplier">
            <el-select v-model="receipt.customer"
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
              v-for="(item, index) in receipt.creditSheetContent"
              :key="index"
              :label="'转账列表' + index">
            <div>
              <el-select v-model="item.accountName" placeholder="收款账户" style="width: 61%; margin-right: 5%">
                <el-option
                    v-for="item1 in accountList"
                    :key="item1.name"
                    :label="item1.name"
                    :value="item1.name">
                </el-option>
              </el-select>
              <el-input v-model="item.amount" style="width: 45%; margin-right: 5%;top: 4px" placeholder="请输入金额"></el-input>
              <el-input v-model="item.remark" style="width: 45%;top: 4px" placeholder="请输入备注"></el-input>
            </div>
            <div style="margin-top: 10px">
              <el-button type="text" size="small" @click="addProduct" v-if="index === receipt.creditSheetContent.length - 1">添加</el-button>
              <el-button type="text" size="small" @click.prevent="removeProduct(item)" v-if="index !== 0">删除</el-button>
            </div>
          </el-form-item>
        </el-form>
      </div>


        <el-button type="primary" @click = "createCreditTable()" >立即创建</el-button>
      </span>
    </el-dialog>

    <el-dialog
        title="收款单详情"
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
            prop="accountName"
            label="账户名"
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
import {financeCreditCreate, accountFindAll,financeFindCreditByState} from "@/network/finance/Finance";
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
      receipt:{
        id:'',
        customer:'',
        operator:sessionStorage.getItem("name"),
        total_amount:'',
        creditSheetState:'待审批',
        createTime: '',
        creditSheetContent:[ {
          id:'',
          purchaseSheetId:'',
          accountName:'',
          amount:'',
          remark:''
        }],
      },
      customerList:[],
      addList : false,
      accountList:[],
      creditList:[],
      detailContent:[],
      seeDetail:false,
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
    financeFindCreditByState({params:{state:''}}).then(_res=>{
       this.creditList = this.creditList.concat(_res.result)
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
            this.receipt.customer=''
            done();
          })
          .catch(_ => {});
    },

    createCreditTable(){
      console.log(this.receipt)
      financeCreditCreate(this.receipt).then(_res=>{
        if (_res.msg === 'Success') {
          this.$message.success('创建成功!')
          this.addList = false
        }
      })
    },

    addProduct() {
      this.receipt.creditSheetContent.push({id:'',
        purchaseSheetId:'',
        accountName:'',
        amount:'',
        remark:''});
    },
    removeProduct(item) {
      let index = this.receipt.creditSheetContent.indexOf(item)
      if (index !== -1) {
        this.receipt.creditSheetContent.splice(index, 1)
      }
    },
    dateFormat:function (row){
      return row.createTime.substring(0,10)
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