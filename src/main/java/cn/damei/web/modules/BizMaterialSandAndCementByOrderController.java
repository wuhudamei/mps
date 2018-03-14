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
import cn.damei.entity.modules.BizMaterialsSandAndCementByOrder;
import cn.damei.service.modules.BizMaterialsSandAndCementByOrderService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 材料类按订单汇总查询沙子水泥Controller
 * @author 王硕
 * @version 2017-10-20
 */
@Controller
@RequestMapping(value = "${adminPath}/bizMaterialSortedByOrder/materialSandAndCementByOrder")
public class BizMaterialSandAndCementByOrderController extends BaseController {

	@Autowired
	private BizMaterialsSandAndCementByOrderService bizMaterialsSandAndCementByOrderService;
	@ModelAttribute
	public BizMaterialsSandAndCementByOrder get(@RequestParam(required=false) String id) {
		BizMaterialsSandAndCementByOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			
		}
		if (entity == null){
			entity = new BizMaterialsSandAndCementByOrder();
		}
		return entity;
	}
	//沙子水泥列表页
	@RequestMapping(value = "/SandAndCementList")
	public String listPage(BizMaterialsSandAndCementByOrder bizMaterialsSandAndCementByOrder, HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizMaterialsSandAndCementByOrder.getStoreId()){
			if(null!=user.getStoreId()){
				bizMaterialsSandAndCementByOrder.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		//查询采购单的三个状态
		List<Dict>listStatus=bizMaterialsSandAndCementByOrderService.findPurchaseStatus();
		model.addAttribute("listStatus", listStatus);
		if(null!=bizMaterialsSandAndCementByOrder.getStatus()){
			String[] status = bizMaterialsSandAndCementByOrder.getStatus().toString().split(",");
			if(null!=status && status.length>0){
				bizMaterialsSandAndCementByOrder.setPurchaseStatusList(Arrays.asList(status));
			}
		}else {
			bizMaterialsSandAndCementByOrder.setStatus("90");
			bizMaterialsSandAndCementByOrder.setPurchaseStatusList(Arrays.asList("90"));
		}
		Page<BizMaterialsSandAndCementByOrder> page = bizMaterialsSandAndCementByOrderService.findPage(new Page<BizMaterialsSandAndCementByOrder>(request, response), bizMaterialsSandAndCementByOrder); 
		model.addAttribute("page", page);
		return "modules/mateialSortedByOrder/bizPurchaseSandAndCementList";
		
	}
	
	//沙子水泥详情页
	@RequestMapping(value = "/detail")
	public String detail(BizMaterialsSandAndCementByOrder bizMaterialsSandAndCementByOrder, HttpServletRequest request, HttpServletResponse response, Model model,String key){
		//查询基本信息
		BizMaterialsSandAndCementByOrder list=bizMaterialsSandAndCementByOrderService.findByOrderId(bizMaterialsSandAndCementByOrder.getOrderId());
		model.addAttribute("bizMaterialsSandAndCementByOrder",list);
		//查询辅料合计信息
		List<BizMaterialsSandAndCementByOrder> findHejiByOrderId = bizMaterialsSandAndCementByOrderService.findHejiByOrderId(bizMaterialsSandAndCementByOrder.getOrderId()); 
		model.addAttribute("findHejiByOrderId", findHejiByOrderId); 
		//查询辅料明细信息
		List<BizMaterialsSandAndCementByOrder> findMingxiByOrderId = bizMaterialsSandAndCementByOrderService.findMingxiByOrderId(bizMaterialsSandAndCementByOrder.getOrderId()); 
		model.addAttribute("findMingxiByOrderId", findMingxiByOrderId); 
		return "modules/mateialSortedByOrder/bizPurchaseSandAndCementDetails";
		
	}
	
	//辅料导出
	@RequestMapping(value = "/export")
	public void export(HttpServletResponse response, BizMaterialsSandAndCementByOrder bizMaterialsSandAndCementByOrder, HttpServletRequest request) {

		String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		if(bizMaterialsSandAndCementByOrder.getStatus()!=null){
			String[] status = bizMaterialsSandAndCementByOrder.getStatus().toString().split(",");
			if(null!=status && status.length>0){
				bizMaterialsSandAndCementByOrder.setPurchaseStatusList(Arrays.asList(status));
			}
		}
		HSSFWorkbook excel = bizMaterialsSandAndCementByOrderService.exportExcel(bizMaterialsSandAndCementByOrder);

		ServletOutputStream out = null;
		try {

			response.setContentType("application/binary;charset=UTF-8");
			String excelHead = new String(("沙子水泥采购明细-" + now).getBytes(), "ISO8859-1");

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