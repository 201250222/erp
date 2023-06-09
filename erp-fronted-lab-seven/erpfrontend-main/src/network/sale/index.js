import request from "@/network/request"
const testAPI = require("@/apis")


export const getAllCustomer = config => request._get(testAPI.SALE_CUSTOMER_QUERY, config)
export const getMaxAmountCustomerOfSalesmanByTime = config => request._get(testAPI.SALE_CUSTOMER_MAX, config)

export const getAllSale = config => request._get(testAPI.SALE_ALL, config);
export const createSale = config => request._post(testAPI.SALE_CREATE, config);
export const firstApproval = config => request._get(testAPI.SALE_FIRST_APPROVAL, config);
export const secondApproval = config => request._get(testAPI.SALE_SECOND_APPROVAL, config);
export const getSaleBySheetId = config => request._get(testAPI.SALE_FIND_SHEET_BY_ID, config);
export const createSaleReturn = config=>request._post(testAPI.SALE_RETURN_MAKE,config);
export const getAllReturnSale  = config=> request._get(testAPI.RETURN_SALE_ALL,config);
export const findReturnByState= config=> request._get(testAPI.SALE_FIND_RETURN_BY_STATE,config)
export const approval = config=> request._get(testAPI.SALE_RETURN_APPROVAL,config);
export const gerPromote = config=>request._post(testAPI.PROMOTION_GET,config);
export const createPromote = config =>request._post(testAPI.PROMOTION_CREATE,config);