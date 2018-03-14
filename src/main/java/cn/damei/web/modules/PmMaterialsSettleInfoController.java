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

			pmMaterialsSettleInfo.setStoreId(Integer.valueOf(UserUtils.getUser().getStoreId()));
		}
		else{

			if(pmMaterialsSettleInfo.getStoreId()!=null && pmMaterialsSettleInfo.getStoreId() == 1)
			{

				pmMaterialsSettleInfo.setStoreId(null);
			}
		}
		
		
		Page<PmMaterialsSettleInfo> page = pmMaterialsSettleInfoService.findPage(new Page<PmMaterialsSettleInfo>(request, response), pmMaterialsSettleInfo);
		model.addAttribute("page", page);
		return "modules/pmMaterialsSettle/pmMaterialsSettleInfo";
	}
	
	
}
