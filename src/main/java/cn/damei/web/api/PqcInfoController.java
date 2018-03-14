package cn.damei.web.api;

import cn.damei.Quartz.CustomerServiceQuartz;
import cn.damei.common.constantUtils.BizOrderReportConstantUtil;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.KeyAuthenticateUtils2;
import cn.damei.entity.modules.EngineDepartSyntheticQueryEntity;
import cn.damei.service.modules.EngineDepartSyntheticQueryService;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by joseph on 2017/6/8.
 * 每次用户访问统计报表信息时访问此接口返回每日统计信息
 * 接口提供方
 */

@Controller
@RequestMapping(value = "${adminPath}/api/pqcStatisticsInfo")
public class PqcInfoController {
    @Autowired
    private EngineDepartSyntheticQueryService dateService;

    private EngineDepartSyntheticQueryEntity entity;

    @Autowired
    private CustomerServiceQuartz customerServiceQuartz;
    private String successCode = "1";
    private String failureCode = "0";
    private String message = "";
    private String data = "";


    @RequestMapping(value = "aaa")
    public void aaa(HttpServletRequest request, HttpServletResponse response,Integer hour) throws IOException {
        customerServiceQuartz.execute2(request,response,hour);



    }

    @RequestMapping(value = "getPqcStatisticsInfo")
    @ResponseBody
    public JSONObject getPqcStatisticsInfo(String storeId, String projectMode, String startTime, String endTime, String key) {
        Map<String, Object> dataMap = new HashMap();


        if (null == entity) {

            entity = new EngineDepartSyntheticQueryEntity();
        }

        if (StringUtils.isEmpty(projectMode) || StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime) || StringUtils.isEmpty(key)) {

            message ="必要参数为空";

            dataMap.put("data", data);
            dataMap.put("code", failureCode);
            dataMap.put("message", message);


            JSONObject jsonObject = JSONObject.fromObject(dataMap);


            return jsonObject;
        }
        if (!PqcInfoController.isValidDate(startTime) || !PqcInfoController.isValidDate(endTime)) {

            message = "开始日期或结束日期不合法";
            dataMap.put("data", data);
            dataMap.put("code", failureCode);
            dataMap.put("message", message);

            JSONObject jsonObject = JSONObject.fromObject(dataMap);


            return jsonObject;

        }


        if (DateUtils.parseDate(endTime).getTime() < DateUtils.parseDate(startTime).getTime()) {

            message = "结束日期小于开始日期";
            dataMap.put("data", data);
            dataMap.put("code", failureCode);
            dataMap.put("message", message);

            JSONObject jsonObject = JSONObject.fromObject(dataMap);


            return jsonObject;
        }
        if (!PqcInfoController.isNumeric(projectMode)) {
            message = "工程模式格式不合法";
            dataMap.put("data", data);
            dataMap.put("code", failureCode);
            dataMap.put("message", message);

            JSONObject jsonObject = JSONObject.fromObject(dataMap);


            return jsonObject;


        }
        Map<String, Object> parameterMap = new HashMap<>();
        String[] paramArr = null;
        if (StringUtils.isEmpty(storeId)) {
            paramArr = new String[]{"projectMode=" + projectMode, "startTime=" + startTime, "endTime=" + endTime};

        } else {
            if (!PqcInfoController.isNumeric(storeId)) {
                message = "门店信息格式不合法";
                dataMap.put("data", data);
                dataMap.put("code", failureCode);
                dataMap.put("message", message);

                JSONObject jsonObject = JSONObject.fromObject(dataMap);


                return jsonObject;


            }


            parameterMap.put("storeId", storeId);
            paramArr = new String[]{"storeId=" + storeId, "projectMode=" + projectMode, "startTime=" + startTime, "endTime=" + endTime};

        }

        String myKey = KeyAuthenticateUtils2.getKey(paramArr, BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);

        if (key.equals(myKey)) {
            //key验证有效
            int daysDiff = 0;

            try {
                daysDiff = (int) DateUtils.getDistanceOfTwoDate(DateUtils.parseDate(startTime, "yyyy-MM-dd HH:mm:ss"), DateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm:ss"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (daysDiff >= 0) {
                parameterMap.put("projectMode", projectMode);
                List<Map<String, Object>> resultMapList = new ArrayList<>();
                for (int v = -1; v < daysDiff; v++) {



                    Map<String, Object> data = new HashMap<>();

                    //如果是同一天的不同小时
                    if(daysDiff==0){
                        parameterMap.put("start",startTime);
                        parameterMap.put("end",endTime);
                        List<Map<String, Object>> dataMapList = dateService. pqcSyntheticDataInfoForSameDay(parameterMap);

                        data.put("dataInfo", dataMapList);
                        data.put("date", startTime);
                        resultMapList.add(data);
                    }else{

                        if (daysDiff - v == 1) {

                            parameterMap.put("pqcQueryDate", endTime);
                            data.put("date", endTime);

                        } else {
                            parameterMap.put("pqcQueryDate", startTime);
                            data.put("date", startTime);

                        }


                        List<Map<String, Object>> dataMapList = dateService.pqcSyntheticDataInfo(parameterMap);


                        data.put("dataInfo", dataMapList);

                        resultMapList.add(data);


                        startTime = DateUtils.formatDate(DateUtils.addDate(DateUtils.parseDate(startTime), 1));


                    }



                }


                message ="调用成功";
                dataMap.put("data", resultMapList);
                dataMap.put("message", message);
                dataMap.put("code", successCode);
                JSONObject jsonObject = JSONObject.fromObject(dataMap);


                return jsonObject;
            } else {

                message ="时间相差有误:";

                dataMap.put("data", data);
                dataMap.put("code", failureCode);
                dataMap.put("message", message);

                JSONObject jsonObject = JSONObject.fromObject(dataMap);


                return jsonObject;
            }


        } else {
            //key 验证失败
            message = "key验证失败:";

            dataMap.put("data", data);
            dataMap.put("code", failureCode);
            dataMap.put("message", message);

            JSONObject jsonObject = JSONObject.fromObject(dataMap);


            return jsonObject;
        }

    }


    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
