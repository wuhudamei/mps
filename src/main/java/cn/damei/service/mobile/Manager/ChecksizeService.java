package cn.damei.service.mobile.Manager;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.dao.mobile.Manager.ChecksizeDao;
import cn.damei.entity.mobile.Manager.Checksize;
import cn.damei.entity.mobile.Manager.ChecksizeOrder;
import cn.damei.entity.mobile.Manager.ChecksizePic;
import cn.damei.entity.mobile.Manager.ChecksizeType;


@Service
@Transactional(readOnly = true)
public class ChecksizeService extends CrudService2<ChecksizeDao,Checksize>{


	public List<ChecksizeOrder> findOrderByManagerId(Integer id) {
		return dao.findOrderByManagerId(id);
	}
	

	@Transactional(readOnly=false)
	public Integer saveChecksize(String orderId, String checksizeType, String checksizeDate,Integer checksizeEmployeeId, String remarks) {
		
		
		Checksize checksize = new Checksize();
		checksize.setOrderId(Integer.valueOf(orderId));
		checksize.setOrderInstallItemId(checksizeType);
		checksize.setChecksizeDate(DateUtils.parseDate(checksizeDate));
		checksize.setChecksizeEmployeeId(Integer.valueOf(checksizeEmployeeId));
		checksize.setRemarks(remarks);
		checksize.setChecksizeStatus(ConstantUtils.CHECKSIZE_STATUS_10);
		checksize.setChecksizeStatusDatetime(new Date());
		String str = dao.findInstallItemName(checksizeType);
		String substring = str.substring(0, 2);
		
		String value = dao.findDictValue(substring);
		checksize.setChecksizeType(value);;
		checksize.preInsert();
		
		dao.saveChecksize(checksize);
		
		return checksize.getId();
	}
	

	@Transactional(readOnly=false)
	public void saveChecksizePic(ChecksizePic checksizePic) {
		dao.saveChecksizePic(checksizePic);
		
	}


	public List<ChecksizeType> findType(Integer id) {
		return dao.findType(id);
	}

	public ChecksizeOrder findOrder(Integer orderId) {
		return dao.findOrder(orderId);
	}

	public String findcheck(String checksizeType) {
		return dao.findcheck(checksizeType);
	}


	@Transactional(readOnly=false)
	public void saveChecksizePicAll(List<ChecksizePic> pList) {
		dao.saveChecksizePicAll(pList);
		
	}


	public Checksize findTimeSpan(int id) {
		return dao.findTimeSpan(id);
	}

	public Date findByOrderIdAndType(int orderId, String type) {

		return dao.findByOrderIdAndType(orderId,type);
	}


	public List<Checksize> findCheckSizeList(Integer id) {
		return dao.findCheckSizeList(id);
	}

	public String findDaysPlanChecksize(String checksizeType) {

		return dao.findDaysPlanChecksize(checksizeType);
	}


	public Checksize findChecksizeCanApplyDate(String checksizeType) {
		return dao.findChecksizeCanApplyDate(checksizeType);
	}

}
