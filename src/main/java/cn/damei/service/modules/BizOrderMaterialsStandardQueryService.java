
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.BizOrderMaterialsStandardQueryDao;
import cn.damei.entity.modules.BizOrderMaterialsStandardQuery;


@Service
@Transactional(readOnly = true)
public class BizOrderMaterialsStandardQueryService extends CrudService<BizOrderMaterialsStandardQueryDao, BizOrderMaterialsStandardQuery> {
	

		public List<BizOrderMaterialsStandardQuery> getBizOrderMaterialsStandardQueryList(BizOrderMaterialsStandardQuery bizOrderMaterialsStandardQuery){
			return dao.getBizOrderMaterialsStandardQueryList(bizOrderMaterialsStandardQuery);
		}

		public BizOrderMaterialsStandardQuery getBizOrderMaterialsStandardQueryListByOrderId(String orderId,String materialsLargeType){
			return dao.getBizOrderMaterialsStandardQueryListByOrderId(orderId,materialsLargeType);
		}
	
}