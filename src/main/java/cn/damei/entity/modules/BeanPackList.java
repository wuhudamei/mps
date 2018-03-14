package cn.damei.entity.modules;

import java.io.Serializable;
import java.util.List;

import cn.damei.entity.modules.OrderTaskpackage;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年10月18日 上午10:35:15 
* 类说明 
*/

public class BeanPackList  implements   Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
private  List<OrderTaskpackage> packList;

public List<OrderTaskpackage> getPackList() {
	return packList;
}

public void setPackList(List<OrderTaskpackage> packList) {
	this.packList = packList;
}

@Override
public String toString() {
	return "BeanPackList [packList=" + packList + "]";
}

}
