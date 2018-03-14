
package cn.damei.service.modules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.damei.dao.modules.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.entity.modules.Order2;
import cn.damei.entity.modules.GroupSendMessage;
import cn.damei.entity.modules.OrderTaskpack;
import cn.damei.entity.modules.OrderTaskpackGenVo;
import cn.damei.entity.modules.TaskpackageTemplat;
import cn.damei.entity.modules.OrderTaskpackageProcedure;
import cn.damei.entity.modules.OrderTaskpackage;
import cn.damei.entity.modules.TeamLeaderInfo;
import cn.damei.common.utils.UserUtils;
import cn.damei.dao.modules.SeiralnumDao;
import cn.damei.dao.modules.BizTaskPackageWorkPlanTemplatRelDao;
import cn.damei.entity.modules.BizTaskPackageTemplat;
import cn.damei.entity.modules.BizTaskPackageType;
import cn.damei.entity.modules.BizTaskPackageWorkPlanTemplatRel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service
@Transactional(readOnly = true)
public class OrderTaskpackageService extends CrudService<OrderTaskpackageDao, OrderTaskpackage> {
	
	@Autowired
	private OrderTaskpackageDao orderTaskpackageDao;
	@Autowired
	private OrderTaskpackDao orderTaskpackDao;
	@Autowired
	private BizTaskPackageTemplatDao bizTaskPackageTemplatDao;
	@Autowired
	private BizTaskPackageTypeDao bizTaskPackageTypeDao;
	@Autowired
	private BizTaskPackageWorkPlanTemplatRelDao bizTaskPackageWorkPlanTemplatRelDao;
	@Autowired
	private SeiralnumDao seiralnumDao;
	@Autowired
	private BizSeiralnumService bizSeiralnumService;
	@Autowired
	private OrderTaskpackageProcedureService orderTaskpackageProcedureService;
	@Autowired
	private OrderTaskpackService orderTaskpackService;
	@Autowired
	private OrderDao2 orderDao2;
	@Autowired
	private TaskpackageTemplatDao taskpackageTemplatDao;
	@Autowired
	private BizProcedureDao bizProcedureDao;
	
	public OrderTaskpackage get(String id) {
		return super.get(id);
	}
	
	public List<OrderTaskpackage> findList(OrderTaskpackage orderTaskpackage) {
		return super.findList(orderTaskpackage);
	}
	
	public Page<OrderTaskpackage> findPage(Page<OrderTaskpackage> page, OrderTaskpackage orderTaskpackage) {
		return super.findPage(page, orderTaskpackage);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderTaskpackage orderTaskpackage) {
		super.save(orderTaskpackage);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderTaskpackage orderTaskpackage) {
		super.delete(orderTaskpackage);
	}
	

	@Transactional(readOnly = false)
	public void update(OrderTaskpackage orderTaskpackage){
		super.save(orderTaskpackage);
	}
	


	public Page<OrderTaskpackage> findPageMy(Page<OrderTaskpackage> page, OrderTaskpackage orderTaskpackage){
		orderTaskpackage.setPage(page);
		page.setList(dao.findListMy(orderTaskpackage));
		return page;
	}
	


	public TeamLeaderInfo  findTeamLeaderInfoByEmployeeGroupId(String employeeGroupId){
		
		return  dao.findTeamLeaderInfoByEmployeeGroupId(employeeGroupId);
		
	}


	public  String findTaskPackageByemployeeGroupId(String employeeGroupId){
		return dao.findTaskPackageByemployeeGroupId(employeeGroupId);
	}
	

	public String findPackageNameById(String packAgeId){
		return dao.findPackageNameById(packAgeId);
	}
	


	public Integer findCountByWorkerId(String packAgeId){
		
		return dao.findCountByWorkerId(packAgeId);
	}



	@Transactional(readOnly = false)
	public boolean insertTaskpackageByOrder(String orderId,String storeId,String projectMode,String templatNumber,Double total, Double laborTotal,Double auxiliaryTotal) throws ParseException {
		BizTaskPackageTemplat taskPackageTem = bizTaskPackageTemplatDao.getByprocedureNo(templatNumber);
		BizTaskPackageType btaskPackType = bizTaskPackageTypeDao.getById(templatNumber);
		
		OrderTaskpack order = orderTaskpackDao.getByOrderId(Integer.valueOf(orderId));
		
		BizTaskPackageWorkPlanTemplatRel workPlanTemRel = bizTaskPackageWorkPlanTemplatRelDao.
				getByStroeIdAndDelflag(storeId,order.getHouseIsNew(),templatNumber,order.getProjectMode());
		boolean flag = false;
		OrderTaskpackage ot = new OrderTaskpackage();
		ot.setId(null);
		ot.setOrderId(orderId);
		ot.setStoreId(storeId);
		ot.setProjectMode(projectMode);
		ot.setPackageCode(templatNumber);
		ot.setPackageName(taskPackageTem.getTemplatName());
		
		if(null != order && 
				null != workPlanTemRel && workPlanTemRel.getBeginDay()!=null){
			ot.setPlanStartdate(DateUtils.addDate(order.getContractStartDate(),
					Integer.valueOf(workPlanTemRel.getBeginDay())));
			ot.setPlanEnddate(DateUtils.addDate(order.getContractStartDate(),
					Integer.valueOf(workPlanTemRel.getEndDay())));
		}else{
			ot.setPlanStartdate(null);
			ot.setPlanEnddate(null);
		}
		ot.setActualStartdate(null);
		ot.setActualEnddate(null);
		ot.setPackageStateId(ConstantUtils.ORDERTASKPACKAGE_ID_CREATE);
		ot.setPackageStatename(ConstantUtils.ORDERTASKPACKAGE_ID_CREATE_REMARKS);
		ot.setEmpGroupid(null);
		ot.setGroupId(null);
		ot.setGroupRealname(null);
		ot.setCreateBy(UserUtils.getUser());
		ot.setCreateDate(new Date());
		ot.setUpdateBy(UserUtils.getUser());
		ot.setUpdateDate(new Date());
		ot.setDelFlag("0");
		ot.setItemCustomer(order.getItemManager());
		ot.setTaskPackageType(btaskPackType.getId());
		ot.setDispatcher(null);
		ot.setCustomerName(order.getCustomerName());
		ot.setCustomerMessage(order.getCommunityName() + "-" +order.getBuildNumber() + "-" +
				order.getBuildUnit() + "-" + order.getBuildRoom());
		ot.setDispatchTime(null);
		ot.setIsOvertime("0");
		ot.setLaborAuxiliaryMaterialsBudgetAmount(total);
		ot.setLaborBudgetAmount(laborTotal);
		ot.setAuxiliaryMaterialsBudgetAmount(auxiliaryTotal);
		ot.setItemManagerId(order.getItemManagerId());
		ot.setTaskTackageTempId(Integer.valueOf(taskPackageTem.getId()));
		ot.setOrderTaskPackageCode(bizSeiralnumService.getDateSequence("RW"));
		ot.setSettleStyle(taskPackageTem.getSettleStyle());
		
		flag = (orderTaskpackageDao.insertTaskpackageByOrder(ot)) ? true : false;
		return flag;
	}


	public int queryListByOrderTaskpackage(String orderId,String storeId,String projectMode) {
		List<OrderTaskpackage> list = orderTaskpackageDao.queryListByOrderTaskpackage(orderId,storeId,projectMode);
		return list.size();
	}


	public List<OrderTaskpackage> getByOrderId(String orderId) {
		List<OrderTaskpackage> taskPackage = orderTaskpackageDao.getByOrderId(orderId);
		return taskPackage;
	}
public static void main(String[] args) throws ParseException {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	System.out.println(sdf.format(new Date()));
	System.out.println(DateUtils.formatDate(new Date(), "yyyyMMdd"));
}

	public String findOrderNumber(Integer id) {
		return dao.findOrderNumber(id);
	}

	public List<GroupSendMessage> getByNewDate(String beforeDate) {
		logger.info("传过来的日期：" + beforeDate);
		return orderTaskpackageDao.getByNewDate(beforeDate);
	}

	public List<OrderTaskpackage> getByOrderIDList(Integer valueOf) {
		return orderTaskpackageDao.getByOrderIDList(valueOf);
	}
	
	@Transactional(readOnly = false)
	public void insertTaskpackage(OrderTaskpackage taskpackage) {
		
		orderTaskpackageDao.insertTaskpackage(taskpackage);
	}

	public OrderTaskpackage findPackageByCode(String code) {
		return orderTaskpackageDao.findPackageByCode(code);
	}

	@Transactional(readOnly = false)
	public void insertProcedure(OrderTaskpackGenVo vo) {
		orderTaskpackageDao.insertProcedure(vo);
	}

	@Transactional(readOnly = false)
	public void insertProcedureList(List<OrderTaskpackGenVo> list) {
		orderTaskpackageDao.insertProcedureList(list);
	}
	
	@Transactional(readOnly = false)
	public String createTaskpackage(OrderTaskpackGenVo orderTaskpack,String orderId,String storeId,String projectMode,String json1) throws Exception{
		String resultCode = "0";
		boolean flag = false;
		boolean flagOrderTaskpackage = false;
		boolean flagOrderTaskpackageProceude = false;
		Map<String,String> map = new HashMap<String,String>();
		JSONObject json = JSONObject.fromObject(json1.toString());
		

		Iterator iter = json.keySet().iterator();  
		while (iter.hasNext()) {    
            String key = (String) iter.next();    
            String value = json.getString(key);    
            map.put(key, value);
        }
		String value=map.get("objson");
		JSONArray jsonArray =JSONArray.fromObject(value);
        List<String> tmpList=new ArrayList<String>();
        List<OrderTaskpackageProcedure> otpList = new ArrayList<OrderTaskpackageProcedure>();
        List<OrderTaskpackage> orderTp = new ArrayList();
        for (int i = 0; i <jsonArray.size(); i++) { 
        	OrderTaskpackageProcedure orderTaskpackageProcedure =  new OrderTaskpackageProcedure();
        	OrderTaskpackage taskpack = new OrderTaskpackage();
            String templatNumber = (String) jsonArray.getJSONObject(i).get("templatNumber");
            taskpack.setPackageCode(templatNumber);
        	orderTaskpackageProcedure.setTaskpackageNumber(templatNumber);
        	orderTaskpackageProcedure.setOrderId(orderId);
        	orderTaskpackageProcedure.setProjectMode(projectMode);
        	taskpack.setOrderId(orderId);
        	taskpack.setProjectMode(projectMode);
        	orderTaskpackageProcedure.setProcedureNo((String) jsonArray.getJSONObject(i).get("procedureNo"));
        	if(((String) jsonArray.getJSONObject(i).get("budgetNumber")).equals("")){
        		orderTaskpackageProcedure.setBudgetNumber(0.00);
        	}else{
        		orderTaskpackageProcedure.setBudgetNumber((
            			(Double.valueOf((String) jsonArray.getJSONObject(i).get("budgetNumber")))
            	));
        	}

        	if(((String) jsonArray.getJSONObject(i).get("remarks")).equals("")){
        		orderTaskpackageProcedure.setRemarks(null);
        	}else{
        		orderTaskpackageProcedure.setRemarks(((String)jsonArray.getJSONObject(i).get("remarks")));
        	}

        	if(((String) jsonArray.getJSONObject(i).get("total")).equals("")){

        		taskpack.setLaborAuxiliaryMaterialsBudgetAmount(null);
        	}else{



        		taskpack.setLaborAuxiliaryMaterialsBudgetAmount(Double.valueOf((String) jsonArray.getJSONObject(i).get("total")));
        	}
        	

        	if (((String) jsonArray.getJSONObject(i).get("laborBudgetAmount")).equals("")) {

				taskpack.setLaborBudgetAmount(0.00);
			} else {

				taskpack.setLaborBudgetAmount((Double.valueOf((String) jsonArray.getJSONObject(i).get("laborBudgetAmount"))).doubleValue());
			}
        	

        	if (((String)jsonArray.getJSONObject(i).getString("auxiliaryMaterialsBudgetAmount")).equals("")) {

        		taskpack.setAuxiliaryMaterialsBudgetAmount(0.00);
			} else {

				taskpack.setAuxiliaryMaterialsBudgetAmount((Double.valueOf((String) jsonArray.getJSONObject(i).get("auxiliaryMaterialsBudgetAmount"))).doubleValue());
			}
        	
        	orderTp.add(taskpack);
        	otpList.add(orderTaskpackageProcedure);
        	tmpList.add(templatNumber);
	    }  
        

        List<String> checkList= new ArrayList<String>(new HashSet<String>(tmpList));
        int orderTaskpackageListSize =  dao.queryListByOrderTaskpackage(orderId,storeId,projectMode).size();
        
        Map<String,Double> mapTotal = new HashMap<String,Double>();
        

        for(OrderTaskpackage pa : orderTp){
        	String packageCode = pa.getPackageCode();
    		if(mapTotal.get(pa.getPackageCode()) == null){
    			mapTotal.put(packageCode, Double.valueOf(pa.getLaborAuxiliaryMaterialsBudgetAmount()));
    			continue;
    		}
    		
    		mapTotal.put(packageCode,Double.valueOf(pa.getLaborAuxiliaryMaterialsBudgetAmount()) + mapTotal.get(packageCode));
        }
        

        Map<String,Double> mapLaborTotal = new HashMap<String,Double>();
        for(OrderTaskpackage pa : orderTp){
        	String packageCode = pa.getPackageCode();
    		if(mapLaborTotal.get(pa.getPackageCode()) == null){
    			mapLaborTotal.put(packageCode, Double.valueOf(pa.getLaborBudgetAmount()));
    			continue;
    		}
    		mapLaborTotal.put(packageCode,Double.valueOf(pa.getLaborBudgetAmount()) + mapLaborTotal.get(packageCode));
        }
        

        Map<String,Double> mapAuxiliaryTotal = new HashMap<String,Double>();
        for(OrderTaskpackage pa : orderTp){
        	String packageCode = pa.getPackageCode();
    		if(mapAuxiliaryTotal.get(pa.getPackageCode()) == null){
    			mapAuxiliaryTotal.put(packageCode, Double.valueOf(pa.getAuxiliaryMaterialsBudgetAmount()));
    			continue;
    		}
    		mapAuxiliaryTotal.put(packageCode,Double.valueOf(pa.getAuxiliaryMaterialsBudgetAmount()) + mapAuxiliaryTotal.get(packageCode));
        }
        
        if(orderTaskpackageListSize <= 0){

            if(checkList.size()>0){
            	for(Entry<String, Double> entry:mapTotal.entrySet()){
	            	for(String templatNumber: checkList){
	            		if(templatNumber.equals(entry.getKey())){
	            			flagOrderTaskpackage = insertTaskpackageByOrder(orderId,storeId,projectMode,templatNumber,entry.getValue(),mapLaborTotal.get(entry.getKey()),mapAuxiliaryTotal.get(entry.getKey()));
			    			if(flagOrderTaskpackage == false){
			    				return resultCode = "1";
			    			}
	            		}
	            	}
            	}
            }
            
            List<OrderTaskpackage> orderTaskpackageList= dao.getByOrderId(orderId);
            
            if(!resultCode.equals("2")){
	            for(OrderTaskpackageProcedure otp : otpList){
	            	for(OrderTaskpackage orderTaskpackage : orderTaskpackageList){
	            		if(otp.getOrderId().equals(orderTaskpackage.getOrderId()) && 
	            				otp.getTaskpackageNumber().equals(orderTaskpackage.getPackageCode())){
	            			otp.setTaskpackageId(Long.parseLong(orderTaskpackage.getId()));
	            			otp.setTaskpackageNumber(orderTaskpackage.getPackageCode());
	            		}
	            	}
	            	
	            }
	            

	            for(OrderTaskpackageProcedure tp:otpList){
	            	flagOrderTaskpackageProceude = orderTaskpackageProcedureService.insertByOrder1(tp);
	            	if(flagOrderTaskpackageProceude == false){
	            		return resultCode = "2";
	            	}
	            }
            }
        }else{
        	resultCode = "3";
        }

        if(resultCode.equals("0")){
        	flag = orderTaskpackService.updateByOrderStatusAndTaskpackageStatus(ConstantUtils.ORDERTASKPACKAGE_STATUS_NUMBER_110,
        			ConstantUtils.ORDERTASKPACKAGE_STATUS_NUMBER_110_REMARKS,ConstantUtils.ORDERTASKPACKAGE_STATUS_YES,orderId);
        	if(flag == false){
        		return "4";
        	}else{
        		return "0";
        	}
        }
        
		return resultCode;
	}


	public int getUnOrderTaskPackage(OrderTaskpackage orderTaskpackage) {
		return orderTaskpackageDao.getUnOrderTaskPackage(orderTaskpackage);
	}

	public Page<OrderTaskpackage> findSpecialPageMy(Page<OrderTaskpackage> page, OrderTaskpackage orderTaskpackage) {
		orderTaskpackage.setPage(page);
		page.setList(dao.findSpecialPageMy(orderTaskpackage));
		return page;
	}
	

	@Transactional(readOnly = false)
	public String createSpecialTaskPackage(String packageName,String parmeters,String startDate,String endDate,Integer orderId) throws Exception{
		String flag = "error";
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
		Date startDate1 = sdf.parse(startDate);
		Date endDate1 = sdf.parse(endDate);
		Order2 order = orderDao2.get(orderId);

		TaskpackageTemplat taskpackageTemplat = taskpackageTemplatDao.findByStoreId(Integer.parseInt(order.getStoreId()),Integer.parseInt(order.getProjectMode()));
		if(taskpackageTemplat == null || taskpackageTemplat.getId() == null){
			return "1";
		}
		OrderTaskpackage taskpackage = new OrderTaskpackage();
		taskpackage.setOrderId(order.getId().toString());
		taskpackage.setStoreId(order.getStoreId());
		taskpackage.setProjectMode(order.getProjectMode());
		taskpackage.setPackageCode(taskpackageTemplat.getNo());
		taskpackage.setPackageName(packageName);
		taskpackage.setPlanStartdate(startDate1);
		taskpackage.setPlanEnddate(endDate1);
		taskpackage.setPackageStateId(ConstantUtils.ORDERTASKPACKAGE_ID_CREATE);
		taskpackage.setPackageStatename(ConstantUtils.ORDERTASKPACKAGE_ID_CREATE_REMARKS);
		Integer managerId = order.getItemManagerId();
		if(managerId == null){
			taskpackage.setItemManagerId(null);
			taskpackage.setItemCustomer(null);
		}else{
			taskpackage.setItemManagerId(order.getItemManagerId().toString());
			taskpackage.setItemCustomer(order.getItemManager());
		}
		taskpackage.setCustomerName(order.getCustomerName());
		taskpackage.setCustomerPhone(order.getCustomerPhone());
		taskpackage.setCustomerMessage(order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom());
		taskpackage.setTaskTackageTempId(taskpackageTemplat.getId());
		String code = bizSeiralnumService.getDateSequence("RW");
		taskpackage.setOrderTaskPackageCode(code);
		taskpackage.setTaskPackageType(taskpackageTemplat.getTaskPackageTypeId());
		taskpackage.setIsOvertime("0");
		Double total = 0.0;
		Double laborBudgetAmount = 0.0;
		Double auxiliaryMaterialsBudgetAmount = 0.0;
		List<OrderTaskpackGenVo> list = new ArrayList<OrderTaskpackGenVo>();
		String[] parmeter = parmeters.split("####");
		for (String parme : parmeter) {
			String[] split = parme.split("-");
			OrderTaskpackGenVo taskpackageProcedure = bizProcedureDao.findTaskpackageProcedureById(split[0],order.getStoreId(),order.getContractStartDate());
			taskpackageProcedure.setBudgetNumber(split[1]);
			taskpackageProcedure.setLaborAuxiliaryMaterialsBudgetAmount(split[2]);
			taskpackageProcedure.setRemarks(split[3]);
			taskpackageProcedure.setProjectMode(order.getProjectMode());
			taskpackageProcedure.setLaborBudgetAmount(String.valueOf(split[4]));
			taskpackageProcedure.setAuxiliaryMaterialsBudgetAmount(String.valueOf(split[5]));
			total = total+ Double.parseDouble(split[2]);
			laborBudgetAmount = laborBudgetAmount + Double.parseDouble(split[4]);
			auxiliaryMaterialsBudgetAmount = auxiliaryMaterialsBudgetAmount + Double.parseDouble(split[5]);
			list.add(taskpackageProcedure);
		}
		taskpackage.setLaborAuxiliaryMaterialsBudgetAmount(total);
		taskpackage.setLaborBudgetAmount(laborBudgetAmount);
		taskpackage.setAuxiliaryMaterialsBudgetAmount(auxiliaryMaterialsBudgetAmount);
		taskpackage.setSettleStyle(taskpackageTemplat.getSettleStyle());
		taskpackage.preInsert();
		orderTaskpackageDao.insertTaskpackage(taskpackage);
		OrderTaskpackage tp = orderTaskpackageDao.findPackageByCode(code);
		for(OrderTaskpackGenVo vo :list){
			vo.setTaskpackageId(tp.getId());
			vo.setPackageName(tp.getPackageName());
			vo.preInsert();

		}
		orderTaskpackageDao.insertProcedureList(list);
		flag = "success";
		return flag;
	}

}