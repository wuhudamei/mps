package cn.damei.web.mobile.Manager;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.service.modules.BizPhoneMsgService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.OrderConstantUtil;
import cn.damei.common.constantUtils.PurchaseConstantUtil;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.SessionUtils;
import cn.damei.service.mobile.Manager.ApplySandService;
import cn.damei.entity.mobile.Manager.AuxiliaryPackageState;
import cn.damei.entity.mobile.Manager.AuxiliaryVo;
import cn.damei.entity.mobile.Manager.OrderVo;
import cn.damei.entity.mobile.Manager.PurchaseTwoCode;
import cn.damei.entity.mobile.Manager.PurchaseTwoVo;
import cn.damei.service.mobile.Manager.AuxiliaryApplyService;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.dao.mobile.home.HomeReportDao;
import cn.damei.entity.mobile.home.ViewLog;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.service.modules.BizMessagegroupService;
import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.OrderService2;


/**
 * @author 梅浩 meihao@zzhyun.cn:
 * @version 创建时间：2016年9月27日 下午3:55:52  
 * 订单的辅料申请   分为三大块:  1:第一次选择 申请         2:  继续购买      3:确定结账
 */

@Controller
@RequestMapping(value = "${adminPath}/app/manager/auxiliary")
public class AuxiliaryApplyController {

	@Autowired
	private AuxiliaryApplyService auxiliaryApplyService;
	@Autowired
	private OrderService2 orderService2;
	@Autowired
	private ApplySandService applySandService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;

	private Logger  logger =  LoggerFactory.getLogger(AuxiliaryApplyController.class);

	/**
	 * 辅料申请之 查询项目经理下的订单
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "order", "" })
	public String order(HttpServletRequest request, Model model,String  index) {
		
		if("0".equals(index)||"1".equals(index)){
			request.getSession().setAttribute("index", index);
		}else{
			logger.warn("辅料访问参数有误 ,无法识别路径参数: index :"+index);
		}
		
		// 1:取出登录经理信息
		Manager manager = SessionUtils.getManagerSession(request);
		// 2:得到订单集合
		List<OrderVo> list = auxiliaryApplyService.orderByManagerId(manager.getId());

		model.addAttribute("orderList", list);

		return "mobile/modules/Manager/auxiliary_apply";
	}

	/**
	 * 单纯的再买一次
	 * 
	 * @param auxiliaryVo
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "goOnChoose", "" })
	public String goOnChoose(AuxiliaryVo auxiliaryVo, HttpServletRequest request, Model model) {
//		// 有orderId和 storeId
//		setModelAttribute(auxiliaryVo,request,model);

		
		//查询订单详情
		Order2 order = orderService2.findOrderById(auxiliaryVo.getOrderId());
		List<AuxiliaryPackageState> packageStateList = null;
		if(null!=order && (order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1) || order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_4))){
			//3.1：如果是订单的工程模式为产业或者准产业【水电、木、瓦、油】【辅料申请校验】
			packageStateList = auxiliaryApplyService.findAuxiliaryPackageState(auxiliaryVo.getOrderId());
			
		}
		
		model.addAttribute("packageStateList", packageStateList);
		model.addAttribute("orderId", auxiliaryVo.getOrderId());
		model.addAttribute("order", order);
				
		return "mobile/modules/Manager/auxiliary_choose";
	}
	
	
	/**
	 * 申请辅料数据校验
	 * @param orderId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "applyAuxiliary_data_check_ajax")
	public @ResponseBody String applyAuxiliaryDataCheckAjax(String orderId,HttpServletRequest request){
		
		String result = "0";
		//1.判断订单ID是否为空
		if(StringUtils.isBlank(orderId)){
			//订单ID为空
			result = "1";
			return result;
		}
		//2.登录的项目经理
		Manager manager = SessionUtils.getManagerSession(request);
		if(null==manager){
			//项目经理是否登录
			result = "2";
			return result;
		}
		
		//3.判断【基装】约检节点的状态是否大于等于10-质检员已提交约检验收。
		//如果是，则不允许再申请开关面板
		Integer count = applySandService.findQcBillAcceptStatus(Integer.valueOf(orderId));
		if(null==count || count>0){
			result = "3";
			return result;
		}
		
		//查询该订单最新一次申请辅料的时间是否间隔有5分钟
		List<PurchaseTwoVo> purchaseByOrderId = auxiliaryApplyService.selectPurchaseByOrderId(Integer.valueOf(orderId));
		if(purchaseByOrderId.size()>0){
				
			// 根据采购单id 查询是否已读
			ViewLog log = new ViewLog();
			log.setBusinessIdInt(purchaseByOrderId.get(0).getPurchaseId());
			log.setBusinessType("302");// 申请辅料
			log.setBusinessViewerOnlyMark(manager.getPhone());
			Integer integer = logDao.findView(log);
	
			if (null == integer || integer == 0) {
				//4. 返回申请开关面板页面
				// 并提示:请查阅采购单情况
				result = "4";
				return result;

			}		
			if (purchaseByOrderId.get(0).getApplyTime().getTime() + 300 * 1000 > new Date().getTime()) {

				// 5.五分钟之内不可以
				result = "5";
				return result;
			}		
			
		}
				
		return result;
		
	}
	

	@Autowired
	private HomeReportDao logDao;
	/**
	 * 辅料申请之,辅料选择
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "auxiliarychoose", "" })
	public String auxiliarychoose(AuxiliaryVo auxiliaryVo, HttpServletRequest request, Model model) {

		
		Integer allCount = 0;
		Double allMoney = (double) 0;

		// 1:订单页传入参数orderId 到形参 辅料vo中

		// 2:根据该订单id查询辅料申请表,以submitStatus 提交状态判别,如果为N,为未提交,返回该辅料记录直接到结算页


		List<AuxiliaryVo> list = auxiliaryApplyService.checkIsSubmit(auxiliaryVo.getOrderId());
		// 如果真有记录!!! ,就根据辅料编号,查询辅料信息,到结算页 (返回值为:辅料编号和辅料数量)
		if (null != list && list.size() > 0) {
			List<AuxiliaryVo> list2 = new ArrayList<AuxiliaryVo>();
			// 遍历
			for (AuxiliaryVo auxiliaryVo2 : list) {
					auxiliaryVo2.setOrderId(auxiliaryVo.getOrderId());
				// parameter: code +orderId
				List<AuxiliaryVo> list3 = auxiliaryApplyService.auxiliaryChoose(auxiliaryVo2);

				if(list3.size()>0){

					// 数量设置到里面
					list3.get(0).setCount(auxiliaryVo2.getCount());
					list3.get(0).setTotalPrice(list3.get(0).getPrice() * list3.get(0).getCount());
					// 所有购买过的辅料都添加到集合中
					list2.add(list3.get(0));
					String string = list3.get(0).getTotalPrice().toString();

					allMoney += Double.parseDouble((string));
					allCount += list3.get(0).getCount();
				}

			}
			String remarks = SessionUtils.getManagerSession(request).getRealname() + "-"
					+ SessionUtils.getManagerSession(request).getPhone();

			//查询订单详情
			Order2 order = orderService2.findOrderById(auxiliaryVo.getOrderId());
			List<AuxiliaryPackageState> packageStateList = null;
			if(null!=order && (order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1) || order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_4))){
				//3.1：如果是订单的工程模式为产业或者准产业【水电、木、瓦、油】【辅料申请校验】
				packageStateList = auxiliaryApplyService.findAuxiliaryPackageState(auxiliaryVo.getOrderId());
				
			}
			model.addAttribute("packageStateList", packageStateList);
			model.addAttribute("order", order);
			model.addAttribute("remarks", remarks);
			model.addAttribute("auxiliaryList", list2);
			model.addAttribute("allMoney", allMoney);
			model.addAttribute("allCount", allCount);
			model.addAttribute("orderId", auxiliaryVo.getOrderId());

			return "mobile/modules/Manager/auxiliary_submit";

		}
		
		
		// 3:如果没有辅料申请记录, 查询所有辅料
		//查询订单详情
		Order2 order = orderService2.findOrderById(auxiliaryVo.getOrderId());
		List<AuxiliaryPackageState> packageStateList = null;
		if(null!=order && (order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1) || order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_4))){
			//3.1：如果是订单的工程模式为产业或者准产业【水电、木、瓦、油】【辅料申请校验】
			packageStateList = auxiliaryApplyService.findAuxiliaryPackageState(auxiliaryVo.getOrderId());
			
		}
		
		model.addAttribute("packageStateList", packageStateList);
		model.addAttribute("orderId", auxiliaryVo.getOrderId());
		model.addAttribute("order", order);
		
		return "mobile/modules/Manager/auxiliary_choose";
	}

	/**
	 * 根据分类动态加载商品
	 * @param workerType
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "categoryItems")
	public @ResponseBody List<AuxiliaryVo> categoryItems(String  workerType,String orderId) {
		List<AuxiliaryVo> items = auxiliaryApplyService.categoryItems(workerType,orderId);
		return items;
	}
	
	/**
	 * 根据订单id查询水电、木、瓦、油任务包的状态
	 * 
	 * @return
	 */
	@RequestMapping(value = "check_auxiliary_package_state_ajax")
	public @ResponseBody List<AuxiliaryPackageState> checkAuxiliaryPackageStateAjax(String orderId) {
		
		Order2 order = orderService2.findOrderById(Integer.valueOf(orderId));
		List<AuxiliaryPackageState> list = null;
		
		if(null!=order && (order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1) || order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_4))){
			//3.1：辅料申请校验
			//如果是订单的工程模式为产业或者准产业【水电、木、瓦、油】
			//【1】：任务包限制
		    //【1.1】：如果没有工种对应的任务包模板，不做限制
		    //		【isCanApplyAuxiliary:1】【不限】
		    //【1.2】：如果没有生产该模板的任务包，必须生产任务包
		    //		【isCanApplyAuxiliary:2】【不可】：“水电路改造工程”任务包的未生成，请您联系工程部的拆单员进行拆单。
		    //【1.3】：如果存在任务包，但是任务包已经验收，则不允许申请其对应的辅料
		    //		【isCanApplyAuxiliary:3】【不可】：水电路改造工程”的任务包点了【确认验收】之 后，就不能再申请【水电】类下面所有的辅料商品
		    //【2】：金额限制
		    //【2.1】：存在任务包，但是任务包预算金额为空，必须要有金额
		    //		【isCanApplyAuxiliary:4】【不可】：“水电路改造工程”任务包的预算金额为0，请您联系工程部的拆单员进行拆单或者修改预算金额。
		    //【2.2】：任务包预算金额不为空，但是申请辅料预算比例为空，不做限制
		    //		【isCanApplyAuxiliary:5】【不限】
		    //【2.3】：任务包预算金额*比例-已申请金额<0，不可以申请
			//		【isCanApplyAuxiliary:6】【不可】【选择页面】：您【水电】类辅料商品申请金额还剩余0元，不可申请【水电】辅料商品,如果商品数量确实不够请联系拆单员修改任务包预算金额。
			//		【isCanApplyAuxiliary:6】【不可】【提交页面】：您【油】类辅料商品申请金额还剩余0元，请删除高亮显示的【油】辅料商品。
		    //【2.4】：任务包预算金额*比例-已申请金额>0，可以申请,注意校验
		    //		【isCanApplyAuxiliary:7】【限制】：您【水电】类辅料商品申请金额还剩余10.00元，请修改【水电】辅料商品数量，如果商品数量确实不够请联系拆单员修改任务包预算金额。
			list = auxiliaryApplyService.findAuxiliaryPackageState(Integer.valueOf(orderId));
			
		}
		
		return list;
	}

	/**
	 * 选好了!
	 * 
	 * @param id
	 * @param count
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "auxiliarybuy", method = RequestMethod.POST)
	public String auxiliarybuy(String[] id, String[] count, String[] auxiMateCode, String orderId, Model model,
			HttpServletRequest request) {

		Integer allCount = 0;
		Double allMoney = (double) 0;

		// -2: 当前登录项目经理姓名加上手机号
		String remarks = SessionUtils.getManagerSession(request).getRealname() + "-"
				+ SessionUtils.getManagerSession(request).getPhone();
		// 0: 暂存的集合
		ArrayList<AuxiliaryVo> list = new ArrayList<AuxiliaryVo>();
		HashSet<AuxiliaryVo> set = new HashSet<AuxiliaryVo>();

		// 2-1根据orderId去 辅料申请记录表 biz_purchase_auxi_mate 查询记录
		List<AuxiliaryVo> AuxiliarybyOrderId = auxiliaryApplyService.getApplyRecordByOrderId(Integer.valueOf(orderId));

		if (null != AuxiliarybyOrderId && AuxiliarybyOrderId.size() > 0) {
			
				// 遍历

				// 查询有price等 辅料表的属性
			List<AuxiliaryVo> auxiliaryDetailList = auxiliaryApplyService.selectAuxiliaryByCodeList(AuxiliarybyOrderId);
			
						if(auxiliaryDetailList.size()>0) {
							for (AuxiliaryVo auxiliaryVo3 : auxiliaryDetailList) {
								for (AuxiliaryVo auxiliaryVo2 : AuxiliarybyOrderId) {
									if (auxiliaryVo3.getAuxiMateCode().equals(auxiliaryVo2.getAuxiMateCode())) {

										// 总价 Vo2 已申请的记录,有数量 没有价格 Vo3辅料表, 有价格没有记录
										auxiliaryVo3.setTotalPrice(auxiliaryVo2.getCount() * auxiliaryVo3.getPrice());
										auxiliaryVo3.setCount(auxiliaryVo2.getCount());

										// 把商品记录加入集合,
										set.add(auxiliaryVo3);
										break;


									}


								}
							}
						}
			
		

		}
		
		if(null != auxiMateCode && auxiMateCode.length>0){
			for (int v = 0; v < auxiMateCode.length; v++) {
				if (StringUtils.isNotBlank(count[v]) && count[v] != "," && !count[v].equals("0")) {
					
					String[] split = auxiMateCode[v].split("/");
					
					for (String itemId : split) {
						if (!itemId.equals("/")) {
							
							// 1: 根据辅料编号查询该商品
							AuxiliaryVo auxiliaryVo = auxiliaryApplyService.selectAuxiliaryById((itemId));
							auxiliaryVo.setOrderId(Integer.parseInt(orderId));
							// 根据新添的code ,和所属订单id 查询辅料记录表,
							AuxiliaryVo auxiliaryVo2 = auxiliaryApplyService
									.getApplyRecordById(auxiliaryVo);
							
							if (null != auxiliaryVo2) {
								// 如果有 则 数量更新
								auxiliaryVo2.setCount(Integer.valueOf(count[v]));
								// 总价
								auxiliaryVo2.setTotalPrice(auxiliaryVo2.getCount() * auxiliaryVo.getPrice());
								auxiliaryVo2.setName(auxiliaryVo.getName());
								auxiliaryVo2.setPic(auxiliaryVo.getPic());
								auxiliaryVo2.setPrice(auxiliaryVo.getPrice());
								auxiliaryVo2.setSupplierPrice(auxiliaryVo.getSupplierPrice());
								auxiliaryVo2.setWangzhenPrice(auxiliaryVo.getWangzhenPrice());
								auxiliaryVo2.setUnit(auxiliaryVo.getUnit());
								auxiliaryVo2.setSpecifications(auxiliaryVo.getSpecifications());
								auxiliaryVo2.setWorkType(auxiliaryVo.getWorkType());
								auxiliaryVo2.setWorkTypeName(auxiliaryVo.getWorkTypeName());
								// 把商品加入集合,
								
								Iterator<AuxiliaryVo> iterator = set.iterator();
								
								while (iterator.hasNext()) {
									AuxiliaryVo vo = iterator.next();
									if (vo.getAuxiMateCode().equals(auxiliaryVo2.getAuxiMateCode())) {
										iterator.remove();
										
									}
								}
								
								set.add(auxiliaryVo2);
								auxiliaryVo2.setSubmmitStatus("NO");
								
								// 4: 保存到数据库, 但字段submit_status 为 未提交
								auxiliaryApplyService.updateAuxliary(auxiliaryVo2);
								
							} else {
								// 如果为空 ,表示新添加的,直接设置属性,保存到记录表,添加到set
								auxiliaryVo.setCount(Integer.valueOf(count[v]));
								auxiliaryVo.setTotalPrice(auxiliaryVo.getPrice() * auxiliaryVo.getCount());
								// 状态依然是未提交
								auxiliaryVo.setSubmmitStatus("NO");
								auxiliaryVo.setOrderId(Integer.valueOf(orderId));
								
								// 把商品加入集合,
								set.add(auxiliaryVo);
								
								auxiliaryVo.setId(null);
								// 4: 保存到数据库, 但字段submit_status 为 未提交
								auxiliaryApplyService.saveAuxliary(auxiliaryVo);
								
							}
							
						}
						
					}
				}
				
			}
		}

		for (AuxiliaryVo auxiliaryVo2 : set) {
			String string = auxiliaryVo2.getTotalPrice().toString();
			allMoney += Double.parseDouble((string));
			allCount += auxiliaryVo2.getCount();

			list.add(auxiliaryVo2);
		}
		
		
		
		//查询订单详情
		Order2 order = orderService2.findOrderById(Integer.valueOf(orderId));
		List<AuxiliaryPackageState> packageStateList = null;
		if(null!=order && (order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1) || order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_4))){
			//3.1：如果是订单的工程模式为产业或者准产业【水电、木、瓦、油】【辅料申请校验】
			packageStateList = auxiliaryApplyService.findAuxiliaryPackageState(Integer.valueOf(orderId));
			
		}
		model.addAttribute("packageStateList", packageStateList);
		model.addAttribute("order", order);
		
		
		// 5: 根据session中项目经理信息,放入名称和手机号 String
		model.addAttribute("remarks", remarks);
		// 6: 把-->0 添加到model中,返回到jsp
		model.addAttribute("auxiliaryList", list);
		model.addAttribute("orderId", orderId);
		model.addAttribute("allMoney", allMoney);
		model.addAttribute("allCount", allCount);

		return "mobile/modules/Manager/auxiliary_submit";
	}

	
	
	
	@Autowired
	private BizMessagegroupService bizMessagegroupService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	/**
	 * 确认用量
	 * @param hopeForTime
	 * @param orderId
	 * @param remarks
	 * @param AuxiliaryAllMoney
	 * @param model
	 * @param auxiMateCode
	 * @param auxiliaryCount
	 * @param request
	 * @return
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "auxiliarypay")
	public @ResponseBody String auxiliarypay(String hopeForTime, String orderId, String remarks, String AuxiliaryAllMoney,
			Model model, String[] auxiMateCode, String[] workType,String[] auxiliaryCount, HttpServletRequest request)
			throws ParseException, UnsupportedEncodingException {

		//查询订单详情
		Order2 order = orderService2.findOrderById(Integer.valueOf(orderId));
		if(null!=order && (order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1) || order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_4))){
			//3.1：如果是订单的工程模式为产业或者准产业【水电、木、瓦、油】【辅料申请校验】
			List<AuxiliaryPackageState> packageStateList = auxiliaryApplyService.findAuxiliaryPackageState(Integer.valueOf(orderId));
			if(CollectionUtils.isNotEmpty(packageStateList) && null!=workType && workType.length>0){
				for(AuxiliaryPackageState a :packageStateList){
					if(PurchaseConstantUtil.PURCHASE_IS_CAN_APPLY_AUXILIARY_2.equals(a.getIsCanApplyAuxiliary()) ||
							PurchaseConstantUtil.PURCHASE_IS_CAN_APPLY_AUXILIARY_3.equals(a.getIsCanApplyAuxiliary()) ||
							PurchaseConstantUtil.PURCHASE_IS_CAN_APPLY_AUXILIARY_4.equals(a.getIsCanApplyAuxiliary()) ||
							PurchaseConstantUtil.PURCHASE_IS_CAN_APPLY_AUXILIARY_6.equals(a.getIsCanApplyAuxiliary()) ){
						for(int x = 0; x < workType.length; x++){
							if(StringUtils.isNotBlank(workType[x]) && workType[x].equals(a.getEmpWorkType())){
								return "gaoliang";
							}
						}
					}
				}
			}
		}
				
		// 保存采购单表
		PurchaseTwoVo purcharse = new PurchaseTwoVo();
		// 订单id
		purcharse.setOrderId(Integer.valueOf(orderId));
		// 状态 10为项目经理已提交
		purcharse.setStatus("10");
		// 申请人为当前登录的项目经理
		purcharse.setApplyPerson(SessionUtils.getManagerSession(request).getId());
		// 申请时间为当前时间
		purcharse.setApplyTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
		java.util.Date date = sdf.parse(hopeForTime);
		// 备注为期望送货人 默认登陆者+手机(项目经理)
		purcharse.setRemarks(remarks);
		// 期望送货时间
		purcharse.setHopeForTime(date);
		purcharse.setCreateDate(new Date());
		//采购单编号
		purcharse.setPurchaseCode(purchaseCode());
		purcharse.setStatus("10");
		purcharse.setDelFlag("0");
		purcharse.setCreateDate(new Date());
		purcharse.setAuxiliaryAllMoney(Double.parseDouble(AuxiliaryAllMoney));
		purcharse.setPurchaseType(ConstantUtils.AUXILIARY_NUMBER);
		auxiliaryApplyService.savePurchase(purcharse);
		
		
		
		// 根据辅料code 直接更新辅料表
		// 遍历辅料code
		for (int x = 0; x < auxiliaryCount.length; x++) {
			if (StringUtils.isNotBlank(auxiliaryCount[x]) && auxiliaryCount[x] != "," && !auxiliaryCount[x].equals("/")) {
				StringTokenizer tokenizer = new StringTokenizer(auxiMateCode[x], "/", false);
				while (tokenizer.hasMoreTokens()) {
					String chuanruCode = tokenizer.nextToken();
					
					List<AuxiliaryVo> list = auxiliaryApplyService.getApplyRecordByOrderId(Integer.valueOf(orderId));

					for (AuxiliaryVo auxiliaryVo : list) {
						//在结算页面上增加了数量可修改, 所以数量以页面为主
						if(auxiliaryVo.getAuxiMateCode().equals(chuanruCode)){
							
							if(auxiliaryCount[x].equals("0")){
								//如果页面恶意选择0 ,那么默认删除该商品
								auxiliaryApplyService.deleteAuxiliaryByCode(auxiliaryVo);
								continue;
							}
							
							//更新为yes
							auxiliaryVo.setSubmmitStatus("YES");
							//更新数量
							auxiliaryVo.setCount(Integer.parseInt(auxiliaryCount[x]));
							auxiliaryVo.setOwedAuxiMateCount(auxiliaryVo.getCount());
							auxiliaryVo.setReceivedAuxiMateCount(0);
							auxiliaryVo.setCreateDate(new Date());
							auxiliaryVo.setDelFlag("0");
							auxiliaryVo.setPurchaseId(purcharse.getId());
							// 更新该辅料表
							auxiliaryApplyService.updateAuxliary(auxiliaryVo);
						}
					}
				}
			}
		}
		String managerName = SessionUtils.getManagerSession(request).getRealname();
		String managerPhone = SessionUtils.getManagerSession(request).getPhone();
		String storeId = SessionUtils.getManagerSession(request).getStoreid();
		
		//根据门店和短信组类型查找  messageGroupType : '5'; 辅料调度
		BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(storeId,"5");
		List<Integer> list = new ArrayList<Integer>();
		List<BizEmployee2> employeelist = null;
		if(null != bizMessagegroup ){
			String[] str = bizMessagegroup.getEmployees().split(",");
			for(String id: str){
				list.add(Integer.valueOf(id));
			}
			employeelist = bizEmployeeService2.getById(list);
			//订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），项目经理（姓名-手机号），项目经理已申请辅料，请尽快登录系统查看详情。
			String content ="订单（"+order.getOrderNumber()+","+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()+"-"+order.getCustomerPhone()+",项目经理（"+managerName+"-"+managerPhone+"），项目经理已申请辅料，请尽快登录系统查看详情。";
			if(null != employeelist && employeelist.size()>0){
				for (BizEmployee2 bizEmployee2 : employeelist) {
					bizPhoneMsgService.sendMessage(bizEmployee2.getId(), bizEmployee2.getPhone(),
							content, SendMsgBusinessType.RELATED_BUSINESS_TYPE_700202, Integer.valueOf(orderId));
				}
	    	}
		}
		return String.valueOf(purcharse.getId());
}
	

	

	
	
	
	
	
	/**
	 * 采购单编号生成方法

	 */
	public String  purchaseCode(){
		//以PO开头
		String purchaseCode = ConstantUtils.PURCHASE_NUMBER;
	
		
		StringBuilder builder = new StringBuilder();
		
		//num和date
		PurchaseTwoCode purchaseObj = auxiliaryApplyService.getCode();
		
		if(null==purchaseObj || null==purchaseObj.getAuxiliaryDate()){
			if(null==purchaseObj){
				purchaseObj = new PurchaseTwoCode();
			}
			purchaseObj.setPurchaseCode("0");
			purchaseObj.setAuxiliaryDate(new Date());
			purchaseObj.setId(2);
			auxiliaryApplyService.updateCode(purchaseObj);
		}
		
		
		if(new SimpleDateFormat("yyyyMMdd").format(purchaseObj.getAuxiliaryDate()).equals(new SimpleDateFormat("yyyyMMdd").format(new Date()))){
		
		
		//流水号+1
		purchaseObj.setPurchaseCode(String.valueOf(Integer.parseInt(purchaseObj.getPurchaseCode())+1));
		}else{
			//一天已过  更新采购单编号到 0开始
			purchaseObj.setPurchaseCode("1");
			
		}
		purchaseObj.setAuxiliaryDate(new Date());
		//更新数据库
		auxiliaryApplyService.updateCode(purchaseObj);
		
		//格式后的时间戳
		String format = new SimpleDateFormat("yyyyMMdd").format( purchaseObj.getAuxiliaryDate());
		//得到的流水号
		String code = purchaseObj.getPurchaseCode();
	
		builder.append(purchaseCode).append(format);
		//判断长度
		if(code.length()==1){
			
			builder.append("000").append(code);
			
		}else if(code.length()==2){
			//拼接采购单编号
			builder.append("00").append(code);
		}else if(code.length()==3){
			builder.append("0").append(code);
		}else if(code.length()==4){
			builder.append(code);
		}
		
		//返回采购单编号
		return builder.toString();
		
		
		
	}
	
	
	
	
	
	@RequestMapping(value = "deleteAuxiliary")
	public @ResponseBody String deleteAuxiliary(String auxiliaryCode,String orderId){
		AuxiliaryVo   vo =new AuxiliaryVo();
		vo.setAuxiMateCode(auxiliaryCode);
		vo.setOrderId(Integer.parseInt(orderId));
		
		//根据辅料编号orderId, 和 状态,去申请记录删除该记录
	auxiliaryApplyService.deleteAuxiliaryByCode(vo);
		
		
		return "1";
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void  setModelAttribute(AuxiliaryVo auxiliaryVo,HttpServletRequest request, Model model){
		//订单ID (查询门店关联)  先查水电
				List<AuxiliaryVo> list = auxiliaryApplyService.auxiliaryChoose(auxiliaryVo);
		//先查水电
				model.addAttribute("waterLightCategory", list);
				model.addAttribute("orderId", auxiliaryVo.getOrderId());
		
	}
	
	
	
	
}