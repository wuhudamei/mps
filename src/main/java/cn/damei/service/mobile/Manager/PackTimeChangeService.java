package cn.damei.service.mobile.Manager;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.mobile.Manager.PackTimeChangeDao;
import cn.damei.entity.mobile.Manager.TaskPackage;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年10月19日 下午5:46:28 
* 类说明 
*/
@Service
@Transactional(readOnly=true)
public class PackTimeChangeService extends CrudService<PackTimeChangeDao, TaskPackage> {
	
	public List<TaskPackage> selectPackByManagerId(TaskPackage pack){
		return dao.selectPackByManagerId(pack);
	}
	public TaskPackage packDetailByPackId(Integer packId){
		return dao.packDetailByPackId(packId);
	}
	@Transactional(readOnly=false)
	public void changePackTime(TaskPackage pack){
		dao.changePackTime(pack);
	}
	
	public String selectDiaoDuYuan(){
		
		return dao.selectDiaoDuYuan();
	}
	public String selectDiaoDuYuanPhone(Integer employeeId){
		
		return dao.selectDiaoDuYuanPhone(employeeId);
	}
}
