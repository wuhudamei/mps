package cn.damei.entity.mobile.Manager;

public class Progress {
	private String orderId;
	private String purchaseType;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	public Progress() {
		super();
	} 
	@Override
	public  int hashCode(){
		int code = orderId.hashCode() * 31 + purchaseType.hashCode() * 31;
		return code;
	}
	@Override
	  public boolean equals(Object obj) {
		Progress d1 = null;
	    if (obj instanceof Progress) {
	    	d1 = (Progress) obj;
	      if (d1.orderId.equals(orderId) && d1.purchaseType.equals(purchaseType) ) 
	        return true;
	      else 
	        return false;
	    }else 
	      return false;
	  }
}
