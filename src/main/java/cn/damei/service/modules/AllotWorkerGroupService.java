package cn.damei.service.modules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import cn.damei.entity.modules.BizOrderDistributeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.DisributeLogConstantUtil;
import cn.damei.common.constantUtils.OrderTaskpackageConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.MessagePushType;
import cn.damei.common.utils.phoneMessage.PhoneMessageDao;
import cn.damei.common.utils.phoneMessage.PhoneMessageEntity;
import cn.damei.dao.modules.BizOrderDistributeLogDao;
import cn.damei.entity.modules.BizMsg;
import cn.damei.service.modules.BizProjectChangeBillService;
import cn.damei.dao.modules.BizEmployeeDao;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.OrderTaskpackage;
import cn.damei.common.utils.UserUtils;
import cn.damei.dao.modules.AllotWorkerGroupDao;
import cn.damei.entity.modules.WorkgroupVo;


@Service
@Transactional(readOnly=true)
public class AllotWorkerGroupService extends CrudService<AllotWorkerGroupDao, WorkgroupVo> {

	public OrderTaskpackage findTargetPackageById(Integer id) {

		return dao.findTargetPackageById(id);
	}


	public Integer findCountByWorkerId(Integer id) {

		return dao.findCountByWorkerId(id);
	}


	public String findTeamLeaderNameById(Integer id) {

		return dao.findTeamLeaderNameById(id);
	}


	public String findTeamLeaderPhoneById(Integer id) {

		return dao.findTeamLeaderPhoneById(id);
	}


	public String findengineerById(String id) {

		return dao.findengineerById(id);
	}


	@Autowired
	private PhoneMessageDao messageDao;
	@Autowired
	private BizProjectChangeBillService bizProjectChangeBillDao;
	@Autowired
	private BizOrderDistributeLogDao bizOrderDistributeLogDao;
	@Autowired
	private BizEmployeeDao bizEmployeeDao;
	@Autowired
	private AllotWorkerGroupDao allotWorkerGroupDao;
	
	@Transactional(readOnly = false)
	public void updatePackage(String packageId,WorkgroupVo workerGroup,String startDate,String endDate,String isRedistribute,String reasonRemarks,String reasonType, boolean isSpecial) throws ParseException {
		

		boolean flag = false;

		OrderTaskpackage packageById = dao.findTargetPackageById(Integer.valueOf(packageId));

		String oldGroupId = packageById.getGroupId();

		String planStartDate = "";
		String planEndDate = "";

		if (null == packageById.getPlanStartdate()) {

			packageById.setPlanStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));

		} else if (null == packageById.getPlanEnddate()) {

			packageById.setPlanEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
		} else {

			planStartDate = new SimpleDateFormat("yyyy-MM-dd").format(packageById.getPlanStartdate());
			planEndDate = new SimpleDateFormat("yyyy-MM-dd").format(packageById.getPlanEnddate());

		}


		if (!planStartDate.equals(startDate) || !planEndDate.equals(endDate)) {
			packageById.setPlanStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));
			packageById.setPlanEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
			flag = true;
		}

		packageById.setDispatchTime(new Date());
		if(null!=isRedistribute && "1".equals(isRedistribute)){
			
			if(Integer.parseInt(packageById.getPackageStateId())>=60){

			}else{

				packageById.setPackageStatename(OrderTaskpackageConstantUtil.ORDER_TASKPACKAGE_STATUS_NAME_50);
				packageById.setPackageStateId(OrderTaskpackageConstantUtil.ORDER_TASKPACKAGE_STATUS_50);
				
				
			}
			
		}else{


			packageById.setPackageStatename(OrderTaskpackageConstantUtil.ORDER_TASKPACKAGE_STATUS_NAME_50);
			packageById.setPackageStateId(OrderTaskpackageConstantUtil.ORDER_TASKPACKAGE_STATUS_50);
		}
		

		packageById.setGroupRealname(dao.findTeamLeaderNameById(Integer.valueOf(workerGroup.getGroupId())));

		packageById.setGroupId(workerGroup.getGroupId());

		packageById.setEmpGroupid(workerGroup.getId());

		dao.updatePackage(packageById);
		
		
		

		BizOrderDistributeLog dl = new  BizOrderDistributeLog();
		
		dl.setOrderId(Integer.parseInt(packageById.getOrderId()));
		
		dl.setOrderTaskpackageId(Integer.parseInt(packageById.getId()));
		
		if(null!=isRedistribute && "1".equals(isRedistribute)){

			dl.setDistributeType(isSpecial?ConstantUtils.DISTRIBUTE_TYPE_305:DisributeLogConstantUtil.DISTRIBUTE_TYPE_302);

			dl.setDistributedEmployeeId(Integer.parseInt(packageById.getGroupId()));

			dl.setOldEmployeeId(Integer.parseInt(oldGroupId));

			dl.setUpdateDate(new Date());

			dl.setUpdateBy(UserUtils.getUser());

			dl.setReasonRemarks(reasonRemarks);

			dl.setReasonType(reasonType);
			

			BizEmployee bizEmployee = bizEmployeeDao.selectExchangeOrderTimesById(Integer.parseInt(oldGroupId));

			if(bizEmployee.getExchangeOrderTimes()==null || bizEmployee.getExchangeOrderTimes()==0){
				bizEmployee.setExchangeOrderTimes(1);
			}else{
				Integer times = bizEmployee.getExchangeOrderTimes();

				bizEmployee.setExchangeOrderTimes(times+=1);
			}
			bizEmployee.setUpdateBy(UserUtils.getUser());
			bizEmployee.setUpdateDate(new Date());

			bizEmployeeDao.updateExchangeOrderTimes(bizEmployee);
			
		}else{

			dl.setDistributeType(isSpecial?ConstantUtils.DISTRIBUTE_TYPE_304:DisributeLogConstantUtil.DISTRIBUTE_TYPE_301);

			dl.setDistributedEmployeeId(Integer.parseInt(packageById.getGroupId()));
			
		}
		dl.preInsert();

		dl.setDistributedGroupId(Integer.parseInt(workerGroup.getId()));
		dl.setDelFlag("0");

		bizOrderDistributeLogDao.insert(dl);
		
		



		String workerLeaderPhone = dao
				.findTeamLeaderPhoneById(Integer.parseInt(workerGroup.getGroupId()));

		OrderTaskpackage info = dao.sendMessagetoWorker(Integer.parseInt(packageId));


		PhoneMessageEntity entity = new PhoneMessageEntity();
		entity.setMessageGenerateTime(new Date());
		entity.setReceiveEmployeeId(Integer.parseInt(workerGroup.getGroupId()));
		entity.setMessageContent("订单（" + info.getXiaoqu() + "-" + info.getLou() + "-" + info.getDanyuan() + "-"
				+ info.getShi() + "-" + info.getCustomerName() + "-" + info.getCustomerPhone() + ") 的任务包("
				+ info.getPackageName() + ")，已发送给您，请登录APP(我要接单)中接收，我们为您保留60分钟。 ");
		entity.setReceivePhone(workerLeaderPhone);
		entity.setRelatedBusinessId(Integer.parseInt(packageId));
		entity.setRelatedBusinessType("200601");
		entity.setStatus("0");
		messageDao.saveMessageContent(entity);






		BizMsg bizMsg = new BizMsg();
		bizMsg.setMsgTitle("分配任务包");
		bizMsg.setMsgTime(new Date());
		bizMsg.setMsgContent("订单（" + info.getXiaoqu() + "-" + info.getLou() + "-" + info.getDanyuan() + "-"
				+ info.getShi() + "-" + info.getCustomerName() + "-" + info.getCustomerPhone() + ") 的任务包("
				+ info.getPackageName() + ")，已发送给您，请在【我要接单】中接收，我们为您保留60分钟");
		bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
		bizMsg.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_100009002);
		bizMsg.setBusiIdInt(Integer.parseInt(packageId));
		bizMsg.setEmployeeId((Integer.parseInt(workerGroup.getGroupId())));
		bizProjectChangeBillDao.saveBizMsg(bizMsg);





		if (flag) {

		}


	
	}

	public List<OrderTaskpackage> findAllPackageWhomHasEmpGroup(List<WorkgroupVo> workgroupVo){
		return dao.findAllPackageWhomHasEmpGroup( workgroupVo);
	}
	

	public WorkgroupVo  getWorkgroupById(Integer workGroupId){
		return dao.getWorkgroupById(workGroupId);
	}
	
	
	

	public  OrderTaskpackage	sendMessagetoWorker(Integer packId){
		
		return dao.sendMessagetoWorker(packId);
	}
	

	public Page<WorkgroupVo> getFindPage(Page<WorkgroupVo> page, WorkgroupVo workgroupVo,OrderTaskpackage taskPack){
		workgroupVo.setPage(page);
		List<WorkgroupVo> findList = dao.findList(workgroupVo);




        if (null == taskPack.getPlanEnddate() || findList.size() ==0) {
            page.setList(findList);
        	return page;

        }


        List<OrderTaskpackage> list2 = dao.findAllPackageWhomHasEmpGroup(findList);


        List<Integer> wantedDeleteWorkerId = new ArrayList<>();

        for (OrderTaskpackage orderTaskpackage : list2) {

            if (null != orderTaskpackage.getPlanStartdate() && null != orderTaskpackage.getPlanEnddate()
                    && null != taskPack.getPlanEnddate()) {





                if ((orderTaskpackage.getPlanStartdate().getTime() > taskPack.getPlanEnddate().getTime()) || (orderTaskpackage.getPlanEnddate().getTime() < taskPack.getPlanStartdate().getTime())) {
                } else {

                    wantedDeleteWorkerId.add(Integer.parseInt(orderTaskpackage.getEmpGroupid()));
                }

            }

        }



        Iterator<WorkgroupVo> iterator = findList.iterator();
        while (iterator.hasNext()) {
            WorkgroupVo workgroupVo2 = iterator.next();

            if (wantedDeleteWorkerId.contains(workgroupVo2.getWorkerGroupId())) {
                iterator.remove();
            }


        }
		page.setList(findList);
		return page;
	}

	public Page<WorkgroupVo> getFindPageNew(Page<WorkgroupVo> page, WorkgroupVo workgroupVo,
			OrderTaskpackage taskpackage) {
		workgroupVo.setPage(page);
		List<WorkgroupVo> findList = dao.findList(workgroupVo);
		page.setList(findList);
		return page;
	}

	public Page<WorkgroupVo> findPageSpecialListNew(Page<WorkgroupVo> page, WorkgroupVo workgroupVo,
			OrderTaskpackage taskpackage) {

		workgroupVo.setPage(page);
		List<WorkgroupVo> findList = allotWorkerGroupDao.findPageSpecialListNew(workgroupVo);
		page.setList(findList);
		return page;
	}

}