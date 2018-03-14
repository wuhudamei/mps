package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.ReportCheckDetailsPic;
import cn.damei.entity.modules.EnginInstallNew;


@MyBatisDao
public interface EnginInstallNewDao extends CrudDao2<EnginInstallNew> {


	Integer findInstallCountUnderStatus(EnginInstallNew enginInstallNew);


	List<EnginInstallNew> findDealWithList(EnginInstallNew enginInstallNew);


	List<EnginInstallNew> findRejectedList(EnginInstallNew enginInstallNew);


	List<EnginInstallNew> findSpecialDealWithList(EnginInstallNew enginInstallNew);


	List<BizBusinessStatusLog> findInstallStatusLog(BizBusinessStatusLog bizBusinessStatusLog);


	List<BizBusinessStatusLog> findInstallStatusLogTwo(BizBusinessStatusLog bizBusinessStatusLog);

	BizBusinessStatusLog getcailiaozhuang(BizBusinessStatusLog bizBusinessStatusLog);

	BizBusinessStatusLog getyanshou(BizBusinessStatusLog bizBusinessStatusLog);

	BizBusinessStatusLog BizBusinessStatusLog(BizBusinessStatusLog bizBusinessStatusLog);

	BizBusinessStatusLog getgonrenrenqiandao(BizBusinessStatusLog bizBusinessStatusLog);

	BizBusinessStatusLog BizBusinessscuesss(BizBusinessStatusLog bizBusinessStatusLog);

	List<BizBusinessStatusLog> findInstallStatusLogC(BizBusinessStatusLog bizBusinessStatusLog);


	List<ReportCheckDetailsPic> findPic(ReportCheckDetailsPic reportCheckDetailsPic);


	List<ReportCheckDetailsPic> findAcceptPic(Integer id);

    List<BizBusinessStatusLog> getAcceptLog(String installId);
}