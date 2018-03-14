
package cn.damei.service.modules;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.entity.modules.BizOrderChecksizeEntity;
import cn.damei.entity.modules.BizOrderChecksizePic;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import cn.damei.dao.modules.BizOrderChecksizeDao;


@Service
@Transactional(readOnly = true)
public class BizOrderChecksizeService extends CrudService2<BizOrderChecksizeDao, BizOrderChecksizeEntity> {

	public BizOrderChecksizeEntity get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderChecksizeEntity> findList(BizOrderChecksizeEntity bizOrderChecksize) {
		return super.findList(bizOrderChecksize);
	}
	
	public Page<BizOrderChecksizeEntity> findPage(Page<BizOrderChecksizeEntity> page, BizOrderChecksizeEntity bizOrderChecksize) {
		return super.findPage(page, bizOrderChecksize);
	}


	public List<BizOrderChecksizePic> selectPicByOrderChecksizeId(Integer orderChecksizeId) {
		return dao.selectPicByOrderChecksizeId(orderChecksizeId);
	}


	public BizOrderChecksizeEntity selectDetailsByOrderChecksizeId(Integer orderChecksizeId) {
		return dao.selectDetailsByOrderChecksizeId(orderChecksizeId);
	}


	@Transactional(readOnly = false)
	public boolean updateOrderChecksize(BizOrderChecksizeEntity bizOrderChecksize) {
		Date date = new Date();
		User user = UserUtils.getUser();
		bizOrderChecksize.setChecksizeStatus(ConstantUtils.CHECKSIZE_STATUS_20);
		bizOrderChecksize.setChecksizeStatusDatetime(date);
		bizOrderChecksize.setMaterialDepartmentDealDatetime(date);
		if (StringUtils.isNotBlank(user.getId())){
			bizOrderChecksize.setMaterialDepartmentDealEmployeeId(Integer.valueOf(user.getId()));
		}
		bizOrderChecksize.setUpdateBy(user);
		bizOrderChecksize.setUpdateDate(date);
		return (dao.updateOrderChecksize(bizOrderChecksize)) ? true : false;
	}

	public List<BizOrderChecksizeEntity> findMainItem(String storeId, String projectModeValue) {

		return dao.findMainItem(storeId,projectModeValue);
	}
	
	
	
	
	
}