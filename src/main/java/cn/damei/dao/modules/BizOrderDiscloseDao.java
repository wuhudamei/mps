package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderDisclose;

import java.util.List;


@MyBatisDao
public interface BizOrderDiscloseDao extends CrudDao2<BizOrderDisclose>{

    public List<BizOrderDisclose> findListForExcel(BizOrderDisclose bizOrderDisclose);

}
