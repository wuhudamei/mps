package cn.damei.web.modules;

import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizNodePlanProject;
import cn.damei.entity.modules.BizTraditionOrder;
import cn.damei.service.modules.BizProjectProgressBoningTraditionOrderService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping(value = "${adminPath}/projectprogressboning/bizProjectProgressBoningTraditionOrder")
public class BizProjectProgressBoningTraditionOrderController extends BaseController {

	@Autowired
	private BizProjectProgressBoningTraditionOrderService bizProjectProgressBoningTraditionOrderService;

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	

	@ModelAttribute
	public BizTraditionOrder get(@RequestParam(required = false) Integer id) {
		BizTraditionOrder entity = null;
		if (StringUtils.isNotBlank(id+"")) {
			entity = bizProjectProgressBoningTraditionOrderService.get(id);
		}
		if (entity == null) {
			entity = new BizTraditionOrder();
		}
		return entity;
	}

		
	@RequiresPermissions("projectprogressboning:bizProjectProgressBoningTraditionOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizTraditionOrder bizTraditionOrder, Model model, HttpServletRequest request, HttpServletResponse response) {

		return "modules/projectprogressboning/bizProjectProgressBoningList3";
	}

	@RequiresPermissions("projectprogressboning:bizProjectProgressBoningTraditionOrder:view")
	@RequestMapping(value = {"loadList", ""})
	public String loadList(BizTraditionOrder bizTraditionOrder, Model model, HttpServletRequest request, HttpServletResponse response) {
		










		

		Page<BizTraditionOrder> page = bizProjectProgressBoningTraditionOrderService.findPage(new Page<BizTraditionOrder>(request, response), bizTraditionOrder);
		List<BizTraditionOrder> list = page.getList();
		for(BizTraditionOrder order :list){
			List<BizNodePlanProject> PList = bizProjectProgressBoningTraditionOrderService.findPlanList(order.getOrderId());
			order.setNodePlanList(PList);
		}
		
		model.addAttribute("page", page);
		return "modules/projectprogressboning/bizProjectProgressBoningList3";
	}

	
	
	@RequestMapping(value="exportExcel")
	public void exportExcel(BizTraditionOrder bizTraditionOrder, HttpServletResponse response) throws Exception{
		User user = UserUtils.getUser();

		if(null==bizTraditionOrder.getStoreId()){
			if(null!=user.getStoreId()){
				bizTraditionOrder.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}

		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		HSSFWorkbook excel = bizProjectProgressBoningTraditionOrderService.exportExcel(bizTraditionOrder);
		ServletOutputStream out= null;
		try {
			response.setContentType("application/binary;charset=utf-8");
			String headerStr =new String(("传统订单工程进度大看板"+sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");
			out = response.getOutputStream();
			excel.write(out);
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if(out != null){
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}