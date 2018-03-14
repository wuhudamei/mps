package cn.damei.Quartz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.damei.entity.modules.BizSynData;
import cn.damei.service.modules.BizSynDataService;
import cn.damei.entity.modules.QuarzBizPrePmSettleFinService;

/**
 * 
 * @author 郝永辉
 * @2017年3月28日
 * @ClassInfo:该类: 从同步数据表中取出二期款和尾款的数据，并把该数据更新到财务收款表中
 */
public class QuarzBizPrePmSettleFin {

	@Autowired
	private QuarzBizPrePmSettleFinService quarzBizPrePmSettleFinService;
	@Autowired
	private BizSynDataService bizSynDataService;

	public void execute() throws Exception {
		BizSynData bizSynData = new BizSynData();
		bizSynData.setDataDirection("1");
		/*bizSynData.setSynStatus("1");*/
		List<BizSynData> bizSynDataList = bizSynDataService.findPrePmSettleFinList(bizSynData);
		for (BizSynData data : bizSynDataList) {
			String reult = "0";
			try {
				quarzBizPrePmSettleFinService.updateBizPrePmData(data);
			} catch (Exception e) {
				reult = "1";
				throw e;
			}finally{
				if (reult.equals("1")) {
					data.setBusinessData(data.getBusinessData().replaceAll("[\\[\\]]", ""));
					data.setSynStatus("5");
					bizSynDataService.save(data);
				}
			}
			

		}

	}
}
