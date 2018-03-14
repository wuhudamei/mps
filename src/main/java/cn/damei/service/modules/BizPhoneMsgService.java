
package cn.damei.service.modules;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.damei.Quartz.SentMsgQuartz;
import cn.damei.common.utils.DesHelper;
import cn.damei.common.utils.MD5;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.phoneMessgeTypeConstant.PhoneMessageTypeConstant;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.dao.modules.BizPhoneMsgDao;
import sun.misc.BASE64Encoder;


@Service
@Transactional(readOnly = true)
public class BizPhoneMsgService extends CrudService2<BizPhoneMsgDao, BizPhoneMsg> {

	private Logger log = LoggerFactory.getLogger(BizPhoneMsgService.class);

	public BizPhoneMsg get(Integer id) {
		return super.get(id);
	}
	
	public List<BizPhoneMsg> findList(BizPhoneMsg bizPhoneMsg) {
		return super.findList(bizPhoneMsg);
	}
	
	public Page<BizPhoneMsg> findPage(Page<BizPhoneMsg> page, BizPhoneMsg bizPhoneMsg) {
		return super.findPage(page, bizPhoneMsg);
	}
	
	@Transactional(readOnly = false)
	public void save(BizPhoneMsg bizPhoneMsg) {
		super.save(bizPhoneMsg);
	}

	@Transactional(readOnly = false)
	public void insert(BizPhoneMsg bizPhoneMsg) {
		if(bizPhoneMsg.getId() != null){
			bizPhoneMsg.setId(null);
		}
		dao.insert(bizPhoneMsg);
	}

	@Transactional(readOnly = false)
	public void update(BizPhoneMsg bizPhoneMsg) {
		dao.update(bizPhoneMsg);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPhoneMsg bizPhoneMsg) {
		super.delete(bizPhoneMsg);
	}

	public List<BizPhoneMsg> queryPhoneMsgList(Map<String, Object> map){
		return dao.queryPhoneMsgList(map);
	}
	

	@Transactional(readOnly = false)
	public void sendMessage(Integer receiveEmployeeId,String receivePhone,String msgContent,String businessType,Integer relatedBusinessIdInt) {
		
		BizPhoneMsg ddMsg = new BizPhoneMsg();
		ddMsg.setId(null);

		ddMsg.setReceiveEmployeeId(receiveEmployeeId);

		ddMsg.setReceivePhone(receivePhone);

		ddMsg.setMsgContent(msgContent);

		ddMsg.setMsgGenerateDatetime(new Date());

		ddMsg.setMsgTosendDatetime(null);

		ddMsg.setMsgSendedDatetime(null);

		ddMsg.setMsgStatus(PhoneMessageTypeConstant.SEND_MSG_STATUS_0);

		ddMsg.setRelatedBusinessType(businessType);

		ddMsg.setRelatedBusinessIdInt(relatedBusinessIdInt);

		ddMsg.setRelatedBusinessIdVarchar("");
		
		dao.insert(ddMsg);
	}


	public List<BizPhoneMsg> findPhoneMsgList() throws IOException {

		Properties props = new Properties();
		props.load(SentMsgQuartz.class.getClassLoader().getResourceAsStream("config.properties"));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msgStatus0", props.getProperty("msg_status_0"));
		map.put("msgStatus2", props.getProperty("msg_status_2"));
		map.put("msgTosendDatetimeDiffMin0", Integer.parseInt(props.getProperty("msg_tosend_datetime_diff_min_0")));
		map.put("msgTosendDatetimeDiffMax0", Integer.parseInt(props.getProperty("msg_tosend_datetime_diff_max_0")));
		map.put("msgTosendDatetimeDiffMin2", Integer.parseInt(props.getProperty("msg_tosend_datetime_diff_min_2")));
		map.put("msgTosendDatetimeDiffMax2", Integer.parseInt(props.getProperty("msg_tosend_datetime_diff_max_2")));
		map.put("msgTosendDatetimeDiffSecond", props.get("msg_tosend_datetime_diff_second"));
		map.put("msgTosendDatetimeDiffHour", props.get("msg_tosend_datetime_diff_hour"));
		map.put("date", new Date());
		return dao.queryPhoneMsgList(map);
	}



	public String sendMessageHttps(String url, String userId, String userName, String password, String msgPhone, String msgContent) {

	    String status = "0";

		SimpleDateFormat df=new SimpleDateFormat("MMddHHmmss");
		String stamp = df.format(new Date());

		String secret= MD5.GetMD5Code(password+stamp).toUpperCase();

		String suffix = "【大美装饰管理平台】";

		try {
			JSONObject j=new JSONObject();
			j.put("UserName", userName);
			j.put("Stamp", stamp);
			j.put("Secret", secret);
			j.put("Moblie", msgPhone);
			j.put("Text", msgContent+suffix);
			j.put("Ext", "");
			j.put("SendTime", "");

			String json=j.toString();
			byte[] data=json.getBytes("utf-8");

			byte[] key=password.getBytes();
			byte[] nkey=new byte[8];
			System.arraycopy(key, 0, nkey, 0, key.length > 8 ? 8 : key.length);

			String str=new BASE64Encoder().encode(DesHelper.encrypt(data, nkey));

			HttpClient client=new HttpClient();
			PostMethod post=new PostMethod(url);
			post.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			NameValuePair UserId=new NameValuePair("UserId",userId);
			NameValuePair Text64=new NameValuePair("Text64",str);
			post.setRequestBody(new NameValuePair[]{UserId,Text64});
			int statu=client.executeMethod(post);

			String result=post.getResponseBodyAsString();
			JSONObject jsonObj = JSONObject.fromObject(result);
			status = jsonObj.getString("StatusCode");
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}

		if(!"1".equals(status)){
			log.error("==========短信接口 异常==============================================================");
			log.error("==========短信接口: 状态返回值:"+ status +" 手机号:"+ msgPhone + " 短信内容:" + msgContent+suffix+"===========");
			log.error("==========短信接口 异常==============================================================");
		}
		return status;
	}

}