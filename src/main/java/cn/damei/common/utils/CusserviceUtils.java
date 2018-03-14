package cn.damei.common.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import cn.damei.common.mapper.JsonMapper;
import cn.damei.entity.modules.BizCusServiceProblem;

public class CusserviceUtils {


	@SuppressWarnings("static-access")
	public static String sendHttp(String cusId, String Status, BizCusServiceProblem bizCusServiceProblem) {
		HttpClient httpClient = new DefaultHttpClient();
		String smsUrl = "http://cm.mdni.net.cn/service/orderUpdate";
		HttpPost httppost = new HttpPost(smsUrl);
		String strResult = "";

		try {
			String json = getParameter(cusId, Status, bizCusServiceProblem);

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

			nameValuePairs.add(new BasicNameValuePair("result", json));
			httppost.addHeader("Content-type", "application/x-www-form-urlencoded");

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

			HttpResponse response1 = httpClient.execute(httppost);
			System.err.println(response1);
			if (response1.getStatusLine().getStatusCode() == 200) {

				String conResult = EntityUtils.toString(response1.getEntity());
				JSONObject sobj = new JSONObject();
				sobj = sobj.fromObject(conResult);
				String code = sobj.getString("code");
				String message = sobj.getString("message");
				if (code.equals("1")) {
					strResult += "SUCCESS";
				} else {
					strResult += "发送失败，" + message;
				}

			} else {
				String err = response1.getStatusLine().getStatusCode() + "";
				strResult += "发送失败:" + err;
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return strResult;

	}

	private static String getParameter(String cusId, String Status, BizCusServiceProblem bizCusServiceProblem) {
		BizCusServiceProblem bizCusServiceProblem2 = new BizCusServiceProblem();

		SimpleDateFormat operationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String format = operationTime.format(date);
		String[] parameterArr = new String[] { "status=" + Status, "operationUser=" + "73", "workorderId=" + cusId, "operationTime=" + format };
		String key = KeyAuthenticateUtils2.getKey(parameterArr, "7b5df6aq2we4r3t6y1vxnmhjklpewd23");
		bizCusServiceProblem2.setKey(key);
		bizCusServiceProblem2.setStatus(Status);
		bizCusServiceProblem2.setOperationUser("73");
		bizCusServiceProblem2.setWorkorderId(cusId);
		bizCusServiceProblem2.setOperationTime(format);
		if (bizCusServiceProblem != null) {
			Date addDate = DateUtils.addDate(date, Integer.parseInt(bizCusServiceProblem.getBeforehandDatehou()));
			String formatDateTime = DateUtils.formatDateTime(addDate);
			bizCusServiceProblem2.setTreamentTime(formatDateTime);
			bizCusServiceProblem2.setRemarks(bizCusServiceProblem.getRemarks());
		}

		JsonMapper jsonMapper = new JsonMapper();
		String json = jsonMapper.toJson(bizCusServiceProblem2);
		return json;
	}


	public static  String getsysOfficeId() {
		String  src =new String ();
		if (UserUtils.getUser().getStoreId() == null) {
			src="";
		} else {
			src =UserUtils.getUser().getStoreId();
		}
		return src;
	}
}
