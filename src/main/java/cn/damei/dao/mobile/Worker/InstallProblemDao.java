package cn.damei.dao.mobile.Worker;


import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Worker.InstallItem;
import cn.damei.entity.modules.BizOrderInstallItemProblem;

@MyBatisDao
public interface InstallProblemDao{


	List<InstallItem> findInstallConstructBillProblemList(InstallItem installItem);


	InstallItem findInstallConstructBillMessage(Integer constructBillId);


	Integer findProblemCount(BizOrderInstallItemProblem bizOrderInstallItemProblem);


	List<BizOrderInstallItemProblem> findProblemLogList(BizOrderInstallItemProblem bizOrderInstallItemProblem);


	List<ReportCheckDetailsPic> findPic(ReportCheckDetailsPic reportCheckDetailsPic);


	


}
