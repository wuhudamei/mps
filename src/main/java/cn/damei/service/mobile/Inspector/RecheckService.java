package cn.damei.service.mobile.Inspector;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.MessagePushType;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.phoneMessage.PhoneMessageDao;
import cn.damei.common.utils.phoneMessage.PhoneMessageEntity;
import cn.damei.common.Base64Util;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Inspector.InspectSign;
import cn.damei.dao.mobile.Inspector.CheckItemDao;
import cn.damei.dao.mobile.Inspector.RecheckDao;
import cn.damei.entity.mobile.Inspector.RecheckEntity;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.QualityControl;
import cn.damei.entity.modules.BizMsg;
import cn.damei.service.modules.BizProjectChangeBillService;


@Service
@Transactional(readOnly=false)
public class RecheckService {

	@Autowired
	private  RecheckDao dao;
	@Autowired
	private BizProjectChangeBillService bizProjectChangeBillService;
	


	
	public List<RecheckEntity> findRecheckList(Integer   inspectId){
		
		return dao.findRecheckList(inspectId);
	}

	

	public String getOrderLngLatByOrderId(Integer orderId) {

		return dao.getOrderLngLatByOrderId(orderId);
	}


	@Transactional(readOnly = false)
	public void inspectorSign(InspectSign InspectSign) {

		dao.inspectorSign(InspectSign);
	}

	public List<RecheckEntity> findRecheckPic(Integer recheckId){


		return dao.findRecheckPic(recheckId);
	}

	public Integer findInspectSignRecord(Integer inspectId) {

		return dao.findInspectSignRecord(inspectId);
	}


	@Transactional(readOnly = false)
	public void updateInspectRecord(InspectSign detail) {

		dao.updateInspectRecord(detail);
	}
	
	

	public void getCheckItemByRecheckId(Integer recheckId,Model model) throws IOException{
		
		
		
		List<RecheckEntity> checkItem = dao.findRecheckCheckItem(recheckId);
		
		if(null!=checkItem&&checkItem.size()>0){
			
			model.addAttribute("checkItemList", checkItem);
			model.addAttribute("recheckId", checkItem.get(0).getRecheckId());
		}else{
			
			model.addAttribute("error", "该复检单没有检查项了");
		}
		
		List<RecheckEntity> recheckPic = dao.findRecheckPic(recheckId);
		if(null!=recheckPic&&recheckPic.size()>0){
			
			model.addAttribute("baseUrl", PicRootName.picPrefixName());
			model.addAttribute("recheckPicList", recheckPic);
		
			model.addAttribute("picLength", recheckPic.size());
		}else{
			
			model.addAttribute("error", "该复检单没有照片");
			
		}
		
	
	}
	
	
	@Transactional(readOnly=false)
	public void  deletePic(Integer picId){
		dao.deletePic(picId);
	}
	
	
	
	

	@Transactional(readOnly=false)
	public boolean  recheckManRecheckedRecheckCheckItemAndPhoto(String recheckId, String[] photo, String []checkItemId, String[] isPassed,HttpServletRequest request){
		
		try{
			Date date = new Date();

				if(null!=recheckId){
						

					if(null!=photo&&photo.length>0){
						
						for (String p : photo) {
							
							String uuid = UUID.randomUUID().toString().replaceAll("-", "");
							String rootPath = request.getSession().getServletContext().getRealPath("");
							File filePath = new File(rootPath +ConstantUtils.UPLOAD_RECHECK +DateUtils.getDate1());
							
							if(!filePath.exists()&&!filePath.isDirectory()){
								filePath.mkdirs();
							}
							String picPath = filePath+filePath.separator+uuid+".jpeg";
							Base64Util.generateImage(p, picPath);
							
							String savePath = ConstantUtils.UPLOAD_RECHECK+DateUtils.getDate1()+filePath.separator+uuid+".jpeg";
							ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
							reportCheckDetailsPic.setBusinessIdInt(Integer.valueOf(recheckId));
							reportCheckDetailsPic.setBusinessType("2");
							reportCheckDetailsPic.setPicUrl(savePath);
							reportCheckDetailsPic.setPicDatetime(date);
							dao.savePic(reportCheckDetailsPic);
						}
						
					}
					

					
					if(null!=isPassed&&isPassed.length>0){
						
						RecheckEntity entity = new RecheckEntity();
						
						

						Integer  length = isPassed.length;
						boolean  flag = false;
						for(int v=0;v<isPassed.length;v++){
							
							entity.setCheckItemId(Integer.parseInt(checkItemId[v]));
							entity.setIsPassed(isPassed[v]);
						
							if(isPassed[v].equals("1")){

								entity.setUpdateTime(date);
								length--;
								if(length==0){
									flag = true;
								}
							}
							
							dao.updateRecheckCheckItem(entity);
							
							
							
						}

						
						

						RecheckEntity recheckEntity = dao.findRecheckById(Integer.parseInt(recheckId));
						
						if(recheckEntity == null){
							recheckEntity = new RecheckEntity();
						}
						if(null!=recheckEntity){

							if(null!=recheckEntity.getRecheckTimes()){
								
								recheckEntity.setRecheckTimes(recheckEntity.getRecheckTimes()+1);
							}else{

								recheckEntity.setRecheckTimes(1);
							}
						}

						if(flag){

							

							QualityControl control = itemDao.findMessageInfoByInspectId(Integer.parseInt(recheckId));
							String content = "订单（" + control.getCommunityName() + "-" + control.getBuildNumber() + "-" + control.getBuildUnit() + "-" + control.getBuildRoom() + "-" + control.getCustomerName() + "-" + control.getCustomerPhone() + "，质检员（" + SessionUtils.getInspectorSession(request).getRealName() + "-" + SessionUtils.getInspectorSession(request).getPhone() + "）质检员复检合格,请及时登录APP查看详情。";

							PhoneMessageEntity entity2 = new PhoneMessageEntity();
							entity2.setReceiveEmployeeId(control.getItemManagerId());
							entity2.setReceivePhone(control.getPhone());
							entity2.setMessageContent(content);
							entity2.setMessageGenerateTime(date);
							entity2.setStatus("0");
							entity2.setRelatedBusinessId(Integer.parseInt(recheckId));
							entity2.setRelatedBusinessType("600402");
							messageDao.saveMessageContent(entity2);
							

							

							BizMsg bizMsg = new BizMsg();
							bizMsg.setMsgTitle("质检员复检合格");
							bizMsg.setMsgTime(date);
							bizMsg.setMsgContent(content);
							bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
							bizMsg.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_103002001);
							bizMsg.setBusiIdInt(Integer.valueOf(recheckId));
							bizMsg.setEmployeeId(control.getItemManagerId());
							bizProjectChangeBillService.saveBizMsg(bizMsg);

							
							
						recheckEntity.setRecheckStatus("4");
						}else{


							recheckEntity.setRecheckStatus("3");
							

							QualityControl control = itemDao.findMessageInfoByInspectId(Integer.parseInt(recheckId));
							String content2 = "订单（" + control.getCommunityName() + "-" + control.getBuildNumber() + "-" + control.getBuildUnit() + "-" + control.getBuildRoom() + "-" + control.getCustomerName() + "-" + control.getCustomerPhone() + "，质检员（" + SessionUtils.getInspectorSession(request).getRealName() + "-" + SessionUtils.getInspectorSession(request).getPhone() + "）质检员复检不合格，不合格原因（检查不合格），请及时登录APP查看详情。";
							PhoneMessageEntity entity2 = new PhoneMessageEntity();
							entity2.setReceiveEmployeeId(control.getItemManagerId());
							entity2.setReceivePhone(control.getPhone());
							entity2.setMessageContent(content2);
							entity2.setMessageGenerateTime(date);
							entity2.setStatus("0");
							entity2.setRelatedBusinessId(Integer.parseInt(recheckId));
							entity2.setRelatedBusinessType("600401");
							messageDao.saveMessageContent(entity2);
							

							

							BizMsg bizMsg = new BizMsg();
							bizMsg.setMsgTitle("质检员复检不合格");
							bizMsg.setMsgTime(date);
							bizMsg.setMsgContent(content2);
							bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
							bizMsg.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_103002002);
							bizMsg.setBusiIdInt(Integer.valueOf(recheckId));
							bizMsg.setEmployeeId(control.getItemManagerId());
							bizProjectChangeBillService.saveBizMsg(bizMsg);

							
							
						}
						recheckEntity.setRecheckManId(SessionUtils.getInspectorSession(request).getId());
						recheckEntity.setRecheckDate(date);
						dao.updateRecheck(recheckEntity);
						
						return true;
					}
					
					
				}
		}catch(Exception e){
			e.printStackTrace();
			return false;
			
		}
				
				
	return true;
		
	}
	@Autowired
	private CheckItemDao  itemDao;
	@Autowired
	private PhoneMessageDao messageDao;


	public RecheckEntity findRecheckById(Integer recheckId){


		return dao.findRecheckById(recheckId);
	}

}
