
package cn.damei.common.supcan.common.fonts;

import cn.damei.common.supcan.annotation.common.fonts.SupFont;
import cn.damei.common.utils.ObjectUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;


@XStreamAlias("Font")
public class Font {


	@XStreamAsAttribute
	private String faceName;


	@XStreamAsAttribute
	private String charSet;


	@XStreamAsAttribute
	private String height;
	

	@XStreamAsAttribute
	private String weight;


	@XStreamAsAttribute
	private String width;
	

	@XStreamAsAttribute
	private String italic;
	

	@XStreamAsAttribute
	private String underline;
	
	public Font() {
		
	}

	public Font(SupFont supFont) {
		this();
		ObjectUtils.annotationToObject(supFont, this);
	}
	
	public Font(String faceName) {
		this();
		this.faceName = faceName;
	}
	
	public Font(String faceName, String charSet, String height) {
		this(faceName);
		this.charSet = charSet;
		this.height = height;
	}
	
	public Font(String faceName, String charSet, String height, String weight) {
		this(faceName, charSet, height);
		this.weight = weight;
	}

	public String getFaceName() {
		return faceName;
	}

	public void setFaceName(String faceName) {
		this.faceName = faceName;
	}

	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getItalic() {
		return italic;
	}

	public void setItalic(String italic) {
		this.italic = italic;
	}

	public String getUnderline() {
		return underline;
	}

	public void setUnderline(String underline) {
		this.underline = underline;
	}
	
}
