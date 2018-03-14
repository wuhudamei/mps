package cn.damei.web.mobile.Manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.DictType;
import cn.damei.entity.mobile.Manager.NewChecksizeOrder;
import cn.damei.service.mobile.Manager.DictTypeService;
import cn.damei.service.mobile.Manager.NewChecksizeService;
import cn.damei.entity.mobile.Manager.Manager;


@Controller
@RequestMapping(value="${adminPath}/app/manager")
public class NewChecksizeController {
	private static Logger logger = LoggerFactory.getLogger(NewChecksizeController.class);
	@Autowired
	private NewChecksizeService newChecksizeService;
	@Autowired
	private DictTypeService dictTypeService;


	@RequestMapping(value={"newChecksizeList",""})
	public String list(HttpServletRequest request,Model model) throws IOException {

		Manager manager = SessionUtils.getManagerSession(request);
		logger.info("获取当前项目经理的编号："+manager.getId()+"\t项目经理名字："+manager.getRealname()
		+"\t手机号："+manager.getPhone());
		

		List<NewChecksizeOrder> orderList = newChecksizeService.queryList(manager.getId());
		

		List<DictType> dictList = dictTypeService.queryListByType(PicRootName.getConfigValue(
				ConstantUtils.COMPLEX_CONTENT_MAP));
		
		model.addAttribute("orderList", orderList);
		model.addAttribute("dictList", dictList);
		return "mobile/modules/Manager/progressMain/newChecksize/list";
	}
}
