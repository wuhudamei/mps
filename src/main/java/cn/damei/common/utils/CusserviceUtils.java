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
	/**
	 * 
	 * @Title: sendHttp
	 * @Description: TODO
	 * @param @param cusId 售后系统的iD对应我们系统的 workOrderCode
	 * @param @param Status 售后系统需要的挡住状态
	 * @param @param url 售后的url
	 * @param @param bizCusServiceProblem 需要传到售后的个别字段例如预执行时间 可以为空当为空
	 * @param @return
	 * @return String
	 * @author ZhangTongWei
	 * @throws
	 */

	@SuppressWarnings("static-access")
	public static String sendHttp(String cusId, String Status, BizCusServiceProblem bizCusServiceProblem) {
		HttpClient httpClient = new DefaultHttpClient();
		// "http://192.168.1.90:8012/service/orderUpdate");
		// 准生产59.110.170.55:48016
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
				/* 读返回数据 */
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
		bizCusServiceProblem2.setKey(key);// 密钥
		bizCusServiceProblem2.setStatus(Status);// 状态
		bizCusServiceProblem2.setOperationUser("73"); // 操作人
		bizCusServiceProblem2.setWorkorderId(cusId); // 工单id/售后id
		bizCusServiceProblem2.setOperationTime(format);// 操作时间
		if (bizCusServiceProblem != null) {
			Date addDate = DateUtils.addDate(date, Integer.parseInt(bizCusServiceProblem.getBeforehandDatehou()));
			String formatDateTime = DateUtils.formatDateTime(addDate);
			bizCusServiceProblem2.setTreamentTime(formatDateTime);// 预处理时间
			bizCusServiceProblem2.setRemarks(bizCusServiceProblem.getRemarks());// 备注
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
