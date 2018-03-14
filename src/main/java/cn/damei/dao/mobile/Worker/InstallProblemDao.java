package cn.damei.dao.mobile.Worker;


import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Worker.InstallItem;
import cn.damei.entity.modules.BizOrderInstallItemProblem;

@MyBatisDao
public interface InstallProblemDao{

	/**
	 * 查询施工单列表(问题上报)
	 * @param installItem
	 * @return
	 */
	List<InstallItem> findInstallConstructBillProblemList(InstallItem installItem);

	/**
	 * 根据施工单id查询相关信息(问题上报)
	 * @param constructBillId
	 * @return
	 */
	InstallItem findInstallConstructBillMessage(Integer constructBillId);

	/**
	 * 查询该订单5分钟内提交问题上报的数量
	 * @param bizOrderInstallItemProblem
	 * @return
	 */
	Integer findProblemCount(BizOrderInstallItemProblem bizOrderInstallItemProblem);

	/**
	 * 动态加载问题上报记录页面
	 * @param bizOrderInstallItemProblem
	 * @return
	 */
	List<BizOrderInstallItemProblem> findProblemLogList(BizOrderInstallItemProblem bizOrderInstallItemProblem);

	/**
	 * 查看图片
	 * @param reportCheckDetailsPic
	 * @return
	 */
	List<ReportCheckDetailsPic> findPic(ReportCheckDetailsPic reportCheckDetailsPic);


	


}
