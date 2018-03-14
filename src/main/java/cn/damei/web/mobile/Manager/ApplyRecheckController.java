package cn.damei.web.mobile.Manager;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import cn.damei.entity.mobile.Inspector.RecheckEntity;
import cn.damei.service.mobile.Inspector.RecheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.phoneMessage.PhoneMessageDao;
import cn.damei.common.utils.phoneMessage.PhoneMessageEntity;
import cn.damei.common.Base64Util;
import cn.damei.common.SessionUtils;
import cn.damei.service.mobile.Inspector.CheckItemService;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.ApplyRecheckEntity;
import cn.damei.service.mobile.Manager.ApplyRecheckService;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.QualityControl;

/**
 * 项目经理复检申请
 * 
 * @author 	梅浩
 *
 */
@Controller
@RequestMapping (value="${adminPath}/app/manager/recheck")
public class ApplyRecheckController {

	@Autowired
	private ApplyRecheckService service;
	
	/**
	 * 查询复检单list
	 * @return
	 */
	@RequestMapping(value="recheckList")
	public String recheckList(HttpServletRequest request,Model model){	
Manager manager = SessionUtils.getManagerSession(request);
	
//根据当前登录经理, 查询该经理下的复检单
//需要查询的字段有: 顾客信息,实际开工日期  合同工期  复检类型  复检单状态
List<ApplyRecheckEntity> recheckList = service.findRecheckList(manager.getId());
model.addAttribute("list", recheckList);

		
		return "mobile/modules/Manager/recheck/recheck_apply";
	}
	
	
	/**
	 * 去复检项申请附件页面
	 * @param recheckId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="toRecheckCheckItemList")
	public String toRecheckCheckItemList(String recheckId,HttpServletRequest request,Model model){
		//根据复检单id  查询不合格的检查项
		//需要的数据有: 线上线下,检查项名称, 是否合格
		List<ApplyRecheckEntity> checkItemList = service.selectCheckItemByRecheckId(Integer.parseInt(recheckId));
		
		model.addAttribute("list", checkItemList);
		model.addAttribute("recheckId", recheckId);
		
		
		
		return "mobile/modules/Manager/recheck/recheck_apply_details";
	}
	
	
	@Autowired
	private RecheckService recheckService;
	@RequestMapping(value="applyRecheck" ,method=RequestMethod.POST)
	public @ResponseBody String applyRecheck(String recheckId,String input_date,String[] photo,HttpServletRequest request) throws ParseException{
			//期望验收日期
			//复检单id
			//整改的图片

		//检查质检单是否重复提交
		RecheckEntity re=recheckService.findRecheckById(Integer.parseInt(recheckId==null?"0":recheckId));
		if(null!=re){

			if(re.getRecheckStatus().equals("2")){

				return "repeat";
			}

		}


		//插入照片表, 需要的有: type:1 (质检) id (复检单id), pic_url (图片路径),pic_date (上传日期)
		
		
		
		
		
		
		//		1:==============================保存图片
		if (null!=photo&&photo.length>0) {
			
			for(String p : photo){
				
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				
				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + ConstantUtils.UPLOAD_RECHECK + DateUtils.getDate1());
				//判断该文件是否存在
				if(!filePath.exists() && !filePath.isDirectory()){
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);
				
				String picpath = ConstantUtils.UPLOAD_RECHECK + DateUtils.getDate1()+filePath.separator + uuid + ".jpeg";
				
				//保存图片到数据库
				ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
				//业务类型,   1: 质检单
				reportCheckDetailsPic.setBusinessType("1");
				//图片路径
				reportCheckDetailsPic.setPicUrl(picpath);
					//上传图片时间
				reportCheckDetailsPic.setPicDatetime(new Date());
				//关联复检单id
				reportCheckDetailsPic.setBusinessIdInt(Integer.parseInt(recheckId));
				service.savePic(reportCheckDetailsPic);
			
			}
		}
		
		//2:============================更新复检单表: 
		//添加期望验收日期, 更改复检单status  由已创建 到  已申请(1-->2)   By  recheckId ,apply_employee_id 申请人id
		
		ApplyRecheckEntity recheck = new ApplyRecheckEntity();
		
		recheck.setManagerId(SessionUtils.getManagerSession(request).getId() );
		recheck.setRecheckStatus("2");
		recheck.setHopeApplyDate(new SimpleDateFormat("yyyy-MM-dd").parse(input_date));
		recheck.setRecheckId(Integer.parseInt(recheckId));
		service.updateRecheckStatus(recheck);
		
		
		
		//3: 保存短信
//订单（东晨小区-10-4-202-王维-13333333333），项目经理（王毅-13212341234），项目经理申请复检，请及时登录APP查看详情。
		
		//发给质检员,短信内是经理
		QualityControl control = itemService.findMessageInfoByInspectId2(Integer.parseInt(recheckId));
		PhoneMessageEntity entity = new PhoneMessageEntity();
		entity.setReceiveEmployeeId(control.getOrderInspectorId());
		entity.setReceivePhone(control.getPhone());
		entity.setMessageContent("订单（" + control.getCommunityName() + "-" + control.getBuildNumber()
				+ "-" + control.getBuildUnit() + "-" + control.getBuildRoom() + "-"
				+ control.getCustomerName() + "-" + control.getCustomerPhone() + "，项目经理（"
				+ SessionUtils.getManagerSession(request).getRealname()+ "-"
				+ SessionUtils.getManagerSession(request).getPhone()
				+ "），项目经理申请复检，请及时登录APP查看详情。");
		entity.setMessageGenerateTime(new Date());
		entity.setStatus("0");
		entity.setRelatedBusinessId(Integer.parseInt(recheckId));
		entity.setRelatedBusinessType("600301");
		messageDao.saveMessageContent(entity);
		
		
		return "OK";
	}
	
	@Autowired
	private CheckItemService itemService;
	@Autowired
	private PhoneMessageDao messageDao;

}
