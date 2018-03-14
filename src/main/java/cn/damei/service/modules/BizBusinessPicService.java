package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizBusinessPicDao;
import cn.damei.entity.modules.BizBusinessPic;

/**
 * 公共上传图片
 * @author llp
 */
@Service
@Transactional(readOnly = true)
public class BizBusinessPicService extends CrudService2<BizBusinessPicDao, BizBusinessPic>{
	
	@Autowired
	private BizBusinessPicDao bizBusinessPicDao;

	public List<BizBusinessPic> getByBusinessId(Integer valueOf) {
		// TODO Auto-generated method stub
		return bizBusinessPicDao.getByBusinessId(valueOf);
	}

	/**
	 * 获取复尺相关图片
	 * @param recheckID
	 * @param type
	 * @return List
	 */
	public List<BizBusinessPic> getByRecheckID(Integer recheckID, String type) {
		// TODO Auto-generated method stub
		return bizBusinessPicDao.getByRecheckID(recheckID,type);
	}

}
