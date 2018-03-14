
package cn.damei.common.supcan.freeform;

import cn.damei.common.supcan.common.Common;
import cn.damei.common.supcan.common.properties.Properties;
import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("FreeForm")
public class FreeForm extends Common {

	public FreeForm() {
		super();
	}
	
	public FreeForm(Properties properties) {
		this();
		this.properties = properties;
	}
	
}
