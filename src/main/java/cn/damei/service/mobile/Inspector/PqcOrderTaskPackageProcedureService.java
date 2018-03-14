package cn.damei.service.mobile.Inspector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.dao.mobile.Inspector.PqcOrderTaskpackageProcedureDao;
import cn.damei.entity.mobile.Inspector.PqcOrderTaskpackageProcedure;

/** 
* @author qww
* @version 创建时间：2016年10月24日 下午3:47:59 
* 质检登录系统首页
*/
@Service
@Transactional(readOnly=true)
public class PqcOrderTaskPackageProcedureService  extends CrudService2<PqcOrderTaskpackageProcedureDao, PqcOrderTaskpackageProcedure>{
	
	/**
	 * 查询工程清单
	 * @param map
	 * @return
	 */
	public List<PqcOrderTaskpackageProcedure> queryBizOrderTaskpackageProcedure(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taskpackageId", id);
		map.put("measurementUnitType", ConstantUtils.MEASUREMENT_UNIT_TYPE);
		return dao.queryBizOrderTaskpackageProcedure(map);
	}
}
