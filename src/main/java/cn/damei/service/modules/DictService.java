
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.common.utils.CacheUtils;
import cn.damei.dao.modules.DictDao;
import cn.damei.entity.modules.Dict;
import cn.damei.common.utils.DictUtils;


@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {
	

	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}

	@Transactional(readOnly = false)
	public void save(Dict dict) {
		super.save(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Transactional(readOnly = false)
	public void delete(Dict dict) {
		super.delete(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}


	public List<Dict> getByType(String type) {
		return dao.getByType(type);
	}

}
