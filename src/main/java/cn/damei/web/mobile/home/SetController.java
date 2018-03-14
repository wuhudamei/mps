package cn.damei.web.mobile.home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="${adminPath}/app/home")
public class SetController {

	
	@RequestMapping(value="setPage")
	public String setPage(Model model,HttpServletRequest request){
		String client = (String) request.getSession().getAttribute("client");
		if("wechat".equals(client)){
			return "mobile/modules/home/setIndex2";
		}else{
			return "mobile/modules/home/setIndex";
		}
	}
}
