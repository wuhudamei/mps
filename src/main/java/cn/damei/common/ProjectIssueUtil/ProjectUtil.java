package cn.damei.common.ProjectIssueUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.projectProblem.ProjectProblemConstantUtil;

/**
 * Created by joseph on 2017/7/3.
 */

@Service
public class ProjectUtil {

	@Autowired
	private ProjectUtilDao dao;

	private static final String STATUS_30 = "30";

	/**
	 * 根据登录人查询 列表list
	 *
	 * @param employeeId
	 * @return
	 */
	public List<Map<String, Object>> findProjectProblemByDealPersonId(Integer employeeId) {

		try {
			List<Map<String, Object>> problemMapList = dao.findProjectProblemByDealPersonId(employeeId);
			return problemMapList;
		} catch (Exception e) {

			e.printStackTrace();
			return new ArrayList<Map<String, Object>>();
		}

	}

	/**
	 * 根据订单id和登录人及对应type查询问题 key: orderId , dealPersonId dealType
	 *
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findProblemByMap(Map<String, Object> map) {

		List<Map<String, Object>> mapList = dao.findProblemByMap(map);
		return mapList;
	}

	/**
	 * 根据参数保存操作log日志
	 *
	 * @param map
	 *            参数key:dealId dealPersonType dealPersonId dealStatus
	 *            dealDateTime dealDescribe
	 */
	@Transactional(readOnly = false)
	public void saveHandleLog(Map<String, Object> map) {

		dao.saveHandleLog(map);

	}

	/**
	 * 通用更新 参数: handleId ,status 返回 problem表的id 然后更新 problem的状态
	 *
	 * @return 返回关联id
	 */
	@Transactional(readOnly = false)
	public void updateHandleStatusDataById(Map<String, String> handleIdStatusMap) {

		Integer handleId = Integer.valueOf(handleIdStatusMap.get("handleId"));
		// 更新handle 表状态
		dao.updateHandleStatusDataById(handleIdStatusMap);

		// 更新问题 problem 表的状态
		handleIdStatusMap.put("relatedId", String.valueOf(dao.findRelatedIdByhandleId(handleId)));
		dao.updateProblemItemStatusDataById(handleIdStatusMap);

		// 更新 order_complaint 表的状态
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

	/**
	 * 通用插入pic
	 *
	 * @param map
	 *            参数: #{businessType}, #{businessIdInt}, #{picUrl},
	 *            #{picDateTime}
	 */
	@Transactional(readOnly = false)
	public void saveProjectIssuePic(List<Map<String, Object>> map) {

		dao.saveProjectIssuePic(map);

	}

	/**
	 * 参数:businessType businessIdInt
	 *
	 * @return
	 */
	public List<String> findPicByIdAndType(Map<String, String> map) {

		List<String> list = dao.findPicListByRelatedIdAndType(map);
		return list;
	}

	public int selectCountNoDealByWorkOrderCode(String workOrderCode) {
		return dao.selectCountNoDealByWorkOrderCode(workOrderCode);
	}

}
