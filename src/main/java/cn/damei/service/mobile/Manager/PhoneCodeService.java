package cn.damei.service.mobile.Manager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.PhoneCodeDao;
import cn.damei.entity.mobile.Manager.PhoneCode;
@Service
@Transactional(readOnly = true)
public class PhoneCodeService extends CrudService2<PhoneCodeDao, PhoneCode>{

	public PhoneCode findByUsernameAndCode(String username, String code, String appType) {

		return dao.findByUsernameAndCode(username,code,appType);
	}
	
	@Transactional(readOnly = false)
	public void add(PhoneCode phoneCode) {
		PhoneCode code = dao.findByPhone(phoneCode.getPhoneNumber(),phoneCode.getAppType());
		if(code != null){
			code.setPhoneCode(phoneCode.getPhoneCode());
			code.preUpdate();
			dao.update(code);
		}else{
			phoneCode.preInsert();
			dao.insert(phoneCode);
		}
	}
}
