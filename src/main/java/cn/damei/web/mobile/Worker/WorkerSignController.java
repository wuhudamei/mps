package cn.damei.web.mobile.Worker;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.MessagePushType;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.OrderSignVo;
import cn.damei.entity.mobile.Worker.Message;
import cn.damei.service.mobile.Worker.MessageService;
import cn.damei.entity.mobile.Worker.SignDetail;
import cn.damei.entity.mobile.Worker.TaskPackSignVo;
import cn.damei.service.mobile.Worker.WorkerSignService;
import cn.damei.entity.mobile.Worker.Worker;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;

import net.sf.json.JSONArray;


@Controller
@RequestMapping(value = "${adminPath}/app/worker/sign")
public class WorkerSignController {

	@Autowired
	private WorkerSignService workerSignService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private MessageService messageService;

	@RequestMapping(value = { "signIndex", "" })
	public String signIndex(HttpServletRequest request, Model model) {
		

		
		

		Worker worker = SessionUtils.getWorkerSession(request);

		List<TaskPackSignVo> list = workerSignService.packByworkerLeaderId(worker.getId());
		

		if(null==list || list.size()<1){
			model.addAttribute("error", "签到列表");
			return "mobile/modules/Worker/sign";
		}
		
		


		model.addAttribute("signList", list);
		return "mobile/modules/Worker/sign";
	}
		
		
		
	


	@RequestMapping(value = { "searchOrder", "" })
	public @ResponseBody List<TaskPackSignVo> searchOrder(HttpServletRequest request, Model model) {
	Worker worker=SessionUtils.getWorkerSession(request);
		List<TaskPackSignVo> list = workerSignService.packByworkerLeaderId(worker.getId());
		return list;
	}


	@RequestMapping(value = { "signByGPS", "" })
	public String signByGPS(HttpServletRequest request, Model model, String packId) {

		OrderSignVo signVo = workerSignService.getAddressThroughOrder(Integer.valueOf(packId));
		String[] split = signVo.getMapAddress().split(",");
		model.addAttribute("lon", split[0]);
		model.addAttribute("lat", split[1]);
		model.addAttribute("packId", packId);
		
		return "mobile/modules/Worker/map2";
	}


	@RequestMapping(value = { "getAddress", "" })
	public @ResponseBody JSONArray getAddress(OrderSignVo order, HttpServletRequest request, Model model) {


		OrderSignVo signVo = workerSignService.getAddressThroughOrder(order.getId());
		String[] split = signVo.getMapAddress().split(",");
 
		return JSONArray.fromObject(split);
	}


	@RequestMapping(value = { "uploadPhoto", "" })
	public String uploadPhoto(HttpServletRequest request, Model model, String packId) {


		model.addAttribute("packId", packId);
		return "mobile/modules/Worker/upload_photo";
	}


	@RequestMapping(value = "savePhoto", method = RequestMethod.POST)
	public @ResponseBody List<String> savePhoto(HttpServletRequest req,
			@RequestParam(value = "pic", required = false) MultipartFile[] pic)
			throws IllegalStateException, IOException {
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) req;


		String pathRoot = request.getSession().getServletContext().getRealPath("");
		String path = "";
		List<String> listImagePath = new ArrayList<String>();
		for (MultipartFile mf : pic) {
			if (!mf.isEmpty()) {


				String contentType = mf.getContentType();

				String imageName = contentType.substring(contentType.indexOf("/") + 1);

				path = "/static/mobile/modules/Worker/css/photo/" + mf.getOriginalFilename() + "." + imageName;
				mf.transferTo(new File(pathRoot + path));

				listImagePath.add(path);
			}
		}

		return listImagePath;
	}

































































































	
	

	@RequestMapping(value = "workersign")
	public @ResponseBody String workersign(SignDetail signDetail, Model model, HttpServletRequest request) {
		String flag = "error";
		try{
			

			Worker worker = SessionUtils.getWorkerSession(request);
			Date date = new Date();
			
			SignDetail sign = new SignDetail();
			sign.setPackId(signDetail.getPackId());
			sign.setSignDate(date);
			sign.setSignAddress(signDetail.getSignAddress());
			sign.setSignDistance(signDetail.getSignDistance());
			sign.setSignXy(signDetail.getLon()+","+signDetail.getLat());
			sign.setSignType(ConstantUtils.SIGN_TYPE_EMPLOYEE_START);
			sign.setWorkerLeaderId(worker.getId());
			sign.setWorkerLeaderName(worker.getRealname());
			sign.setSignName(worker.getRealname());


			SignDetail detail=workerSignService.signDetailByWorkerLeaderId(signDetail.getWorkerLeaderId(),signDetail.getPackId());

			if (null!=detail){
				flag = "error";
			} else {

				TaskPackSignVo packSignVo = workerSignService.getCustomerInfoByPackId(signDetail.getPackId());
				sign.setCustomerInfo(packSignVo.getCustomerMessage()+"-"+packSignVo.getCustomerName());

				workerSignService.signSuccess(sign);
				
				

				workerSignService.updatePackStatusById(signDetail.getPackId());
				
				



				
				String content = "订单（"+packSignVo.getCustomerMessage()+"-"+packSignVo.getCustomerName()+"）的任务包（）,工人:"+worker.getRealname()+"-"+worker.getPhone()+",已签到,请登录app查看详情。";
				BizPhoneMsg phone = new BizPhoneMsg();
				phone.setReceiveEmployeeId(packSignVo.getItemManagerId());
				phone.setReceivePhone(packSignVo.getItemManagerPhone());
				phone.setMsgContent(content);
				phone.setMsgGenerateDatetime(date);
				phone.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
				phone.setRelatedBusinessType(SendMsgBusinessType.SEND_MSG_BUSINESS_TYPE_200701);
				phone.setRelatedBusinessIdInt(signDetail.getPackId());
				bizPhoneMsgService.insert(phone);
				
				Message message = new Message();
				message.setMsgTitle("任务包工人签到");
				message.setMsgTime(new Date());
				message.setMsgContent(content);
				message.setMsgType(MessagePushType.MSG_TYPE_1);
				message.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_100005001);
				message.setEmployeeId(packSignVo.getItemManagerId());
				message.setBusiIdInt(signDetail.getPackId());
				messageService.insert(message);
				

				
				flag = "success";
			}
			
			 
		}catch(Exception e){
			flag = "error";
		}
		
		return flag;
	}
	
	
	
	

	@RequestMapping(value = "signDetail")
	public String signDetail(SignDetail detail, Model model) {


		SignDetail signDetail = workerSignService.getSignDetailByPackIdLimit(detail.getPackId());

		String[] strings = signDetail.getSignPic().split("--");
		for (String string : strings) {
			signDetail.setSignPic(string);
		}
		
		model.addAttribute("signDetail", signDetail);

		return "mobile/modules/Worker/sign_details";
	}

}
