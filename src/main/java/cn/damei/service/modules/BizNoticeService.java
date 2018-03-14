/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.*;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.BizEmployeeDao2;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.entity.modules.BizNoticeVo;
import cn.damei.dao.modules.BizNoticeReceiverDao;
import cn.damei.entity.modules.BizNoticeReceiver;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizNotice;
import cn.damei.dao.modules.BizNoticeDao;

/**
 * 公告Service
 * @author qww
 * @version 2017-01-14
 */
@Service
@Transactional(readOnly = true)
public class BizNoticeService extends CrudService2<BizNoticeDao, BizNotice> {

	@Autowired
	private BizEmployeeDao2 bizEmployeeDao2;

	@Autowired
	private BizNoticeReceiverDao bizNoticeReceiverDao;

	public BizNotice get(Integer id) {
		return super.get(id);
	}
	
	public List<BizNotice> findList(BizNotice bizNotice) {
		return super.findList(bizNotice);
	}
	
	public Page<BizNotice> findPage(Page<BizNotice> page, BizNotice bizNotice) {
		return super.findPage(page, bizNotice);
	}
	
	@Transactional(readOnly = false)
	public void save(BizNotice bizNotice) {
		Date date = new Date();
		User user = UserUtils.getUser();
		try{
			if(bizNotice.getId() != null){
				BizNotice notice = dao.get(bizNotice.getId());
				if(notice != null){
					notice.setStoreId(bizNotice.getStoreId());
					notice.setProjectMode(bizNotice.getProjectMode());
					notice.setNoticeType(bizNotice.getNoticeType());
					notice.setNoticeTitle(bizNotice.getNoticeTitle());
					notice.setNoticeContent(bizNotice.getNoticeContent());
					notice.setNoticeStatus(bizNotice.getNoticeStatus());
					if(ConstantUtils.NOTICE_STATUS_2.equals(bizNotice.getNoticeStatus())){
						notice.setPublishDatetime(date);
					}
					notice.setUpdateBy(user);
					notice.setUpdateDate(date);
					dao.update(notice);

					bizNoticeReceiverDao.deleteByNoticeId(bizNotice.getId());
					Set<Integer> set = new HashSet<Integer>();
					List<BizNoticeReceiver> receiverList = new ArrayList<BizNoticeReceiver>();

					if(bizNotice.getReceiverEmployeeIds() != null && bizNotice.getReceiverEmployeeIds().length > 0){
						for(Integer receiverEmployeeId:bizNotice.getReceiverEmployeeIds()){
							if(set.add(receiverEmployeeId)){
								BizNoticeReceiver receiver = new BizNoticeReceiver();
								receiver.setNoticeId(bizNotice.getId());
								receiver.setReceiverType(ConstantUtils.NOTICE_RECEIVER_TYPE_2);
								receiver.setReceiverEmployeeId(receiverEmployeeId);
								receiver.setCreateBy(user);
								receiver.setCreateDate(date);
								receiver.setUpdateBy(user);
								receiver.setUpdateDate(date);
								receiverList.add(receiver);
							}
						}
					}

					if(StringUtils.isNoneBlank(bizNotice.getReceiverRole())){
						String[] receiverRoles = bizNotice.getReceiverRole().split(",");
						BizNotice receivertemp = new BizNotice();
						
						for(String role:receiverRoles){
							receivertemp.setNoticeType(role);
							receivertemp.setProjectMode(bizNotice.getProjectMode());
							receivertemp.setStoreId(bizNotice.getStoreId());
							List<BizEmployee2> employeeList = bizEmployeeDao2.queryEmployeeByEmpType2(receivertemp);
							for(BizEmployee2 employee:employeeList){
								if(set.add(employee.getId())){
									BizNoticeReceiver receiver = new BizNoticeReceiver();
									receiver.setNoticeId(notice.getId());
									receiver.setReceiverType(ConstantUtils.NOTICE_RECEIVER_TYPE_1);
									receiver.setReceiverRole(role);
									receiver.setReceiverEmployeeId(employee.getId());
									receiver.setCreateBy(user);
									receiver.setCreateDate(date);
									receiver.setUpdateBy(user);
									receiver.setUpdateDate(date);
									receiverList.add(receiver);
								}
							}
						}
					}

					bizNoticeReceiverDao.insertBatch(receiverList);
				}
			}else{
				if(ConstantUtils.NOTICE_STATUS_2.equals(bizNotice.getNoticeStatus())){
					bizNotice.setPublishDatetime(date);
				}
				bizNotice.setCreateBy(user);
				bizNotice.setCreateDate(date);
				bizNotice.setUpdateBy(user);
				bizNotice.setUpdateDate(date);
				dao.insert(bizNotice);

				
				
					Set<Integer> set = new HashSet<Integer>();
					List<BizNoticeReceiver> receiverList = new ArrayList<BizNoticeReceiver>();

					if(bizNotice.getReceiverEmployeeIds() != null && bizNotice.getReceiverEmployeeIds().length > 0){
						for(Integer receiverEmployeeId:bizNotice.getReceiverEmployeeIds()){
							if(set.add(receiverEmployeeId)){
								BizNoticeReceiver receiver = new BizNoticeReceiver();
								receiver.setNoticeId(bizNotice.getId());
								receiver.setReceiverType(ConstantUtils.NOTICE_RECEIVER_TYPE_2);
								receiver.setReceiverEmployeeId(receiverEmployeeId);
								receiver.setCreateBy(user);
								receiver.setCreateDate(date);
								receiver.setUpdateBy(user);
								receiver.setUpdateDate(date);
								receiverList.add(receiver);
							}
						}
					}

					if(StringUtils.isNoneBlank(bizNotice.getReceiverRole())){
						String[] receiverRoles = bizNotice.getReceiverRole().split(",");
						BizNotice receivertemp = new BizNotice();
						for(String role:receiverRoles){
							receivertemp.setNoticeType(role);
							receivertemp.setProjectMode(bizNotice.getProjectMode());
							receivertemp.setStoreId(bizNotice.getStoreId());
							List<BizEmployee2> employeeList = bizEmployeeDao2.queryEmployeeByEmpType2(receivertemp);
							for(BizEmployee2 employee:employeeList){
								if(set.add(employee.getId())){
									BizNoticeReceiver receiver = new BizNoticeReceiver();
									receiver.setNoticeId(bizNotice.getId());
									receiver.setReceiverType(ConstantUtils.NOTICE_RECEIVER_TYPE_1);
									receiver.setReceiverRole(role);
									receiver.setReceiverEmployeeId(employee.getId());
									receiver.setCreateBy(user);
									receiver.setCreateDate(date);
									receiver.setUpdateBy(user);
									receiver.setUpdateDate(date);
									receiverList.add(receiver);
								}
							}
						}
					}

					bizNoticeReceiverDao.insertBatch(receiverList);
				}
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		bizNoticeReceiverDao.deleteByNoticeId(id);
		dao.delete(id);

	}

	@Transactional(readOnly = false)
	public void revoked(Integer id) {
		BizNotice notice = dao.get(id);
		notice.setNoticeStatus(ConstantUtils.NOTICE_STATUS_3);
		notice.setUpdateBy(UserUtils.getUser());
		notice.setUpdateDate(new Date());
		dao.update(notice);
	}

	public Page<BizNoticeVo> findNoticePage(Page<BizNoticeVo> page, BizNoticeVo entity) {
		entity.setPage(page);
		page.setList(dao.findNoticeList(entity));
		return page;
	}

	public BizNotice queryNoticeById(Integer id){
		return dao.queryNoticeById(id);
	}

	public Page<BizNotice> findAppNoticePage(Page<BizNotice> page, BizNotice entity) {
		entity.setPage(page);
		page.setList(dao.findAppNoticePageList(entity));
		return page;
	}

	public Integer queryAppNoticePageListCount( BizNotice entity) {
		return dao.findAppNoticePageListCount(entity);
	}
	public Integer findAppNoticePageListNum( BizNotice entity) {
		return dao.findAppNoticePageListNum(entity);
	}
	
}