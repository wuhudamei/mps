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


	@RequestMapping(value = "receiveJsonDate")
	@ResponseBody
	public String receiveJsonData(ReciveJson reciveJson, HttpServletRequest request, HttpServletResponse response,
                                  Model model) throws IOException {
		return bs.receiveJsonData(reciveJson);
	}

	@RequestMapping(value = "receiveJsonStr")
	@ResponseBody
	public String receiveJsonStr(String reciveJson) throws IOException {
		return bs.receiveJsonStr(reciveJson);
	}
	
	
	


	public void sendJSONDate() throws UnsupportedEncodingException {

		List<BizSynDataCnfg> findSendJsonDate = bs.findSendJsonDate();

		String url;
		for (BizSynDataCnfg bizSynDataCnfg : findSendJsonDate) {

			String id = bizSynDataCnfg.getId();

			String businessType = bizSynDataCnfg.getBusinessType();

			String businessDate = bizSynDataCnfg.getBusinessData();
			


				if (businessDate.charAt(0) != '[') {
					businessDate = "[" + businessDate + "]";
				}

				JSONArray fromObject1 = JSONArray.fromObject(businessDate);
				JSONObject jsonObject = fromObject1.getJSONObject(0);

				url = bs.findBizSynDateConf(businessType);
				if (url != null && !url.equals("")) {

					Map<String, String> map = new HashMap<String,String>();
				    map.put("requestKey", jsonObject.toString());
				    String string2 = null;
				   try {
					   	String sendPost = HttpConnection.post(url, map);

						JSONObject fromObject = JSONObject.fromObject(sendPost);

						string2 = fromObject.getString("code");
						if (string2.equals("0")) {
							string2 = ConstantUtils.SYN_STATUS_2;
						} else {
							string2 = ConstantUtils.SYN_STATUS_1;
						}
					} catch (Exception e) {
						string2 = ConstantUtils.SYN_STATUS_5;
					}finally {

						bs.updateBizSynDate(id, new Date(), string2);
					}
				}
			}
		}
	}



