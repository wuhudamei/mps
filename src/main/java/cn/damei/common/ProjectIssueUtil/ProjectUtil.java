package cn.damei.common.ProjectIssueUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.projectProblem.ProjectProblemConstantUtil;



@Service
public class ProjectUtil {

	@Autowired
	private ProjectUtilDao dao;

	private static final String STATUS_30 = "30";


	public List<Map<String, Object>> findProjectProblemByDealPersonId(Integer employeeId) {

		try {
			List<Map<String, Object>> problemMapList = dao.findProjectProblemByDealPersonId(employeeId);
			return problemMapList;
		} catch (Exception e) {

			e.printStackTrace();
			return new ArrayList<Map<String, Object>>();
		}

	}


	public List<Map<String, Object>> findProblemByMap(Map<String, Object> map) {

		List<Map<String, Object>> mapList = dao.findProblemByMap(map);
		return mapList;
	}


	@Transactional(readOnly = false)
	public void saveHandleLog(Map<String, Object> map) {

		dao.saveHandleLog(map);

	}


	@Transactional(readOnly = false)
	public void updateHandleStatusDataById(Map<String, String> handleIdStatusMap) {

		Integer handleId = Integer.valueOf(handleIdStatusMap.get("handleId"));

		dao.updateHandleStatusDataById(handleIdStatusMap);


		handleIdStatusMap.put("relatedId", String.valueOf(dao.findRelatedIdByhandleId(handleId)));
		dao.updateProblemItemStatusDataById(handleIdStatusMap);


		Map<String, Long> isOver = dao.checkIsComplaintAllOver(handleId);

		handleIdStatusMap.put("orderComplaintId", String.valueOf(isOver.get("orderComplaintId")));
		Long value = isOver.get("isOver");
		if (value == 0D) {
			Integer orderComplaintId = dao.updateOrderComplaintOver(handleId);
			handleIdStatusMap.put("orderComplaintId", String.valueOf(orderComplaintId));
			handleIdStatusMap.put("status", ProjectProblemConstantUtil.PROJECT_PROBLEM_ITEM_STATUS_30);
		} else {
			handleIdStatusMap.put("status", ProjectProblemConstantUtil.PROJECT_PROBLEM_ITEM_STATUS_20);

		}
		dao.updateOrderComplaintById(handleIdStatusMap);

	}


	@Transactional(readOnly = false)
	public void saveProjectIssuePic(List<Map<String, Object>> map) {

		dao.saveProjectIssuePic(map);

	}


	public List<String> findPicByIdAndType(Map<String, String> map) {

		List<String> list = dao.findPicListByRelatedIdAndType(map);
		return list;
	}

	public int selectCountNoDealByWorkOrderCode(String workOrderCode) {
		return dao.selectCountNoDealByWorkOrderCode(workOrderCode);
	}

}
