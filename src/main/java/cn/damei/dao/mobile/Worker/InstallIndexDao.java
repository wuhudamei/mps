package cn.damei.dao.mobile.Worker;


import cn.damei.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface InstallIndexDao{


	/**
	 * 查询工人组的施工单--未完工的数量
	 * @param groupId
	 * @return
	 */
	Integer findUnfinishedCount(Integer groupId);

	/**
	 * 查询工人组的施工单--已完工的数量
	 * @param groupId
	 * @return
	 */
	Integer findFinishedCount(Integer groupId);


}
