package cn.damei.web.mobile.home;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.service.modules.ScoreOrderService;


@Controller
@RequestMapping(value="${adminPath}/app/home/score")
public class ScoreController{
	
	@Autowired
	private ScoreOrderService scoreOrderService;
	

	private final String RESULT = "result";

	private final String CODE = "code";

	private final String MESSAGE = "message";

	private final String DATA = "data";
	
	private final Integer SUCCESS_FLAG = 0;
	private final Integer ERROR_FLAG = 1;
	private final Integer SUCCESS_CODE = 200;	
	

	@RequestMapping(value="/order")
	public String toOrderPage(){
		return "mobile/modules/home/score/teamGroup";
	}
	

	@RequestMapping(value="/team")
	public String toTeamPage(){
		return "mobile/modules/home/score/team";
	}
	

	@RequestMapping(value="/complaint")
	public String toComplaintPage(){
		return "mobile/modules/home/score/feedback";
	}
	

	@RequestMapping(value="getCustomerOrder",method=RequestMethod.GET)
	@ResponseBody
	public Object getCustomerOrder(HttpServletRequest request) throws JsonParseException, JsonMappingException{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		String username = (String) request.getSession().getAttribute("customerPhone");
		if( StringUtils.isBlank( username ) ){
			resultMap.put(RESULT, ERROR_FLAG);
			resultMap.put(CODE, "202");
			resultMap.put(MESSAGE, "获取登录用信息失败！");
		}else{
			resultMap.put(RESULT, SUCCESS_FLAG);
			resultMap.put(CODE, SUCCESS_CODE);
			resultMap.put(MESSAGE, "");
			try{
				resultMap.put(DATA, this.scoreOrderService.selectOrderByCustomer(username));
			}catch(Exception e){
				e.printStackTrace();
				resultMap.put(RESULT, ERROR_FLAG);
				resultMap.put(CODE, "201");
			}
		}
		
		return resultMap; 
	}
	

	@RequestMapping(value="getOrderHistoryScore",method=RequestMethod.GET)
	@ResponseBody
	public Object getOrderHistoryScore(@RequestParam(required = true) Integer orderId) throws JsonParseException, JsonMappingException{
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put(RESULT, SUCCESS_FLAG);
		resultMap.put(CODE, SUCCESS_CODE);
		
		try{
			resultMap.put(DATA, this.scoreOrderService.orderHistoryScore(orderId));
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT, ERROR_FLAG);
			resultMap.put(CODE, "201");
		}
		
		return resultMap; 
	}
	

	@RequestMapping(value="getOrderNoScoreType",method=RequestMethod.GET)
	@ResponseBody
	public Object getOrderNoScoreType(@RequestParam(required = true) Integer orderId) throws JsonParseException, JsonMappingException{
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put(RESULT, SUCCESS_FLAG);
		resultMap.put(CODE, SUCCESS_CODE);
		try{
			resultMap.put(DATA, this.scoreOrderService.orderNoScoreType(orderId));
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT, ERROR_FLAG);
			resultMap.put(CODE, "201");
		}
		
		return resultMap; 
	}
	

	@RequestMapping(value="commitOrderScore",method=RequestMethod.POST)
	@ResponseBody
	public Object commitOrderScore(
			@RequestParam(required = true) Integer orderId,
			@RequestParam(required = true) String scoreType,
			@RequestParam(required = true) Integer scoreValue,
			@RequestParam(required = false) String scoreContent,
			HttpServletRequest request
			) throws JsonParseException, JsonMappingException{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int result = this.scoreOrderService.orderScoreSave( orderId, scoreType, scoreValue, scoreContent);
		if(result > 0){
			resultMap.put("result", SUCCESS_FLAG);
			resultMap.put("code", SUCCESS_CODE);
			resultMap.put("message", "提交评分成功！");
		}else{
			resultMap.put("result", ERROR_FLAG);
			resultMap.put("code", "201");
			resultMap.put("message", "提交评分失败！");
		}
		return resultMap;
	}
	

	@RequestMapping(value="getComplainType",method=RequestMethod.GET)
	@ResponseBody
	public Object getComplainType() throws JsonParseException, JsonMappingException{
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put(RESULT, SUCCESS_FLAG);
		resultMap.put(CODE, SUCCESS_CODE);
		
		try{
			resultMap.put(DATA, this.scoreOrderService.getComplainType());
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT, ERROR_FLAG);
			resultMap.put(CODE, "201");
		}
		
		return resultMap;
	}
	

	@RequestMapping(value="commitComplain",method=RequestMethod.POST)
	@ResponseBody
	public Object commitComplain(
			@RequestParam(required = true) Integer orderId,
			@RequestParam(required = true) String complainType,
			@RequestParam(required = false) String complainContent,
			HttpServletRequest request
			) throws JsonParseException, JsonMappingException{
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put(RESULT, SUCCESS_FLAG);
		resultMap.put(CODE, SUCCESS_CODE);
		resultMap.put(MESSAGE, "提交成功！");
		
		try{
			this.scoreOrderService.commitComplain( orderId, complainType, complainContent);
		}catch(Exception e){
			e.printStackTrace( );
			resultMap.put(RESULT, ERROR_FLAG);
			resultMap.put(CODE, "201");
			resultMap.put(MESSAGE, "提交失败！");
		}
		
		return resultMap; 
	}
	
	

	@RequestMapping(value="selectAllTeamMember",method=RequestMethod.GET)
	@ResponseBody
	public Object selectAllTeamMember(@RequestParam(required = true) Integer orderId) throws JsonParseException, JsonMappingException{
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put(RESULT, SUCCESS_FLAG);
		resultMap.put(CODE, SUCCESS_CODE);
		
		try{
			resultMap.put(DATA, this.scoreOrderService.selectAllTeamMember(orderId));
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT, ERROR_FLAG);
			resultMap.put(CODE, "201");
		}
		
		return resultMap; 
	}
	

	@RequestMapping(value="commitEvaluate",method=RequestMethod.POST)
	@ResponseBody
	public Object commitEvaluate(
			@RequestParam(required = false) Integer employeeId,
			@RequestParam(required = true) Integer orderId,
			@RequestParam(required = true) Integer evaluateType,
			@RequestParam(required = false) String evaluateContent,
			@RequestParam(required = true) String employeeName,
			@RequestParam(required = true) String employeePhone,
			@RequestParam(required = true) Integer storeId,
			@RequestParam(required = true) String workType
			) throws JsonParseException, JsonMappingException{
		Map<String,Object> resultMap = new HashMap<String,Object>();
			
		if( StringUtils.isAnyBlank(employeeName,employeePhone ) ){
			resultMap.put(RESULT, ERROR_FLAG);
			resultMap.put(CODE, "203");
			resultMap.put(MESSAGE, "人员姓名或人员电话不可以为空！");
		}else{
			if( this.scoreOrderService.evaluateValidate(orderId, employeeId, employeeName, employeePhone) == 0 ){
				try{
					this.scoreOrderService.evaluateSave( orderId, evaluateType, evaluateContent, employeeId,employeeName,employeePhone,storeId,workType);
				}catch(Exception e){
					e.printStackTrace( );
					resultMap.put(RESULT, ERROR_FLAG);
					resultMap.put(CODE, "201");
					resultMap.put(MESSAGE, "评价失败！");
				}
				resultMap.put(RESULT, SUCCESS_FLAG);
				resultMap.put(CODE, SUCCESS_CODE);
				resultMap.put(MESSAGE, "评价成功！");
			}else{
				resultMap.put(RESULT, ERROR_FLAG);
				resultMap.put(CODE, "204");
				resultMap.put(MESSAGE, "已评价，请勿重复操作！");
			}
		}
		return resultMap; 
	}
}
