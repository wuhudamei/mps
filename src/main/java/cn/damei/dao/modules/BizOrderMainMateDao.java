
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsChoiceBill;
import cn.damei.entity.modules.BizOrderMainMate;


@MyBatisDao
public interface BizOrderMainMateDao extends CrudDao2<BizOrderMainMate> {


	public int deleteByOrderId(int orderId);

	public void savebyid(BizOrderMainMate bizOrderMainMate);

	public void updateuni(BizOrderMainMate bizOrderMainMate);

	public BizMaterialsChoiceBill ismaterialschoicebill(String string);

	public List<BizOrderMainMate> findListOne(BizOrderMainMate bizOrderMainMate);

	public int insertpurchaseCount(int parseInt);
}