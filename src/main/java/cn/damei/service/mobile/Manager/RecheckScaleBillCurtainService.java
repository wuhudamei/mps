package cn.damei.service.mobile.Manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.DateUtils;
import cn.damei.dao.mobile.Manager.RecheckScaleBillCurtainDao;
import cn.damei.entity.mobile.Manager.RecheckScaleBillCurtain;


@Service
@Transactional(readOnly = true)
public class RecheckScaleBillCurtainService extends CrudService2<RecheckScaleBillCurtainDao,RecheckScaleBillCurtain>{

	@Autowired
	private RecheckScaleBillCurtainDao recheckScaleBillCurtainDao;

	@Transactional(readOnly = false)
	public void insert(int idKey, String position, String poleType, String poleLength, String clothHeight) {
		RecheckScaleBillCurtain curtain = new RecheckScaleBillCurtain();
		
		curtain.setId(null);
		curtain.setRecheckScaleBillId(idKey);
		curtain.setPosition((position.substring(0,position.indexOf("-"))).trim());
		curtain.setPoleType(poleType);
		curtain.setPoleLength(poleLength);
		curtain.setClothHeight(clothHeight);
		curtain.setRemarks(position.trim());
		curtain.setCreateDate(DateUtils.addDate(new Date(), 0));
		curtain.setUpdateDate(DateUtils.addDate(new Date(), 0));
		curtain.setDelFlag("0");
		
		recheckScaleBillCurtainDao.insert(curtain);
	}

	@Transactional(readOnly = false)
	public String insertCurtain(int idKey, String position, String poleType, String poleLength, String clothHeight, Integer managerID) {
		RecheckScaleBillCurtain curtain = new RecheckScaleBillCurtain();
		
		curtain.setId(null);
		curtain.setRecheckScaleBillId(idKey);
		curtain.setPosition((position.substring(0,position.indexOf("-"))).trim());
		curtain.setPoleType(poleType);
		curtain.setPoleLength(poleLength);
		curtain.setClothHeight(clothHeight);
		curtain.setRemarks(position.trim());
		curtain.setCreateDate(DateUtils.addDate(new Date(), 0));
		curtain.setCreatePeo(managerID.toString());
		curtain.setUpdateDate(DateUtils.addDate(new Date(), 0));
		curtain.setUpdatePeo(managerID.toString());;
		curtain.setDelFlag("0");
		
		return recheckScaleBillCurtainDao.insertCurtain(curtain) ? "0" : "2";
	}


	public List<RecheckScaleBillCurtain> getByRecheckID(Integer recheckIDs) {

		return recheckScaleBillCurtainDao.getByRecheckID(recheckIDs);
	}
	

}
