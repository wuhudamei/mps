package cn.damei.service.mobile.Manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.DateUtils;
import cn.damei.dao.mobile.Manager.BusinessPicDao;
import cn.damei.entity.mobile.Manager.BusinessPic;

/**
 * 公共上传图片
 * @author llp
 */
@Service
@Transactional(readOnly = true)
public class BusinessPicService extends CrudService2<BusinessPicDao, BusinessPic>{
	
	@Autowired
	private BusinessPicDao businessPicDao;
	
	@Transactional(readOnly = false)
	public void insertPic(String photos, String type, int finshProjectBillId, String picUrl) {
		Date nowDate = DateUtils.addDate(new Date(), 0);
		BusinessPic pic = new BusinessPic();
		
		pic.setId(null);
		pic.setBusinessType(type);
		pic.setBusinessIdInt(finshProjectBillId);
		pic.setBusinessIdVarchar(null);
		pic.setPicType("1");
		pic.setPicIndex(null);
		pic.setPicUrl(picUrl);
		pic.setPicDatetime(nowDate);
		pic.setRemarks(null);
		pic.setCreateDate(nowDate);
		pic.setUpdateDate(nowDate);
		pic.setDelFlag("0");
		
		businessPicDao.insertPic(pic);
	}

	/**
	 * 根据biz_order_finish_project_bill的主键查找
	 * biz_business_pic下的所有数据
	 * 关联条件biz_business_pic.business_id_int=biz_order_finish_project_bill.id
	 * @param id
	 * @return List
	 */
	public List<BusinessPic> queryByBusinessID(Integer businessID) {
		// TODO Auto-generated method stub
		return businessPicDao.queryByBusinessID(businessID);
	}

	@Transactional(readOnly = false)
	public String insertPhotos(String photos, String type, int finshProjectBillId, String picUrl) {
		Date nowDate = DateUtils.addDate(new Date(), 0);
		BusinessPic pic = new BusinessPic();
		
		pic.setId(null);
		pic.setBusinessType(type);
		pic.setBusinessIdInt(finshProjectBillId);
		pic.setBusinessIdVarchar(null);
		pic.setPicType("1");
		pic.setPicIndex(null);
		pic.setPicUrl(picUrl);
		pic.setPicDatetime(nowDate);
		pic.setRemarks(null);
		pic.setCreateDate(nowDate);
		pic.setUpdateDate(nowDate);
		pic.setDelFlag("0");
		
		return businessPicDao.insertPhotos(pic) ? "0" : "3";
	}

	/**
	 * 获取所有图片
	 * @param taokouKey、recheckID
	 * @return List
	 */
	public List<BusinessPic> getByBusType(String businessType, Integer businessID) {
		// TODO Auto-generated method stub
		return businessPicDao.getByBusType(businessType, businessID);
	}


}
