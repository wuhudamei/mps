

package cn.damei.web.mobile.Manager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.ApplyMaterialsStandardReceiveDetail;
import cn.damei.entity.mobile.Manager.ApplyStandardMaterial;
import cn.damei.entity.mobile.Manager.BizMaterialsStandard;
import cn.damei.entity.mobile.Manager.BizMaterialsStandardReceiveBillApply;
import cn.damei.service.mobile.Manager.ApplyStandardMaterialService;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.service.mobile.Manager.AppOrderService;
import cn.damei.entity.modules.BizOrderMaterialsStandard;
import cn.damei.service.modules.BizOrderMaterialsStandardService;
import cn.damei.service.modules.BizSeiralnumService;
@RequestMapping(value="${adminPath}/app/manager/applyStandardMaterial")
@Controller
public class ApplyStandardMaterialController {
	private static Logger logger = LoggerFactory.getLogger(ApplyProjectChangeController.class);// 日志
	
	@Autowired
	private ApplyStandardMaterialService applyStandardMaterialService;
	@Autowired
	private AppOrderService appOrderService;
	@Autowired
	private BizOrderMaterialsStandardService bizOrderMaterialsStandardService;
	@Autowired
	private BizSeiralnumService bizSeiralnumService;
	@RequestMapping(value="standarMaterialList")
	public String standarMaterialList(String villageAndName,HttpServletRequest request, Model model){
		Integer managerId = SessionUtils.getManagerSession(request).getId();
		ApplyStandardMaterial am = new ApplyStandardMaterial();
		am.setId(managerId);
		am.setCommunityName(villageAndName);
		List<ApplyStandardMaterial> list = applyStandardMaterialService.findOrderListById(am,"1");
		model.addAttribute("listOrder", list);
		return "mobile/modules/Manager/materialManagement/standarList";
	}
	
	@RequestMapping(value="applyStandarMaterialList/{orderId}")
	public String applyStandarMaterialList(@PathVariable String orderId,HttpServletRequest request, Model model){
		//查看是否竣工 1 竣工  0 未竣工 null 无状态
		String orderStatusByMaterislType = appOrderService.getOrderStatusByMaterislType(orderId, "1");
		if(!"0".equals(orderStatusByMaterislType)){
			model.addAttribute("applyFlag", false);
			return "mobile/modules/Manager/materialManagement/standarList";
		}
		//查询该订单下的没有查看的bill
		String materialIsView = bizOrderMaterialsStandardService.getMaterialIsView(orderId);
		//沒有未被查看的
		if(materialIsView!=null){
			model.addAttribute("isView", false);
			model.addAttribute("billId", materialIsView);
			model.addAttribute("orderId", orderId);
			return "mobile/modules/Manager/materialManagement/standarList";
		}else{
		Manager manager =(Manager) request.getSession().getAttribute("manager");
		String storeid = manager.getStoreid();
		//List<BizMaterialsStandardRecords> standardlist = applyStandardMaterialService.findBizMaterialsStandardList(storeid,orderId);
		 List<BizOrderMaterialsStandard> standardlist = bizOrderMaterialsStandardService.getList(storeid, orderId,"1");
		 model.addAttribute("standardlist", standardlist);
		model.addAttribute("orderId", orderId);
		return "mobile/modules/Manager/materialManagement/standarApply";
		}
	}
	@Transactional
	@RequestMapping(value="saveStandardBill")
	public String saveStandardBill(Integer shippingType,Double shippingFee,String random,String orderId,String[] id,String[] number,HttpServletRequest request, Model model ){
		//查看是否竣工 1 竣工  0 未竣工 null 无状态
				String orderStatusByMaterislType = appOrderService.getOrderStatusByMaterislType(orderId, "1");
				if(!"0".equals(orderStatusByMaterislType)){
					model.addAttribute("applyFlag", false);
					return "mobile/modules/Manager/materialManagement/standarList";
				}
				//查询该订单下的没有查看的bill
				String materialIsView = bizOrderMaterialsStandardService.getMaterialIsView(orderId);
				//沒有未被查看的
				if(materialIsView!=null){
					model.addAttribute("isView", false);
					model.addAttribute("billId", materialIsView);
					model.addAttribute("orderId", orderId);
					return "mobile/modules/Manager/materialManagement/standarList";
				}else{
		BizMaterialsStandardReceiveBillApply bsr = new BizMaterialsStandardReceiveBillApply();
					bsr.setShippingFee(shippingFee);
					bsr.setShippingType(shippingType);
		Integer managerId = SessionUtils.getManagerSession(request).getId();
		//根据经理的id查询门店的id
		String storeId = applyStandardMaterialService.findStoreIdByEmployeeId(managerId);
		bsr.setStoreId(Integer.valueOf(storeId));
		bsr.setApplyEmployeeId(managerId);
		bsr.setApplyDatetime(new Date());
		bsr.setOrderId(Integer.valueOf(orderId));
		//生成订单的编号
		String dateSequence = bizSeiralnumService.getDateSequence("BH");
		//设置订单的编号
		bsr.setMaterialsStandardReceiveBillCode(dateSequence);
		//订单的总金额
		Double count = 0.0;
		List<BizMaterialsStandard> list = new ArrayList<>();
		for (int i = 0; i < number.length; i++) {
			//在商品的数量大于0的时候在计算金额
			if(Integer.valueOf(number[i])>0){
				//根据id查询商品的单价
				BizMaterialsStandard temp = new BizMaterialsStandard();
				temp = applyStandardMaterialService.findStandardPriceById(id[i]);
				temp.setReceiveNumber(Double.valueOf(number[i]));
				list.add(temp);
				Double price = temp.getMaterialsPrice();
				Double valueOf = Double.valueOf(price);
				Integer valueOf2 = Integer.valueOf(number[i]);
				count = (valueOf*valueOf2) + count;
			}
		}
		Integer f=1;
		//配送加配送费
		if(f.equals(shippingType)) {
			if(shippingFee!=null) {
				count += shippingFee;
			}
		}
		DecimalFormat decimalFormat = new DecimalFormat(".#");
		double c =Double.parseDouble(decimalFormat.format(count)) ;
		bsr.setReceiveBillAmount(c);
		//设置为未查看
		bsr.setIsView("0");
		//设置状态
		bsr.setStatus(10);
		//设置 为标化1
		bsr.setReceiveBillType("1");
		//插入标化表
		Integer standardId = applyStandardMaterialService.saveMaterialsStandardReceiveBill(bsr);
		//插入成功后返回主键ID
		Integer receiveBillId = bsr.getId();
		//插入详情表
		List<ApplyMaterialsStandardReceiveDetail> listBD = new ArrayList<ApplyMaterialsStandardReceiveDetail>();
		
		int i = 0;
		//交易表對象
		ArrayList<BizOrderMaterialsStandard> bizOrderMaterialsStandardList = new ArrayList<BizOrderMaterialsStandard>();
		
		for (BizMaterialsStandard ms : list) {
			//交易表对象
			BizOrderMaterialsStandard bizOrderMaterialsStandard=new BizOrderMaterialsStandard();
			bizOrderMaterialsStandard.setMaterialsStandardId(ms.getId());
			bizOrderMaterialsStandard.setOrderId(Integer.valueOf(orderId));
			bizOrderMaterialsStandard.setMaterialsType(ms.getMaterialsType());
			bizOrderMaterialsStandard.setMaterialsName(ms.getMaterialsName());
			bizOrderMaterialsStandard.setMaterialsUnit(ms.getMaterialsUnit());
			bizOrderMaterialsStandard.setMaterialsPrice(ms.getMaterialsPrice());
			//交易金額 為0
			bizOrderMaterialsStandard.setMaterialsAmount(0d);;
			bizOrderMaterialsStandard.setApplyNumberTotal(ms.getReceiveNumber());
			bizOrderMaterialsStandard.setReceiveNumberTotal(0d);
			bizOrderMaterialsStandard.setMaxReceiveNumber(ms.getMaxReceiveNumber());
			bizOrderMaterialsStandardList.add(bizOrderMaterialsStandard);
			//标化详情表对象
			ApplyMaterialsStandardReceiveDetail bd = new ApplyMaterialsStandardReceiveDetail();
			bd.setMaterialsStandardReceiveBillId(receiveBillId);
			bd.setMaterialsId(ms.getId());
			bd.setMaterialsType(ms.getMaterialsType());
			bd.setMaterialsName(ms.getMaterialsName());
			bd.setMaterialsUnit(ms.getMaterialsUnit());
			bd.setMaterialsPrice(ms.getMaterialsPrice());
			bd.setReceiveNumber(ms.getReceiveNumber());
			bd.setMaterialsAmount((ms.getMaterialsPrice()*ms.getReceiveNumber()));
			bd.setMaxReceiveNumberSnap(ms.getMaxReceiveNumber());
			bd.setCreateDate(new Date());
			listBD.add(bd);
		}
		if(listBD.size()>0){
			applyStandardMaterialService.insertMaterialBillVO(listBD);
		}
		
		//插入或者修改交易表
		for (BizOrderMaterialsStandard bizOrderMaterialsStandard : bizOrderMaterialsStandardList) {
			BizOrderMaterialsStandard bizOrderMaterialsStandard2 = bizOrderMaterialsStandardService.getBizOrderMaterialsStandard(String.valueOf(bizOrderMaterialsStandard.getMaterialsStandardId()), orderId);
			//沒有則 添加
			if(bizOrderMaterialsStandard2==null){
				bizOrderMaterialsStandardService.save(bizOrderMaterialsStandard);
			//有则 修改	
			}else{
				//字段修改
				bizOrderMaterialsStandard.setId(bizOrderMaterialsStandard2.getId());
				bizOrderMaterialsStandard.setApplyNumberTotal(bizOrderMaterialsStandard2.getApplyNumberTotal()+bizOrderMaterialsStandard.getApplyNumberTotal());
				bizOrderMaterialsStandard.setMaterialsAmount(bizOrderMaterialsStandard2.getMaterialsAmount()+bizOrderMaterialsStandard.getMaterialsAmount());
				bizOrderMaterialsStandard.setReceiveNumberTotal(bizOrderMaterialsStandard2.getReceiveNumberTotal());
				bizOrderMaterialsStandardService.update(bizOrderMaterialsStandard);;
			}
		}
		//查询申请详情记录
		ApplyStandardMaterial asm = applyStandardMaterialService.findApplyStandardMaterialByOrderId(orderId);
		model.addAttribute("customerInfo",asm);
		BizMaterialsStandardReceiveBillApply bms = applyStandardMaterialService.findBizMaterialsStandardReceiveBillApplyByid(String.valueOf(receiveBillId));
		model.addAttribute("applyMaterial",bms);
		List<ApplyMaterialsStandardReceiveDetail> amsr = applyStandardMaterialService.findApplyMaterialsStandardReceiveDetailById(String.valueOf(receiveBillId));
		model.addAttribute("applyMaterialDetail",amsr);
		int size = amsr.size();
		model.addAttribute("totalSize",size);
		model.addAttribute("orderId",orderId);
		model.addAttribute("billId",receiveBillId);
		return "mobile/modules/Manager/materialManagement/standarDetails";
		}
	}
	
	
	@RequestMapping(value="detail")
	public String materialBillDetail(String id,String orderId,HttpServletRequest request, Model model){
		model.addAttribute("isView", true);
		ApplyStandardMaterial asm = applyStandardMaterialService.findApplyStandardMaterialByOrderId(orderId);
		model.addAttribute("customerInfo",asm);
		BizMaterialsStandardReceiveBillApply bms = applyStandardMaterialService.findBizMaterialsStandardReceiveBillApplyByid(id);
		model.addAttribute("applyMaterial",bms);
		List<ApplyMaterialsStandardReceiveDetail> amsr = applyStandardMaterialService.findApplyMaterialsStandardReceiveDetailById(id);
		model.addAttribute("applyMaterialDetail",amsr);
		int size = amsr.size();
		model.addAttribute("totalSize",size);
		model.addAttribute("orderId",orderId);
		return "mobile/modules/Manager/materialManagement/standarDetails";
	}
	@RequestMapping(value="record")
	public String record(String orderId,HttpServletRequest request, Model model){
		//查询申请记录
		ApplyStandardMaterial asm = applyStandardMaterialService.findApplyStandardMaterialByOrderId(orderId);
		model.addAttribute("customerInfo",asm);
		List<BizMaterialsStandardReceiveBillApply> fso = applyStandardMaterialService.findMaterialStandardBillByOrderId(orderId,"1");
		model.addAttribute("MaterialsStandardReceive", fso);
		return"mobile/modules/Manager/materialManagement/standarRecordList";
	}
	@RequestMapping(value="updateView")
	public @ResponseBody void updateBill(String billId){
		
			//先去查看
			String selectBillVile = bizOrderMaterialsStandardService.selectBillVile(billId);
			if(selectBillVile!=null&&selectBillVile.equals("1")){
				//已经查看过
			}else{
			//1 已經查看
			bizOrderMaterialsStandardService.updateBill("1", new Date(), billId);
				}
		}
	
	
}
