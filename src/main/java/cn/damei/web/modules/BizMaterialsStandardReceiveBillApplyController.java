package cn.damei.web.modules;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.BizMaterialsStandardRecords;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.service.mobile.Manager.AppOrderService;
import cn.damei.entity.modules.ReciveJson;
import cn.damei.entity.modules.BizOrderMaterialsStandard;
import cn.damei.service.modules.BizOrderMaterialsStandardService;
import cn.damei.entity.modules.BizMaterialsStandardReceiveBillApply;
import cn.damei.service.modules.BizMaterialsStandardReceiveBillApplyService;
import cn.damei.entity.modules.BizMaterialsStandardReceiveDetail;
import cn.damei.service.modules.BizMaterialsStandardReceiveDetailService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

import net.sf.json.JSONArray;
@Controller                       
@RequestMapping(value="${adminPath}/standradmaterialsrecords/bizMaterialsStandardReceiveBillApply")
public class BizMaterialsStandardReceiveBillApplyController extends BaseController{
	
	@Autowired
	private BizMaterialsStandardReceiveBillApplyService bizMaterialsStandardReceiveBillApplyService;
	@Autowired

	private BizOrderMaterialsStandardService bizOrderMaterialsStandardService;
	@Autowired
	private AppOrderService appOrderService;
	@Autowired

	private BizMaterialsStandardReceiveDetailService bizMaterialsStandardReceiveDetailService;
	
	@ModelAttribute
	public BizMaterialsStandardReceiveBillApply get(@RequestParam(required=false) Integer id) {
		BizMaterialsStandardReceiveBillApply entity = null;
		if (id != null){
			entity = bizMaterialsStandardReceiveBillApplyService.get(id);
		}
		if (entity == null){
			entity = new BizMaterialsStandardReceiveBillApply();
		}
		return entity;
	}

	@RequiresPermissions("standradmaterialsapplyrecords:bizMaterialsStandardReceiveBillApply:edit")
	@RequestMapping(value = "list")
	public String materialsApplyBillList(BizMaterialsStandardReceiveBillApply bizMaterialsStandardReceiveBillApply,
			HttpServletRequest request, HttpServletResponse response, Model model){
		return "modules/managerSettlement/standradmaterialsapplybill/bizMaterialsStandardReceiveBillApplyList";
	}

    @RequiresPermissions("standradmaterialsapplyrecords:bizMaterialsStandardReceiveBillApply:edit")
    @RequestMapping(value = "findlist")
    public String findList(BizMaterialsStandardReceiveBillApply bizMaterialsStandardReceiveBillApply,
                                         HttpServletRequest request, HttpServletResponse response, Model model){
        bizMaterialsStandardReceiveBillApply.setReceiveBillType("1");
        Page<BizMaterialsStandardReceiveBillApply> page = bizMaterialsStandardReceiveBillApplyService.findPage(new Page<BizMaterialsStandardReceiveBillApply>(request, response), bizMaterialsStandardReceiveBillApply);
        model.addAttribute("page", page);
        return "modules/managerSettlement/standradmaterialsapplybill/bizMaterialsStandardReceiveBillApplyList";
    }

	@RequiresPermissions("standradmaterialsapplyrecords:bizMaterialsStandardReceiveBillApply:edit")
	@RequestMapping(value="from/{ssid}/{ssstatus}/{winDate}",method = RequestMethod.GET)
	public String from(@PathVariable("ssid") String id,@PathVariable("ssstatus") String status,@PathVariable("winDate") String winDate,
			HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException{
		User user = UserUtils.getUser();
		String id2 = user.getId();
		Date date = new Date();
		BizMaterialsStandardReceiveBillApply bizMaterialsStandardReceiveBillApply = new BizMaterialsStandardReceiveBillApply();

		String orderId = bizMaterialsStandardReceiveDetailService.getOrderId(id);

		String orderStatusByMaterislType = appOrderService.getOrderStatusByMaterislType(orderId, "1");
		if(!"0".equals(orderStatusByMaterislType)){
			status="30";
			winDate="基装验收完成";
		}

		if(Integer.valueOf(status)!=30){
				Date dates = new SimpleDateFormat("yyyy-MM-dd").parse(winDate);
				bizMaterialsStandardReceiveBillApply.setReceiveDatetime(dates);

				bizMaterialsStandardReceiveDetailService.updateDtaileApplySnape(id);

				List<BizMaterialsStandardReceiveDetail> findDetailsByBillId = bizMaterialsStandardReceiveDetailService.findDetailsByBillId(Integer.valueOf(id));

				for (BizMaterialsStandardReceiveDetail bizMaterialsStandardReceiveDetail : findDetailsByBillId) {

					BizOrderMaterialsStandard bizOrderMaterialsStandard2 = bizOrderMaterialsStandardService.getBizOrderMaterialsStandard(String.valueOf(bizMaterialsStandardReceiveDetail.getMaterialsId()), orderId);
					if(bizOrderMaterialsStandard2!=null){

					Double receiveNumber = bizMaterialsStandardReceiveDetail.getReceiveNumber();

					Double applyNumberTotal = bizOrderMaterialsStandard2.getApplyNumberTotal();
					if(applyNumberTotal!=null){
					bizOrderMaterialsStandard2.setApplyNumberTotal(applyNumberTotal-receiveNumber);
					}

					bizOrderMaterialsStandard2.setReceiveNumberTotal(receiveNumber+bizOrderMaterialsStandard2.getReceiveNumberTotal());


					bizOrderMaterialsStandard2.setMaterialsAmount(bizOrderMaterialsStandard2.getMaterialsAmount()+receiveNumber*bizOrderMaterialsStandard2.getMaterialsPrice());
					bizOrderMaterialsStandardService.updateOrderMaterialsByOrderIdAndStandId(bizOrderMaterialsStandard2);
					}else{
						BizOrderMaterialsStandard bizOrderMaterialsStandard=new BizOrderMaterialsStandard();
						bizOrderMaterialsStandard.setOrderId(Integer.valueOf(orderId));
						bizOrderMaterialsStandard.setMaterialsStandardId(bizMaterialsStandardReceiveDetail.getMaterialsId());
						bizOrderMaterialsStandard.setMaterialsType(bizMaterialsStandardReceiveDetail.getMaterialsType());
						bizOrderMaterialsStandard.setMaterialsName(bizMaterialsStandardReceiveDetail.getMaterialsName());
						bizOrderMaterialsStandard.setMaterialsUnit(bizMaterialsStandardReceiveDetail.getMaterialsUnit());
						bizOrderMaterialsStandard.setMaterialsPrice(bizMaterialsStandardReceiveDetail.getMaterialsPrice());
						bizOrderMaterialsStandard.setApplyNumberTotal(bizMaterialsStandardReceiveDetail.getReceiveNumber());
						bizOrderMaterialsStandard.setReceiveNumberTotal(bizMaterialsStandardReceiveDetail.getReceiveNumber());
						bizOrderMaterialsStandard.setMaxReceiveNumber(bizMaterialsStandardReceiveDetail.getMaxReceiveNumberSnap());
						bizOrderMaterialsStandard.setMaterialsAmount(bizMaterialsStandardReceiveDetail.getMaterialsAmount());
						bizOrderMaterialsStandardService.save(bizOrderMaterialsStandard);
					}
					
					}
				
		}
		else{

			List<BizMaterialsStandardReceiveDetail> findDetailsByBillId = bizMaterialsStandardReceiveDetailService.findDetailsByBillId(Integer.valueOf(id));


			for (BizMaterialsStandardReceiveDetail bizMaterialsStandardReceiveDetail : findDetailsByBillId) {

				BizOrderMaterialsStandard bizOrderMaterialsStandard2 = bizOrderMaterialsStandardService.getBizOrderMaterialsStandard(String.valueOf(bizMaterialsStandardReceiveDetail.getMaterialsId()), orderId);
				if(bizOrderMaterialsStandard2!=null){

				Double receiveNumber = bizMaterialsStandardReceiveDetail.getReceiveNumber();

				Double materialsPrice = bizMaterialsStandardReceiveDetail.getMaterialsPrice();

				Double applyNumberTotal = bizOrderMaterialsStandard2.getApplyNumberTotal();
				if(applyNumberTotal!=null){
				bizOrderMaterialsStandard2.setApplyNumberTotal(applyNumberTotal-receiveNumber);
				}

				bizOrderMaterialsStandardService.updateOrderMaterialsByOrderIdAndStandId(bizOrderMaterialsStandard2);
				}else{
					BizOrderMaterialsStandard bizOrderMaterialsStandard=new BizOrderMaterialsStandard();
					bizOrderMaterialsStandard.setOrderId(Integer.valueOf(orderId));
					bizOrderMaterialsStandard.setMaterialsStandardId(bizMaterialsStandardReceiveDetail.getMaterialsId());
					bizOrderMaterialsStandard.setMaterialsType(bizMaterialsStandardReceiveDetail.getMaterialsType());
					bizOrderMaterialsStandard.setMaterialsName(bizMaterialsStandardReceiveDetail.getMaterialsName());
					bizOrderMaterialsStandard.setMaterialsUnit(bizMaterialsStandardReceiveDetail.getMaterialsUnit());
					bizOrderMaterialsStandard.setMaterialsPrice(bizMaterialsStandardReceiveDetail.getMaterialsPrice());
					bizOrderMaterialsStandard.setApplyNumberTotal(0d);
					bizOrderMaterialsStandard.setReceiveNumberTotal(0d);
					bizOrderMaterialsStandard.setMaxReceiveNumber(bizMaterialsStandardReceiveDetail.getMaxReceiveNumberSnap());
					bizOrderMaterialsStandard.setMaterialsAmount(0d);
					bizOrderMaterialsStandardService.save(bizOrderMaterialsStandard);
				}
			}
			
			bizMaterialsStandardReceiveBillApply.setReceiveDatetime(null);
			bizMaterialsStandardReceiveBillApply.setAbandonReason(winDate);
		}
		bizMaterialsStandardReceiveBillApply.setId(Integer.parseInt(id));
		bizMaterialsStandardReceiveBillApply.setStatus(Integer.parseInt(status));
		bizMaterialsStandardReceiveBillApply.setOperatorEmployeeId(Integer.valueOf(id2));
		bizMaterialsStandardReceiveBillApply.setOperateDatetime(date);
		bizMaterialsStandardReceiveBillApplyService.materialsApplyBillById(bizMaterialsStandardReceiveBillApply);
		return "redirect:"+Global.getAdminPath()+"/standradmaterialsrecords/bizMaterialsStandardReceiveBillApply/list";
	}

	@RequiresPermissions("standradmaterialsapplyrecords:bizMaterialsStandardReceiveBillApply:edit")
	@RequestMapping(value="detail/{billId}/{billStatus}",method = RequestMethod.GET)
	public String detail(@PathVariable("billId")String billId,@PathVariable("billStatus")Integer billStatus,HttpServletRequest request, HttpServletResponse response, Model model){
		BizMaterialsStandardReceiveBillApply ba = new BizMaterialsStandardReceiveBillApply();
		ba.setStatus(Integer.valueOf(billStatus));
		ba.setId(Integer.valueOf(billId));
		List<BizMaterialsStandardReceiveBillApply> list = bizMaterialsStandardReceiveBillApplyService.materialsApplyBillVoById(ba);
		BizMaterialsStandardReceiveBillApply app = list.get(0);
		if(list.size()!=1){
			app.setOperateDatetime(null);
			app.setName(null);
		}
		model.addAttribute("list", app);
		List<BizMaterialsStandardRecords> listStandard = bizMaterialsStandardReceiveBillApplyService.findApplyMaterialsStandardReceiveDetailById(billId);
		model.addAttribute("listStandard", listStandard);
		return "modules/managerSettlement/standradmaterialsapplybill/bizMaterialsStandardReceiveBillDetail";
	}
	
	
	
	
	
	

	
	@RequestMapping(value = "api/receiveJsonDate")
	@ResponseBody
	public String receiveJsonDate(ReciveJson reciveJson,HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		String businessType = reciveJson.getBusiness_type();
		JSONArray fromObject = JSONArray.fromObject(reciveJson);
		String string2 = fromObject.getJSONObject(0).getString("businessType");
		String string = fromObject.toString();
		String[] split = string.split(",");
		

		
		return "";
	}
	
	
	
	@RequestMapping(value = "sendJsonDate")
	@ResponseBody
	public String sendJsonDate(HttpServletRequest request, HttpServletResponse response, Model model){


		
		return "";
	}
	
	
}
