/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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

/**
 * 短信Service
 * @author qww
 * @version 2016-12-01
 */
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
	
	/**
	 * 发送短信
	 * @param receiveEmployeeId
	 * @param receivePhone
	 * @param msgContent
	 * @param businessType
	 * @param relatedBusinessIdInt
	 */
	@Transactional(readOnly = false)
	public void sendMessage(Integer receiveEmployeeId,String receivePhone,String msgContent,String businessType,Integer relatedBusinessIdInt) {
		
		BizPhoneMsg ddMsg = new BizPhoneMsg();
		ddMsg.setId(null);
		// 短信接收员工id
		ddMsg.setReceiveEmployeeId(receiveEmployeeId);
		// 短信接收手机号
		ddMsg.setReceivePhone(receivePhone);
		// 短信内容
		ddMsg.setMsgContent(msgContent);
		// 短信生成日期时间
		ddMsg.setMsgGenerateDatetime(new Date());
		// 期望发送日期时间
		ddMsg.setMsgTosendDatetime(null);
		// 实际发送日期时间
		ddMsg.setMsgSendedDatetime(null);
		// 短信状态 -- '0-待发送；1-发送成功；2-发送失败
		ddMsg.setMsgStatus(PhoneMessageTypeConstant.SEND_MSG_STATUS_0);
		// 关联业务类型
		ddMsg.setRelatedBusinessType(businessType);
		// 关联业务id整型
		ddMsg.setRelatedBusinessIdInt(relatedBusinessIdInt);
		// 关联业务id字符型
		ddMsg.setRelatedBusinessIdVarchar("");
		
		dao.insert(ddMsg);
	}

	/**
	 * 短信定时器发送短信【查询待发送的短信集合】
	 * @return
	 * @throws IOException
	 */
	public List<BizPhoneMsg> findPhoneMsgList() throws IOException {
		// 获取配置参数
		Properties props = new Properties();
		props.load(SentMsgQuartz.class.getClassLoader().getResourceAsStream("config.properties"));
		// 查询
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


	/**
	 * HTTPS版本短信
	 * @param url 接口
	 * @param userId 企业id
	 * @param userName 用户账号
	 * @param password 用户密码
	 * @param msgPhone 被叫号码
	 * @param msgContent 发送内容
	 * @return
	 */
	public String sendMessageHttps(String url, String userId, String userName, String password, String msgPhone, String msgContent) {

	    String status = "0";
		//时间戳
		SimpleDateFormat df=new SimpleDateFormat("MMddHHmmss");
		String stamp = df.format(new Date());
		//认证密文
		String secret= MD5.GetMD5Code(password+stamp).toUpperCase();
		//签名
		String suffix = "【大美装饰管理平台】";

		try {
			JSONObject j=new JSONObject();
			j.put("UserName", userName);//帐户名称
			j.put("Stamp", stamp);//时间戳
			j.put("Secret", secret);//认证密文
			j.put("Moblie", msgPhone);//手机号码
			j.put("Text", msgContent+suffix);//短信的内容
			j.put("Ext", "");//扩展号
			j.put("SendTime", "");//定时时间
			//获取json字符串，将json串用utf8转为字节数组
			String json=j.toString();
			byte[] data=json.getBytes("utf-8");
			//获取加密的key【Key 的生成方式，将Password转为字节数组，取前8位（不足8位，右侧补字节0）】
			byte[] key=password.getBytes();
			byte[] nkey=new byte[8];
			System.arraycopy(key, 0, nkey, 0, key.length > 8 ? 8 : key.length);
			//Des加密，base64转码
			String str=new BASE64Encoder().encode(DesHelper.encrypt(data, nkey));
			//发送http请求
			HttpClient client=new HttpClient();
			PostMethod post=new PostMethod(url);
			post.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			NameValuePair UserId=new NameValuePair("UserId",userId);
			NameValuePair Text64=new NameValuePair("Text64",str);
			post.setRequestBody(new NameValuePair[]{UserId,Text64});
			int statu=client.executeMethod(post);
			//返回结果
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