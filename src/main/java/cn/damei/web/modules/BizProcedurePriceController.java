/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizProcedurePrice;
import cn.damei.service.modules.BizProcedurePriceService;
import cn.damei.common.utils.UserUtils;

/**
 * 工序价格管理Controller
 * @author 魏建勇
 * @version 2016-09-03
 */
@Controller
@RequestMapping(value = "${adminPath}/procedureprice/bizProcedurePrice")
public class BizProcedurePriceController extends BaseController {

	@Autowired
	private BizProcedurePriceService bizProcedurePriceService;
	
	@ModelAttribute
	public BizProcedurePrice get(@RequestParam(required=false) String id) {
		BizProcedurePrice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizProcedurePriceService.get(id);
		}
		if (entity == null){
			entity = new BizProcedurePrice();
		}
		return entity;
	}
	
	//@RequiresPermissions("procedureprice:bizProcedurePrice:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizProcedurePrice bizProcedurePrice, HttpServletRequest request, HttpServletResponse response, Model model) {
        if(StringUtils.isBlank(bizProcedurePrice.getStoreId())){
            bizProcedurePrice.setStoreId(UserUtils.getUser().getStoreId());
        }
        if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
            model.addAttribute("storeDropEnable", true);
        }
	    Page<BizProcedurePrice> page = bizProcedurePriceService.findPage(new Page<BizProcedurePrice>(request, response), bizProcedurePrice); 
		model.addAttribute("page", page);
		return "modules/procedureprice/bizProcedurePriceList";
	}

	//@RequiresPermissions("procedureprice:bizProcedurePrice:view")
	@RequestMapping(value = "form")
	public String form(BizProcedurePrice bizProcedurePrice, Model model) {
	    if(StringUtils.isBlank(bizProcedurePrice.getStoreId())){
            bizProcedurePrice.setStoreId(UserUtils.getUser().getStoreId());
        }
        if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
            model.addAttribute("storeDropEnable", true);
        }
        List<BizProcedurePrice> prices =  bizProcedurePriceService.findList(bizProcedurePrice);
		if(prices != null && prices.size()>0){
			model.addAttribute("dateEnable",true);
		}
		if(bizProcedurePrice.getProjectMode() == null || bizProcedurePrice.getProjectMode().equals("")){
			bizProcedurePrice.setProjectMode("1");
		}
        model.addAttribute("bizProcedurePrice", bizProcedurePrice);
		return "modules/procedureprice/bizProcedurePriceForm";
	}

	//@RequiresPermissions("procedureprice:bizProcedurePrice:edit")
	@RequestMapping(value = "save")
	public String save(BizProcedurePrice bizProcedurePrice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizProcedurePrice)){
			return form(bizProcedurePrice, model);
		}
		if(bizProcedurePrice.getId() == null || "".equals(bizProcedurePrice.getId())){
    		BizProcedurePrice entity = new BizProcedurePrice();
    		entity.setProcedureNo(bizProcedurePrice.getProcedureNo());
    		entity.setStoreId(bizProcedurePrice.getStoreId());
    		entity.setDelFlag(entity.DEL_FLAG_NORMAL);
    		entity.setProjectMode(bizProcedurePrice.getProjectMode());
    		List<BizProcedurePrice> entityList =  bizProcedurePriceService.findList(entity);
    		 int maxVersion = 0;
    		if(entityList!=null && entityList.size()>0){
    		    //addMessage(redirectAttributes, "工序价格成功");
    		   // return form(bizProcedurePrice, model);
    		   
    		    for(BizProcedurePrice bpp :entityList){
    		        if(bpp.getVersion()>maxVersion){
    		            maxVersion = bpp.getVersion();
    		        }
    		        if(bpp.getEffectiveDate().getTime()==bizProcedurePrice.getEffectiveDate().getTime()){
    		            addMessage(model, new String[]{"数据验证失败：","同一门店下的生效日期同一天的!只允许有一条记录，不能保存多条记录"});
    		            return form(bizProcedurePrice, model);
    		        }
    		    }
    		}
    		bizProcedurePrice.setVersion(maxVersion+1);
		}
		bizProcedurePrice.setSynthesizePrice(bizProcedurePrice.getAccessoriesPrice().add(bizProcedurePrice.getLaborPrice()));
		
		bizProcedurePriceService.save(bizProcedurePrice);
		addMessage(redirectAttributes, "保存工序价格成功");
		return "redirect:"+Global.getAdminPath()+"/procedureprice/bizProcedurePrice/?procedureNo="+bizProcedurePrice.getProcedureNo()+"&repage";
	}
	
	@RequiresPermissions("procedureprice:bizProcedurePrice:edit")
	@RequestMapping(value = "delete")
	public String delete(BizProcedurePrice bizProcedurePrice, RedirectAttributes redirectAttributes) {
		bizProcedurePriceService.delete(bizProcedurePrice);
		addMessage(redirectAttributes, "删除工序价格成功");
		return "redirect:"+Global.getAdminPath()+"/procedureprice/bizProcedurePrice/?procedureNo="+bizProcedurePrice.getProcedureNo()+"&repage";
	}

}