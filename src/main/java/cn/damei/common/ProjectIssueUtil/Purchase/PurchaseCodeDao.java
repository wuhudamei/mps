package cn.damei.common.ProjectIssueUtil.Purchase;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.ReCheckCode;

/**
 * Created by joseph on 2017/7/8.
 */
@MyBatisDao
public interface PurchaseCodeDao {

    void saveCode();
    ReCheckCode getCode();
      void updateCode(ReCheckCode code);

}