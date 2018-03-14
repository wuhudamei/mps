/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizNoticeViewLog;

import java.util.List;

/**
 * 消息公告查看日志DAO接口
 * @author qww
 * @version 2017-01-14
 */
@MyBatisDao
public interface BizNoticeViewLogDao extends CrudDao2<BizNoticeViewLog> {

    public List<BizNoticeViewLog> queryNoticeViewLogByNoticeId(Integer noticeId);

    public BizNoticeViewLog queryByNoticeIdAndEmployeeId(BizNoticeViewLog log);

	public String findNoticeSum(Integer id);

	public String findYesNoticeSum(Integer id);

}