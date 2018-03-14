/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizApplyCheckDelete;
import cn.damei.service.modules.BizApplyCheckDeleteService;

/**
 * 约检信息管理Controller
 * @author 梅浩
 * @version 2017-04-21
 */
@Controller
@RequestMapping(value = "${adminPath}/qcapplycheckdelete/bizApplyCheckDelete")
public class BizApplyCheckDeleteController extends BaseController {

	@Autowired
	private BizApplyCheckDeleteService bizApplyCheckDeleteService;
	
	@ModelAttribute
	public BizApplyCheckDelete get(@RequestParam(required=false) String id) {
		BizApplyCheckDelete entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizApplyCheckDeleteService.get(id);
		}
		if (entity == null){
			entity = new BizApplyCheckDelete();
		}
		return entity;
	}
	
	@RequiresPermissions("qcapplycheckdelete:bizApplyCheckDelete:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizApplyCheckDelete bizApplyCheckDelete, HttpServletRequest request, HttpServletResponse response, Model model) {
        //过滤门店
        User user = UserUtils.getUser();
        if(null==bizApplyCheckDelete.getStoreId()){
            if(null!=user.getStoreId()){
                bizApplyCheckDelete.setStoreId(Integer.valueOf(user.getStoreId()));
            }
        }
		Page<BizApplyCheckDelete> page = bizApplyCheckDeleteService.findPage(new Page<BizApplyCheckDelete>(request, response), bizApplyCheckDelete); 
		model.addAttribute("page", page);
		model.addAttribute("bizApplyCheckDelete", bizApplyCheckDelete);
		return "modules/qcapplycheckdelete/bizApplyCheckDeleteList";
	}


	@RequiresPermissions("qcapplycheckdelete:bizApplyCheckDelete:edit")
	@RequestMapping(value = "delete")
	public @ResponseBody
	String delete(BizApplyCheckDelete bizApplyCheckDelete, RedirectAttributes redirectAttributes) {
		bizApplyCheckDeleteService.delete(bizApplyCheckDelete);

		return "1";
	}

}