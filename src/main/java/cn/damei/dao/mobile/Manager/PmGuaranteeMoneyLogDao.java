package cn.damei.dao.mobile.Manager;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.PmGuaranteeMoneyLog;

import java.util.List;

/** 
* @author 邱威威qww 
* @version 创建时间：2016年10月5日 下午4:28:27 
* 类说明 
*/
@MyBatisDao
public interface PmGuaranteeMoneyLogDao extends CrudDao2<PmGuaranteeMoneyLog>{

    public List<PmGuaranteeMoneyLog> queryPmGuaranteeMoneyLog(Integer pmEmployeeId);
}