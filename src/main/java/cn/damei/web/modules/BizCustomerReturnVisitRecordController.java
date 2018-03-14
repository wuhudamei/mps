
package cn.damei.web.modules;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.BizCustomerReturnVisitRecordAnswer;
import cn.damei.entity.modules.BizCustomerReturnVisitTraditionOrderData;
import cn.damei.entity.modules.Dict;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.ExportCustomerReturnVisitRecord;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizCustomerReturnVisitContent;
import cn.damei.service.modules.BizCustomerReturnVisitService;
import cn.damei.service.modules.BizCustomerReturnVisitRecordAnswerService;
import cn.damei.dao.modules.BizCustomerReturnVisitOrderEnableDao;
import cn.damei.entity.modules.BizCustomerReturnVisitRecord;
import cn.damei.service.modules.BizCustomerReturnVisitRecordService;
import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.OrderService2;
import cn.damei.entity.modules.StatusDto;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/customerreturnvisitrecorecord/bizCustomerReturnVisitRecord")
public class BizCustomerReturnVisitRecordController extends BaseController {

	@Autowired
	BizCustomerReturnVisitRecordService bizCustomerReturnVisitRecordService;
	@Autowired
	BizCustomerReturnVisitRecordAnswerService bizCustomerReturnVisitRecordAnswerService;
	@Autowired
	BizCustomerReturnVisitService returnVisitService;

	@Autowired
	OrderService2 orderService2;

	@Autowired
	BizCustomerReturnVisitOrderEnableDao bizCustomerReturnVisitOrderEnableDao;


	private String[] satisfactionDegreeCodeArray = {"degree_001","degree_002","degree_003","degree_004"};
	private String[] satisfactionDegreeNameArray = {"满意","一般","不满意","不评价"};

	@ModelAttribute
	public BizCustomerReturnVisitRecord get(@RequestParam(required=false) Integer id) {
		BizCustomerReturnVisitRecord entity = null;
		if (id!=null){
			entity = bizCustomerReturnVisitRecordService.get(id);
		}
		if (entity == null){
			entity = new BizCustomerReturnVisitRecord();
		}
		return entity;
	}


	@RequiresPermissions("customerreturnvisitrecorecord:bizCustomerReturnVisitRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord, HttpServletRequest request, HttpServletResponse response, Model model) {

        User user = UserUtils.getUser();
        if(null==bizCustomerReturnVisitRecord.getStoreId()){
            if(null!=user.getStoreId()){
                bizCustomerReturnVisitRecord.setStoreId(user.getStoreId());
            }
        }
		Page<BizCustomerReturnVisitRecord> page = bizCustomerReturnVisitRecordService.findPage(new Page<BizCustomerReturnVisitRecord>(request, response), bizCustomerReturnVisitRecord);
		model.addAttribute("page", page);
		return "modules/customerreturnvisitrecorecord/bizCustomerReturnVisitRecordList";
	}


	@RequiresPermissions("customerreturnvisitrecorecord:bizCustomerReturnVisitRecord:view")
	@RequestMapping(value = "form")
	public String form(@RequestParam(required=true,value="dealReturnVisitNode") String dealReturnVisitNode,BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord, Model model) {
		BizCustomerReturnVisitRecord entity = new BizCustomerReturnVisitRecord();
		entity.setOrderId(bizCustomerReturnVisitRecord.getOrderId());
		entity.setReturnVisitNode(dealReturnVisitNode);
		try{

			Order2 order = orderService2.get( bizCustomerReturnVisitRecord.getOrderId());
			model.addAttribute("nodeName", returnVisitService.getReturnVisitName(dealReturnVisitNode, order.getStoreId(), order.getProjectMode()) );
			model.addAttribute("dealReturnVisitNode", dealReturnVisitNode );
			model.addAttribute("bizCustomerReturnVisitRecord", bizCustomerReturnVisitRecord);
			model.addAttribute("order", bizCustomerReturnVisitRecordService.getOrderInfoForCheck( entity ) );
			model.addAttribute("questionList", returnVisitService.getByProjectNode(dealReturnVisitNode, order.getStoreId(),order.getProjectMode() ));
			model.addAttribute("historyVisitRecord", bizCustomerReturnVisitRecordService.getOrderHistoryVisitRecord( bizCustomerReturnVisitRecord.getOrderId() ) );
			model.addAttribute("invalidRecordList", returnVisitService.getInvalidRecordList(dealReturnVisitNode, bizCustomerReturnVisitRecord.getOrderId()));
			model.addAttribute("projectModeValue",order.getProjectMode());

		}catch(Exception e){
			e.printStackTrace();
		}
		return "modules/customerreturnvisitrecorecord/bizCustomerReturnVisitSubmit";
	}


	@RequiresPermissions("customerreturnvisitrecorecord:bizCustomerReturnVisitRecord:view")
	@RequestMapping(value = "recordDetail")
	public String recordDetail(BizCustomerReturnVisitRecord customerReturnVisitRecord, Model model) {

		model.addAttribute("nodeName", returnVisitService.getReturnVisitName(customerReturnVisitRecord.getReturnVisitNode(),customerReturnVisitRecord.getStoreId(),customerReturnVisitRecord.getProjectMode() ) );
		model.addAttribute("order", bizCustomerReturnVisitRecordService.getOrderInfoForCheck( customerReturnVisitRecord ) );
		model.addAttribute("questionList", bizCustomerReturnVisitRecordService.getReturnVisitRecordQuestion( customerReturnVisitRecord.getId() ));
		model.addAttribute("invalidRecordList", returnVisitService.getInvalidRecordList(customerReturnVisitRecord.getReturnVisitNode(), customerReturnVisitRecord.getOrderId()));
		return "modules/customerreturnvisitrecorecord/bizCustomerReturnVisitDetail";
	}


	@RequiresPermissions("customerreturnvisitrecorecord:bizCustomerReturnVisitRecord:edit")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	@ResponseBody
	public Object save(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord,HttpServletRequest request) {
		try{
			String[] questionContent = request.getParameterValues("questionContent[]");
			String[] statisticsDepartment = request.getParameterValues("statisticsDepartment[]");
			String[] statisticsResult = request.getParameterValues("statisticsResult[]");
			String[] questionAnswer = request.getParameterValues("questionAnswer[]");
			bizCustomerReturnVisitRecordService.save(bizCustomerReturnVisitRecord,questionContent,statisticsDepartment,statisticsResult,questionAnswer);
			if(null!=bizCustomerReturnVisitRecord.getOrderId()){
				bizCustomerReturnVisitRecordService.updateStatusHaveDone(bizCustomerReturnVisitRecord.getOrderId());
			}
			return StatusDto.buildDataSuccessStatusDto("保存成功");
		}catch(Exception e){
			e.printStackTrace();
			return StatusDto.buildDataFailureStatusDto("保存失败");
		}
	}



	@RequiresPermissions("customerreturnvisitrecorecord:bizCustomerReturnVisitRecord:edit")
	@RequestMapping(value = "invalidSubmit",method=RequestMethod.POST)
	@ResponseBody
	public Object invalidSubmit(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord,@RequestParam(required=true,value="invalidReason") String invalidReason) {
		try{

			bizCustomerReturnVisitRecordService.saveInvalidRecord(bizCustomerReturnVisitRecord,invalidReason);
			return StatusDto.buildDataSuccessStatusDto("保存成功");
		}catch(Exception e){
			e.printStackTrace();
			return StatusDto.buildDataFailureStatusDto("保存失败");
		}
	}


	@RequiresPermissions("customerreturnvisitrecorecord:bizCustomerReturnVisitRecord:view")
	@RequestMapping(value = {"checkingList", ""})
	public String checkingList(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord, HttpServletRequest request, HttpServletResponse response, Model model) {

		List<Dict>listStatus=bizCustomerReturnVisitRecordService.findProjectMode();
		model.addAttribute("listStatus", listStatus);
		Page<BizCustomerReturnVisitRecord> page = bizCustomerReturnVisitRecordService.findPageForChecking(new Page<BizCustomerReturnVisitRecord>(request, response), bizCustomerReturnVisitRecord);
		model.addAttribute("page", page);
		return "modules/customerreturnvisitrecorecord/bizCustomerReturnVisitCheckingList";
	}


	@RequestMapping(value = {"traditionalorderlist", ""})
	public String traditionalOrderList(BizCustomerReturnVisitTraditionOrderData bizCustomerReturnVisitTraditionOrderData, HttpServletRequest request, HttpServletResponse response, Model model) {

		Page<BizCustomerReturnVisitTraditionOrderData> page = bizCustomerReturnVisitRecordService.findPageForTraditionOrder(new Page<BizCustomerReturnVisitTraditionOrderData>(request, response), bizCustomerReturnVisitTraditionOrderData);
		model.addAttribute("page", page);
		return "modules/customerreturnvisitrecorecord/bizCustomerReturnVisitTraditionOrderList";
	}


	@RequestMapping(value = "queryReturnVisitNodeByStoreId",method=RequestMethod.GET)
	@ResponseBody
	public Object queryReturnVisitNodeByStoreId(@RequestParam(value = "storeId", required = true) String storeId){
		List<Map<String,Object>> result = bizCustomerReturnVisitRecordService.queryReturnVisitNodeByStoreId(storeId);
		return StatusDto.buildDataSuccessStatusDto(result);
	}


	@RequiresPermissions("customerreturnvisitrecorecord:bizCustomerReturnVisitRecord:view")
	@RequestMapping(value = {"controlList", ""})
	public String controlList(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizCustomerReturnVisitRecord> page = bizCustomerReturnVisitRecordService.findPageForChecking1(new Page<BizCustomerReturnVisitRecord>(request, response), bizCustomerReturnVisitRecord);
		model.addAttribute("page", page);
		return "modules/customerreturnvisitrecorecord/bizCustomerReturnVisitControlList";
	}


	@RequestMapping(value = {"invalidList", ""})
	public String invalidList(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizCustomerReturnVisitRecord> page = bizCustomerReturnVisitRecordService.invalidList(new Page<BizCustomerReturnVisitRecord>(request, response), bizCustomerReturnVisitRecord);
		model.addAttribute("page", page);
		return "modules/customerreturnvisitrecorecord/bizCustomerReturnVisitInvalidList";
	}


	@RequiresPermissions("customerreturnvisitrecorecord:bizCustomerReturnVisitRecord:view")
	@RequestMapping(value = {"returnVisitReportPage", ""})
	public String returnVisitReportPage(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord,Model model) {
		try{
			Date lastDay = DateUtils.addDate(new Date(), -1);
			bizCustomerReturnVisitRecord.setVisitDateBegin( lastDay );
			bizCustomerReturnVisitRecord.setVisitDateEnd( lastDay );

			model.addAttribute("bizCustomerReturnVisitRecord", bizCustomerReturnVisitRecord);
		}catch( Exception e ){
			e.printStackTrace();
		}

		return "modules/customerreturnvisitrecorecord/dailyReportPage";
	}


	@RequiresPermissions("customerreturnvisitrecorecord:bizCustomerReturnVisitRecord:view")
	@RequestMapping(value = {"visitRecordSummaryQuery", ""})
	public String visitRecordSummaryQuery(
			BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord,
			Model model) {
		try{
			SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
			Map<String,String> param = new HashMap<String,String>();

			param.put("storeId", bizCustomerReturnVisitRecord.getStoreId());

			if( bizCustomerReturnVisitRecord.getVisitDateBegin() != null ){
				param.put("returnVisitTimeBegin", sdf.format( bizCustomerReturnVisitRecord.getVisitDateBegin() )  );
			}
			if( bizCustomerReturnVisitRecord.getVisitDateEnd() != null ){
				param.put("returnVisitTimeEnd", sdf.format( bizCustomerReturnVisitRecord.getVisitDateEnd() )  );
			}

			Map<String,Object> resultMap = bizCustomerReturnVisitRecordService.visitRecordSummaryQuery(param);

			model.addAttribute("nodeArray", resultMap.get("nodeArray"));
			model.addAttribute("titleArray", resultMap.get("titleArray"));
			model.addAttribute("resultList", resultMap.get("resultList"));
			model.addAttribute("bizCustomerReturnVisitRecord", bizCustomerReturnVisitRecord);

		}catch( Exception e ){
			e.printStackTrace();
		}

		return "modules/customerreturnvisitrecorecord/dailyReportPage";
	}



	@RequiresPermissions("customerreturnvisitrecorecord:bizCustomerReturnVisitRecord:view")
	@RequestMapping(value = {"satisfactionDegreeReportPage", ""})
	public String satisfactionDegreeReportPage(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord,Model model) {
		try{
			Date lastDay = DateUtils.addDate(new Date(), -1);
			bizCustomerReturnVisitRecord.setVisitDateBegin( lastDay );
			bizCustomerReturnVisitRecord.setVisitDateEnd( lastDay );

			model.addAttribute("bizCustomerReturnVisitRecord", bizCustomerReturnVisitRecord);
			model.addAttribute("titleArray", satisfactionDegreeNameArray);
		}catch( Exception e ){
			e.printStackTrace();
		}

		return "modules/customerreturnvisitrecorecord/satisfactionDegreeReportPage";
	}


	@RequiresPermissions("customerreturnvisitrecorecord:bizCustomerReturnVisitRecord:view")
	@RequestMapping(value = {"satisfactionDegreeQuery", ""})
	public String satisfactionDegreeQuery(
			BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord,
			Model model) {
		try{
			SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
			Map<String,String> param = new HashMap<String,String>();

			param.put("storeId", bizCustomerReturnVisitRecord.getStoreId());

			if( bizCustomerReturnVisitRecord.getVisitDateBegin() != null ){
				param.put("returnVisitTimeBegin", sdf.format( bizCustomerReturnVisitRecord.getVisitDateBegin() )  );
			}
			if( bizCustomerReturnVisitRecord.getVisitDateEnd() != null ){
				param.put("returnVisitTimeEnd", sdf.format( bizCustomerReturnVisitRecord.getVisitDateEnd() )  );
			}

			List<Map<String,Object>> resultList = bizCustomerReturnVisitRecordService.satisfactionDegreeQuery(param,satisfactionDegreeCodeArray,satisfactionDegreeNameArray);

			model.addAttribute("nodeArray", satisfactionDegreeCodeArray );
			model.addAttribute("titleArray", satisfactionDegreeNameArray );

			model.addAttribute("resultList", resultList);
			model.addAttribute("bizCustomerReturnVisitRecord", bizCustomerReturnVisitRecord);

		}catch( Exception e ){
			e.printStackTrace();
		}

		return "modules/customerreturnvisitrecorecord/satisfactionDegreeReportPage";
	}


	@RequiresPermissions("customerreturnvisitrecorecord:bizCustomerReturnVisitRecord:view")
	@RequestMapping(value = { "export", "" })
	public @ResponseBody void  export(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord,HttpServletRequest request,HttpServletResponse  response) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");




		List<BizCustomerReturnVisitRecord> list = bizCustomerReturnVisitRecordService.findList(bizCustomerReturnVisitRecord);
		List<Integer> ids = new ArrayList<>();
		for (BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord2 : list) {
			ids.add(bizCustomerReturnVisitRecord2.getId());
		}
		List<BizCustomerReturnVisitRecordAnswer> listByRecordIds = bizCustomerReturnVisitRecordAnswerService.getListByRecordIds(ids);
		Map<Integer, List<BizCustomerReturnVisitRecordAnswer>> map = new HashMap<>();
		for (BizCustomerReturnVisitRecordAnswer bizCustomerReturnVisitRecordAnswer:listByRecordIds){
			Integer returnVisitRecordId = bizCustomerReturnVisitRecordAnswer.getReturnVisitRecordId();
			List<BizCustomerReturnVisitRecordAnswer> bizCustomerReturnVisitRecordAnswers = map.get(returnVisitRecordId);
			if(bizCustomerReturnVisitRecordAnswers ==null){
				List<BizCustomerReturnVisitRecordAnswer> lists = new ArrayList<>();
				lists.add(bizCustomerReturnVisitRecordAnswer);
				map.put(returnVisitRecordId,lists);
			}else{
				bizCustomerReturnVisitRecordAnswers.add(bizCustomerReturnVisitRecordAnswer);
				map.put(returnVisitRecordId,bizCustomerReturnVisitRecordAnswers);
			}
		}
		for (BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord2 : list) {
			Integer id = bizCustomerReturnVisitRecord2.getId();
			bizCustomerReturnVisitRecord2.setAnswerList(map.get(id));
		}
		List<BizCustomerReturnVisitContent> questionList = returnVisitService.getByProjectNodeForExport(bizCustomerReturnVisitRecord.getReturnVisitNode(), bizCustomerReturnVisitRecord.getStoreId(),bizCustomerReturnVisitRecord.getProjectMode());
		HSSFWorkbook problemDetail = ExportCustomerReturnVisitRecord.exportCustomerReturnVisitRecord(questionList,list);
		ServletOutputStream ouputStream= null;
		try {
			response.setContentType("application/binary;charset=utf-8");
			String headerStr =new String(("客户回访统计表"+sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");
			ouputStream = response.getOutputStream();
			problemDetail.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (IOException ex) {
            ex.printStackTrace();
        }
	}

}