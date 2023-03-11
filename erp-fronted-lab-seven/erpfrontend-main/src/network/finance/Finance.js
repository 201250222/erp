import request from "@/network/request";
const testAPI = require("@/apis")

export const financeSalaryCreate = config => request._get(testAPI.FINANCE_CREATE_SALARY, config);
export const accountCreate = config=>request._post(testAPI.ACCOUNT_CREATE,config);
export const accountRemove = config=>request._get(testAPI.ACCOUNT_REMOVE,config);
export const accountModify = config=>request._get(testAPI.ACCOUNT_MODIFY,config);
export const accountFindAll = config=>request._get(testAPI.ACCOUNT_FIND_ALL,config);
export const financeFindAllSalary = config=>request._get(testAPI.FINANCE_FIND_ALL_SALARY,config);
export const approvalSalary = config=>request._get(testAPI.FINANCE_SALARY_APPROVAL,config);
export const financeFindSalaryByState = config=>request._get(testAPI.FINANCE_FIND_SALARY_BY_STATE,config);
export const financeFindCreditByState = config =>request._get(testAPI.FINANCE_FIND_CREDIT_BY_STATE,config);
export const financeCreditCreate  = config=>request._post(testAPI.FINANCE_CREATE_CREDIT,config);
export const financePaymentCreate = config=>request._post(testAPI.FINANCE_CREATE_PAYMENT,config);
export const financeFindPaymentByState = config=>request._get(testAPI.FINANCE_FIND_PAYMENT_BY_STATE,config);
export const approvalPayment = config=>request._get(testAPI.FINANCE_PAYMENT_APPROVAL,config);
export const approvalCredit = config=>request._get(testAPI.FINANCE_CREDIT_APPROVAL,config);
export const financeFindSaleDetail = config=>request._post(testAPI.FINANCE_FIND_SALE_DETAIL,config);
export const saleTable = config =>request._post(testAPI.FINANCE_QUERY_SALE_TABLE,config);
export const financeTable = config =>request._post(testAPI.FINANCE_QUERY_FINANCE_TABLE,config);
export const purchaseTable = config=>request._post(testAPI.FINANCE_QUERY_PURCHASE_TABLE,config);
export const inventoryTable =config=>request._post(testAPI.FINANCE_QUERY_INVENTORY_TABLE,config);
export const yearBonusFind  = config =>request._get(testAPI.FINANCE_YEAR_BONUS_FIND,config);
export const businessCircumstance = config=>request._post(testAPI.FINANCE_BUSINESS_CIRCUMSTANCE,config);
export const yearBonusGet = config=>request._get(testAPI.FINANCE_BONUS_GET_ALL,config);
export const yearBonusUpdate = config=>request._post(testAPI.FINANCE_BONUS_UPDATE,config);
export const initCreateClass = config=>request._post(testAPI.FINANCE_INIT_CREATE_CLASS,config)
export const initCreateProduct = config=>request._post(testAPI.FINANCE_INIT_CREATE_PRODUCT,config)
export const initCreateAccount = config=>request._post(testAPI.FINANCE_INIT_CREATE_ACCOUNT,config)
export const initCreateCustomer = config=>request._post(testAPI.FINANCE_INIT_CREATE_CUSTOMER,config)
export const initGetClass = config=>request._get(testAPI.FINANCE_INIT_GET_CLASS,config)
export const initGetAccount = config=>request._get(testAPI.FINANCE_INIT_GET_ACCOUNT,config)
export const initGetCustomer = config=>request._get(testAPI.FINANCE_INIT_GET_CUSTOMER,config)
export const initGetProduct = config=>request._get(testAPI.FINANCE_INIT_GET_PRODUCT,config)
export const initEnd = config=>request._get(testAPI.FINANCE_INIT_END,config);
export const flush = config =>request._post(testAPI.FINANCE_RED_FLUSH,config);