
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


@Service
@Transactional(readOnly = true)
public class BizOrderInstallItemReportQuartzService{


	@Autowired
	private BizOrderInstallItemReportDao bizOrderInstallItemReportDao;
	

	public List<BizOrderInstallItemReport> findOrderMessage() {
		
		return bizOrderInstallItemReportDao.findOrderMessage();
	}


	@Transactional(readOnly = false)
	public boolean batchUpdateList(List<BizOrderInstallItemReport> mixUpdateList) {
		return (bizOrderInstallItemReportDao.batchUpdateList(mixUpdateList))?true:false;
	}


	@Transactional(readOnly = false)
	public boolean batchInsertList(List<BizOrderInstallItemReport> mixInsertListList) {
		return (bizOrderInstallItemReportDao.batchInsertList(mixInsertListList))?true:false;
	}
	

	public BizOrderInstallItemReport findOrderInstallReportMessage(BizOrderInstallItemReport report) {
		

		List<BizOrderCheckSize> checkSizeList = bizOrderInstallItemReportDao.findCheckSizeMessage(report.getOrderId());
		

		List<BizOrderInstasllPlanAndProblem> planAndProblemList = bizOrderInstallItemReportDao.findInstallPlanAndProblemMessage(report.getOrderId());
		

		if(CollectionUtils.isNotEmpty(checkSizeList)){
			for(BizOrderCheckSize checkSize:checkSizeList){
				
				if(StringUtils.isNotBlank(checkSize.getChecksizeType())){
					if(checkSize.getChecksizeType().equals(CheckSizeConstantUtil.CHECK_SCALE_4_VALUE)){

						if(null!=checkSize.getChecksizeDate()){
							report.setChecksizeApplyDatetimeChugui(checkSize.getChecksizeDate());
						}
					}else if(checkSize.getChecksizeType().equals(CheckSizeConstantUtil.CHECK_SCALE_3_VALUE)){

						if(null!=checkSize.getChecksizeDate()){
							report.setChecksizeApplyDatetimeFangmen(checkSize.getChecksizeDate());
						}
					}else if(checkSize.getChecksizeType().equals(CheckSizeConstantUtil.CHECK_SCALE_6_VALUE)){

						if(null!=checkSize.getChecksizeDate()){
							report.setChecksizeApplyDatetimeChuanglian(checkSize.getChecksizeDate());
						}
					}else if(checkSize.getChecksizeType().equals(CheckSizeConstantUtil.CHECK_SCALE_10_VALUE)){

						if(null!=checkSize.getChecksizeDate()){
							report.setChecksizeApplyDatetimeLinyufang(checkSize.getChecksizeDate());
						}
					}else if(checkSize.getChecksizeType().equals(CheckSizeConstantUtil.CHECK_SCALE_5_VALUE)){

						if(null!=checkSize.getChecksizeDate()){
							report.setChecksizeApplyDatetimeYigui(checkSize.getChecksizeDate());
						}
					}
				}
				
			}
		}
			
		

		if(CollectionUtils.isNotEmpty(planAndProblemList)){
			for(BizOrderInstasllPlanAndProblem planAndProblem:planAndProblemList){
				
				if(StringUtils.isNotBlank(planAndProblem.getInstallItemSequence())){
					
					if(planAndProblem.getInstallItemSequence().equals(InstallPlanConstantUtil.INSTALL_ITEM_SEQUENCE_4)){
						
						
						



						if(null!=planAndProblem.getApplyIntoCreateDatetime()){
							report.setInstallItemApplyDatetimeChugui(planAndProblem.getApplyIntoCreateDatetime());
							report.setInstallItemApplyDatetimeYigui(planAndProblem.getApplyIntoCreateDatetime());
						}

						if(null!=planAndProblem.getApplyIntoDate()){
							report.setInstallItemApplyIntoDateChugui(planAndProblem.getApplyIntoDate());
							report.setInstallItemApplyIntoDateYigui(planAndProblem.getApplyIntoDate());
						}

						if(null!=planAndProblem.getRealIntoDate()){
							report.setInstallItemRealIntoDateChugui(planAndProblem.getRealIntoDate());
							report.setInstallItemRealIntoDateYigui(planAndProblem.getRealIntoDate());
						}

						if(null!=planAndProblem.getRealCompleteDate()){
							report.setInstallItemRealAcceptDateChugui(planAndProblem.getRealCompleteDate());
							report.setInstallItemRealAcceptDateYigui(planAndProblem.getRealCompleteDate());
						}

						if(null!=planAndProblem.getProblemCount()){
							if(planAndProblem.getProblemCount() > 0){
								report.setInstallItemIsReturnedChugui(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_1);
								report.setInstallItemIsReturnedYigui(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_1);
							}else{
								report.setInstallItemIsReturnedChugui(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_0);
								report.setInstallItemIsReturnedYigui(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_0);
							}
						}

						if(StringUtils.isNotBlank(planAndProblem.getProblemDescribe())){
							report.setInstallItemProblemDescribeChugui(planAndProblem.getProblemDescribe());
							report.setInstallItemProblemDescribeYigui(planAndProblem.getProblemDescribe());
						}

						if(null!=planAndProblem.getProblemDateTime()){
							report.setInstallItemProblemApplyDatetimeChugui(planAndProblem.getProblemDateTime());
							report.setInstallItemProblemApplyDatetimeYigui(planAndProblem.getProblemDateTime());
						}
						
						
						
						
					}else if(planAndProblem.getInstallItemSequence().equals(InstallPlanConstantUtil.INSTALL_ITEM_SEQUENCE_6)){
						
						
						
						


						if(null!=planAndProblem.getApplyIntoCreateDatetime()){
							report.setInstallItemApplyDatetimeFangmen(planAndProblem.getApplyIntoCreateDatetime());
						}

						if(null!=planAndProblem.getApplyIntoDate()){
							report.setInstallItemApplyIntoDateFangmen(planAndProblem.getApplyIntoDate());
						}

						if(null!=planAndProblem.getRealIntoDate()){
							report.setInstallItemRealIntoDateFangmen(planAndProblem.getRealIntoDate());
						}

						if(null!=planAndProblem.getRealCompleteDate()){
							report.setInstallItemRealAcceptDateFangmen(planAndProblem.getRealCompleteDate());
						}

						if(null!=planAndProblem.getProblemCount()){
							if(planAndProblem.getProblemCount() > 0){
								report.setInstallItemIsReturnedFangmen(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_1);
							}else{
								report.setInstallItemIsReturnedFangmen(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_0);
							}
						}

						if(StringUtils.isNotBlank(planAndProblem.getProblemDescribe())){
							report.setInstallItemProblemDescribeFangmen(planAndProblem.getProblemDescribe());
						}

						if(null!=planAndProblem.getProblemDateTime()){
							report.setInstallItemProblemApplyDatetimeFangmen(planAndProblem.getProblemDateTime());
						}
						
						
						
						
					}else if(planAndProblem.getInstallItemSequence().equals(InstallPlanConstantUtil.INSTALL_ITEM_SEQUENCE_8)){
						
						
						


						if(null!=planAndProblem.getApplyIntoCreateDatetime()){
							report.setInstallItemApplyDatetimeJieju(planAndProblem.getApplyIntoCreateDatetime());
						}

						if(null!=planAndProblem.getApplyIntoDate()){
							report.setInstallItemApplyIntoDateJieju(planAndProblem.getApplyIntoDate());
						}

						if(null!=planAndProblem.getRealIntoDate()){
							report.setInstallItemRealIntoDateJieju(planAndProblem.getRealIntoDate());
						}

						if(null!=planAndProblem.getRealCompleteDate()){
							report.setInstallItemRealAcceptDateJieju(planAndProblem.getRealCompleteDate());
						}

						if(null!=planAndProblem.getProblemCount()){
							if(planAndProblem.getProblemCount() > 0){
								report.setInstallItemIsReturnedJieju(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_1);
							}else{
								report.setInstallItemIsReturnedJieju(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_0);
							}
						}

						if(StringUtils.isNotBlank(planAndProblem.getProblemDescribe())){
							report.setInstallItemProblemDescribeJieju(planAndProblem.getProblemDescribe());
						}

						if(null!=planAndProblem.getProblemDateTime()){
							report.setInstallItemProblemApplyDatetimeJieju(planAndProblem.getProblemDateTime());
						}
						
						
						
						
					}else if(planAndProblem.getInstallItemSequence().equals(InstallPlanConstantUtil.INSTALL_ITEM_SEQUENCE_5)){
						
						
						
						


						if(null!=planAndProblem.getApplyIntoCreateDatetime()){
							report.setInstallItemApplyDatetimeBizhi(planAndProblem.getApplyIntoCreateDatetime());
						}

						if(null!=planAndProblem.getApplyIntoDate()){
							report.setInstallItemApplyIntoDateBizhi(planAndProblem.getApplyIntoDate());
						}

						if(null!=planAndProblem.getRealIntoDate()){
							report.setInstallItemRealIntoDateBizhi(planAndProblem.getRealIntoDate());
						}

						if(null!=planAndProblem.getRealCompleteDate()){
							report.setInstallItemRealCompleteDateBizhi(planAndProblem.getRealCompleteDate());
						}

						if(null!=planAndProblem.getProblemCount()){
							if(planAndProblem.getProblemCount() > 0){
								report.setInstallItemIsReturnedBizhi(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_1);
							}else{
								report.setInstallItemIsReturnedBizhi(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_0);
							}
						}

						if(StringUtils.isNotBlank(planAndProblem.getProblemDescribe())){
							report.setInstallItemProblemDescribeBizhi(planAndProblem.getProblemDescribe());
						}

						if(null!=planAndProblem.getProblemDateTime()){
							report.setInstallItemProblemApplyDatetimeBizhi(planAndProblem.getProblemDateTime());
						}
						
						
						
						
					}else if(planAndProblem.getInstallItemSequence().equals(InstallPlanConstantUtil.INSTALL_ITEM_SEQUENCE_9)){
						
						
						
						


						if(null!=planAndProblem.getApplyIntoCreateDatetime()){
							report.setInstallItemApplyDatetimeMudiban(planAndProblem.getApplyIntoCreateDatetime());
						}

						if(null!=planAndProblem.getApplyIntoDate()){
							report.setInstallItemApplyIntoDateMudiban(planAndProblem.getApplyIntoDate());
						}

						if(null!=planAndProblem.getRealIntoDate()){
							report.setInstallItemRealIntoDateMudiban(planAndProblem.getRealIntoDate());
						}

						if(null!=planAndProblem.getRealCompleteDate()){
							report.setInstallItemRealAcceptDateMudiban(planAndProblem.getRealCompleteDate());
						}

						if(null!=planAndProblem.getProblemCount()){
							if(planAndProblem.getProblemCount() > 0){
								report.setInstallItemIsReturnedMudiban(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_1);
							}else{
								report.setInstallItemIsReturnedMudiban(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_0);
							}
						}

						if(StringUtils.isNotBlank(planAndProblem.getProblemDescribe())){
							report.setInstallItemProblemDescribeMudiban(planAndProblem.getProblemDescribe());
						}

						if(null!=planAndProblem.getProblemDateTime()){
							report.setInstallItemProblemApplyDatetimeMudiban(planAndProblem.getProblemDateTime());
						}
						
						
						
						
					}else if(planAndProblem.getInstallItemSequence().equals(InstallPlanConstantUtil.INSTALL_ITEM_SEQUENCE_2)){
						
						
						
						


						if(null!=planAndProblem.getApplyIntoCreateDatetime()){
							report.setInstallItemApplyDatetimeDiaoding(planAndProblem.getApplyIntoCreateDatetime());
						}

						if(null!=planAndProblem.getApplyIntoDate()){
							report.setInstallItemApplyIntoDateDiaoding(planAndProblem.getApplyIntoDate());
						}

						if(null!=planAndProblem.getRealIntoDate()){
							report.setInstallItemRealIntoDateDiaoding(planAndProblem.getRealIntoDate());
						}

						if(null!=planAndProblem.getRealCompleteDate()){
							report.setInstallItemRealAcceptDateDiaoding(planAndProblem.getRealCompleteDate());
						}

						if(null!=planAndProblem.getProblemCount()){
							if(planAndProblem.getProblemCount() > 0){
								report.setInstallItemIsReturnedDiaoding(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_1);
							}else{
								report.setInstallItemIsReturnedDiaoding(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_0);
							}
						}

						if(StringUtils.isNotBlank(planAndProblem.getProblemDescribe())){
							report.setInstallItemProblemDescribeDiaoding(planAndProblem.getProblemDescribe());
						}

						if(null!=planAndProblem.getProblemDateTime()){
							report.setInstallItemProblemApplyDatetimeDiaoding(planAndProblem.getProblemDateTime());
						}
						
						
						
						
					}else if(planAndProblem.getInstallItemSequence().equals(InstallPlanConstantUtil.INSTALL_ITEM_SEQUENCE_7)){
						
						
						
						


						if(null!=planAndProblem.getApplyIntoCreateDatetime()){
							report.setInstallItemApplyDatetimeDengju(planAndProblem.getApplyIntoCreateDatetime());
						}

						if(null!=planAndProblem.getApplyIntoDate()){
							report.setInstallItemApplyIntoDateDengju(planAndProblem.getApplyIntoDate());
						}

						if(null!=planAndProblem.getRealIntoDate()){
							report.setInstallItemRealIntoDateDengju(planAndProblem.getRealIntoDate());
						}

						if(null!=planAndProblem.getRealCompleteDate()){
							report.setInstallItemRealAcceptDateDengju(planAndProblem.getRealCompleteDate());
						}

						if(null!=planAndProblem.getProblemCount()){
							if(planAndProblem.getProblemCount() > 0){
								report.setInstallItemIsReturnedDengju(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_1);
							}else{
								report.setInstallItemIsReturnedDengju(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_0);
							}
						}

						if(StringUtils.isNotBlank(planAndProblem.getProblemDescribe())){
							report.setInstallItemProblemDescribeDengju(planAndProblem.getProblemDescribe());
						}

						if(null!=planAndProblem.getProblemDateTime()){
							report.setInstallItemProblemApplyDatetimeDengju(planAndProblem.getProblemDateTime());
						}
						
						
						
						
					}else if(planAndProblem.getInstallItemSequence().equals(InstallPlanConstantUtil.INSTALL_ITEM_SEQUENCE_10)){
						
						
						
						


						if(null!=planAndProblem.getApplyIntoCreateDatetime()){
							report.setInstallItemApplyDatetimeChuanglian(planAndProblem.getApplyIntoCreateDatetime());
						}

						if(null!=planAndProblem.getApplyIntoDate()){
							report.setInstallItemApplyIntoDateChuanglian(planAndProblem.getApplyIntoDate());
						}

						if(null!=planAndProblem.getRealIntoDate()){
							report.setInstallItemRealIntoDateChuanglian(planAndProblem.getRealIntoDate());
						}

						if(null!=planAndProblem.getRealCompleteDate()){
							report.setInstallItemRealAcceptDateChuanglian(planAndProblem.getRealCompleteDate());
						}

						if(null!=planAndProblem.getProblemCount()){
							if(planAndProblem.getProblemCount() > 0){
								report.setInstallItemIsReturnedChuanglian(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_1);
							}else{
								report.setInstallItemIsReturnedChuanglian(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_0);
							}
						}

						if(StringUtils.isNotBlank(planAndProblem.getProblemDescribe())){
							report.setInstallItemProblemDescribeChuanglian(planAndProblem.getProblemDescribe());
						}

						if(null!=planAndProblem.getProblemDateTime()){
							report.setInstallItemProblemApplyDatetimeChuanglian(planAndProblem.getProblemDateTime());
						}
						
					
					}else if(planAndProblem.getInstallItemSequence().equals(InstallPlanConstantUtil.INSTALL_ITEM_SEQUENCE_3)){
					
					
					
					


						if(null!=planAndProblem.getApplyIntoCreateDatetime()){
							report.setInstallItemApplyDatetimeLinyufang(planAndProblem.getApplyIntoCreateDatetime());
						}

						if(null!=planAndProblem.getApplyIntoDate()){
							report.setInstallItemApplyIntoDateLinyufang(planAndProblem.getApplyIntoDate());
						}

						if(null!=planAndProblem.getRealIntoDate()){
							report.setInstallItemRealIntoDateLinyufang(planAndProblem.getRealIntoDate());
						}

						if(null!=planAndProblem.getRealCompleteDate()){
							report.setInstallItemRealAcceptDateLinyufang(planAndProblem.getRealCompleteDate());
						}

						if(null!=planAndProblem.getProblemCount()){
							if(planAndProblem.getProblemCount() > 0){
								report.setInstallItemIsReturnedLinyufang(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_1);
							}else{
								report.setInstallItemIsReturnedLinyufang(BusinessProblemConstantUtil.INSTALL_ITEM_IS_RETURNED_0);
							}
						}

						if(StringUtils.isNotBlank(planAndProblem.getProblemDescribe())){
							report.setInstallItemProblemDescribeLinyufang(planAndProblem.getProblemDescribe());
						}

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