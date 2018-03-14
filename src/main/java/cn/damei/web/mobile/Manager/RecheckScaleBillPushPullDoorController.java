package cn.damei.web.mobile.Manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.entity.mobile.Manager.BusinessPic;
import cn.damei.service.mobile.Manager.BusinessPicService;
import cn.damei.entity.mobile.Manager.RecheckScaleBill;
import cn.damei.entity.mobile.Manager.RecheckScaleBillPushPullDoor;
import cn.damei.service.mobile.Manager.RecheckScaleBillPushPullDoorService;
import cn.damei.service.mobile.Manager.RecheckScaleBillService;


@Controller
@RequestMapping(value="${adminPath}/app/manager")
public class RecheckScaleBillPushPullDoorController {
	private static Logger logger = LoggerFactory.getLogger(RecheckScaleBillPushPullDoorController.class);
	
	@Autowired
	private RecheckScaleBillPushPullDoorService recheckScaleBillPushPullDoorService;
	@Autowired
	private BusinessPicService businessPicService;
	@Autowired
	private RecheckScaleBillService recheckScaleBillService;
	

	@RequestMapping(value={"pushpullDetail",""})
	public String pushpullDetail(RecheckScaleBillPushPullDoor recheckScaleBillPushPullDoor,HttpServletRequest request,Model model,
			String recheckID, String orderID) throws IOException{
		logger.info("复尺编号："+recheckID+"\t\t订单编号："+orderID);
		
		Integer recheckIDs = Integer.valueOf(recheckID);

		RecheckScaleBill scale = recheckScaleBillService.getByID(recheckIDs);
		

		List<RecheckScaleBillPushPullDoor> pushpullList = recheckScaleBillPushPullDoorService.
				getByRecheckID(recheckIDs);
		

		List<BusinessPic> picList = businessPicService.getByBusType(ConstantUtils.PUSH_PULL_DOOR_KEY, 
				recheckIDs);
		
		model.addAttribute("recheckScaleBill", scale);
		model.addAttribute("orderID", orderID);
		model.addAttribute("pushpullList", pushpullList);
		model.addAttribute("picList", picList);
		model.addAttribute("prefixName", PicRootName.picPrefixName());
		return "mobile/modules/Manager/progressMain/newChecksize/pushpullDetail";
	}
}
