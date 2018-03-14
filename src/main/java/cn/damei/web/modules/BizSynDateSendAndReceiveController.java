package cn.damei.web.modules;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.HttpConnection;
import cn.damei.entity.modules.BizSynDataCnfg;
import cn.damei.entity.modules.ReciveJson;
import cn.damei.service.modules.BizSynDateSendAndReceiveService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "${adminPath}/api/BizSynDateSendAndReceive")
public class BizSynDateSendAndReceiveController {

	@Autowired
	private BizSynDateSendAndReceiveService bs;

	/**
	 * 接收json数据
	 * 
	 * @param reciveJson
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "receiveJsonDate")
	@ResponseBody
	public String receiveJsonData(ReciveJson reciveJson, HttpServletRequest request, HttpServletResponse response,
                                  Model model) throws IOException {
		return bs.receiveJsonData(reciveJson);
	}
	/**
	 * 接收json数据
	 * 
	 * @param receiveJsonStr
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "receiveJsonStr")
	@ResponseBody
	public String receiveJsonStr(String reciveJson) throws IOException {
		return bs.receiveJsonStr(reciveJson);
	}
	
	
	
	/** 
	* @Description: 请求订单流转系统
	* @param @throws UnsupportedEncodingException
	* @author zkj 
	* @date 2017年10月25日 上午11:40:52 
	*/
	/*@RequestMapping(value = "sendJSONDate")
	@ResponseBody*/
	public void sendJSONDate() throws UnsupportedEncodingException {
		// 查询需要发送的数据，ID和json数据
		List<BizSynDataCnfg> findSendJsonDate = bs.findSendJsonDate();
		// 遍历
		String url;
		for (BizSynDataCnfg bizSynDataCnfg : findSendJsonDate) {
			// 获取ID
			String id = bizSynDataCnfg.getId();
			// 获取业务类型
			String businessType = bizSynDataCnfg.getBusinessType();
			// 需要发送的数据
			String businessDate = bizSynDataCnfg.getBusinessData();
			
			/*if (businessType.equals("505")) {
				businessDate = businessDate.substring(1,businessDate.length()-1);
		        Map<String, String> map = new HashMap<String,String>();
		        map.put("IndustrialWorkersAuxiliaryKey", JSONObject.fromObject(businessDate).toString());
				String sendPost = HttpConnection.post(bs.findBizSynDateConf(businessType), map);
				sendPost = new String(sendPost.getBytes("GBK"), "UTF8");
				sendPost = "[" + sendPost + "]";
				// 转换成JSONArray对象
				JSONArray fromObject = JSONArray.fromObject(sendPost);
				// 获取状态码并判断，如果成功，更改状态和更新时间
				String string2 = fromObject.getJSONObject(0).getString("code");
				if (string2.equals("0")) {
					string2 = ConstantUtils.SYN_STATUS_2;// 失败
				} else {
					string2 = ConstantUtils.SYN_STATUS_1;// 已解析
				}
				// 更新数据状态
				bs.updateBizSynDate(id, new Date(), string2);
			} else {*/
				// 对需要发送的数据格式进行判断
				if (businessDate.charAt(0) != '[') {
					businessDate = "[" + businessDate + "]";
				}
				// 转换成JSONObject对象
				JSONArray fromObject1 = JSONArray.fromObject(businessDate);
				JSONObject jsonObject = fromObject1.getJSONObject(0);
				//根据业务类型查询url
				url = bs.findBizSynDateConf(businessType);
				if (url != null && !url.equals("")) {
					//发送POST请求
					Map<String, String> map = new HashMap<String,String>();
				    map.put("requestKey", jsonObject.toString());
				    String string2 = null;
				   try {
					   	String sendPost = HttpConnection.post(url, map);
						// 转换成JSONArray对象
						JSONObject fromObject = JSONObject.fromObject(sendPost);
						// 获取状态码并判断，如果成功，更改状态和更新时间
						string2 = fromObject.getString("code");
						if (string2.equals("0")) {
							string2 = ConstantUtils.SYN_STATUS_2;// 失败
						} else {
							string2 = ConstantUtils.SYN_STATUS_1;// 已解析
						}
					} catch (Exception e) {
						string2 = ConstantUtils.SYN_STATUS_5;// 失败
					}finally {
						// 更新数据状态
						bs.updateBizSynDate(id, new Date(), string2);
					}
				}
			}
		}
	}
/*}*/


