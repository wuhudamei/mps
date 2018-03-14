
package cn.damei.service.modules;

import cn.damei.common.constantUtils.InstallPlanConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.BizMainMaterialsUnqualifiedReasonDao;
import cn.damei.entity.modules.BizMainMaterialsUnqualifiedReason;
import cn.damei.dao.modules.BizProjectInstallItemDao;
import cn.damei.entity.modules.BizProjectInstallItem;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class BizMainMaterialsUnqualifiedReasonService extends CrudService2<BizMainMaterialsUnqualifiedReasonDao, BizMainMaterialsUnqualifiedReason> {

	@Autowired
	private BizProjectInstallItemDao bizProjectInstallItemDao;

	public BizMainMaterialsUnqualifiedReason get(Integer id) {
		return super.get(id);
	}
	
	public List<BizMainMaterialsUnqualifiedReason> findList(BizMainMaterialsUnqualifiedReason bizMainMaterialsUnqualifiedReason) {
		return super.findList(bizMainMaterialsUnqualifiedReason);
	}


	public Page<BizMainMaterialsUnqualifiedReason> findPage(Page<BizMainMaterialsUnqualifiedReason> page, BizMainMaterialsUnqualifiedReason bizMainMaterialsUnqualifiedReason) {

		List<Integer> list = new ArrayList<Integer>();
		if(null != bizMainMaterialsUnqualifiedReason.getMainMaterialsInstallItemId()){
			list.add(bizMainMaterialsUnqualifiedReason.getMainMaterialsInstallItemId());
		}
		if(null != bizMainMaterialsUnqualifiedReason.getMainMaterialsInstallItemIdStop()){
			list.add(bizMainMaterialsUnqualifiedReason.getMainMaterialsInstallItemIdStop());
		}
		if(CollectionUtils.isNotEmpty(list)){
			bizMainMaterialsUnqualifiedReason.setMainMaterialsInstallItemIdList(list);
		}
		return super.findPage(page, bizMainMaterialsUnqualifiedReason);
	}


	@Transactional(readOnly = false)
	public void save(BizMainMaterialsUnqualifiedReason bizMainMaterialsUnqualifiedReason) {
		BizProjectInstallItem bizProjectInstallItem = bizProjectInstallItemDao.get(String.valueOf(bizMainMaterialsUnqualifiedReason.getMainMaterialsInstallItemId()));
		bizMainMaterialsUnqualifiedReason.setMainMaterialsInstallItem(bizProjectInstallItem.getInstallItemName());
		super.save(bizMainMaterialsUnqualifiedReason);
	}


	@Transactional(readOnly = false)
	public String updateRreasonEnable(BizMainMaterialsUnqualifiedReason bizMainMaterialsUnqualifiedReason) {
		if(InstallPlanConstantUtil.INSTALL_ACCEPT_REASON_STATUS_YES_1.equals(bizMainMaterialsUnqualifiedReason.getStatus())){
			bizMainMaterialsUnqualifiedReason.setStatus(InstallPlanConstantUtil.INSTALL_ACCEPT_REASON_STATUS_NO_0);
		}else {
			bizMainMaterialsUnqualifiedReason.setStatus(InstallPlanConstantUtil.INSTALL_ACCEPT_REASON_STATUS_YES_1);
		}
		bizMainMaterialsUnqualifiedReason.preUpdate();
		return (dao.updateRreasonEnable(bizMainMaterialsUnqualifiedReason))?"0":"1";
	}


	@Transactional(readOnly = false)
	public String updateRreasonDelete(Integer id) {
		return (dao.updateRreasonDelete(id))?"0":"1";
	}


	public void storeIdAndProjectMode(BizMainMaterialsUnqualifiedReason bizMainMaterialsUnqualifiedReason, Model model) {
		User user = UserUtils.getUser();

		if(null==bizMainMaterialsUnqualifiedReason.getStoreId()){
			if(StringUtils.isNotBlank(user.getStoreId())){
				bizMainMaterialsUnqualifiedReason.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}

		if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
			model.addAttribute("gongcheng", true);
		}else{
			bizMainMaterialsUnqualifiedReason.setProjectMode(user.getProjectMode());
		}
	}



	public List<BizProjectInstallItem> queryInstallItemList(String storeId, String projectMode, String installMode) {
		BizProjectInstallItem bizProjectInstallItem = new BizProjectInstallItem();
		bizProjectInstallItem.setStoreId(storeId);
		bizProjectInstallItem.setProjectMode(projectMode);
		bizProjectInstallItem.setInstallMode(installMode);
		return dao.queryInstallItemList(bizProjectInstallItem);
	}


	public List<BizMainMaterialsUnqualifiedReason> queryUnqualifiedReasonList(Integer id) {
		return dao.queryUnqualifiedReasonList(id);
	}
}