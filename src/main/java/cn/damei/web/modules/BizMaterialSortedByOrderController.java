package cn.damei.web.modules;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

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
import cn.damei.entity.modules.BizMaterialsSortedByOrder;
import cn.damei.service.modules.BizMaterialsSortedByOrderService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 材料类按订单汇总查询辅料Controller
 * @author 王硕
 * @version 2017-10-20
 */
@Controller
@RequestMapping(value = "${adminPath}/bizMaterialSortedByOrder/materialSortedByOrder")
public class BizMaterialSortedByOrderController extends BaseController {

	@Autowired
	private BizMaterialsSortedByOrderService bizMaterialsSortedByOrderService;
	@Autowired
	private DataSource dataSource;
	@ModelAttribute
	public BizMaterialsSortedByOrder get(@RequestParam(required=false) String id) {
		BizMaterialsSortedByOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			
		}
		if (entity == null){
			entity = new BizMaterialsSortedByOrder();
		}
		return entity;
	}
	//辅料列表页
	@RequestMapping(value = "/AccessoriesList")
	public String listPage(BizMaterialsSortedByOrder bizMaterialsSortedByOrder, HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizMaterialsSortedByOrder.getStoreId()){
			if(null!=user.getStoreId()){
				bizMaterialsSortedByOrder.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		List<Dict>listStatus=bizMaterialsSortedByOrderService.findPurchaseStatus();
		model.addAttribute("listStatus", listStatus);
		if(null!=bizMaterialsSortedByOrder.getStatus()){
			String[] status = bizMaterialsSortedByOrder.getStatus().toString().split(",");
			if(null!=status && status.length>0){
				bizMaterialsSortedByOrder.setPurchaseStatusList(Arrays.asList(status));
			}
		}else {
			bizMaterialsSortedByOrder.setStatus("90");
			bizMaterialsSortedByOrder.setPurchaseStatusList(Arrays.asList("90"));
		}
		Page<BizMaterialsSortedByOrder> page = bizMaterialsSortedByOrderService.findPage(new Page<BizMaterialsSortedByOrder>(request, response), bizMaterialsSortedByOrder); 
		model.addAttribute("page", page);
		return "modules/mateialSortedByOrder/bizPurchaseAccessoriesList";
		
	}
	
	//辅料详情页
	@RequestMapping(value = "/detail")
	public String detail(BizMaterialsSortedByOrder bizMaterialsSortedByOrder, HttpServletRequest request, HttpServletResponse response, Model model,String key){
		//查询基本信息
		BizMaterialsSortedByOrder list=bizMaterialsSortedByOrderService.findByOrderId(bizMaterialsSortedByOrder.getOrderId());
		model.addAttribute("bizMaterialsSortedByOrder",list);
		//查询辅料合计信息
		List<BizMaterialsSortedByOrder> findHejiByOrderId = bizMaterialsSortedByOrderService.findHejiByOrderId(bizMaterialsSortedByOrder.getOrderId()); 
		model.addAttribute("findHejiByOrderId", findHejiByOrderId); 
		//查询辅料明细信息
		List<BizMaterialsSortedByOrder> findMingxiByOrderId = bizMaterialsSortedByOrderService.findMingxiByOrderId(bizMaterialsSortedByOrder.getOrderId()); 
		model.addAttribute("findMingxiByOrderId", findMingxiByOrderId); 
		return "modules/mateialSortedByOrder/bizPurchaseAccessoriesDetails";
		
	}
	
	//辅料导出
	@RequestMapping(value = "/export")
	public void export(HttpServletResponse response, BizMaterialsSortedByOrder bizMaterialsSortedByOrder, HttpServletRequest request) {

		String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		if(bizMaterialsSortedByOrder.getStatus()!=null){
			String[] status = bizMaterialsSortedByOrder.getStatus().toString().split(",");
			if(null!=status && status.length>0){
				bizMaterialsSortedByOrder.setPurchaseStatusList(Arrays.asList(status));
			}
		}
		HSSFWorkbook excel = bizMaterialsSortedByOrderService.exportExcel(bizMaterialsSortedByOrder);
		ServletOutputStream out = null;
		try {

			response.setContentType("application/binary;charset=UTF-8");
			String excelHead = new String(("辅料采购明细-" + now).getBytes(), "ISO8859-1");

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