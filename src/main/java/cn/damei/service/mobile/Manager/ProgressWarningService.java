package cn.damei.service.mobile.Manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.ProgressWarningDao;
import cn.damei.entity.mobile.Manager.Progress;
import cn.damei.entity.mobile.Manager.ProgressWarning;
@Service
public class ProgressWarningService extends  CrudService2<ProgressWarningDao,ProgressWarning>{

	@Autowired
	private ProgressWarningDao progressWarningDao;
	public List<ProgressWarning> findDelayMaterialCount(Integer id) {
		List<ProgressWarning> list1 = new ArrayList<>();
		ProgressWarning pg = new ProgressWarning();
		ProgressWarning pg1 = new ProgressWarning();
		pg.setPurchaseType("1");
		pg1.setPurchaseType("5");
		List<ProgressWarning> findDelayMaterialInfo = findDelayMaterialInfo(id,"1");
		List<ProgressWarning> findDelayMaterialInfo2 = findDelayMaterialInfo(id,"5");
		if(findDelayMaterialInfo!=null){
			pg.setCount(findDelayMaterialInfo.size()+"");
		}
		if(findDelayMaterialInfo2!=null){
			pg1.setCount(findDelayMaterialInfo2.size()+"");
		}
		list1.add(pg);
		list1.add(pg1);
		return list1;
	}
	public List<ProgressWarning> findDelayMaterialInfo(Integer id, String purchaseType) {

		List<String> allOrderId = progressWarningDao.findAllDelayOrderId(id);
		if(allOrderId.size() ==0){
			return null;
		}
		ProgressWarning pw = new ProgressWarning();
		pw.setPurchaseType(purchaseType);
		pw.setList(allOrderId);
		List<ProgressWarning> list = progressWarningDao.findDelayMaterialInfo(pw);
		List<Progress> findDelayMaterialsMiss = progressWarningDao.findDelayMaterialsMiss(allOrderId);
		if(findDelayMaterialsMiss!=null){
			for (Progress progress : findDelayMaterialsMiss) {
				String orderId = progress.getOrderId();
				String purchaseType2 = progress.getPurchaseType();
				for (int i = 0; i < list.size(); i++) {
					ProgressWarning progressWarning = list.get(i);
					String purchaseType3 = progressWarning.getPurchaseType();
					String orderId2 = progressWarning.getOrderId();
					if(orderId.equals(orderId2)&&purchaseType3.equals(purchaseType2)){
						list.remove(i);
					}
					
				}
			}
		}
		
		return list;
	}

}
