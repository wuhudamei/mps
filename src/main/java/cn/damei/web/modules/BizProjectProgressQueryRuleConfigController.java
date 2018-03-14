package cn.damei.web.modules;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cn.damei.common.config.Global;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizProjectProgressQueryRuleConfig;
import cn.damei.service.modules.BizProjectProgressQueryRuleConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "${adminPath}/projectprogressboning/bizProjectProgressQueryRuleConfig")
public class BizProjectProgressQueryRuleConfigController extends BaseController {

    @Autowired
    private BizProjectProgressQueryRuleConfigService bizProjectProgressQueryRuleConfigService;


    @RequestMapping(value = "/configList")
    public  String configList(BizProjectProgressQueryRuleConfig bizProjectProgressQueryRuleConfig, Model model){
        List<BizProjectProgressQueryRuleConfig> list = Lists.newArrayList();
        List<BizProjectProgressQueryRuleConfig> Alllist = bizProjectProgressQueryRuleConfigService.findList(bizProjectProgressQueryRuleConfig);
        BizProjectProgressQueryRuleConfig.sortList(list, Alllist, null, true);
        model.addAttribute("list",list);
        return "modules/projectprogressboning/bizProjectProgressQueryRuleConfigList";
    }


    @RequestMapping(value = "/form")
    public String form(BizProjectProgressQueryRuleConfig bizProjectProgressQueryRuleConfig,Model model){
        if(bizProjectProgressQueryRuleConfig.getId() != null){
            bizProjectProgressQueryRuleConfig = bizProjectProgressQueryRuleConfigService.get(bizProjectProgressQueryRuleConfig.getId());
        }
        BizProjectProgressQueryRuleConfig parentConfig = bizProjectProgressQueryRuleConfigService.get(bizProjectProgressQueryRuleConfig.getParentId());
        model.addAttribute("parentConfig",parentConfig);
        model.addAttribute("bizProjectProgressQueryRuleConfig",bizProjectProgressQueryRuleConfig);
        return "modules/projectprogressboning/bizProjectProgressQueryRuleConfigForm";
    }


    @RequestMapping(value = "/save")
    public String save(BizProjectProgressQueryRuleConfig bizProjectProgressQueryRuleConfig, Model model, RedirectAttributes redirectAttributes) {
        bizProjectProgressQueryRuleConfigService.save(bizProjectProgressQueryRuleConfig);
        return "redirect:" + Global.getAdminPath() + "/projectprogressboning/bizProjectProgressQueryRuleConfig/configList?repage";
    }



    @ResponseBody
    @RequestMapping(value = "/treeData")
    public List<Map<String, Object>> treeData(HttpServletResponse response) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<BizProjectProgressQueryRuleConfig> list = bizProjectProgressQueryRuleConfigService.findList(null);
        if (list != null && list.size() > 0) {
            for (BizProjectProgressQueryRuleConfig config : list) {
                Map<String, Object> map = Maps.newHashMap();
                map.put("id", config.getId());
                map.put("pId", config.getParentId());
                map.put("name", config.getEnColumnName() + "[" + config.getCnColumnName() + "]");
                mapList.add(map);
            }
        }
        return mapList;
    }


    @RequestMapping(value = "/delete")
    public  String delete(BizProjectProgressQueryRuleConfig bizProjectProgressQueryRuleConfig, Model model, RedirectAttributes redirectAttributes){
        bizProjectProgressQueryRuleConfigService.deleteConfig(bizProjectProgressQueryRuleConfig);
        return "redirect:" + Global.getAdminPath() + "/projectprogressboning/bizProjectProgressQueryRuleConfig/configList?repage";
    }
}
