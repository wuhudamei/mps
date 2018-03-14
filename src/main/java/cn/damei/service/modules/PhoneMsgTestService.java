/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.DesHelper;
import cn.damei.common.utils.MD5;
import cn.damei.dao.modules.BizPhoneMsgDao;
import cn.damei.entity.modules.BizPhoneMsg;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 短信https 接口测试 Service
 */
@Service
@Transactional(readOnly = true)
public class PhoneMsgTestService extends CrudService2<BizPhoneMsgDao, BizPhoneMsg> {

	private Logger log = LoggerFactory.getLogger(PhoneMsgTestService.class);

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
		String suffix = "【美得你】";

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

			log.info("==========短信接口==============================================================");
			log.info("==========短信接口调用: result结果:"+ result +"===========");
			log.info("==========短信接口调用: 状态返回值:"+ status +"===========");
			log.info("==========短信接口调用: 手机号:"+ msgPhone +"===========");
			log.info("==========短信接口调用: 短信内容:"+ msgContent+suffix +"===========");
			log.info("==========短信接口==============================================================");

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