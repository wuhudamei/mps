
package cn.damei.service.modules;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;


public class ActGroupEntityServiceFactory implements SessionFactory {
	
	@Autowired
	private ActGroupEntityService actGroupEntityService;
	
	public Class<?> getSessionType() {

		return GroupIdentityManager.class;
	}

	public Session openSession() {

		return actGroupEntityService;
	}

}
