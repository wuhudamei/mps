
package cn.damei.service.modules;

import java.util.*;

import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizEmployee;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import cn.damei.dao.modules.BizTaskPackageTemplatDao;
import cn.damei.dao.modules.BizTaskPackageTemplatRelDao;
import cn.damei.entity.modules.BizTaskPackageTemplat;
import cn.damei.entity.modules.BizTaskPackageTemplatRel;


@Service
@Transactional(readOnly = true)
public class BizTaskPackageTemplatService extends CrudService<BizTaskPackageTemplatDao, BizTaskPackageTemplat> {

	@Autowired
	BizTaskPackageTemplatRelDao bizTaskPackageTemplatRelDao;
	
	@Autowired
	BizTaskPackageTemplatDao bizTaskPackageTemplatDao;
	
	public BizTaskPackageTemplat get(String id) {
		return super.get(id);
	}
	
	public List<BizTaskPackageTemplat> findList(BizTaskPackageTemplat bizTaskPackageTemplat) {
		return super.findList(bizTaskPackageTemplat);
	}
	
	public Page<BizTaskPackageTemplat> findPage(Page<BizTaskPackageTemplat> page, BizTaskPackageTemplat bizTaskPackageTemplat) {
		return super.findPage(page, bizTaskPackageTemplat);
	}
	
	@Transactional(readOnly = false)
	public void save(BizTaskPackageTemplat bizTaskPackageTemplat) {
		User user = UserUtils.getUser();
		Date now = new Date();
		super.save(bizTaskPackageTemplat);
		List<BizTaskPackageTemplatRel> existItems = bizTaskPackageTemplatRelDao.findByTaskPackageTemplateId(bizTaskPackageTemplat.getId());
		Set<String> existsSet = new HashSet<String>();
		for (BizTaskPackageTemplatRel bizTaskPackageTemplatRel : existItems) {
			existsSet.add(bizTaskPackageTemplatRel.getProcedureNo());
		}
		
		String[] newItems = bizTaskPackageTemplat.getProcedureItems().split(",");
		Set<String> newSet = new HashSet<String>();    
		CollectionUtils.addAll(newSet, newItems);
		newSet.remove("");
		

		Set<String> needAdd = new HashSet<String>();
		needAdd.addAll(newSet);
		needAdd.removeAll(existsSet);
		

		Set<String> needDelete = new HashSet<String>();
		needDelete.addAll(existsSet);
		needDelete.removeAll(newSet);
		

		List<BizTaskPackageTemplatRel> addList = new LinkedList<BizTaskPackageTemplatRel>();
		for (String no : needAdd) {
			BizTaskPackageTemplatRel rel = new BizTaskPackageTemplatRel();
			rel.setTaskPackageTemplatId(bizTaskPackageTemplat.getId());
			rel.setProcedureNo(no);
			rel.setCreateBy(user);
			rel.setCreateDate(now);
			rel.setUpdateBy(user);
			rel.setUpdateDate(now);
			rel.setDelFlag("0");
			addList.add(rel);
		}
		if(addList.size()>0){
			bizTaskPackageTemplatRelDao.batchInsert(addList);
		}
		

		if(!needDelete.isEmpty()){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("taskPackageTemplatId", bizTaskPackageTemplat.getId());
			param.put("procedureNos", needDelete.toArray());
			bizTaskPackageTemplatRelDao.batchDeleteByTemplatId(param);
		}
	}
	@Transactional(readOnly = false)
	public void update(BizTaskPackageTemplat bizTaskPackageTemplat) {
		super.save(bizTaskPackageTemplat);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizTaskPackageTemplat bizTaskPackageTemplat) {
		super.delete(bizTaskPackageTemplat);
	}
	
	public List<BizTaskPackageTemplat> queryTaskpackageTemplat() {
		return dao.queryTaskpackageTemplat();
	}

	public BizTaskPackageTemplat getByNo(String no) {

		return bizTaskPackageTemplatDao.getByNo(no);
	}
	
	public List<BizTaskPackageTemplat> getByNo1(String no) {

		return bizTaskPackageTemplatDao.getByNo1(no);
	}
	

	public List<BizTaskPackageTemplat> queryByStoreId(Integer storeid,Integer projectMode){
		return bizTaskPackageTemplatDao.queryByStoreId(storeid,projectMode);
	}


	public List<DropModel> queryTaskPackageByStoreIdAndProjectMode(String storeId,String projectMode){
		BizTaskPackageTemplat param = new BizTaskPackageTemplat();
        param.setStoreId(storeId);
        param.setProjectMode(projectMode);
		param.setStatus("1");
		List<BizTaskPackageTemplat> list = bizTaskPackageTemplatDao.findList(param);
		List<DropModel> taskList = new ArrayList<DropModel>();
		for (BizTaskPackageTemplat task : list) {
			taskList.add(new DropModel(task.getTemplatName(), task.getId()));
		}
		return taskList;
	}

	public List<DropModel> findtaskpackageByStroeId(Integer storeid,String status) {

		return bizTaskPackageTemplatDao.findtaskpackageByStroeId(storeid,status);
	}

	public List<DropModel> findtaskpackageByStroeId1(String status) {

		return bizTaskPackageTemplatDao.findtaskpackageByStroeId1(status);
	}

	public List<DropModel> getTaskList(BizEmployee bizEmployee) {
		 List<BizTaskPackageTemplat> list = bizTaskPackageTemplatDao.getTaskList(bizEmployee);
	        List<DropModel> taskList = new ArrayList<DropModel>();
	        for (BizTaskPackageTemplat task : list) {
	            taskList.add(new DropModel(task.getTemplatName(), task.getId()));
	        }
		return taskList;
	}


	public Integer checkEmpWorkType(BizTaskPackageTemplat bizTaskPackageTemplat) {
		return dao.checkEmpWorkType(bizTaskPackageTemplat);
	}
	
	
	
}