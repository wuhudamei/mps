

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
	private static Logger logger = LoggerFactory.getLogger(ApplyProjectChangeController.class);
	
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

		String orderStatusByMaterislType = appOrderService.getOrderStatusByMaterislType(orderId, "1");
		if(!"0".equals(orderStatusByMaterislType)){
			model.addAttribute("applyFlag", false);
			return "mobile/modules/Manager/materialManagement/standarList";
		}

		String materialIsView = bizOrderMaterialsStandardService.getMaterialIsView(orderId);

		if(materialIsView!=null){
			model.addAttribute("isView", false);
			model.addAttribute("billId", materialIsView);
			model.addAttribute("orderId", orderId);
			return "mobile/modules/Manager/materialManagement/standarList";
		}else{
		Manager manager =(Manager) request.getSession().getAttribute("manager");
		String storeid = manager.getStoreid();

		 List<BizOrderMaterialsStandard> standardlist = bizOrderMaterialsStandardService.getList(storeid, orderId,"1");
		 model.addAttribute("standardlist", standardlist);
		model.addAttribute("orderId", orderId);
		return "mobile/modules/Manager/materialManagement/standarApply";
		}
	}
	@Transactional
	@RequestMapping(value="saveStandardBill")
	public String saveStandardBill(Integer shippingType,Double shippingFee,String random,String orderId,String[] id,String[] number,HttpServletRequest request, Model model ){

				String orderStatusByMaterislType = appOrderService.getOrderStatusByMaterislType(orderId, "1");
				if(!"0".equals(orderStatusByMaterislType)){
					model.addAttribute("applyFlag", false);
					return "mobile/modules/Manager/materialManagement/standarList";
				}

				String materialIsView = bizOrderMaterialsStandardService.getMaterialIsView(orderId);

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

		String storeId = applyStandardMaterialService.findStoreIdByEmployeeId(managerId);
		bsr.setStoreId(Integer.valueOf(storeId));
		bsr.setApplyEmployeeId(managerId);
		bsr.setApplyDatetime(new Date());
		bsr.setOrderId(Integer.valueOf(orderId));

		String dateSequence = bizSeiralnumService.getDateSequence("BH");

		bsr.setMaterialsStandardReceiveBillCode(dateSequence);

		Double count = 0.0;
		List<BizMaterialsStandard> list = new ArrayList<>();
		for (int i = 0; i < number.length; i++) {

			if(Integer.valueOf(number[i])>0){

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

		if(f.equals(shippingType)) {
			if(shippingFee!=null) {
				count += shippingFee;
			}
		}
		DecimalFormat decimalFormat = new DecimalFormat(".#");
		double c =Double.parseDouble(decimalFormat.format(count)) ;
		bsr.setReceiveBillAmount(c);

		bsr.setIsView("0");

		bsr.setStatus(10);

		bsr.setReceiveBillType("1");

		Integer standardId = applyStandardMaterialService.saveMaterialsStandardReceiveBill(bsr);

		Integer receiveBillId = bsr.getId();

		List<ApplyMaterialsStandardReceiveDetail> listBD = new ArrayList<ApplyMaterialsStandardReceiveDetail>();
		
		int i = 0;

		ArrayList<BizOrderMaterialsStandard> bizOrderMaterialsStandardList = new ArrayList<BizOrderMaterialsStandard>();
		
		for (BizMaterialsStandard ms : list) {

			BizOrderMaterialsStandard bizOrderMaterialsStandard=new BizOrderMaterialsStandard();
			bizOrderMaterialsStandard.setMaterialsStandardId(ms.getId());
			bizOrderMaterialsStandard.setOrderId(Integer.valueOf(orderId));
			bizOrderMaterialsStandard.setMaterialsType(ms.getMaterialsType());
			bizOrderMaterialsStandard.setMaterialsName(ms.getMaterialsName());
			bizOrderMaterialsStandard.setMaterialsUnit(ms.getMaterialsUnit());
			bizOrderMaterialsStandard.setMaterialsPrice(ms.getMaterialsPrice());

			bizOrderMaterialsStandard.setMaterialsAmount(0d);;
			bizOrderMaterialsStandard.setApplyNumberTotal(ms.getReceiveNumber());
			bizOrderMaterialsStandard.setReceiveNumberTotal(0d);
			bizOrderMaterialsStandard.setMaxReceiveNumber(ms.getMaxReceiveNumber());
			bizOrderMaterialsStandardList.add(bizOrderMaterialsStandard);

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
		

		for (BizOrderMaterialsStandard bizOrderMaterialsStandard : bizOrderMaterialsStandardList) {
			BizOrderMaterialsStandard bizOrderMaterialsStandard2 = bizOrderMaterialsStandardService.getBizOrderMaterialsStandard(String.valueOf(bizOrderMaterialsStandard.getMaterialsStandardId()), orderId);

			if(bizOrderMaterialsStandard2==null){
				bizOrderMaterialsStandardService.save(bizOrderMaterialsStandard);

			}else{

				bizOrderMaterialsStandard.setId(bizOrderMaterialsStandard2.getId());
				bizOrderMaterialsStandard.setApplyNumberTotal(bizOrderMaterialsStandard2.getApplyNumberTotal()+bizOrderMaterialsStandard.getApplyNumberTotal());
				bizOrderMaterialsStandard.setMaterialsAmount(bizOrderMaterialsStandard2.getMaterialsAmount()+bizOrderMaterialsStandard.getMaterialsAmount());
				bizOrderMaterialsStandard.setReceiveNumberTotal(bizOrderMaterialsStandard2.getReceiveNumberTotal());
				bizOrderMaterialsStandardService.update(bizOrderMaterialsStandard);;
			}
		}

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

		ApplyStandardMaterial asm = applyStandardMaterialService.findApplyStandardMaterialByOrderId(orderId);
		model.addAttribute("customerInfo",asm);
		List<BizMaterialsStandardReceiveBillApply> fso = applyStandardMaterialService.findMaterialStandardBillByOrderId(orderId,"1");
		model.addAttribute("MaterialsStandardReceive", fso);
		return"mobile/modules/Manager/materialManagement/standarRecordList";
	}
	@RequestMapping(value="updateView")
	public @ResponseBody void updateBill(String billId){
		

			String selectBillVile = bizOrderMaterialsStandardService.selectBillVile(billId);
			if(selectBillVile!=null&&selectBillVile.equals("1")){

			}else{

			bizOrderMaterialsStandardService.updateBill("1", new Date(), billId);
				}
		}
	
	
}
