
package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.web.BaseController;
import cn.damei.common.utils.WiexinSignUtil;


@Controller
@RequestMapping(value = "${frontPath}/weixin")
public class WeixinController extends BaseController {


	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public String get(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request) {
		
		System.out.println("=============================================== get start");
		for (Object o : request.getParameterMap().keySet()){
			System.out.println(o + " = " + request.getParameter((String)o));
		}
		System.out.println("=============================================== get end");
		

        if (WiexinSignUtil.checkSignature(signature, timestamp, nonce)) {  
        	return echostr;
        }

		return "";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public String post(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request) {
		System.out.println("=============================================== post start");
		for (Object o : request.getParameterMap().keySet()){
			System.out.println(o + " = " + request.getParameter((String)o));
		}
		System.out.println("=============================================== post end");
		StringBuilder result = new StringBuilder();
		result.append("<xml>" +
				"<ToUserName><![CDATA[toUser]]></ToUserName>" +
				"<FromUserName><![CDATA[fromUser]]></FromUserName>" +
				"<CreateTime>12345678</CreateTime>" +
				"<MsgType><![CDATA[text]]></MsgType>" +
				"<Content><![CDATA[你好]]></Content>" +
				"</xml>");
		return result.toString();
	}
	
	
}
