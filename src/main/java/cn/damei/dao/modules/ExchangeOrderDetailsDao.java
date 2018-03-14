package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ExchangeOrderDetails;

/**
 * 更换详情
 * @author chenguanhua
 *
 */
@MyBatisDao
public interface ExchangeOrderDetailsDao extends CrudDao2<ExchangeOrderDetails>{
	/**
	 * 详情page
	 * @param id
	 * @return
	 */
	public List<ExchangeOrderDetails> selectDetailsPageById(ExchangeOrderDetails exchangeOrderDetails);
	
	
	/**
	 * 项目经理换单详情
	 * @param id
	 * @return
	 */
	public List<ExchangeOrderDetails> selectManagerDetailsPageById(ExchangeOrderDetails exchangeOrderDetails);
	/**
	 * 质检员换单详情
	 * @param id
	 * @return
	 */
	public List<ExchangeOrderDetails> selectInspectorDetailsPageById(ExchangeOrderDetails exchangeOrderDetails);
}
