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

/**
 * 上报复尺新版(20161107-20161113)
 * @author llp 
 * 2016-11-15
 */
@Controller
@RequestMapping(value="${adminPath}/app/manager")
public class NewChecksizeController {
	private static Logger logger = LoggerFactory.getLogger(NewChecksizeController.class);
	@Autowired
	private NewChecksizeService newChecksizeService;
	@Autowired
	private DictTypeService dictTypeService;

	/**
	 * 上报复尺list
	 */
	@RequestMapping(value={"newChecksizeList",""})
	public String list(HttpServletRequest request,Model model) throws IOException {
		// 获取项目经理
		Manager manager = SessionUtils.getManagerSession(request);
		logger.info("获取当前项目经理的编号："+manager.getId()+"\t项目经理名字："+manager.getRealname()
		+"\t手机号："+manager.getPhone());
		
		//根据当前项目经理ID查询所有的订单List
		List<NewChecksizeOrder> orderList = newChecksizeService.queryList(manager.getId());
		
		//从字段表获取复尺内容值List
		List<DictType> dictList = dictTypeService.queryListByType(PicRootName.getConfigValue(
				ConstantUtils.COMPLEX_CONTENT_MAP));
		
		model.addAttribute("orderList", orderList);
		model.addAttribute("dictList", dictList);
		return "mobile/modules/Manager/progressMain/newChecksize/list";
	}
}
