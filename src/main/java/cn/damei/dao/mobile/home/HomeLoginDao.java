package cn.damei.dao.mobile.home;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.home.CustomerVo;

import java.util.List;
import java.util.Map;


@MyBatisDao
public interface HomeLoginDao  {
	

	 Integer selectRecordPhone(CustomerVo vo);


	List<Map<String,String>> findUnReadReportCountByCustomerPhone(String customerPhone);
	 List<Integer> findProgressCountByCustomerPhone(String customerPhone);
	List<Integer> findProjectChangeCountByCustomerPhone(String customerPhone);
	List<Integer> findEvalCountByCustomerPhone(String customerPhone);
	List<Integer> findProjectProgressCountByCustomerPhone(String customerPhone);


	int commonViewLogCountByBusinessIntId(Map<String,Object> map);

}
