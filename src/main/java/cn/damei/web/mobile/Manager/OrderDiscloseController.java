package cn.damei.web.mobile.Manager;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.AppManagerOrder;
import cn.damei.service.mobile.Manager.AppManagerOrderService;
import cn.damei.service.mobile.Manager.OrderDisclosePicService;
import cn.damei.service.mobile.Manager.OrderDiscloseService;
import cn.damei.entity.mobile.home.BroadCastPicEntity;
import cn.damei.service.mobile.home.BroadCastService;


@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class
OrderDiscloseController {
	private static Logger logger = LoggerFactory.getLogger(OrderDiscloseController.class);

	@Autowired
	private OrderDiscloseService orderDiscloseService;
	
	@Autowired
	private OrderDisclosePicService orderDisclosePicService;
	
	@Autowired
	private AppManagerOrderService appManagerOrderService;
	

	@RequestMapping(value={"orderDiscloseDetail",""})
	public String orderDiscloseDetail(AppManagerOrder appManagerOrder, HttpServletRequest request, Model model) {

		Manager manager = (Manager) request.getSession().getAttribute("manager");
		logger.info("当前项目经理ID："+ manager.getId() + "当前项目经理名字：" + manager.getRealname());
		
		Integer id = Integer.valueOf(request.getParameter("id"));
		logger.info("传值的订单ID：" + id);
		
		AppManagerOrder order = appManagerOrderService.getById(id);
		
		if(null != order){
			model.addAttribute("order", order);
		}
		model.addAttribute("orderID", id);
		return "mobile/modules/Manager/progressMain/orderDisclose/orderDiscloseDetail";
	}
	

	@RequestMapping(value={"disCloseShowPhotos",""})
	public String disCloseShowPhotos(String picsVal, String type,String orderID,Model model){
		logger.info("传入图片的值："+picsVal +"传入的类型："+type);
		
		model.addAttribute("type", type);
		model.addAttribute("photos", picsVal);
		model.addAttribute("orderID", orderID);
		return "mobile/modules/Manager/progressMain/orderDisclose/viewDetailPhotos";
	}
	

	@RequestMapping(value={"disCloseSubmitData",""})
	public @ResponseBody String disCloseSubmitData(String disCloseDate, String photos1, String photos2,
			String photos3, String orderID, String pics,HttpServletRequest request,double measureSquare ) throws ParseException, IOException{
		System.out.println("方法开始：" + new Date().toLocaleString());
		System.out.println("图片大小：" + (photos1+photos2+photos3).getBytes().length);
		if(null==photos1 &&null==photos2&&null==photos3){


			return "noPic";
		}
		

		Manager manager = (Manager) request.getSession().getAttribute("manager");
		String result = "0";
		

		Integer orderId = null;
		if(StringUtils.isNotBlank(orderID)){
			orderId = orderDiscloseService.findByOrderId(Integer.parseInt(orderID));
			if(orderId != null){
				return "4";
			}

			Integer discloseId = orderDiscloseService.insertByDisclose(orderID,disCloseDate,manager.getId(),measureSquare);
			logger.info("获取biz_order_disclose的主键：" + discloseId);
			if(null!= discloseId && discloseId < 0){
				result = "1";
			}else{
				


				
				BroadCastPicEntity bc = new BroadCastPicEntity();
				
				
				bc.setOrderId(Integer.parseInt(orderID));
				bc.setRelatedBusinessId(Integer.parseInt(orderID));
				bc.setBroadCastName("设计交底");
				bc.setCusBroadCastType("1");
				bc.setCusBroadCastCode(bcService.getBroadCastCode());
				bc.setPicCount(3);
				bc.setApplyEmployeeId(SessionUtils.getManagerSession(request).getId());
				bc.setApplyDate(new Date());
				bc.setStatus("10");
				bc.setStatusTime(new Date());
				bc.setCreateBy(SessionUtils.getManagerSession(request).getId());
				bc.setCreateDate(new Date());
				
				bc.setDelFlag("0");
				bcService.saveBroadCastRecord(bc);

				
				

				bc.setPicType("501");
				bc.setBroadCastId(bc.getId());
				bc.setPicDateTime(new Date());
				
				



				







				System.out.println("上传图片开始：" + new Date().toLocaleString());
				if(null != photos1){






					result = orderDisclosePicService.insertByDisclosePic(discloseId,"1",photos1);

					bc.setPicUrl(photos1);
					bcService.saveBroadCastPic(bc);
					
					
				}
				if(null != photos2){






					result = orderDisclosePicService.insertByDisclosePic(discloseId,"2",photos2);

					bc.setPicUrl(photos2);
					bcService.saveBroadCastPic(bc);
					
				}
				if(null != photos3){






					result = orderDisclosePicService.insertByDisclosePic(discloseId,"3",photos3);

					bc.setPicUrl(photos3);
					bcService.saveBroadCastPic(bc);
					
				}
				System.out.println("结束：" + new Date().toLocaleString());

				result = appManagerOrderService.updateByOrderStatusNumber(ConstantUtils.ORDERSTATUS_130_VALUE,
						ConstantUtils.ORDERSTATUS_130_VALUE_REMARK,Integer.valueOf(orderID)); 
			}
		}
		
		return result;
	}

	@Autowired 
	private BroadCastService bcService;
}
