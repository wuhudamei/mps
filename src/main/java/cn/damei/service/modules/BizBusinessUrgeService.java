/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.modules.BizBusinessUrge;
import cn.damei.entity.modules.InstallBusinessUrge;
import cn.damei.entity.modules.WallAndFloorBusinessUrge;
import cn.damei.dao.modules.BizBusinessUrgeDao;

/**
 * 业务催促表Service
 * @author wyb
 * @version 2017-05-03
 */
@Service
@Transactional(readOnly = true)
public class BizBusinessUrgeService extends CrudService2<BizBusinessUrgeDao, BizBusinessUrge> {

	public BizBusinessUrge get(Integer id) {
		return super.get(id);
	}
	
	public List<BizBusinessUrge> findList(BizBusinessUrge bizBusinessUrge) {
		return super.findList(bizBusinessUrge);
	}
	
	public Page<BizBusinessUrge> findPage(Page<BizBusinessUrge> page, BizBusinessUrge bizBusinessUrge) {
		return super.findPage(page, bizBusinessUrge);
	}
	
	@Transactional(readOnly = false)
	public void save(BizBusinessUrge bizBusinessUrge) {
		super.save(bizBusinessUrge);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizBusinessUrge bizBusinessUrge) {
		super.delete(bizBusinessUrge);
	}

	
	/**
	 * 保存催促回复
	 * @param managerId
	 * @param operateContent 
	 * @param id
	 * @param businessUrgeBusinessType1
	 * @param businessUrgeOperateType1
	 * @param businessUrgeOperatorType1
	 * @return
	 */
	@Transactional(readOnly = false)
	public Integer saveBusinessUrge(Integer managerId, String operateContent, Integer id, String businessUrgeBusinessType1,
			String businessUrgeOperateType1, String businessUrgeOperatorType1) {
		
		BizBusinessUrge bizBusinessUrge = new BizBusinessUrge();
		//业务唯一标识整形
		bizBusinessUrge.setBusinessOnlyMarkInt(id);
		//业务类型
		bizBusinessUrge.setBusinesType(businessUrgeBusinessType1);
		//操作类型
		bizBusinessUrge.setOperateType(businessUrgeOperateType1);
		//操作内容
		bizBusinessUrge.setOperateContent(operateContent);
		//操作人员工ID
		bizBusinessUrge.setOperatorEmployeeId(managerId);
		//操作人类型
		bizBusinessUrge.setOperatorType(businessUrgeOperatorType1);
		//操作日期时间
		bizBusinessUrge.setOperateDatetime(new Date());
		
		bizBusinessUrge.preInsert();
		
		dao.insert(bizBusinessUrge);
		
		return bizBusinessUrge.getId();
	}

	/**
	 * 催促安装，一天最多允许催促次数
	 * @param businessUrgeOperatorType1 
	 * @param businessUrgeOperateType1 
	 * @param businessUrgeBusinessType1 
	 * @param manager 
	 * @param id
	 * @return
	 */
	public Integer findCount(Manager manager, Integer id, String businessUrgeBusinessType1, String businessUrgeOperateType1, String businessUrgeOperatorType1) {
		
		BizBusinessUrge bizBusinessUrge = new BizBusinessUrge();
		//业务唯一标识整形
		bizBusinessUrge.setBusinessOnlyMarkInt(id);
		//业务类型
		bizBusinessUrge.setBusinesType(businessUrgeBusinessType1);
		//操作类型
		bizBusinessUrge.setOperateType(businessUrgeOperateType1);
		//操作人员工ID
		bizBusinessUrge.setOperatorEmployeeId(manager.getId());
		//操作人类型
		bizBusinessUrge.setOperatorType(businessUrgeOperatorType1);
		
		return dao.findCount(bizBusinessUrge);
	}

	/**
	 * 催促回复5分钟校验
	 * @param id
	 * @param managerId
	 * @param businessUrgeBusinessType1
	 * @param businessUrgeOperateType2
	 * @param businessUrgeOperatorType1
	 * @return
	 */
	public Integer findCountByfiveTime(Integer id, Integer managerId, String businessUrgeBusinessType1,
			String businessUrgeOperateType2, String businessUrgeOperatorType1) {
		
		BizBusinessUrge bizBusinessUrge = new BizBusinessUrge();
		//业务唯一标识整形
		bizBusinessUrge.setBusinessOnlyMarkInt(id);
		//业务类型
		bizBusinessUrge.setBusinesType(businessUrgeBusinessType1);
		//操作类型
		bizBusinessUrge.setOperateType(businessUrgeOperateType2);
		//操作人员工ID
		bizBusinessUrge.setOperatorEmployeeId(managerId);
		//操作人类型
		bizBusinessUrge.setOperatorType(businessUrgeOperatorType1);
		
		return dao.findCountByfiveTime(bizBusinessUrge);
	}

	/**
	 * 主材催促查询
	 * @param page
	 * @param installBusinessUrge
	 * @return
	 */
	public Page<InstallBusinessUrge> findInstallSelectPage(Page<InstallBusinessUrge> page,
			InstallBusinessUrge installBusinessUrge) {
		
		installBusinessUrge.setPage(page);
		page.setList(dao.findInstallSelectList(installBusinessUrge));
		return page;
	}
	
	/**
	 * 墙地砖催促查询
	 * @param page
	 * @param bizBusinessUrge
	 * @return
	 */
	public Page<WallAndFloorBusinessUrge> findWallAndFloorSelectPage(Page<WallAndFloorBusinessUrge> page, WallAndFloorBusinessUrge wallAndFloorBusinessUrge) {
		
		wallAndFloorBusinessUrge.setPage(page);
		page.setList(dao.findWallAndFloorSelectList(wallAndFloorBusinessUrge));
		return page;
		
	}

    /**
    * @Description: 查询安装项验收不合格的日期
    * @Author zhangkangjian
    * @param
    * @return
    * @Date 2017/12/4 16:01
    */
    public List<BizBusinessUrge> findUnqualifiedAcceptLog(Map<String, String> map) {
        return dao.findUnqualifiedAcceptLog(map);
    }
}