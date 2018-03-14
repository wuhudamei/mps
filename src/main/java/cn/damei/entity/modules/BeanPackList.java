package cn.damei.entity.modules;

import java.io.Serializable;
import java.util.List;

import cn.damei.entity.modules.OrderTaskpackage;



public class BeanPackList  implements   Serializable{


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
