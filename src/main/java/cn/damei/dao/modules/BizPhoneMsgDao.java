
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPhoneMsg;

import java.util.List;
import java.util.Map;


@MyBatisDao
public interface BizPhoneMsgDao extends CrudDao2<BizPhoneMsg> {

    public List<BizPhoneMsg> queryPhoneMsgList(Map<String, Object> map);


	public void installPlanInsert(BizPhoneMsg ddMsg);
}