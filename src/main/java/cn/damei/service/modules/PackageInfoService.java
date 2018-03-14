package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.dao.modules.PackageInfoDao;
import cn.damei.entity.modules.PackageInfo;

@Service
@Transactional(readOnly = true)
public class PackageInfoService {
	@Autowired
	private PackageInfoDao packageInfoDao;

	public List<PackageInfo> queryPackInfoByOrderNumber(String orderNumber) {
		return packageInfoDao.queryPackInfoByOrderNumber(orderNumber);
	}
}
