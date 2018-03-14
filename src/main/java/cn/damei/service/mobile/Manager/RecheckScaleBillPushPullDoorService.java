package cn.damei.service.mobile.Manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.DateUtils;
import cn.damei.dao.mobile.Manager.RecheckScaleBillPushPullDoorDao;
import cn.damei.entity.mobile.Manager.RecheckScaleBillPushPullDoor;

/**
 * 上报复尺(20161107-20161113)
 * 
 * @author llp 2016-11-15
 */
@Service
@Transactional(readOnly = true)
public class RecheckScaleBillPushPullDoorService
		extends CrudService2<RecheckScaleBillPushPullDoorDao, RecheckScaleBillPushPullDoor> {

	@Autowired
	private RecheckScaleBillPushPullDoorDao recheckScaleBillPushpullDoorDao;

	@Transactional(readOnly = false)
	public void insert(int idKey, String position, String pushPullStyle, String packageCover, String openDirection, String doorAmount,
			String newDoorWidth, String newDoorHeight, String width, String height, String thickness) {
		RecheckScaleBillPushPullDoor door = new RecheckScaleBillPushPullDoor();

		door.setId(null);
		door.setRecheckScaleBillId(idKey);
		door.setPosition((position.substring(0, position.indexOf("-"))).trim());
		door.setPushPullStyle(pushPullStyle);
		door.setPackageCover(packageCover);
		door.setOpenDirection(openDirection);
		door.setDoorAmount(doorAmount);
		door.setNewDoorWidth(newDoorWidth);
		door.setNewDoorHeight(newDoorHeight);
		door.setWidth(width);
		door.setHeight(height);
		door.setThickness(thickness);
		door.setRemarks(position.trim());
		door.setCreateDate(DateUtils.addDate(new Date(), 0));
		door.setUpdateDate(DateUtils.addDate(new Date(), 0));
		door.setDelFlag("0");

		recheckScaleBillPushpullDoorDao.insert(door);
	}
	
	@Transactional(readOnly = false)
	public String insertpush(int idKey, String position, String pushPullStyle, String packageCover, String openDirection, String doorAmount,
			String newDoorWidth, String newDoorHeight, String width, String height, String thickness, Integer managerID) {
		RecheckScaleBillPushPullDoor door = new RecheckScaleBillPushPullDoor();

		door.setId(null);
		door.setRecheckScaleBillId(idKey);
		door.setPosition((position.substring(0, position.indexOf("-"))).trim());
		door.setPushPullStyle(pushPullStyle);
		door.setPackageCover(packageCover);
		door.setOpenDirection(openDirection);
		door.setDoorAmount(doorAmount);
		door.setNewDoorWidth(newDoorWidth);
		door.setNewDoorHeight(newDoorHeight);
		door.setWidth(width);
		door.setHeight(height);
		door.setThickness(thickness);
		door.setRemarks(position.trim());
		door.setCreateDate(DateUtils.addDate(new Date(), 0));
		door.setCreatePeo(managerID.toString());
		door.setUpdateDate(DateUtils.addDate(new Date(), 0));
		door.setUpdatePeo(managerID.toString());
		door.setDelFlag("0");

		return recheckScaleBillPushpullDoorDao.insertpush(door) ? "0" : "2";
	}

	public List<RecheckScaleBillPushPullDoor> getByRecheckID(Integer recheckIDs) {
		// TODO Auto-generated method stub
		return recheckScaleBillPushpullDoorDao.getByRecheckID(recheckIDs);
	}

}
