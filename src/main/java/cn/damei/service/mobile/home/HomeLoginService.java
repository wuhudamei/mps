package cn.damei.service.mobile.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.entity.mobile.home.CustomerVo;
import cn.damei.dao.mobile.home.HomeLoginDao;

import java.util.List;
import java.util.Map;


@Service
@Transactional(readOnly=true)
public class HomeLoginService {
	@Autowired
	private HomeLoginDao dao;
	
public Integer selectRecordPhone(CustomerVo vo) {
		
		return dao.selectRecordPhone(vo);
	}


	public List<Map<String,String>> findUnReadReportCountByCustomerPhone(String customerPhone){

		return dao.findUnReadReportCountByCustomerPhone(customerPhone);
	}
	public List<Integer> findProgressCountByCustomerPhone(String customerPhone){

		return dao.findProgressCountByCustomerPhone(customerPhone);
	}
	public List<Integer> findProjectChangeCountByCustomerPhone(String customerPhone){

		return dao.findProjectChangeCountByCustomerPhone(customerPhone);
	}
	public List<Integer> findEvalCountByCustomerPhone(String customerPhone){

		return dao.findEvalCountByCustomerPhone(customerPhone);
	}
	public List<Integer> findProjectProgressCountByCustomerPhone(String customerPhone){


		return dao.findProjectProgressCountByCustomerPhone(customerPhone);
	}



	public int commonViewLogCountByBusinessIntId(Map<String,Object> map){

		return dao.commonViewLogCountByBusinessIntId(map);
	}
}
