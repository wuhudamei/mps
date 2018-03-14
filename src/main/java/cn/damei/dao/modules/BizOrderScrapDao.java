
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderScrap;
import cn.damei.entity.modules.PublicPic;


@MyBatisDao
public interface BizOrderScrapDao extends CrudDao2<BizOrderScrap> {


	List<BizOrderScrap> findRecoveryList(BizOrderScrap bizOrderScrap);

	void scrapUpdate(BizOrderScrap bizOrderScrap);

	void savePicAll(List<PublicPic> pList);
	void updateOrderNumber(BizOrderScrap bizOrderScrap);
}