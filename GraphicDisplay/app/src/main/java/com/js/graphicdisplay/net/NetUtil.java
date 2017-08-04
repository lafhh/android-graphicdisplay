package com.js.graphicdisplay.net;

/**
 * Created by js_gg on 2017/6/27.
 */

public class NetUtil {
    //106.14.172.38:8080
    //192.168.1.193:8081
    public static final String IP        = "106.14.172.38";
    public static final String PORT      = ":8080";
    public static final String HTTP_PREF = "http://" + IP + PORT;
    public static final String BASE_URL  = HTTP_PREF + "/jsbi/";

    public static final String URL_FUNDSTURNEDOVER      = BASE_URL + "fundsTurnedOver/";
    public static final String URL_LANDBANKING          = BASE_URL + "landbanking/";
    public static final String URL_RECEIVEDPAYMENTS     = BASE_URL + "receivedPayments/";
    public static final String URL_SALES                = BASE_URL + "sales/";
    public static final String URL_REALESTATERENTAL     = BASE_URL + "realEstateRental/";
    public static final String URL_MAIN                 = BASE_URL + "main/";

    /**
     * 查询各[集团]资金月度上缴情况表(用于表格展示数据)
     * 
     * orgId=&ym=&limit=10&offset=0&order=&sort=
     * 按集团排序 sort=groupName
     *
     * @link{curl -X POST --data "orgId=2&ym=201701&limit=10&offset=0&order=desc&sort=groupName" http://192.168.1.193:8081/jsbi/fundsTurnedOver/getFundsTurnedOverForGroupTable.do}
     {
         "total": 1,
         "rows": [
             {
             "id": null,
             "groupId": 2,
             "groupCode": "scjt",
             "groupName": "申城集团",
             "compId": null,
             "compCode": null,
             "compName": null,
             "itemCode": null,
             "itemName": null,
             "ym": 201701,
             "monthIndex": 100000,
             "monthFulfilQuantity": 80000,
             "monthAch": 80,
             "cumulativeIndex": 100000,
             "cumulativeFulfilQuantity": 80000,
             "cumulativeAch": 80,
             "incompleteDescription": null,
             "createBy": null,
             "createTime": null,
             "updateBy": null,
             "updateTime": null
             }
         ]
     }

    {"total":0,"rows":[]}
     */
    public static final String URL_FUNDSTURNEDOVER_GROUP_TABLE  = URL_FUNDSTURNEDOVER + "getFundsTurnedOverForGroupTable.do";

    /**
     * 查询各[公司]资金资金月度上缴情况表(用于表格展示数据)
     * 
     * groupId=4&compId=68&ym=&limit=5&offset=0&order=&sort=
     *
     * curl -X POST --data "groupId=3&limit=10&offset=0&order=asc&sort=compName" http://192.168.1.193:8081/jsbi/fundsTurnedOver/getFundsTurnedOverForComTable.do
     {
         "total": 2,
         "rows": [
             {
                 "id": null,
                 "groupId": null,
                 "groupCode": "scjt",
                 "groupName": "申城集团",
                 "compId": null,
                 "compCode": "txjy",
                 "compName": "桐乡市佳源房地产开发有限公司",
                 "itemCode": null,
                 "itemName": null,
                 "ym": 201701,
                 "monthIndex": 100000,
                 "monthFulfilQuantity": 80000,
                 "monthAch": 80,
                 "cumulativeIndex": 100000,
                 "cumulativeFulfilQuantity": 80000,
                 "cumulativeAch": 80,
                 "incompleteDescription": "",
                 "createBy": null,
                 "createTime": null,
                 "updateBy": null,
                 "updateTime": null
             },
             ...
         ]
     }
     */
    public static final String URL_FUNDSTURNEDOVER_COMP_TABLE   = URL_FUNDSTURNEDOVER + "getFundsTurnedOverForComTable.do";

    /**
     * 查询各集团资金月度上缴情况表数据(用于图形展示)
     * 
     * orgId=&ym=
     * orgId和ym都为空为查询所有数据
     * curl -X POST --data "orgId=2&ym=201701" http://192.168.1.193:8081/jsbi/fundsTurnedOver/getAllFundsTurnedOverForChart.do
     [
         {
             "id": null,
             "groupId": null,
             "groupCode": "scjt",
             "groupName": "申城集团",
             "compId": null,
             "compCode": null,
             "compName": null,
             "itemCode": null,
             "itemName": null,
             "ym": 201701,
             "monthIndex": 100000,
             "monthFulfilQuantity": 80000,
             "monthAch": 80,
             "cumulativeIndex": 100000,
             "cumulativeFulfilQuantity": 80000,
             "cumulativeAch": 80,
             "incompleteDescription": null,
             "createBy": null,
             "createTime": null,
             "updateBy": null,
             "updateTime": null
         }
     ]
     */
    public static final String URL_FUNDSTURNEDOVER_ALL_CHART    = URL_FUNDSTURNEDOVER + "getAllFundsTurnedOverForChart.do";


    /**
     * 查询各[集团]土地储备情况表数据(用于图形展示)
     * 
     * orgId=&ym=
     *
     * orgId和ym都为空为查询所有数据
     * curl -X POST --data "orgId=2&ym=201701" http://192.168.1.193:8081/jsbi/landbanking/queryItemsForChart.do
     [
         {
             "id": 32,
             "groupId": null,
             "groupCode": null,
             "groupName": null,
             "compId": null,
             "compCode": null,
             "compName": null,
             "itemCode": null,
             "ym": 201701,
             "acre": 19000,
             "totalPrice": 28000,
             "paid": 2500,
             "buildableArea": 16000,
             "reserveBuildingArea": 20000,
             "createBy": null,
             "createTime": null,
             "updateBy": null,
             "updateTime": null,
             "orgName": "申城集团",
             "itemName": null,
             "orgId": null,
             "type": null,
             "price": null,
             "monthRealPaid": null,
             "plotRatio": null,
             "overgroundBuildableArea": null,
             "undergroundBuildableArea": null,
             "unsoldOvergroundBuildableArea": null,
             "withoutPresaleCertificateOvergroundBuildableArea": null,
             "unstartedOvergroundBuildableAea": null,
             "reserveUndergroundBuildingArea": null,
             "withoutPresaleCertificateArea": null,
             "unstartedArea": null,
             "mortgageSituation": null,
             "remark": null,
             "isFinished": null,
             "purchaseDate": null,
             "landbankingItemId": null
         }
     ]
     */
    public static final String URL_LANDBANKING_QUERYITEM_CHART  = URL_LANDBANKING + "queryItemsForChart.do";

    /**
     * 查询各[集团]土地储备情况表数据(用于表格展示)
     * orgId=&ym=&limit=5&offset=0&order=&sort=
     * 按集团排序 sort=orgName
     *
     * curl -X POST --data "orgId=2&limit=5&offset=0&order=asc&sort=orgName" http://192.168.1.193:8081/jsbi/landbanking/queryItems.do
     {
         "total": 3,
         "rows": [
             {
                 "id": 38,
                 "groupId": null,
                 "groupCode": null,
                 "groupName": null,
                 "compId": null,
                 "compCode": null,
                 "compName": null,
                 "itemCode": null,
                 "ym": 201704,
                 "acre": 10000,
                 "totalPrice": 10000,
                 "paid": 5000,
                 "buildableArea": 8000,
                 "reserveBuildingArea": 10000,
                 "createBy": null,
                 "createTime": null,
                 "updateBy": null,
                 "updateTime": null,
                 "orgName": "申城集团",
                 "itemName": null,
                 "orgId": 2,
                 "type": null,
                 "price": null,
                 "monthRealPaid": null,
                 "plotRatio": null,
                 "overgroundBuildableArea": null,
                 "undergroundBuildableArea": null,
                 "unsoldOvergroundBuildableArea": null,
                 "withoutPresaleCertificateOvergroundBuildableArea": null,
                 "unstartedOvergroundBuildableAea": null,
                 "reserveUndergroundBuildingArea": null,
                 "withoutPresaleCertificateArea": null,
                 "unstartedArea": null,
                 "mortgageSituation": null,
                 "remark": null,
                 "isFinished": null,
                 "purchaseDate": null,
                 "landbankingItemId": null
             },
             ...
         ]
     */
    public static final String URL_LANDBANKING_QUERYITEM = URL_LANDBANKING + "queryItems.do";

    /**
     * 查询各[公司]土地储备情况表数据(用于表格展示)
     * cgId=&comId=&ym=&limit=5&offset=0&order=&sort=
     *
     * curl -X POST --data "cgId=2&limit=5&offset=0&order=asc&sort=orgName" http://192.168.1.193:8081/jsbi/landbanking/queryItemsForCom.do
     {
         "total": 5,
         "rows": [
             {
                 "id": 34,
                 "groupId": null,
                 "groupCode": null,
                 "groupName": null,
                 "compId": null,
                 "compCode": null,
                 "compName": null,
                 "itemCode": null,
                 "ym": 201703,
                 "acre": 9000.00,
                 "totalPrice": 18000.00,
                 "paid": 3000.00,
                 "buildableArea": 8000.00,
                 "reserveBuildingArea": 10000.00,
                 "createBy": null,
                 "createTime": null,
                 "updateBy": null,
                 "updateTime": null,
                 "orgName": "桐乡巨能置业",
                 "itemName": "桐乡罗马都市",
                 "orgId": null,
                 "type": null,
                 "price": null,
                 "monthRealPaid": null,
                 "plotRatio": null,
                 "overgroundBuildableArea": null,
                 "undergroundBuildableArea": null,
                 "unsoldOvergroundBuildableArea": null,
                 "withoutPresaleCertificateOvergroundBuildableArea": null,
                 "unstartedOvergroundBuildableAea": null,
                 "reserveUndergroundBuildingArea": null,
                 "withoutPresaleCertificateArea": null,
                 "unstartedArea": null,
                 "mortgageSituation": null,
                 "remark": null,
                 "isFinished": null,
                 "purchaseDate": null,
                 "landbankingItemId": null
             },
             ...
        ]
     }
     */
    public static final String URL_LANDBANKING_QUERYITEM_COMP   = URL_LANDBANKING + "queryItemsForCom.do";

    /**
     * 查询各集团回款达成率数据(用于图形展示)
     * 
     * orgId=&ym=
     * curl -X POST http://192.168.1.193:8081/jsbi/receivedPayments/queryItemsForChart.do
     [
         {
             "id": 8,
             "groupCode": null,
             "compCode": null,
             "itemCode": null,
             "propertyType": null,
             "ym": 201707,
             "monthIncomePlan": 18000.00,
             "monthIncomeReal": 2200.00,
             "monthIncomeAch": 12.22,
             "cumulativeIncomePlan": null,
             "cumulativeIncomeReal": null,
             "cumulativeIncomeAch": null,
             "createBy": null,
             "createTime": null,
             "updateBy": null,
             "updateTime": null,
             "orgName": "申城集团",
             "itemName": null,
             "orgId": null
         },
         ...
     ]
     */
    public static final String URL_RECEIVEDPAYMENTS_CHART = URL_RECEIVEDPAYMENTS + "queryItemsForChart.do";

    /**
     * 查询各[集团]回款达成率数据(用于表格展示)
     * 
     * orgId=&ym=&limit=&offset=&sort=&order=
     *
     * curl -X POST --data "limit=10&offset=0&order=asc&sort=orgName" http://192.168.1.193:8081/jsbi/receivedPayments/queryItems.do
     {
         "total": 2,
         "rows": [
             {
                 "id": 10,
                 "groupCode": null,
                 "compCode": null,
                 "itemCode": null,
                 "propertyType": null,
                 "ym": 201708,
                 "monthIncomePlan": 9000.00,
                 "monthIncomeReal": 800.00,
                 "monthIncomeAch": 8.89,
                 "cumulativeIncomePlan": 9000.00,
                 "cumulativeIncomeReal": 2200.00,
                 "cumulativeIncomeAch": 24.44,
                 "createBy": null,
                 "createTime": null,
                 "updateBy": null,
                 "updateTime": null,
                 "orgName": "申城集团",
                 "itemName": null,
                 "orgId": 2
             },
             ...
         ]
     }
     */
    public static final String URL_RECEIVEDPAYMENTS_TABLE_GROUP = URL_RECEIVEDPAYMENTS + "queryItems.do";

    /**
     * 查询各[公司]回款达成率数据(用于表格展示)
     * 
     * compId=&ym=&limit=10&offset=0&sort=orgName&order=asc&groupId=2
     * curl -X POST --data "groupId=2&limit=10&offset=0&order=asc&sort=orgName" http://192.168.1.193:8081/jsbi/receivedPayments/queryItemsForCom.do
     {
         "total": 3,
         "rows": [
             {
                 "id": 10,
                 "groupCode": null,
                 "compCode": null,
                 "itemCode": null,
                 "propertyType": null,
                 "ym": 201708,
                 "monthIncomePlan": 9000.00,
                 "monthIncomeReal": 800.00,
                 "monthIncomeAch": 0.09,
                 "cumulativeIncomePlan": 9000.00,
                 "cumulativeIncomeReal": 2200.00,
                 "cumulativeIncomeAch": 0.25,
                 "createBy": null,
                 "createTime": null,
                 "updateBy": null,
                 "updateTime": null,
                 "orgName": "桐乡市足佳置业有限公司",
                 "itemName": "桐乡威尼斯广场",
                 "orgId": null
             },
             ...
         ]
     }
     */
    public static final String URL_RECEIVEDPAYMENTS_TABLE_COMP = URL_RECEIVEDPAYMENTS + "queryItemsForCom.do";


    /**
     * 查询各集团销售情况表数据(用于图形展示)
     * 
     * orgId=&ymd=
     * curl -X POST http://192.168.1.193:8081/jsbi/sales/getAllSalesForChart.do
     [
         {
             "id": null,
             "groupId": null,
             "groupCode": "scjt",
             "groupName": "申城集团",
             "compId": null,
             "compCode": null,
             "compName": null,
             "itemCode": null,
             "itemName": null,
             "propertyType": null,
             "ymd": 20170701,
             "dayContractedQuantity": 4.00,
             "dayContractedAmount": 800.00,
             "dayIncome": 600.00,
             "monthContractedQuantity": 4.00,
             "monthContractedAmount": 800.00,
             "monthIncome": 600.00,
             "yearContractedQuantity": 4.00,
             "yearContractedAmount": 800.00,
             "yearIncome": 600.00,
             "createBy": null,
             "createTime": null,
             "updateBy": null,
             "updateTime": null
         },
         ...
     ]
     */
    public static final String URL_SALES_CHART = URL_SALES + "getAllSalesForChart.do";

    /**
     * 查询各[集团]销售情况表数据(用于表格展示)
     * 
     * orgId=&ymd=&limit=10&offset=0&order=&sort=
     * curl -X POST --data "limit=10&offset=0" http://192.168.1.193:8081/jsbi/sales/getSalesForGroupTable.do
     {
         "total": 3,
         "rows": [
             {
                 "id": null,
                 "groupId": 2,
                 "groupCode": "scjt",
                 "groupName": "申城集团",
                 "compId": null,
                 "compCode": null,
                 "compName": null,
                 "itemCode": null,
                 "itemName": null,
                 "propertyType": null,
                 "ymd": 20170701,
                 "dayContractedQuantity": 4.00,
                 "dayContractedAmount": 800.00,
                 "dayIncome": 600.00,
                 "monthContractedQuantity": 4.00,
                 "monthContractedAmount": 800.00,
                 "monthIncome": 600.00,
                 "yearContractedQuantity": 4.00,
                 "yearContractedAmount": 800.00,
                 "yearIncome": 600.00,
                 "createBy": null,
                 "createTime": null,
                 "updateBy": null,
                 "updateTime": null
             },
             ...
         ]
     }
     */
    public static final String URL_SALES_TABLE_GROUP = URL_SALES + "getSalesForGroupTable.do";

    /**
     * 查询各[公司]销售情况表数据(用于表格展示)
     * 
     * groupId=2&compId=&ymd=&limit=10&offset=0&order=&sort=
     * curl -X POST --data "compId=14&groupId=2&limit=10&offset=0" http://192.168.1.193:8081/jsbi/sales/getSalesForComTable.do
     {
         "total": 3,
         "rows": [
             {
                 "id": null,
                 "groupId": 2,
                 "groupCode": "scjt",
                 "groupName": "申城集团",
                 "compId": "14",
                 "compCode": "txzjzy",
                 "compName": "桐乡市足佳置业有限公司",
                 "itemCode": null,
                 "itemName": null,
                 "propertyType": null,
                 "ymd": 20170701,
                 "dayContractedQuantity": 4.00,
                 "dayContractedAmount": 800.00,
                 "dayIncome": 600.00,
                 "monthContractedQuantity": 4.00,
                 "monthContractedAmount": 800.00,
                 "monthIncome": 600.00,
                 "yearContractedQuantity": 4.00,
                 "yearContractedAmount": 800.00,
                 "yearIncome": 600.00,
                 "createBy": null,
                 "createTime": null,
                 "updateBy": null,
                 "updateTime": null
             },
         ...
         ]
     }
     */
    public static final String URL_SALES_TABLE_COMP = URL_SALES + "getSalesForComTable.do";

    /**
     * 查询各集团不动产出租情况表数据(用于图形展示)
     * groupId=&ym=
     * curl -X POST http://192.168.1.193:8081/jsbi/realEstateRental/queryRealEstateRentalForChart.do
     [
         {
             "id": null,
             "groupId": null,
             "groupCode": null,
             "groupName": "安徽集团",
             "propertyType": null,
             "bizFmt": null,
             "bizFmtName": null,
             "ym": 201701,
             "totalConstructionArea": null,
             "monthRangeArea": null,
             "houseSalePrice": null,
             "houseSaleTotalPrice": null,
             "leasableArea": 9000.00,
             "monthlyRentalArea": null,
             "leasedArea": 1200.00,
             "lettingRate": 13.33,
             "monthAverageRentPrice": null,
             "annualAverageRentAmount": null,
             "totalContract": null,
             "rentPeriodReturnRate": null,
             "rentFreePeriodReturnRate": null,
             "createBy": null,
             "createTime": null,
             "updateBy": null,
             "updateTime": null,
             "remark": null
         },
         ...
     ]
     */
    public static final String URL_REALESTATERENTAL_CHART = URL_REALESTATERENTAL + "queryRealEstateRentalForChart.do";

    /**
     * 查询各[集团]不动产出租情况表数据(用于表格展示)
     * groupId=&ym=&limit=10&offset=0&order=&sort=
     * @link curl -X POST --data "limit=10&offset=0" http://192.168.1.193:8081/jsbi/realEstateRental/getRealEstateRentalForGroupTable.do
     {
         "total": 9,
         "rows": [
             {
                 "id": null,
                 "groupId": 4,
                 "groupCode": null,
                 "groupName": "安徽集团",
                 "propertyType": null,
                 "bizFmt": null,
                 "bizFmtName": null,
                 "ym": 201701,
                 "totalConstructionArea": 9500.00,
                 "monthRangeArea": null,
                 "houseSalePrice": null,
                 "houseSaleTotalPrice": null,
                 "leasableArea": 9000.00,
                 "monthlyRentalArea": 1200.00,
                 "leasedArea": 1200.00,
                 "lettingRate": 13.33,
                 "monthAverageRentPrice": null,
                 "annualAverageRentAmount": null,
                 "totalContract": 1150000.00,
                 "rentPeriodReturnRate": null,
                 "rentFreePeriodReturnRate": null,
                 "createBy": null,
                 "createTime": null,
                 "updateBy": null,
                 "updateTime": null,
                 "remark": null
             },
         ...
         ]
     }
     */
    public static final String URL_REALESTATERENTAL_TABLE_GROUP = URL_REALESTATERENTAL + "getRealEstateRentalForGroupTable.do";

    /**
     * 查询各[物业]不动产出租情况表数据(用于表格展示)
     * groupId=&ym=&limit=10&offset=0&order=&sort=&propertyType=0&fmt=house
     * propertyType: 物业类型 0:自有物业;1:酒店
     * fmt: 物业业态
     * 自有物业: 住宅(house)、会所(club)、商业(business)、办公用房(office_room)、厂房(workshop)、车位(parking_lot)
     * 酒店: 对应的为该集团下的项目，传递的参数为项目CODE
     * curl -X POST --data "limit=10&offset=0" http://192.168.1.193:8081/jsbi/realEstateRental/queryRealEstateRentalForFmtTable.do
     {
         "total": 10,
         "rows": [
             {
                 "id": null,
                 "groupId": null,
                 "groupCode": null,
                 "groupName": "安徽集团",
                 "propertyType": "1",
                 "bizFmt": null,
                 "bizFmtName": "蚌埠东方都市",
                 "ym": 201702,
                 "totalConstructionArea": 10000.00,
                 "monthRangeArea": null,
                 "houseSalePrice": null,
                 "houseSaleTotalPrice": null,
                 "leasableArea": 9500.00,
                 "monthlyRentalArea": 2500.00,
                 "leasedArea": 2500.00,
                 "lettingRate": 26.32,
                 "monthAverageRentPrice": null,
                 "annualAverageRentAmount": null,
                 "totalContract": 2400000.00,
                 "rentPeriodReturnRate": null,
                 "rentFreePeriodReturnRate": null,
                 "createBy": null,
                 "createTime": null,
                 "updateBy": null,
                 "updateTime": null,
                 "remark": null
             },
         ...
         ]
     }
     */
    public static final String URL_REALESTATERENTAL_TABLE_FMT = URL_REALESTATERENTAL + "queryRealEstateRentalForFmtTable.do";

    /**
     * 查询所有集团信息接口
     * curl http://192.168.1.193:8081/jsbi/main/queryOrg.do
     [
         {
             "orgId": 2,
             "orgCode": "scjt",
             "orgName": "申城集团",
             "parentId": 1,
             "parentIds": null,
             "level": null,
             "orgTypeCode": 1,
             "leftId": null,
             "rightId": null,
             "sort": null
         },
         ...
     ]
     */
    public static final String URL_MAIN_QUERYORG = URL_MAIN + "queryOrg.do";

    /**
     * 查询某集团下所有的公司信息
     * orgId=4
     * curl -X POST --data "orgId=4" http://192.168.1.193:8081/jsbi/main/queryOrgWithParam.do
     [
         {
             "orgId": 68,
             "orgCode": "zjjyah",
             "orgName": "浙江佳源安徽房地产开发有限公司",
             "parentId": 4,
             "parentIds": null,
             "level": null,
             "orgTypeCode": 2,
             "leftId": null,
             "rightId": null,
             "sort": null
         },
         ...
     ]
     */
    public static final String URL_MAIN_QUERYORGWITHPARAM = URL_MAIN + "queryOrgWithParam.do";

    /**
     * 查询某公司下所有的项目信息
     * compId=14
     * curl -X POST --data "compId=1&itemId=1" http://192.168.1.193:8081/jsbi/main/queryItemByCompId.do
     [
         {
             "itemId": 1,
             "itemCode": "txwnsgc",
             "itemName": "桐乡威尼斯广场",
             "itemMemo": null
         },
         {
             "itemId": 2,
             "itemCode": "txzqmrgjxq",
             "itemName": "桐乡洲泉名人国际小区",
             "itemMemo": null
         }
     ]
      */
    public static final String URL_QUERYITEMBYCOMPID = URL_MAIN + "queryItemByCompId.do";

    /**
     * 查询自有物业下的所有业态信息
     *  curl http://192.168.1.193:8081/jsbi/main/queryOwnProperty.do
     [
         {
             "id": 2,
             "code": "house",
             "name": "住宅",
             "parentId": null
         },
         {
             "id": 3,
             "code": "club",
             "name": "会所",
             "parentId": null
         },
         {
             "id": 4,
             "code": "business",
             "name": "商业",
             "parentId": null
         },
         {
             "id": 5,
             "code": "office_room",
             "name": "办公用房",
             "parentId": null
         },
         {
             "id": 6,
             "code": "workshop",
             "name": "厂房",
             "parentId": null
         },
         {
             "id": 7,
             "code": "parking_lot",
             "name": "车位",
             "parentId": null
         }
     ]
     */
    public static final String URL_QUERYOWNPROPERTY = URL_MAIN + "queryOwnProperty.do";

    /**
     * 查询某集团下所有的项目信息
     * groupId=4
     * curl -X POST --data "groupId=4" http://192.168.1.193:8081/jsbi/main/queryItemsByGroupId.do
     [
         {
             "itemId": 51,
             "itemCode": "hfblds",
             "itemName": "合肥巴黎都市",
             "itemMemo": null
         },
         {
             "itemId": 52,
             "itemCode": "bbdfds",
             "itemName": "蚌埠东方都市",
             "itemMemo": null
         },
         ...
     ]
     */
    public static final String URL_QUERYITEMSBYGROUPID = URL_MAIN + "queryItemsByGroupId.do";




    public static final String KEY_ORGID   = "orgId";
    public static final String KEY_DATE    = "ym";
    public static final String KEY_LIMIT = "limit";
    public static final String KEY_OFFSET = "offset";
    public static final String KEY_ORDER = "order";
    public static final String KEY_SORT = "sort";

    //value of sort parameter

    public static final String GROUPNAME = "groupName";
    public static final String COMPNAME = "compName";



}
