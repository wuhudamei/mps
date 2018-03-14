package cn.damei.common.utils;

import java.util.Comparator;

import cn.damei.entity.modules.WorkgroupVo;



public class CompareUtils  implements Comparator<WorkgroupVo> {
	public int compare(WorkgroupVo s1,WorkgroupVo s2) {
		double address1 =Double.parseDouble( s1.getAddressToWorkerAddressDistance());
		double address2 = Double.parseDouble(s2.getAddressToWorkerAddressDistance());
		if(address1>address2){
			return 1;
		}else if(address1<address2){
			return -1;
		}else{
			return 0;
		}
	}
}
