package cn.damei.web.modules;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.mobile.Manager.ConfirmStartOrder;
import cn.damei.service.modules.OrderStartWorkerDateSetService;

@Controller
@RequestMapping(value="${adminPath}/orderStartWorkerDateSet")
public class OrderStartWorkerDateSetController {
	
	@Autowired
	private OrderStartWorkerDateSetService orderStartWorkerDateSetService;

	
	@ModelAttribute
	public ConfirmStartOrder get(@RequestParam(required = false) Integer id) {
		ConfirmStartOrder entity = null;
		if (StringUtils.isNotBlank(id + "")) {
			entity = orderStartWorkerDateSetService.get(id+"");
		}
		if (entity == null) {
			entity = new ConfirmStartOrder();
		}
		return entity;
	}
	
	@RequestMapping(value="list")
	public String list(ConfirmStartOrder confirmStartOrder,HttpServletRequest request,HttpServletResponse response,Model model){
		Page<ConfirmStartOrder> findPage = orderStartWorkerDateSetService.findPage(new Page<ConfirmStartOrder>(request,response), confirmStartOrder);
		model.addAttribute("page", findPage);
		return "/modules/orderstartworkerdateset/orderStartWorkerDateSetList";
	}
	
	@RequestMapping(value="set")
	public String set(ConfirmStartOrder confirmStartOrder,HttpServletRequest request,HttpServletResponse response,Model model){
		
		model.addAttribute("confirmStartOrder", confirmStartOrder);
		return "/modules/orderstartworkerdateset/orderStartWorkerDateSetListForm";
	}

	@RequestMapping(value="detail")
	public String detail(ConfirmStartOrder confirmStartOrder,HttpServletRequest request,HttpServletResponse response,Model model){
		ConfirmStartOrder detail = orderStartWorkerDateSetService.findDetail(confirmStartOrder);
		model.addAttribute("confirmStartOrder", detail);
		return "/modules/orderstartworkerdateset/orderStartWorkerDateSetDetail";
	}
	
	

	@RequestMapping(value ="updateById")
	public @ResponseBody String startUpdate(String houseIsNew, String projectMode, String storeId, String orderId, String input_date, String startRemark, String dateCompare,
			String delayType, String decorateDelayDays, String isSelfDecorateNeedSign, String[] photos, String isNeedSign, HttpServletRequest request) throws ParseException, IOException, NoSuchAlgorithmException {
		
        return orderStartWorkerDateSetService.saveConfirmStart(houseIsNew, projectMode, storeId, orderId, input_date, startRemark, 
        		dateCompare, delayType, decorateDelayDays, isSelfDecorateNeedSign, 
        		photos, isNeedSign, request);
	}
	
}
