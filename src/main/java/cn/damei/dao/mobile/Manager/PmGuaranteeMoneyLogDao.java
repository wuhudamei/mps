package cn.damei.dao.mobile.Manager;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.PmGuaranteeMoneyLog;

import java.util.List;


@MyBatisDao
public interface PmGuaranteeMoneyLogDao extends CrudDao2<PmGuaranteeMoneyLog>{

    public List<PmGuaranteeMoneyLog> queryPmGuaranteeMoneyLog(Integer pmEmployeeId);
}