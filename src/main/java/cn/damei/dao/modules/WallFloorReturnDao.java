
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.WallFloorReturn;


@MyBatisDao
public interface WallFloorReturnDao extends CrudDao<WallFloorReturn> {

	List<WallFloorReturn> findList30(WallFloorReturn wallFloorReturn);

}