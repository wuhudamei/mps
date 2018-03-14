package cn.damei.web.api;

import cn.damei.service.modules.DelaySheetStandBookService;
import cn.damei.common.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "${adminPath}/api/delaySheetStandBook")
public class DelaySheetStandBookController extends BaseController {
	@Autowired
    private DelaySheetStandBookService delaySheetStandBookService;

	@RequestMapping(value ="/findDelaySheet",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody Object findDelaySheet(@RequestParam String orderno){
		return delaySheetStandBookService.findDelaySheet(orderno);
    }
}