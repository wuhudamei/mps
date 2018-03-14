package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderInstallPlan;
import cn.damei.entity.modules.BizProjectInstallItem;


@MyBatisDao
public interface BizOrderInstallPlanDao extends CrudDao2<BizOrderInstallPlan>{

	List<BizOrderInstallPlan> getByList();

	BizOrderInstallPlan selectByInstallID(Integer id);

	boolean updateByIdAndStatus(Integer id, String status,String date, String supplierConfirmRemarks);

	List<BizProjectInstallItem> findInstallNameByStoreIdAndProjectModeId(BizProjectInstallItem bizProjectInstallItem);

    List<BizOrderInstallPlan> findUnqualifiedLogList(BizOrderInstallPlan bizOrderInstallPlan);

    List<String> findUnPhone(String id, String s);

    List<String> findPhone(String id);

    List<BizOrderInstallPlan> findOrderInstallAccept(BizOrderInstallPlan bizOrderInstallPlan);

    BizOrderInstallPlan getOrderDetail(BizOrderInstallPlan bizOrderInstallPlan);

    List<BizOrderInstallPlan> findItemPlanLog(BizOrderInstallPlan bizOrderInstallPlan);

    List<BizOrderInstallPlan> findUnqualifiedInstallItemCount(BizOrderInstallPlan bizOrderInstallPlan);

    List<BizOrderInstallPlan> findUnqualifiedResonCount(BizOrderInstallPlan bizOrderInstallPlan);
}
