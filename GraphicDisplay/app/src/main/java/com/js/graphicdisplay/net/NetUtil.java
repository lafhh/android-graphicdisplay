package com.js.graphicdisplay.net;

/**
 * Created by js_gg on 2017/6/27.
 */

public class NetUtil {
    public static final String IP        = "192.168.1.193";
    public static final String PORT      = ":8081";
    public static final String HTTP_PREF = "http://" + IP + PORT;
    public static final String BASE_URL  = HTTP_PREF + "/jsbi/";

    public static final String URL_FUNDSTURNEDOVER = BASE_URL + "fundsTurnedOver/";
    public static final String URL_LANDBANKING     = BASE_URL + "landbanking/";

    public static final String URL_FUNDSTURNEDOVER_GROUP_TABLE  = URL_FUNDSTURNEDOVER + "getFundsTurnedOverForGroupTable.do";
    public static final String URL_FUNDSTURNEDOVER_COMP_TABLE   = URL_FUNDSTURNEDOVER + "getFundsTurnedOverForComTable.do";

    //http://192.168.1.193:8081/jsbi/fundsTurnedOver/getAllFundsTurnedOverForChart.do
    public static final String URL_FUNDSTURNEDOVER_ALL_CHART    = URL_FUNDSTURNEDOVER + "getAllFundsTurnedOverForChart.do";

    public static final String URL_LANDBANKING_QUERYITEM_CHART  = URL_LANDBANKING + "queryItemsForChart.do";
    public static final String URL_LANDBANKING_QUERYITEM        = URL_LANDBANKING + "queryItems.do";
    public static final String URL_LANDBANKING_QUERYITEM_COMP   = URL_LANDBANKING + "queryItemsForCom.do";

    public static final String POST_ORGID   = "orgId";
    public static final String POST_DATE    = "ym";

}
