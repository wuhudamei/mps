package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPurchaseVo;

@MyBatisDao
public interface BizPurchaseVoDao extends CrudDao2<BizPurchaseVo>{

	BizPurchaseVo findById(Integer id);

	void updateStatusById(Integer id);

	List<BizPurchaseVo> findList1(Integer applyemployeeId,String type);

	/**
	 * 供应商列表
	 * @param bizPurchaseVo
	 * @return
	 */
	List<BizPurchaseVo> findSupplierList(BizPurchaseVo bizPurchaseVo);
	
}
