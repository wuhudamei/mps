package cn.damei.web.api;

import cn.damei.service.modules.QualityCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "${adminPath}/api/qualityCheckStandBook")
public class QualityCheckController {
    @Autowired
    private QualityCheckService qualityCheckService;

    @RequestMapping(value ="/findQualityCheck",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody Object findQualityCheck(@RequestParam String orderno){
        return this.qualityCheckService.findQualityCheck(orderno);
    }
    @RequestMapping(value ="/findRepeatQualityCheck",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody Object findRepeatQualityCheck(@RequestParam String orderno){
        return this.qualityCheckService.findRepeatQualityCheck(orderno);
    }
}
