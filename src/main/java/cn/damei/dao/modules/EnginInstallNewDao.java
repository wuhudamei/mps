package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.ReportCheckDetailsPic;
import cn.damei.entity.modules.EnginInstallNew;

/**
 * 主材安装
 */
@MyBatisDao
public interface EnginInstallNewDao extends CrudDao2<EnginInstallNew> {

	/**
	 * 统计每个状态下的安装项数量
	 * 
	 * @param enginInstallNew
	 * @return
	 */
	Integer findInstallCountUnderStatus(EnginInstallNew enginInstallNew);

	/**
	 * 主材安装申请 已处理
	 * 
	 * @param enginInstallNew
	 * @return
	 */
	List<EnginInstallNew> findDealWithList(EnginInstallNew enginInstallNew);

	/**
	 * 主材安装申请 已驳回
	 * 
	 * @param enginInstallNew
	 * @return
	 */
	List<EnginInstallNew> findRejectedList(EnginInstallNew enginInstallNew);

	/**
	 * 主材安装申请 特殊处理
	 * 
	 * @param enginInstallNew
	 * @return
	 */
	List<EnginInstallNew> findSpecialDealWithList(EnginInstallNew enginInstallNew);

	/**
	 * 主材安装操作日志
	 * 
	 * @param bizBusinessStatusLog
	 * @return
	 */
	List<BizBusinessStatusLog> findInstallStatusLog(BizBusinessStatusLog bizBusinessStatusLog);

	/**
	 * 安装项操作日志(重新申请、驳回)
	 * 
	 * @param bizBusinessStatusLog
	 * @return
	 */
	List<BizBusinessStatusLog> findInstallStatusLogTwo(BizBusinessStatusLog bizBusinessStatusLog);

	BizBusinessStatusLog getcailiaozhuang(BizBusinessStatusLog bizBusinessStatusLog);

	BizBusinessStatusLog getyanshou(BizBusinessStatusLog bizBusinessStatusLog);

	BizBusinessStatusLog BizBusinessStatusLog(BizBusinessStatusLog bizBusinessStatusLog);

	BizBusinessStatusLog getgonrenrenqiandao(BizBusinessStatusLog bizBusinessStatusLog);

	BizBusinessStatusLog BizBusinessscuesss(BizBusinessStatusLog bizBusinessStatusLog);

	List<BizBusinessStatusLog> findInstallStatusLogC(BizBusinessStatusLog bizBusinessStatusLog);

	/**
	 * 查询图片公共方法
	 * 
	 * @Title: findPic
	 * @Description: TODO
	 * @param @param reportCheckDetailsPic
	 * @param @return
	 * @return List<ReportCheckDetailsPic>
	 * @author ZhangTongWei
	 * @throws
	 */
	List<ReportCheckDetailsPic> findPic(ReportCheckDetailsPic reportCheckDetailsPic);

	/**
	 * 查询验收图片
	 * 
	 * @Title: findAcceptPic
	 * @Description: TODO
	 * @param @param id
	 * @param @return
	 * @return List<ReportCheckDetailsPic>
	 * @author ZhangTongWei
	 * @throws
	 */
	List<ReportCheckDetailsPic> findAcceptPic(Integer id);

    List<BizBusinessStatusLog> getAcceptLog(String installId);
}