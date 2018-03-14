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


@Controller
@RequestMapping (value="${adminPath}/app/manager/recheck")
public class ApplyRecheckController {

	@Autowired
	private ApplyRecheckService service;
	

	@RequestMapping(value="recheckList")
	public String recheckList(HttpServletRequest request,Model model){	
Manager manager = SessionUtils.getManagerSession(request);
	


List<ApplyRecheckEntity> recheckList = service.findRecheckList(manager.getId());
model.addAttribute("list", recheckList);

		
		return "mobile/modules/Manager/recheck/recheck_apply";
	}
	
	

	@RequestMapping(value="toRecheckCheckItemList")
	public String toRecheckCheckItemList(String recheckId,HttpServletRequest request,Model model){


		List<ApplyRecheckEntity> checkItemList = service.selectCheckItemByRecheckId(Integer.parseInt(recheckId));
		
		model.addAttribute("list", checkItemList);
		model.addAttribute("recheckId", recheckId);
		
		
		
		return "mobile/modules/Manager/recheck/recheck_apply_details";
	}
	
	
	@Autowired
	private RecheckService recheckService;
	@RequestMapping(value="applyRecheck" ,method=RequestMethod.POST)
	public @ResponseBody String applyRecheck(String recheckId,String input_date,String[] photo,HttpServletRequest request) throws ParseException{





		RecheckEntity re=recheckService.findRecheckById(Integer.parseInt(recheckId==null?"0":recheckId));
		if(null!=re){

			if(re.getRecheckStatus().equals("2")){

				return "repeat";
			}

		}



		
		
		
		
		
		

		if (null!=photo&&photo.length>0) {
			
			for(String p : photo){
				
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				
				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + ConstantUtils.UPLOAD_RECHECK + DateUtils.getDate1());

				if(!filePath.exists() && !filePath.isDirectory()){
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);
				
				String picpath = ConstantUtils.UPLOAD_RECHECK + DateUtils.getDate1()+filePath.separator + uuid + ".jpeg";
				

				ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();

				reportCheckDetailsPic.setBusinessType("1");

				reportCheckDetailsPic.setPicUrl(picpath);

				reportCheckDetailsPic.setPicDatetime(new Date());

				reportCheckDetailsPic.setBusinessIdInt(Integer.parseInt(recheckId));
				service.savePic(reportCheckDetailsPic);
			
			}
		}
		


		
		ApplyRecheckEntity recheck = new ApplyRecheckEntity();
		
		recheck.setManagerId(SessionUtils.getManagerSession(request).getId() );
		recheck.setRecheckStatus("2");
		recheck.setHopeApplyDate(new SimpleDateFormat("yyyy-MM-dd").parse(input_date));
		recheck.setRecheckId(Integer.parseInt(recheckId));
		service.updateRecheckStatus(recheck);
		
		
		


		

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
