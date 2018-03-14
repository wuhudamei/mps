package cn.damei.common.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartZJobTaskPackage {
	
	private static Logger logger = LoggerFactory.getLogger(QuartZJobTaskPackage.class);
	
	protected void execute() {
		 logger.info("---------------------QuartZ开始-------------");
		 
		 
		 logger.info("---------------------QuartZ结束-------------");
	}
}
