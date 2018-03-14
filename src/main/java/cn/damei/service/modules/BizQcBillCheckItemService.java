/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import cn.damei.common.constantUtils.WallfloorConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizQcBillCheckItem;
import cn.damei.dao.modules.BizQcBillCheckItemDao;

/**
 * 质检检查项查询Service
 * @author ztw
 * @version 2017-12-21
 */
@Service
@Transactional(readOnly = true)
public class BizQcBillCheckItemService extends CrudService<BizQcBillCheckItemDao, BizQcBillCheckItem> {

	public BizQcBillCheckItem get(String id) {
		return super.get(id);
	}
	
	public List<BizQcBillCheckItem> findList(BizQcBillCheckItem bizQcBillCheckItem) {
		return super.findList(bizQcBillCheckItem);
	}
	
	public Page<BizQcBillCheckItem> findPage(Page<BizQcBillCheckItem> page, BizQcBillCheckItem bizQcBillCheckItem) {

        bizQcBillCheckItem.setPage(page);
        //根据名称查询责任人项目经理的id集合
        BizQcBillCheckItem bizQcBillCheckItempOne = new  BizQcBillCheckItem();
        if(!StringUtils.isEmpty(bizQcBillCheckItem.getMnagerPerson())){
            bizQcBillCheckItempOne.setMnagerPerson(bizQcBillCheckItem.getMnagerPerson());
            List<BizQcBillCheckItem> pmPunishEmployeelist = dao.queryEmployeeAndgroupIdinfo(bizQcBillCheckItempOne);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pmPunishEmployeelist.size(); i++) {
                if(i< (pmPunishEmployeelist.size()-1)){
                    sb.append(pmPunishEmployeelist.get(i).getEmployeeId()+",");
                }else{
                    sb.append(pmPunishEmployeelist.get(i).getEmployeeId());

                }
            }
            bizQcBillCheckItem.setProjectManagerId(sb.toString());
        }
        //根据名称查询被罚组长的id集合
        if(!StringUtils.isEmpty(bizQcBillCheckItem.getWorkGroupPerson())){
            bizQcBillCheckItempOne.setMnagerPerson(bizQcBillCheckItem.getWorkGroupPerson());
            List<BizQcBillCheckItem> pmPunishEmployeelist = dao.queryEmployeeAndgroupIdinfo(bizQcBillCheckItempOne);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pmPunishEmployeelist.size(); i++) {
                if(i< (pmPunishEmployeelist.size()-1)){
                    sb.append(pmPunishEmployeelist.get(i).getGroupId()+",");
                }else{
                    sb.append(pmPunishEmployeelist.get(i).getGroupId());

                }
            }
            bizQcBillCheckItem.setWorkerPunishEmployeegroupId (sb.toString());
        }
        //根据名称查询被罚工人组的id集合
        if(!StringUtils.isEmpty(bizQcBillCheckItem.getItemManager())){
            bizQcBillCheckItempOne.setMnagerPerson(bizQcBillCheckItem.getItemManager());
            List<BizQcBillCheckItem> pmPunishEmployeelist = dao.queryEmployeeAndgroupIdinfo(bizQcBillCheckItempOne);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pmPunishEmployeelist.size(); i++) {
                if(i< (pmPunishEmployeelist.size()-1)){
                    sb.append(pmPunishEmployeelist.get(i).getEmployeeId()+",");
                }else{
                    sb.append(pmPunishEmployeelist.get(i).getEmployeeId());

                }
            }
            bizQcBillCheckItem.setPmPunishEmployeeId(sb.toString());
        }


        if(null!=bizQcBillCheckItem.getIsPassed()){
                if(bizQcBillCheckItem.getIsPassed().equals(WallfloorConstant.PROCESSING_METHOD1)){
                    bizQcBillCheckItem.setIsWarned(WallfloorConstant.WAll_FLOOR_ISTRUE1);
                }
                if(bizQcBillCheckItem.getIsPassed().equals(WallfloorConstant.PROCESSING_METHOD2)){
                    bizQcBillCheckItem.setIsLimitDateRepaire(WallfloorConstant.WAll_FLOOR_ISTRUE1);
                }
                if(bizQcBillCheckItem.getIsPassed().equals(WallfloorConstant.PROCESSING_METHOD3)){
                    bizQcBillCheckItem.setIsLocaleRepaire(WallfloorConstant.WAll_FLOOR_ISTRUE1);
                }
                if(bizQcBillCheckItem.getIsPassed().equals(WallfloorConstant.PROCESSING_METHOD4)){
                    bizQcBillCheckItem.setIsPunishMoney(WallfloorConstant.WAll_FLOOR_ISTRUE1);
                }
        }


        List<BizQcBillCheckItem> list = dao.findList(bizQcBillCheckItem);
        for (int i = 0; i <list.size() ; i++) {

            BizQcBillCheckItem bizQcBillCheckItemp = new  BizQcBillCheckItem();
            //根据员工ID查询被罚项目经理名称
            if(null!=list.get(i).getPmPunishEmployeeId()) {
                bizQcBillCheckItemp.setPmPunishEmployeeId(list.get(i).getPmPunishEmployeeId());
                List<BizQcBillCheckItem> pmPunishEmployeelist = dao.queryEmployeeAndgroupIdinfo(bizQcBillCheckItemp);
                bizQcBillCheckItemp.setPmPunishEmployeeId("");
                list.get(i).setItemManager(pmPunishEmployeelist.get(0).getEmployeeName());
            }
            //根据工人组ID查询被工人组名称
            if(null!=list.get(i).getWorkerPunishEmployeegroupId()) {
                bizQcBillCheckItemp.setWorkerPunishEmployeegroupId(list.get(i).getWorkerPunishEmployeegroupId());
                List<BizQcBillCheckItem> workerPunishEmployeegroupIdlist = dao.queryEmployeeAndgroupIdinfo(bizQcBillCheckItemp);
                bizQcBillCheckItemp.setWorkerPunishEmployeegroupId("");
                list.get(i).setWorkGroupPerson(workerPunishEmployeegroupIdlist.get(0).getEmployeeName());
            }
            //根据员工ID责任人项目经理名称
            if(null!=list.get(i).getProjectManagerId()) {
                bizQcBillCheckItemp.setProjectManagerId(list.get(i).getProjectManagerId());
                List<BizQcBillCheckItem> mnagerPersonlist = dao.queryEmployeeAndgroupIdinfo(bizQcBillCheckItemp);
                bizQcBillCheckItemp.setProjectManagerId("");
                list.get(i).setMnagerPerson(mnagerPersonlist.get(0).getEmployeeName());
            }
            //根据工人组ID查询责任工人组长名称
            if(null!=list.get(i).getWorkerGroupId()) {
                bizQcBillCheckItemp.setWorkerGroupId(list.get(i).getWorkerGroupId());
                List<BizQcBillCheckItem> workerGroupId = dao.queryEmployeeAndgroupIdinfo(bizQcBillCheckItemp);
                bizQcBillCheckItemp.setWorkerGroupId("");
                list.get(i).setWorkerGroupName(workerGroupId.get(0).getEmployeeName());
            }



        }



        page.setList(list);
        return page;
	}

	@Transactional(readOnly = false)
	public void save(BizQcBillCheckItem bizQcBillCheckItem) {
		super.save(bizQcBillCheckItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizQcBillCheckItem bizQcBillCheckItem) {
		super.delete(bizQcBillCheckItem);
	}
	
}