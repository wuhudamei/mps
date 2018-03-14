
package cn.damei.web.modules;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.mobile.Manager.BusinessPicture;
import cn.damei.service.mobile.Manager.BusinessPictureService;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.ExportProjectExcel;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.Auxiliary;
import cn.damei.entity.modules.BizPurchaseVo;
import cn.damei.service.modules.AuxiliaryService;
import cn.damei.service.modules.BizPurchaseVoService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "${adminPath}/purchase/bizPurchase")
public class BizPurchaseController extends BaseController {

	@Autowired
	private BizPurchaseVoService bizPurchaseVoService;
	@Autowired
	private AuxiliaryService auxiliarySerivice;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BusinessPictureService businessPictureService;
	
	@ModelAttribute
	public BizPurchaseVo get(@RequestParam(required=false) Integer id) {
		BizPurchaseVo entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizPurchaseVoService.get(id);
		}
		if (entity == null){
			entity = new BizPurchaseVo();
		}
		return entity;
	}
	

	@RequiresPermissions("purchase:bizPurchase:view")
	@RequestMapping(value = "listPage")
	public String listPage(BizPurchaseVo bizPurchaseVo, HttpServletRequest request, HttpServletResponse response, Model model){
		if(UserUtils.getUser().getStoreId()!=null)
		{

			bizPurchaseVo.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}
		else{

		if(bizPurchaseVo.getStoreId()!= null && bizPurchaseVo.getStoreId() ==1)
			{

				bizPurchaseVo.setStoreId(null);
			}
		}
		User user = UserUtils.getUser();
		if(null !=user.getEmpId()){
			BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
			if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
				bizPurchaseVo.setProjectMode(null);
			}else{
				bizPurchaseVo.setProjectMode(be.getProjectMode());
				model.addAttribute("projectModeEnable", true);
			}
		}else{
			bizPurchaseVo.setProjectMode(null);
		}
		if(null == bizPurchaseVo.getStatus()|| "".equals(bizPurchaseVo.getStatus())){
			bizPurchaseVo.setStatus("10");
		}
		return "modules/purchase/bizPurchaseList";
		
	}
	@RequiresPermissions("purchase:bizPurchase:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPurchaseVo bizPurchaseVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(UserUtils.getUser().getStoreId()!=null)
		{

			bizPurchaseVo.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}
		else{

		if(bizPurchaseVo.getStoreId()!= null && bizPurchaseVo.getStoreId() ==1)
			{

				bizPurchaseVo.setStoreId(null);
			}
		}
		if(null == bizPurchaseVo.getStatus()){
			bizPurchaseVo.setStatus("10");
		}
		bizPurchaseVo.setPurchaseType(ConstantUtils.AUXILIARY_NUMBER);
		if(bizPurchaseVo.getProjectMode() == null|| "".equals(bizPurchaseVo.getProjectMode())){
			User user = UserUtils.getUser();
			if(null !=user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
					bizPurchaseVo.setProjectMode(null);
				}else{
					bizPurchaseVo.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			}else{
				bizPurchaseVo.setProjectMode(null);
			}
		}
		Page<BizPurchaseVo> page = bizPurchaseVoService.findPage(new Page<BizPurchaseVo>(request, response), bizPurchaseVo);

		model.addAttribute("page", page);
		return "modules/purchase/bizPurchaseList";
	}
	

	@RequiresPermissions("purchase:bizPurchase:view")
	@RequestMapping(value = "details")
	public String details(Integer id , HttpServletRequest request, HttpServletResponse response, Model model){

		BizPurchaseVo bizPurchaseVo =  bizPurchaseVoService.findById(id);
		BizEmployee2 employee = bizEmployeeService2.findEmployeeNameById(bizPurchaseVo.getApplyEmployee());
		
		bizPurchaseVo.setApplyName(employee.getRealname());
		Integer storeId = bizPurchaseVo.getStoreId();
		List<Auxiliary> auxiliarys = auxiliarySerivice.findListPriceByPurchaseId(id,storeId,bizPurchaseVo.getApplyTime());
		
		model.addAttribute("bizPurchaseVo", bizPurchaseVo);
		model.addAttribute("auxiliarys", auxiliarys);
		return "modules/purchase/auxiliaryDetails";
	}
	


	@RequestMapping(value = "seePhoto")
	@ResponseBody
	public Map<Object, Object> seePhoto(Integer id, Model model, HttpServletRequest request) throws IOException{

		List<BusinessPicture> pictures = businessPictureService.queryPicture(id, ConstantUtils.PICTURE_BUSINESS_TYPE_5);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("pictures", pictures);
		model.addAttribute("baseUrl", baseUrl);

		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", pictures);
		return mapObject;
	}
	

	@RequiresPermissions("purchase:bizPurchase:view")
	@RequestMapping(value="sendAuxiliaryList")
	public String sendAuxiliaryList(BizPurchaseVo bizPurchaseVo, HttpServletRequest request, HttpServletResponse response, Model model){
		if(UserUtils.getUser().getStoreId()!=null)
		{

			bizPurchaseVo.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}
		else{

		if(bizPurchaseVo.getStoreId()!= null && bizPurchaseVo.getStoreId() == 1)
			{

				bizPurchaseVo.setStoreId(null);
			}
		}
		bizPurchaseVo.setStatus("40");
		bizPurchaseVo.setPurchaseType(ConstantUtils.AUXILIARY_NUMBER);
		Page<BizPurchaseVo> page = bizPurchaseVoService.findPage(new Page<BizPurchaseVo>(request, response), bizPurchaseVo);
		model.addAttribute("page", page);
		return "modules/purchase/sendAuxiliaryList";
	}

	@RequiresPermissions("purchase:bizPurchase:view")
	@RequestMapping(value="exportExcel")
	public String exportExcel(Integer id,HttpServletResponse response){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		ServletOutputStream ouputStream= null;
		

		BizPurchaseVo bizPurchaseVo =  bizPurchaseVoService.findById(id);
		BizEmployee2 employee = bizEmployeeService2.findEmployeeNameById(bizPurchaseVo.getApplyEmployee());
		bizPurchaseVo.setApplyName(employee.getRealname());
		Integer storeId = bizPurchaseVo.getStoreId();
		List<Auxiliary> auxiliarys = auxiliarySerivice.findListPriceByPurchaseId(id,storeId,bizPurchaseVo.getApplyTime());
		
		HSSFWorkbook projectExcel = ExportProjectExcel.exportPurchaseOrders(bizPurchaseVo,employee,auxiliarys);
		try {  
			response.setContentType("application/binary;charset=utf-8"); 
			String headerStr =new String((bizPurchaseVo.getPurchaseCode()+"--"+sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");
			ouputStream = response.getOutputStream();    
			projectExcel.write(ouputStream);  
			ouputStream.flush();    
			ouputStream.close();
		} catch (IOException ex) {  
            ex.printStackTrace();  
        }  
		
		return "";
	}
}