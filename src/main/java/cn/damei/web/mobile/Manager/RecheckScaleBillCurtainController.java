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
import cn.damei.entity.mobile.Manager.RecheckScaleBillCurtain;
import cn.damei.service.mobile.Manager.RecheckScaleBillCurtainService;
import cn.damei.service.mobile.Manager.RecheckScaleBillService;

/**
 * 复尺主页面(20161107-20161113)
 * @author llp 
 * 2016-11-16
 */
@Controller
@RequestMapping(value="${adminPath}/app/manager")
public class RecheckScaleBillCurtainController {
	private static Logger logger = LoggerFactory.getLogger(RecheckScaleBillCurtainController.class);
	
	@Autowired
	private RecheckScaleBillCurtainService recheckScaleBillCurtainService;
	@Autowired
	private BusinessPicService businessPicService;
	@Autowired
	private RecheckScaleBillService recheckScaleBillService;
	
	/****
	 * 窗帘复尺记录
	 ****/
	@RequestMapping(value={"curtainDetail",""})
	public String curtainDetail(RecheckScaleBillCurtain recheckScaleBillCurtain,HttpServletRequest request,Model model,
			String recheckID, String orderID) throws IOException{
		logger.info("复尺编号："+recheckID+"\t\t订单编号："+orderID);
		
		Integer recheckIDs = Integer.valueOf(recheckID);
		//获取复尺信息
		RecheckScaleBill scale = recheckScaleBillService.getByID(recheckIDs);
		
		//根据订单编号查询该订单复尺的所有内容
		List<RecheckScaleBillCurtain> curtainList = recheckScaleBillCurtainService.getByRecheckID(
				recheckIDs);
		
		/**获取图片路径*/
		List<BusinessPic> picList = businessPicService.getByBusType(ConstantUtils.CURTAIN_KEY, 
				recheckIDs);
		
		model.addAttribute("recheckScaleBill", scale);
		model.addAttribute("orderID", orderID);
		model.addAttribute("curtainList", curtainList);
		model.addAttribute("picList", picList);
		model.addAttribute("prefixName", PicRootName.picPrefixName());
		return "mobile/modules/Manager/progressMain/newChecksize/curtainDetail";
	}
}
