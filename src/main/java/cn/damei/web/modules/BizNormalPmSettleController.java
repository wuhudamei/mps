/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.common.constantUtils.SettleStatusConstantUtil;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizNormalPmSettle;
import cn.damei.service.modules.BizNormalPmSettleService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 经理申请结算单Controller
 * @author 梅浩
 * @version 2017-04-17
 */
@Controller
@RequestMapping(value = "${adminPath}/bizsettlebill/bizNormalPmSettle")
public class BizNormalPmSettleController extends BaseController {

	@Autowired
	private BizNormalPmSettleService bizNormalPmSettleService;

	@ModelAttribute
	public BizNormalPmSettle get(@RequestParam(required=false) String id) {
		BizNormalPmSettle entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizNormalPmSettleService.get(id);
		}
		if (entity == null){
			entity = new BizNormalPmSettle();
		}
		return entity;
	}

	@RequiresPermissions("bizsettlebill:bizNormalPmSettle:view")
	@RequestMapping(value = {"prelist"})
	public String prelist(BizNormalPmSettle bizNormalPmSettle, HttpServletRequest request, HttpServletResponse response, Model model) {

		return "modules/bizsettlebill/bizNormalPmSettleList";
	}

	@RequiresPermissions("bizsettlebill:bizNormalPmSettle:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizNormalPmSettle bizNormalPmSettle, HttpServletRequest request, HttpServletResponse response, Model model) {
        //过滤门店
        User user = UserUtils.getUser();
        if(null==bizNormalPmSettle.getStoreId()){
            if(null!=user.getStoreId()){
                bizNormalPmSettle.setStoreId(Integer.valueOf(user.getStoreId()));
            }
        }
		Page<BizNormalPmSettle> page = bizNormalPmSettleService.findPage(new Page<BizNormalPmSettle>(request, response), bizNormalPmSettle);
		model.addAttribute("page", page);
		return "modules/bizsettlebill/bizNormalPmSettleList";
	}

	/**
	 * 结算员操作收到或者拒绝 或收款
	 * @param bizNormalPmSettle
	 * 2:收到  3:拒绝 4:打款
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizsettlebill:bizNormalPmSettle:view")
	@RequestMapping(value = "updateSettleBill.php")
	public @ResponseBody
	String updateSettleBill(BizNormalPmSettle bizNormalPmSettle, HttpServletRequest request, HttpServletResponse response, Model model) {
		//参数 settleId 状态


		String status=bizNormalPmSettleService.checkSettleIsExit(bizNormalPmSettle.getSettleId());

		if(Integer.parseInt(status)>2){

			return "2";
		}
		//操作人,check_employee_id  操作时间 status_time ,回复内容,对应更新的状态

		User user =UserUtils.getUser();
		if(null==user.getEmpId()){

			bizNormalPmSettle.setCheckEmployeeId(0);
		}else{
			bizNormalPmSettle.setCheckEmployeeId(Integer.parseInt(user.getEmpId()));

		}
		bizNormalPmSettle.setId(String.valueOf(bizNormalPmSettle.getSettleId()));
		bizNormalPmSettle.setStatusDatetime(new Date());

		bizNormalPmSettleService.save(bizNormalPmSettle);


		return "1";
	}

/*	*//**
	 * 申请结算图片
	 * @param bizNormalPmSettle
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *//*
	@RequiresPermissions("bizsettlebill:bizNormalPmSettle:view")
	@RequestMapping(value = {"settlePicList", ""})
	public String settlePicList(BizNormalPmSettle bizNormalPmSettle, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> picList=bizNormalPmSettleService.findSettlePicBySettleId(bizNormalPmSettle.getSettleId(), SettleStatusConstantUtil.SETTLE_PIC_TYPE_666);

		model.addAttribute("picList",picList);
		model.addAttribute("picPrefixName",request.getContextPath());
		return "modules/bizsettlebill/managerSettle";
	}*/

	/**
	 * 申请结算图片Ajax
	 * @param
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizsettlebill:bizNormalPmSettle:view")
	@RequestMapping(value = {"settlePicList", ""})
	@ResponseBody
	public Map<Object, Object> settlePicList(Integer settleId, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> picList=bizNormalPmSettleService.findSettlePicBySettleId(settleId, SettleStatusConstantUtil.SETTLE_PIC_TYPE_666);

		model.addAttribute("picList",picList);
		model.addAttribute("picPrefixName",request.getContextPath());
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", picList);
		return mapObject;
	}

	/**
	 * 根据门店查询结算节点
	 * @param bizNormalPmSettle
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizsettlebill:bizNormalPmSettle:view")
	@RequestMapping(value = "findSettleNodeNameByStoreId")
	public @ResponseBody
	List<String> findSettleNodeNameByStoreId(BizNormalPmSettle bizNormalPmSettle, HttpServletRequest request, HttpServletResponse response, Model model) {
		//参数 门店id

		List<String> settleNodeNameList=	bizNormalPmSettleService.findSettleNodeByStoreId(bizNormalPmSettle.getStoreId());
		return settleNodeNameList;
	}

}