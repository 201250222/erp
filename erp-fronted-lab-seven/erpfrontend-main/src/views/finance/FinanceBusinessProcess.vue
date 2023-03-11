<template>
  <Layout>

    <Title title="经营历程表"></Title>
    <el-button type="primary" size="medium" @click="addList  = true">查看经营历程表</el-button>


    <div style="margin-top: 10px" v-if="showSale">
      <download-excel
          :data="saleList.saleBuyList"
          name="销售出货表.xls"
          style="display: inline-block;margin-left:10px"
      >
        <el-button type="primary">导出excel</el-button>
      </download-excel>
      <el-table
          :data="saleList.saleBuyList"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="type"
            label= "单据类型"
            width="80"
        >
        </el-table-column>
        <el-table-column
            prop="id"
            label= "单据编号"
            width="120"
            >
        </el-table-column>
        <el-table-column
            prop="supplier"
            label="客户编号"
            width="80">
        </el-table-column>
        <el-table-column
            prop="salesman"
            label="业务员"
            width="120">
        </el-table-column>
        <el-table-column
            prop="operator"
            label="操作员"
            width="120">
        </el-table-column>
        <el-table-column
            prop="remark"
            label="备注"
            width="100">
        </el-table-column>
        <el-table-column
            prop="rawTotalAmount"
            label="折让前总额(元)"
            width="120">
        </el-table-column>
        <el-table-column
            prop="discount"
            label="折让"
            width="100">
        </el-table-column>
        <el-table-column
            prop="voucherAmount"
            label="代金券总额(元)"
            width="120">
        </el-table-column>
        <el-table-column
            prop="finalAmount"
            label="折让后总额(元)"
            width="120">
        </el-table-column>
        <el-table-column
            prop="state"
            label="单据状态"
            width="100">
        </el-table-column>
        <el-table-column
            prop="createTime"
            label="创建时间"
            width="120"
            :formatter= dateFormat>
        </el-table-column>
        <el-table-column
            label="红冲"
            width="100"
        v-show="getUserRole!== 'Gm'">
          <template slot-scope="scope">
            <el-button
                @click.native.prevent="redFlush(scope.row)"
                type="text"
                size="small">
              确定
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <download-excel
          :data="saleList.saleReturnList"
          name="销售退货表.xls"
          style="display: inline-block;margin-left:10px"
      >
        <el-button type="primary">导出excel</el-button>
      </download-excel>
      <el-table
          :data="saleList.saleReturnList"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="type"
            label= "单据类型"
            width="80"
        >
        </el-table-column>
        <el-table-column
            prop="id"
            label= "单据编号"
            width="120"
        >
        </el-table-column>
        <el-table-column
            prop="saleSheetId"
            label= "关联销售单编号"
            width="120"
        >
        </el-table-column>
        <el-table-column
            prop="supplier"
            label="销售商"
            width="80">
        </el-table-column>
        <el-table-column
            prop="operator"
            label="操作员"
            width="120">
        </el-table-column>
        <el-table-column
            prop="remark"
            label="备注"
            width="100">
        </el-table-column>
        <el-table-column
            prop="state"
            label="单据状态"
            width="100">
        </el-table-column>
        <el-table-column
            prop="createTime"
            label="创建时间"
            width="120"
            :formatter= "dateFormat">
        </el-table-column>
        <el-table-column
            label="红冲"
            width="100"
            v-show="getUserRole!== 'Gm'">
          <template slot-scope="scope">
            <el-button
                @click.native.prevent="redFlush(scope.row)"
                type="text"
                size="small">
              确定
            </el-button>
          </template>
        </el-table-column>
      </el-table>

    </div>

    <div style="margin-top: 10px" v-if="showPurchase">
      <download-excel
          :data="purchaseList.purchaseBuyList"
          name="进货表.xls"
          style="display: inline-block;margin-left:10px"
      >
        <el-button type="primary">导出excel</el-button>
      </download-excel>
      <el-table
          :data="purchaseList.purchaseBuyList"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="type"
            label="类型"
            width="80">
        </el-table-column>
        <el-table-column
            prop="id"
            label="单据编号"
            width="120">
        </el-table-column>
        <el-table-column
            prop="supplier"
            label="供应商编号"
            width="100">
        </el-table-column>
        <el-table-column
            prop="operator"
            label="操作员"
            width="120">
        </el-table-column>
        <el-table-column
            prop="remark"
            label="备注"
            width="120">
        </el-table-column>
        <el-table-column
            prop="totalAmount"
            label="总金额(元)"
            width="120">
        </el-table-column>
        <el-table-column
            prop="state"
            label="单据状态"
            width="100">
        </el-table-column>
        <el-table-column
            prop="time"
            label= "创建时间"
            width="120"
            :formatter= dateFormat>
        </el-table-column>
        <el-table-column
            label="红冲"
            width="100"
            v-show="getUserRole!== 'Gm'">
          <template slot-scope="scope">
            <el-button
                @click.native.prevent="redFlush(scope.row)"
                type="text"
                size="small">
              确定
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <download-excel
          :data="purchaseList.purchaseReturnList"
          name="退货表.xls"
          style="display: inline-block;margin-left:10px"
      >
        <el-button type="primary">导出excel</el-button>
      </download-excel>
      <el-table
          :data="purchaseList.purchaseReturnList"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="type"
            label="类型"
            width="80">
        </el-table-column>
        <el-table-column
            prop="id"
            label="单据编号"
            width="120">
        </el-table-column>
        <el-table-column
            prop="purchaseSheetId"
            label="关联进货单id"
            width="120">
        </el-table-column>
        <el-table-column
            prop="operator"
            label="操作员"
            width="120">
        </el-table-column>
        <el-table-column
            prop="remark"
            label="备注"
            width="120">
        </el-table-column>
        <el-table-column
            prop="totalAmount"
            label="总金额(元)"
            width="120">
        </el-table-column>
        <el-table-column
            prop="state"
            label="单据状态"
            width="100">
        </el-table-column>
        <el-table-column
            prop="time"
            label= "创建时间"
            width="120"
            :formatter= dateFormat>
        </el-table-column>
        <el-table-column
            label="红冲"
            width="100"
            v-show="getUserRole!== 'Gm'">
          <template slot-scope="scope">
            <el-button
                @click.native.prevent="redFlush(scope.row)"
                type="text"
                size="small">
              确定
            </el-button>
          </template>
        </el-table-column>
      </el-table>

    </div>

    <div style="margin-top: 10px" v-if="showFinance">
      <download-excel
          :data="financeList.paymentList"
          name="付款表.xls"
          style="display: inline-block;margin-left:10px"
      >
        <el-button type="primary">导出excel</el-button>
      </download-excel>
      <el-table
          :data="financeList.paymentList"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="type"
            label= "单据类型"
            width="100">
        </el-table-column>
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
<!--        <el-table-column-->
<!--            prop="state"-->
<!--            label="备注"-->
<!--            width="150">-->
<!--        </el-table-column>-->
        <el-table-column
            label="红冲"
            width="100"
            v-show="getUserRole!== 'Gm'">
          <template slot-scope="scope">
            <el-button
                @click.native.prevent="redFlush(scope.row)"
                type="text"
                size="small">
              确定
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <download-excel
          :data="financeList.creditList"
          name="收款表.xls"
          style="display: inline-block;margin-left:10px"
      >
        <el-button type="primary">导出excel</el-button>
      </download-excel>
      <el-table
          :data="financeList.creditList"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="type"
            label= "单据类型"
            width="100">
        </el-table-column>
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
            width="150"
            :formatter="dateFormat"
        >
        </el-table-column>
<!--        <el-table-column-->
<!--            prop="state"-->
<!--            label="备注"-->
<!--            width="150">-->
<!--        </el-table-column>-->
        <el-table-column
            label="红冲"
            width="100"
            v-show="getUserRole!== 'Gm'">
          <template slot-scope="scope">
            <el-button
                @click.native.prevent="redFlush(scope.row)"
                type="text"
                size="small">
              确定
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <download-excel
          :data="financeList.salaryList"
          name="工资表.xls"
          style="display: inline-block;margin-left:10px"
      >
        <el-button type="primary">导出excel</el-button>
      </download-excel>
      <el-table
          :data="financeList.salaryList"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="type"
            label= "单据类型"
            width="100">
        </el-table-column>
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
        <el-table-column
            label="红冲"
            width="100"
            v-show="getUserRole!== 'Gm'">
          <template slot-scope="scope">
            <el-button
                @click.native.prevent="redFlush(scope.row)"
                type="text"
                size="small">
              确定
            </el-button>
          </template>
        </el-table-column>
      </el-table>

    </div>

    <div style="margin-top: 10px" v-if="showInventory">
      <download-excel
          :data="inventoryList.presentList"
          name="赠送表.xls"
          style="display: inline-block;margin-left:10px"
      >
        <el-button type="primary">导出excel</el-button>
      </download-excel>
      <el-table
          :data="inventoryList.presentList"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="type"
            label= "单据类型"
            width="100"
            :formatter= dateFormat>
        </el-table-column>
        <el-table-column
            prop="id"
            label="单据编号"
            width="100">
        </el-table-column>
        <el-table-column
            prop="商品编号"
            label="productId"
            width="200">
        </el-table-column>
        <el-table-column
            prop="amount"
            label="数量"
            width="100">
        </el-table-column>
        <el-table-column
            prop="operator"
            label="操作员"
            width="100">
        </el-table-column>
        <el-table-column
            prop="warehouseId"
            label="仓库号"
            width="150">
        </el-table-column>
        <el-table-column
            prop="remark"
            label="备注"
            width="150">
        </el-table-column>
        <el-table-column
            prop="createTime"
            label="创建时间"
            width="120"
        :formatter="dateFormat">
        </el-table-column>
        <el-table-column
            label="红冲"
            width="100"
            v-show="getUserRole!== 'Gm'">
          <template slot-scope="scope">
            <el-button
                @click.native.prevent="redFlush(scope.row)"
                type="text"
                size="small">
              确定
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <download-excel
          :data="inventoryList.overflowList"
          name="报溢表.xls"
          style="display: inline-block;margin-left:10px"
      >
        <el-button type="primary">导出excel</el-button>
      </download-excel>
      <el-table
          :data="inventoryList.overflowList"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="type"
            label= "单据类型"
            width="100"
            :formatter= dateFormat>
        </el-table-column>
        <el-table-column
            prop="id"
            label="单据编号"
            width="100">
        </el-table-column>
        <el-table-column
            prop="商品编号"
            label="productId"
            width="200">
        </el-table-column>
        <el-table-column
            prop="amount"
            label="数量"
            width="100">
        </el-table-column>
        <el-table-column
            prop="operator"
            label="操作员"
            width="100">
        </el-table-column>
        <el-table-column
            prop="warehouseId"
            label="仓库号"
            width="150">
        </el-table-column>
        <el-table-column
            prop="remark"
            label="备注"
            width="150">
        </el-table-column>
        <el-table-column
            prop="createTime"
            label="创建时间"
            width="120"
            :formatter="dateFormat">
        </el-table-column>
        <el-table-column
            label="红冲"
            width="100"
            v-show="getUserRole!== 'Gm'">
          <template slot-scope="scope">
            <el-button
                @click.native.prevent="redFlush(scope.row)"
                type="text"
                size="small">
              确定
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <download-excel
          :data="inventoryList.lossList"
          name="报损表.xls"
          style="display: inline-block;margin-left:10px"
      >
        <el-button type="primary">导出excel</el-button>
      </download-excel>
      <el-table
          :data="inventoryList.lossList"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="type"
            label= "单据类型"
            width="100"
            :formatter= dateFormat>
        </el-table-column>
        <el-table-column
            prop="id"
            label="单据编号"
            width="100">
        </el-table-column>
        <el-table-column
            prop="商品编号"
            label="productId"
            width="200">
        </el-table-column>
        <el-table-column
            prop="amount"
            label="数量"
            width="100">
        </el-table-column>
        <el-table-column
            prop="operator"
            label="操作员"
            width="100">
        </el-table-column>
        <el-table-column
            prop="warehouseId"
            label="仓库号"
            width="150">
        </el-table-column>
        <el-table-column
            prop="remark"
            label="备注"
            width="150">
        </el-table-column>
        <el-table-column
            prop="createTime"
            label="创建时间"
            width="120"
            :formatter="dateFormat">
        </el-table-column>
        <el-table-column
            label="红冲"
            width="100"
            v-show="getUserRole!== 'Gm'">
          <template slot-scope="scope">
            <el-button
                @click.native.prevent="redFlush(scope.row)"
                type="text"
                size="small">
              确定
            </el-button>
          </template>
        </el-table-column>
      </el-table>

    </div>

    <el-dialog
        title="查询经营历程表"
        :visible.sync="addList"
        width="40%"
        :before-close="handleClose">
      <el-form :label-width="'100px'" size="mini">
        <el-date-picker
            type="date"
            placeholder="选择开始日期"
            value-format="yyyy-MM-dd"
            v-model="financeBusinessProcessForm.beginTime"
            style="width: 40%"
        ></el-date-picker>
        <el-date-picker
            type="date"
            placeholder="选择结束日期"
            value-format="yyyy-MM-dd"
            v-model="financeBusinessProcessForm.endTime"
            style="width: 40% ;left:15px"
        ></el-date-picker>
        <el-select  v-model="financeBusinessProcessForm.type" filterable placeholder="请选择单据类型" style="width: 50%;top: 10px">
          <el-option  value="销售类单据" />
          <el-option  value="进货类单据" />
          <el-option  value="财务类单据" />
          <el-option  value="库存类单据" />
        </el-select>
        <el-select v-model="financeBusinessProcessForm.customer"
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
        <el-select v-model="financeBusinessProcessList.operator"
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
import {accountFindAll,financeTable,purchaseTable,saleTable,inventoryTable,flush} from "@/network/finance/Finance";
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import {getAllCustomer} from "@/network/purchase";
import {getAllCommodity} from "@/network/commodity";
import {staff_find_all} from "@/network/staff/staff";


export default {
  name: 'FinanceBusinessProcess',
  components: {
    Layout,
    Title
  },
  data() {
    return {
      date:'',
      commodityList: [],
      financeBusinessProcessForm:{
        beginTime:'',
        endTime:'',
        type:'',
        customer:'',
        operator:'',
        warehouse:'',

      },
      customerList:[],
      addList : false,
      accountList:[],
      financeBusinessProcessList:[],
      staffList:[],
      showSale:false,
      showPurchase: false,
      showFinance:false,
      showInventory:false,
      saleList:{
        saleBuyList:[],
        saleReturnList:[],
      },
      financeList:{
        salaryList:[],
        paymentList:[],
        creditList:[],
      },
      inventoryList:{
        overflowList:[],
        lossList:[],
        presentList:[],
      },
      purchaseList:{
        purchaseBuyList:[],
        purchaseReturnList:[],
      },

      redFlushForm:{
        saleSheetVO:{},
        saleReturnsSheetVO:{},
        purchaseSheetVO:{},
        purchaseReturnsSheetVO:{},
        creditSheetVO:{},
        paymentSheetVO:{},
        salarySheetVO:{},
        manageProcType:'',
        userVO:{
          name :sessionStorage.getItem("name"),
          role:sessionStorage.getItem("role"),
          password:'',
        }

      }
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
    getUserRole() {
      return sessionStorage.getItem("role");
    },
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
      if (this.financeBusinessProcessForm.endTime<=this.financeBusinessProcessForm.beginTime){
        alert("结束日期应大于开始日期")
      }else {
        if (this.financeBusinessProcessForm.type==="销售类单据"){
          this.showSale = true;
          this.showInventory = false;
          this.showPurchase = false;
          this.showFinance = false;
          saleTable(this.financeBusinessProcessForm).then(_res=>{
            this.saleList.saleBuyList = _res.result.filter(item => (item.type === 'XSCHD'))
            this.saleList.saleReturnList = _res.result.filter(item => (item.type === 'XSTHD'))
            this.$message("成功")
            this.addList = false;
          })
        }
        else if (this.financeBusinessProcessForm.type==="进货类单据"){
          this.showSale = false;
          this.showInventory = false;
          this.showPurchase = true;
          this.showFinance = false;
          purchaseTable(this.financeBusinessProcessForm).then(_res=>{
            this.purchaseList.purchaseBuyList = _res.result.filter(item=>item.type==="JHD")
            this.purchaseList.purchaseReturnList = _res.result.filter(item=>item.type==="JHTHD")
            this.$message("成功")
            this.addList = false;
          })
        }
        else if (this.financeBusinessProcessForm.type==="财务类单据"){
          this.showSale = false;
          this.showInventory = false;
          this.showPurchase = false;
          this.showFinance = true;
          financeTable(this.financeBusinessProcessForm).then(_res=>{
            this.financeList.salaryList = _res.result.filter(item=>item.type==="GZD")
            this.financeList.paymentList = _res.result.filter(item=>item.type==="FKD")
            this.financeList.creditList = _res.result.filter(item=>item.type==="SKD")
            this.$message("成功")
            this.addList = false;
          })
        }

        else if (this.financeBusinessProcessForm.type==="库存类单据"){
          this.showSale = false;
          this.showInventory = true;
          this.showPurchase = false;
          this.showFinance = false;
          inventoryTable(this.financeBusinessProcessForm).then(_res=>{
            this.inventoryList.lossList = _res.result.filter(item=>item.type==="BSD")
            this.inventoryList.overflowList = _res.result.filter(item=>item.type==="BYD")
            this.inventoryList.presentList = _res.result.filter(item=>item.type==="ZSD")
          })
        }
      }
    },

    redFlush(row){
      this.redFlushForm.manageProcType = row.type
      console.log(this.redFlushForm)
      if (row.type==="XSCHD"){
        this.redFlushForm.saleSheetVO = row
        flush(this.redFlushForm).then(_res=>{
          this.$message("成功")
        })
      }
      else if (row.type==="XSTHD"){
        this.redFlushForm.saleReturnsSheetVO = row
        flush(this.redFlushForm).then(_res=>{
          this.$message("成功")
        })
      }else if (row.type==="JHD"){
        this.redFlushForm.purchaseSheetVO = row
        flush(this.redFlushForm).then(_res=>{
          this.$message("成功")
        })
      }
      else if (row.type ==="JHTHD"){
        this.redFlushForm.purchaseReturnsSheetVO = row
        flush(this.redFlushForm).then(_res=>{
          this.$message("成功")
        })
      }
      else if (row.type==="GZD"){
        this.redFlushForm.salarySheetVO = row
        flush(this.redFlushForm).then(_res=>{
          this.$message("成功")
        })
      }
      else if (row.type ==="FKD"){
        this.redFlushForm.paymentSheetVO = row
        flush(this.redFlushForm).then(_res=>{
          this.$message("成功")
        })
      }
      else if (row.type==="SKD"){
        this.redFlushForm.creditSheetVO = row
        flush(this.redFlushForm).then(_res=>{
          this.$message("成功")
        })
      }
    },

    dateFormat:function (row){
      return row.createTime.substring(0,10)
    },






  }
}
</script>

<style>

</style>