package cn.damei.web.modules;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.damei.common.config.Global;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.DataAuthority;
import cn.damei.service.modules.DataAuthorityService;

@Controller
@RequestMapping(value="${adminPath}/dataAuthority/configure")
public class DataAuthorityController  extends BaseController{
	@Autowired
	private DataAuthorityService dataAuthorityService;
	
	@ModelAttribute
	public DataAuthority get(@RequestParam(required=false) String id) {
		DataAuthority entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dataAuthorityService.get(id);
		}
		if (entity == null){
			entity = new DataAuthority();
		}
		return entity;
	}
	
	@RequestMapping(value = "list")
	public String configure(Model model,String roleName,String authorityId){

		List<DataAuthority> list = dataAuthorityService.findTransactionData(authorityId);
		model.addAttribute("list", list);

		String str = "<select id='adds' class='input-medium' >"+"<option value = ''>"+"    "+"</option>";
		for (DataAuthority s : list) {
			 str += "<option value = ''>"+s.getTransactionData()+"</option>";
		}
		String str2 = "</select>";
		model.addAttribute("transaction", str+str2);
		model.addAttribute("roleName", roleName);
		model.addAttribute("roleId", authorityId);
		return "modules/dataAuthority/configureDataAuthorityList";
	}
	
	@RequestMapping(value = "details")
	public String details(Model model,String roleName,String data,String ids,String roleId){
		
		List<DataAuthority> list = dataAuthorityService.findDataPermissionOptions(ids);
		model.addAttribute("list", list);
		model.addAttribute("roleName", roleName);
		model.addAttribute("id", ids);
		model.addAttribute("data", data);
		model.addAttribute("roleId", roleId);
		Integer value = dataAuthorityService.findOptionsByRoleId(roleId,ids);
		model.addAttribute("value", value);
		return "modules/dataAuthority/dataAuthorityDetails";
		
	}
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "save")
	public String save(String roleId,String transactionId,String checkId,String roleName){

		List<String> list = dataAuthorityService.findSysDataAuthRuleOptionRole(roleId,transactionId);
		DataAuthority da = new DataAuthority();
		da.setRoleId(roleId);
		da.setDataAuthOptionId(checkId);

		if(list!=null&&list.size()>0){


			dataAuthorityService.delete(da);
			dataAuthorityService.deleteSysDataAuthRuleOptionRole(roleId,list.get(0));
		}

		if(checkId!=null&&!checkId.equals("")){
			da.preInsert();
			dataAuthorityService.insertSysDataAuthRuleOptionRole(da);
		}
		
		String encode = null;
		try {
			encode = URLEncoder.encode(roleName,"UTF-8");
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return "redirect:" + Global.getAdminPath() + "/dataAuthority/configure/list?authorityId="+roleId+"&roleName="+encode;
	}
	
	
}

