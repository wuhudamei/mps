package cn.damei.dao.mobile.Worker;


import cn.damei.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface InstallIndexDao{



	Integer findUnfinishedCount(Integer groupId);


	Integer findFinishedCount(Integer groupId);


}
