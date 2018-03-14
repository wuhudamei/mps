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

/**
 * 上报复尺(20161107-20161113)
 * @author llp
 * 2016-11-15
 * 浴室柜复尺
 * BIZ_RECHECK_SCALE_BILL_BATHROOM_CABINET
 */
@Service
@Transactional(readOnly = true)
public class RecheckScaleBillRoomCabinetService extends CrudService2<RecheckScaleBillRoomCabinetDao,RecheckScaleBillRoomCabinet>{

	@Autowired
	private RecheckScaleBillRoomCabinetDao recheckScaleBillRoomCabinetDao;

	/*@Transactional(readOnly = false)
	public void insert(int idKey, String position, String packageCover, String holeWidth, String holeHigh, String thick) {
		RecheckScaleBillToilet taokou = new RecheckScaleBillToilet();
		
		taokou.setId(null);
		taokou.setRecheckScaleBillId(idKey);
		taokou.setPosition((position.substring(0,position.indexOf("-"))).trim());
		taokou.setRemarks(position.trim());
		taokou.setCreateDate(DateUtils.addDate(new Date(), 0));
		taokou.setUpdateDate(DateUtils.addDate(new Date(), 0));
		taokou.setDelFlag("0");
		
		recheckScaleBillToiletDao.insert(taokou);
	}*/
	
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
		// TODO Auto-generated method stub
		return recheckScaleBillRoomCabinetDao.getByRecheckID(recheckIDs);
	}
	
}
