
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialSelfbuy;


@MyBatisDao
public interface BizMaterialSelfbuyDao extends CrudDao2<BizMaterialSelfbuy> {


	List<BizMaterialSelfbuy> findMaterialList(BizMaterialSelfbuy bizMaterialSelfbuy);


	List<BizMaterialSelfbuy> findMaterialSelfbuyListAjax(BizMaterialSelfbuy bizMaterialSelfbuy);
	
}