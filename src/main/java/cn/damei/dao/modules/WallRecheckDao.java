
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.WallRecheck;


@MyBatisDao
public interface WallRecheckDao extends CrudDao2<WallRecheck> {


	WallRecheck findwallRecheckMessage(Integer orderId);

	List<WallRecheck> findListall(WallRecheck wallRecheck);

	void agreRecheckUpdate(WallRecheck wallRecheck);

	void agreRecheckUpdate1(WallRecheck wallRecheck);

	void agreRecheckUpdate2(WallRecheck wallRecheck);

	void updateExamine(WallRecheck wallRecheck);

	void NotagreRecheckUpdate2(WallRecheck wallRecheck);

	String getempName(int parseInt);

	WallRecheck findSquareActualtow(Integer orderId);

	WallRecheck findWallRecheck(Integer orderId);

	Double querySquate(Integer orderId);

}