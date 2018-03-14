package cn.damei.web.modules;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.BizNodePlan;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ExportExcelKanban;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.service.modules.BizNodePlanService;
import cn.damei.entity.modules.BizConstructionSchedule;
import cn.damei.service.modules.BizConstructionScheduleService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.ProgressKanban;
import cn.damei.service.modules.ProgressKanbanService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 进度看板
 * @author llp 
 * 2016/10/18
 */
@Controller
@RequestMapping(value = "${adminPath}/progresskanban/progressKanban")
public class ProgressKanbanController extends BaseController {

	@Autowired
	private ProgressKanbanService progressKanbanService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizConstructionScheduleService bizConstructionScheduleService;
	
	@Autowired
	private BizNodePlanService bizNodePlanService;

	@ModelAttribute
	public ProgressKanban get(@RequestParam(required = false) Integer id) {
		ProgressKanban entity = null;
		if (StringUtils.isNotBlank(id + "")) {
			entity = progressKanbanService.get(id);
		}
		if (entity == null) {
			entity = new ProgressKanban();
		}
		return entity;
	}

	@RequiresPermissions("progresskanban:progressKanban:view")
	@RequestMapping(value = { "preList", "" })
	public String preList(ProgressKanban progressKanban, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		User user = UserUtils.getUser();
		//过滤门店
		if(null == progressKanban.getStoreId()){
			if(null != user.getStoreId()){
				progressKanban.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(progressKanban.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						progressKanban.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}else{
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						progressKanban.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		return "modules/progressKanban/kanbanList";
	}
	
	/**
	 * 主页list页面
	 * @param progressKanban
	 * @param request
	 * @param response
	 * @param model
	 * @return list
	 */
	@RequiresPermissions("progresskanban:progressKanban:view")
	@RequestMapping(value = { "list", "" })
	public String list(ProgressKanban progressKanban, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		User user = UserUtils.getUser();
		//过滤门店
		if(null == progressKanban.getStoreId()){
			if(null != user.getStoreId()){
				progressKanban.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(progressKanban.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						progressKanban.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}else{
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						progressKanban.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		List<Integer> list = new ArrayList<Integer>();
		Page<ProgressKanban> page = progressKanbanService.findPage(new Page<ProgressKanban>(request, response),
				progressKanban);
		
		Integer stroeID = Integer.valueOf(progressKanban.getStoreId());//获取当前门店编号
		String isOldHouse = progressKanban.getHouseIsNew();//新牢房
		logger.info("选取的门店编号：" + stroeID+"新老房："+isOldHouse);
		
		//获取所有的节点
		List<BizConstructionSchedule> csList = bizConstructionScheduleService.getByStoreIdAndDelflag(stroeID,isOldHouse);
		for(BizConstructionSchedule bcs:csList){
			logger.info("获得所有节点名称：" + bcs.getConstructionScheduleName());
			list.add(Integer.valueOf(bcs.getSort()));
		}
		List<BizNodePlan> npList = null;
		if(list.size() > 0){
			npList = bizNodePlanService.getByOrderIdListInIndex(list);
		}
		
		model.addAttribute("page", page);
		model.addAttribute("nodePlanList", npList);
		model.addAttribute("constructionScheduleList", csList);
		return "modules/progressKanban/kanbanList";
	}
	
	/**
	 * 导出excel
	 * @throws IOException 
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
	public void KanbanExport(ProgressKanban progressKanban, BizNodePlan bizNodePlan, HttpServletRequest request, HttpServletResponse response,
							 RedirectAttributes redirectAttributes, String storeID) throws IOException{
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		ServletOutputStream ouputStream= null;//创建一个输出流对象
		logger.info("门店编号："+progressKanban.getStoreId()+"\t新老房："+progressKanban.getHouseIsNew());
		List<Integer> list = new ArrayList<Integer>();
		List<ProgressKanban> listKan = progressKanbanService.findOrderByStoreId(Integer.valueOf(storeID));

		//获取所有的节点
		List<BizConstructionSchedule> csList = bizConstructionScheduleService.getByStoreIdAndDelflag(Integer.valueOf(storeID),progressKanban.getHouseIsNew());
		for(BizConstructionSchedule bcs:csList){
			logger.info("获得所有节点名称：" + bcs.getConstructionScheduleName());
			list.add(Integer.valueOf(bcs.getSort()));
		}
		
		List<BizNodePlan> npList = bizNodePlanService.getByOrderIdListInIndex(list);
		
		HSSFWorkbook exportKanban = ExportExcelKanban.exportKanban(listKan,csList,npList);
		
		try {  
			response.setContentType("application/binary;charset=utf-8"); 
			String headerStr =new String(("工程进度看板模板"+sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");//headerString为中文时转码  
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");//filename是下载的xls的名
			ouputStream = response.getOutputStream();    
			exportKanban.write(ouputStream);  
			ouputStream.flush();    
			ouputStream.close();
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }  
    }  
}
