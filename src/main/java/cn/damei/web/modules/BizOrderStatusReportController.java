package cn.damei.web.modules;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizOrderStatusReport;
import cn.damei.entity.modules.BizOrderStatusReportService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.Dict;
import cn.damei.entity.modules.User;
import cn.damei.service.modules.DictService;
import cn.damei.common.utils.UserUtils;

/**
 * 大统计表-大阶段(只查询订单状态是100至400 的项目)
 * @author llp
 * @version 2016-11-29
 */
@Controller
@RequestMapping(value = "${adminPath}/bizorderstatusreport/bizOrderStatusReport")
public class BizOrderStatusReportController extends BaseController {

	/*private static Logger logger = LoggerFactory.getLogger(BizConfirmStartController.class);*/

	@Autowired
	private BizOrderStatusReportService bizOrderStatusReportService;
	@Autowired
	private DictService dictService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	/**
	 * 准备列出大统计表
	 * 大阶段(只查询订单状态是100至400 的项目)列表
	 */
	@RequiresPermissions("bizorderstatusreport:bizOrderStatusReport:view")
	@RequestMapping(value = { "preList", "" })
	public String packageList(BizOrderStatusReport bizOrderStatusReport, Model model, 
			HttpServletRequest request) {
		User user = UserUtils.getUser();
		//过滤门店
		if(null == bizOrderStatusReport.getStoreId()){
			if(null != user.getStoreId()){
				bizOrderStatusReport.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(bizOrderStatusReport.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizOrderStatusReport.setProjectMode(be.getProjectMode());
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
						bizOrderStatusReport.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		/**获取所有的订单状态**/
		List<Dict> dictList = dictService.getByType("order_status");
		
		model.addAttribute("dictList", dictList);
		return "modules/bizconfirmstart/bizStatusReportList";
	}

	/**
	 * 列出大统计表
	 * 大阶段(只查询订单状态是100至400 的项目)列表
	 */
	@RequiresPermissions("bizorderstatusreport:bizOrderStatusReport:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizOrderStatusReport bizOrderStatusReport, Model model, HttpServletResponse response, 
			HttpServletRequest request) {
		User user = UserUtils.getUser();
		//过滤门店
		if(null == bizOrderStatusReport.getStoreId()){
			if(null != user.getStoreId()){
				bizOrderStatusReport.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(bizOrderStatusReport.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizOrderStatusReport.setProjectMode(be.getProjectMode());
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
						bizOrderStatusReport.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		/**获取所有的订单状态**/
		List<Dict> dictList = dictService.getByType("order_status");
		
		Page<BizOrderStatusReport> page = bizOrderStatusReportService.findPage(new Page<BizOrderStatusReport>(request, response),
				bizOrderStatusReport);
		
		List<BizOrderStatusReport> list = bizOrderStatusReportService.getByStoreID(bizOrderStatusReport.getStoreId());
		
		if(StringUtils.isBlank(bizOrderStatusReport.getProjectMode())){
			model.addAttribute("modeType", "");
		}else if(bizOrderStatusReport.getProjectMode().equals("1")){
			model.addAttribute("modeType", "1");
		}else if(bizOrderStatusReport.getProjectMode().equals("2")){
			model.addAttribute("modeType", "2");
		}
		
		model.addAttribute("page", page);
		model.addAttribute("list", list);
		model.addAttribute("dictList", dictList);
		return "modules/bizconfirmstart/bizStatusReportList";
	}
}
