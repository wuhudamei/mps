package cn.damei.service.mobile.Worker;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.dao.mobile.Worker.InstallItemListDao;
import cn.damei.entity.mobile.Worker.InstallItem;


@Service
@Transactional(readOnly = false)
public class InstallItemListService{
	
	
	@Autowired
	private InstallItemListDao dao;


	public List<InstallItem> findInstallConstructBillList(InstallItem installItem) {
		return dao.findInstallConstructBillList(installItem);
	}





	
	
}
