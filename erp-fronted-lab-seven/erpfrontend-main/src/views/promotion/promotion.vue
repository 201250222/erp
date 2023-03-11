<template>
  <Layout>

    <Title title="制定促销策略"></Title>
    <el-button type="primary" size="medium" @click="addDiscount  = true">添加折扣策略</el-button>
    <el-button type="primary" size="medium" @click="addPresent = true">添加赠送策略</el-button>
    <el-button type="primary" size="medium" @click="addVoucher  = true">添加优惠券赠送策略</el-button>
    <el-button type="primary" size="medium" @click="addUnity  = true">添加商品组合优惠</el-button>

    <div style="margin-top: 10px">
      <el-table
          :data="promoteList"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="id"
            label="编号"
            width="100">
        </el-table-column>
        <el-table-column
            prop="operator"
            label="操作员"
            width="100">
        </el-table-column>
        <el-table-column
            prop="productId1"
            label="商品1"
            width="100">
        </el-table-column>
        <el-table-column
            prop="amount1"
            label="触发数量"
            width="100">
        </el-table-column>
        <el-table-column
            prop="productId2"
            label="商品2"
            width="100">
        </el-table-column>
        <el-table-column
            prop="amount2"
            label="触发数量"
            width="100">
        </el-table-column>
        <el-table-column
            prop="productId3"
            label="商品3"
            width="100">
        </el-table-column>
        <el-table-column
            prop="amount3"
            label="触发数量"
            width="100">
        </el-table-column>
        <el-table-column
            prop="totalPrice"
            label="触发价格(元)"
            width="120">
        </el-table-column>
        <el-table-column
            prop="customerLevel"
            label="客户等级"
            width="120">
        </el-table-column>
        <el-table-column
            prop="presentId"
            label="赠品id"
            width="120">
        </el-table-column>
        <el-table-column
            prop="presentNumber"
            label="赠品数目"
            width="120">
        </el-table-column>
        <el-table-column
            prop="discount"
            label="折扣"
            width="100">
        </el-table-column>
        <el-table-column
            prop="voucherPrice"
            label="优惠券总额"
            width="100">
        </el-table-column>
        <el-table-column
            prop="beginTime"
            label="起始时间"
            width="120"
        :formatter="dateFormat1">
        </el-table-column>
        <el-table-column
            prop="endTime"
            label="结束时间"
            width="100"
        :formatter="dateFormat2">
        </el-table-column>
      </el-table>
    </div>



    <el-dialog
        title="添加折扣策略"
        :visible.sync="addDiscount"
        width="40%"
        :before-close="handleClose">
      <el-form :label-width="'100px'" size="mini">
        <el-input type="number" v-model="promoteForm.totalPrice" placeholder="请输入触发总价" ></el-input>
        <el-select  v-model="promoteForm.customerLevel" filterable placeholder="请选择客户等级" style="width: 50%;margin-top: 5px">
          <el-option  value="1" />
          <el-option  value="2" />
          <el-option  value="3" />
          <el-option  value="4" />
          <el-option  value="5" />
        </el-select>
        <el-select  v-model="promoteForm.discount" filterable placeholder="请选择折扣" style="width: 50%;margin-top: 5px">
          <el-option  value="0.1" />
          <el-option  value="0.2" />
          <el-option  value="0.3" />
          <el-option  value="0.4" />
          <el-option  value="0.5" />
          <el-option  value="0.6" />
          <el-option  value="0.7" />
          <el-option  value="0.8" />
          <el-option  value="0.9" />
        </el-select>
        <el-date-picker
            type="date"
            placeholder="选择开始日期"
            value-format="yyyy-MM-dd"
            v-model="promoteForm.beginTime"
            style="width: 40%"
        ></el-date-picker>
        <el-date-picker
            type="date"
            placeholder="选择结束日期"
            value-format="yyyy-MM-dd"
            v-model="promoteForm.endTime"
            style="width: 40% ;left:15px"
        ></el-date-picker>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" style="top: 10px" @click="createDiscount()">确认</el-button>
      </div>
    </el-dialog>

    <el-dialog
        title="添加赠送策略"
        :visible.sync="addPresent"
        width="40%"
        :before-close="handleClose">
      <el-form :label-width="'100px'" size="mini">
        <el-input type="number" v-model="promoteForm.totalPrice" placeholder="请输入触发价格" ></el-input>
        <el-select  v-model="promoteForm.customerLevel" filterable placeholder="请选择客户等级" style="width: 50%;margin-top: 5px">
          <el-option  value="1" />
          <el-option  value="2" />
          <el-option  value="3" />
          <el-option  value="4" />
          <el-option  value="5" />
        </el-select>
        <el-input type="number" v-model="promoteForm.presentId" placeholder="请输入赠品编号" ></el-input>
        <el-input type="number" v-model="promoteForm.presentNumber" placeholder="请输入赠品数量" ></el-input>
        <el-date-picker
            type="date"
            placeholder="选择开始日期"
            value-format="yyyy-MM-dd"
            v-model="promoteForm.beginTime"
            style="width: 40%"
        ></el-date-picker>
        <el-date-picker
            type="date"
            placeholder="选择结束日期"
            value-format="yyyy-MM-dd"
            v-model="promoteForm.endTime"
            style="width: 40% ;left:15px"
        ></el-date-picker>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" style="top: 10px" @click="createPresent()">确认</el-button>
      </div>
    </el-dialog>

    <el-dialog
        title="添加代金券赠送策略"
        :visible.sync="addVoucher"
        width="40%"
        :before-close="handleClose">
      <el-form :label-width="'100px'" size="mini">
        <el-input type="number" v-model="promoteForm.totalPrice" placeholder="请输入触发金额" ></el-input>
        <el-select  v-model="promoteForm.customerLevel" filterable placeholder="请选择客户等级" style="width: 50%;margin-top: 5px">
          <el-option  value="1" />
          <el-option  value="2" />
          <el-option  value="3" />
          <el-option  value="4" />
          <el-option  value="5" />
        </el-select>
        <el-input type="number" v-model="promoteForm.voucherPrice" placeholder="请输入代金券总额" ></el-input>
        <el-date-picker
            type="date"
            placeholder="选择开始日期"
            value-format="yyyy-MM-dd"
            v-model="promoteForm.beginTime"
            style="width: 40%"
        ></el-date-picker>
        <el-date-picker
            type="date"
            placeholder="选择结束日期"
            value-format="yyyy-MM-dd"
            v-model="promoteForm.endTime"
            style="width: 40% ;left:15px"
        ></el-date-picker>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" style="top: 10px" @click="createVoucher()">确认</el-button>
      </div>
    </el-dialog>

    <el-dialog
        title="添加商品组合优惠"
        :visible.sync="addUnity"
        width="40%"
        :before-close="handleClose">
      <el-form :label-width="'100px'" size="mini">
        <el-input type="number" v-model="promoteForm.productId1" placeholder="请输入商品1编号" ></el-input>
        <el-input type="number" v-model="promoteForm.amount1" placeholder="请输入商品1触发数量" ></el-input>
        <el-input type="number" v-model="promoteForm.productId2" placeholder="请输入商品2编号" ></el-input>
        <el-input type="number" v-model="promoteForm.amount2" placeholder="请输入商品2触发数量" ></el-input>
        <el-input type="number" v-model="promoteForm.productId3" placeholder="请输入商品3编号" ></el-input>
        <el-input type="number" v-model="promoteForm.amount3" placeholder="请输入商品3触发数量" ></el-input>
        <el-select  v-model="promoteForm.customerLevel" filterable placeholder="请选择客户等级" style="width: 50%;margin-top: 5px">
          <el-option  value="1" />
          <el-option  value="2" />
          <el-option  value="3" />
          <el-option  value="4" />
          <el-option  value="5" />
        </el-select>
        <el-select  v-model="promoteForm.discount" filterable placeholder="请选择折扣" style="width: 50%;margin-top: 5px">
          <el-option  value="0.1" />
          <el-option  value="0.2" />
          <el-option  value="0.3" />
          <el-option  value="0.4" />
          <el-option  value="0.5" />
          <el-option  value="0.6" />
          <el-option  value="0.7" />
          <el-option  value="0.8" />
          <el-option  value="0.9" />
        </el-select>
        <el-date-picker
            type="date"
            placeholder="选择开始日期"
            value-format="yyyy-MM-dd"
            v-model="promoteForm.beginTime"
            style="width: 40%"
        ></el-date-picker>
        <el-date-picker
            type="date"
            placeholder="选择结束日期"
            value-format="yyyy-MM-dd"
            v-model="promoteForm.endTime"
            style="width: 40% ;left:15px"
        ></el-date-picker>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" style="top: 10px" @click="createUnity()">确认</el-button>
      </div>
    </el-dialog>


  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import {createPromote,gerPromote} from "@/network/sale/index"



export default {
  name: 'promotion',
  components: {
    Layout,
    Title
  },
  data() {
    return {
      date:'',
      commodityList: [],
      addDiscount:false,
      addPresent:false,
      addVoucher: false,
      addUnity:false,
      promoteForm:{
        id:"",
        operator: sessionStorage.getItem("name"),
        beginTime:'',
        endTime:'',
        promoteTrigger:'',
        customerLevel:'',
        productId1:'',
        amount1:'',
        productId2:"",
        amount2:'',
        productId3:'',
        amount3:'',
        totalPrice:'',
        promoteType:'',
        presentId:'',
        presentNumber:'',
        discount:'',
        voucherPrice:'',
    },

      promoteList:[],
    }
  },
  async mounted() {
    gerPromote().then(_res=>{
      console.log(_res.result)
      this.promoteList = _res.result
    })

  },
  methods: {
    filterTag(value, row) {
      return row.type === value
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
            this.clearForm()
          })
          .catch(_ => {});
    },
      createDiscount(){
      if (this.promoteForm.endTime<=this.promoteForm.beginTime){
        alert("结束日期应大于开始日期")
      }else {
        this.promoteForm.promoteTrigger =1
        this.promoteForm.promoteType = 0
        createPromote(this.promoteForm).then(_res=>{
          console.log(this.promoteForm)
          this.$message("成功")
          this.addDiscount = false;
          this.clearForm()
          this.$router.go(0)
        })
          }
      },
    createPresent(){
      if (this.promoteForm.endTime<=this.promoteForm.beginTime){
        alert("结束日期应大于开始日期")
      }else {
        this.promoteForm.promoteTrigger=1
        this.promoteForm.promoteType= 1
        createPromote(this.promoteForm).then(_res=>{
          this.$message("成功")
          this.addPresent = false
          this.clearForm()
          this.$router.go(0)
        })
      }
    },
    createVoucher(){
      if (this.promoteForm.endTime<=this.promoteForm.beginTime){
        alert("结束日期应大于开始日期")
      }else {
        this.promoteForm.promoteTrigger=1
        this.promoteForm.promoteType = 2
        createPromote(this.promoteForm).then(_res=>{
          this.$message("成功")
          this.addVoucher = false
          this.clearForm()
          this.$router.go(0)
        })
      }
    },
    createUnity(){
      if (this.promoteForm.endTime<=this.promoteForm.beginTime){
        alert("结束日期应大于开始日期")
      }else {
        this.promoteForm.promoteTrigger=0
        this.promoteForm.promoteType = 0
        createPromote(this.promoteForm).then(_res=>{
          this.$message("成功")
          this.addUnity = false
          this.clearForm()
          this.$router.go(0)
        })
      }
    },




    clearForm(){
      this.promoteForm={
        id:"",
        operator:sessionStorage.getItem("name"),
        beginTime:'',
        endTime:'',
        promoteTrigger:'',
        customerLevel:'',
        productId1:'',
        amount1:'',
        productId2:"",
        amount2:'',
        productId3:'',
        amount3:'',
        totalPrice:'',
        promoteType:'',
        presentId:'',
        presentNumber:'',
        discount:'',
        voucherPrice:'',
      }
    },
    dateFormat1:function (row){
      return row.beginTime.substring(0,10)
    },
    dateFormat2:function (row){
      return row.endTime.substring(0,10)
    },

  }
}
</script>

<style>

</style>