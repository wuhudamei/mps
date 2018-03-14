package cn.damei.service.mobile.Manager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.dao.mobile.Manager.SysTokenDao;
import cn.damei.entity.mobile.Manager.SysToken;
@Service
@Transactional(readOnly = true)
public class SysTokenService extends CrudService2<SysTokenDao, SysToken>{

	public SysToken findByTokenId(String tokenid,String appType) {
		// TODO Auto-generated method stub
		return dao.findByTokenId(tokenid,appType);
	}
	
	/**
	 * 根据tokenid获取业主端登陆手机号
	 * @param tokenid
	 * @return
	 */
	public SysToken findByTokenIdForIndex(String tokenid) {
		return dao.findByTokenIdForIndex(tokenid,ConstantUtils.EMP_TYPE_4);
	}
	
	@Transactional(readOnly = false)
	public void add(SysToken sysToken) {
		SysToken token = dao.finByPhone(sysToken.getPhoneNumber(),sysToken.getAppType());
		if(token != null){
			token.setTokenid(sysToken.getTokenid());
			token.preUpdate();
			dao.update(token);
		}else{
			sysToken.preInsert();
			dao.insert(sysToken);
		}
	}
	@Transactional(readOnly = false)
	public void update(SysToken token) {
		token.preUpdate();
		dao.update(token);
	}
	
	@Transactional(readOnly = false)
	public void deleteByPhone(String phone,String appType) {
		dao.deleteByPhone(phone,appType);
	}

}
