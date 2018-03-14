
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizNoticeViewLog;

import java.util.List;


@MyBatisDao
public interface BizNoticeViewLogDao extends CrudDao2<BizNoticeViewLog> {

    public List<BizNoticeViewLog> queryNoticeViewLogByNoticeId(Integer noticeId);

    public BizNoticeViewLog queryByNoticeIdAndEmployeeId(BizNoticeViewLog log);

	public String findNoticeSum(Integer id);

	public String findYesNoticeSum(Integer id);

}