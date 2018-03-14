package cn.damei.web.api;

import cn.damei.service.modules.ConstructionPlanStandBookService;
import cn.damei.common.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <dl>
 * <dd>描述:施工计划台账(用于远程调用)</dd>
 * <dd>公司: 大城若谷信息技术有限公司</dd>
 * <dd>创建时间：2017/7/3</dd>
 * <dd>创建人：Chaos</dd>
 * </dl>
 */
@Controller
@RequestMapping(value = "${adminPath}/api/contructionPlanStandBook")
public class ConstructionPlanStandBookController extends BaseController {
	@Autowired
    private ConstructionPlanStandBookService constructionPlanStandBookService;

	@RequestMapping(value ="/findPlanAndDoneTimeByOrderNo",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody Object findPlanAndDoneTimeByOrderNo(@RequestParam String orderno){
		return constructionPlanStandBookService.findPlanAndDoneTimeByOrderNo(orderno);
    }
}