
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


@Service
@Transactional(readOnly = true)
public class PhoneMsgTestService extends CrudService2<BizPhoneMsgDao, BizPhoneMsg> {

	private Logger log = LoggerFactory.getLogger(PhoneMsgTestService.class);


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