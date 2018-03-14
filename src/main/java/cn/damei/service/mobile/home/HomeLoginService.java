package cn.damei.service.mobile.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.entity.mobile.home.CustomerVo;
import cn.damei.dao.mobile.home.HomeLoginDao;

import java.util.List;
import java.util.Map;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年10月24日 下午3:47:59 
* 质检登录系统首页
*/
@Service
@Transactional(readOnly=true)
public class HomeLoginService {
	@Autowired
	private HomeLoginDao dao;
	
public Integer selectRecordPhone(CustomerVo vo) {
		
		return dao.selectRecordPhone(vo);
	}

	/**
	 * 客户未读信息
	 * @param customerPhone
	 * @return
	 */
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


	/**
	 * view_log数据过大, 分离function
	 * 参数: 关联id List ,businessType
	 *
	 */
	public int commonViewLogCountByBusinessIntId(Map<String,Object> map){

		return dao.commonViewLogCountByBusinessIntId(map);
	}
}
