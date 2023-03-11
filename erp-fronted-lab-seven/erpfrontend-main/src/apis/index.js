//TEST
const TEST_GET = "/api/test/get";
const TEST_POST = "/api/test/post";

const AUTH = "/api/user/auth"
const LOGIN = "/api/user/login"
const REGISTER = '/api/user/register'

// 商品分类管理
const COMMODITY_CLASSIFICATION_ALL = '/api/category/queryAll'
const COMMODITY_CLASSIFICATION_CREATE = '/api/category/create'
const COMMODITY_CLASSIFICATION_UPDATE = '/api/category/update'
const COMMODITY_CLASSIFICATION_DELETE = '/api/category/delete'

// 商品管理
const COMMODITY_ALL = '/api/product/queryAll';
const COMMODITY_CREATE = '/api/product/create';
const COMMODITY_UPDATE = '/api/product/update';
const COMMODITY_DELETE = '/api/product/delete';


const WAREHOUSE_INPUT = '/api/warehouse/input';
const WAREHOUSE_OUTPUT_PRE = '/api/warehouse/product/count';
const WAREHOUSE_OUTPUT = '/api/warehouse/output';
const WAREHOUSE_GET_INPUTSHEET = '/api/warehouse/inputSheet/state';
const WAREHOUSE_GET_OUTPUTSHEET = '/api/warehouse/outputSheet/state';
const WAREHOUSE_IO_DEATIL_BY_TIME = '/api/warehouse/sheetContent/time';
const WAREHOUSE_IPQ_BY_TIME = '/api/warehouse/inputSheet/time/quantity';
const WAREHOUSE_OPQ_BY_TIME = '/api/warehouse/outputSheet/time/quantity';
const WAREHOUSE_OUTPUTSHEET_APPROVE = '/api/warehouse/outputSheet/approve';
const WAREHOUSE_INPUTSHEET_APPROVE = '/api/warehouse/inputSheet/approve';
const WAREHOUSE_DAILY_COUNT = '/api/warehouse/warehouse/counting';
const WAREHOUSE_EXCEL  = '/api/warehouse/counting/excel'


// 进货管理
const PURCHASE_ALL = '/api/purchase/sheet-show';
const PURCHASE_FIND_SHEET_BY_ID = '/api/purchase/find-sheet';
const PURCHASE_CREATE = '/api/purchase/sheet-make';
const PURCHASE_FIRST_APPROVAL = '/api/purchase/first-approval';
const PURCHASE_SECOND_APPROVAL = '/api/purchase/second-approval';
// 进货退货管理
const PURCHASE_RETURN_ALL = '/api/purchase-returns/sheet-show';
const PURCHASE_RETURN_CREATE = '/api/purchase-returns/sheet-make';
const PURCHASE_RETURN_FIRST_APPROVAL = '/api/purchase-returns/first-approval';
const PURCHASE_RETURN_SECOND_APPROVAL = '/api/purchase-returns/second-approval';
// 销售管理
const SALE_ALL = '/api/sale/sheet-show';
const SALE_CREATE = '/api/sale/sheet-make';
const SALE_FIRST_APPROVAL = '/api/sale/first-approval';
const SALE_SECOND_APPROVAL = '/api/sale/second-approval';
const SALE_FIND_SHEET_BY_ID = '/api/sale/find-sheet';
//销售退货
const SALE_FIND_RETURN_BY_STATE = '/api/sale-returns/sheet-show';
const SALE_RETURN_MAKE =  '/api/sale-returns/sheet-make';
const SALE_RETURN_APPROVAL = '/api/sale-returns/first-approval'
// 客户管理
const CUSTOMER_QUERY = '/api/customer/findByType';
const SALE_PURCHASE_ALL = '/api/purchase/sheet-show';
const SALE_PURCHASE_CREATE = '/api/purchase/sheet-make';
const SALE_CUSTOMER_MAX = '/api/sale/maxAmountCustomer';
const CUSTOMER_CREATE = '/api/customer/add';
const CUSTOMER_BY_ID= '/api/customer/findById';
const RETURN_SALE_ALL = '/api/sale-returns/sheet-show';
const CUSTOMER_DELETE = '/api/customer/delete'
//员工管理
const STAFF_ADD = '/api/staff/add';
const STAFF_FIND_ALL = '/api/staff/show';
const STAFF_SALARY_AMEND = '/api/staff/amend';
const  STAFF_CLOCK_IN = '/api/staff/clockIn';
//财务管理
const FINANCE_CREATE_SALARY= '/api/salary//all-sheets-make';
const ACCOUNT_CREATE = '/api/account/account-create';
const ACCOUNT_REMOVE = '/api/account/account-remove' ;
const ACCOUNT_MODIFY = '/api/account//account-modify';
const ACCOUNT_FIND_ALL = '/api/account//account-selectAll';
const FINANCE_FIND_ALL_SALARY = 'api/salary/findAll';
const FINANCE_SALARY_APPROVAL = '/api/salary/approval';
const FINANCE_FIND_SALARY_BY_STATE = '/api/salary/sheet-show';
const FINANCE_FIND_CREDIT_BY_STATE = '/api/credit/sheet-select-by-state';
const FINANCE_CREATE_CREDIT = '/api/credit/sheet-make';
const FINANCE_CREATE_PAYMENT = '/api/payment/sheet-make';
const FINANCE_FIND_PAYMENT_BY_STATE ='/api/payment/sheet-select-by-state';
const FINANCE_PAYMENT_APPROVAL = '/api/payment/sheet-pass';
const FINANCE_CREDIT_APPROVAL = "/api/credit/sheet-pass"
const FINANCE_FIND_SALE_DETAIL = "/api/sale/detail";
const FINANCE_QUERY_SALE_TABLE = "/api/manage-proc/sale-sheet";
const FINANCE_QUERY_PURCHASE_TABLE = "/api/manage-proc/purchase-sheet";
const FINANCE_QUERY_FINANCE_TABLE =    "/api/manage-proc/financial-sheet";
const FINANCE_QUERY_INVENTORY_TABLE = "/api/manage-proc/inventory-sheet";
const FINANCE_YEAR_BONUS_FIND = "/api//bonus//showPerformance";
const FINANCE_BUSINESS_CIRCUMSTANCE = "/api/manage-cond/make-inquiry";
const FINANCE_BONUS_GET_ALL = "/api/bonus/showMade";
const FINANCE_BONUS_UPDATE = "/api/bonus/updateOne";
const FINANCE_INIT_CREATE_CLASS = "/api/establishment/addCategory"
const FINANCE_INIT_GET_CLASS = "/api/establishment/showCategory"
const FINANCE_INIT_CREATE_PRODUCT = "/api/establishment/addProduct"
const FINANCE_INIT_GET_PRODUCT = "/api/establishment/showProduct"
const FINANCE_INIT_CREATE_CUSTOMER = "/api/establishment/addCustomer"
const FINANCE_INIT_GET_CUSTOMER = "/api/establishment/showCustomer"
const FINANCE_INIT_CREATE_ACCOUNT = "/api/establishment/addAccount";
const FINANCE_INIT_GET_ACCOUNT = "/api/establishment/showAccount";
const FINANCE_INIT_END = "/api/establishment/initDB";
const PROMOTION_GET = "/api/promote/show-promotion";
const PROMOTION_CREATE = "/api/promote/create-promotion"
const FINANCE_RED_FLUSH = "/api/manage-proc/red-flush";



module.exports = {
  TEST_GET,
  TEST_POST,

  AUTH,
  LOGIN,
  REGISTER,
  COMMODITY_CLASSIFICATION_ALL,
  COMMODITY_CLASSIFICATION_CREATE,
  COMMODITY_CLASSIFICATION_UPDATE,
  COMMODITY_CLASSIFICATION_DELETE,

  COMMODITY_ALL,
  COMMODITY_CREATE,
  COMMODITY_UPDATE,
  COMMODITY_DELETE,

  WAREHOUSE_INPUT,
  WAREHOUSE_OUTPUT_PRE,
  WAREHOUSE_OUTPUT,
  WAREHOUSE_GET_INPUTSHEET,
  WAREHOUSE_GET_OUTPUTSHEET,
  WAREHOUSE_IO_DEATIL_BY_TIME,
  WAREHOUSE_IPQ_BY_TIME,
  WAREHOUSE_OPQ_BY_TIME,
  WAREHOUSE_OUTPUTSHEET_APPROVE,
  WAREHOUSE_INPUTSHEET_APPROVE,
  WAREHOUSE_DAILY_COUNT,
  WAREHOUSE_EXCEL,

  PURCHASE_ALL,
  PURCHASE_CREATE,
  PURCHASE_FIRST_APPROVAL,
  PURCHASE_SECOND_APPROVAL,
  PURCHASE_RETURN_ALL,
  PURCHASE_RETURN_CREATE,
  PURCHASE_RETURN_FIRST_APPROVAL,
  PURCHASE_RETURN_SECOND_APPROVAL,
  PURCHASE_FIND_SHEET_BY_ID,
  CUSTOMER_DELETE,

  SALE_ALL,
  SALE_CREATE,
  SALE_FIRST_APPROVAL,
  SALE_SECOND_APPROVAL,
  CUSTOMER_BY_ID,
  SALE_CUSTOMER_MAX,
  SALE_FIND_SHEET_BY_ID,
  CUSTOMER_QUERY,
  CUSTOMER_CREATE,
  SALE_RETURN_MAKE,
  RETURN_SALE_ALL,
  SALE_FIND_RETURN_BY_STATE,
  SALE_RETURN_APPROVAL,

  STAFF_ADD,
  STAFF_FIND_ALL,
  STAFF_SALARY_AMEND,
  STAFF_CLOCK_IN,

  FINANCE_CREATE_SALARY,
  FINANCE_FIND_ALL_SALARY,

  ACCOUNT_MODIFY,
  ACCOUNT_REMOVE,
  ACCOUNT_FIND_ALL,
  ACCOUNT_CREATE,
  FINANCE_SALARY_APPROVAL,
  FINANCE_FIND_SALARY_BY_STATE,
  FINANCE_FIND_CREDIT_BY_STATE,
  FINANCE_CREATE_CREDIT,
  FINANCE_FIND_PAYMENT_BY_STATE,
  FINANCE_CREATE_PAYMENT,
  FINANCE_PAYMENT_APPROVAL,
  FINANCE_CREDIT_APPROVAL,
  FINANCE_FIND_SALE_DETAIL,
  FINANCE_QUERY_FINANCE_TABLE,
  FINANCE_QUERY_INVENTORY_TABLE,
  FINANCE_QUERY_SALE_TABLE,
  FINANCE_QUERY_PURCHASE_TABLE,
  FINANCE_YEAR_BONUS_FIND,
  FINANCE_BUSINESS_CIRCUMSTANCE,
  FINANCE_BONUS_GET_ALL,
  FINANCE_BONUS_UPDATE,
  FINANCE_INIT_CREATE_ACCOUNT,
  FINANCE_INIT_GET_ACCOUNT,
  FINANCE_INIT_CREATE_CLASS,
  FINANCE_INIT_GET_CLASS,
  FINANCE_INIT_CREATE_CUSTOMER,
  FINANCE_INIT_GET_CUSTOMER,
  FINANCE_INIT_GET_PRODUCT,
  FINANCE_INIT_CREATE_PRODUCT,
  FINANCE_INIT_END,
  FINANCE_RED_FLUSH,
  PROMOTION_GET,
  PROMOTION_CREATE,
};
