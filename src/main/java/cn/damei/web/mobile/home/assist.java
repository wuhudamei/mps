package cn.damei.web.mobile.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.config.Global;

@Controller
@RequestMapping(value="/mDJlSsBc")
public class assist {

	@RequestMapping(value="")
	public String   redirectHomeLogin(String micro){
		
		
		return "redirect:"+Global.getAdminPath()+"/app/home/toLogin?micro="+micro;
	}
}
