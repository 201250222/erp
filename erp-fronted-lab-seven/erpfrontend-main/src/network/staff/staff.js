import request from "@/network/request";
const testAPI = require("@/apis");

export const staff_add = config=> request._post(testAPI.STAFF_ADD,config);
export const staff_find_all = config=> request._get(testAPI.STAFF_FIND_ALL,config);
export const staff_salary_amend = config=>request._get(testAPI.STAFF_SALARY_AMEND,config);
export const staff_clock_in  = config=>request._get(testAPI.STAFF_CLOCK_IN,config)