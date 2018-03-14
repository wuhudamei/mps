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

/**
 * @author 梅浩 meihao@zzhyun.cn:
 * @version 创建时间：2016年9月22日 下午7:38:30 类说明
 */
@Controller
@RequestMapping(value = "${adminPath}/app/worker/sign")
public class WorkerSignController {

	@Autowired
	private WorkerSignService workerSignService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private MessageService messageService;
	/**
	 * 签到首页 ,查询工长下的订单
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "signIndex", "" })
	public String signIndex(HttpServletRequest request, Model model) {
		
		/*//已经签到的给项目经理发送短信
		if(null!=request.getSession().getAttribute("packName")){
			model.addAttribute("packName", request.getSession().getAttribute("packName"));
			model.addAttribute("workerInfo", SessionUtils.getWorkerSession(request).getRealname()+"-"+SessionUtils.getWorkerSession(request).getPhone());
			model.addAttribute("customerInfo", request.getSession().getAttribute("customerInfo"));
			model.addAttribute("managerPhone", request.getSession().getAttribute("managerPhone"));
			
			request.getSession().removeAttribute("packName");
		
			
		}*/
		
		
		// 1:取出登录工长信息
		Worker worker = SessionUtils.getWorkerSession(request);
		// 2:得到任务包集合
		List<TaskPackSignVo> list = workerSignService.packByworkerLeaderId(worker.getId());
		
		//如果没有任务包
		if(null==list || list.size()<1){
			model.addAttribute("error", "签到列表");
			return "mobile/modules/Worker/sign";
		}
		
		


		model.addAttribute("signList", list);
		return "mobile/modules/Worker/sign";
	}
		
		
		
	

	/**
	 * 查询订单,签到页
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "searchOrder", "" })
	public @ResponseBody List<TaskPackSignVo> searchOrder(HttpServletRequest request, Model model) {
	Worker worker=SessionUtils.getWorkerSession(request);
		List<TaskPackSignVo> list = workerSignService.packByworkerLeaderId(worker.getId());
		return list;
	}

	/**
	 * gps定位页面
	 * 
	 * @param request
	 * @param model
	 * @param packId
	 * @return
	 */
	@RequestMapping(value = { "signByGPS", "" })
	public String signByGPS(HttpServletRequest request, Model model, String packId) {

		OrderSignVo signVo = workerSignService.getAddressThroughOrder(Integer.valueOf(packId));
		String[] split = signVo.getMapAddress().split(",");
		model.addAttribute("lon", split[0]);
		model.addAttribute("lat", split[1]);
		model.addAttribute("packId", packId);
		
		return "mobile/modules/Worker/map2";
	}

	/**
	 * 获取订单地址 经纬度
	 * 
	 * @param order
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "getAddress", "" })
	public @ResponseBody JSONArray getAddress(OrderSignVo order, HttpServletRequest request, Model model) {

		//通过任务包关联的订单id查询 坐标
		OrderSignVo signVo = workerSignService.getAddressThroughOrder(order.getId());
		String[] split = signVo.getMapAddress().split(",");
 
		return JSONArray.fromObject(split);
	}

	/**
	 * 到图片上传页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "uploadPhoto", "" })
	public String uploadPhoto(HttpServletRequest request, Model model, String packId) {

		// 接着把订单id带过去
		model.addAttribute("packId", packId);
		return "mobile/modules/Worker/upload_photo";
	}

	/**
	 * 上传图片,不做持久保存
	 * 
	 * @param pic
	 * @param
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping(value = "savePhoto", method = RequestMethod.POST)
	public @ResponseBody List<String> savePhoto(HttpServletRequest req,
			@RequestParam(value = "pic", required = false) MultipartFile[] pic)
			throws IllegalStateException, IOException {
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) req;

		// 获得物理路径webapp所在路径
		String pathRoot = request.getSession().getServletContext().getRealPath("");
		String path = "";
		List<String> listImagePath = new ArrayList<String>();
		for (MultipartFile mf : pic) {
			if (!mf.isEmpty()) {

				// 获得文件类型（可以判断如果不是图片，禁止上传）
				String contentType = mf.getContentType();
				// 获得文件后缀名称
				String imageName = contentType.substring(contentType.indexOf("/") + 1);

				path = "/static/mobile/modules/Worker/css/photo/" + mf.getOriginalFilename() + "." + imageName;
				mf.transferTo(new File(pathRoot + path));

				listImagePath.add(path);
			}
		}

		return listImagePath;
	}

//	/**
//	 * 现场图片签到
//	 * 
//	 * @param req
//	 * @param sign
//	 * @param model
//	 * @param pic
//	 * @return
//	 */
//	@RequestMapping(value = "workersign", method = RequestMethod.POST)
//	public @ResponseBody String submitPic(HttpServletRequest req, SignDetail sign, Model model,
//			@RequestParam(required = false) MultipartFile[] pic) {
//		// 签到有两种 一个是上传图片签到, 一个是根据地址gps签到
//
//		MultipartHttpServletRequest request = (MultipartHttpServletRequest) req;
//		// 签到时间
//		sign.setSignDate(new Date());
//		// 工人组长id
//		sign.setWorkerLeaderId(SessionUtils.getWorkerSession(request).getId());
//
//		TaskPackSignVo packSignVo = workerSignService.getCustomerInfoByPackId(sign.getPackId());
//		// 顾客信息 组成元素: message+name
//		sign.setCustomerInfo(packSignVo.getCustomerMessage()+"-"+packSignVo.getCustomerName());
//		/*// 保存该签到路径
//		StringBuilder sb = new StringBuilder();*/
//		/*if (null != pic && pic.length > 0)
//			for (MultipartFile p : pic) {
//				// 得到图片名称
//				String fileName = p.getOriginalFilename();
//
//				// 图片完整路径
//				String path = "/mdn/static/mobile/modules/Worker/css/photo/" + fileName;
//	 			
//				// 保存属性
//				// 图片路径
//				sign.setSignType("2");
//				sb.append(path+"--");
//			}
//		else {
//			sign.setSignType("2");
//			
//			
//			//经纬度
//			Double  x =LngAndLatUtils.getLngAndLat(sign.getSignAddress()).get("lng");
//			Double  y =LngAndLatUtils.getLngAndLat(sign.getSignAddress()).get("lat");
//			sign.setSignXy(x+","+y);
//		}*/
//		
//		//工人id 和 name 及手机号
//		
//		sign.setSignType("2");
//		//经纬度
//		Double  x =LngAndLatUtils.getLngAndLat(sign.getSignAddress()).get("lng");
//		Double  y =LngAndLatUtils.getLngAndLat(sign.getSignAddress()).get("lat");
//		sign.setSignXy(x+","+y);
//		
//		SignDetail setManagerInfoForWorker = workerSignService.setManagerInfoForWorker(sign.getPackId());
//		sign.setManagerId(setManagerInfoForWorker.getManagerId());
//		sign.setManagerName(setManagerInfoForWorker.getManagerName());
//		
//		//登录人姓名
//		sign.setSignName(SessionUtils.getWorkerSession(request).getRealname());
//		//签到所属订单id (传入任务包id,得到订单 id)
//		sign.setOrderId(workerSignService.getAddressThroughOrder(sign.getPackId()).getId());
//		//图片路径(多图以--分隔)
//	//	sign.setSignPic(sb.toString());
//		sign.setWorkerLeaderName(sign.getSignName());
//		
//		//更新该签到关联的任务包   状态为施工中
//		workerSignService.updatePackStatusById(sign.getPackId());
//		
//		
//		
//		
//		
//		
//		//保存工人组长签到详情!
//		workerSignService.signSuccess(sign);
//
//		//发送短信      ====================开始============
//		
//		String packName = workerSignService.selectPackNameById(sign.getPackId());	
//		String customerInfo = sign.getCustomerInfo();
//		String managerPhone = setManagerInfoForWorker.getManagerPhone();
//		
//		request.getSession().setAttribute("packName", packName);
//		request.getSession().setAttribute("customerInfo", customerInfo);
//		request.getSession().setAttribute("managerPhone", managerPhone);
//		
//		
//		//发送短信    ====================结束==================
//		
//		
//		
//		return "1";
//	}
	
	
	/**
	 * 工人端  签到
	 * @param signDetail
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "workersign")
	public @ResponseBody String workersign(SignDetail signDetail, Model model, HttpServletRequest request) {
		String flag = "error";
		try{
			
			// 获取工人
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

			//根据任务包id+工人id
			SignDetail detail=workerSignService.signDetailByWorkerLeaderId(signDetail.getWorkerLeaderId(),signDetail.getPackId());
			// 5-->8如果签到
			if (null!=detail){
				flag = "error";
			} else {
				// 5-->8:如果没有签到信息,允许签到
				TaskPackSignVo packSignVo = workerSignService.getCustomerInfoByPackId(signDetail.getPackId());
				sign.setCustomerInfo(packSignVo.getCustomerMessage()+"-"+packSignVo.getCustomerName());
				//保存工人组长签到详情!
				workerSignService.signSuccess(sign);
				
				
				//更新该签到关联的任务包   状态为施工中
				workerSignService.updatePackStatusById(signDetail.getPackId());
				
				
				//============================短信start==================================================
				/**
				 * 1.工人签到==项目经理
				 */
				//【美得你】订单  (小区名称-楼牌号-客户姓名) 的任务包 (任务包名称), 工人:工人组长姓名-手机号,已签到,请登录app查看详情.
				
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
				
				//============================短信end==================================================
				
				flag = "success";
			}
			
			 
		}catch(Exception e){
			flag = "error";
		}
		
		return flag;
	}
	
	
	
	

	@RequestMapping(value = "signDetail")
	public String signDetail(SignDetail detail, Model model) {

		// 根据订单id 查询签到详情
		SignDetail signDetail = workerSignService.getSignDetailByPackIdLimit(detail.getPackId());
		// 得到签到数据 回显jsp
		String[] strings = signDetail.getSignPic().split("--");
		for (String string : strings) {
			signDetail.setSignPic(string);
		}
		
		model.addAttribute("signDetail", signDetail);

		return "mobile/modules/Worker/sign_details";
	}

}
