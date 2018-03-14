
package cn.damei.service.modules;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;


public class ActUserEntityServiceFactory implements SessionFactory {
	
	@Autowired
	private ActUserEntityService actUserEntityService;

	public Class<?> getSessionType() {

		return UserIdentityManager.class;
	}

	public Session openSession() {

		return actUserEntityService;
	}

}
