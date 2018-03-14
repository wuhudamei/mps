package cn.damei.service.mobile.Manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.dao.mobile.Manager.BizOrderTaskpackageProcedureDao;
import cn.damei.entity.mobile.Manager.BizOrderTaskpackageProcedure;
/**
 * @author 邱威威qww
 * @version 创建时间：2016年9月19日 下午5:00:04 类说明
 */
@Service
@Transactional(readOnly = true)
public class BizOrderTaskpackageProcedureService extends CrudService2<BizOrderTaskpackageProcedureDao, BizOrderTaskpackageProcedure> {
		
	public List<BizOrderTaskpackageProcedure> queryOrderTaskpackageProcedure(Integer taskpackageId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taskpackageId", taskpackageId);
		map.put("measurementUnitType", ConstantUtils.MEASUREMENT_UNIT_TYPE);
		return dao.queryOrderTaskpackageProcedure(map);
	}

	public List<BizOrderTaskpackageProcedure> queryOrderTaskpackageProcedure1(Integer taskpackageId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taskpackageId", taskpackageId);
		map.put("measurementUnitType", ConstantUtils.MEASUREMENT_UNIT_TYPE);
		return dao.queryOrderTaskpackageProcedure1(map);
	}
}