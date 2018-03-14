package cn.damei.web.mobile.Manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.DelayBillConstant;
import cn.damei.common.constantUtils.PicturePathContantUtil;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.utils.PicRootName;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.DelaySheet;
import cn.damei.service.mobile.Manager.DelaySheetService;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.AppOrder;
import cn.damei.service.mobile.Manager.AppOrderService;
import cn.damei.service.mobile.Manager.ProblemService;
import cn.damei.service.mobile.Manager.WallAndFloorProblemService;
import cn.damei.entity.modules.Dict;

@Controller
@RequestMapping(value="${adminPath}/app/manager/delaysheet")
public class DelaySheetController {

	@Autowired
	private DelaySheetService delaySheetService;
	@Autowired
	private AppOrderService appOrderService;
	@Autowired
	private WallAndFloorProblemService wallAndFloorProblemService;
	@Autowired
	private ProblemService problemService;
	
	@RequestMapping(value="list")
	public String list(DelaySheet delaySheet,Model model) throws IOException{
		delaySheet = delaySheetService.findDelayDetails(delaySheet);
		List<ReportCheckDetailsPic> picList = problemService.findPic(Integer.parseInt(delaySheet.getId()),PictureTypeContantUtil.PICTURE_TYPE_10010);
		model.addAttribute("delay", delaySheet);
		model.addAttribute("picList", picList);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("baseUrl", baseUrl);
		return "mobile/modules/Manager/delaysheet/delayDetails";
	}
	
	
	@RequestMapping(value="list2")
	public String list2(DelaySheet delaySheet,Model model){
		//查询订单基础信息
		AppOrder order = appOrderService.getOrder(delaySheet.getOrderId());
		//查询延期分类 一级类目
		List<Dict> list = delaySheetService.findDelayCategory(1);
		//延期阶段 
		/*List<Dict> list2 = delaySheetService.findDelayCategoryStatus();*/
		List<Dict> nodePlan = delaySheetService.findOrderNodePlan(String.valueOf(delaySheet.getOrderId()));
		model.addAttribute("order", order);
		model.addAttribute("list", list);
		model.addAttribute("list2", nodePlan);
		//重新申请
		if(delaySheet.getId()!=null){
			delaySheet = delaySheetService.get(delaySheet.getId());
			model.addAttribute("delaySheet", delaySheet);
			model.addAttribute("flag", 1);
		}
		return "mobile/modules/Manager/delaysheet/delayApply";
	}
	/**
	 * 延期列表查询
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list3")
	public String list3(HttpServletRequest request,HttpServletResponse response,Model model){
		//获得项目经理ID
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		//查询项目经理下所有的订单
		List<DelaySheet> list = delaySheetService.findDelayOrder(manager.getId());
		model.addAttribute("list", list);
		return "mobile/modules/Manager/delaysheet/delayList";
	}
	/**
	 * 动态加载延期原因
	 * @param id
	 * @return
	 */
	@RequestMapping(value="ajaxreson")
	@ResponseBody
	public List<Dict> ajaxreson(String id){
		//查询延期分类 根据一级ID查询二级类目
		List<Dict> list = delaySheetService.findDelayCategorytow(id,2);
		return list;
	}


	/**
	 * 保存
	 * @param
	 * @return
	 */
	@RequestMapping(value="save")
	public String save(DelaySheet delaySheet,String photo[],HttpServletRequest request){
		//查询延期分类 根据一级ID查询二级类目
		delaySheet.preUserInsert();
		delaySheet.setStatus(DelayBillConstant.DELAY_BILL_STATUS_10);
		delaySheetService.save(delaySheet);
		if(null!=photo && photo.length>0){
			wallAndFloorProblemService.saveProblemPic(Integer.parseInt(delaySheet.getId()),PictureTypeContantUtil.PICTURE_TYPE_10010,photo,PicturePathContantUtil.UPLOAD_DELAYSHEET_PATH,request);
		} 
		 return "redirect:"+Global.getAdminPath()+"/app/manager/delaysheet/list3";
	}
	@RequestMapping(value="isSubmit")
	@ResponseBody
	public String isSubmit(String orderId,String stageStatus){
		DelaySheet delaySheet = delaySheetService.checkSubmit(orderId,stageStatus);
		//判断这个订单是否有待审核的 如果有不让申请
		String str = delaySheetService.checkSubmitOver(orderId);
		if(!str.equals("0")){
			return "2";
		}
		if(delaySheet == null){
			//第一次申请，可以申请  1
			return "1";
		}else{
			//获取延期状态
			String status = delaySheet.getStatus();
			if(status != null){
				//如果审核通过或者被拒绝，可以申请
				if(status.equals("20")){
					//如果状态
					if(stageStatus.equals(delaySheet.getDelayBillStageStatus())){
						return "3";//审核通过了不可以重复申请
					}else{
						return "1";
					}
				}else if(status.equals("15") || status.equals("90")){
					return "1";
					
				}else{
					//不可以申请
					return "2"; //在审核中不可以申请
				}
			}
		}
		return "";
	}
	
	
}
