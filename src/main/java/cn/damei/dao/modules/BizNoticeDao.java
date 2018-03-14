
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizNotice;
import cn.damei.entity.modules.BizNoticeVo;

import java.util.List;


@MyBatisDao
public interface BizNoticeDao extends CrudDao2<BizNotice> {

    public BizNotice queryNotice(BizNotice notice);

    public List<BizNoticeVo> findNoticeList(BizNoticeVo notice);

    public BizNotice queryNoticeById(Integer id);

    public List<BizNotice> findAppNoticePageList(BizNotice notice);

    public Integer findAppNoticePageListCount(BizNotice notice);
    
    public Integer findAppNoticePageListNum(BizNotice notice);
}