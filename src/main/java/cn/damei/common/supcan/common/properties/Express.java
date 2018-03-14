
package cn.damei.common.supcan.common.properties;

import cn.damei.common.supcan.annotation.common.properties.SupExpress;
import cn.damei.common.utils.ObjectUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;


@XStreamAlias("Express")
@XStreamConverter(value = ToAttributedValueConverter.class, strings = {"text"})
public class Express {


	@XStreamAsAttribute
	private String isOpt;
	

	private String text;

	public Express() {
		
	}
	
	public Express(SupExpress supExpress) {
		this();
		ObjectUtils.annotationToObject(supExpress, this);
	}
	
	public Express(String text) {
		this.text = text;
	}
	
	public Express(String name, String text) {
		this(name);
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIsOpt() {
		return isOpt;
	}

	public void setIsOpt(String isOpt) {
		this.isOpt = isOpt;
	}
	
}
