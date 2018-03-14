
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizBusinessUrge;
import cn.damei.entity.modules.InstallBusinessUrge;
import cn.damei.entity.modules.WallAndFloorBusinessUrge;


@MyBatisDao
public interface BizBusinessUrgeDao extends CrudDao2<BizBusinessUrge> {


	Integer findCount(BizBusinessUrge bizBusinessUrge);


	Integer findCountByfiveTime(BizBusinessUrge bizBusinessUrge);


	List<InstallBusinessUrge> findInstallSelectList(InstallBusinessUrge installBusinessUrge);


	List<WallAndFloorBusinessUrge> findWallAndFloorSelectList(WallAndFloorBusinessUrge wallAndFloorBusinessUrge);


    List<BizBusinessUrge> findUnqualifiedAcceptLog(Map<String, String> map);
}