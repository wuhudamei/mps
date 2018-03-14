package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.LaborCapital;
import cn.damei.service.modules.LaborCapitalService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value="${adminPath}/laborCapital/laborCapital/")
@Controller
public class LaborCapitalController extends BaseController {
	
	@Autowired
	private LaborCapitalService laborCapitalService;
	
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@RequiresPermissions("laborCapital:laborCapital:view")
	@RequestMapping(value="queryOrderList")
	public String query(@ModelAttribute LaborCapital laborCapital,HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();
		// 过滤门店
		laborCapitalService.store(laborCapital, model);
		bizEmployeeService2.projectMode(laborCapital, model, user, this);
		return "modules/laborCapital/laborCapital";
	}
	/**
	* @Description:工人接单和罚款查询
	* @Author zhangkangjian
	* @param
	* @return
	* @Date 2017/11/22 14:59
	*/
	@RequiresPermissions("laborCapital:laborCapital:view")
	@RequestMapping(value="queryOrderList1")
	public String query1(@ModelAttribute LaborCapital laborCapital,HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();
		// 过滤门店
		laborCapitalService.store(laborCapital, model);
		// 过滤工程模式
		bizEmployeeService2.projectMode(laborCapital, model, user, this);
		Page<LaborCapital> page = laborCapitalService.findPage(new Page<LaborCapital>(request, response), laborCapital);
		model.addAttribute("page", page);
		return "modules/laborCapital/laborCapital";
	}

	/**
	* @Description: 导出管理扣款/延期扣款/公司扣款任意一个值不为零的所有数据
	* @Author zhangkangjian
	* @param
	* @return
	* @Date 2017/11/23 14:49
	*/
	@RequestMapping(value="exportexcel")
	@ResponseBody
	public void exportexcel(LaborCapital laborCapital,HttpServletResponse response){
		laborCapitalService.exportDetailExcel(laborCapital,response);
	}

}
