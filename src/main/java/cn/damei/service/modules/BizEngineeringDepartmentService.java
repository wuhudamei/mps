
package cn.damei.service.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizEngineeringDepartment;
import cn.damei.entity.modules.EngineeringDepartmentEmployeePosition;
import cn.damei.dao.modules.BizEngineeringDepartmentDao;
import cn.damei.dao.modules.EngineeringDepartmentEmployeePositionDao;


@Service
@Transactional(readOnly = true)
public class BizEngineeringDepartmentService extends CrudService<BizEngineeringDepartmentDao, BizEngineeringDepartment> {

	@Resource
	private BizEngineeringDepartmentDao bizEngineeringDepartmentDao;
	@Autowired
	private EngineeringDepartmentEmployeePositionDao engineeringDepartmentEmployeePositionDao;
	
	public BizEngineeringDepartment get(String id) {
		return super.get(id);
	}
	
	public List<BizEngineeringDepartment> findList(BizEngineeringDepartment bizEngineeringDepartment) {
		return super.findList(bizEngineeringDepartment);
	}
	
	public Page<BizEngineeringDepartment> findPage(Page<BizEngineeringDepartment> page, BizEngineeringDepartment bizEngineeringDepartment) {
		return super.findPage(page, bizEngineeringDepartment);
	}
	
	@Transactional(readOnly = false)
	public void save(BizEngineeringDepartment bizEngineeringDepartment) {
		super.save(bizEngineeringDepartment);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizEngineeringDepartment bizEngineeringDepartment) {
		engineeringDepartmentEmployeePositionDao.deleteByDepartmentId(Integer.parseInt(bizEngineeringDepartment.getId()));
		super.delete(bizEngineeringDepartment);
	}
	
	public List<BizEngineeringDepartment> findByStoreId(String storeId)
	{
		return bizEngineeringDepartmentDao.findByStoreId(storeId);
	}

	public List<DropModel> findEngDepListByStoreId(String storeId) {

		return bizEngineeringDepartmentDao.findEngDepListByStoreId(storeId);
	}

	public List<DropModel> findEngDepListByStoreId1() {

		return bizEngineeringDepartmentDao.findEngDepListByStoreId1();
	}
	
	

	
	
	public List<EngineeringDepartmentEmployeePosition> getPositonList(BizEngineeringDepartment bizEngineeringDepartment){
		List<EngineeringDepartmentEmployeePosition> list = new ArrayList<EngineeringDepartmentEmployeePosition>();
		if(!StringUtils.isBlank(bizEngineeringDepartment.getBudgeteer())){
			String[] split = bizEngineeringDepartment.getBudgeteer().split(",");
			for(int i = 0; i<split.length; i++){
				EngineeringDepartmentEmployeePosition position = new EngineeringDepartmentEmployeePosition();
				position.setEnginDepartId(Integer.parseInt(bizEngineeringDepartment.getId()));
				position.setEmployeeId(Integer.parseInt(split[i]));
				position.setPositionType("8");
				position.preInsert();
				list.add(position);
			}
		}
		
		if(!StringUtils.isBlank(bizEngineeringDepartment.getReceptionist())){
			String[] split = bizEngineeringDepartment.getReceptionist().split(",");
			for(int i = 0; i<split.length; i++){
				EngineeringDepartmentEmployeePosition position = new EngineeringDepartmentEmployeePosition();
				position.setEnginDepartId(Integer.parseInt(bizEngineeringDepartment.getId()));
				position.setEmployeeId(Integer.parseInt(split[i]));
				position.setPositionType("6");
				position.preInsert();
				list.add(position);
			}
		}
		
		if(!StringUtils.isBlank(bizEngineeringDepartment.getSettlementClerk())){
			String[] split = bizEngineeringDepartment.getSettlementClerk().split(",");
			for(int i = 0; i<split.length; i++){
				EngineeringDepartmentEmployeePosition position = new EngineeringDepartmentEmployeePosition();
				position.setEnginDepartId(Integer.parseInt(bizEngineeringDepartment.getId()));
				position.setEmployeeId(Integer.parseInt(split[i]));
				position.setPositionType("9");
				position.preInsert();
				list.add(position);
			}
		}
		if(!StringUtils.isBlank(bizEngineeringDepartment.getOrderDispatcher())){
			String[] split = bizEngineeringDepartment.getOrderDispatcher().split(",");
			for(int i = 0; i<split.length; i++){
				EngineeringDepartmentEmployeePosition position = new EngineeringDepartmentEmployeePosition();
				position.setEnginDepartId(Integer.parseInt(bizEngineeringDepartment.getId()));
				position.setEmployeeId(Integer.parseInt(split[i]));
				position.setPositionType("7");
				position.preInsert();
				list.add(position);
			}
		}
		
		if(!StringUtils.isBlank(bizEngineeringDepartment.getLeader())){
			EngineeringDepartmentEmployeePosition position = new EngineeringDepartmentEmployeePosition();
			position.setEnginDepartId(Integer.parseInt(bizEngineeringDepartment.getId()));
			position.setEmployeeId(Integer.parseInt(bizEngineeringDepartment.getLeader()));
			position.setPositionType("1");
			position.preInsert();
			list.add(position);
		}
		
		if(!StringUtils.isBlank(bizEngineeringDepartment.getJobDispatcher())){
			String[] split = bizEngineeringDepartment.getJobDispatcher().split(",");
			for(int i = 0; i<split.length; i++){
				EngineeringDepartmentEmployeePosition position = new EngineeringDepartmentEmployeePosition();
				position.setEnginDepartId(Integer.parseInt(bizEngineeringDepartment.getId()));
				position.setEmployeeId(Integer.parseInt(split[i]));
				position.setPositionType("2");
				position.preInsert();
				list.add(position);
			}
		}
		
		if(!StringUtils.isBlank(bizEngineeringDepartment.getMaterialDispatcher())){
			String[] split = bizEngineeringDepartment.getMaterialDispatcher().split(",");
			for(int i = 0; i<split.length; i++){
				EngineeringDepartmentEmployeePosition position = new EngineeringDepartmentEmployeePosition();
				position.setEnginDepartId(Integer.parseInt(bizEngineeringDepartment.getId()));
				position.setEmployeeId(Integer.parseInt(split[i]));
				position.setPositionType("3");
				position.preInsert();
				list.add(position);
			}
		}
		
		if(!StringUtils.isBlank(bizEngineeringDepartment.getInspector())){
			String[] split = bizEngineeringDepartment.getInspector().split(",");
			for(int i = 0; i<split.length; i++){
				EngineeringDepartmentEmployeePosition position = new EngineeringDepartmentEmployeePosition();
				position.setEnginDepartId(Integer.parseInt(bizEngineeringDepartment.getId()));
				position.setEmployeeId(Integer.parseInt(split[i]));
				position.setPositionType("4");
				position.preInsert();
				list.add(position);
			}
		}
		
		if(!StringUtils.isBlank(bizEngineeringDepartment.getManager())){
			String[] split = bizEngineeringDepartment.getManager().split(",");
			for(int i = 0; i<split.length; i++){
				EngineeringDepartmentEmployeePosition position = new EngineeringDepartmentEmployeePosition();
				position.setEnginDepartId(Integer.parseInt(bizEngineeringDepartment.getId()));
				position.setEmployeeId(Integer.parseInt(split[i]));
				position.setPositionType("5");
				position.preInsert();
				list.add(position);
			}
		}
		return list;
	}
	
	@Transactional(readOnly = false)
	public void insert(BizEngineeringDepartment bizEngineeringDepartment) {
		dao.insert(bizEngineeringDepartment);
		List<EngineeringDepartmentEmployeePosition> positions = getPositonList(bizEngineeringDepartment);
		engineeringDepartmentEmployeePositionDao.insertList(positions);
	}
	@Transactional(readOnly = false)
	public void update(BizEngineeringDepartment bizEngineeringDepartment) {
		dao.update(bizEngineeringDepartment);
		List<EngineeringDepartmentEmployeePosition> positions = getPositonList(bizEngineeringDepartment);
		engineeringDepartmentEmployeePositionDao.deleteByDepartmentId(Integer.parseInt(bizEngineeringDepartment.getId()));
		engineeringDepartmentEmployeePositionDao.insertList(positions);
	}

	public List<DropModel> findEngDepListByMap(Map<String, Object> map) {

		return dao.findEngDepListByMap(map);
	}

	public List<Integer> findByEmployeeId(Integer employeeId) {
		
		return dao.findByEmployeeId(employeeId);
	}

	public List<Integer> findAll() {

		return dao.findAll();
	}
}