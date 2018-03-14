package cn.damei.service.modules;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BizStarConstantUtil;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.BizStarBasicSalaryDao;
import cn.damei.entity.modules.BizStarBasicSalary;

@Service
@Transactional(readOnly = true)
public class BizStarBasicSalaryService extends CrudService2<BizStarBasicSalaryDao, BizStarBasicSalary> {
	
	public String getMaxVersion(){
		String version = dao.getMaxVersion();
		if(!StringUtils.isNotBlank(version)){
			
			return BizStarConstantUtil.BASIC_SALARY_MAX_VERSION;
		}
		return String.valueOf(Integer.parseInt(version)+1);
	}
	
	public Double getSalary(String attendMonth,Integer storeId,String projectMode,Integer starLevel){
		return dao.getSalary(attendMonth,storeId,projectMode,starLevel);
	}
	
}
