package cn.damei.web.modules;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.PmMaterialsSettleInfo;
import cn.damei.service.modules.PmMaterialsSettleInfoService;
import cn.damei.common.utils.UserUtils;

/**
 * 项目经理材料结算信息Controller
 * 
 * @author hyh
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/pmMaterialsSettleInfo/pmMaterialsSettleInfo")
public class PmMaterialsSettleInfoController extends BaseController {
	@Autowired
	private PmMaterialsSettleInfoService pmMaterialsSettleInfoService;

	@RequestMapping(value = "queryPmMaterialsSettleInfo")
	public String queryPmMaterialsSettleInfo(PmMaterialsSettleInfo pmMaterialsSettleInfo, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if(pmMaterialsSettleInfo.getSettleStatus()!=null){
			String[] status = pmMaterialsSettleInfo.getSettleStatus().split(",");
			if(null!=status && status.length>0){
				pmMaterialsSettleInfo.setStatus(Arrays.asList(status));
			}
		}
		
		if(UserUtils.getUser().getStoreId()!=null)
		{
			//当前登录用户门店
			pmMaterialsSettleInfo.setStoreId(Integer.valueOf(UserUtils.getUser().getStoreId()));
		}
		else{
			//门店是总部的查询所有部门信息
			if(pmMaterialsSettleInfo.getStoreId()!=null && pmMaterialsSettleInfo.getStoreId() == 1)
			{
				//总部
				pmMaterialsSettleInfo.setStoreId(null);
			}
		}
		
		
		Page<PmMaterialsSettleInfo> page = pmMaterialsSettleInfoService.findPage(new Page<PmMaterialsSettleInfo>(request, response), pmMaterialsSettleInfo);
		model.addAttribute("page", page);
		return "modules/pmMaterialsSettle/pmMaterialsSettleInfo";
	}
	
	
}
