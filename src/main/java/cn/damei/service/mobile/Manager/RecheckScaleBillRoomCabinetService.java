package cn.damei.service.mobile.Manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.DateUtils;
import cn.damei.dao.mobile.Manager.RecheckScaleBillRoomCabinetDao;
import cn.damei.entity.mobile.Manager.RecheckScaleBillRoomCabinet;


@Service
@Transactional(readOnly = true)
public class RecheckScaleBillRoomCabinetService extends CrudService2<RecheckScaleBillRoomCabinetDao,RecheckScaleBillRoomCabinet>{

	@Autowired
	private RecheckScaleBillRoomCabinetDao recheckScaleBillRoomCabinetDao;


	
	@Transactional(readOnly = false)
	public String insertToilet(int idKey, String position, String bathroomCabinetModel, Integer managerID) {
		RecheckScaleBillRoomCabinet cabinet = new RecheckScaleBillRoomCabinet();
		
		cabinet.setId(null);
		cabinet.setRecheckScaleBillId(idKey);
		cabinet.setPosition((position.substring(0,position.indexOf("-"))).trim());
		cabinet.setBathroomCabinetModel(bathroomCabinetModel.trim());
		cabinet.setRemarks(position.trim());
		cabinet.setCreateDate(DateUtils.addDate(new Date(), 0));
		cabinet.setCreatePeo(managerID.toString());
		cabinet.setUpdateDate(DateUtils.addDate(new Date(), 0));
		cabinet.setUpdatePeo(managerID.toString());
		cabinet.setDelFlag("0");
		
		return recheckScaleBillRoomCabinetDao.insertCabinet(cabinet) ? "0" : "2";
	}

	public List<RecheckScaleBillRoomCabinet> getByRecheckID(Integer recheckIDs) {

		return recheckScaleBillRoomCabinetDao.getByRecheckID(recheckIDs);
	}
	
}
