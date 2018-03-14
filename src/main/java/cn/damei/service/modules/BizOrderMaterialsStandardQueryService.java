/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.BizOrderMaterialsStandardQueryDao;
import cn.damei.entity.modules.BizOrderMaterialsStandardQuery;

/**
 * 标化辅料（筒灯灯带）查询Service
 * @author lft
 * @version 2017-05-12
 */
@Service
@Transactional(readOnly = true)
public class BizOrderMaterialsStandardQueryService extends CrudService<BizOrderMaterialsStandardQueryDao, BizOrderMaterialsStandardQuery> {
	
		//根据条件查询
		public List<BizOrderMaterialsStandardQuery> getBizOrderMaterialsStandardQueryList(BizOrderMaterialsStandardQuery bizOrderMaterialsStandardQuery){
			return dao.getBizOrderMaterialsStandardQueryList(bizOrderMaterialsStandardQuery);
		}
		//查询 一个 根据 orderId
		public BizOrderMaterialsStandardQuery getBizOrderMaterialsStandardQueryListByOrderId(String orderId,String materialsLargeType){
			return dao.getBizOrderMaterialsStandardQueryListByOrderId(orderId,materialsLargeType);
		}
	
}