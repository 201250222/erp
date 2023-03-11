<template>
  <Layout>
    <Title title="销售退货管理"></Title>
    <el-button type="primary" size="medium" @click="dialogVisible = true">制定销售退货单</el-button>
    <el-button type="primary" size=" medium" @click=" shenpi = true">审批单据</el-button>
    <div class="body">
      <el-tabs v-model="activeName" :stretch="true">
        <el-tab-pane label="待一级审批" name="PENDING_LEVEL_1">
          <div v-if="pendingLevel1List.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>
          <div v-else>
            <sale-return-list :list="pendingLevel1List" :type="1" @refresh="getSale()"/>
          </div>
        </el-tab-pane>
        <el-tab-pane label="待二级审批" name="PENDING_LEVEL_2">
          <div v-if="pendingLevel2List.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>
          <div v-else>
            <sale-return-list :list="pendingLevel2List" :type="2" @refresh="getSale()"/>
          </div>
        </el-tab-pane>
        <el-tab-pane label="审批完成" name="SUCCESS">
          <div v-if="successList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>
          <div v-else>
            <sale-return-list :list="successList" :type="3"/>
          </div>
        </el-tab-pane>
        <el-tab-pane label="审批失败" name="FAILURE">
          <div v-if="failureList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>
          <div v-else>
            <sale-return-list :list="failureList" :type="4"/>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
    <el-dialog
      title="创建销售退货单"
      :visible.sync="dialogVisible"
      width="40%"
      :before-close="handleClose">
      <div>
        <el-input type="text" v-model="returnForm.id_1" placeholder="请输入退货单id" ></el-input>
        <el-input type="text" v-model="returnForm.id_2" placeholder="请输入商品id" ></el-input>
        <el-input type="text" v-model="returnForm.amount" placeholder="请输入数量" ></el-input>
        <el-input type="text" v-model="returnForm.total" placeholder="请输入总价" ></el-input>
        <el-input type="text" v-model="returnForm.remark" placeholder="请输入备注" ></el-input>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm('saleForm')">立即创建</el-button>
      </span>
    </el-dialog>
    <el-dialog
        title="审批单据"
        :visible.sync="shenpi"
        width="40%"
        :before-close="handleClose">
      <div>
        <el-input type="text" v-model="approvalId" placeholder="请输入单据id" ></el-input>
        <el-input type="text" v-model="approvalId" placeholder="请输入目标状态" ></el-input>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm2()">立即创建</el-button>
      </span>
    </el-dialog>



  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import SaleReturnList from './components/SaleReturnList'
import {getAllSale, createSale, createSaleReturn,getAllReturnSale,findReturnByState,approval} from '../../network/sale'
import {createCustomer, getAllCustomer, getCustomerById} from '../../network/purchase'
import { getAllCommodity } from '../../network/commodity'
export default {
  name: 'SaleReturnView',
  components: {
    Layout,
    Title,
    SaleReturnList,
  },
  data() {
    return {
      activeName: 'PENDING_LEVEL_1',
      saleList: [],
      pendingLevel1List: [],
      pendingLevel2List: [],
      successList: [],
      failureList: [],
      dialogVisible: false,
      shenpi:false,
      returnForm: {
        id_1:'',
        id_2:'',
        total:'',
        amount:'',
        remark: '',
      },
      approvalId:'',
      approvalState:'',
    }
  },
  async mounted() {
    // await getAllReturnSale().then(_res => {
    //   this.saleList= _res.result;
    //     this.pendingLevel1List = this.saleList.filter(item => item.state === '待一级审批')
    //     this.pendingLevel2List = this.saleList.filter(item => item.state === '待二级审批')
    //     this.successList = this.saleList.filter(item => item.state === '审批完成')
    //     this.failureList = this.saleList.filter(item => item.state === '审批失败')
    // })
    await findReturnByState({params:{state:'待一级审批' }}).then(_res => {
      this.saleList= _res.result;
          this.pendingLevel1List = this.saleList.filter(item => item.state === '待一级审批')
          this.pendingLevel2List = this.saleList.filter(item => item.state === '待二级审批')
          this.successList = this.saleList.filter(item => item.state === '审批完成')
          this.failureList = this.saleList.filter(item => item.state === '审批失败')
    })
    // await findReturnByState({params:{state:'待二级审批' }}).then(_res => {
    //   this.pendingLevel2List = _res.result;
    // })
    // await findReturnByState({params:{state:'审批完成' }}).then(_res => {
    //   this.successList = _res.result;
    // })
    // await findReturnByState({params:{state:'审批失败' }}).then(_res => {
    //   this.failureList = _res.result;
    // })


  },
  methods: {
    getSale() {
      // getAllReturnSale({}).then(_res => {
      //   this.saleList = _res.result
      //   this.pendingLevel1List = this.saleList.filter(item => item.state === '待一级审批')
      //   this.pendingLevel2List = this.saleList.filter(item => item.state === '待二级审批')
      //   this.successList = this.saleList.filter(item => item.state === '审批完成')
      //   this.failureList = this.saleList.filter(item => item.state === '审批失败')
      // })
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          this.resetForm()
          done();
        })
        .catch(_ => {});
    },
    submitForm() {
      createSaleReturn(this.returnForm).then(_res => {
        if (_res.msg === 'Success') {
          this.$message.success('创建成功!')
          this.dialogVisible = false
        } else {
          this.$message.error('Error!');
        }
      })

    },
    submitForm2() {
      approval({params:{saleReturnsSheetId:this.approvalId,state:this.approvalState}}).then(_res => {
        if (_res.msg === 'Success') {
          this.$message.success('创建成功!')
          this.shenpi = false
        }
      })

    },
    resetForm() {
      this.saleForm = {
        saleSheetContent: [
          {
            pid: '',
            quantity: '',
            unitPrice: '',
            remark: ''
          }
        ]
      }
    },

  }
}
</script>

<style lang="scss" scoped>
.body {
  margin: 0 auto;
  margin-top: 10px;
  height: 80vh;
  overflow-y: auto;
  width: 100%;
  background: rgba($color: #fff, $alpha: 0.5);
}
</style>