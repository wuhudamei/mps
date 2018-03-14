
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderChecksizeEntity;
import cn.damei.entity.modules.BizOrderChecksizePic;


@MyBatisDao
public interface BizOrderChecksizeDao extends CrudDao2<BizOrderChecksizeEntity> {


	List<BizOrderChecksizePic> selectPicByOrderChecksizeId(Integer orderChecksizeId);


	BizOrderChecksizeEntity selectDetailsByOrderChecksizeId(Integer orderChecksizeId);


	boolean updateOrderChecksize(BizOrderChecksizeEntity bizOrderChecksize);

	List<BizOrderChecksizeEntity> findMainItem(String storeId, String projectModeValue);
	
}