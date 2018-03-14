package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.ManagerBroadCastEntity;

@MyBatisDao
public interface ManagerBroadCastDao {


	public List<ManagerBroadCastEntity> findBroadCastList(ManagerBroadCastEntity entity);

	public List<ManagerBroadCastEntity> findBroadCastInfoAndPic(Integer broadcastId);
	

	public void updateCurrentPicStatus(List<ManagerBroadCastEntity> entity);

	public void  savePicAndIsShow(List<ManagerBroadCastEntity> entity);
	

	
	public void updateBroadCast(ManagerBroadCastEntity entity);
	
	
}
