<template>
  <Layout>
    <Title title="客户管理"></Title>
    <el-button type="primary" size="medium" @click="addList  = true">新增客户</el-button>
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
    </div>

    <el-dialog
        title="增加客户"
        :visible.sync="addList"
        width="40%"
        :before-close="handleClose">
      <div style="width: 90%; margin: 0 auto">
        <el-input type="text" v-model="customerForm.id" placeholder="请输入客户id" ></el-input>
        <el-input type="text" v-model="customerForm.name" placeholder="请输入客户姓名" ></el-input>
        <el-input type="text" v-model="customerForm.type" placeholder="请输入客户类型" ></el-input>
        <el-input type="text" v-model="customerForm.level" placeholder="请输入客户级别" ></el-input>
        <el-input type="text" v-model="customerForm.phone" placeholder="请输入客户电话" ></el-input>
        <el-input type="text" v-model="customerForm.address" placeholder="请输入客户地址" ></el-input>
        <el-input type="text" v-model="customerForm.zipcode" placeholder="请输入客户邮编" ></el-input>
        <el-input type="text" v-model="customerForm.email" placeholder="请输入客户邮箱" ></el-input>
        <el-input type="text" v-model="customerForm.lineOfCredit" placeholder="请输入客户应收额度（元）" ></el-input>
        <el-input type="text" v-model="customerForm.receivable" placeholder="请输入客户应收（元）" ></el-input>
        <el-input type="text" v-model="customerForm.payable" placeholder="请输入客户应付（元）" ></el-input>
        <el-input type="text" v-model="customerForm.operator" placeholder="请输入客户操作员" ></el-input>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click = "addCustomer()" >立即创建</el-button>
      </span>
    </el-dialog>

  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import {createCustomer, createPurchase, getAllCustomer, getCustomerById,deleteCustomer} from "../../network/purchase";
export default {
  name: 'CustomerView',
  components: {
    Layout,
    Title
  },
  data() {
    return {
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
          operator: '',

      },
      customerList: [],
      addList : false
    }
  },
  async mounted() {
    await getAllCustomer({ params : { type:'SUPPLIER' } }).then(_res => {
      this.customerList = this.customerList.concat(_res.result)
    })
    await getAllCustomer({ params : { type:'SELLER' } }).then(_res => {
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

    addCustomer() {
      createCustomer(this.customerForm ).then(_res => {
        if (_res.msg === 'Success') {
          this.$message.success('创建成功!')
          this.dialogVisible = false
          this.addList = false;
        } else {
          this.$message.error('Error!');
        }
      })

    },
    resetForm() {
      this.customerForm={
        id:'',
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
            operator: '',
      }
    },



    editInfo(id) {
      deleteCustomer({ params : { id:id } } ).then(_res => {
        console.log(id)
        if (_res.msg === 'Success') {
          // this.$message.success('创建成功!')
          // this.dialogVisible = false
        } else {
          // this.$message.error('Error!');
        }
      })
      alert("删除成功")
    }
  }
}
</script>

<style>

</style>