package cn.damei.Quartz;


import cn.damei.service.modules.CustomerServiceForQuartzService;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.HttpConnection;
import cn.damei.common.utils.KeyAuthenticateUtils2;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;




public class CustomerServiceQuartz {

    private final String RemoteUrl = "http://cm.mdni.net.cn/service/orderList";
    private final static String key = "7b5df6aq2we4r3t6y1vxnmhjklpewd23";
    private final static String dateFormatPattern = "yyyy-MM-dd HH:mm:ss";
    private Logger log = LoggerFactory.getLogger(CustomerServiceQuartz.class);
    private final static String liableDepartments = "103,120,107";
    private final static String liableCompanys = "100001,100002,100003,100004,100005";
    private final static String serverUrl = "http://cm.mdni.net.cn";


    @Autowired
    private CustomerServiceForQuartzService customerServiceForQuartzService;


    public void execute() throws IOException {


        Map<String, String> paraMap = new HashMap(12);
        Date date = new Date();
        Date startDate = DateUtils.addHours(date, -1);

        Date endDate = date;


        String start = DateUtils.formatDate(startDate, dateFormatPattern);
        String end = DateUtils.formatDate(endDate, dateFormatPattern);

        paraMap.put("startDate", start);
        paraMap.put("endDate", end);
        paraMap.put("liableDepartments", liableDepartments);
        paraMap.put("liableCompanys", liableCompanys);
        log.info("半小时一次售后信息定时接口远程抓取开始     ===============  开始时间:  " + start + "      结束时间:" + end);
        String[] parameterArr = new String[]{"startDate=" + start, "endDate=" + end, "liableDepartments=" + liableDepartments, "liableCompanys=" + liableCompanys};

        String myKey = KeyAuthenticateUtils2.getKey(parameterArr, key);


        paraMap.put("key", myKey);


        String callBackDataInfo = "";
        try {
            callBackDataInfo = HttpConnection.post(RemoteUrl, paraMap);

            log.info("售后信息定时接口远程数据 :  " + callBackDataInfo);

        } catch (UnsupportedEncodingException e) {

            log.warn("售后信息定时接口远程调用异常   :    错误信息: ");
            e.printStackTrace();
        }
        if (null != callBackDataInfo && !"".equals(callBackDataInfo.trim())) {

            JSONObject jsonObject = JSONObject.fromObject(callBackDataInfo);
            Map<String, Object> map = (Map) jsonObject;


            if ("1".equals(map.get("code")) && (boolean) map.get("success")) {

                Object object = map.get("data");
                log.info("售后信息定时接口远程数据:" + object);
                JSONArray jsonArray = JSONArray.fromObject(object);
                List<Map<String, Object>> mapListJson = (List<Map<String, Object>>) jsonArray;


                Integer mapListJsonSize = mapListJson.size();


                if (mapListJsonSize > 0) {
                    List<String> allCode = getAllCodeFromDataObject(mapListJson);
                    List<String> allExistCode = customerServiceDataIsExist(allCode);


                    if (null != allExistCode && allExistCode.size() > 0) {
                        allCode.removeAll(allExistCode);


                    }

                    List<Map<String, Object>> updateMapList = new ArrayList<>();
                    List<Map<String, Object>> insertMapList = new ArrayList<>();


                    for (int i = 0; i < mapListJsonSize; i++) {


                        Map<String, Object> mapInfo = new HashMap<>();
                        Map<String, Object> objectMap = mapListJson.get(i);


                        Map<String, Object> returnMapInfo = tranferMapToListMap(objectMap, mapInfo);

                        returnMapInfo.put("createDate", date);
                        if (allCode.contains(returnMapInfo.get("workOrderCode"))) {

                            insertMapList.add(returnMapInfo);


                        } else {
                            updateMapList.add(returnMapInfo);


                            update(returnMapInfo);
                        }


                    }

                    if (insertMapList.size() > 0) {
                        saveCustomerServiceData(insertMapList);


                    }






                    log.info("本次抓取售后数据完毕: 更新条数: ================" + updateMapList.size() + "============ 插入条数: ================" + insertMapList.size());
                    log.info("本次抓取售后数据完毕: 更新条数: ================" + updateMapList.size() + "============ 插入条数: ================" + insertMapList.size());
                    log.info("本次抓取售后数据完毕: 更新条数: ================" + updateMapList.size() + "============ 插入条数: ================" + insertMapList.size());





                }

            }

        } else {
            log.info("本次没有抓取到售后数据:  ===============  开始时间:  " + start + "      结束时间:" + end);



        }

    }



    public void saveCustomerServiceData(List<Map<String, Object>> mapList) {

        customerServiceForQuartzService.saveCustomerServiceData(mapList);
    }


    public void updateCustomerServiceData(List<Map<String, Object>> mapList) {
        customerServiceForQuartzService.updateCustomerServiceData(mapList);

    }


    public void update(Map<String, Object> map) {

        customerServiceForQuartzService.update(map);

    }


    public List<String> customerServiceDataIsExist(List<String> dataList) {




        return customerServiceForQuartzService.customerServiceDataIsExist(dataList);

    }


    public List<String> getAllCodeFromDataObject(List<Map<String, Object>> mapListJson) {
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < mapListJson.size(); i++) {


            Map<String, Object> objectMap = mapListJson.get(i);
            if (null != objectMap) {

                String workOrderCode = (String) objectMap.get("workOrderCode");

                list.add(workOrderCode);
            }


        }

        return list;
    }


    public Map<String, Object> tranferMapToListMap(Map<String, Object> objectMap, Map<String, Object> mapInfo) {

        if (null != objectMap) {


            Object companyName = objectMap.get("liableCompanyName");

            if (companyName == null) {
                companyName = "";

            }
            Object workOrderCode = objectMap.get("workOrderCode");


            Object customerName = objectMap.get("customerName");
            Object customerMobile = objectMap.get("customerMobile");

            if (customerMobile != null) {
                String a = (String) customerMobile;
                if (a.length() != 11) {

                    customerMobile = null;

                }

            }

            Object customerAddress = objectMap.get("customerAddress") == null ? "" : objectMap.get("customerAddress");
            ;









            Object problemCreateDate = objectMap.get("createDate") == null ? "" : objectMap.get("createDate");
            Object problem = objectMap.get("problem") == null ? "" : objectMap.get("problem");


            Object orderNo = objectMap.get("orderNo") == null ? "" : objectMap.get("orderNo");


            Object departMentObject = objectMap.get("liableDepartment");
            Object departId = null;
            if (departMentObject != null) {
                Map<String, Object> departMap = (Map) departMentObject;
                departId = String.valueOf(departMap.get("id"));
            }






















            Object importantDegree1Object = objectMap.get("importantDegree1");


            Object importantDegree1Id = null;

            if (importantDegree1Object != null) {
                Map<String, Object> importantDegree1Map = (Map) importantDegree1Object;
                importantDegree1Id = importantDegree1Map.get("id");

            }












            Object complaintTypeObject = objectMap.get("complaintType");
           Object  complaintTypeName =null;
            if (null!=complaintTypeObject) {

                Map<String, Object> complaintTypeObjectMap = (Map) complaintTypeObject;
                complaintTypeName = String.valueOf(complaintTypeObjectMap.get("name"));
            }




            Object questionType1Object = objectMap.get("questionType1");
            String questionType1ObjectMapName = null;
            if (null != questionType1Object) {
                Map<String, Object> questionType1ObjectMap = (Map) questionType1Object;
                questionType1ObjectMapName = (String) questionType1ObjectMap.get("name");
            }


            Object questionType2Object = objectMap.get("questionType2");

            String questionType2ObjectMapName = null;
            if (null != questionType1Object) {

                Map<String, Object> questionType2ObjectMap = (Map) questionType2Object;

                questionType2ObjectMapName = (String) questionType2ObjectMap.get("name");


            }

            Object sourceObject = objectMap.get("source");
            String sourceObjectName = null;
            if (null != sourceObject) {

                Map<String, Object> sourceObjectMap = (Map) sourceObject;

                sourceObjectName = (String) sourceObjectMap.get("name");

            }


            Object photo = "";
            try {

                photo = objectMap.get("photo");
            } catch (Exception e) {

                e.printStackTrace();


            }


            if (null != photo && !"".equals(photo.toString().trim())) {

                String photos[] = photo.toString().split(",");

                if (photos.length == 1) {

                    photo = serverUrl + photos[0];

                } else {
                    StringBuilder sb = new StringBuilder();
                    for (int v = 0; v < photos.length; v++) {

                        if(v==photos.length-1){

                            sb.append(serverUrl + photos[v]);
                        }else{

                            sb.append(serverUrl + photos[v]+",");
                        }


                    }
                    photo = sb.toString();


                }


            }

            mapInfo.put("companyName", companyName);
            mapInfo.put("workOrderCode", workOrderCode);
            mapInfo.put("customerName", customerName);
            mapInfo.put("customerMobile", customerMobile);


            mapInfo.put("customerAddress", customerAddress);








            mapInfo.put("problemCreateDate", problemCreateDate);
            mapInfo.put("problem", problem);


            mapInfo.put("departId", departId);











            mapInfo.put("importantDegree1Id", importantDegree1Id);
            mapInfo.put("complaintTypeName", complaintTypeName);


            mapInfo.put("questionType1ObjectMapName", questionType1ObjectMapName);
            mapInfo.put("questionType2ObjectMapName", questionType2ObjectMapName);
            mapInfo.put("sourceObjectName", sourceObjectName);
            mapInfo.put("orderNo", orderNo);




            mapInfo.put("photo", photo);

        }

        return mapInfo;

    }


    public void execute2(HttpServletRequest request, HttpServletResponse response, Integer hour) throws IOException {


        Map<String, String> paraMap = new HashMap();
        Date date = new Date();
        Date startDate = DateUtils.addHours(date, null != hour ? hour : -1);

        Date endDate = date;


        String start = DateUtils.formatDate(startDate, dateFormatPattern);
        String end = DateUtils.formatDate(endDate, dateFormatPattern);

        paraMap.put("startDate", start);
        paraMap.put("endDate", end);
        paraMap.put("liableDepartments", liableDepartments);
        paraMap.put("liableCompanys", liableCompanys);
        log.info("半小时一次售后信息定时接口远程抓取开始     ===============  开始时间:  " + start + "      结束时间:" + end);
        String[] parameterArr = new String[]{"startDate=" + start, "endDate=" + end, "liableDepartments=" + liableDepartments, "liableCompanys=" + liableCompanys};

        String myKey = KeyAuthenticateUtils2.getKey(parameterArr, key);


        paraMap.put("key", myKey);


        String callBackDataInfo = "";
        try {
            callBackDataInfo = HttpConnection.post(RemoteUrl, paraMap);

            log.info("售后信息定时接口远程数据 :  " + callBackDataInfo);

        } catch (UnsupportedEncodingException e) {

            log.warn("售后信息定时接口远程调用异常   :    错误信息: ");
            e.printStackTrace();
        }
        if (null != callBackDataInfo && !"".equals(callBackDataInfo.trim())) {

            JSONObject jsonObject = JSONObject.fromObject(callBackDataInfo);
            Map<String, Object> map = (Map) jsonObject;


            if ("1".equals(map.get("code")) && (boolean) map.get("success")) {

                Object object = map.get("data");
                log.info("售后信息定时接口远程数据:" + object);
                JSONArray jsonArray = JSONArray.fromObject(object);
                List<Map<String, Object>> mapListJson = (List<Map<String, Object>>) jsonArray;


                Integer mapListJsonSize = mapListJson.size();


                if (mapListJsonSize > 0) {
                    List<String> allCode = getAllCodeFromDataObject(mapListJson);
                    List<String> allExistCode = customerServiceDataIsExist(allCode);


                    if (null != allExistCode && allExistCode.size() > 0) {
                        allCode.removeAll(allExistCode);


                    }

                    List<Map<String, Object>> updateMapList = new ArrayList<>();
                    List<Map<String, Object>> insertMapList = new ArrayList<>();


                    for (int i = 0; i < mapListJsonSize; i++) {


                        Map<String, Object> mapInfo = new HashMap<>();
                        Map<String, Object> objectMap = mapListJson.get(i);


                        Map<String, Object> returnMapInfo = tranferMapToListMap(objectMap, mapInfo);

                        returnMapInfo.put("createDate", date);
                        returnMapInfo.put("del_flag", "0");
                        if (allCode.contains(returnMapInfo.get("workOrderCode"))) {

                            insertMapList.add(returnMapInfo);


                        } else {
                            updateMapList.add(returnMapInfo);


                            update(returnMapInfo);
                        }


                    }

                     if (insertMapList.size() > 0) {
                        saveCustomerServiceData(insertMapList);


                    }





                    response.setHeader("Content-type", "text/html;charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("本次抓取售后数据完毕: 更新条数: ================" + updateMapList.size() + "============ 插入条数: ================" + insertMapList.size());
                    log.info("本次抓取售后数据完毕: 更新条数: ================" + updateMapList.size() + "============ 插入条数: ================" + insertMapList.size());
                    log.info("本次抓取售后数据完毕: 更新条数: ================" + updateMapList.size() + "============ 插入条数: ================" + insertMapList.size());
                    log.info("本次抓取售后数据完毕: 更新条数: ================" + updateMapList.size() + "============ 插入条数: ================" + insertMapList.size());





                }

            }

        } else {
            response.getWriter().write("本次没有抓取到售后数据:  ===============  开始时间:  " + start + "      结束时间:" + end);


            log.info("本次没有抓取到售后数据:  ===============  开始时间:  " + startDate + "      结束时间:" + endDate);

        }

    }

}
