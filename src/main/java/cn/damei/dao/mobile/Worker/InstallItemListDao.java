package cn.damei.dao.mobile.Worker;


import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Worker.InstallItem;

@MyBatisDao
public interface InstallItemListDao{

	/**
	 * 查询施工单列表
	 * @param installItem
	 * @return
	 */
	List<InstallItem> findInstallConstructBillList(InstallItem installItem);




}
