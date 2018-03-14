package cn.damei.web.mobile.Manager;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.AppOrder;
import cn.damei.entity.mobile.Manager.AppOrderCadfile;
import cn.damei.service.mobile.Manager.AppOrderService;

/**
 * 订单管理Controller
 * 
 * @author wyb
 * @version 2016-09-08
 */
@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class AppOrderController {

	@Autowired
	private AppOrderService appOrderService;
	
	
	//订单列表页
	@RequestMapping(value = { "appOrderList", "" })
	public String appOrderList(AppOrder appOrder,HttpServletRequest request, Model model) {
		//获取项目经理
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		appOrder.setItemManagerId(manager.getId());
		
		//通过项目经理名称查询该项目经理下的所有订单
		List<AppOrder> order = appOrderService.findAppOrderByitemManager(appOrder);
		//查询该项目经理下所有订单的状态
		List<String> stateName = appOrderService.selectState(appOrder.getItemManagerId());
		
		model.addAttribute("stateName", stateName);
		model.addAttribute("order", order);
		return "mobile/modules/Manager/myorder";
	}
	
	
	//异步ajax
	@RequestMapping(value = { "appOrderCondition", "" })
	public @ResponseBody List<AppOrder> appOrderCondition(AppOrder appOrder,HttpServletRequest request) {
		
		
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		appOrder.setItemManagerId(manager.getId());
		
		List<AppOrder> order = appOrderService.findAppOrderByitemManager(appOrder);
		return order;
	}
	
	
	
	//订单详情页
	@RequestMapping(value = {"appOrderDetails",""})
	public String appOrderDetails(String id, Model model) {
		AppOrder appOrder = appOrderService.getOrder(Integer.valueOf(id));
		String house = appOrderService.findHouseType(appOrder);
		appOrder.setHouse(house);
		model.addAttribute("appOrder", appOrder);
		return "mobile/modules/Manager/details";
	}
	
	//订单图纸
	@RequestMapping(value = {"appOrderCadfile",""})
	public String appOrderCadfile(String id,Model model){
		
		//通过订单id查询订单详情
		AppOrder appOrder = appOrderService.getOrder(Integer.valueOf(id));
		//通过订单id查询订单图纸
		List<AppOrderCadfile> appOrderCadfile = appOrderService.selectCadfile(Integer.valueOf(id));
		for(AppOrderCadfile aoc : appOrderCadfile){
			String path = aoc.getFilePath();
			path = path.substring(1);
			aoc.setFilePath(path);
		}
		model.addAttribute("appOrder",appOrder);
		model.addAttribute("appOrderCadfile", appOrderCadfile);
		return "mobile/modules/Manager/draw";
	}
	
	//下载图纸
//	@RequestMapping(value = ("download"),method=RequestMethod.POST)
//	public @ResponseBody String download(HttpServletResponse response,String filePath,String fileName,Model model,HttpServletRequest request) throws Exception{
//		
//		// 创建流  
//        BufferedInputStream in = new BufferedInputStream(new URL(filePath).openStream());  
//
//        // 生成图片名  
//        int index = filePath.lastIndexOf("/");  
//        String sName = filePath.substring(index+1, filePath.length());  
//        System.out.println(sName);  
//        // 存放地址  
//        File img = new File(filePath+sName);  
//        // 生成图片  
//        BufferedOutputStream out = new BufferedOutputStream(  
//                new FileOutputStream(img));  
//        byte[] buf = new byte[2048];  
//        int length = in.read(buf);  
//        while (length != -1) {  
//            out.write(buf, 0, length);  
//            length = in.read(buf);  
//        }  
//        in.close();  
//        out.close();  
//		
//		return "0";
//		
//	}
	
	
	
	
	
}