package cn.damei.web.mobile.Manager;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class EnginInstallController {
	private static Logger logger = LoggerFactory.getLogger(EnginInstallController.class);


	@RequestMapping(value = "projectInstallMenu")
	public String projectInstallMenu(HttpServletRequest request) {

		String index = (String) request.getSession().getAttribute("index");
		request.getSession().removeAttribute("index");

		if (null != index) {
			if ("0".equals(index)) {

				return "mobile/modules/Manager/projectInstall/projectBuild";
			} else if ("1".equals(index)) {

				return "mobile/modules/Manager/manager_index";
			} else {
				logger.warn("安装申请路径参数index有误  index:" + index);
				return "mobile/modules/Manager/manager_index";
			}
		} else {
			logger.warn("安装申请路径参数为空");
			return "mobile/modules/Manager/projectInstall/projectBuild";
		}
	}



}
