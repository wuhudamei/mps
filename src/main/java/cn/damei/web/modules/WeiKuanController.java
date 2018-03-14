/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.WeiKuanEntity;
import cn.damei.entity.modules.WeiKuanService;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 财务确认尾款Controller
 * @author 梅浩
 * @version 2016-12-28
 */
@Controller
@RequestMapping(value = "${adminPath}/balancecomplete/WeiKuan")
public class WeiKuanController extends BaseController {

	@Autowired
	private WeiKuanService weiKuanService;
	
	@ModelAttribute
	public WeiKuanEntity get(@RequestParam(required=false) String id) {
		WeiKuanEntity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weiKuanService.get(id);
		}
		if (entity == null){
			entity = new WeiKuanEntity();
		}
		return entity;
	}
	
	@RequiresPermissions("balancecomplete:WeiKuanEntity:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeiKuanEntity WeiKuanEntity, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		/*List<Integer> orderIds = (List<Integer>) request.getSession().getAttribute("wkOrderIds");
		if(orderIds != null && orderIds.size()>0){*/
		List<Integer> list = new ArrayList<Integer>();
		request.getSession().setAttribute("wkOrderIds",list);
		//}
		
		return "modules/balancecomplete/WeiKuanEntityList";
	}
	@RequiresPermissions("balancecomplete:WeiKuanEntity:view")
	@RequestMapping(value = "list2")
	public String list2(WeiKuanEntity WeiKuanEntity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeiKuanEntity> page = weiKuanService.findPage(new Page<WeiKuanEntity>(request, response), WeiKuanEntity);
		model.addAttribute("page", page);
		model.addAttribute("WeiKuanEntity",WeiKuanEntity);
		return "modules/balancecomplete/WeiKuanEntityList";
	}

	@RequestMapping(value = "confirmCheckEndMoney")
	public @ResponseBody String confirmCheckEndMoney(Integer orderId, RedirectAttributes redirectAttributes) {
		String result=null;
		try{
			result=weiKuanService.confirmCheckEndMoney(orderId);
			if(result.equals("0")){
				weiKuanService.confirmCheckEndMoneyPbc(orderId);
			}
		}catch (Exception e){
			result="2";
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	/**
	 * 批量
	 * @param orderIds
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("balancecomplete:WeiKuanEntity:edit")
	@RequestMapping(value = "confirmCheckEndMoneys")
	public String confirmCheckEndMoneys(Integer [] orderIds, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		String result=null;
		try{
			List<Integer> list = new ArrayList<Integer>();
			
			if(orderIds != null){
				for (Integer integer : orderIds) {
					list.add(integer);
				}
			}
			
			List<Integer> orderIdSession = (List<Integer>) request.getSession().getAttribute("wkOrderIds");
			for (Integer integer : orderIdSession) {
				if(!list.contains(integer)){
					list.add(integer);
				}
			}
			
			result = weiKuanService.confirmCheckEndMoneys(list);
			if(result.equals("0")){
				weiKuanService.confirmCheckEndMoneyPbcs(list);	
			}
			
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}
		if(result.equals("0")){
			addMessage(redirectAttributes, "确认已收尾款成功!");
		}else if(result.equals("1")){
			addMessage(redirectAttributes, "已收尾款,请不要重复提交!");
		}
		
		return "redirect:"+ Global.getAdminPath()+"/balancecomplete/WeiKuan/list2?repage";
	}
	
	/**
	 * 导出execl
	 * @param orderIds
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("balancecomplete:WeiKuanEntity:edit")
	@RequestMapping(value = "exportExcel")
	public void exportExcel(WeiKuanEntity weiKuanEntity,HttpServletResponse response){
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		HSSFWorkbook excel = weiKuanService.exportExcel(weiKuanEntity);
		ServletOutputStream out= null;//创建一个输出流对象
		try {  
			response.setContentType("application/binary;charset=utf-8"); 
			String headerStr =new String(("确认尾款"+sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");//headerString为中文时转码  
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");//filename是下载的xls的名
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
	
	@RequestMapping(value="saveCheckWKValue")
	@ResponseBody
	public void saveCheckValue(Integer checkedStr,HttpServletRequest request){
		
		if (checkedStr != null) {
			List<Integer> list2 = (List<Integer>) request.getSession().getAttribute("wkOrderIds");
			// session 获取list
			if (list2 != null && list2.size() > 0) {

				if (!list2.contains(checkedStr)) {
					list2.add(checkedStr);
				} else {
					// 判断是否有重复
					Iterator<Integer> it = list2.iterator();
					while (it.hasNext()) {
						if (it.next().equals(checkedStr)) {
							it.remove();
						}
					}
				}
				request.getSession().setAttribute("wkOrderIds", list2);
			} else {
				List<Integer> list = new ArrayList<Integer>();
				list.add(checkedStr);
				request.getSession().setAttribute("wkOrderIds", list);
			}

		}
	}
}