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

/**
 * @author 梅浩 meihao@zzhyun.cn:
 * @version 创建时间：2016年9月12日 下午3:00:22 工人组service
 */
@Service
@Transactional(readOnly=true)
public class AllotWorkerGroupService extends CrudService<AllotWorkerGroupDao, WorkgroupVo> {
	// 根据任务包id查询任务包
	public OrderTaskpackage findTargetPackageById(Integer id) {

		return dao.findTargetPackageById(id);
	}

	// 根据工人组id 去 工人关系表查询 有多少工人
	public Integer findCountByWorkerId(Integer id) {

		return dao.findCountByWorkerId(id);
	}

	/**
	 * 根据组长id 获取组长名字
	 * 
	 * @return
	 */
	public String findTeamLeaderNameById(Integer id) {

		return dao.findTeamLeaderNameById(id);
	}

	/**
	 * 根据组长id,查询组长手机号
	 * 
	 * @return
	 */
	public String findTeamLeaderPhoneById(Integer id) {

		return dao.findTeamLeaderPhoneById(id);
	}

	/**
	 * 根据工程部id , 查询工程部名称
	 * 
	 * @return
	 */
	public String findengineerById(String id) {

		return dao.findengineerById(id);
	}

	/**
	 * 更新任务包的状态
	 * @throws ParseException 
	 */
	@Autowired
	private PhoneMessageDao messageDao; //短信接口
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
		
		// 是否调整了任务包时间
		boolean flag = false;
		// 查询任务包
		OrderTaskpackage packageById = dao.findTargetPackageById(Integer.valueOf(packageId));
		//旧的id
		String oldGroupId = packageById.getGroupId();
		// 设置属性
		String planStartDate = "";
		String planEndDate = "";

		if (null == packageById.getPlanStartdate()) {
			// 开始时间
			packageById.setPlanStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));

		} else if (null == packageById.getPlanEnddate()) {
			// 结束时间
			packageById.setPlanEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
		} else {
			// 如果不为空
			planStartDate = new SimpleDateFormat("yyyy-MM-dd").format(packageById.getPlanStartdate());
			planEndDate = new SimpleDateFormat("yyyy-MM-dd").format(packageById.getPlanEnddate());

		}

		// 调整任务包时间
		if (!planStartDate.equals(startDate) || !planEndDate.equals(endDate)) {
			packageById.setPlanStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));
			packageById.setPlanEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
			flag = true;
		}

		packageById.setDispatchTime(new Date());
		if(null!=isRedistribute && "1".equals(isRedistribute)){
			
			if(Integer.parseInt(packageById.getPackageStateId())>=60){
				//如果该任务包状态已经大于等于 60  已确定工人组, 不更改状态
			}else{
				//反之 更改状态
				packageById.setPackageStatename(OrderTaskpackageConstantUtil.ORDER_TASKPACKAGE_STATUS_NAME_50);
				packageById.setPackageStateId(OrderTaskpackageConstantUtil.ORDER_TASKPACKAGE_STATUS_50);
				
				
			}
			
		}else{
			//不是重新分配 正常流程走

			packageById.setPackageStatename(OrderTaskpackageConstantUtil.ORDER_TASKPACKAGE_STATUS_NAME_50);
			packageById.setPackageStateId(OrderTaskpackageConstantUtil.ORDER_TASKPACKAGE_STATUS_50);
		}
		
		// 给任务包设置组长姓名
		packageById.setGroupRealname(dao.findTeamLeaderNameById(Integer.valueOf(workerGroup.getGroupId())));
		// 给任务包设置组长id
		packageById.setGroupId(workerGroup.getGroupId());
		// 设置工人组id
		packageById.setEmpGroupid(workerGroup.getId());
		// 保存更新
		dao.updatePackage(packageById);
		
		
		
		//日志  记录操作人 操作时间  在  biz_order_distribute_log 表中
		BizOrderDistributeLog dl = new  BizOrderDistributeLog();
		
		dl.setOrderId(Integer.parseInt(packageById.getOrderId()));
		
		dl.setOrderTaskpackageId(Integer.parseInt(packageById.getId()));
		
		if(null!=isRedistribute && "1".equals(isRedistribute)){
			//再分配
			dl.setDistributeType(isSpecial?ConstantUtils.DISTRIBUTE_TYPE_305:DisributeLogConstantUtil.DISTRIBUTE_TYPE_302);
			//新empId
			dl.setDistributedEmployeeId(Integer.parseInt(packageById.getGroupId()));
			//老的empID
			dl.setOldEmployeeId(Integer.parseInt(oldGroupId));
			//更新时间
			dl.setUpdateDate(new Date());
			//更新人
			dl.setUpdateBy(UserUtils.getUser());
			//说明
			dl.setReasonRemarks(reasonRemarks);
			//原因
			dl.setReasonType(reasonType);
			
			//修改员工被换单次数
			BizEmployee bizEmployee = bizEmployeeDao.selectExchangeOrderTimesById(Integer.parseInt(oldGroupId));
			//如果被换单次数为0 则为1
			if(bizEmployee.getExchangeOrderTimes()==null || bizEmployee.getExchangeOrderTimes()==0){
				bizEmployee.setExchangeOrderTimes(1);
			}else{
				Integer times = bizEmployee.getExchangeOrderTimes();
				//换单次数+1
				bizEmployee.setExchangeOrderTimes(times+=1);
			}
			bizEmployee.setUpdateBy(UserUtils.getUser());
			bizEmployee.setUpdateDate(new Date());
			//更新employee 表
			bizEmployeeDao.updateExchangeOrderTimes(bizEmployee);
			
		}else{
			//第一次分配
			dl.setDistributeType(isSpecial?ConstantUtils.DISTRIBUTE_TYPE_304:DisributeLogConstantUtil.DISTRIBUTE_TYPE_301);
			//分配的员工id
			dl.setDistributedEmployeeId(Integer.parseInt(packageById.getGroupId()));
			
		}
		dl.preInsert();
		//工人组id
		dl.setDistributedGroupId(Integer.parseInt(workerGroup.getId()));
		dl.setDelFlag("0");
		//保存分配记录信息
		bizOrderDistributeLogDao.insert(dl);
		
		

		// 2: 发短信 ==============开始================
		// 根据1:得到工人手机
		String workerLeaderPhone = dao
				.findTeamLeaderPhoneById(Integer.parseInt(workerGroup.getGroupId()));
		// 根据任务包id , 得到 客户地址和姓名,手机,任务包名称
		OrderTaskpackage info = dao.sendMessagetoWorker(Integer.parseInt(packageId));

		// 保存短信
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

		// 2:结束

		// =====================================消息推送start========================================================

		/**
		 * 消息推送 消息推送类型MESSAGE_PUSH_TYPE_100004001-通知项目经理-分配任务包
		 * 订单（东晨小区-10-4-202-王维-13333333333）的任务包（泥瓦工程），已发送给您，请在【我要接单】中接收，
		 * 我们为您保留60分钟。
		 */
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
		// =====================================消息推送end========================================================

		// =========================发送工人调度员更新任务包的计划开始时间和结束时间的短信================================
		// 如果调整任务包时间 发送给项目经理短信通知

		if (flag) {
			/*
			logger.info("==========任务包计划被工人调度员调整,触发短信==========/r短信模板为:工人调度员调整了项目（小区名-楼号-单元号-门牌号-客户姓名）的用工计划，请知晓 ");
			*/
		}

		// =========================发送工人调度员更新任务包的计划开始时间和结束时间的短信================================
	
	}
	/**
	 * 查找所有任务包
	 * 条件: 有分配过的工人组id
	 * @return
	 */
	public List<OrderTaskpackage> findAllPackageWhomHasEmpGroup(List<WorkgroupVo> workgroupVo){
		return dao.findAllPackageWhomHasEmpGroup( workgroupVo);
	}
	
	/**
	 * 根据工人组id查询工人组
	 * @param workGroupId
	 * @return
	 */
	public WorkgroupVo  getWorkgroupById(Integer workGroupId){
		return dao.getWorkgroupById(workGroupId);
	}
	
	
	
	/**
	 * 发短信所需要的信息
	 * @param packId
	 * @return
	 */
	public  OrderTaskpackage	sendMessagetoWorker(Integer packId){
		
		return dao.sendMessagetoWorker(packId);
	}
	
	/**
	 * 
	 */
	public Page<WorkgroupVo> getFindPage(Page<WorkgroupVo> page, WorkgroupVo workgroupVo,OrderTaskpackage taskPack){
		workgroupVo.setPage(page);
		List<WorkgroupVo> findList = dao.findList(workgroupVo);
		// 类型一致的工人组

        // 如果该任务包没有时间或者没有工人组 就直接不做时间上的剔除

        if (null == taskPack.getPlanEnddate() || findList.size() ==0) {
            page.setList(findList);
        	return page;

        }

        // 根据工人组list 去 任务包表 查询 已接的 任务包
        List<OrderTaskpackage> list2 = dao.findAllPackageWhomHasEmpGroup(findList);

        //需要删除的工人组集合
        List<Integer> wantedDeleteWorkerId = new ArrayList<>();
        // 遍历任务包 比较时间是否冲突
        for (OrderTaskpackage orderTaskpackage : list2) {

            if (null != orderTaskpackage.getPlanStartdate() && null != orderTaskpackage.getPlanEnddate()
                    && null != taskPack.getPlanEnddate()) {

                // 判断时间是否冲突
                // 取集合的交集 为不可分配时间
                //即 已分配的任务包 要么开始时间比 要分配的任务包的结束时间大, 要么已分配的任务包的结束时间比要分配的任务包的开始时间

                if ((orderTaskpackage.getPlanStartdate().getTime() > taskPack.getPlanEnddate().getTime()) || (orderTaskpackage.getPlanEnddate().getTime() < taskPack.getPlanStartdate().getTime())) {
                } else {
                    // 如果冲突就删除
                    wantedDeleteWorkerId.add(Integer.parseInt(orderTaskpackage.getEmpGroupid()));
                }

            }

        }


        //迭代删除
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
		// TODO Auto-generated method stub
		workgroupVo.setPage(page);
		List<WorkgroupVo> findList = allotWorkerGroupDao.findPageSpecialListNew(workgroupVo);
		page.setList(findList);
		return page;
	}

}