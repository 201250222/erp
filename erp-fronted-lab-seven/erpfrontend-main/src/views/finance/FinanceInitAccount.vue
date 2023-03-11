<template>
  <Layout>

    <Title title="期初建账"></Title>
    <el-button type="primary" size="medium" @click="addClass  = true">添加商品分类信息</el-button>
    <el-button type="primary" size="medium" @click="addCommodity = true">添加商品信息</el-button>
    <el-button type="primary" size="medium" @click="addCustomer  = true">添加客户</el-button>
    <el-button type="primary" size="medium" @click="addAccount  = true">添加账户</el-button>
    <el-button type="primary" size="medium" @click="init()">建账</el-button>

    <div style="margin-top: 10px">
      <el-table
          :data="classList"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="id"
            label="编号"
            width="60">
        </el-table-column>
        <el-table-column
            prop="name"
            label="分类名"
            width="120">
        </el-table-column>
        <el-table-column
            prop="parentId"
            label="父类编号"
            width="100">
        </el-table-column>
        <el-table-column
            prop="itemCount"
            label="商品数量"
            width="120">
        </el-table-column>
        <el-table-column
            prop="itemIndex"
            label="下一件商品"
            width="100">
        </el-table-column>
      </el-table>
    </div>

    <div style="margin-top: 10px">
      <el-table
          :data="productList"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="id"
            label="编号"
            width="60">
        </el-table-column>
        <el-table-column
            prop="name"
            label="商品名"
            width="120">
        </el-table-column>
        <el-table-column
            prop="categoryId"
            label="分类id"
            width="100">
        </el-table-column>
        <el-table-column
            prop="type"
            label="商品型号"
            width="120">
        </el-table-column>
        <el-table-column
            prop="quantity"
            label="商品数量"
            width="100">
        </el-table-column>
        <el-table-column
            prop="purchasePrice"
            label="进价"
            width="100">
        </el-table-column>
        <el-table-column
            prop="retailPrice"
            label="零售价"
            width="100">
        </el-table-column>
        <el-table-column
            prop="recentPp"
            label="最近进价"
            width="100">
        </el-table-column><el-table-column
          prop="recentRp"
          label="最近零售价"
          width="100">
      </el-table-column>

        <el-table
            :data="customerList"
            stripe
            style="width: 100%"
            :header-cell-style="{'text-align':'center'}"
            :cell-style="{'text-align':'center'}">
          <el-table-column
              prop="id"
              label="id"
              width="60">
          </el-table-column>
          <el-table-column
              prop="name"
              label="姓名"
              width="70">
          </el-table-column>
          <el-table-column
              prop="type"
              label="类型"
              width="100"
              :filters="[{ text: '供应商', value: '供应商' }, { text: '销售商', value: '销售商' }]"
              :filter-method="filterTag"
              filter-placement="bottom-end">
            <template slot-scope="scope">
              <el-tag
                  :type="scope.row.type === '供应商' ? 'primary' : 'success'"
                  disable-transitions>{{scope.row.type}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
              prop="level"
              label="级别"
              width="50">
          </el-table-column>
          <el-table-column
              prop="phone"
              label="电话"
              width="150">
          </el-table-column>
          <el-table-column
              prop="address"
              label="地址"
              width="150">
          </el-table-column>
          <el-table-column
              prop="zipcode"
              label="邮编"
              width="100">
          </el-table-column>
          <el-table-column
              prop="email"
              label="邮箱"
              width="200">
          </el-table-column>
          <el-table-column
              prop="lineOfCredit"
              label="应收额度(元)"
              width="120">
          </el-table-column>
          <el-table-column
              prop="receivable"
              label="应收(元)"
              width="120">
          </el-table-column>
          <el-table-column
              prop="payable"
              label="应付(元)"
              width="120">
          </el-table-column>
          <el-table-column
              prop="operator"
              label="操作员"
              width="120">
          </el-table-column>
          <el-table-column
              label="操作">
            <template slot-scope="scope">
              <el-button
                  @click.native.prevent="editInfo(scope.row.id)"
                  type="text"
                  size="small">
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>

      </el-table>
    </div>

    <div style="margin-top: 10px">
      <el-table
          :data="customerList"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="id"
            label="id"
            width="60">
        </el-table-column>
        <el-table-column
            prop="name"
            label="姓名"
            width="70">
        </el-table-column>
        <el-table-column
            prop="type"
            label="类型"
            width="100"
            :filters="[{ text: '供应商', value: '供应商' }, { text: '销售商', value: '销售商' }]"
            :filter-method="filterTag"
            filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag
                :type="scope.row.type === '供应商' ? 'primary' : 'success'"
                disable-transitions>{{scope.row.type}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column
            prop="level"
            label="级别"
            width="50">
        </el-table-column>
        <el-table-column
            prop="phone"
            label="电话"
            width="150">
        </el-table-column>
        <el-table-column
            prop="address"
            label="地址"
            width="150">
        </el-table-column>
        <el-table-column
            prop="zipcode"
            label="邮编"
            width="100">
        </el-table-column>
        <el-table-column
            prop="email"
            label="邮箱"
            width="200">
        </el-table-column>
        <el-table-column
            prop="lineOfCredit"
            label="应收额度(元)"
            width="120">
        </el-table-column>
        <el-table-column
            prop="receivable"
            label="应收(元)"
            width="120">
        </el-table-column>
        <el-table-column
            prop="payable"
            label="应付(元)"
            width="120">
        </el-table-column>
        <el-table-column
            prop="operator"
            label="操作员"
            width="120">
        </el-table-column>
      </el-table>
    </div>

    <div style="margin-top: 10px">
      <el-table
          :data="accountList"
          stripe
          style="width: 75%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="name"
            label="名称"
            width="140">
        </el-table-column>
        <el-table-column
            prop="cardNum"
            label="银行卡号"
            width="140">
        </el-table-column>
        <el-table-column
            prop="balance"
            label="余额(元)"
            width="140">
        </el-table-column>
      </el-table>
    </div>

    <el-dialog
        title="添加商品分类信息"
        :visible.sync="addClass"
        width="40%"
        :before-close="handleClose">
      <el-form :label-width="'100px'" size="mini">
        <el-input type="text" v-model="classForm.name" placeholder="请输入分类名" ></el-input>
        <el-input type="text" v-model="classForm.parentId" placeholder="请输入父分类id" style="margin-top: 5px"></el-input>
        <el-select  v-model="classForm.isLeaf" filterable placeholder="请选择是否为叶节点" style="width: 50%;margin-top: 5px">
          <el-option  value="true" />
          <el-option  value="false" />
        </el-select>
        <el-input type="number" v-model="classForm.itemCount" placeholder="请输入商品数量" style="margin-top: 5px"></el-input>
        <el-input type="text" v-model="classForm.itemIndex" placeholder="请输入下一个商品目录" style="margin-top: 5px" ></el-input>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" style="top: 10px" @click="createClass()">确认</el-button>
      </div>
    </el-dialog>

    <el-dialog
        title="添加商品"
        :visible.sync="addCommodity"
        width="40%"
        :before-close="handleClose">
      <el-form :label-width="'100px'" size="mini">
        <el-input type="text" v-model="productForm.name" placeholder="请输入商品名" ></el-input>
        <el-input type="text" v-model="productForm.categoryId" placeholder="请输入分类id" style="margin-top: 5px"></el-input>
        <el-input type="text" v-model="productForm.type" placeholder="请输入商品型号" style="margin-top: 5px"></el-input>
        <el-input type="number" v-model="productForm.quantity" placeholder="请输入商品数量" style="margin-top: 5px" ></el-input>
        <el-input type="number" v-model="productForm.purchasePrice" placeholder="请输入商品进价" style="margin-top: 5px" ></el-input>
        <el-input type="number" v-model="productForm.retailPrice" placeholder="请输入商品零售价" style="margin-top: 5px" ></el-input>
        <el-input type="number" v-model="productForm.recentPp" placeholder="请输入商品最近进价" style="margin-top: 5px" ></el-input>
        <el-input type="number" v-model="productForm.recentRp" placeholder="请输入商品最近零售价" style="margin-top: 5px" ></el-input>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" style="top: 10px" @click="createProduct()">确认</el-button>
      </div>
    </el-dialog>

    <el-dialog
        title="增加客户"
        :visible.sync="addCustomer"
        width="40%"
        :before-close="handleClose">
      <div style="width: 90%; margin: 0 auto">
        <el-input type="text" v-model="customerForm.name" placeholder="请输入客户姓名" ></el-input>
        <el-select  v-model="customerForm.type" filterable placeholder="请选择客户类型">
          <el-option  value="供应商" />
          <el-option  value="销售商" />
        </el-select>
        <el-select  v-model="customerForm.level" filterable placeholder="请选择客户级别">
          <el-option  value="1" />
          <el-option  value="2" />
          <el-option  value="3" />
          <el-option  value="4" />
          <el-option  value="5" />
        </el-select>
        <el-input type="text" v-model="customerForm.phone" placeholder="请输入客户电话" ></el-input>
        <el-input type="text" v-model="customerForm.address" placeholder="请输入客户地址" ></el-input>
        <el-input type="text" v-model="customerForm.zipcode" placeholder="请输入客户邮编" ></el-input>
        <el-input type="text" v-model="customerForm.email" placeholder="请输入客户邮箱" ></el-input>
        <el-input type="number" v-model="customerForm.lineOfCredit" placeholder="请输入客户应收额度（元）" ></el-input>
        <el-input type="number" v-model="customerForm.receivable" placeholder="请输入客户应收（元）" ></el-input>
        <el-input type="number" v-model="customerForm.payable" placeholder="请输入客户应付（元）" ></el-input>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click = "createCustomer()" >立即创建</el-button>
      </span>
    </el-dialog>

    <el-dialog
        title="增加客户"
        :visible.sync="addAccount"
        width="40%"
        :before-close="handleClose">
      <div style="width: 90%; margin: 0 auto">
        <el-input type="text" v-model="accountForm.name" placeholder="请输入账户名称" ></el-input>
        <el-input type="number" v-model="accountForm.cardNum" placeholder="请输入账户银行卡号" ></el-input>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click = "createAccount()" >立即创建</el-button>
      </span>
    </el-dialog>

  </Layout>
</template>

<script>
import {initGetClass,initGetAccount,initCreateAccount,initCreateClass,initCreateProduct,initGetCustomer,initCreateCustomer,initGetProduct,initEnd} from "@/network/finance/Finance";
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";



export default {
  name: 'FinanceInitAccount',
  components: {
    Layout,
    Title
  },
  data() {
    return {
      date:'',
      commodityList: [],
      classForm:{
        id:'',
        name:"",
        parentId:'',
        isLeaf:"",
        itemCount:'',
        itemIndex:"",
      },
      productForm:{
        id :'',
        name:'',
        categoryId:'',
        type:'',
        quantity:'',
        purchasePrice:'',
        retailPrice:'',
        recentPp:'',
        recentRp:'',
      },
      customerForm:{
        id: '',
        type: '',
        level: '',
        name: '',
        phone: '',
        address: '',
        zipcode: '',
        email: '',
        lineOfCredit: '',
        receivable: '',
        payable: '',
        operator: sessionStorage.getItem("name"),

      },
      accountForm: {
        name: '',
        cardNum: '',
      },

      addList : false,
      financeBusinessProcessList:[],
      staffList:[],
      addClass:false,
      addCommodity:false,
      addAccount :false,
      addCustomer:false,

      classList:[],
      productList:[],
      customerList:[],
      accountList:[],

    }
  },
  async mounted() {
    initGetClass().then(_res=>{
      this.classList = this.classList.concat(_res.result)
    })
    initGetProduct().then(_res=>{
      this.productList = this.productList.concat(_res.result)
      console.log(_res.result)
    })
    initGetAccount().then(_res=>{
      this.accountList = this.accountList.concat(_res.result)
    })
    initGetCustomer().then(_res=>{
      this.customerList = this.customerList.concat(_res.result)
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
    createClass(){
      initCreateClass(this.classForm).then(_res=>{
        this.$message("成功")
        this.addClass = false
      })
    },
    createProduct(){
      initCreateProduct(this.productForm).then(_res=>{
        this.$message("成功")
        this.addCommodity  = false;
      })
    },
    createCustomer(){
      initCreateCustomer(this.customerForm).then(_res=>{
        this.$message("成功")
        this.addCustomer = false;
      })
    },
    createAccount(){
      initCreateAccount(this.accountForm).then(_res=>{
        this.$message("成功")
        this.addAccount = false;
      })
    },
    init(){
      initEnd().then(_res=>{
        this.$message("成功")
      })
    }



  }
}
</script>

<style>

</style>