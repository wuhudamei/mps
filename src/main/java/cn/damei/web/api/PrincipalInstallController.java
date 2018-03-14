package cn.damei.web.api;

import cn.damei.service.modules.PrincipalInstallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <dl>
 * <dd>描述:主材安装账(用于远程调用)</dd>
 * <dd>公司: 智装</dd>
 * <dd>创建时间：2017/9/14</dd>
 * <dd>创建人：Chaos</dd>
 * </dl>
 */
@Controller
@RequestMapping(value = "${adminPath}/api/principalInstallStandBook")
public class PrincipalInstallController {
    @Autowired
    private PrincipalInstallService principalInstallService;



    @RequestMapping(value ="/findPrincipalInstall",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody Object findPrincipalInstall(@RequestParam String orderno){
        return this.principalInstallService.findPrincipalInstall(orderno);
    }
}
