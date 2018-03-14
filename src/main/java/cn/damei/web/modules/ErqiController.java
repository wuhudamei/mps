
package cn.damei.web.modules;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.common.config.Global;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.ErqiEntity;
import cn.damei.entity.modules.ErqiMoneyService;
import cn.damei.service.modules.OrderService;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(value = "${adminPath}/balancemid/Erqi")
public class ErqiController extends BaseController {

	@Autowired
	private ErqiMoneyService erqiMoneyService;
	
	@ModelAttribute
	public ErqiEntity get(@RequestParam(required=false) String id) {
		ErqiEntity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = erqiMoneyService.get(id);
		}
		if (entity == null){
			entity = new ErqiEntity();
		}
		return entity;
	}
	
	@RequiresPermissions("balancemid:ErqiEntity:view")
	@RequestMapping(value = {"list", ""})
	public String list(ErqiEntity ErqiEntity, HttpServletRequest request, HttpServletResponse response, Model model) {

			List<Integer> list = new ArrayList<Integer>();
			request.getSession().setAttribute("orderIds",list);

		
		return "modules/balancemid/ErqiEntityList";
	}
	@Autowired
	private OrderService orderService;
	@RequiresPermissions("balancemid:ErqiEntity:view")
	@RequestMapping(value = {"list2"})
	public String list2(ErqiEntity ErqiEntity, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		
		Page<ErqiEntity> page = erqiMoneyService.findPage(new Page<ErqiEntity>(request, response), ErqiEntity);
		model.addAttribute("page", page);
		model.addAttribute("ErqiEntity",ErqiEntity);	
		return "modules/balancemid/ErqiEntityList";
	}

	@RequestMapping(value = "confirmCheckSecondMoney")
	public @ResponseBody String confirmCheckSecondMoney(Integer orderId, RedirectAttributes redirectAttributes) {
		String result=null;
		try{
			result = erqiMoneyService.confirmCheckSecondMoney(orderId);
			if(result.equals("0")){
				erqiMoneyService.confirmCheckSecondMoneyPqc(orderId);	
			}
			
		}catch (Exception e){
			result = "2";
			e.printStackTrace();
			throw e;
		}

		
		return result;
	}
	

	@RequiresPermissions("balancemid:ErqiEntity:edit")
	@RequestMapping(value = "confirmCheckSecondMoneys")
	public String confirmCheckSecondMoneys(Integer [] orderIds, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		String result=null;
		try{
			List<Integer> list = new ArrayList<Integer>();
			if(orderIds != null){
				for (Integer integer : orderIds) {
					list.add(integer);
				}
			}
			
			
			List<Integer> orderIdSession = (List<Integer>) request.getSession().getAttribute("orderIds");
			for (Integer integer : orderIdSession) {
				if(!list.contains(integer)){
					list.add(integer);
				}
			}
			result = erqiMoneyService.confirmCheckSecondMoneys(list);
			if(result.equals("0")){
				erqiMoneyService.confirmCheckSecondMoneyPqcs(list);
			}
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}
		if(result.equals("0")){
			addMessage(redirectAttributes, "确认已收二期款成功");
		}else if(result.equals("1")){
			addMessage(redirectAttributes, "已收二期款,请不要重复提交！");
		}
		return "redirect:"+ Global.getAdminPath()+"/balancemid/Erqi/list2?repage";
	}
	

	@RequiresPermissions("balancemid:ErqiEntity:edit")
	@RequestMapping(value = "exportExcel")
	public void exportExcel(ErqiEntity erqiEntity,HttpServletResponse response){
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		HSSFWorkbook excel = erqiMoneyService.exportExcel(erqiEntity);
		ServletOutputStream out= null;
		try {  
			response.setContentType("application/binary;charset=utf-8"); 
			String headerStr =new String(("确认二期款"+sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");
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
	
	@RequestMapping(value="saveCheckValue")
	@ResponseBody
	public void saveCheckValue(Integer checkedStr,HttpServletRequest request){
		
			if(checkedStr != null){
				List<Integer> list2 = (List<Integer>) request.getSession().getAttribute("orderIds");

				if(list2 != null && list2.size() >0){
					
					if(!list2.contains(checkedStr)){
						list2.add(checkedStr);
					}else{

						Iterator<Integer> it = list2.iterator();
						while(it.hasNext()){
							if(it.next().equals(checkedStr)){
								it.remove();
							}
						}
					}
					request.getSession().setAttribute("orderIds", list2);
				}else{
					List<Integer> list = new ArrayList<Integer>();
					list.add(checkedStr);
					request.getSession().setAttribute("orderIds", list);
				}
				
			}
	}
}