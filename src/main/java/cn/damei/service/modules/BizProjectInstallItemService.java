
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.BizProjectInstallItemDao;
import cn.damei.entity.modules.BizProjectInstallItem;


@Service
@Transactional(readOnly = true)
public class BizProjectInstallItemService extends CrudService<BizProjectInstallItemDao, BizProjectInstallItem> {

	public BizProjectInstallItem get(String id) {
		return super.get(id);
	}

	public List<BizProjectInstallItem> findList(BizProjectInstallItem bizProjectInstallItem) {
		return super.findList(bizProjectInstallItem);
	}

	public Page<BizProjectInstallItem> findPage(Page<BizProjectInstallItem> page, BizProjectInstallItem bizProjectInstallItem) {
		return super.findPage(page, bizProjectInstallItem);
	}

	@Transactional(readOnly = false)
	public void save(BizProjectInstallItem bizProjectInstallItem) {
		super.save(bizProjectInstallItem);
	}

	@Transactional(readOnly = false)
	public void delete(BizProjectInstallItem bizProjectInstallItem) {
		super.delete(bizProjectInstallItem);
	}



	public List<BizProjectInstallItem> getAllInstallItemByStoreId(String storeId) {

		return null;
	}

	@Transactional(readOnly = false)
	public void isON(BizProjectInstallItem installItem) {
		super.dao.isON(installItem);
	}

	public List<BizProjectInstallItem> queryInstallItemList(BizProjectInstallItem bizProjectInstallItem) {
		return dao.queryInstallItemList(bizProjectInstallItem);
	}
}