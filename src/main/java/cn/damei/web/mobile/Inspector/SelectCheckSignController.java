package cn.damei.web.mobile.Inspector;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import cn.damei.entity.mobile.Manager.PurchaseTwoCode;
import cn.damei.service.mobile.Inspector.SelectCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.entity.mobile.Inspector.BizQcBill;
import cn.damei.entity.mobile.Inspector.Order;
import cn.damei.entity.mobile.Inspector.Sign;
import cn.damei.service.mobile.Inspector.SelectCheckSignService;

import net.sf.json.JSONArray;


@Controller
@RequestMapping(value = "${adminPath}/app/pqc/selectCheck/sign")
public class SelectCheckSignController {

	@Autowired
	private SelectCheckSignService signService;

	@Autowired
	private SelectCheckService selectCheckService;

	@RequestMapping(value = { "list", "" })
	public String list(HttpServletRequest request, Model model, Order order) {

		String mapAddress = signService.getOrderLngLatByOrderId(order.getOrderId());
		String[] split = mapAddress.split(",");
		model.addAttribute("lon", split[0]);
		model.addAttribute("lat", split[1]);
		model.addAttribute("qcBillId", order.getQcBillId());
		model.addAttribute("orderId", order.getOrderId());
		return "mobile/modules/pqc/selectCheck/sign/map";
	}


	@RequestMapping(value = { "getAddress", "" })
	public @ResponseBody JSONArray getAddress(Order order, HttpServletRequest request, Model model) {
		
		String mapAddress = signService.getOrderLngLatByOrderId(order.getOrderId());

		String[] split = mapAddress.split(",");

		return JSONArray.fromObject(split);
	}
































































	

	@RequestMapping(value = "pqcsign")
	public @ResponseBody String pqcsign(Sign detail, Model model, HttpServletRequest request) {
		String flag = "error";
		try{

			BizQcBill bill =	selectCheckService.findSelectCheckIsExist(detail.getOrderId());
			if(null!=bill){

				detail.setRelatedBusinessId(bill.getQcBillId());

			}


			Inspector inspector = SessionUtils.getInspectorSession(request);
			Date date = new Date();
			
			Sign inspectSign = new Sign();
			
			inspectSign.setSignType(ConstantUtils.SIGN_TYPE_INSPECTOR_CHECK_302);
			inspectSign.setSignEmployeeId(inspector.getId());
			inspectSign.setSignDateTime(date);
			inspectSign.setSignAddress(detail.getSignAddress());
			inspectSign.setSignXy(detail.getLon()+","+detail.getLat());
			inspectSign.setSignErrorDistance(detail.getSignErrorDistance());
			inspectSign.setCreateDate(date);
			inspectSign.setUpdateDate(date);
			inspectSign.setDelFlag("0");
			
			if(detail.getRelatedBusinessId()==0){


				BizQcBill bizQcBill = new BizQcBill();
				bizQcBill.setQcBillCode(qcBillCode());
				bizQcBill.setQcBillType(ConstantUtils.QC_BILL_TYPE_2);
				bizQcBill.setIsRecheck(ConstantUtils.IS_RECHECK_0);
				bizQcBill.setOrderId(detail.getOrderId());
				bizQcBill.setStatus("-1");
				bizQcBill.setCreateDate(date);
				bizQcBill.setUpdateDate(date);
				signService.insertQcBill(bizQcBill);
				

				inspectSign.setRelatedBusinessId(bizQcBill.getQcBillId());

				signService.inspectorSign(inspectSign);
			}else{


				inspectSign.setRelatedBusinessId(detail.getRelatedBusinessId());

				Integer  record =signService.findInspectSignRecord(detail.getRelatedBusinessId());

				if(null!=record&&record!=0){

					signService.updateInspectRecord(inspectSign);
				}else{

					signService.inspectorSign(inspectSign);
				}
			}
			flag = "success";
		}catch(Exception e){
			flag = "error";
		}
		
		return flag;
	}
	
	

	public String  qcBillCode(){

		String purchaseCode = "ZJ";
	
		
		StringBuilder builder = new StringBuilder();
		


		PurchaseTwoCode purchaseObj = signService.getCode();
		String format ="";
		String code ="";
		
		if(purchaseObj!=null){

			purchaseObj.setPurchaseCode(String.valueOf(Integer.parseInt(purchaseObj.getPurchaseCode())+1));

			signService.updateCode(purchaseObj);

			format = new SimpleDateFormat("yyyyMMdd").format( purchaseObj.getAuxiliaryDate());

			code = purchaseObj.getPurchaseCode();
		}else{
			PurchaseTwoCode purchaseObjVo = new PurchaseTwoCode();

			purchaseObjVo.setId(3);
			purchaseObjVo.setPurchaseCode(String.valueOf(1));
			purchaseObjVo.setAuxiliaryDate(new Date());

			signService.insertPurchase(purchaseObjVo);

			format = new SimpleDateFormat("yyyyMMdd").format(new Date());

			code = "1";
		}
		
		
	
		builder.append(purchaseCode).append(format);

		if(code.length()==1){
			
			builder.append("000").append(code);
			
		}else if(code.length()==2){

			builder.append("00").append(code);
		}else if(code.length()==3){
			builder.append("0").append(code);
		}else if(code.length()==4){
			builder.append(code);
		}
		

		return builder.toString();
	}


}
