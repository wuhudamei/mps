package cn.damei.service.mobile.home;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.dao.mobile.home.BroadCastDao;
import cn.damei.entity.mobile.home.BroadCastCodeEntity;
import cn.damei.entity.mobile.home.BroadCastPicEntity;


@Service
@Transactional(readOnly = true)
public class BroadCastService {

	@Autowired
	private BroadCastDao dao;


	@Transactional(readOnly = false)
	public void saveBroadCastRecord(BroadCastPicEntity entity) {

		dao.saveBroadCastRecord(entity);
	}


	@Transactional(readOnly = false)
	public void saveBroadCastPic(BroadCastPicEntity entity) {

		dao.saveBroadCastPic(entity);
	}
	@Transactional(readOnly = false)
	public void saveCode() {
		dao.saveCode();

	}

	public BroadCastCodeEntity getCode() {

		return dao.getCode();
	}
	@Transactional(readOnly = false)
	public void updateCode(BroadCastCodeEntity code) {

		dao.updateCode(code);
	}
	

	@Transactional(readOnly = false)
	public String getBroadCastCode() {

			
		StringBuilder builder = new StringBuilder();


		BroadCastCodeEntity code1 = dao.getCode();

		if (null == code1) {
			dao.saveCode();
			code1 = dao.getCode();
		}
		builder.append(code1.getBussinessType());

		String format = new SimpleDateFormat("yyyyMMdd").format(new Date());
		if (!new SimpleDateFormat("yyyyMMdd").format(code1.getGenerateTime()).equals(format)) {
			code1.setGenerateTime(new Date());
		}
		builder.append(new SimpleDateFormat("yyyyMMdd").format(code1.getGenerateTime()));
		code1.setLastSeiralnum((code1.getLastSeiralnum() + 1));
dao.updateCode(code1);
		String code = String.valueOf(code1.getLastSeiralnum());

		if (code.length() == 1) {

			builder.append("000").append(code);

		} else if (code.length() == 2) {

			builder.append("00").append(code);
		} else if (code.length() == 3) {
			builder.append("0").append(code);
		} else if (code.length() == 4) {
			builder.append(code);
		}


		return builder.toString();

	}
	
	

	@Transactional(readOnly = false)
	public Integer saveBroadCast(Integer orderId, String[] photos, Manager manager) {
		
		Date date = new Date();
		
		BroadCastPicEntity bc = new BroadCastPicEntity();
			
        bc.setOrderId(orderId);
        bc.setRelatedBusinessId(orderId);
        bc.setBroadCastName("确认开工");
        bc.setCusBroadCastType("2");
        bc.setCusBroadCastCode(getBroadCastCode());
        bc.setPicCount(photos.length);
        bc.setApplyEmployeeId(manager.getId());
        bc.setApplyDate(new Date());
        bc.setStatus("10");
        bc.setStatusTime(date);
        bc.setCreateBy(manager.getId());
        bc.setCreateDate(date);
        bc.setDelFlag("0");
        dao.saveBroadCastRecord(bc);
		
		return bc.getId();
	}
	
	
}
