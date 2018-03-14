package cn.damei.dao.mobile.Inspector;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.InspectSign;
import cn.damei.entity.mobile.Inspector.RecheckEntity;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;


@MyBatisDao
public interface RecheckDao {


	
	public List<RecheckEntity>  findRecheckList(Integer   inspectId);
	
	
	

		public String getOrderLngLatByOrderId(Integer orderId);


		public void inspectorSign(InspectSign inspectSign);


		public Integer findInspectSignRecord(Integer inspectId);


		public void updateInspectRecord(InspectSign InspectSign);
		
		
		

		public List<RecheckEntity> findRecheckPic(Integer recheckId);

		public List<RecheckEntity> findRecheckCheckItem(Integer recheckId);
		
		
		

		public void deletePic(Integer picId);

		
		
		

		public void savePic(ReportCheckDetailsPic p);



		public void updateRecheckCheckItem(RecheckEntity entity);



		public RecheckEntity findRecheckById(Integer recheckId);



		public void updateRecheck(RecheckEntity recheckEntity);
}
