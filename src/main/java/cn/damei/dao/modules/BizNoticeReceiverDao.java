
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizNoticeReceiver;

import java.util.List;


@MyBatisDao
public interface BizNoticeReceiverDao extends CrudDao2<BizNoticeReceiver> {

    public void insertBatch(List<BizNoticeReceiver> list);

    public List<DropModel> queryNoticeReceiverEmployee(Integer noticeId);

    public void deleteByNoticeId(Integer noticeId);

    public List<String> queryNoticeReceiverByNoticeId(Integer noticeId);
}