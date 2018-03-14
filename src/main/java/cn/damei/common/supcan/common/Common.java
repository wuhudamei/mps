
package cn.damei.common.supcan.common;

import java.util.List;

import com.google.common.collect.Lists;
import cn.damei.common.supcan.common.fonts.Font;
import cn.damei.common.supcan.common.properties.Properties;
import cn.damei.common.utils.IdGen;
import com.thoughtworks.xstream.annotations.XStreamAlias;


public class Common {


	@XStreamAlias("Properties")
	protected Properties properties;
	

	@XStreamAlias("Fonts")
	protected List<Font> fonts;

	public Common() {
		properties = new Properties(IdGen.uuid());
		fonts = Lists.newArrayList(
				new Font("宋体", "134", "-12"),
				new Font("宋体", "134", "-13", "700"));
	}
	
	public Common(Properties properties) {
		this();
		this.properties = properties;
	}
	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public List<Font> getFonts() {
		return fonts;
	}

	public void setFonts(List<Font> fonts) {
		this.fonts = fonts;
	}

}
