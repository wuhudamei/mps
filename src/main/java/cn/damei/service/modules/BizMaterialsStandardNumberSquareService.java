
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizMaterialsStandardNumberSquareDao;
import cn.damei.entity.modules.BizMaterialsStandardNumberSquare;


@Service
@Transactional(readOnly = true)
public class BizMaterialsStandardNumberSquareService extends CrudService2<BizMaterialsStandardNumberSquareDao, BizMaterialsStandardNumberSquare> {

	public BizMaterialsStandardNumberSquare get(Integer id) {
		return super.get(id);
	}
	
	public List<BizMaterialsStandardNumberSquare> findList(BizMaterialsStandardNumberSquare bizMaterialsStandardNumberSquare) {
		return super.findList(bizMaterialsStandardNumberSquare);
	}
	
	public Page<BizMaterialsStandardNumberSquare> findPage(Page<BizMaterialsStandardNumberSquare> page, BizMaterialsStandardNumberSquare bizMaterialsStandardNumberSquare) {
		return super.findPage(page, bizMaterialsStandardNumberSquare);
	}
	
	@Transactional(readOnly = false)
	public void save(BizMaterialsStandardNumberSquare bizMaterialsStandardNumberSquare) {
		super.save(bizMaterialsStandardNumberSquare);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizMaterialsStandardNumberSquare bizMaterialsStandardNumberSquare) {
		super.delete(bizMaterialsStandardNumberSquare);
	}

	public	List<String> getIdByMaterialsId(String materialsId){
	return dao.getIdByMaterialsId(materialsId);		
	}
}