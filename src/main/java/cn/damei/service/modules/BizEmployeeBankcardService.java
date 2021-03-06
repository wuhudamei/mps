
package cn.damei.service.modules;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizEmployeeBankcard;
import cn.damei.entity.modules.BizEmployeeBankcard2;
import cn.damei.entity.modules.BizEmployeeBankcardRelation;
import cn.damei.dao.modules.BizEmployeeBankcardDao;


@Service
@Transactional(readOnly = true)
public class BizEmployeeBankcardService extends CrudService<BizEmployeeBankcardDao, BizEmployeeBankcard> {

	public BizEmployeeBankcard get(String id) {
		return super.get(id);
	}
	
	public List<BizEmployeeBankcard> findList(BizEmployeeBankcard bizEmployeeBankcard) {
		return super.findList(bizEmployeeBankcard);
	}
	
	public Page<BizEmployeeBankcard> findPage(Page<BizEmployeeBankcard> page, BizEmployeeBankcard bizEmployeeBankcard) {
		return super.findPage(page, bizEmployeeBankcard);
	}
	
	@Transactional(readOnly = false)
	public void save(BizEmployeeBankcard bizEmployeeBankcard) {
		super.save(bizEmployeeBankcard);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizEmployeeBankcard bizEmployeeBankcard) {
		super.delete(bizEmployeeBankcard);
	}

	public List<BizEmployeeBankcardRelation> queryRelationMessage(Integer id) {

		return dao.queryRelationMessage(id);
	}
	
	@Transactional(readOnly = false)
	public void deleteRelation(Integer id) {

		dao.deleteRelation(id);
	}
	@Transactional(readOnly = false)
	public void addRelation(Integer employeeBankcardId, String name, String card) {

		dao.addRelation(employeeBankcardId,name,card);
	}
	
	@Transactional(readOnly = false)
	public BizEmployeeBankcard2 queryBizEmployeeBankcard(Integer id) {

		return dao.queryBizEmployeeBankcard(id);
	}

	public BizEmployeeBankcard2 getByBankcard(String bankCardNo) {

		return dao.getByBankcard(bankCardNo);
	}

	public List<BizEmployeeBankcard> findAll() {

		return dao.findAll();
	}
	
	public Map<String,Integer> checkIdCard(String cardNo){
		
		
		return dao.checkIdCard(cardNo);
	}

	public Integer checkBankCard(String bankCardNo, String id) {
		String resuid = dao.checkBankCard(bankCardNo);

		if(!id.equals("")){
			if(resuid == null){
				return 0;
			}else{
				if(resuid.equals(id)){
					return 0;
				}else{
					return 1;
				}
			}
			
		}
		if(id.equals("")){
			if(resuid == null){
				return 0;
			}else{
				return 1;
			}
			
		}
		return null;
	}

	 
}