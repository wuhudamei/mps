/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizNotice;
import cn.damei.entity.modules.BizNoticeVo;

import java.util.List;

/**
 * 公告DAO接口
 * @author qww
 * @version 2017-01-14
 */
@MyBatisDao
public interface BizNoticeDao extends CrudDao2<BizNotice> {

    public BizNotice queryNotice(BizNotice notice);

    public List<BizNoticeVo> findNoticeList(BizNoticeVo notice);

    public BizNotice queryNoticeById(Integer id);

    public List<BizNotice> findAppNoticePageList(BizNotice notice);

    public Integer findAppNoticePageListCount(BizNotice notice);
    
    public Integer findAppNoticePageListNum(BizNotice notice);
}