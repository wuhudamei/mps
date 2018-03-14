package cn.damei.dao.mobile.Inspector;

import java.util.List;
import java.util.Map;
import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.PqcOrderTaskPackage;
import cn.damei.entity.mobile.Inspector.PqcOrderTaskpackageVo;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年9月19日 下午4:28:27 
* 类说明 
*/
@MyBatisDao
public interface PqcOrderTaskPackageDao  extends CrudDao2<PqcOrderTaskPackage>{
	
	/**
	 * 质检端   查询质检员下所有[待质检复核结算单]状态下的任务包
	 * @param map
	 * @return
	 */
	public List<PqcOrderTaskPackage> queryTaskPackageByInspectorId(Map<String, Object> map);
	
	/**
	 * 查询复核清单
	 * @param id
	 * @return
	 */
	public PqcOrderTaskpackageVo queryTaskPackageRecheck(Integer id);
	
	/**
	 * 根据订单任务包id更新状态
	 * @param task
	 */
	public void updatePackageStateIdById(PqcOrderTaskPackage task);
	
	/**
	 * 质检员复核完后给项目经理发短信
	 * @param id
	 * @return
	 */
	public PqcOrderTaskPackage querySmsMessageForManager(Integer id);
	
	public PqcOrderTaskPackage queryOrderTaskPackageByParam(Map<String,Object> param);
}