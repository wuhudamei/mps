package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.ManagerBroadCastEntity;

@MyBatisDao
public interface ManagerBroadCastDao {

	//查询播报单(带模糊查询搜索)
	public List<ManagerBroadCastEntity> findBroadCastList(ManagerBroadCastEntity entity);
	//根据播报单id查询图片等
	public List<ManagerBroadCastEntity> findBroadCastInfoAndPic(Integer broadcastId);
	
	//更新当前有图片的 展示状态
	public void updateCurrentPicStatus(List<ManagerBroadCastEntity> entity);
	//保存项目经理新上传的图片及展示状态
	public void  savePicAndIsShow(List<ManagerBroadCastEntity> entity);
	
	//更新播报单的状态 ,日期 等
	
	public void updateBroadCast(ManagerBroadCastEntity entity);
	
	
}
