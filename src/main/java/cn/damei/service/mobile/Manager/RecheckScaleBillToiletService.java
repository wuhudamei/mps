package cn.damei.service.mobile.Manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.DateUtils;
import cn.damei.dao.mobile.Manager.RecheckScaleBillToiletDao;
import cn.damei.entity.mobile.Manager.RecheckScaleBillToilet;

/**
 * 上报复尺(20161107-20161113)
 * @author llp
 * 2016-11-15
 */
@Service
@Transactional(readOnly = true)
public class RecheckScaleBillToiletService extends CrudService2<RecheckScaleBillToiletDao,RecheckScaleBillToilet>{

	@Autowired
	private RecheckScaleBillToiletDao recheckScaleBillToiletDao;

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
	public String insertToilet(int idKey, String position, String closestoolHoleSize, Integer managerID) {
		RecheckScaleBillToilet toilet = new RecheckScaleBillToilet();
		
		toilet.setId(null);
		toilet.setRecheckScaleBillId(idKey);
		toilet.setPosition((position.substring(0,position.indexOf("-"))).trim());
		toilet.setClosestoolHoleSize(closestoolHoleSize.trim());
		toilet.setRemarks(position.trim());
		toilet.setCreateDate(DateUtils.addDate(new Date(), 0));
		toilet.setCreatePeo(managerID.toString());
		toilet.setUpdateDate(DateUtils.addDate(new Date(), 0));
		toilet.setUpdatePeo(managerID.toString());
		toilet.setDelFlag("0");
		
		return recheckScaleBillToiletDao.insertToilet(toilet) ? "0" : "2";
	}

	public List<RecheckScaleBillToilet> getByRecheckID(Integer recheckIDs) {
		// TODO Auto-generated method stub
		return recheckScaleBillToiletDao.getByRecheckID(recheckIDs);
	}
	
}
