package cn.damei.web.mobile.Inspector;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Inspector.ApplyCheckOrderEntity;
import cn.damei.service.mobile.Inspector.ApplyCheckDetailService;
import cn.damei.entity.mobile.Inspector.BizEvalManagerDetail;
import cn.damei.service.mobile.Inspector.InspectorEvaluateWorkerService;
import cn.damei.service.mobile.Manager.BizEvalTaskpackRoleIndexScoreService;
import cn.damei.entity.mobile.Manager.SettlementEvalStore;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joseph on 2017/4/22. 我的约检记录
 */
@Controller
@RequestMapping(value = "${adminPath}/app/pqc/apply-check-detail")
public class ApplyCheckDetailController {

	@Autowired
	private ApplyCheckDetailService service;
	@Autowired
	private InspectorEvaluateWorkerService inspectorEvaluateWorkerService;
	@Autowired
	private BizEvalTaskpackRoleIndexScoreService bizEvalTaskpackRoleIndexScoreService;

	@RequestMapping(value = "pre-list.php")
	public String preList(HttpServletRequest request, Model model) {
		model.addAttribute("pqcId", SessionUtils.getInspectorSession(request).getId());
		return "mobile/modules/pqc/qualityCheck/pqccheckdetail/dateCheckRecord";

	}

	@RequestMapping(value = "list.php")
	public @ResponseBody List<ApplyCheckOrderEntity> list(ApplyCheckOrderEntity orderEntity, HttpServletRequest request,
			Model model) {

		if (null == orderEntity) {

			orderEntity = new ApplyCheckOrderEntity();

		}
		List<ApplyCheckOrderEntity> list = service.findCheckDoneOrderListByManagerId(orderEntity);

		return list;

	}

	@RequestMapping(value = "detail")
	public String detail(Integer orderId, HttpServletRequest request, Model model) {

		model.addAttribute("orderId", orderId);

		return "mobile/modules/pqc/qualityCheck/pqccheckdetail/dateCheckRecordDetails";
	}

	@RequestMapping(value = "detail.php")
	public @ResponseBody ApplyCheckOrderEntity qcDetail(Integer orderId, HttpServletRequest request) {

		ApplyCheckOrderEntity entity = service.applyCheckDetailByOrderId(orderId);
		return entity;
	}

	@RequestMapping(value = "photo.php")
	public String photo(Integer qcBillId, HttpServletRequest request, Model model) {
		List<String> picList = service.picList(qcBillId);

		model.addAttribute("picList", picList);
		model.addAttribute("baseUrl", request.getContextPath());
		return "mobile/modules/pqc/qualityCheck/pqccheckdetail/photo";
	}

	@RequestMapping(value = "evalDetail")
	public String evalDetail(Integer evalScoreId, HttpServletRequest request, Model model) {
		BizEvalManagerDetail bizEvalManagerDetail = inspectorEvaluateWorkerService.queryEvalManagerDetail(evalScoreId);
		
		//评价项目经理
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("relatedBusinessId", bizEvalManagerDetail.getRelatedBusinessId());
		map.put("evalType", "2");
		map.put("evalRoleType", ConstantUtils.EVAL_ROLE_TYPE_201);
		List<SettlementEvalStore> evalStoreList = bizEvalTaskpackRoleIndexScoreService.querySettlementEval(map);
		if (CollectionUtils.isNotEmpty(evalStoreList)) {
			for (SettlementEvalStore store : evalStoreList) {
				Double selectCount = store.getGotScore() / (store.getEvalTotalScore() / 5);
				store.setSelectCount(selectCount.intValue());
			}
		}
		model.addAttribute("bizEvalManagerDetail", bizEvalManagerDetail);
		model.addAttribute("evalStoreList",evalStoreList);
		return "mobile/modules/pqc/evaluate/evalManager/commentDetails";
	}

}
