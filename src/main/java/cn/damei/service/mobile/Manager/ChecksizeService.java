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

/**
 * 上报复尺
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true)
public class ChecksizeService extends CrudService2<ChecksizeDao,Checksize>{

	//根据项目经理id查询项目经理下的状态小于300的所有订单
	public List<ChecksizeOrder> findOrderByManagerId(Integer id) {
		return dao.findOrderByManagerId(id);
	}
	
	//保存上报复尺信息
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
	
	//保存复尺图片
	@Transactional(readOnly=false)
	public void saveChecksizePic(ChecksizePic checksizePic) {
		dao.saveChecksizePic(checksizePic);
		
	}

	//查询复尺类型
	public List<ChecksizeType> findType(Integer id) {
		return dao.findType(id);
	}

	public ChecksizeOrder findOrder(Integer orderId) {
		return dao.findOrder(orderId);
	}

	public String findcheck(String checksizeType) {
		return dao.findcheck(checksizeType);
	}

	/**
	 * 批量插入上报厂家复尺图片
	 * @param pList
	 */
	@Transactional(readOnly=false)
	public void saveChecksizePicAll(List<ChecksizePic> pList) {
		dao.saveChecksizePicAll(pList);
		
	}

	/**
	 * 查询该订单最新一次申请厂家复尺的时间是否间隔有5分钟
	 * @param id
	 * @return
	 */
	public Checksize findTimeSpan(int id) {
		return dao.findTimeSpan(id);
	}

	public Date findByOrderIdAndType(int orderId, String type) {
		// TODO Auto-generated method stub
		return dao.findByOrderIdAndType(orderId,type);
	}

	/**
	 * 根据订单ID查询所有的厂家复尺
	 * @param id
	 * @return
	 */
	public List<Checksize> findCheckSizeList(Integer id) {
		return dao.findCheckSizeList(id);
	}

	public String findDaysPlanChecksize(String checksizeType) {
		// TODO Auto-generated method stub
		return dao.findDaysPlanChecksize(checksizeType);
	}

	/**
	 * 校验：该工地2017-7-1开工，按照工程部规定主材（橱柜、台面）开工10天后（2017-7-21）才可以申请橱柜复尺，如有提前完工或疑问请联系大区经理。
	 * @param checksizeType
	 * @return
	 */
	public Checksize findChecksizeCanApplyDate(String checksizeType) {
		return dao.findChecksizeCanApplyDate(checksizeType);
	}

}
