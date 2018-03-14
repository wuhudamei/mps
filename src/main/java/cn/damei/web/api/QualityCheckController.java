package cn.damei.web.api;

import cn.damei.service.modules.QualityCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <dl>
 * <dd>描述:质检台账(用于远程调用)</dd>
 * <dd>公司: 智装</dd>
 * <dd>创建时间：2017/9/13</dd>
 * <dd>创建人：Chaos</dd>
 * </dl>
 */
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
