package cn.damei.service.mobile.Manager;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.PicturePathContantUtil;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.constantUtils.projectProblem.ProjectProblemConstantUtil;
import cn.damei.common.service.BaseService;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.Base64Util;
import cn.damei.common.SessionUtils;
import cn.damei.common.ProjectIssueUtil.ProjectUtil;
import cn.damei.entity.mobile.Manager.ManagerProjectIssueBean;
import cn.damei.dao.mobile.Manager.ManagerProjectIssueDao;
import cn.damei.common.utils.UserUtils;



@Service
@Transactional(readOnly = true)
public class ManagerProjectIssueService extends BaseService {
	@Autowired
	private ManagerProjectIssueDao dao;

	@Autowired
	private ProjectUtil util;

	public List<Map<String, Object>> findProjectProblemByDealPersonId(Integer managerId) {

		return util.findProjectProblemByDealPersonId(managerId);
	}

	public List<Map<String, Object>> findProblemByOrderId(Map<String, Object> map) {

		return util.findProblemByMap(map);
	}

	public List<Map<String, Object>> findWorkerInfoByRelatedId(Integer relatedId) {

		return dao.findWorkerInfoByRelatedId(relatedId);
	}

	public Map<String, Object> findProblemByHandleId(Integer handleId) {

		return dao.findProblemByHandleId(handleId);
	}

	@Transactional(readOnly = false)
	public String saveManagerDeal(HttpServletRequest request, String describe, Integer handleId, Integer complaintProblemItemId, Integer orderId, Double delayDays, Integer orderComplaintProblemId, String... photos) {



		Integer isExist = dao.findIssueIsDoneByHandleId(handleId);

		if (null != isExist && isExist == 0) {
			Date date = new Date();
			Map<String, String> map = new HashMap<>();



			map.put("handleId", String.valueOf(handleId));
			map.put("dealDescribe", describe);
			dao.saveHandleDescribeByHandleIdAndDealDescribe(map);

			map.put("handleStatus", ProjectProblemConstantUtil.PROJECT_PROBLEM_STATUS_20);
			map.put("itemStatus", ProjectProblemConstantUtil.PROJECT_PROBLEM_ITEM_STATUS_30);
			util.updateHandleStatusDataById(map);

			try {
				Map<String, Object> mapForfeit = new HashMap<>();

				mapForfeit.put("orderId", orderId.toString());
				mapForfeit.put("complaintProblemItemId", complaintProblemItemId.toString());
				mapForfeit.put("orderComplaintProblemId", orderComplaintProblemId.toString());

				Map<String, Object> mapDate = dao.findProblemByHandleId(handleId);
				String responseTime = mapDate.get("responseTime").toString();

				Date problemCreateDate = (Date) mapDate.get("createDate");

				Date problemDelayDate = DateUtils.addDate(problemCreateDate, (new Double(responseTime)).intValue());

				mapForfeit.put("promiseComDate", problemDelayDate);
				mapForfeit.put("userCreate", UserUtils.getUser().getId());
				mapForfeit.put("userUpdate", UserUtils.getUser().getId());
				mapForfeit.put("punishMoney", (delayDays) * 100);

				dao.saveCustomerComForfeit(mapForfeit);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (null != photos && photos.length > 0) {


				List<Map<String, Object>> picMapList = new ArrayList<>();

				for (int x = 0; x < photos.length; x++) {

					Map<String, Object> map1 = new HashMap();
					map1.put("businessType", PictureTypeContantUtil.PICTURE_TYPE_109);
					map1.put("businessIdInt", handleId);
					map1.put("picUrl", savePic(request, photos[x]));
					map1.put("picDateTime", date);
					picMapList.add(map1);

				}
				util.saveProjectIssuePic(picMapList);

			}



			Map<String, Object> logMap = new HashMap<>();

			logMap.put("dealId", handleId);
			logMap.put("dealPersonType", ProjectProblemConstantUtil.PROJECT_DEAL_PERSON_TYPE_MANAGER_1);
			logMap.put("dealPersonId", SessionUtils.getManagerSession(request).getId());
			logMap.put("dealStatus", ProjectProblemConstantUtil.PROJECT_PROBLEM_STATUS_20);
			logMap.put("dealDateTime", date);
			logMap.put("dealDescribe", describe);

			util.saveHandleLog(logMap);
			return "1";
		} else {


			return "2";
		}

	}

	public String savePic(HttpServletRequest request, String photo) {

		String rootPath = request.getSession().getServletContext().getRealPath("");
		File filePath = new File(rootPath + PicturePathContantUtil.UPLOAD_MANAGER_PROJECT_DEAL + DateUtils.getDate1());

		String uuid = UUID.randomUUID().toString().replaceAll("-", "");




		if (!filePath.exists() && !filePath.isDirectory()) {
			filePath.mkdirs();
		}
		String filepath = filePath + filePath.separator + uuid + ".jpeg";
		Base64Util.generateImage(photo, filepath);

		String picpath = PicturePathContantUtil.UPLOAD_MANAGER_PROJECT_DEAL + DateUtils.getDate1() + filePath.separator + uuid + ".jpeg";

		return picpath;

	}


	public List<String> findPic(Map<String, String> map) {

		List<String> list = util.findPicByIdAndType(map);

		return list;
	}


	public int selectCountNoDealByWorkOrderCode(String workOrderCode) {
		return util.selectCountNoDealByWorkOrderCode(workOrderCode);
	}

	@Transactional(readOnly = false)
	public void updateStatus(ManagerProjectIssueBean bizCusServiceProblem) {
		dao.updateStatus(bizCusServiceProblem);

	}

}