/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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

/**
 * 主材安装项验收不合格原因配置Service
 * @author wyb
 * @version 2018-01-23
 */
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

	/**
	 * 主材不合格验收原因列表分页查询
	 * @param page
	 *            分页对象
	 * @param bizMainMaterialsUnqualifiedReason
	 * @return
	 */
	public Page<BizMainMaterialsUnqualifiedReason> findPage(Page<BizMainMaterialsUnqualifiedReason> page, BizMainMaterialsUnqualifiedReason bizMainMaterialsUnqualifiedReason) {
		//安装项（启用+停用）
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

	/**
	 * 主材不合格验收原因保存
	 * @param bizMainMaterialsUnqualifiedReason
	 */
	@Transactional(readOnly = false)
	public void save(BizMainMaterialsUnqualifiedReason bizMainMaterialsUnqualifiedReason) {
		BizProjectInstallItem bizProjectInstallItem = bizProjectInstallItemDao.get(String.valueOf(bizMainMaterialsUnqualifiedReason.getMainMaterialsInstallItemId()));
		bizMainMaterialsUnqualifiedReason.setMainMaterialsInstallItem(bizProjectInstallItem.getInstallItemName());
		super.save(bizMainMaterialsUnqualifiedReason);
	}

	/**
	 * 停启用
	 * @param bizMainMaterialsUnqualifiedReason
	 * @return
	 */
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

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public String updateRreasonDelete(Integer id) {
		return (dao.updateRreasonDelete(id))?"0":"1";
	}

	/**
	 * 门店和工程模式权限控制
	 * @param bizMainMaterialsUnqualifiedReason
	 * @param model
	 */
	public void storeIdAndProjectMode(BizMainMaterialsUnqualifiedReason bizMainMaterialsUnqualifiedReason, Model model) {
		User user = UserUtils.getUser();
		//门店
		if(null==bizMainMaterialsUnqualifiedReason.getStoreId()){
			if(StringUtils.isNotBlank(user.getStoreId())){
				bizMainMaterialsUnqualifiedReason.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		//工程模式
		if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
			model.addAttribute("gongcheng", true);
		}else{
			bizMainMaterialsUnqualifiedReason.setProjectMode(user.getProjectMode());
		}
	}


	/**
	 * 动态加载主材安装项
	 * @param storeId
	 * @param projectMode
	 * @param installMode
	 * @return
	 */
	public List<BizProjectInstallItem> queryInstallItemList(String storeId, String projectMode, String installMode) {
		BizProjectInstallItem bizProjectInstallItem = new BizProjectInstallItem();
		bizProjectInstallItem.setStoreId(storeId);
		bizProjectInstallItem.setProjectMode(projectMode);
		bizProjectInstallItem.setInstallMode(installMode);
		return dao.queryInstallItemList(bizProjectInstallItem);
	}

	/**
	 * 根据安装项计划id查询安装项不合格验收原因列表
	 * @param id
	 * @return
	 */
	public List<BizMainMaterialsUnqualifiedReason> queryUnqualifiedReasonList(Integer id) {
		return dao.queryUnqualifiedReasonList(id);
	}
}