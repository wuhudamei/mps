
package cn.damei.service.modules;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.StringUtils;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.dao.modules.InspectorConfirmRejectDao;
import cn.damei.entity.modules.InspectorConfirmReject;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Service
@Transactional(readOnly = true)
public class InspectorConfirmRejectService extends CrudService2<InspectorConfirmRejectDao, InspectorConfirmReject> {

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	public InspectorConfirmReject get(Integer id) {
		return super.get(id);
	}

	public List<InspectorConfirmReject> findList(InspectorConfirmReject inspectorConfirmReject) {
		return super.findList(inspectorConfirmReject);
	}


	public Page<InspectorConfirmReject> findPage(Page<InspectorConfirmReject> page, InspectorConfirmReject inspectorConfirmReject) {
		return super.findPage(page, inspectorConfirmReject);
	}


	public void queryStoreIdAndprojectModeAndEnginDepart(InspectorConfirmReject inspectorConfirmReject, Model model) {
		User user = UserUtils.getUser();
		

		if (null == inspectorConfirmReject.getStoreId()) {
			if (StringUtils.isNotBlank(user.getStoreId())) {
				inspectorConfirmReject.setStoreId(user.getStoreId());
			}
		}


		if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
			model.addAttribute("gongcheng", true);
		}else{
			inspectorConfirmReject.setProjectMode(user.getProjectMode());
		}
		

		List<Integer> list = new ArrayList<Integer>();
		if (null == inspectorConfirmReject.getEnginDepartId()) {
			if (StringUtils.isNotBlank(user.getEmpId())) {
				list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(user.getEmpId()));
			} 
		} else {
			list.add(inspectorConfirmReject.getEnginDepartId());
		}
		if (list != null && list.size() > 0) {
			inspectorConfirmReject.setEnginDepartIds(list);
		}else{
			inspectorConfirmReject.setEnginDepartIds(null);
		}
	}
}