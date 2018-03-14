package cn.damei.web.mobile.Manager;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import cn.damei.service.modules.BizOrderChecksizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.config.Global;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.Base64Util;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.Checksize;
import cn.damei.entity.mobile.Manager.ChecksizeOrder;
import cn.damei.entity.mobile.Manager.ChecksizePic;
import cn.damei.entity.mobile.Manager.ChecksizeType;
import cn.damei.service.mobile.Manager.ChecksizeService;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.modules.BizOrderChecksizePic;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.service.modules.BizMessagegroupService;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;

import net.sf.json.JSONObject;

/**
 * 上报复尺
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="${adminPath}/app/manager")
public class ChecksizeController {

	@Autowired
	private ChecksizeService checksizeService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private BizMessagegroupService bizMessagegroupService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizOrderChecksizeService bizOrderChecksizeService;
	
	
	//上报复尺列表页
	@RequestMapping(value={"checksizeList",""})
	public String checksizeList(String timeForbidden,HttpServletRequest request,Model model){
		
		//获取项目经理
		Manager manager = SessionUtils.getManagerSession(request);
		
		//根据项目经理id查询项目经理下的状态小于300的所有订单
		List<ChecksizeOrder> checksizeOrder = checksizeService.findOrderByManagerId(manager.getId());
		
		for(ChecksizeOrder order : checksizeOrder){
			String info = order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName();
			order.setInfo(info);
		}
		model.addAttribute("timeForbidden",timeForbidden );
		model.addAttribute("checksizeOrder", checksizeOrder);
		return "mobile/modules/Manager/progressMain/checksize/recheck";
	}
	
	//复尺详情页
	@RequestMapping(value={"checksizeDetails",""})
	public String checksizeDetails(Integer id,HttpServletRequest request,Model model){
		
		//查询该订单最新一次申请厂家复尺的时间是否间隔有5分钟
		Checksize checksize = checksizeService.findTimeSpan(id);
		
		if(null!=checksize){
			if(checksize.getCreateDate().getTime()+300*1000 > new Date().getTime()){
				//如果小于5分钟 则不允许申请厂家复尺,并给出提示
				return "redirect:"+Global.getAdminPath()+"/app/manager/checksizeList?timeForbidden=1";
			}
		}
		
		ChecksizeOrder order = checksizeService.findOrder(id);
		String info = order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName();
		
		//查询复尺类型
		List<ChecksizeType> list = checksizeService.findType(id);
		model.addAttribute("info", info);
		model.addAttribute("orderId", id);
		model.addAttribute("list", list);
		
		return "mobile/modules/Manager/progressMain/checksize/recheck_pic";
	}
	
	
	//上传复尺
	@RequestMapping(value = "checksize", method = RequestMethod.POST)
	@ResponseBody
	public  String checksize(String orderId,String checksizeType,String checksizeDate,String remarks,HttpServletRequest request, String[] photo) throws ParseException {
		
		Manager manager = SessionUtils.getManagerSession(request);
		Integer checksizeEmployeeId = manager.getId();
		Date date = new Date();
		//对复尺的时间进行控制
		if(checksizeType!=null&&"".equals("")){
//			ChecksizeOrder order = checksizeService.findOrder(Integer.parseInt(orderId));
//			//延期时间
//			String data = checksizeService.findDaysPlanChecksize(checksizeType);
//			//实际开工时间
//			Date actualStartDate = order.getActualStartDate();
//			
//			long time = 0l;
//			if(data!=null&&!data.equals("")){
//				time = Integer.parseInt(data)*24*60*60*1000;
//			}else{
//				data = "0";
//			}
//			long time2 = actualStartDate.getTime();
//			long time3 = time + time2;
//			long time4 = new Date().getTime();
//			SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
//			
//			String format2 = sdf.format(new Date(time2));
//			String format3 = sdf.format(new Date(time3));
//			
//			
//			if(time3>time4){
//				String str = "{code:3,message:"+format3+",actualStartDate:"+format2+",dayCount:"+data+"}";
//				JSONObject fromObject = JSONObject.fromObject(str);
//				String string = fromObject.toString();
//				return string;
//			}
			
			//校验：该工地2017-7-1开工，按照工程部规定主材（橱柜、台面）开工10天后（2017-7-21）才可以申请橱柜复尺，如有提前完工或疑问请联系大区经理。
			Checksize checksize = checksizeService.findChecksizeCanApplyDate(checksizeType);
			if(null != checksize){
				//该工地2017-7-1开工，按照工程部规定主材（橱柜、台面）开工10天后（2017-7-21）才可以申请橱柜复尺，如有提前完工或疑问请联系大区经理。
				//String messageRemark = "该工地"+checksize.getOrderActualStartDateString()+"开工，按照工程部规定主材（"+checksize.getInstallItemName()+"）开工"+checksize.getDaysPlanChecksizeString()+"天后（"+checksize.getCanApplyChecksizeDateString()+"）才可以申请橱柜复尺，如有提前完工或疑问请联系大区经理。";
				String str = "{code:3,message:"+checksize.getCanApplyChecksizeDateString()+",actualStartDate:"+checksize.getOrderActualStartDateString()+",dayCount:"+checksize.getDaysPlanChecksizeString()+"}";
				JSONObject fromObject = JSONObject.fromObject(str);
				String string = fromObject.toString();
				return string;
			}
			
			
			
		}
		
		
		
		//查询该订单最新一次申请厂家复尺的时间是否间隔有5分钟
		Checksize checksizeVerification = checksizeService.findTimeSpan(Integer.valueOf(orderId));
		
		if(null!=checksizeVerification && null != checksizeVerification.getCreateDate()){
			if(checksizeVerification.getCreateDate().getTime()+300*1000 > new Date().getTime()){
				//如果小于5分钟 则不允许申请厂家复尺,并给出提示
				return "1";
			}
		}
				
		//保存复尺信息
		Integer checksizeId = checksizeService.saveChecksize(orderId,checksizeType,checksizeDate,checksizeEmployeeId,remarks);
		if(null==checksizeId || checksizeId<1){
			//复尺信息保存失败
			return "2";
		}
		
		List<ChecksizePic> pList = new ArrayList<ChecksizePic>();
		
		//保存复尺图片
		if (null!=photo && photo.length>0) {


			for (String p : photo) {
				
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				
//				String rootPath = PicRootName.SystemEnvironment(request);
				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + ConstantUtils.UPLOAD_CHECKSIZE + DateUtils.getDate1());
				//判断该文件是否存在
				if(!filePath.exists() && !filePath.isDirectory()){
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);
				
				String picpath = ConstantUtils.UPLOAD_CHECKSIZE + DateUtils.getDate1()+filePath.separator + uuid + ".jpeg";
				
				ChecksizePic checksizePic = new ChecksizePic();
				checksizePic.setId(null);
				checksizePic.setPicUrl(picpath);
				checksizePic.setOrderChecksizeId(checksizeId);
				checksizePic.setCreateDate(date);
				checksizePic.setUpdateDate(date);
				checksizePic.setDelFlag("0");
				pList.add(checksizePic);
//				checksizeService.saveChecksizePic(checksizePic);
			}

			//批量插入上报厂家复尺图片
			checksizeService.saveChecksizePicAll(pList);
			
		}
		
		ChecksizeOrder change = checksizeService.findOrder(Integer.valueOf(orderId));
		String check = checksizeService.findcheck(checksizeType);
		
		
		//=====================================短信start========================================================
		/**
		 * 项目经理申请厂家复尺==材料员
		 */
		//材料员【美得你】订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），项目经理（姓名-手机号），项目经理已申请复尺（复尺内容），请及时登录系统联系供应商。
		String content = "订单（"+change.getCommunityName()+"-"+change.getBuildNumber()+"-"+change.getBuildUnit()+"-"+change.getBuildRoom()+"-"+change.getCustomerName()+"-"+change.getCustomerPhone()+"），项目经理（"+manager.getRealname()+"-"+manager.getPhone()+"），项目经理已申请复尺（"+check+"），请及时登录系统联系供应商。";
		BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(change.getStoreId()+"","9");
		List<Integer> list = new ArrayList<Integer>();
		List<BizEmployee2> employeelist = null;
		if(null != bizMessagegroup ){
			String[] str = bizMessagegroup.getEmployees().split(",");
			for(String id1: str){
				list.add(Integer.valueOf(id1));
			}
			employeelist = bizEmployeeService2.getById(list);
			if(null != employeelist && employeelist.size()>0){
				for (BizEmployee2 bizEmployee2 : employeelist) {
					
					BizPhoneMsg phone = new BizPhoneMsg();
					phone.setReceiveEmployeeId(bizEmployee2.getId());
					phone.setReceivePhone(bizEmployee2.getPhone());
					phone.setMsgContent(content);
					phone.setMsgGenerateDatetime(new Date());
					phone.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
					phone.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_300102);
					phone.setRelatedBusinessIdInt(checksizeId);
					bizPhoneMsgService.insert(phone);
				}
			}
		}
		//=====================================短信end========================================================
		
		
		return "0";

	}
	
	
	//复尺记录
	@RequestMapping(value={"checksizeRecord",""})
	public String checksizeRecord(Integer id,HttpServletRequest request,Model model){
		
		//查询该订单所有的厂家复尺
		List<Checksize> list = null;
		list = checksizeService.findCheckSizeList(id);
		model.addAttribute("list", list);
			
		return "mobile/modules/Manager/progressMain/checksize/reviewRecord";
	}
	
	//复尺--图片
	@RequestMapping(value={"checksizeRecordPic",""})
	public String checksizeRecordPic(Integer checksizeId,HttpServletRequest request,Model model) throws IOException{
		//通过复尺表id查询复尺图片
		List<BizOrderChecksizePic> picList = bizOrderChecksizeService.selectPicByOrderChecksizeId(checksizeId);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("picList", picList);
		model.addAttribute("baseUrl", baseUrl);
		
		return "mobile/modules/Manager/progressMain/checksize/photo";
	}
		
		
}
