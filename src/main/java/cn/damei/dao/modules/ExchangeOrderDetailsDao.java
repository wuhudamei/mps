package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ExchangeOrderDetails;


@MyBatisDao
public interface ExchangeOrderDetailsDao extends CrudDao2<ExchangeOrderDetails>{

	public List<ExchangeOrderDetails> selectDetailsPageById(ExchangeOrderDetails exchangeOrderDetails);
	
	

	public List<ExchangeOrderDetails> selectManagerDetailsPageById(ExchangeOrderDetails exchangeOrderDetails);

	public List<ExchangeOrderDetails> selectInspectorDetailsPageById(ExchangeOrderDetails exchangeOrderDetails);
}
