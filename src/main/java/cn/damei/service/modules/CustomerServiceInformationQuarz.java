package cn.damei.service.modules;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.dao.modules.CustomerServiceInformationDao;
import cn.damei.common.constantUtils.BizOrderReportConstantUtil;
import cn.damei.common.utils.HttpConnection;
import cn.damei.common.utils.JsonUtils;
import cn.damei.common.utils.KeyAuthenticateUtils;
import cn.damei.common.utils.PicRootName;

@Service
public class CustomerServiceInformationQuarz {

	Logger logger = Logger.getLogger(CustomerServiceInformationQuarz.class); 
	
	@Autowired
	private CustomerServiceInformationDao customerServiceInformationDao;

	private String [] storeId;
	@Transactional
	public   void  execute(){
		String url = "";
		try{
			url = PicRootName.getConfigValue("remote_interface_url") +"/CustomerAPI/GetAllCustomerServiceInfo";
		}catch(Exception e){
			e.printStackTrace();
		}
		
		logger.info("客服同步定时任务：执行......begin......");
		for (int i = 0; i < storeId.length; i++) {


			logger.info("客服同步定时任务：执行中............");
			
			Map<String,String> params=new HashMap<String,String>();
			params.put("storeId", storeId[i]);
			String timeStamp=String.valueOf(new Date().getTime());
			params.put("timeStamp", timeStamp);
			String appoIntKey=BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY;
			String post="";
			try {
				String[] paramArr=new String[]{"storeId:"+storeId[i],"timeStamp:"+timeStamp};
				params.put("key", KeyAuthenticateUtils.getKey(paramArr, appoIntKey));
				 post = HttpConnection.post(url, params);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			Map<String, Object> parseJSON2Map = JsonUtils.parseJSON2Map(post);

			if(1==((Integer)parseJSON2Map.get("code"))){
				List<Map<String,Object>> o = (List<Map<String, Object>>) parseJSON2Map.get("data");
				for (Map<String, Object> map : o) {

					if("0".equals(map.get("isLeave"))){
						logger.info("客服同步定时任务：执行......更新......");
						customerServiceInformationDao.updateOne(map);
					}
				}
			}
				
		}
		logger.info("客服同步定时任务：执行......end......");
	}

	public String[] getStoreId() {
		return storeId;
	}

	public void setStoreId(String[] storeId) {
		this.storeId = storeId;
	}
	
}
