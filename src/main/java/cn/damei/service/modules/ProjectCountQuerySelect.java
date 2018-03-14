package cn.damei.service.modules;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="${adminPath}/projectselect")
public class ProjectCountQuerySelect {

	
	
	@RequestMapping(value="/findList")
	public String    queryList(){
		return "";
	}
}
