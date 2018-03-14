package cn.damei.web.api;

import cn.damei.service.modules.ConstructionPlanStandBookService;
import cn.damei.common.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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