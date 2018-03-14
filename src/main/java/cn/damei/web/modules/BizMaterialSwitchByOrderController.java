package cn.damei.web.modules;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.Dict;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.common.persistence.Page;
import cn.damei.entity.modules.BizMaterialsSwitchByOrder;
import cn.damei.service.modules.BizMaterialsSwitchByOrderService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/bizMaterialSortedByOrder/materialSwitchByOrder")
public class BizMaterialSwitchByOrderController extends BaseController {

	@Autowired
	private BizMaterialsSwitchByOrderService bizMaterialsSwitchByOrderService;
	@ModelAttribute
	public BizMaterialsSwitchByOrder get(@RequestParam(required=false) String id) {
		BizMaterialsSwitchByOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			
		}
		if (entity == null){
			entity = new BizMaterialsSwitchByOrder();
		}
		return entity;
	}

	@RequestMapping(value = "/switchList")
	public String listPage(BizMaterialsSwitchByOrder bizMaterialsSwitchByOrder, HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();

		if(null==bizMaterialsSwitchByOrder.getStoreId()){
			if(null!=user.getStoreId()){
				bizMaterialsSwitchByOrder.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}

		List<Dict>listStatus=bizMaterialsSwitchByOrderService.findPurchaseStatus();
		model.addAttribute("listStatus", listStatus);
		if(bizMaterialsSwitchByOrder.getStatus()!=null){
			String[] status = bizMaterialsSwitchByOrder.getStatus().toString().split(",");
			if(null!=status && status.length>0){
				bizMaterialsSwitchByOrder.setPurchaseStatusList(Arrays.asList(status));
			}
		}else{
			bizMaterialsSwitchByOrder.setStatus("90");
			bizMaterialsSwitchByOrder.setPurchaseStatusList(Arrays.asList("90"));
		}
		Page<BizMaterialsSwitchByOrder> page = bizMaterialsSwitchByOrderService.findPage(new Page<BizMaterialsSwitchByOrder>(request, response), bizMaterialsSwitchByOrder); 
		model.addAttribute("page", page);
		return "modules/mateialSortedByOrder/bizPurchaseSwitchList";
		
	}
	

	@RequestMapping(value = "/detail")
	public String detail(BizMaterialsSwitchByOrder bizMaterialsSwitchByOrder, HttpServletRequest request, HttpServletResponse response, Model model,String key){

		BizMaterialsSwitchByOrder list=bizMaterialsSwitchByOrderService.findByOrderId(bizMaterialsSwitchByOrder.getOrderId());
		model.addAttribute("bizMaterialsSwitchByOrder",list);

		List<BizMaterialsSwitchByOrder> findHejiByOrderId = bizMaterialsSwitchByOrderService.findHejiByOrderId(bizMaterialsSwitchByOrder.getOrderId()); 
		model.addAttribute("findHejiByOrderId", findHejiByOrderId); 

		List<BizMaterialsSwitchByOrder> findMingxiByOrderId = bizMaterialsSwitchByOrderService.findMingxiByOrderId(bizMaterialsSwitchByOrder.getOrderId()); 
		model.addAttribute("findMingxiByOrderId", findMingxiByOrderId); 
		return "modules/mateialSortedByOrder/bizPurchaseSwitchDetails";
		
	}
	

	@RequestMapping(value = "/export")
	public void export(HttpServletResponse response, BizMaterialsSwitchByOrder bizMaterialsSwitchByOrder, HttpServletRequest request) {

		String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		if(bizMaterialsSwitchByOrder.getStatus()!=null){
			String[] status = bizMaterialsSwitchByOrder.getStatus().toString().split(",");
			if(null!=status && status.length>0){
				bizMaterialsSwitchByOrder.setPurchaseStatusList(Arrays.asList(status));
			}
		}
		HSSFWorkbook excel = bizMaterialsSwitchByOrderService.exportExcel(bizMaterialsSwitchByOrder);

		ServletOutputStream out = null;
		try {
			response.setContentType("application/binary;charset=UTF-8");
			String excelHead = new String(("开关面板采购明细-" + now).getBytes(), "ISO8859-1");
			response.setHeader("Content-disposition", "attachment; filename=" + excelHead + ".xls");
			out = response.getOutputStream();
			excel.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}