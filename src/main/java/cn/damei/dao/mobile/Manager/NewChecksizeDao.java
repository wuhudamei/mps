package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.NewChecksizeOrder;


@MyBatisDao
public interface NewChecksizeDao extends CrudDao2<NewChecksizeOrder>{

	List<NewChecksizeOrder> queryList(Integer managerID);

	NewChecksizeOrder getByID(Integer id);

}
