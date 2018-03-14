package cn.damei.service.mobile.Manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.DateUtils;
import cn.damei.dao.mobile.Manager.RecheckScaleBillTaokouDao;
import cn.damei.entity.mobile.Manager.RecheckScaleBillTaokou;


@Service
@Transactional(readOnly = true)
public class RecheckScaleBillTaokouService extends CrudService2<RecheckScaleBillTaokouDao,RecheckScaleBillTaokou>{

	@Autowired
	private RecheckScaleBillTaokouDao recheckScaleBillTaokouDao;

	@Transactional(readOnly = false)
	public void insert(int idKey, String position, String packageCover, String holeWidth, String holeHigh, String thick) {
		RecheckScaleBillTaokou taokou = new RecheckScaleBillTaokou();
		
		taokou.setId(null);
		taokou.setRecheckScaleBillId(idKey);
		taokou.setPosition((position.substring(0,position.indexOf("-"))).trim());
		taokou.setPackageCover(packageCover);
		taokou.setHoleWidth(holeWidth);
		taokou.setHoleHeight(holeHigh);
		taokou.setHoleThickness(thick);
		taokou.setRemarks(position.trim());
		taokou.setCreateDate(DateUtils.addDate(new Date(), 0));
		taokou.setUpdateDate(DateUtils.addDate(new Date(), 0));
		taokou.setDelFlag("0");
		
		recheckScaleBillTaokouDao.insert(taokou);
	}
	
	@Transactional(readOnly = false)
	public String insertTaokou(int idKey, String position, String packageCover, String holeWidth, String holeHigh, String thick, Integer managerID) {
		RecheckScaleBillTaokou taokou = new RecheckScaleBillTaokou();
		
		taokou.setId(null);
		taokou.setRecheckScaleBillId(idKey);
		taokou.setPosition((position.substring(0,position.indexOf("-"))).trim());
		taokou.setPackageCover(packageCover);
		taokou.setHoleWidth(holeWidth);
		taokou.setHoleHeight(holeHigh);
		taokou.setHoleThickness(thick);
		taokou.setRemarks(position.trim());
		taokou.setCreateDate(DateUtils.addDate(new Date(), 0));
		taokou.setCreatePeo(managerID.toString());
		taokou.setUpdateDate(DateUtils.addDate(new Date(), 0));
		
		taokou.setDelFlag("0");
		
		return recheckScaleBillTaokouDao.insertTaokou(taokou) ? "0" : "2";
	}


	public List<RecheckScaleBillTaokou> getByRecheckID(Integer recheckID) {

		return recheckScaleBillTaokouDao.getByRecheckID(recheckID);
	}
	
}
