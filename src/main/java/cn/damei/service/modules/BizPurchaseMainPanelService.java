package cn.damei.service.modules;

import java.util.ArrayList;
import java.util.List;

import cn.damei.common.constantUtils.PurchaseConstantUtil;
import cn.damei.common.persistence.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizPurchaseMainPanelDao;
import cn.damei.entity.modules.BizPurchaseMainPanel;

@Service
@Transactional(readOnly = true)
public class BizPurchaseMainPanelService extends CrudService2<BizPurchaseMainPanelDao, BizPurchaseMainPanel> {

	public BizPurchaseMainPanel findById(Integer id) {

		return dao.get(id);
	}

	public List<BizPurchaseMainPanel> findList1(Integer applyemployeeId,String type) {

		return dao.findList1(applyemployeeId,type);
	}

	public Page<BizPurchaseMainPanel> findPage(Page<BizPurchaseMainPanel> page, BizPurchaseMainPanel bizPurchaseMainPanel) {
		bizPurchaseMainPanel.setPage(page);
		List<BizPurchaseMainPanel> list = dao.findList(bizPurchaseMainPanel);
		if(null != list && list.size() > 0){
			List<Integer> ids = new ArrayList<>();
			for (BizPurchaseMainPanel a : list){
				ids.add(a.getId());
			}
			BizPurchaseMainPanel purchaseIds = new BizPurchaseMainPanel();
			purchaseIds.setPurchaseType(PurchaseConstantUtil.PURCHASE_TYPE_2);
			purchaseIds.setPurchaseIds(ids);
			List<BizPurchaseMainPanel> purchaseApplyIndexList = dao.queryPurchaseApplyIndex(purchaseIds);
			for (BizPurchaseMainPanel a : purchaseApplyIndexList){
				for (BizPurchaseMainPanel b : list){
					if (a.getId().equals(b.getId())){
						b.setPurchaseApplyIndex(a.getPurchaseApplyIndex());
						break;
					}
				}
			}
		}
		page.setList(list);
		return page;
	}

}
