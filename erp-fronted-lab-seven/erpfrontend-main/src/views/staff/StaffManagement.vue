<template>
  <Layout>

    <Title title="员工管理"></Title>
    <el-button type="primary" size="medium" @click="addList  = true">新增员工</el-button>
    <el-button type="primary" size="medium" @click="amendList  = true">修改基本工资</el-button>
    <div style="margin-top: 10px">
      <el-table
          :data="staffList"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="id"
            label="id"
            width="40">
        </el-table-column>
        <el-table-column
            prop="name"
            label="姓名"
            width="120">
        </el-table-column>
        <el-table-column
            prop="gender"
            label="性别"
            width="60">
        </el-table-column>
        <el-table-column
            prop="birthday"
            label="出生日期"
            :formatter= dateFormat
            width="130"
            >
        </el-table-column>
        <el-table-column
            prop="phone"
            label="电话"
            width="130">
        </el-table-column>
        <el-table-column
            prop="role"
            label="职位"
            width="120">
        </el-table-column>
        <el-table-column
            prop="bankCard"
            label="银行卡"
            width="100">
        </el-table-column>
        <el-table-column
            prop="performanceSalary"
            label="提成(元)"
            width="120">
        </el-table-column>
      </el-table>
    </div>

    <el-dialog
        title="员工入职"
        :visible.sync="addList"
        width="40%"
        :before-close="handleClose">
      <div style="width: 90%; margin: 0 auto">
        <el-select  v-model="staffForm.role" filterable placeholder="请选择员工职位">
          <el-option  value="INVENTORY_MANAGER" />
          <el-option  value="SALE_STAFF" />
          <el-option  value="FINANCIAL_STAFF" />
          <el-option  value="SALE_MANAGER" />
          <el-option  value="HR" />
          <el-option  value="GM" />
        </el-select>
        <el-input type="text" v-model="staffForm.name" placeholder="请输入员工姓名" ></el-input>
        <el-select  v-model="staffForm.gender" filterable placeholder="请选择员工性别">
        <el-option  value="男" />
        <el-option  value="女" />
        </el-select>
        <el-input type="text" v-model="staffForm.birthday" placeholder="请输入员工生日" ></el-input>
        <el-input type="text" v-model="staffForm.phone" placeholder="请输入员工电话" ></el-input>
        <el-input type="text" v-model="staffForm.bankCard" placeholder="请输入员工银行卡号" ></el-input>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click = "addStaff()" >立即创建</el-button>
      </span>
    </el-dialog>

    <el-dialog
        title="基本工资修改"
        :visible.sync="amendList"
        width="40%"
        :before-close="handleClose">
      <div style="width: 90%; margin: 0 auto">
        <el-select  v-model="amendRole" filterable placeholder="请选择员工职位">
          <el-option  value="INVENTORY_MANAGER" />
          <el-option  value="SALE_STAFF" />
          <el-option  value="FINANCIAL_STAFF" />
          <el-option  value="SALE_MANAGER" />
          <el-option  value="HR" />
          <el-option  value="GM" />
        </el-select>
        <el-input type="number" v-model="amendSalary" placeholder="请输入新的基本工资(元)" ></el-input>

      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click = "amend()" >修改</el-button>
      </span>
    </el-dialog>

  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import {staff_add,staff_find_all,staff_salary_amend} from "@/network/staff/staff";
export default {
  name: 'StaffManagement',
  components: {
    Layout,
    Title
  },
  data() {
    return {
      staffForm:{
        name:'',
        gender:'',
        birthday:'',
        phone:'',
        role:'',
        bankCard: '',
      },
      staffList: [],
      addList : false,
      amendList:false,
      amendRole:'',
      amendSalary:'',

    }
  },
  async mounted() {
    await staff_find_all({}).then(_res => {
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

    addStaff() {
      staff_add(this.staffForm).then(_res => {
        if (_res.msg === 'Success') {
          this.$message.success('创建成功!')
          this.addList = false;
        } else {
          this.$message.error('Error!');
        }
      })

    },
    resetForm() {

    },
    amend(){
      if (this.amendSalary<0){
        alert("工资不能为负")
      }else {
         staff_salary_amend({params:{role:this.amendRole,targetSalary:this.amendSalary}}).then(_res=>{
           this.amendSalary = '';
           this.amendRole = '';
           this.$message('修改成功');
           this.$router.go(0)
         })
      }
    },

    dateFormat:function (row){
      return row.birthday.substring(0,10)
    },




  }
}
</script>

<style>

</style>