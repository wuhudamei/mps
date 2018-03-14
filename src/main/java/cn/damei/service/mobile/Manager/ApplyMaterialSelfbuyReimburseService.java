package cn.damei.service.mobile.Manager;




import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.MaterialSelfbuyConstantUtil;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.Base64Util;
import cn.damei.dao.mobile.Inspector.CheckConfirmDao;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.dao.mobile.Manager.ApplyMaterialSelfbuyReimburseDao;
import cn.damei.entity.mobile.Manager.ApplyMaterialSelfbuyReimburseStatusLog;
import cn.damei.entity.mobile.Manager.MaterialManagement;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.BizMaterialSelfbuy;
import cn.damei.dao.modules.BizMaterialSelfbuyReimburseDao;
import cn.damei.entity.modules.BizMaterialSelfbuyReimburse;


/**
 * 自采材料报销Service
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true)
public class ApplyMaterialSelfbuyReimburseService {

	@Autowired
	private ApplyMaterialSelfbuyReimburseDao dao;
	@Autowired
	private BizMaterialSelfbuyReimburseDao bizMaterialSelfbuyReimburseDao;
	@Autowired
	private CheckConfirmDao checkConfirmDao;
	@Autowired
	private BizBusinessStatusLogDao bizBusinessStatusLogDao;
	
	
	/**
	 * 自采材料报销 -- 订单列表
	 * @param managerId
	 * @param text
	 * @return
	 */
	public List<MaterialManagement> findOrderList(Integer managerId, String text) {
		MaterialManagement materialManagement = new MaterialManagement();
		//项目经理
		materialManagement.setItemManagerId(managerId);
		//搜索文本框
		materialManagement.setText(text);
		
		return dao.findOrderList(materialManagement);
	}

	/**
	 * 根据订单id查询订单信息
	 * @param orderId
	 * @return
	 */
	public MaterialManagement findOrder(Integer orderId) {
		return dao.findOrder(orderId);
	}

	/**
	 * 查询该订单5分钟内提交自采材料报销的数量
	 * @param orderId
	 * @param relatedReimburseId
	 * @param materialSelfbuyReimburseType
	 * @return
	 */
	public Integer findMaterialSelfbuyReimburseCount(Integer orderId,String relatedReimburseId,String materialSelfbuyReimburseType) {
		
		BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse = new BizMaterialSelfbuyReimburse();
		
		// 1.报销类型
		bizMaterialSelfbuyReimburse.setReimburseType(materialSelfbuyReimburseType);
		// 2.关联报销id
		if(StringUtils.isNotBlank(relatedReimburseId)){
			bizMaterialSelfbuyReimburse.setRelatedReimburseId(Integer.valueOf(relatedReimburseId));
		}
		// 3.订单id
		bizMaterialSelfbuyReimburse.setOrderId(orderId);
		 
		Integer materialSelfbuyReimburseCount = dao.findMaterialSelfbuyReimburseCount(bizMaterialSelfbuyReimburse);
		
		return materialSelfbuyReimburseCount;
		
	}
	
	
	/**
	 * 查询自采材料名称列表
	 * @param materialManagement
	 * @return
	 */
	public List<BizMaterialSelfbuy> findMaterialSelfbuyList(MaterialManagement materialManagement) {

		BizMaterialSelfbuy bizMaterialSelfbuy = new BizMaterialSelfbuy();
		bizMaterialSelfbuy.setStoreId(materialManagement.getStoreId());
		bizMaterialSelfbuy.setIsEnabled(MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_IS_ENABLED_1);
		bizMaterialSelfbuy.setProjectMode(materialManagement.getProjectMode());
		bizMaterialSelfbuy.setDelFlag(MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_DEL_FLAG_0);
		
		return dao.findMaterialSelfbuyList(bizMaterialSelfbuy);
	}

	/**
	 * 保存自采材料报销
	 * @param orderId
	 * @param materialId
	 * @param materialSelfbuyId
	 * @param customerPayAmount
	 * @param settleRateTwo
	 * @param settleStage
	 * @param settleAmount
	 * @param reimburseRemarks
	 * @param materialSelfbuyReimburseType
	 * @param materialSelfbuyReimburseStatus
	 * @param materialSelfbuyReimburseStatusRemarks 
	 * @return
	 */
	@Transactional(readOnly = false)
	public Integer saveMaterialSelfbuyReimburse(Integer orderId, Integer materialId, Integer materialSelfbuyId, Double customerPayAmount,
			Double settleRateTwo, String settleStage, Double settleAmount, String reimburseRemarks,
			String materialSelfbuyReimburseType, String materialSelfbuyReimburseStatus, String materialSelfbuyReimburseStatusRemarks) {

		BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse = new BizMaterialSelfbuyReimburse();
		
		// 1.报销类型
		bizMaterialSelfbuyReimburse.setReimburseType(materialSelfbuyReimburseType);
		// 2.关联报销id
		bizMaterialSelfbuyReimburse.setRelatedReimburseId(materialId);
		// 3.订单id
		bizMaterialSelfbuyReimburse.setOrderId(orderId);
		// 4.材料自采id
		bizMaterialSelfbuyReimburse.setMaterialSelfbuyId(materialSelfbuyId);
		// 5.客户交费金额
		bizMaterialSelfbuyReimburse.setCustomerPayAmount(customerPayAmount);
		// 6.结算比例
		bizMaterialSelfbuyReimburse.setSettleRateTwo(settleRateTwo);
		// 7.所属结算阶段
		bizMaterialSelfbuyReimburse.setSettleStage(settleStage);
		// 8.实际结算金额
		bizMaterialSelfbuyReimburse.setSettleAmount(settleAmount);
		// 9.报销说明
		bizMaterialSelfbuyReimburse.setReimburseRemarks(reimburseRemarks);
		// 10.报销状态
		bizMaterialSelfbuyReimburse.setReimburseStatus(materialSelfbuyReimburseStatus);
		// 11.报销状态日期时间
		bizMaterialSelfbuyReimburse.setReimburseStatusDatetime(new Date());
		// 12.报销状态说明
		bizMaterialSelfbuyReimburse.setReimburseStatusRemarks(materialSelfbuyReimburseStatusRemarks);
		
		bizMaterialSelfbuyReimburse.preInsert();
		 
		bizMaterialSelfbuyReimburseDao.insert(bizMaterialSelfbuyReimburse);
		
		if(null==materialId){
			// 关联报销id
			bizMaterialSelfbuyReimburse.setRelatedReimburseId(bizMaterialSelfbuyReimburse.getId());
			bizMaterialSelfbuyReimburseDao.update(bizMaterialSelfbuyReimburse);
		}
		return bizMaterialSelfbuyReimburse.getId();
	}

	
	
	
	/**
	 * 保存自采材料报销状态日志
	 * @param materialSelfbuyReimburseId
	 * @param materialSelfbuyReimburseStatusRemarks 
	 * @param materialSelfbuyReimburseStatus 
	 * @param managerId 
	 * @param businessType 
	 * @return
	 */
	@Transactional(readOnly = false)
	public Integer saveBusinessStatusLog(Integer materialSelfbuyReimburseId, Integer managerId, String materialSelfbuyReimburseStatus,
			String reimburseRemarks, String businessType) {
		
		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		//1.唯一标识
		bizBusinessStatusLog.setBusinessOnlyMarkInt(materialSelfbuyReimburseId);
		//2.业务类型
		bizBusinessStatusLog.setBusinessType(businessType);
		//3.业务状态
		bizBusinessStatusLog.setBusinessStatus(materialSelfbuyReimburseStatus);
		//4.业务备注
		bizBusinessStatusLog.setBusinessRemarks(reimburseRemarks);
		//5.状态时间
		bizBusinessStatusLog.setStatusDatetime(new Date());
		//6.业务人员员工id
		bizBusinessStatusLog.setBusinessEmployeeId(managerId);
		bizBusinessStatusLog.preInsert();
		
		bizBusinessStatusLogDao.insert(bizBusinessStatusLog);
		
		return bizBusinessStatusLog.getId();
	}
	
	
	/**
	 * 删除材料自采报销
	 * @param materialSelfbuyReimburseId
	 */
	@Transactional(readOnly=false)
	public void deleteMaterialSelfbuyReimburse(Integer materialSelfbuyReimburseId) {
		bizMaterialSelfbuyReimburseDao.deleteMaterialSelfbuyReimburse(materialSelfbuyReimburseId);
	}
	
	
	/**
	 * 保存问题上报  图片
	 * @param materialSelfbuyReimburseId
	 * @param pictureType
	 * @param photo
	 * @param uploadSiteDesignProblemManagerApply
	 * @param request 
	 */
	@Transactional(readOnly=false)
	public void saveMaterialSelfbuyReimbursePic(Integer materialSelfbuyReimburseId, String pictureType, String[] photo,
			String uploadSiteDesignProblemManagerApply, HttpServletRequest request) {
		
		Date date = new Date();
		List<ReportCheckDetailsPic> pList = new ArrayList<ReportCheckDetailsPic>();
		//保存图片
		if (null!=photo && photo.length>0) {
			
			for(String p : photo){
				
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				
//				String rootPath = RootName.SystemEnvironment(request);
				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + uploadSiteDesignProblemManagerApply + DateUtils.getDate1());
				//判断该文件是否存在
				if(!filePath.exists() && !filePath.isDirectory()){
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);
				
				String picpath = uploadSiteDesignProblemManagerApply + DateUtils.getDate1()+filePath.separator + uuid + ".jpeg";
				
				//保存图片到数据库
				ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
				reportCheckDetailsPic.setBusinessIdInt(materialSelfbuyReimburseId);
				reportCheckDetailsPic.setBusinessType(pictureType);
				reportCheckDetailsPic.setPicUrl(picpath);
				reportCheckDetailsPic.setPicDatetime(date);
				reportCheckDetailsPic.preInsert();
				pList.add(reportCheckDetailsPic);
			}
			//批量插入约检验收图片
			checkConfirmDao.savePicAll(pList);
		}
		
	}

	/**
	 * 动态加载自采材料报销  记录页面
	 * @param orderId
	 * @param materialSelfbuyReimburseType
	 * @return
	 */
	public List<BizMaterialSelfbuyReimburse> findMaterialSelfbuyReimburseRecordList(Integer orderId) {
		
		BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse = new BizMaterialSelfbuyReimburse();
		
		// 1.订单id
		bizMaterialSelfbuyReimburse.setOrderId(orderId);
		
		return dao.findMaterialSelfbuyReimburseRecordList(bizMaterialSelfbuyReimburse);
	}

	/**
	 * 最新一次的自采材料报销申请内容
	 * @param orderId
	 * @param relatedReimburseId
	 * @return
	 */
	public BizMaterialSelfbuyReimburse findLastTimeMaterialSelfbuyDetail(Integer orderId, Integer relatedReimburseId) {

		BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse = new BizMaterialSelfbuyReimburse();
		bizMaterialSelfbuyReimburse.setOrderId(orderId);
		bizMaterialSelfbuyReimburse.setRelatedReimburseId(relatedReimburseId);
		
		return dao.findLastTimeMaterialSelfbuyDetail(bizMaterialSelfbuyReimburse);
	}


	/**
	 * 根据id查询出所有的 图片（之前的）并保存
	 * @param picUrlId
	 * @param materialSelfbuyReimburseId 
	 */
	@Transactional(readOnly=false)
	public void findLastPicListAndSave(String[] picUrlId, Integer materialSelfbuyReimburseId) {
		ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
		reportCheckDetailsPic.setPicList(Arrays.asList(picUrlId));
		//根据id查询出所有的 图片（之前的）
		List<ReportCheckDetailsPic> list = dao.findLastPicList(reportCheckDetailsPic);
		
		if(CollectionUtils.isNotEmpty(list)){
			for(ReportCheckDetailsPic pic:list){
				pic.setId(null);
				pic.setBusinessIdInt(materialSelfbuyReimburseId);
				pic.setPicDatetime(new Date());
				pic.preInsert();
			}
			//批量插入图片
			checkConfirmDao.savePicAll(list);
		}
	}

	/**
	 * 自采材料报销详情
	 * @param orderId
	 * @param relatedReimburseId
	 * @return
	 */
	public List<BizMaterialSelfbuyReimburse> findMaterialSelfbuyReimburseDetails(Integer orderId, Integer relatedReimburseId) {
		BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse = new BizMaterialSelfbuyReimburse();
		//订单id
		bizMaterialSelfbuyReimburse.setOrderId(orderId);
		//关联id
		bizMaterialSelfbuyReimburse.setRelatedReimburseId(relatedReimburseId);
		return dao.findMaterialSelfbuyReimburseDetails(bizMaterialSelfbuyReimburse);
	}

	/**
	 * 自采材料报销详情--状态日志
	 * @param orderId
	 * @param relatedReimburseId
	 * @param businessType
	 * @return
	 */
	public List<ApplyMaterialSelfbuyReimburseStatusLog> findMaterialStatusLogDetails(Integer orderId, Integer relatedReimburseId,
			String businessType) {
		
		ApplyMaterialSelfbuyReimburseStatusLog applyMaterialSelfbuyReimburseStatusLog = new ApplyMaterialSelfbuyReimburseStatusLog();
		//订单id
		applyMaterialSelfbuyReimburseStatusLog.setOrderId(orderId);
		//关联id
		applyMaterialSelfbuyReimburseStatusLog.setRelatedReimburseId(relatedReimburseId);
		//关联状态类型
		applyMaterialSelfbuyReimburseStatusLog.setBusinessType(businessType);
		return dao.findMaterialStatusLogDetails(applyMaterialSelfbuyReimburseStatusLog);
	}


	

	
	


}
