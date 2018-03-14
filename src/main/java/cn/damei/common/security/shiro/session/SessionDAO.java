package cn.damei.common.security.shiro.session;

import java.util.Collection;

import org.apache.shiro.session.Session;

public interface SessionDAO extends org.apache.shiro.session.mgt.eis.SessionDAO {


	public Collection<Session> getActiveSessions(boolean includeLeave);
	

	public Collection<Session> getActiveSessions(boolean includeLeave, Object principal, Session filterSession);
	
}
