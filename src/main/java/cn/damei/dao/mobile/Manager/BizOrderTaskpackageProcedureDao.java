package cn.damei.dao.mobile.Manager;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.BizOrderTaskpackageProcedure;

/** 
* @author 邱威威qww 
* @version 创建时间：2016年10月5日 下午4:28:27 
* 类说明 
*/
@MyBatisDao
public interface BizOrderTaskpackageProcedureDao  extends CrudDao2<BizOrderTaskpackageProcedure>{
	
	/**
	 * 查询工程清单
	 * @param map
	 * @return
	 */
	public List<BizOrderTaskpackageProcedure> queryOrderTaskpackageProcedure(Map<String, Object> map);

	public List<BizOrderTaskpackageProcedure> queryOrderTaskpackageProcedure1(Map<String, Object> map);
}