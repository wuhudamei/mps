package cn.damei.service.mobile.Worker;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.dao.mobile.Worker.InstallIndexDao;


@Service
@Transactional(readOnly = false)
public class InstallIndexService{
	
	
	@Autowired
	private InstallIndexDao dao;


	/**
	 * 查询工人组的施工单--未完工的数量
	 * @param groupId
	 * @return
	 */
	public Integer findUnfinishedCount(Integer groupId) {
		return dao.findUnfinishedCount(groupId);
	}

	/**
	 * 查询工人组的施工单--已完工的数量
	 * @param groupId
	 * @return
	 */
	public Integer findFinishedCount(Integer groupId) {
		return dao.findFinishedCount(groupId);
	}



	
	
}
