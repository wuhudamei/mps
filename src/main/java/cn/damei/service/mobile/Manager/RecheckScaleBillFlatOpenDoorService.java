package cn.damei.service.mobile.Manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.DateUtils;
import cn.damei.dao.mobile.Manager.RecheckScaleBillFlatOpenDoorDao;
import cn.damei.entity.mobile.Manager.RecheckScaleBillFlatOpenDoor;


@Service
@Transactional(readOnly = true)
public class RecheckScaleBillFlatOpenDoorService
		extends CrudService2<RecheckScaleBillFlatOpenDoorDao, RecheckScaleBillFlatOpenDoor> {

	@Autowired
	private RecheckScaleBillFlatOpenDoorDao recheckScaleBillFlatOpenDoorDao;

	@Transactional(readOnly = false)
	public String insertDoor(int idKey, String position, String packageCover, String inOutOpen,  String openDirection, String width, 
			String height, String thickness, Integer managerID) {
		RecheckScaleBillFlatOpenDoor openDoor = new RecheckScaleBillFlatOpenDoor();

		openDoor.setId(null);
		openDoor.setRecheckScaleBillId(idKey);
		openDoor.setPosition((position.substring(0, position.indexOf("-"))).trim());
		openDoor.setPackageCover(packageCover);
		openDoor.setInOutOpen(inOutOpen);
		openDoor.setOpenDirection(openDirection);
		openDoor.setWidth(width);
		openDoor.setHeight(height);
		openDoor.setThickness(thickness);
		openDoor.setRemarks(position.trim());
		openDoor.setCreateDate(DateUtils.addDate(new Date(), 0));
		openDoor.setCreatePeo(managerID.toString());
		openDoor.setUpdateDate(DateUtils.addDate(new Date(), 0));
		openDoor.setUpdatePeo(managerID.toString());
		openDoor.setDelFlag("0");

		return recheckScaleBillFlatOpenDoorDao.insertDoor(openDoor) ? "0" : "2";
	}

	public List<RecheckScaleBillFlatOpenDoor> getByRecheckID(Integer recheckIDs) {

		return recheckScaleBillFlatOpenDoorDao.getByRecheckID(recheckIDs);
	}

}
