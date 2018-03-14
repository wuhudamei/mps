package cn.damei.service.mobile.Manager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import cn.damei.dao.modules.OrderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.phoneMessage.MessageEmployeePhoneAndId;
import cn.damei.common.utils.phoneMessage.PhoneMessageDao;
import cn.damei.common.utils.phoneMessage.PhoneMessageEntity;
import cn.damei.common.Base64Util;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Inspector.ReCheckCode;
import cn.damei.dao.mobile.Inspector.CheckConfirmDao;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.dao.mobile.Manager.ApplyProjectChangeDao;
import cn.damei.entity.mobile.Manager.ApplyProjectChangeEntity;
import cn.damei.entity.mobile.Manager.ProjectItem;
import cn.damei.entity.mobile.Manager.ProjectItemType;

@Service
@Transactional(readOnly = true)
public class ApplyProjectChangeService {

	private static Logger logger = LoggerFactory.getLogger(ApplyProjectChangeService.class);

	@Autowired
	private ApplyProjectChangeDao dao;
	@Autowired
	private PhoneMessageDao messageDao;
	@Autowired
	private CheckConfirmDao checkConfirmDao;
	@Autowired
	private OrderDao orderDao;

	public List<ApplyProjectChangeEntity> findOrderList(Integer managerId) {

		return dao.findOrderList(managerId);
	}


	public Integer findOrderCount(Integer orderId) {

		return dao.findOrderCount(orderId);
	}


	public List<ProjectItem> findAddItemList(Integer typeId, String storeId) {

		return dao.findAddItemList(typeId,storeId);
	}
	

	public List<ProjectItemType> findAllProjectTypes(){
		
		return dao.findAllProjectTypes();
	}


	public List<ProjectItem> findMinusInnerItemList(ProjectItem item) {

		return dao.findMinusInnerItemList(item);
	}


	public ProjectItem findProjectItemDetailById(String itemId, String storeId) {

		return dao.findItemDetailById(itemId,storeId);
	}


	@Transactional(readOnly = false)
	public String saveChangeForm(String changeReason,String[] addOrMinusNum, String[] itemDetail, String[] itemId, String[] itemCount,
			String[] price, String orderId, String changeId,String[] photo, HttpServletRequest request) {

		

		Double totalAddPrice = 0D;
		Double totalMinusPrice = 0D;



		ApplyProjectChangeEntity entity = new ApplyProjectChangeEntity();
		entity.setOrderId(Integer.parseInt(orderId));
		entity.setProjectChangeCode(changeBillCode());
		entity.setChangeReason(changeReason);
		entity.setApplyDate(new Date());
		entity.setAddItemTotalPrice(totalAddPrice);
		entity.setMinusItemTotalPrice(totalMinusPrice);
		entity.setStatus("10");

		dao.saveProjectBill(entity);
		if (null != changeId) {


			dao.deleteChangeBillById(Integer.parseInt(changeId));
			dao.deleteChangeItemByBillId(Integer.parseInt(changeId));

			dao.updatePicBusinessId(entity.getProjectChangeId(),changeId);
			

		}
		
		Date date = new Date();
		
		List<ReportCheckDetailsPic> pList = new ArrayList<ReportCheckDetailsPic>();

		if (null!=photo && photo.length>0) {
			
			for(String p : photo){
				
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				

				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + ConstantUtils.UPLOAD_CONFIRM + DateUtils.getDate1());

				if(!filePath.exists() && !filePath.isDirectory()){
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);
				
				String picpath = ConstantUtils.UPLOAD_CONFIRM + DateUtils.getDate1()+filePath.separator + uuid + ".jpeg";
				

				ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();

				reportCheckDetailsPic.setBusinessIdInt(entity.getProjectChangeId());

				reportCheckDetailsPic.setBusinessType(PictureTypeContantUtil.PICTURE_TYPE_105);
				reportCheckDetailsPic.setPicUrl(picpath);
				reportCheckDetailsPic.setPicDatetime(date);
				reportCheckDetailsPic.setCreateDate(date);
				reportCheckDetailsPic.setUpdateDate(date);
				reportCheckDetailsPic.setDelFlag("0");
				pList.add(reportCheckDetailsPic);

			}

			checkConfirmDao.savePicAll(pList);
		}

		for (int v = 0; v < itemId.length; v++) {


			String storeId = orderDao.findStoreId(orderId);
			ProjectItem projectItem = dao.findItemDetailById(itemId[v],storeId);
			projectItem.setItemWay(addOrMinusNum[v]);
			if (itemDetail.length > 0 && itemDetail[v] != "") {
				projectItem.setItemDescription(itemDetail[v]);

			} else {
				projectItem.setItemDescription("");

			}
			projectItem.setChangeBillId(entity.getProjectChangeId());
			projectItem.setItemWay(addOrMinusNum[v]);
			if (null != itemCount[v] && itemCount[v] != "") {

				projectItem.setItemCount(Double.parseDouble(itemCount[v]));

				if (null != projectItem.getItemPrice()) {

					if (projectItem.getItemWay().equals("1")) {
						totalAddPrice += Double.parseDouble(price[v]) * projectItem.getItemCount();

					} else if (projectItem.getItemWay().equals("2")) {
						totalMinusPrice += Double.parseDouble(price[v]) * projectItem.getItemCount();

					}

				} else {
					logger.warn("保存项目经理申请施工变更项时:有数据异常 !!!    施工项id为:" + itemId[v] + "的价格为空");
					return "0";

				}


			} else {

				logger.warn("保存项目经理申请施工变更项时:有数据异常 !!!    施工项id为:" + itemId[v] + "的数量为空");
				return "0";
			}




			dao.saveProjectItem(projectItem);

		}

		entity.setAddItemTotalPrice(totalAddPrice);
		entity.setMinusItemTotalPrice(totalMinusPrice);
		dao.updateProjectBillMoney(entity);

		MessageEmployeePhoneAndId phoneAndId = dao.findMessageInfoByOrderId(Integer.parseInt(orderId));

		if (null != phoneAndId) {
			PhoneMessageEntity entity2 = new PhoneMessageEntity();
			entity2.setReceivePhone(phoneAndId.getDesignerPhone());
			entity2.setMessageContent("订单（" + phoneAndId.getXiaoqu() + "-" + phoneAndId.getLou() + "-"
					+ phoneAndId.getDanyuan() + "-" + phoneAndId.getShi() + "-" + phoneAndId.getCustomerName() + "-"
					+ phoneAndId.getCustomerPhone() + "），项目经理（" + SessionUtils.getManagerSession(request).getRealname()
					+ "-" + SessionUtils.getManagerSession(request).getPhone() + "），项目经理已提交施工变更申请，请及时登录系统审核。");
			entity2.setMessageGenerateTime(new Date());
			entity2.setStatus("0");
			entity2.setRelatedBusinessType("400101");
			messageDao.saveMessageContent(entity2);
		} else {

			logger.warn("项目经理在提报变更时 ,根据orderId查询到的客户,设计师信息为null,orderId 为:" + orderId);
		}

		return "1";

	}


	public List<ApplyProjectChangeEntity> changeRecord(String orderId) {
		List<ApplyProjectChangeEntity> list = dao.findProjectApplyByOrderId(Integer.parseInt(orderId));

		return list;
	}


	public ApplyProjectChangeEntity findChangeBillDetailById(Integer billId) {
		return dao.findChangeBillDetailById(billId);
	}


	public ApplyProjectChangeEntity findChangeBillDetailByIdAndStatus(Integer billId) {
		return dao.findChangeBillDetailByIdAndStatus(billId);
	}


	public List<ProjectItem> findChangeItemByChangeBillId(Integer billId) {

		return dao.findChangeItemByChangeBillId(billId);

	}

	public String findCustomerNameByprojectChangeId(Integer projectId) {

		return dao.findCustomerNameByprojectChangeId(projectId);
	}


	public String changeBillCode() {

		StringBuilder builder = new StringBuilder();

		ReCheckCode code1 = dao.getCode();

		if (null == code1) {
			dao.saveCode();
			code1 = dao.getCode();
		}
		builder.append(code1.getBussinessType());

		String format = new SimpleDateFormat("yyyyMMdd").format(new Date());
		if (!new SimpleDateFormat("yyyyMMdd").format(code1.getGenerateTime()).equals(format)) {
			code1.setGenerateTime(new Date());
		}
		builder.append(new SimpleDateFormat("yyyyMMdd").format(code1.getGenerateTime()));
		code1.setLastSeiralnum((code1.getLastSeiralnum() + 1));
		dao.updateCode(code1);
		String code = String.valueOf(code1.getLastSeiralnum());

		if (code.length() == 1) {

			builder.append("000").append(code);

		} else if (code.length() == 2) {

			builder.append("00").append(code);
		} else if (code.length() == 3) {
			builder.append("0").append(code);
		} else if (code.length() == 4) {
			builder.append(code);
		}
		return builder.toString();
	}

	public Integer findCountByItemId(Integer itemId) {

		return dao.findCountByItemId(itemId);
	}

}
