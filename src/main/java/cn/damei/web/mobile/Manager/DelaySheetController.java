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

		AppOrder order = appOrderService.getOrder(delaySheet.getOrderId());

		List<Dict> list = delaySheetService.findDelayCategory(1);


		List<Dict> nodePlan = delaySheetService.findOrderNodePlan(String.valueOf(delaySheet.getOrderId()));
		model.addAttribute("order", order);
		model.addAttribute("list", list);
		model.addAttribute("list2", nodePlan);

		if(delaySheet.getId()!=null){
			delaySheet = delaySheetService.get(delaySheet.getId());
			model.addAttribute("delaySheet", delaySheet);
			model.addAttribute("flag", 1);
		}
		return "mobile/modules/Manager/delaysheet/delayApply";
	}

	@RequestMapping(value="list3")
	public String list3(HttpServletRequest request,HttpServletResponse response,Model model){

		Manager manager = (Manager) request.getSession().getAttribute("manager");

		List<DelaySheet> list = delaySheetService.findDelayOrder(manager.getId());
		model.addAttribute("list", list);
		return "mobile/modules/Manager/delaysheet/delayList";
	}

	@RequestMapping(value="ajaxreson")
	@ResponseBody
	public List<Dict> ajaxreson(String id){

		List<Dict> list = delaySheetService.findDelayCategorytow(id,2);
		return list;
	}



	@RequestMapping(value="save")
	public String save(DelaySheet delaySheet,String photo[],HttpServletRequest request){

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

		String str = delaySheetService.checkSubmitOver(orderId);
		if(!str.equals("0")){
			return "2";
		}
		if(delaySheet == null){

			return "1";
		}else{

			String status = delaySheet.getStatus();
			if(status != null){

				if(status.equals("20")){

					if(stageStatus.equals(delaySheet.getDelayBillStageStatus())){
						return "3";
					}else{
						return "1";
					}
				}else if(status.equals("15") || status.equals("90")){
					return "1";
					
				}else{

					return "2";
				}
			}
		}
		return "";
	}
	
	
}
