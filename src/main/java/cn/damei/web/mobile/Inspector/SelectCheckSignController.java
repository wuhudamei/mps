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

/**
 * 质检系统  抽检  签到
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/app/pqc/selectCheck/sign")
public class SelectCheckSignController {

	@Autowired
	private SelectCheckSignService signService;

	@Autowired
	private SelectCheckService selectCheckService;
	/**
	 * 到场签到
	 */
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

	/**
	 * 获取订单地址 经纬度
	 * 
	 * @param order
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "getAddress", "" })
	public @ResponseBody JSONArray getAddress(Order order, HttpServletRequest request, Model model) {
		
		String mapAddress = signService.getOrderLngLatByOrderId(order.getOrderId());

		String[] split = mapAddress.split(",");

		return JSONArray.fromObject(split);
	}

//	/**
//	 * 质检员  抽检  签到
//	 * @param request
//	 * @param sign
//	 * @param model
//	 * @param inspectId
//	 * @return
//	 */
//	@RequestMapping(value = "pqcsign", method = RequestMethod.POST)
//	public @ResponseBody String pqcsign(HttpServletRequest request, Sign inspectSign, Model model,String qcBillId,String orderId) {
//		
//		
//		//签到类型
//		inspectSign.setSignType("302");
//		// 质检员信息id
//		inspectSign.setSignEmployeeId(SessionUtils.getInspectorSession(request).getId());
//		// 签到时间
//		inspectSign.setSignDateTime(new Date());
//		//经纬度
//		Double  x =LngAndLatUtils.getLngAndLat(inspectSign.getSignAddress()).get("lng");
//		Double  y =LngAndLatUtils.getLngAndLat(inspectSign.getSignAddress()).get("lat");
//		inspectSign.setSignXy(x+","+y);
//		
//		
//		if(qcBillId.equals("0")){
//			//没有抽检单
//			//创建抽检单
//			BizQcBill bizQcBill = new BizQcBill();
//			bizQcBill.setQcBillCode(qcBillCode());
//			bizQcBill.setQcBillType("2");
//			bizQcBill.setIsRecheck("0");
//			bizQcBill.setOrderId(Integer.valueOf(orderId));
//			bizQcBill.setStatus("-1");
//			bizQcBill.setCreateDate(new Date());
//			bizQcBill.setUpdateDate(new Date());
//			signService.insertQcBill(bizQcBill);
//			
//			//抽检单id
//			inspectSign.setRelatedBusinessId(bizQcBill.getQcBillId());
//			inspectSign.setCreateDate(new Date());
//			inspectSign.setUpdateDate(new Date());
//			//保存抽检签到信息
//			signService.inspectorSign(inspectSign);
//		}else{
//			//存在抽检单
//			//抽检单id
//			inspectSign.setRelatedBusinessId(Integer.parseInt(qcBillId));
//			//1:根据抽检单id,节点查询签到表
//			Integer  record =signService.findInspectSignRecord(Integer.parseInt(qcBillId));
//			//2:如果该抽检单已经签到过  只更新签到时间, 否则 保存该签到
//			if(null!=record&&record!=0){
//				//已经签到过	
//				inspectSign.setUpdateDate(new Date());
//				signService.updateInspectRecord(inspectSign);
//			}else{
//				//没有签到
//				inspectSign.setCreateDate(new Date());
//				inspectSign.setUpdateDate(new Date());
//				signService.inspectorSign(inspectSign);
//			}
//		}
//		return "0";
//	}
	
	/**
	 * 质检员  抽检  签到
	 * @param detail
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "pqcsign")
	public @ResponseBody String pqcsign(Sign detail, Model model, HttpServletRequest request) {
		String flag = "error";
		try{

			BizQcBill bill =	selectCheckService.findSelectCheckIsExist(detail.getOrderId());
			if(null!=bill){

				detail.setRelatedBusinessId(bill.getQcBillId());

			}

			//获取质检
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
				//没有抽检单
				//创建抽检单
				BizQcBill bizQcBill = new BizQcBill();
				bizQcBill.setQcBillCode(qcBillCode());
				bizQcBill.setQcBillType(ConstantUtils.QC_BILL_TYPE_2);
				bizQcBill.setIsRecheck(ConstantUtils.IS_RECHECK_0);
				bizQcBill.setOrderId(detail.getOrderId());
				bizQcBill.setStatus("-1");
				bizQcBill.setCreateDate(date);
				bizQcBill.setUpdateDate(date);
				signService.insertQcBill(bizQcBill);
				
				//抽检单id
				inspectSign.setRelatedBusinessId(bizQcBill.getQcBillId());
				//保存抽检签到信息
				signService.inspectorSign(inspectSign);
			}else{
				//存在抽检单
				//抽检单id
				inspectSign.setRelatedBusinessId(detail.getRelatedBusinessId());
				//1:根据抽检单id,节点查询签到表
				Integer  record =signService.findInspectSignRecord(detail.getRelatedBusinessId());
				//2:如果该抽检单已经签到过  只更新签到时间, 否则 保存该签到
				if(null!=record&&record!=0){
					//已经签到过	
					signService.updateInspectRecord(inspectSign);
				}else{
					//没有签到
					signService.inspectorSign(inspectSign);
				}
			}
			flag = "success";
		}catch(Exception e){
			flag = "error";
		}
		
		return flag;
	}
	
	
	//抽检单编码
	public String  qcBillCode(){
		//以PO开头
		String purchaseCode = "ZJ";
	
		
		StringBuilder builder = new StringBuilder();
		
		//num和date
		//获取抽检单编码
		PurchaseTwoCode purchaseObj = signService.getCode();
		String format ="";
		String code ="";
		
		if(purchaseObj!=null){
			//流水号+1
			purchaseObj.setPurchaseCode(String.valueOf(Integer.parseInt(purchaseObj.getPurchaseCode())+1));
			//更新抽检单编码数据库
			signService.updateCode(purchaseObj);
			//格式后的时间戳
			format = new SimpleDateFormat("yyyyMMdd").format( purchaseObj.getAuxiliaryDate());
			//得到的流水号
			code = purchaseObj.getPurchaseCode();
		}else{
			PurchaseTwoCode purchaseObjVo = new PurchaseTwoCode();
			//流水号+1
			purchaseObjVo.setId(3);
			purchaseObjVo.setPurchaseCode(String.valueOf(1));
			purchaseObjVo.setAuxiliaryDate(new Date());
			//插入抽检单编码数据库
			signService.insertPurchase(purchaseObjVo);
			//格式后的时间戳
			format = new SimpleDateFormat("yyyyMMdd").format(new Date());
			//得到的流水号
			code = "1";
		}
		
		
	
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


}
