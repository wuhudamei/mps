
package cn.damei.web.modules;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizPmAttendCnfg;
import cn.damei.entity.modules.BizPmAttendCnfgStar;
import cn.damei.service.modules.BizPmAttendCnfgService;
import cn.damei.entity.modules.Dict;
import cn.damei.common.utils.DictUtils;
import cn.damei.common.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = "${adminPath}/bizpmattendcnfg/bizPmAttendCnfg")
public class BizPmAttendCnfgController extends BaseController {

	@Autowired
	private BizPmAttendCnfgService bizPmAttendCnfgService;
	
	@ModelAttribute
	public BizPmAttendCnfg get(@RequestParam(required=false) String id) {
		BizPmAttendCnfg entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizPmAttendCnfgService.get(id);
		}
		if (entity == null){
			entity = new BizPmAttendCnfg();
		}
		return entity;
	}
	
	@RequiresPermissions("bizpmattendcnfg:bizPmAttendCnfg:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPmAttendCnfg bizPmAttendCnfg, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizPmAttendCnfg> page = bizPmAttendCnfgService.findPage(new Page<BizPmAttendCnfg>(request, response), bizPmAttendCnfg); 
		model.addAttribute("page", page);
		return "modules/bizpmattendcnfg/bizPmAttendCnfgList";
	}

	@RequiresPermissions("bizpmattendcnfg:bizPmAttendCnfg:view")
	@RequestMapping(value = "form")
	public String form(BizPmAttendCnfg bizPmAttendCnfg, Model model) {
		List<Dict> managerStarList =  DictUtils.getDictList("manager_star");
		List<BizPmAttendCnfgStar> bizPmAttendCnfgStarList = bizPmAttendCnfgService.findBizPmAttendCnfgStarListByCnfgId(bizPmAttendCnfg.getId());
		bizPmAttendCnfg.setBizPmAttendCnfgStarList(bizPmAttendCnfgStarList);
		model.addAttribute("bizPmAttendCnfg", bizPmAttendCnfg);
		model.addAttribute("bizPmAttendCnfgStarList", bizPmAttendCnfgStarList);
		model.addAttribute("managerStarList", managerStarList);

		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM");
		Calendar c=Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH,-1);
		Date m=c.getTime();
		String mon=format.format(m);
		model.addAttribute("mon",mon);
		return "modules/bizpmattendcnfg/bizPmAttendCnfgForm";
	}

	@RequiresPermissions("bizpmattendcnfg:bizPmAttendCnfg:edit")
	@RequestMapping(value = "save")
	public String save(BizPmAttendCnfg bizPmAttendCnfg,Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizPmAttendCnfg)){
			return form(bizPmAttendCnfg, model);
		}
		bizPmAttendCnfg.setCreateBy(UserUtils.getUser());
		bizPmAttendCnfgService.saveOrInsert(bizPmAttendCnfg);

		String[] star = request.getParameterValues("star");
		String[] starSalaryAllAttend =request.getParameterValues("starSalaryAllAttend");
		String[] starSalaryMin =request.getParameterValues("starSalaryMin");
		if(null!=star){
			List<BizPmAttendCnfgStar> bizPmAttendCnfgStarList = new ArrayList<BizPmAttendCnfgStar>();
			for(int i=0;i<star.length;i++){
				BizPmAttendCnfgStar b = new BizPmAttendCnfgStar();
				b.setStar(star[i]);
				b.setStarSalaryAllAttend(Double.valueOf(starSalaryAllAttend[i]));
				b.setStarSalaryMin(Double.valueOf(starSalaryMin[i]));
				b.setPmAttendCnfgId(bizPmAttendCnfg.getId());
				bizPmAttendCnfgStarList.add(b);
			}
			bizPmAttendCnfgService.saveBizPmAttendCnfgStarList(bizPmAttendCnfgStarList);
		}

		String[] idU = request.getParameterValues("id_u");
		String[] starU = request.getParameterValues("star_u");
		String[] starSalaryAllAttendU =request.getParameterValues("starSalaryAllAttend_u");
		String[] starSalaryMinU =request.getParameterValues("starSalaryMin_u");
		if(idU!=null){
			for(int i=0;i<idU.length;i++){
				BizPmAttendCnfgStar b = new BizPmAttendCnfgStar();
				b.setId(idU[i]);
				b.setStar(starU[i]);
				b.setStarSalaryAllAttend(Double.valueOf(starSalaryAllAttendU[i]));
				b.setStarSalaryMin(Double.valueOf(starSalaryMinU[i]));
				bizPmAttendCnfgService.updateBizPmAttendCnfgStarListById(b);
			}
		}
		addMessage(redirectAttributes, "保存项目经理考勤基础设置成功");
		return "redirect:"+Global.getAdminPath()+"/bizpmattendcnfg/bizPmAttendCnfg/?repage";
	}
	@RequestMapping(value = "deleteStarById")
	public void deleteStarById(String id,HttpServletResponse response) throws IOException {
		bizPmAttendCnfgService.deleteStarById(id);
		

	}
	@RequiresPermissions("bizpmattendcnfg:bizPmAttendCnfg:edit")
	@RequestMapping(value = "delete")
	public String delete(BizPmAttendCnfg bizPmAttendCnfg, RedirectAttributes redirectAttributes) {
		bizPmAttendCnfgService.delete(bizPmAttendCnfg);
		addMessage(redirectAttributes, "删除项目经理考勤基础设置成功");
		return "redirect:"+Global.getAdminPath()+"/bizpmattendcnfg/bizPmAttendCnfg/?repage";
	}

	@RequestMapping(value = "checkRepeateByStorIdAndMonth")
	public void checkRepeateByStorIdAndMonth(String storeId,String effectMonth,String id,String projectMode,HttpServletRequest request,HttpServletResponse response) throws IOException {
		int total = bizPmAttendCnfgService.checkRepeateByStorIdAndMonth(storeId,effectMonth, id,projectMode);
		PrintWriter pw = response.getWriter();
		String result ="";
		if(total==0){
			result = "true";
		}else{
			result = "false";
		}
		pw.print(result);
		pw.flush();
		pw.close();
	}
	@RequestMapping(value = "updateIsEnabledById")
	public String updateIsEnabledById(String id,String isEnabled,RedirectAttributes redirectAttributes) {
		bizPmAttendCnfgService.updateIsEnabledById(id,isEnabled);
		addMessage(redirectAttributes, "修改状态成功！");
		return "redirect:"+Global.getAdminPath()+"/bizpmattendcnfg/bizPmAttendCnfg/?repage";
	}
}