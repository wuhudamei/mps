/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPhoneMsg;

import java.util.List;
import java.util.Map;

/**
 * 短信DAO接口
 * @author qww
 * @version 2016-12-01
 */
@MyBatisDao
public interface BizPhoneMsgDao extends CrudDao2<BizPhoneMsg> {

    public List<BizPhoneMsg> queryPhoneMsgList(Map<String, Object> map);

    /****安装项确认调整****/
	public void installPlanInsert(BizPhoneMsg ddMsg);
}