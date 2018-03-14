package cn.damei.dao.mobile.Inspector;

import java.util.List;
import java.util.Map;
import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.PqcOrderTaskpackageProcedure;

/** 
* @author 邱威威qww 
* @version 创建时间：2016年10月5日 下午4:28:27 
* 类说明 
*/
@MyBatisDao
public interface PqcOrderTaskpackageProcedureDao  extends CrudDao2<PqcOrderTaskpackageProcedure>{
	
	/**
	 * 查询工程清单
	 * @param map
	 * @return
	 */
	public List<PqcOrderTaskpackageProcedure> queryBizOrderTaskpackageProcedure(Map<String, Object> map);
}