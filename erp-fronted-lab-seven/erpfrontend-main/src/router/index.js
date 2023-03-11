import Vue from "vue";
import VueRouter from "vue-router";
import { ROLE, PATH } from "../common/const";
const Error = () => import("../components/content/Error");
const Login = () => import("../views/auth/Login");
const Home = () => import("../views/Home");
const CommodityManagement = () =>
  import("../views/commodity/CommodityManagement");
const CommodityClassification = () =>
  import("../views/commodity/CommodityClassification");
const InventoryManagement = () => import("../views/inventory/InventoryManagement");
const InventoryCheck = () => import("../views/inventory/InventoryCheck");
const InventoryOperation = () =>
  import("../views/inventory/InventoryOperation");
const InventoryIn = () => import("../views/inventory/InventoryIn");
const InventoryOut = () => import("../views/inventory/InventoryOut");
const InventoryLoss = () => import("../views/inventory/InventoryLoss");
const InventoryOverflow = () => import("../views/inventory/InventoryOverflow");
const InventoryPresent = () => import("../views/inventory/InventoryPresent");
const InventoryView = () => import("../views/inventory/InventoryView");
const InventoryWarning = () => import("../views/inventory/InventoryWarning");
const PurchaseView = () => import("../views/purchase/PurchaseView");
const PurchaseReturnView = () => import("../views/purchase/PurchaseReturnView");
const SaleView = () => import("../views/sale/SaleView");
const SaleReturnView = () => import("../views/sale/SaleReturnView");
const CustomerView = () => import ("../views/purchase/CustomerView");
const Approval = () => import("../views/approval/Approval");
const StaffManagement = () => import("../views/staff/StaffManagement");
const FinanceSalary = () =>import("../views/finance/FinanceSalary");
const FinanceAccount = ()=> import("../views/finance/FinanceAccount");
const ApprovalSalary = ()=>import("../views/approval/ApprovalSalary");
const FinanceReceipt = () =>import("../views/finance/FinanceCredit");
const FinancePayment = ()=>import("../views/finance/FinancePayment");
const ApprovalPayment = ()=>import("../views/approval/ApprovalPayment");
const ApprovalCredit = ()=>import("../views/approval/ApprovalCredit");
const FinanceSaleDetail = ()=>import("../views/finance/FinanceSaleDetail");
const FinanceBusinessProcess=()=>import("../views/finance/FinanceBusinessProcess");
const FinanceBusinessCircumstance=()=>import("../views/finance/FinanceBusinessCircumstance");
const FinanceYearBonus = ()=>import("../views/finance/FinanceYearBonus");
const FinanceInitAccount = ()=>import("../views/finance/FinanceInitAccount");
const Promotion = ()=>import("../views/promotion/promotion")
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    component: Home
  },
  {
    path: "/error",
    component: Error
  },
  {
    path: "/login",
    component: Login
  },
  {
    path: "/register",
    component: () => import("@/views/auth/register.vue")
  },
  // 商品管理
  {
    path: PATH.COMMODITY_CLASSIFICATION.path,
    component: CommodityClassification,
    meta: { requiresAuth: PATH.COMMODITY_CLASSIFICATION.requiresAuth }
  },
  {
    path: PATH.COMMODITY_MANAGEMENT.path,
    component: CommodityManagement,
    meta: { requiresAuth: PATH.COMMODITY_MANAGEMENT.requiresAuth }
  },
  // 库存管理
  {
    path: PATH.INVENTORY_MANAGEMENT.path,
    component: InventoryManagement,
    meta: { requiresAuth: PATH.INVENTORY_MANAGEMENT.requiresAuth }
  },
  {
    path: PATH.INVENTORY_CHECK.path,
    component: InventoryCheck,
    meta: { requiresAuth: PATH.INVENTORY_CHECK.requiresAuth }
  },
  {
    path: PATH.INVENTORY_OPERATION.path,
    component: InventoryOperation,
    name: "InventoryOperation",
    meta: { requiresAuth: PATH.INVENTORY_OPERATION.requiresAuth }
  },
  {
    path: PATH.INVENTORY_IN.path,
    component: InventoryIn,
    name: "InventoryIn",
    meta: { requiresAuth: PATH.INVENTORY_LOSS.requiresAuth }
  },
  {
    path: PATH.INVENTORY_OUT.path,
    component: InventoryOut,
    name: "InventoryOut",
    meta: { requiresAuth: PATH.INVENTORY_LOSS.requiresAuth }
  },
  {
    path: PATH.INVENTORY_LOSS.path,
    component: InventoryLoss,
    name: "InventoryLoss",
    meta: { requiresAuth: PATH.INVENTORY_LOSS.requiresAuth }
  },
  {
    path: PATH.INVENTORY_OVERFLOW.path,
    component: InventoryOverflow,
    name: "InventoryOverflow",
    meta: { requiresAuth: PATH.INVENTORY_OVERFLOW.requiresAuth }
  },
  {
    path: PATH.INVENTORY_PRESENT.path,
    component: InventoryPresent,
    name: "InventoryPresent",
    meta: { requiresAuth: PATH.INVENTORY_PRESENT.requiresAuth }
  },
  {
    path: PATH.INVENTORY_VIEW.path,
    component: InventoryView,
    meta: { requiresAuth: PATH.INVENTORY_VIEW.requiresAuth }
  },
  {
    path: PATH.INVENTORY_WARNING.path,
    component: InventoryWarning,
    name: "InventoryWarning",
    meta: { requiresAuth: PATH.INVENTORY_WARNING.requiresAuth }
  },
  // 销售管理
  {
    path: PATH.PURCHASE_VIEW.path,
    component: PurchaseView,
    name: "PurchaseView",
    meta: { requiresAuth: PATH.PURCHASE_VIEW.requiresAuth }
  },
  {
    path: PATH.PURCHASE_RETURN_VIEW.path,
    component: PurchaseReturnView,
    name: "PurchaseReturnView",
    meta: { requiresAuth: PATH.PURCHASE_RETURN_VIEW.requiresAuth }
  },
  {
    path: PATH.SALE_VIEW.path,
    component: SaleView,
    name: "SaleView",
    meta: { requiresAuth: PATH.SALE_VIEW.requiresAuth }
  },
  {
    path: PATH.SALE_RETURN_VIEW.path,
    component: SaleReturnView,
    name: "SaleReturnView",
    meta: {requiresAuth: PATH.SALE_RETURN_VIEW.requiresAuth },
  },
  {
    path: PATH.CUSTOMER_VIEW.path,
    component: CustomerView,
    name: "CustomerView",
    meta: { requiresAuth: PATH.CUSTOMER_VIEW.requiresAuth }
  },
  // 审批
  {
    path: PATH.GM_APPROVAL.path,
    component: Approval,
    meta: { requiresAuth: PATH.GM_APPROVAL.requiresAuth }
  },
  {
    path:PATH.GM_SALARY_APPROVAL.path,
    component:ApprovalSalary,
    meta:{requiresAuth: PATH.GM_SALARY_APPROVAL.requiresAuth}
  },

  //员工管理
  {
    path: PATH.STAFF_MANAGEMENT.path,
    component: StaffManagement,
    meta: {requiresAuth: PATH.STAFF_MANAGEMENT.requiresAuth}
  },
  //财务人员
  {
    path:PATH.FINANCE_SALARY.path,
    component: FinanceSalary,
    meta: {requiresAuth: PATH.FINANCE_SALARY.requiresAuth}
  },
  {
    path: PATH.FINANCE_ACCOUNT.path,
    component:FinanceAccount,
    meta: {requiresAuth: PATH.FINANCE_ACCOUNT.requiresAuth}
  },
  {
    path:PATH.FINANCE_RECEIPT.path,
    component:FinanceReceipt,
    meta:{requiresAuth: PATH.FINANCE_RECEIPT.requiresAuth}
  },
  {
    path: PATH.FINANCE_PAYMENT.path,
    component:FinancePayment,
    meta: {requiresAuth: PATH.FINANCE_PAYMENT.requiresAuth}
  },
  {
    path: PATH.GM_PAYMENT_APPROVAL.path,
    component:ApprovalPayment,
    meta: {requiresAuth: PATH.GM_PAYMENT_APPROVAL.requiresAuth}
  },
  {
    path: PATH.GM_CREDIT_APPROVAL.path,
    component:ApprovalCredit,
    meta: {requiresAuth: PATH.GM_CREDIT_APPROVAL.requiresAuth}
  },
  {
    path: PATH.FINANCE_SALE_DETAIL.path,
    component:FinanceSaleDetail,
    meta: {requiresAuth: PATH.FINANCE_SALE_DETAIL.requiresAuth}
  },
  {
    path: PATH.FINANCE_BUSINESS_CIRCUMSTANCE.path,
    component:FinanceBusinessCircumstance,
    meta: {requiresAuth: PATH.FINANCE_BUSINESS_CIRCUMSTANCE.requiresAuth}
  },
  {
    path: PATH.FINANCE_BUSINESS_PROCESS.path,
    component:FinanceBusinessProcess,
    meta: {requiresAuth: PATH.FINANCE_BUSINESS_PROCESS.requiresAuth}
  },
  {
    path: PATH.FINANCE_YEAR_BONUS.path,
    component:FinanceYearBonus,
    meta: {requiresAuth: PATH.FINANCE_YEAR_BONUS.requiresAuth}
  },
  {
    path:PATH.FINANCE_INIT_ACCOUNT.path,
    component:FinanceInitAccount,
    meta: {requiresAuth: PATH.FINANCE_INIT_ACCOUNT.requiresAuth}
  },
  {
    path: PATH.PROMOTION.path,
    component:Promotion,
    meta: {requiresAuth: PATH.PROMOTION.requiresAuth}
  },
  // -----------------------未找到页面-----------------------------
  {
    path: "*",
    redirect: "/error"
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

router.beforeEach(async (to, from, next) => {
  // console.log(to.path);
  if (to.path === "/error" || to.path === "/login") {
    next();
  } else if (to.path === "/") {
    let token = sessionStorage.getItem("token");
    let role = sessionStorage.getItem("role");
    if (token == null || role == null) next("/login");
    else next();
  } else if (to.meta.requiresAuth) {
    if (
      to.meta.requiresAuth.some(
        role => role.toString() === sessionStorage.getItem("role")
      )
    ) {
      //有权限
      // console.log("获得访问权限");
      next()
    } else {
      // console.log("无权限访问");
      next("/"); //无权限,跳回主页
    }
  } else {
    // 非法路径, 直接next (跳转error)
    next();
  }
});

export default router;
