
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.IllegalModalityDao;
import cn.damei.entity.modules.IllegalModality;


@Service
@Transactional(readOnly = true)
public class IllegalModalityService extends CrudService2<IllegalModalityDao, IllegalModality> {

	public IllegalModality get(Integer id) {
		return super.get(id);
	}
	
	public List<IllegalModality> findList(IllegalModality illegalModality) {
		return super.findList(illegalModality);
	}
	
	public Page<IllegalModality> findPage(Page<IllegalModality> page, IllegalModality illegalModality) {
		return super.findPage(page, illegalModality);
	}
	
	@Transactional(readOnly = false)
	public void save(IllegalModality illegalModality) {
		super.save(illegalModality);
	}
	
	@Transactional(readOnly = false)
	public void delete(IllegalModality illegalModality) {
		super.delete(illegalModality);
	}
	
	
	

	public IllegalModality getStoreKindItemInfoByIllegalModalityId(Integer id){
		
		return dao.getStoreKindItemInfoByIllegalModalityId(id);
	}
	
	
}