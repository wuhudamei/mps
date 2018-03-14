
package cn.damei.web.modules;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.BizAttendBillConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizPmAttendMonth;
import cn.damei.service.modules.BizPmAttendMonthService;
import cn.damei.service.modules.BizSeiralnumService;
import cn.damei.service.modules.SysSequenceService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping(value = "${adminPath}/attend/bizPmAttendMonth")
public class BizPmAttendMonthController extends BaseController {
	
	@Autowired
	private BizPmAttendMonthService bizPmAttendMonthService;
	@Autowired
	private BizSeiralnumService bizSeiralnumService;
	@Autowired
	private SysSequenceService sysSequenceService;
	
	@ModelAttribute
	public BizPmAttendMonth get(@RequestParam(required=false) String id) {
		BizPmAttendMonth entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizPmAttendMonthService.get(id);
		}
		if (entity == null){
			entity = new BizPmAttendMonth();
		}
		return entity;
	}
	
	@RequiresPermissions("attend:bizPmAttendMonth:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPmAttendMonth bizPmAttendMonth, HttpServletRequest request, HttpServletResponse response, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date time = c.getTime();
		String mon = sdf.format(time);
		System.out.println(mon);
		bizPmAttendMonth.setAttendMonth(mon);
		if(UserUtils.getUser().getStoreId()!=null){

			bizPmAttendMonth.setStoreId(UserUtils.getUser().getStoreId());
		}
		else{

			if(bizPmAttendMonth.getStoreId()!=null && bizPmAttendMonth.getStoreId().equals("1")){

				bizPmAttendMonth.setStoreId(null);
			}
		}
		model.addAttribute("attendMonth", bizPmAttendMonth);
		model.addAttribute("maxMonth",mon);
		return "modules/attend/bizPmAttendMonthList";
	}
	@RequiresPermissions("attend:bizPmAttendMonth:view")
	@RequestMapping(value = {"findList", ""})
	public String findlist(BizPmAttendMonth bizPmAttendMonth, HttpServletRequest request, HttpServletResponse response, Model model) {

		String mon = DateUtils.formatDate(DateUtils.addMonths(new Date(),-1),"yyyy-MM");
		if(UserUtils.getUser().getStoreId()!=null){

			bizPmAttendMonth.setStoreId(UserUtils.getUser().getStoreId());
		}
		else{

			if(bizPmAttendMonth.getStoreId()!=null && bizPmAttendMonth.getStoreId().equals("1")){

				bizPmAttendMonth.setStoreId(null);
			}
		}
		Page<BizPmAttendMonth> page = bizPmAttendMonthService.findPage(new Page<BizPmAttendMonth>(request, response), bizPmAttendMonth);
		model.addAttribute("page", page);
		model.addAttribute("maxMonth",mon);
		return "modules/attend/bizPmAttendMonthList";
	}
	
	
	
	@RequiresPermissions("attend:bizPmAttendMonth:view")
	@RequestMapping(value = "form")
	public String form(BizPmAttendMonth bizPmAttendMonth, Model model) {
		Calendar cale = null;
		cale = Calendar.getInstance();
		String month=bizPmAttendMonth.getAttendMonth();
		String pp="-01";
		String fullMonth=month+pp;
		try {
			Date d1 = new SimpleDateFormat("yyyy-MM-01").parse(fullMonth);
			bizPmAttendMonth.setAttendStartDate(DateUtils.addSeconds(d1,0));
			cale.setTime(d1);
			cale.add(Calendar.MONTH, 0);
			cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
			bizPmAttendMonth.setAttendEndDate(cale.getTime());
			model.addAttribute("dd", d1);
			model.addAttribute("end",cale.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		model.addAttribute("bizPmAttendMonth", bizPmAttendMonth);
		return "modules/attend/bizPmAttendMonthForm";
	}

	@RequiresPermissions("attend:bizPmAttendMonth:view")
	@RequestMapping(value = "getForm2List")
	@ResponseBody
	public Map<String, Object> getForm2List(BizPmAttendMonth bizPmAttendMonth,HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String,Object> map = new HashMap<>();
		List<BizPmAttendMonth> bizPmAttendMonthList = new ArrayList<>();
        bizPmAttendMonthList = bizPmAttendMonthService.findBizPmAttendMonthList(bizPmAttendMonth);
		map.put("resultMap", bizPmAttendMonthList);
		return map;
	}

	@RequiresPermissions("attend:bizPmAttendMonth:view")
	@RequestMapping(value = "getForm3Count")
	@ResponseBody
	public Map<String, Object> getForm3Count(BizPmAttendMonth bizPmAttendMonth,HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String,Object> map = new HashMap<>();
        List<BizPmAttendMonth> bizPmAttendMonthList = bizPmAttendMonthService.findBizPmAttendMonthList(bizPmAttendMonth);
		map.put("resultMap", bizPmAttendMonthList);
		return map;
	}
	
	@RequiresPermissions("attend:bizPmAttendMonth:edit")
	@RequestMapping(value = "save")
	public String save(BizPmAttendMonth bizPmAttendMonth, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizPmAttendMonth)){
			return form(bizPmAttendMonth, model);
		}
		BizPmAttendMonth findAttendMonth = bizPmAttendMonthService.findAttendMonth(bizPmAttendMonth);
		if(null == findAttendMonth){
			if(StringUtils.isEmpty(bizPmAttendMonth.getPmAttendMonthId())){

				String sequence = sysSequenceService.getSequence(BizAttendBillConstantUtil.KQ_NO);

				String kqNo = sequence.substring(0,2);

				String No = sequence.substring(2);

				String date = BizAttendBillConstantUtil.getDate(new Date());

				bizPmAttendMonth.setAttendMonthCode(kqNo+date+No);

				bizPmAttendMonth.setStatus("20");
				bizPmAttendMonthService.save(bizPmAttendMonth);
				bizPmAttendMonth.setPmAttendMonthId(bizPmAttendMonth.getId());
			}else{
				bizPmAttendMonth.setStatus("20");
				bizPmAttendMonthService.updatePmAttendMonth(bizPmAttendMonth);

			}
			BizPmAttendMonth findAttendMonthOrder = bizPmAttendMonthService.findAttendMonthOrder(bizPmAttendMonth);
			if(null==findAttendMonthOrder){
				bizPmAttendMonthService.saveMonthOrder(bizPmAttendMonth);
			}
			addMessage(redirectAttributes, "保存考勤月度表成功");
			return "redirect:"+Global.getAdminPath()+"/attend/bizPmAttendMonth/findList?storeId="+bizPmAttendMonth.getStoreId()+"&itemManagerId="+bizPmAttendMonth.getItemManagerId()+"&enginDepartId="+bizPmAttendMonth.getEnginDepartId()+"&attendMonth="+bizPmAttendMonth.getAttendMonth();
		}else{
			addMessage(redirectAttributes, "页面重复提交，请刷新页面后再试。");
			return "redirect:"+Global.getAdminPath()+"/attend/bizPmAttendMonth/form";
		}
		
		
		
	}
	
	@RequiresPermissions("attend:bizPmAttendMonth:edit")
	@RequestMapping(value = "delete")
	public String delete(BizPmAttendMonth bizPmAttendMonth, RedirectAttributes redirectAttributes) {
		bizPmAttendMonthService.delete(bizPmAttendMonth);
		bizPmAttendMonthService.deleteMonth(bizPmAttendMonth);
		addMessage(redirectAttributes, "删除考勤月度表成功");
		return "redirect:"+Global.getAdminPath()+"/attend/bizPmAttendMonth/?repage";
	}
	
	@RequiresPermissions("attend:bizPmAttendMonth:view")
	@RequestMapping(value = {"bizPmAttendList", ""})
	public String bizPmAttendList(BizPmAttendMonth bizPmAttendMonth, HttpServletRequest request, HttpServletResponse response, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date time = c.getTime();
		String mon = sdf.format(time);
		bizPmAttendMonth.setAttendMonth(mon);
		if(UserUtils.getUser().getStoreId()!=null){

			bizPmAttendMonth.setStoreId(UserUtils.getUser().getStoreId());
		}
		else{

			if(bizPmAttendMonth.getStoreId()!=null && bizPmAttendMonth.getStoreId().equals("1")){

				bizPmAttendMonth.setStoreId(null);
			}
		}
		model.addAttribute("bizPmAttendMonth", bizPmAttendMonth);
		model.addAttribute("maxMonth",mon);
		return "modules/attend/bizPmAttendList";
	}
	@RequiresPermissions("attend:bizPmAttendMonth:view")
	@RequestMapping(value = {"findPmAttendList", ""})
	public String findPmAttendList(BizPmAttendMonth bizPmAttendMonth, HttpServletRequest request, HttpServletResponse response, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date time = c.getTime();
		String mon = sdf.format(time);
		System.out.println(mon);
		if(UserUtils.getUser().getStoreId()!=null){

			bizPmAttendMonth.setStoreId(UserUtils.getUser().getStoreId());
		}
		else{

			if(bizPmAttendMonth.getStoreId()!=null && bizPmAttendMonth.getStoreId().equals("1")){

				bizPmAttendMonth.setStoreId(null);
			}
		}
		Page<BizPmAttendMonth> page = bizPmAttendMonthService.findPmAttendList(new Page<BizPmAttendMonth>(request, response), bizPmAttendMonth);
		model.addAttribute("page", page);
		model.addAttribute("bizPmAttendMonth", bizPmAttendMonth);
		model.addAttribute("maxMonth", mon);
		return "modules/attend/bizPmAttendList";
	}
	
	@RequiresPermissions("attend:bizPmAttendMonth:view")
	@RequestMapping(value = {"bizPmAttendFindList", ""})
	public String bizPmAttendFindList(BizPmAttendMonth bizPmAttendMonth, HttpServletRequest request, HttpServletResponse response, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date time = c.getTime();
		String mon = sdf.format(time);
		bizPmAttendMonth.setAttendMonth(mon);
		if(UserUtils.getUser().getStoreId()!=null){

			bizPmAttendMonth.setStoreId(UserUtils.getUser().getStoreId());
		}
		else{

			if(bizPmAttendMonth.getStoreId()!=null && bizPmAttendMonth.getStoreId().equals("1")){

				bizPmAttendMonth.setStoreId(null);
			}
		}
		model.addAttribute("bizPmAttendMonth", bizPmAttendMonth);
		model.addAttribute("maxMonth", mon);
		return "modules/attend/bizPmAttendFindList";
	}
	
	@RequiresPermissions("attend:bizPmAttendMonth:view")
	@RequestMapping(value = {"findBizPmAttendFindList", ""})
	public String findBizPmAttendFindList(BizPmAttendMonth bizPmAttendMonth, HttpServletRequest request, HttpServletResponse response, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date time = c.getTime();
		String mon = sdf.format(time);
		if(UserUtils.getUser().getStoreId()!=null){

			bizPmAttendMonth.setStoreId(UserUtils.getUser().getStoreId());
		}
		else{

			if(bizPmAttendMonth.getStoreId()!=null && bizPmAttendMonth.getStoreId().equals("1")){

				bizPmAttendMonth.setStoreId(null);

			}
		}
		Page<BizPmAttendMonth> page = bizPmAttendMonthService.findPmAttendDetailList(new Page<BizPmAttendMonth>(request, response), bizPmAttendMonth);
		model.addAttribute("page", page);
		model.addAttribute("bizPmAttendMonth", bizPmAttendMonth);
		model.addAttribute("maxMonth", mon);
		return "modules/attend/bizPmAttendFindList";
	}
	
	@RequiresPermissions("attend:bizPmAttendMonth:view")
	@RequestMapping(value = "bizPmAttendDetail")
	public String bizPmAttendDetail(BizPmAttendMonth bizPmAttendMonth, Model model) {
		String findProjectMode=bizPmAttendMonthService.findProjectModeByManagerId(bizPmAttendMonth.getItemManagerId());
		BizPmAttendMonth findSalaryAllAttend=bizPmAttendMonthService.findSalaryAllAttend(findProjectMode,bizPmAttendMonth.getStoreId(),bizPmAttendMonth.getItemManagerStar());
		if(null==findSalaryAllAttend){
			bizPmAttendMonth.setStarSalaryAllAttend(0);
		}else{
			bizPmAttendMonth.setStarSalaryAllAttend(findSalaryAllAttend.getStarSalaryAllAttend());
		}
		model.addAttribute("bizPmAttendMonth", bizPmAttendMonth);
		return "modules/attend/bizPmAttendForm";
	}
	
	@RequiresPermissions("attend:bizPmAttendMonth:view")
	@RequestMapping(value = "getDetailForm2List")
	@ResponseBody
	public Map<String,Object> getDetailForm2List(BizPmAttendMonth bizPmAttendMonth, Model model) {
		Map<String,Object> map = new HashMap<>();
		String date=bizPmAttendMonthService.getOrderInsertDate(bizPmAttendMonth);
		List<BizPmAttendMonth> detailFormList=new ArrayList<>();
		if(null!=date){
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date dd = null;
			try {
				dd = s.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			SimpleDateFormat kk= new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String formatDate =kk.format(dd);
			detailFormList = bizPmAttendMonthService.getDetailFormList(bizPmAttendMonth.getAttendMonth(),bizPmAttendMonth.getItemManagerId(),formatDate,bizPmAttendMonth.getStatus());
		}






		map.put("resultMap", detailFormList);
		return map;
	}
	
}