
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEmployeeBankcard;
import cn.damei.entity.modules.BizEmployeeBankcard2;
import cn.damei.entity.modules.BizEmployeeBankcardRelation;


@MyBatisDao
public interface BizEmployeeBankcardDao extends CrudDao<BizEmployeeBankcard> {

	List<BizEmployeeBankcardRelation> queryRelationMessage(Integer id);

	void deleteRelation(Integer id);

	void addRelation(Integer employeeBankcardId, String name, String card);

	BizEmployeeBankcard2 queryBizEmployeeBankcard(Integer id);

	BizEmployeeBankcard2 getByBankcard(String bankCardNo);

	List<BizEmployeeBankcard> findAll();
	
	Map<String,Integer>  checkIdCard(String cardNo);

	String checkBankCard(String bankCardNo);
	
}