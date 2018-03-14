package cn.damei.service.mobile.Manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.NewChecksizeDao;
import cn.damei.entity.mobile.Manager.NewChecksizeOrder;

/**
 * 上报复尺新版(20161107-20161113)
 * @author llp
 * 2016-11-15
 */
@Service
@Transactional(readOnly = true)
public class NewChecksizeService extends CrudService2<NewChecksizeDao,NewChecksizeOrder>{

	@Autowired
	private NewChecksizeDao newChecksizeDao;
	
	
	
	public List<NewChecksizeOrder> queryList(Integer managerID) {
		return newChecksizeDao.queryList(managerID);
	}



	public NewChecksizeOrder getByID(Integer id) {
		// TODO Auto-generated method stub
		return newChecksizeDao.getByID(id);
	}

}
