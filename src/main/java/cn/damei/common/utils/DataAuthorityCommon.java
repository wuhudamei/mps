package cn.damei.common.utils;

import java.util.List;

import cn.damei.entity.modules.DataAuthorityEntityDisclose;
import cn.damei.service.modules.BizProjectChangeBillService;
import cn.damei.entity.modules.Office;
import cn.damei.entity.modules.Role;
import cn.damei.entity.modules.User;

public class DataAuthorityCommon {

	
	public static DataAuthorityEntityDisclose dataAuthority(DataAuthorityEntityDisclose bizOrderDisclose, BizProjectChangeBillService bizProjectChangeBillService){
		
		User user = UserUtils.getUser();
		
		List<Role> roleList = user.getRoleList();
		Role role2 = roleList.get(0);
		
		String string = role2.getId();
		


		
		

		String optionId = bizProjectChangeBillService.findDescribeByRoleId(string);
		if(optionId==null){
			optionId = "2";
		}
		if(optionId.equals("1")){
			String phone = user.getMobile();
			bizOrderDisclose.setDesignerPhone(phone);
		}else if(optionId.equals("2")){
			Office office = user.getOffice();
			String parentIds = office.getParentIds();
			String id = office.getId();
			String[] split = parentIds.split(",");

			if(split.length == 4){
				bizOrderDisclose.setParentId(null);
				bizOrderDisclose.setOfficeId(id);
			}else{
				bizOrderDisclose.setOfficeId(null);
				
				parentIds = parentIds+"%";
				bizOrderDisclose.setParentId(parentIds);
			}
			
		}
		
		String userId = user.getId();
		bizOrderDisclose.setUserId(userId);
		
		return bizOrderDisclose;
	}
	
}
