package cn.damei.service.mobile.Manager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.BizPurchaseReceiveBillProductDao;
import cn.damei.entity.mobile.Manager.BizPurchaseReceiveBillProduct;

@Service
@Transactional
public class BizPurchaseReceiveBillProductService extends CrudService2<BizPurchaseReceiveBillProductDao, BizPurchaseReceiveBillProduct>{

}
