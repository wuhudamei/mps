package cn.damei.dao.mobile.home;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.home.BroadCastCodeEntity;
import cn.damei.entity.mobile.home.BroadCastPicEntity;

@MyBatisDao
public interface BroadCastDao {

	
	
	

	public void saveBroadCastRecord(BroadCastPicEntity entity);

	public void saveBroadCastPic(BroadCastPicEntity entity);
	
	
	public void saveCode();
	public  BroadCastCodeEntity  getCode();
	public  void updateCode(BroadCastCodeEntity code);
}
