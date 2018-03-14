package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.NewChecksizeOrder;

/**
 * 上报复尺新版(20161107-20161113)
 * @author llp
 * 2016-11-15
 */
@MyBatisDao
public interface NewChecksizeDao extends CrudDao2<NewChecksizeOrder>{

	List<NewChecksizeOrder> queryList(Integer managerID);

	NewChecksizeOrder getByID(Integer id);

}
