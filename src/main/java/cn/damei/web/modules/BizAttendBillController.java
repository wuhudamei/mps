package cn.damei.web.modules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.BizAttendBillConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizAttendBatch;
import cn.damei.entity.modules.BizAttendBill;
import cn.damei.entity.modules.BizAttendDay;
import cn.damei.service.modules.BizAttendBatchService;
import cn.damei.service.modules.BizAttendBillService;
import cn.damei.service.modules.BizAttendDayService;
import cn.damei.service.modules.BizStarBasicSalaryService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/bizAttend/bizAttendBills/")
public class BizAttendBillController extends BaseController {

	@Autowired
	private BizAttendBillService bizAttendBillService;
	
	@Autowired
	private BizAttendDayService bizAttendDayService;
	@Autowired
	private BizStarBasicSalaryService bizStarBasicSalaryService;
	@Autowired
	private BizAttendBatchService bizAttendBatchService;
	
	/**
	 * 集合
	 * 
	 * @param bizAttendDay
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizAttendBills:bizAttendBills:view")
	@RequestMapping(value = "list")
	public String sings(@ModelAttribute BizAttendBill bizAttendBill,HttpServletRequest request, HttpServletResponse response, Model model) {
        //过滤门店
        User user = UserUtils.getUser();
        if(null==bizAttendBill.getStoreId()){
            if(null!=user.getStoreId()){
                bizAttendBill.setStoreId(user.getStoreId());
            }
        }
		Page<BizAttendBill> page = bizAttendBillService.findPage(new Page<BizAttendBill>(request, response), bizAttendBill);
		model.addAttribute("page", page);
		
		return "modules/attend/bizAttendBillList";
	}
	
	
	/**
	 * 生成考勤单参数跳转 并根据月份获取底薪的记录
	 * @param attendEmployeeId 项目经理
	 * @param attendMonth 日期
	 * 
	 * @return
	 */
	@RequiresPermissions("bizAttendBills:bizAttendBills:edit")
	@RequestMapping(value="bill")
	public String bill(Integer attendEmployeeId,String attendMonth,Model model,Integer storeId,
			String projectMode,Integer starLevel,String attendEmployeeRole){
		if(attendEmployeeId != null && StringUtils.isNotBlank(attendMonth)){
			//项目经理日签到数据
			List<BizAttendDay> attendDays = bizAttendDayService.findAttendDays(attendEmployeeId, attendMonth);
			
			//获取对应的底薪
			Double salary = bizStarBasicSalaryService.getSalary(attendMonth, storeId, projectMode, starLevel);
			
			model.addAttribute("salary", salary);
			model.addAttribute("attendDays", attendDays);
			model.addAttribute("attendEmployeeId", attendEmployeeId);
			model.addAttribute("attendMonth", attendMonth);
			model.addAttribute("attendEmployeeRole", attendEmployeeRole);
			
			model.addAttribute("projectMode", projectMode);
			model.addAttribute("storeId", storeId);
		}
		return "modules/attend/bizAttendDaysList";
	}
	
	/**
	 * 修改考勤单记录
	 * @param attendEmployeeId
	 * @param attendMonth
	 * @param model
	 * @param storeId
	 * @param projectMode
	 * @param starLevel
	 * @param attendEmployeeRole
	 * @return
	 */
	@RequiresPermissions("bizAttendBills:bizAttendBills:edit")
	@RequestMapping(value="form")
	public String form(Integer id,Integer attendEmployeeId,String attendMonth,Model model,Integer storeId,
			String projectMode,Integer starLevel,String attendEmployeeRole){
		if(attendEmployeeId != null && StringUtils.isNotBlank(attendMonth)){
			//项目经理日签到数据
			List<BizAttendDay> attendDays = bizAttendDayService.findAttendDays(attendEmployeeId, attendMonth);
			
			//获取对应的底薪
			Double salary = bizStarBasicSalaryService.getSalary(attendMonth, storeId, projectMode, starLevel);
			//更具id获取
			model.addAttribute("bizAttendBill", bizAttendBillService.get(id));
			
			model.addAttribute("salary", salary);
			model.addAttribute("attendDays", attendDays);
			model.addAttribute("attendEmployeeId", attendEmployeeId);
			model.addAttribute("attendMonth", attendMonth);
			model.addAttribute("attendEmployeeRole", attendEmployeeRole);
			
			model.addAttribute("projectMode", projectMode);
			model.addAttribute("storeId", storeId);
		}
		return "modules/attend/bizAttendDaysList";
	}
	/**
	 * insert 考勤单 并更新日次表已生成考勤单
	 * @param bizAttendBill
	 * @param attendEmployeeId
	 * @return
	 */
	@RequestMapping(value="createAttendBill")
	public String createAttendBill(BizAttendBill bizAttendBill,Integer attendEmployeeId,String attendMonth,String storeId,String projectMode,
									Model model){
		if(bizAttendBill.getId()==null){
			SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM");
			Date date = null;
			try {
					date = f2.parse(attendMonth);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			bizAttendBill.setAttendMonth(date);
			//insert考勤单
			bizAttendBillService.save(bizAttendBill);
			
			//获取managerID的list 以便于批量更新考勤单状态
			List<BizAttendDay> list = bizAttendDayService.findAttendDaysByEmployeeId(attendEmployeeId);
			
			if(list.size()!=0){
				//更新为以生成考勤单状态
				bizAttendDayService.upadteAttendDays(list);
			}
			
			return "redirect:"+Global.getAdminPath()+"/bizAttend/bizAttendBills/list";
		}else{
			//更新
			bizAttendBillService.update(bizAttendBill);
			return "redirect:"+Global.getAdminPath()+"/bizAttend/bizAttendBills/list";
		}
	}
	
	
	/**
	 * 生成考勤批次
	 * @param list
	 * @return
	 */
	@RequiresPermissions("bizAttendBills:bizAttendBills:edit")
	@RequestMapping(value="createBatch")
	public String createBatch(BizAttendBatch bizAttendBatch,String [] ids,String attendBatchMonth,Integer enginDepartId,Integer storeId){
		bizAttendBatch.setAttendBillCount(ids.length);
		
		bizAttendBatch.setAttendBatchMonth(attendBatchMonth);
		bizAttendBatch.setStoreId(storeId);
		bizAttendBatch.setEnginDepartId(enginDepartId);
		//保存后返回的主键
		int batchId = bizAttendBatchService.saveBatch(bizAttendBatch);
		//修改状态
		bizAttendBillService.updateStatus(ids, BizAttendBillConstantUtil.ALREADY_ATTEND_BATCH, batchId+"");
		
		return "redirect:"+Global.getAdminPath()+"/bizAttend/bizAttendBills/list";
	}
	
}
