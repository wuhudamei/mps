package cn.damei.entity.mobile.home;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(value={"checkItem"})
public class CheckBreak implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private String breakDescribe;
	private String isRequiredRemarks;
	private String breakRemarks;
	
	private CheckItem checkItem;

	public String getBreakDescribe() {
		return breakDescribe;
	}

	public void setBreakDescribe(String breakDescribe) {
		this.breakDescribe = breakDescribe;
	}

	public String getIsRequiredRemarks() {
		return isRequiredRemarks;
	}

	public void setIsRequiredRemarks(String isRequiredRemarks) {
		this.isRequiredRemarks = isRequiredRemarks;
	}

	public String getBreakRemarks() {
		return breakRemarks;
	}

	public void setBreakRemarks(String breakRemarks) {
		this.breakRemarks = breakRemarks;
	}

	public CheckItem getCheckItem() {
		return checkItem;
	}

	public void setCheckItem(CheckItem checkItem) {
		this.checkItem = checkItem;
	}
	
	
	
}
