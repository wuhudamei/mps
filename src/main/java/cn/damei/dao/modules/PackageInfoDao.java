package cn.damei.dao.modules;

import java.util.List;

import cn.damei.entity.modules.PackageInfo;
import cn.damei.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface PackageInfoDao{

	public List<PackageInfo> queryPackInfoByOrderNumber(String orderNumber);
	
}
