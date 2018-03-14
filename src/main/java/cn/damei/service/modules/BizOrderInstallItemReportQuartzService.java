/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessProblemConstantUtil;
import cn.damei.common.constantUtils.CheckSizeConstantUtil;
import cn.damei.common.constantUtils.InstallPlanConstantUtil;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.BizOrderInstallItemReportDao;
import cn.damei.entity.modules.BizOrderCheckSize;
import cn.damei.entity.modules.BizOrderInstallItemReport;
import cn.damei.entity.modules.BizOrderInstasllPlanAndProblem;

/**
 * 工程部主材工期统计表
 */
@Service
@Transactional(readOnly = true)
public class BizOrderInstallItemReportQuartzService{


	@Autowired
	private BizOrderInstallItemReportDao bizOrderInstallItemReportDao;
	
	/**
	 * 查询订单信息
	 * @return
	 */
	public List<BizOrderInstallItemReport> findOrderMessage() {
		
		return bizOrderInstallItemReportDao.findOrderMessage();
	}

	/**
	 * 批量更新  --- 工程部主材工期统计表
	 * @param mixUpdateList
	 * @return 
	 */
	@Transactional(readOnly = false)
	public boolean batchUpdateList(List<BizOrderInstallItemReport> mixUpdateList) {
		return (bizOrderInstallItemReportDao.batchUpdateList(mixUpdateList))?true:false;
	}

	/**
	 * 批量插入  --- 工程部主材工期统计表
	 * @param mixInsertListList
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean batchInsertList(List<BizOrderInstallItemReport> mixInsertListList) {
		return (bizOrderInstallItemReportDao.batchInsertList(mixInsertListList))?true:false;
	}
	
	/**
	 * 工程部主材工期统计表
	 * @param report
	 * @return
	 */
	public BizOrderInstallItemReport findOrderInstallReportMessage(BizOrderInstallItemReport report) {
		
		//1.根据订单查询    厂家复尺信息
		List<BizOrderCheckSize> checkSizeList = bizOrderInstallItemReportDao.findCheckSizeMessage(report.getOrderId());
		
		//2.根据订单查询   主材安装以及问题上报信息
		List<BizOrderInstasllPlanAndProblem> planAndProblemList = bizOrderInstallItemReportDao.findInstallPlanAndProblemMessage(report.getOrderId());
		
		//3.申报复尺时间
		if(CollectionUtils.isNotEmpty(checkSizeList)){
			for(BizOrderCheckSize checkSize:checkSizeList){
				
				if(StringUtils.isNotBlank(checkSize.getChecksizeType())){
					if(checkSize.getChecksizeType().equals(CheckSizeConstantUtil.CHECK_SCALE_4_VALUE)){
						//<!-- 1.橱柜 -->
						if(null!=checkSize.getChecksizeDate()){
							report.setChecksizeApplyDatetimeChugui(checkSize.getChecksizeDate());
						}
					}else if(checkSize.getChecksizeType().equals(CheckSizeConstantUtil.CHECK_SCALE_3_VALUE)){
						//<!-- 2.房门  -->
						if(null!=checkSize.getChecksizeDate()){
							report.setChecksizeApplyDatetimeFangmen(checkSize.getChecksizeDate());
						}
					}else if(checkSize.getChecksizeType().equals(CheckSizeConstantUtil.CHECK_SCALE_6_VALUE)){
						//<!-- 8.窗帘 -->
						if(null!=checkSize.getChecksizeDate()){
							report.setChecksizeApplyDatetimeChuanglian(checkSize.getChecksizeDate());
						}
					}else if(checkSize.getChecksizeType().equals(CheckSizeConstantUtil.CHECK_SCALE_10_VALUE)){
						//<!-- 9.淋浴房 -->
						if(null!=checkSize.getChecksizeDate()){
							report.setChecksizeApplyDatetimeLinyufang(checkSize.getChecksizeDate());
						}
					}else if(checkSize.getChecksizeType().equals(CheckSizeConstantUtil.CHECK_SCALE_5_VALUE)){
						//<!-- 10.定制衣柜 -->
						if(null!=checkSize.getChecksizeDate()){
							report.setChecksizeApplyDatetimeYigui(checkSize.getChecksizeDate());
						}
					}
				}
				
			}
		}
			
		
		//4.主材安装及问题上报
		if(CollectionUtils.isNotEmpty(planAndProblemList)){
			for(BizOrderInstasllPlanAndProblem planAndProblem:planAndProblemList){
				
				if(StringUtils.isNotBlank(planAndProblem.getInstallItemSequence())){
					
					if(planAndProblem.getInstallItemSequence().equals(InstallPlanConstantUtil.INSTALL_ITEM_SEQUENCE_4)){
						
						
						
						//<!-- 1.橱柜 -->
						//<!-- 10.定制衣柜 -->
						//1.安装提报日期时间
						if(null!=planAndProblem.getApplyIntoCreateDatetime()){
							report.setInstallItemApplyDatetimeChugui(planAndProblem.getApplyIntoCreateDatetime());
							report.setInstallItemApplyDatetimeYigui(planAndProblem.getApplyIntoCreateDatetime());
						}
						//2.期望进场日期
						if(null!=planAndProblem.getApplyIntoDate()){
							report.setInstallItemApplyIntoDateChugui(planAndProblem.getApplyIntoDate());
							report.setInstallItemApplyIntoDateYigui(planAndProblem.getApplyIntoDate());
						}
						//3.实际进场日期
						if(null!=planAndProblem.getRealIntoDate()){
							report.setInstallItemRealIntoDateChugui(planAndProblem.getRealIntoDate());
							report.setInstallItemRealIntoDateYigui(planAndProblem.getRealIntoDate());
						}
						//4.实际完工日期
						if(null!=planAndProblem.getRealCompleteDate()){
							report.setInstallItemRealAcceptDateChugui(planAndProblem.getRealCompleteDate());
							report.setInstallItemRealAcceptDateYigui(planAndProblem.getRealCompleteDate());
						}
						//5.是否返工  0：否  1：是
						if(null!=planAndProblem.getProblemCount()){
							if(planAndProblem.getProblemCount() > 0){
								report.setInstallItemIsReturnedChugui(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_1);
								report.setInstallItemIsReturnedYigui(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_1);
							}else{
								report.setInstallItemIsReturnedChugui(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_0);
								report.setInstallItemIsReturnedYigui(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_0);
							}
						}
						//6.安装问题描述
						if(StringUtils.isNotBlank(planAndProblem.getProblemDescribe())){
							report.setInstallItemProblemDescribeChugui(planAndProblem.getProblemDescribe());
							report.setInstallItemProblemDescribeYigui(planAndProblem.getProblemDescribe());
						}
						//7.安装问题提交日期时间
						if(null!=planAndProblem.getProblemDateTime()){
							report.setInstallItemProblemApplyDatetimeChugui(planAndProblem.getProblemDateTime());
							report.setInstallItemProblemApplyDatetimeYigui(planAndProblem.getProblemDateTime());
						}
						
						
						
						
					}else if(planAndProblem.getInstallItemSequence().equals(InstallPlanConstantUtil.INSTALL_ITEM_SEQUENCE_6)){
						
						
						
						
						//<!-- 2.房门  -->
						//1.安装提报日期时间
						if(null!=planAndProblem.getApplyIntoCreateDatetime()){
							report.setInstallItemApplyDatetimeFangmen(planAndProblem.getApplyIntoCreateDatetime());
						}
						//2.期望进场日期
						if(null!=planAndProblem.getApplyIntoDate()){
							report.setInstallItemApplyIntoDateFangmen(planAndProblem.getApplyIntoDate());
						}
						//3.实际进场日期
						if(null!=planAndProblem.getRealIntoDate()){
							report.setInstallItemRealIntoDateFangmen(planAndProblem.getRealIntoDate());
						}
						//4.实际完工日期
						if(null!=planAndProblem.getRealCompleteDate()){
							report.setInstallItemRealAcceptDateFangmen(planAndProblem.getRealCompleteDate());
						}
						//5.是否返工  0：否  1：是
						if(null!=planAndProblem.getProblemCount()){
							if(planAndProblem.getProblemCount() > 0){
								report.setInstallItemIsReturnedFangmen(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_1);
							}else{
								report.setInstallItemIsReturnedFangmen(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_0);
							}
						}
						//6.安装问题描述
						if(StringUtils.isNotBlank(planAndProblem.getProblemDescribe())){
							report.setInstallItemProblemDescribeFangmen(planAndProblem.getProblemDescribe());
						}
						//7.安装问题提交日期时间
						if(null!=planAndProblem.getProblemDateTime()){
							report.setInstallItemProblemApplyDatetimeFangmen(planAndProblem.getProblemDateTime());
						}
						
						
						
						
					}else if(planAndProblem.getInstallItemSequence().equals(InstallPlanConstantUtil.INSTALL_ITEM_SEQUENCE_8)){
						
						
						
						//<!-- 3.洁具 -->
						//1.安装提报日期时间
						if(null!=planAndProblem.getApplyIntoCreateDatetime()){
							report.setInstallItemApplyDatetimeJieju(planAndProblem.getApplyIntoCreateDatetime());
						}
						//2.期望进场日期
						if(null!=planAndProblem.getApplyIntoDate()){
							report.setInstallItemApplyIntoDateJieju(planAndProblem.getApplyIntoDate());
						}
						//3.实际进场日期
						if(null!=planAndProblem.getRealIntoDate()){
							report.setInstallItemRealIntoDateJieju(planAndProblem.getRealIntoDate());
						}
						//4.实际完工日期
						if(null!=planAndProblem.getRealCompleteDate()){
							report.setInstallItemRealAcceptDateJieju(planAndProblem.getRealCompleteDate());
						}
						//5.是否返工  0：否  1：是
						if(null!=planAndProblem.getProblemCount()){
							if(planAndProblem.getProblemCount() > 0){
								report.setInstallItemIsReturnedJieju(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_1);
							}else{
								report.setInstallItemIsReturnedJieju(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_0);
							}
						}
						//6.安装问题描述
						if(StringUtils.isNotBlank(planAndProblem.getProblemDescribe())){
							report.setInstallItemProblemDescribeJieju(planAndProblem.getProblemDescribe());
						}
						//7.安装问题提交日期时间
						if(null!=planAndProblem.getProblemDateTime()){
							report.setInstallItemProblemApplyDatetimeJieju(planAndProblem.getProblemDateTime());
						}
						
						
						
						
					}else if(planAndProblem.getInstallItemSequence().equals(InstallPlanConstantUtil.INSTALL_ITEM_SEQUENCE_5)){
						
						
						
						
						//<!-- 4.壁纸 -->
						//1.安装提报日期时间
						if(null!=planAndProblem.getApplyIntoCreateDatetime()){
							report.setInstallItemApplyDatetimeBizhi(planAndProblem.getApplyIntoCreateDatetime());
						}
						//2.期望进场日期
						if(null!=planAndProblem.getApplyIntoDate()){
							report.setInstallItemApplyIntoDateBizhi(planAndProblem.getApplyIntoDate());
						}
						//3.实际进场日期
						if(null!=planAndProblem.getRealIntoDate()){
							report.setInstallItemRealIntoDateBizhi(planAndProblem.getRealIntoDate());
						}
						//4.实际完工日期
						if(null!=planAndProblem.getRealCompleteDate()){
							report.setInstallItemRealCompleteDateBizhi(planAndProblem.getRealCompleteDate());
						}
						//5.是否返工  0：否  1：是
						if(null!=planAndProblem.getProblemCount()){
							if(planAndProblem.getProblemCount() > 0){
								report.setInstallItemIsReturnedBizhi(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_1);
							}else{
								report.setInstallItemIsReturnedBizhi(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_0);
							}
						}
						//6.安装问题描述
						if(StringUtils.isNotBlank(planAndProblem.getProblemDescribe())){
							report.setInstallItemProblemDescribeBizhi(planAndProblem.getProblemDescribe());
						}
						//7.安装问题提交日期时间
						if(null!=planAndProblem.getProblemDateTime()){
							report.setInstallItemProblemApplyDatetimeBizhi(planAndProblem.getProblemDateTime());
						}
						
						
						
						
					}else if(planAndProblem.getInstallItemSequence().equals(InstallPlanConstantUtil.INSTALL_ITEM_SEQUENCE_9)){
						
						
						
						
						//<!-- 5.木地板 -->
						//1.安装提报日期时间
						if(null!=planAndProblem.getApplyIntoCreateDatetime()){
							report.setInstallItemApplyDatetimeMudiban(planAndProblem.getApplyIntoCreateDatetime());
						}
						//2.期望进场日期
						if(null!=planAndProblem.getApplyIntoDate()){
							report.setInstallItemApplyIntoDateMudiban(planAndProblem.getApplyIntoDate());
						}
						//3.实际进场日期
						if(null!=planAndProblem.getRealIntoDate()){
							report.setInstallItemRealIntoDateMudiban(planAndProblem.getRealIntoDate());
						}
						//4.实际完工日期
						if(null!=planAndProblem.getRealCompleteDate()){
							report.setInstallItemRealAcceptDateMudiban(planAndProblem.getRealCompleteDate());
						}
						//5.是否返工  0：否  1：是
						if(null!=planAndProblem.getProblemCount()){
							if(planAndProblem.getProblemCount() > 0){
								report.setInstallItemIsReturnedMudiban(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_1);
							}else{
								report.setInstallItemIsReturnedMudiban(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_0);
							}
						}
						//6.安装问题描述
						if(StringUtils.isNotBlank(planAndProblem.getProblemDescribe())){
							report.setInstallItemProblemDescribeMudiban(planAndProblem.getProblemDescribe());
						}
						//7.安装问题提交日期时间
						if(null!=planAndProblem.getProblemDateTime()){
							report.setInstallItemProblemApplyDatetimeMudiban(planAndProblem.getProblemDateTime());
						}
						
						
						
						
					}else if(planAndProblem.getInstallItemSequence().equals(InstallPlanConstantUtil.INSTALL_ITEM_SEQUENCE_2)){
						
						
						
						
						//<!-- 6.吊顶-->
						//1.安装提报日期时间
						if(null!=planAndProblem.getApplyIntoCreateDatetime()){
							report.setInstallItemApplyDatetimeDiaoding(planAndProblem.getApplyIntoCreateDatetime());
						}
						//2.期望进场日期
						if(null!=planAndProblem.getApplyIntoDate()){
							report.setInstallItemApplyIntoDateDiaoding(planAndProblem.getApplyIntoDate());
						}
						//3.实际进场日期
						if(null!=planAndProblem.getRealIntoDate()){
							report.setInstallItemRealIntoDateDiaoding(planAndProblem.getRealIntoDate());
						}
						//4.实际完工日期
						if(null!=planAndProblem.getRealCompleteDate()){
							report.setInstallItemRealAcceptDateDiaoding(planAndProblem.getRealCompleteDate());
						}
						//5.是否返工  0：否  1：是
						if(null!=planAndProblem.getProblemCount()){
							if(planAndProblem.getProblemCount() > 0){
								report.setInstallItemIsReturnedDiaoding(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_1);
							}else{
								report.setInstallItemIsReturnedDiaoding(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_0);
							}
						}
						//6.安装问题描述
						if(StringUtils.isNotBlank(planAndProblem.getProblemDescribe())){
							report.setInstallItemProblemDescribeDiaoding(planAndProblem.getProblemDescribe());
						}
						//7.安装问题提交日期时间
						if(null!=planAndProblem.getProblemDateTime()){
							report.setInstallItemProblemApplyDatetimeDiaoding(planAndProblem.getProblemDateTime());
						}
						
						
						
						
					}else if(planAndProblem.getInstallItemSequence().equals(InstallPlanConstantUtil.INSTALL_ITEM_SEQUENCE_7)){
						
						
						
						
						//<!-- 7.灯具、开关、小五金 -->
						//1.安装提报日期时间
						if(null!=planAndProblem.getApplyIntoCreateDatetime()){
							report.setInstallItemApplyDatetimeDengju(planAndProblem.getApplyIntoCreateDatetime());
						}
						//2.期望进场日期
						if(null!=planAndProblem.getApplyIntoDate()){
							report.setInstallItemApplyIntoDateDengju(planAndProblem.getApplyIntoDate());
						}
						//3.实际进场日期
						if(null!=planAndProblem.getRealIntoDate()){
							report.setInstallItemRealIntoDateDengju(planAndProblem.getRealIntoDate());
						}
						//4.实际完工日期
						if(null!=planAndProblem.getRealCompleteDate()){
							report.setInstallItemRealAcceptDateDengju(planAndProblem.getRealCompleteDate());
						}
						//5.是否返工  0：否  1：是
						if(null!=planAndProblem.getProblemCount()){
							if(planAndProblem.getProblemCount() > 0){
								report.setInstallItemIsReturnedDengju(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_1);
							}else{
								report.setInstallItemIsReturnedDengju(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_0);
							}
						}
						//6.安装问题描述
						if(StringUtils.isNotBlank(planAndProblem.getProblemDescribe())){
							report.setInstallItemProblemDescribeDengju(planAndProblem.getProblemDescribe());
						}
						//7.安装问题提交日期时间
						if(null!=planAndProblem.getProblemDateTime()){
							report.setInstallItemProblemApplyDatetimeDengju(planAndProblem.getProblemDateTime());
						}
						
						
						
						
					}else if(planAndProblem.getInstallItemSequence().equals(InstallPlanConstantUtil.INSTALL_ITEM_SEQUENCE_10)){
						
						
						
						
						//<!-- 8.窗帘 -->
						//1.安装提报日期时间
						if(null!=planAndProblem.getApplyIntoCreateDatetime()){
							report.setInstallItemApplyDatetimeChuanglian(planAndProblem.getApplyIntoCreateDatetime());
						}
						//2.期望进场日期
						if(null!=planAndProblem.getApplyIntoDate()){
							report.setInstallItemApplyIntoDateChuanglian(planAndProblem.getApplyIntoDate());
						}
						//3.实际进场日期
						if(null!=planAndProblem.getRealIntoDate()){
							report.setInstallItemRealIntoDateChuanglian(planAndProblem.getRealIntoDate());
						}
						//4.实际完工日期
						if(null!=planAndProblem.getRealCompleteDate()){
							report.setInstallItemRealAcceptDateChuanglian(planAndProblem.getRealCompleteDate());
						}
						//5.是否返工  0：否  1：是
						if(null!=planAndProblem.getProblemCount()){
							if(planAndProblem.getProblemCount() > 0){
								report.setInstallItemIsReturnedChuanglian(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_1);
							}else{
								report.setInstallItemIsReturnedChuanglian(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_0);
							}
						}
						//6.安装问题描述
						if(StringUtils.isNotBlank(planAndProblem.getProblemDescribe())){
							report.setInstallItemProblemDescribeChuanglian(planAndProblem.getProblemDescribe());
						}
						//7.安装问题提交日期时间
						if(null!=planAndProblem.getProblemDateTime()){
							report.setInstallItemProblemApplyDatetimeChuanglian(planAndProblem.getProblemDateTime());
						}
						
					
					}else if(planAndProblem.getInstallItemSequence().equals(InstallPlanConstantUtil.INSTALL_ITEM_SEQUENCE_3)){
					
					
					
					
						//<!-- 9.淋浴房 -->
						//1.安装提报日期时间
						if(null!=planAndProblem.getApplyIntoCreateDatetime()){
							report.setInstallItemApplyDatetimeLinyufang(planAndProblem.getApplyIntoCreateDatetime());
						}
						//2.期望进场日期
						if(null!=planAndProblem.getApplyIntoDate()){
							report.setInstallItemApplyIntoDateLinyufang(planAndProblem.getApplyIntoDate());
						}
						//3.实际进场日期
						if(null!=planAndProblem.getRealIntoDate()){
							report.setInstallItemRealIntoDateLinyufang(planAndProblem.getRealIntoDate());
						}
						//4.实际完工日期
						if(null!=planAndProblem.getRealCompleteDate()){
							report.setInstallItemRealAcceptDateLinyufang(planAndProblem.getRealCompleteDate());
						}
						//5.是否返工  0：否  1：是
						if(null!=planAndProblem.getProblemCount()){
							if(planAndProblem.getProblemCount() > 0){
								report.setInstallItemIsReturnedLinyufang(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_1);
							}else{
								report.setInstallItemIsReturnedLinyufang(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_0);
							}
						}
						//6.安装问题描述
						if(StringUtils.isNotBlank(planAndProblem.getProblemDescribe())){
							report.setInstallItemProblemDescribeLinyufang(planAndProblem.getProblemDescribe());
						}
						//7.安装问题提交日期时间
						if(null!=planAndProblem.getProblemDateTime()){
							report.setInstallItemProblemApplyDatetimeLinyufang(planAndProblem.getProblemDateTime());
						}
					
					}
				
				}
				
			}
		
		}
		
		
		
		return report;
		
	}

	

	
}