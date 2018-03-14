package cn.damei.web.mobile.Manager;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.damei.service.modules.BizOrderChecksizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.Checksize;
import cn.damei.entity.mobile.Manager.ChecksizeOrder;
import cn.damei.entity.mobile.Manager.ChecksizeType;
import cn.damei.service.mobile.Manager.ChecksizeNewService;
import cn.damei.entity.mobile.Manager.OrderInstallPlan;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.modules.BizOrderChecksizePic;


/**
 * 上报厂家复尺
 * @author Administrator
 * @author wyb
 *
 */
@Controller
@RequestMapping(value="${adminPath}/app/manager/checksize")
public class ChecksizeNewController {

	@Autowired
	private ChecksizeNewService checksizeNewService;
	@Autowired
	private BizOrderChecksizeService bizOrderChecksizeService;
	
	
	/**
	 * 上报厂家复尺列表页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"checksizeList",""})
	public String checksizeList(HttpServletRequest request,Model model){
	
		return "mobile/modules/Manager/progressMain/checksize/new/reRulerList";
	}
	
	
	/**
	 * 动态加载厂家复尺页面的订单
	 * 根据项目经理id查询项目经理下的状态小于300的所有订单(加搜索条件)
	 * @param text
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "checksize_order_list_ajax")
	public @ResponseBody List<ChecksizeOrder> checksizeOrderListAjax(String text,HttpServletRequest request) {

		//获取项目经理
		Manager manager = SessionUtils.getManagerSession(request);
		List<ChecksizeOrder> list = null;
		if (null!=manager && null != manager.getId()) {
			//根据项目经理id查询项目经理下的状态小于300的所有订单(加搜索条件)
			list = checksizeNewService.findOrderByManagerId(manager.getId(),text);
		}
		return list;

	}
	
	
	/**
	 * 校验
	 * 1：该订单是否有可复尺的安装项
	 * 2：同一个订单两次厂家复尺申请操作时间必须间隔5分钟，请过5分钟后再申请
	 * 3：该复尺项是否已申请
	 * 4：该工地2017-7-1开工，按照工程部规定主材（橱柜、台面）开工10天后（2017-7-21）才可以申请橱柜复尺，如果需提前申请，请点击【提前申请】，如有其它问题请联系大区经理或助理。
	 * 5:每个安装项厂家复尺【提前申请】只能申请一次
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "check_checksize_data_ajax")
	@ResponseBody
	public String checkChecksizeDataAjax(String orderId,String type,String orderInstallItemId) {

		return checksizeNewService.queryChecksizeCheckData(orderId,type,orderInstallItemId);
	}
	
	
	
	/**
	 * 厂家复尺申请页面
	 * @param orderId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"applyChecksize",""})
	public String applyChecksize(String orderId,HttpServletRequest request,Model model){
		
		
		ChecksizeOrder order = null;
		List<ChecksizeType> list = null;
		if(StringUtils.isNotBlank(orderId)){
			//查询订单详情
			order = checksizeNewService.findOrder(Integer.valueOf(orderId));
			//查询订单可复尺的安装项列表
			list = checksizeNewService.findChecksizeTypeList(Integer.valueOf(orderId));
		}
		
		model.addAttribute("order", order);
		model.addAttribute("list", list);
		
		return "mobile/modules/Manager/progressMain/checksize/new/reRuler";
	}
	
	
	/**
	 * 上传复尺
	 * @param orderId
	 * @param checksizeDate
	 * @param orderInstallItemId
	 * @param remarks
	 * @param photo
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "apply_checksize_submit_ajax", method = RequestMethod.POST)
	@ResponseBody
	public  String applyChecksizeSubmitAjax(String orderId,String checksizeDate,String orderInstallItemId,String remarks,String[] photo,HttpServletRequest request) throws ParseException {
		
		Manager manager = SessionUtils.getManagerSession(request);
		return checksizeNewService.saveChecksize(orderId,checksizeDate,orderInstallItemId,remarks,photo,request,manager);

	}

	
	
	/**
	 * 复尺记录
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"checksizeRecord",""})
	public String checksizeRecord(String orderId,HttpServletRequest request,Model model){
		
		model.addAttribute("orderId", orderId);
		return "mobile/modules/Manager/progressMain/checksize/new/reRulerDetails";
	}
	
	
	/**
	 * 动态加载厂家复尺记录
	 * @param orderId
	 * @param text
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "checksize_record_list_ajax")
	public @ResponseBody List<Checksize> checksizeRecordListAjax(Integer orderId,String text,HttpServletRequest request) {
		
		//查询该订单所有的厂家复尺
		return checksizeNewService.findCheckSizeList(orderId,text);

	}
	
	/**
	 * 厂家复尺--图片
	 * @param checksizeId
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value={"checksizeRecordPic",""})
	public String checksizeRecordPic(Integer checksizeId,HttpServletRequest request,Model model) throws IOException{
		//通过复尺表id查询复尺图片
		List<BizOrderChecksizePic> picList = bizOrderChecksizeService.selectPicByOrderChecksizeId(checksizeId);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("picList", picList);
		model.addAttribute("baseUrl", baseUrl);
		
		return "mobile/modules/Manager/progressMain/checksize/new/viewPic";
	}
		
	
	/**
	 * 申请主材复尺项--快捷申请【提前申请】
	 * 
	 * @param id
	 * @param orderId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "installChecksizeAdvanceApply", "" })
	public String installPlanAdvanceApply(Integer id, Integer orderId, HttpServletRequest request, Model model) {
		ChecksizeOrder order = checksizeNewService.findOrder(Integer.valueOf(orderId));
		OrderInstallPlan orderInstall = checksizeNewService.queryInstallItemDetail(id);
		model.addAttribute("order", order);
		model.addAttribute("orderInstall", orderInstall);
		return "mobile/modules/Manager/progressMain/checksize/new/reRulerApply-earlyApply";
	}
	
	
	/**
	 * ajax 主材复尺项提前申请记录保存【提前申请】
	 * @param orderInstallItemId 安装项ID
	 * @param photo 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "save_install_apply_checksize_advance_apply_ajax")
	public @ResponseBody String saveInstallApplyChecksizeAdvanceApplyAjax(String orderInstallItemId,String[] photo, HttpServletRequest request) {
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		return 	checksizeNewService.saveInstallApplyChecksizeAdvanceApplyAjax(orderInstallItemId,photo,manager,request);
	}
	
	
}
