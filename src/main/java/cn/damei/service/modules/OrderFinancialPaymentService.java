package cn.damei.service.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BizOrderReportConstantUtil;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.HttpConnection;
import cn.damei.common.utils.JsonUtils;
import cn.damei.common.utils.KeyAuthenticateUtils;
import cn.damei.common.utils.PicRootName;

import net.sf.json.JSONArray;


@Service
@Transactional(readOnly = true)
public class OrderFinancialPaymentService {

	private Logger logger =LoggerFactory.getLogger(QuarzUpdateOrderReportStatus.class);
    private String remoteUrl = "/IndustyOrderQueryAPI/QueryOrderInfo";
    

	public List<Map<String, String>> orderFinancialPaymentAjaxList(String storeId, String orderNumber,
			String customerName, String customerPhone) {
		

		Map<String,String> params=new HashMap<String,String>();

        if(null==storeId || ("").equals(storeId)){
        	storeId = "-1";
        }
        if(null==orderNumber || ("").equals(orderNumber)){
        	orderNumber = "-1";
        }
        if(null==customerName || ("").equals(customerName)){
        	customerName = "-1";
        }
        if(null==customerPhone || ("").equals(customerPhone)){
        	customerPhone = "-1";
        }
        
        params.put("storeId",storeId);
        params.put("orderNumber", orderNumber);
        params.put("customerName", customerName);
        params.put("customerPhone", customerPhone);
        
        String[] paramArr=new String[]{"storeId:"+storeId,"orderNumber:"+orderNumber,"customerName:"+customerName,"customerPhone:"+customerPhone};
        






        
        params.put("key", KeyAuthenticateUtils.getKey(paramArr, BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY));


        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        
        try {
            Map<String,Object> map =httpRequest(remoteUrl, params);

          if( null!= map.get("code")&& ("1").equals( map.get("code").toString() ) ){
                Object object = map.get("data");
                JSONArray jsonArray = JSONArray.fromObject(object);

                List<Map<String, Object>> mapListJson = (List<Map<String, Object>>) jsonArray;
                for (int i = 0; i < mapListJson.size(); i++) {
                    Map<String, Object> obj = mapListJson.get(i);


                    String storeNameParams  = (String) obj.get("storeName");

					String orderNumberParams  = (String) obj.get("orderNumber");

					String detailAddressParams  = (String) obj.get("detailAddress");

					String customerNameParams  = (String) obj.get("customerName");

					String customerPhoneParams  = (String) obj.get("customerPhone");

					String  contractTimeParams  = (String) obj.get("contractTime");

					String startMoneyParams  = (String) obj.get("startMoney");

					String secondMoneyParams  = (String) obj.get("secondMoney");

					String lastMoneyParams  = (String) obj.get("lastMoney");
					
					
					
                    Map<String, String> resultParams = new HashMap<String, String>();
                    
                    resultParams.put("storeNameParams",storeNameParams);
                    resultParams.put("orderNumberParams",orderNumberParams);
                    resultParams.put("detailAddressParams",detailAddressParams);
                    resultParams.put("customerNameParams",customerNameParams);
                    resultParams.put("customerPhoneParams",customerPhoneParams);
                    
                    if(null!=contractTimeParams && !contractTimeParams.equals("") && !contractTimeParams.equals("1980/1/1 0:00:00")){
                    	resultParams.put("contractTimeParams",DateFormatUtils.format(DateUtils.parseDate(contractTimeParams), "yyyy-MM-dd HH:mm:ss"));
                    }else{
                    	resultParams.put("contractTimeParams","");
                    }
                    
                    if(null!=startMoneyParams && !startMoneyParams.equals("") && !startMoneyParams.equals("1980/1/1 0:00:00")){
                    	resultParams.put("startMoneyParams",DateFormatUtils.format(DateUtils.parseDate(startMoneyParams), "yyyy-MM-dd HH:mm:ss"));
                    }else{
                    	resultParams.put("startMoneyParams","");
                    }
                    
                    if(null!=secondMoneyParams && !secondMoneyParams.equals("") && !secondMoneyParams.equals("1980/1/1 0:00:00")){
                    	resultParams.put("secondMoneyParams",DateFormatUtils.format(DateUtils.parseDate(secondMoneyParams), "yyyy-MM-dd HH:mm:ss"));
                    }else{
                    	resultParams.put("secondMoneyParams","");
                    }
                    
                    if(null!=lastMoneyParams && !lastMoneyParams.equals("") && !lastMoneyParams.equals("1980/1/1 0:00:00")){
                    	resultParams.put("lastMoneyParams",DateFormatUtils.format(DateUtils.parseDate(lastMoneyParams), "yyyy-MM-dd HH:mm:ss"));
                    }else{
                    	resultParams.put("lastMoneyParams","");
                    }
                    
                    
                    list.add(resultParams);
                }

            }
          
        }catch(Exception e){
            e.printStackTrace();

        }
        
		return list;
	}


    private Map<String,Object> httpRequest(String requestURL,Map<String,String> params){
        String post = null;
        try {
            post = HttpConnection.post(PicRootName.getConfigValue("remote_interface_url") + requestURL, params);
        }catch(Exception e){
            logger.error("财务收款2.0有效性验证接口调用异常！！！"+post);
            logger.error("接口地址："+requestURL);
            logger.error(e.getMessage());
        }


        return JsonUtils.parseJSON2Map(post);
    }
	
}