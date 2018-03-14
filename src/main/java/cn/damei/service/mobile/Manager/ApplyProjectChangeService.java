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

	private static Logger logger = LoggerFactory.getLogger(ApplyProjectChangeService.class);// 日志

	@Autowired
	private ApplyProjectChangeDao dao;
	@Autowired
	private PhoneMessageDao messageDao;
	@Autowired
	private CheckConfirmDao checkConfirmDao;
	@Autowired
	private OrderDao orderDao;
	/**
	 * 订单
	 * 
	 * @param managerId
	 * @return
	 */
	public List<ApplyProjectChangeEntity> findOrderList(Integer managerId) {

		return dao.findOrderList(managerId);
	}

	// 是否有申请记录
	public Integer findOrderCount(Integer orderId) {

		return dao.findOrderCount(orderId);
	}

	/**
	 * 查询所有增项,套餐外, 状态启用的 施工分类和施工项
	 * 
	 * @return
	 */
	public List<ProjectItem> findAddItemList(Integer typeId, String storeId) {

		return dao.findAddItemList(typeId,storeId);
	}
	
	/**
	 * 查询所有启用的变更分类
	 * @return
	 */
	public List<ProjectItemType> findAllProjectTypes(){
		
		return dao.findAllProjectTypes();
	}

	/**
	 * 减项的 套餐内外, 根据参数决定
	 * 
	 * @param item
	 * @return
	 */
	public List<ProjectItem> findMinusInnerItemList(ProjectItem item) {

		return dao.findMinusInnerItemList(item);
	}

	/**
	 * 根据施工项查询详情
	 * 
	 * @param itemId
	 * @param storeId
     * @return
	 */
	public ProjectItem findProjectItemDetailById(String itemId, String storeId) {

		return dao.findItemDetailById(itemId,storeId);
	}

	/**
	 * 提交变更单, 保存变更项详情 biz_project_change_bill biz_project_changebill_item
	 * 
	 * @param changeReason
	 * @param itemDetail
	 * @param itemId
	 * @param itemCount
	 * @param orderId
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveChangeForm(String changeReason,String[] addOrMinusNum, String[] itemDetail, String[] itemId, String[] itemCount,
			String[] price, String orderId, String changeId,String[] photo, HttpServletRequest request) {

		

		Double totalAddPrice = 0D;
		Double totalMinusPrice = 0D;
		// 1: 保存变更单: 订单id(param) , 生成变更单编号, 变更原因(param) 提交日期: new Date() ,
		// 增项总价(根据1-3 查询的价格 *数量 相加为总价)
		// 减项总价 , 状态
		ApplyProjectChangeEntity entity = new ApplyProjectChangeEntity();
		entity.setOrderId(Integer.parseInt(orderId));
		entity.setProjectChangeCode(changeBillCode());
		entity.setChangeReason(changeReason);
		entity.setApplyDate(new Date());
		entity.setAddItemTotalPrice(totalAddPrice);
		entity.setMinusItemTotalPrice(totalMinusPrice);
		entity.setStatus("10");
		// 2: 根据保存的变更单返回主键: 保存 变更项记录
		dao.saveProjectBill(entity);
		if (null != changeId) {

			// 根据变更单id ,删除之前的数据
			dao.deleteChangeBillById(Integer.parseInt(changeId));
			dao.deleteChangeItemByBillId(Integer.parseInt(changeId));
			//更新图片唯一标识
			dao.updatePicBusinessId(entity.getProjectChangeId(),changeId);
			

		}
		
		Date date = new Date();
		
		List<ReportCheckDetailsPic> pList = new ArrayList<ReportCheckDetailsPic>();
		//保存图片
		if (null!=photo && photo.length>0) {
			
			for(String p : photo){
				
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				
//				String rootPath = RootName.SystemEnvironment(request);
				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + ConstantUtils.UPLOAD_CONFIRM + DateUtils.getDate1());
				//判断该文件是否存在
				if(!filePath.exists() && !filePath.isDirectory()){
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);
				
				String picpath = ConstantUtils.UPLOAD_CONFIRM + DateUtils.getDate1()+filePath.separator + uuid + ".jpeg";
				
				//保存图片到数据库
				ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
				//id
				reportCheckDetailsPic.setBusinessIdInt(entity.getProjectChangeId());
				//类型
				reportCheckDetailsPic.setBusinessType(PictureTypeContantUtil.PICTURE_TYPE_105);
				reportCheckDetailsPic.setPicUrl(picpath);
				reportCheckDetailsPic.setPicDatetime(date);
				reportCheckDetailsPic.setCreateDate(date);
				reportCheckDetailsPic.setUpdateDate(date);
				reportCheckDetailsPic.setDelFlag("0");
				pList.add(reportCheckDetailsPic);
//				checkConfirmService.savePic(reportCheckDetailsPic);
			}
			//批量插入约检验收图片
			checkConfirmDao.savePicAll(pList);
		}

		for (int v = 0; v < itemId.length; v++) {
			// 根据对应的itemId , 查询 分类id , 名称, 增减项,施工项编码,施工项name 施工项unit 施工项套餐
			//根据订单ID查找门店
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
				// 设置数量
				projectItem.setItemCount(Double.parseDouble(itemCount[v]));

				if (null != projectItem.getItemPrice()) {
					// 1:增 项价格
					if (projectItem.getItemWay().equals("1")) {
						totalAddPrice += Double.parseDouble(price[v]) * projectItem.getItemCount();
						// 2:减项价格
					} else if (projectItem.getItemWay().equals("2")) {
						totalMinusPrice += Double.parseDouble(price[v]) * projectItem.getItemCount();

					}

				} else {
					logger.warn("保存项目经理申请施工变更项时:有数据异常 !!!    施工项id为:" + itemId[v] + "的价格为空");
					return "0";

				}

				//
			} else {

				logger.warn("保存项目经理申请施工变更项时:有数据异常 !!!    施工项id为:" + itemId[v] + "的数量为空");
				return "0";
			}

			// 4: 需要保存的字段有:由1-3(提供) 变更单id 分类名称 增减项 施工项的编码 施工项名称 单位 套餐类型
			// ,详情(param) createDate( newDate())

			dao.saveProjectItem(projectItem);

		}
		// 更新施工单 总价
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

	/**
	 * 查询申请记录
	 * 
	 * @param orderId
	 * @return
	 */
	public List<ApplyProjectChangeEntity> changeRecord(String orderId) {
		List<ApplyProjectChangeEntity> list = dao.findProjectApplyByOrderId(Integer.parseInt(orderId));

		return list;
	}

	// 变更单详情
	public ApplyProjectChangeEntity findChangeBillDetailById(Integer billId) {
		return dao.findChangeBillDetailById(billId);
	}

	// 变更单详情
	public ApplyProjectChangeEntity findChangeBillDetailByIdAndStatus(Integer billId) {
		return dao.findChangeBillDetailByIdAndStatus(billId);
	}

	// 变更项详情
	public List<ProjectItem> findChangeItemByChangeBillId(Integer billId) {

		return dao.findChangeItemByChangeBillId(billId);

	}

	public String findCustomerNameByprojectChangeId(Integer projectId) {

		return dao.findCustomerNameByprojectChangeId(projectId);
	}

	// 变更单编号
	public String changeBillCode() {

		StringBuilder builder = new StringBuilder();
		// num
		ReCheckCode code1 = dao.getCode();

		if (null == code1) {
			dao.saveCode();
			code1 = dao.getCode();
		}
		builder.append(code1.getBussinessType());
		// 格式后的时间戳
		String format = new SimpleDateFormat("yyyyMMdd").format(new Date());
		if (!new SimpleDateFormat("yyyyMMdd").format(code1.getGenerateTime()).equals(format)) {
			code1.setGenerateTime(new Date());
		}
		builder.append(new SimpleDateFormat("yyyyMMdd").format(code1.getGenerateTime()));
		code1.setLastSeiralnum((code1.getLastSeiralnum() + 1));
		dao.updateCode(code1);
		String code = String.valueOf(code1.getLastSeiralnum());
		// 判断长度
		if (code.length() == 1) {

			builder.append("000").append(code);

		} else if (code.length() == 2) {
			// 拼接采购单编号
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
