package cn.damei.web.mobile.Manager;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.Base64Util;
import cn.damei.common.SessionUtils;
import cn.damei.service.mobile.Manager.BusinessPicService;
import cn.damei.entity.mobile.Manager.DictType;
import cn.damei.entity.mobile.Manager.NewChecksizeOrder;
import cn.damei.entity.mobile.Manager.RecheckScaleBill;
import cn.damei.service.mobile.Manager.ChecksizeService;
import cn.damei.service.mobile.Manager.DictTypeService;
import cn.damei.service.mobile.Manager.NewChecksizeService;
import cn.damei.service.mobile.Manager.RecheckScaleBillCurtainService;
import cn.damei.service.mobile.Manager.RecheckScaleBillFlatOpenDoorService;
import cn.damei.service.mobile.Manager.RecheckScaleBillPushPullDoorService;
import cn.damei.service.mobile.Manager.RecheckScaleBillRoomCabinetService;
import cn.damei.service.mobile.Manager.RecheckScaleBillService;
import cn.damei.service.mobile.Manager.RecheckScaleBillTaokouService;
import cn.damei.service.mobile.Manager.RecheckScaleBillToiletService;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.service.modules.BizMessagegroupService;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;
import cn.damei.service.modules.BizSeiralnumService;

/**
 * 复尺主页面(20161107-20161113)
 * @author llp 
 * 2016-11-15
 */
@Controller
@RequestMapping(value="${adminPath}/app/manager")
public class RecheckScaleBillController {
	private static Logger logger = LoggerFactory.getLogger(RecheckScaleBillController.class);
	@Autowired
	private RecheckScaleBillService recheckScaleBillService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private NewChecksizeService newChecksizeService;
	@Autowired
	private BizMessagegroupService bizMessagegroupService;
	@Autowired
	private DictTypeService dictTypeService;
	@Autowired
	private BizSeiralnumService bizSeiralnumService;
	@Autowired
	private BusinessPicService businessPicService;
	@Autowired
	private RecheckScaleBillTaokouService recheckScaleBillTaokouService;
	@Autowired
	private RecheckScaleBillCurtainService recheckScaleBillCurtainService;
	@Autowired
	private RecheckScaleBillPushPullDoorService recheckScaleBillPushPullDoorService;
	@Autowired
	private RecheckScaleBillFlatOpenDoorService recheckScaleBillFlatOpenDoorService;
	@Autowired
	private RecheckScaleBillToiletService recheckScaleBillToiletService;
	@Autowired
	private RecheckScaleBillRoomCabinetService recheckScaleBillRoomCabinetService;
	@Autowired
	private ChecksizeService checksizeService;
	
	@RequestMapping(value={"checkType",""})
	public String list(HttpServletRequest request,Model model,String recheckType,String orderID) 
			throws IOException {
		String result = null;
		// 获取项目经理
		Manager manager = SessionUtils.getManagerSession(request);
		logger.info("获取当前项目经理的编号："+manager.getId()+"\t项目经理名字："+manager.getRealname()
					+"\t手机号："+manager.getPhone());
		logger.info("类型："+recheckType +"\t 订单编号："+orderID);
		model.addAttribute("orderID", orderID);
		if(recheckType.equals(ConstantUtils.COMPLEX_CONTENT_MAP_1)){//1-套口
			List<DictType> dictList = dictTypeService.queryListByType(PicRootName.RecheckType(ConstantUtils.COMPLEX_CONTENT_MAP_1));
			List<String> list = new ArrayList<String>();
			for(DictType type: dictList){
				list.add(type.getLabel());
			}
			model.addAttribute("taokouList", list);
			result = "mobile/modules/Manager/progressMain/newChecksize/taokouRecheck";
		}else if(recheckType.equals(ConstantUtils.COMPLEX_CONTENT_MAP_2)){//2-窗帘
			List<DictType> dictList = dictTypeService.queryListByType(PicRootName.RecheckType(ConstantUtils.COMPLEX_CONTENT_MAP_2));
			List<String> list = new ArrayList<String>();
			for(DictType type: dictList){
				list.add(type.getLabel());
			}
			model.addAttribute("curtainList", list);
			result = "mobile/modules/Manager/progressMain/newChecksize/curtain";
		}else if(recheckType.equals(ConstantUtils.COMPLEX_CONTENT_MAP_3)){//3-推拉门
			List<DictType> dictList = dictTypeService.queryListByType(PicRootName.RecheckType(ConstantUtils.COMPLEX_CONTENT_MAP_3));
			List<String> list = new ArrayList<String>();
			for(DictType type: dictList){
				list.add(type.getLabel());
			}
			model.addAttribute("pushPullList", list);
			result = "mobile/modules/Manager/progressMain/newChecksize/pushPullDoor";
		}else if(recheckType.equals(ConstantUtils.COMPLEX_CONTENT_MAP_4)){//4-平开门
			List<DictType> dictList = dictTypeService.queryListByType(PicRootName.RecheckType(ConstantUtils.COMPLEX_CONTENT_MAP_4));
			List<String> list = new ArrayList<String>();
			for(DictType type: dictList){
				list.add(type.getLabel());
			}
			model.addAttribute("flatopenList", list);
			result = "mobile/modules/Manager/progressMain/newChecksize/flatopenRecheck";
		}else if(recheckType.equals(ConstantUtils.COMPLEX_CONTENT_MAP_5)){//5-马桶
			List<DictType> dictList = dictTypeService.queryListByType(PicRootName.RecheckType(ConstantUtils.COMPLEX_CONTENT_MAP_5));
			List<String> list = new ArrayList<String>();
			for(DictType type: dictList){
				list.add(type.getLabel());
			}
			model.addAttribute("closeToolList", list);
			result = "mobile/modules/Manager/progressMain/newChecksize/closeTool";
		}else if(recheckType.equals(ConstantUtils.COMPLEX_CONTENT_MAP_6)){//6-浴室柜
			List<DictType> dictList = dictTypeService.queryListByType(PicRootName.RecheckType(ConstantUtils.COMPLEX_CONTENT_MAP_6));
			List<String> list = new ArrayList<String>();
			for(DictType type: dictList){
				list.add(type.getLabel());
			}
			model.addAttribute("roomCabinetList", list);
			result = "mobile/modules/Manager/progressMain/newChecksize/roomCabinet";
		}
		
		//获取订单信息
		NewChecksizeOrder order = newChecksizeService.getByID(Integer.valueOf(orderID));
		model.addAttribute("order", order);
		return result;
	}
	
	/**
	 * 套口保存
	 */
	@ResponseBody
	@RequestMapping(value={"taokouSubmitData",""})
	public String taokouSubmitData(HttpServletRequest request,Model model,String planInstallDate,String[] photos,
			String[] position,String[] holeWidth,String[] holeHigh,String[] thick,String size,String orderID) throws NumberFormatException, ParseException, IOException{
		String result = "0";
		Map<Integer,String> map = new HashMap<Integer,String>();
		RecheckScaleBill bill = null;
		logger.info("预计安装时间："+planInstallDate+"\t订单编号："+orderID);
		// 获取项目经理
		Manager manager = SessionUtils.getManagerSession(request);
		logger.info("获取当前项目经理的编号："+manager.getId()+"\t项目经理名字："+manager.getRealname()
					+"\t手机号："+manager.getPhone());
		
		if(orderID != null){
			//根据orderId和上传的类型查询最新的一条记录 判断是否大于5分钟
			Date createDate = checksizeService.findByOrderIdAndType(Integer.parseInt(orderID),ConstantUtils.COMPLEX_CONTENT_MAP_1);
			if(createDate != null){
				if(createDate.getTime()+300*1000 > new Date().getTime()){
					return "error";
				}
			}
			//获取编号规则：复尺单的编号规则：FC+四位年月日+四位流水号（FC201611090001）
			String number = bizSeiralnumService.getDateSequence(ConstantUtils.APP_RECHECK_STRING);
			int idKey = recheckScaleBillService.insert(Integer.valueOf(orderID),planInstallDate,number,ConstantUtils.COMPLEX_CONTENT_MAP_1,manager.getId());
			bill = recheckScaleBillService.getByID(idKey);
			if(idKey > 0){
				int num = 0;
				if(StringUtils.isNotBlank(size)){
					for(String packageCover : size.split(",")){
						map.put(num, packageCover);
						num ++;
					}
				}
				
				if(null != size){
					for(Entry<Integer, String> entry : map.entrySet()){
						for(int i = 0; i < size.split(",").length ;i++){
							if(entry.getKey() == i){
								if(request.getParameter("packageCover"+entry.getValue()) == null){
									logger.info("获取复尺位置："+position[i]+"\t获取单选按钮："+0);
									logger.info("洞口宽度："+holeWidth[i]+"\t洞口高度："+holeHigh[i]+"\t洞口厚度："+thick[i]);
									result = recheckScaleBillTaokouService.insertTaokou(idKey,position[i],"0",holeWidth[i],holeHigh[i],thick[i],manager.getId());
								}else{
									logger.info("获取复尺位置："+position[i]+"\t获取单选按钮："+request.getParameter("packageCover"+entry.getValue()));
									logger.info("洞口宽度："+holeWidth[i]+"\t洞口高度："+holeHigh[i]+"\t洞口厚度："+thick[i]);
									result = recheckScaleBillTaokouService.insertTaokou(idKey,position[i],request.getParameter("packageCover"+entry.getValue()),holeWidth[i],holeHigh[i],thick[i],manager.getId());
								}
							}
						}
					}
				}
				
				//公用图片保存
				if(photos != null && photos.length > 0){
					String pathName = PicRootName.NewRecheckPath();
					String rootPath = request.getSession().getServletContext().getRealPath("/");
					for (String pic : photos) {
						//生成UUID
						String uuid = UUID.randomUUID().toString().replaceAll("-", "");
						File filePath = new File(rootPath + pathName + DateUtils.getDate1());
						//判断该文件是否存在
						if(!filePath.exists()){
							filePath.mkdirs();
						}
						String picUrl = pathName + DateUtils.getDate1() + "/" + uuid + ".jpeg";
						String fullPath = filePath + filePath.separator + uuid + ".jpeg";
						logger.info("完整路径："+fullPath);
						Base64Util.generateImage(pic, fullPath.toString());//base64解析成图片
						
						result = businessPicService.insertPhotos(pic,ConstantUtils.TAOKOU_KEY,idKey,picUrl);
					}
				}
			}else{
				result = "1";
			}
		}
		
		NewChecksizeOrder order = null;
		if(StringUtils.isNotBlank(orderID)){
			order = newChecksizeService.getByID(Integer.valueOf(orderID));
		}
		
		String html = "";
		if(null!=bill && null != bill.getType()){
			if(bill.getType().equals("1")){
				html += "套口";
			}else if(bill.getType().equals("2")){
				html += "窗帘";
			}else if(bill.getType().equals("3")){
				html += "推拉门";
			}else if(bill.getType().equals("4")){
				html += "平开门";
			}else if(bill.getType().equals("5")){
				html += "马桶";
			}else if(bill.getType().equals("6")){
				html += "浴室柜";
			}
		}
		
		List<Integer> list = new ArrayList<Integer>();
		if(order != null){
			BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(order.getStoreId().toString(), "9");
			List<BizEmployee2> employeelist = null;
			if(null != bizMessagegroup ){
				String[] str = bizMessagegroup.getEmployees().split(",");
				for(String id: str){
					list.add(Integer.valueOf(id));
				}
				employeelist = bizEmployeeService2.getById(list);
				if(list.size() > 0 && employeelist.size() > 0){
					for(BizEmployee2 employee : employeelist){
						logger.info("调度员手机号："+employee.getPhone());
						/**发送短信给材料调度员**/
						BizPhoneMsg p = new BizPhoneMsg();
						p.setId(null);
						p.setReceivePhone(employee.getPhone().trim());
						p.setReceiveEmployeeId(employee.getId());
						p.setMsgContent("订单（"+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()
								+"-"+order.getCustomerPhone()+"），项目经理（"+order.getManagerRealName()+"-"+order.getManagerPhone()+"），项目经理已复尺（"+html+"），请及时登录系统查看详情。");
						p.setMsgGenerateDatetime(DateUtils.addDate(new Date(), 0));
						p.setMsgTosendDatetime(null);
						p.setMsgSendedDatetime(null);
						p.setMsgStatus("0");
						p.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_300101);
						p.setRelatedBusinessIdInt(Integer.valueOf(order.getId()));
						p.setRelatedBusinessIdVarchar("");
						bizPhoneMsgService.save(p);
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 窗帘保存
	 */
	@ResponseBody
	@RequestMapping(value={"curainSubmitData",""})
	public String curainSubmitData(HttpServletRequest request,String planInstallDate,String[] photos,String[] position,String[] poleLength,
			String[] clothHeight,String size,String orderID) throws NumberFormatException,ParseException, IOException{
		String result = "0";
		Map<Integer,String> map = new HashMap<Integer,String>();
		RecheckScaleBill bill = null;
		logger.info("预计安装时间："+planInstallDate+"\t订单编号："+orderID);
		// 获取项目经理
		Manager manager = SessionUtils.getManagerSession(request);
		if(orderID != null){
			
			//根据orderId和上传的类型查询最新的一条记录 判断是否大于5分钟
			Date createDate = checksizeService.findByOrderIdAndType(Integer.parseInt(orderID),ConstantUtils.COMPLEX_CONTENT_MAP_2);
			if(createDate != null){
				if(createDate.getTime()+300*1000 > new Date().getTime()){
					return "error";
				}
			}
			//获取编号规则：复尺单的编号规则：FC+四位年月日+四位流水号（FC201611090001）
			String number = bizSeiralnumService.getDateSequence(ConstantUtils.APP_RECHECK_STRING);
			int idKey = recheckScaleBillService.insert(Integer.valueOf(orderID),planInstallDate,number,ConstantUtils.COMPLEX_CONTENT_MAP_2,manager.getId());
			bill = recheckScaleBillService.getByID(idKey);
			if(idKey > 0){
				int num = 0;
				if(StringUtils.isNotBlank(size)){
					for(String packageCover : size.split(",")){
						map.put(num, packageCover);
						num ++;
					}
				}
				
				if(null != size){
					for(Entry<Integer, String> entry : map.entrySet()){
						for(int i = 0; i < size.split(",").length ;i++){
							if(entry.getKey() == i){
								if(request.getParameter("poleType"+entry.getValue()) == null){
									logger.info("获取复尺位置："+position[i]+"\t获取单选按钮："+0);
									logger.info("窗帘杆长："+poleLength[i]+"\t帘布高度："+clothHeight[i]);
									result = recheckScaleBillCurtainService.insertCurtain(idKey,position[i],"0",poleLength[i],clothHeight[i],manager.getId());
								}else{
									logger.info("获取复尺位置："+position[i]+"\t获取单选按钮："+request.getParameter("poleType"+entry.getValue()));
									logger.info("窗帘杆长："+poleLength[i]+"\t帘布高度："+clothHeight[i]);
									result = recheckScaleBillCurtainService.insertCurtain(idKey,position[i],request.getParameter("poleType"+entry.getValue()),poleLength[i],clothHeight[i],manager.getId());
								}
							}
						}
					}
				}
				
				//公用图片保存
				if(photos != null && photos.length > 0){
					String rootPath = request.getSession().getServletContext().getRealPath("/");
					String pathName = PicRootName.NewRecheckPath();
					for (String pic : photos) {
						//生成UUID
						String uuid = UUID.randomUUID().toString().replaceAll("-", "");
						File filePath = new File(rootPath + pathName + DateUtils.getDate1());
						//判断该文件是否存在
						if(!filePath.exists()){
							filePath.mkdirs();
						}
						String picUrl = pathName + DateUtils.getDate1() + "/" + uuid + ".jpeg";
						String fullPath = filePath + filePath.separator + uuid + ".jpeg";
						logger.info("完整路径："+fullPath);
						Base64Util.generateImage(pic, fullPath.toString());//base64解析成图片
						
						result = businessPicService.insertPhotos(pic,ConstantUtils.CURTAIN_KEY,idKey,picUrl);
					}
				}
			}else{
				result = "1";
			}
		}
		NewChecksizeOrder order = null;
		if(StringUtils.isNotBlank(orderID)){
			order = newChecksizeService.getByID(Integer.valueOf(orderID));
		}
		
		String html = "";
		if(null!=bill && null != bill.getType()){
			if(bill.getType().equals("1")){
				html += "套口";
			}else if(bill.getType().equals("2")){
				html += "窗帘";
			}else if(bill.getType().equals("3")){
				html += "推拉门";
			}else if(bill.getType().equals("4")){
				html += "平开门";
			}else if(bill.getType().equals("5")){
				html += "马桶";
			}else if(bill.getType().equals("6")){
				html += "浴室柜";
			}
		}
		
		List<Integer> list = new ArrayList<Integer>();
		if(order != null){
			BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(order.getStoreId().toString(), "9");
			List<BizEmployee2> employeelist = null;
			if(null != bizMessagegroup ){
				String[] str = bizMessagegroup.getEmployees().split(",");
				for(String id: str){
					list.add(Integer.valueOf(id));
				}
				employeelist = bizEmployeeService2.getById(list);
				if(list.size() > 0 && employeelist.size() > 0){
					for(BizEmployee2 employee : employeelist){
						logger.info("调度员手机号："+employee.getPhone());
						/**发送短信给材料调度员**/
						BizPhoneMsg p = new BizPhoneMsg();
						p.setId(null);
						p.setReceivePhone(employee.getPhone().trim());
						p.setReceiveEmployeeId(employee.getId());
						p.setMsgContent("订单（"+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()
								+"-"+order.getCustomerPhone()+"），项目经理（"+order.getManagerRealName()+"-"+order.getManagerPhone()+"），项目经理已复尺（"+html+"），请及时登录系统查看详情。");
						p.setMsgGenerateDatetime(DateUtils.addDate(new Date(), 0));
						p.setMsgTosendDatetime(null);
						p.setMsgSendedDatetime(null);
						p.setMsgStatus("0");
						p.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_300101);
						p.setRelatedBusinessIdInt(Integer.valueOf(order.getId()));
						p.setRelatedBusinessIdVarchar("");
						bizPhoneMsgService.save(p);
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 推拉门保存
	 */
	@ResponseBody
	@RequestMapping(value={"pushPullSubmitData",""})
	public String pushPullSubmitData(HttpServletRequest request,String planInstallDate,String[] photos,String[] position,String[] pushPullStyle,
			String[] packageCover,String[] openDirection,String[] doorAmount,String[] newDoorWidth,String[] newDoorHeight,String[] width,
			String[] height,String[] thickness,String size,String orderID) throws NumberFormatException, ParseException, IOException{
		logger.info("订单编号："+orderID+"\t加一项总个数："+size+"\t预计安装日期："+planInstallDate);
		Map<Integer,String> map = new HashMap<Integer,String>();
		RecheckScaleBill bill = null;
		// 获取项目经理
		Manager manager = SessionUtils.getManagerSession(request);
		String result = "0";
		if(orderID != null){
			//根据orderId和上传的类型查询最新的一条记录 判断是否大于5分钟
			Date createDate = checksizeService.findByOrderIdAndType(Integer.parseInt(orderID),ConstantUtils.COMPLEX_CONTENT_MAP_3);
			if(createDate != null){
				if(createDate.getTime()+300*1000 > new Date().getTime()){
					return "error";
				}
			}
			//获取编号规则：复尺单的编号规则：FC+四位年月日+四位流水号（FC201611090001）
			String number = bizSeiralnumService.getDateSequence(ConstantUtils.APP_RECHECK_STRING);
			int idKey = recheckScaleBillService.insert(Integer.valueOf(orderID),planInstallDate,number,ConstantUtils.COMPLEX_CONTENT_MAP_3,manager.getId());
			bill = recheckScaleBillService.getByID(idKey);
			if(idKey > 0){
				int num = 0;
				for(String selected : size.split(",")){
					map.put(num, selected);
					num ++;
				}
				
				if(null != size){
					for(Entry<Integer, String> entry : map.entrySet()){
						for(int i = 0; i < size.split(",").length ;i++){
							if(entry.getKey() == i){
								if(request.getParameter("packageCover"+entry.getValue()) == null && request.getParameter("pushPullStyle"+entry.getValue()) == null && request.getParameter("openDirection"+entry.getValue()) == null){
									logger.info("获取复尺位置："+position[i]+"\t推拉方式、包套方式、开启方向："+0);
									logger.info("门 扇 数 量："+doorAmount[i]+"\t新加门垛宽度："+newDoorWidth[i]+"\t新加门垛高度："+newDoorHeight[i]);
									logger.info("宽度："+width[i]+"\t高度："+height[i]+"\t厚度："+thickness[i]);
									result = recheckScaleBillPushPullDoorService.insertpush(idKey,position[i],"0","0","0",doorAmount[i],newDoorWidth[i],newDoorHeight[i],width[i],height[i],thickness[i],manager.getId());
								}else if(request.getParameter("packageCover"+entry.getValue()) != null && request.getParameter("pushPullStyle"+entry.getValue()) == null && request.getParameter("openDirection"+entry.getValue()) == null){
									logger.info("获取复尺位置："+position[i]+"\t推拉方式、开启方向："+0+"\t包套方式："+request.getParameter("packageCover"+entry.getValue()));
									logger.info("门 扇 数 量："+doorAmount[i]+"\t新加门垛宽度："+newDoorWidth[i]+"\t新加门垛高度："+newDoorHeight[i]);
									logger.info("宽度："+width[i]+"\t高度："+height[i]+"\t厚度："+thickness[i]);
									result = recheckScaleBillPushPullDoorService.insertpush(idKey,position[i],"0",request.getParameter("packageCover"+entry.getValue()),"0",doorAmount[i],newDoorWidth[i],newDoorHeight[i],width[i],height[i],thickness[i],manager.getId());
								}else if(request.getParameter("packageCover"+entry.getValue()) != null && request.getParameter("pushPullStyle"+entry.getValue()) != null && request.getParameter("openDirection"+entry.getValue()) == null){
									logger.info("获取复尺位置："+position[i]+"\t开启方向："+0+"\t包套方式："+request.getParameter("packageCover"+entry.getValue())+"\t推拉方式："+request.getParameter("pushPullStyle"+entry.getValue()));
									logger.info("门 扇 数 量："+doorAmount[i]+"\t新加门垛宽度："+newDoorWidth[i]+"\t新加门垛高度："+newDoorHeight[i]);
									logger.info("宽度："+width[i]+"\t高度："+height[i]+"\t厚度："+thickness[i]);
									result = recheckScaleBillPushPullDoorService.insertpush(idKey,position[i],request.getParameter("pushPullStyle"+entry.getValue()),request.getParameter("packageCover"+entry.getValue()),"0",doorAmount[i],newDoorWidth[i],newDoorHeight[i],width[i],height[i],thickness[i],manager.getId());
								}else if(request.getParameter("packageCover"+entry.getValue()) != null && request.getParameter("pushPullStyle"+entry.getValue()) == null && request.getParameter("openDirection"+entry.getValue()) != null){
									logger.info("获取复尺位置："+position[i]+"\t推拉方式："+0+"\t包套方式："+request.getParameter("packageCover"+entry.getValue())+"\t开启方向："+request.getParameter("openDirection"+entry.getValue()));
									logger.info("门 扇 数 量："+doorAmount[i]+"\t新加门垛宽度："+newDoorWidth[i]+"\t新加门垛高度："+newDoorHeight[i]);
									logger.info("宽度："+width[i]+"\t高度："+height[i]+"\t厚度："+thickness[i]);
									result = recheckScaleBillPushPullDoorService.insertpush(idKey,position[i],"0",request.getParameter("packageCover"+entry.getValue()),request.getParameter("openDirection"+entry.getValue()),doorAmount[i],newDoorWidth[i],newDoorHeight[i],width[i],height[i],thickness[i],manager.getId());
								}else if(request.getParameter("packageCover"+entry.getValue()) == null && request.getParameter("pushPullStyle"+entry.getValue()) != null && request.getParameter("openDirection"+entry.getValue()) != null){
									logger.info("获取复尺位置："+position[i]+"\t包套方式："+0+"\t推拉方式："+request.getParameter("pushPullStyle"+entry.getValue())+"\t开启方向："+request.getParameter("openDirection"+entry.getValue()));
									logger.info("门 扇 数 量："+doorAmount[i]+"\t新加门垛宽度："+newDoorWidth[i]+"\t新加门垛高度："+newDoorHeight[i]);
									logger.info("宽度："+width[i]+"\t高度："+height[i]+"\t厚度："+thickness[i]);
									result = recheckScaleBillPushPullDoorService.insertpush(idKey,position[i],"0",request.getParameter("pushPullStyle"+entry.getValue()),request.getParameter("openDirection"+entry.getValue()),doorAmount[i],newDoorWidth[i],newDoorHeight[i],width[i],height[i],thickness[i],manager.getId());
								}else if(request.getParameter("packageCover"+entry.getValue()) == null && request.getParameter("pushPullStyle"+entry.getValue()) == null && request.getParameter("openDirection"+entry.getValue()) != null){
									logger.info("获取复尺位置："+position[i]+"\t包套方式、推拉方式："+0+"\t开启方向："+request.getParameter("openDirection"+entry.getValue()));
									logger.info("门 扇 数 量："+doorAmount[i]+"\t新加门垛宽度："+newDoorWidth[i]+"\t新加门垛高度："+newDoorHeight[i]);
									logger.info("宽度："+width[i]+"\t高度："+height[i]+"\t厚度："+thickness[i]);
									result = recheckScaleBillPushPullDoorService.insertpush(idKey,position[i],"0","0",request.getParameter("openDirection"+entry.getValue()),doorAmount[i],newDoorWidth[i],newDoorHeight[i],width[i],height[i],thickness[i],manager.getId());
								}else if(request.getParameter("packageCover"+entry.getValue()) == null && request.getParameter("pushPullStyle"+entry.getValue()) != null && request.getParameter("openDirection"+entry.getValue()) == null){
									logger.info("获取复尺位置："+position[i]+"\t包套方式、开启方向："+0+"\t推拉方式："+request.getParameter("openDirection"+entry.getValue()));
									logger.info("门 扇 数 量："+doorAmount[i]+"\t新加门垛宽度："+newDoorWidth[i]+"\t新加门垛高度："+newDoorHeight[i]);
									logger.info("宽度："+width[i]+"\t高度："+height[i]+"\t厚度："+thickness[i]);
									result = recheckScaleBillPushPullDoorService.insertpush(idKey,position[i],"0",request.getParameter("pushPullStyle"+entry.getValue()),"0",doorAmount[i],newDoorWidth[i],newDoorHeight[i],width[i],height[i],thickness[i],manager.getId());
								}else{
									logger.info("获取复尺位置："+position[i]+"\t推拉方式："+request.getParameter("pushPullStyle"+entry.getValue())+"\t包套方式："+request.getParameter("packageCover"+entry.getValue())+"\t开启方向："+request.getParameter("openDirection"+entry.getValue()));
									logger.info("门 扇 数 量："+doorAmount[i]+"\t新加门垛宽度："+newDoorWidth[i]+"\t新加门垛高度："+newDoorHeight[i]);
									logger.info("宽度："+width[i]+"\t高度："+height[i]+"\t厚度："+thickness[i]);
									result = recheckScaleBillPushPullDoorService.insertpush(idKey,position[i],request.getParameter("pushPullStyle"+entry.getValue()),request.getParameter("packageCover"+entry.getValue()),request.getParameter("openDirection"+entry.getValue()),doorAmount[i],newDoorWidth[i],newDoorHeight[i],width[i],height[i],thickness[i],manager.getId());
								}
							}
						}
					}
				}
				
				//公用图片保存
				if(photos != null && photos.length > 0){
					String pathName = PicRootName.NewRecheckPath();
					String rootPath = request.getSession().getServletContext().getRealPath("/");
					for (String pic : photos) {
						//生成UUID
						String uuid = UUID.randomUUID().toString().replaceAll("-", "");
						File filePath = new File(rootPath + pathName + DateUtils.getDate1());
						//判断该文件是否存在
						if(!filePath.exists()){
							filePath.mkdirs();
						}
						String picUrl = pathName + DateUtils.getDate1() + "/" + uuid + ".jpeg";
						String fullPath = filePath + filePath.separator + uuid + ".jpeg";
						logger.info("完整路径："+fullPath);
						Base64Util.generateImage(pic, fullPath.toString());//base64解析成图片
						
						result = businessPicService.insertPhotos(pic,ConstantUtils.PUSH_PULL_DOOR_KEY,idKey,picUrl);
					}
				}
			}else{
				result = "1";
			}
		}
		
		NewChecksizeOrder order = newChecksizeService.getByID(Integer.valueOf(orderID));
		String html = "";
		if(bill.getType() != null){
			if(bill.getType().equals("1")){
				html += "套口";
			}else if(bill.getType().equals("2")){
				html += "窗帘";
			}else if(bill.getType().equals("3")){
				html += "推拉门";
			}else if(bill.getType().equals("4")){
				html += "平开门";
			}else if(bill.getType().equals("5")){
				html += "马桶";
			}else if(bill.getType().equals("6")){
				html += "浴室柜";
			}
		}
		
		List<Integer> list = new ArrayList<Integer>();
		if(order != null){
			BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(order.getStoreId().toString(), "9");
			List<BizEmployee2> employeelist = null;
			if(null != bizMessagegroup ){
				String[] str = bizMessagegroup.getEmployees().split(",");
				for(String id: str){
					list.add(Integer.valueOf(id));
				}
				employeelist = bizEmployeeService2.getById(list);
				if(list.size() > 0 && employeelist.size() > 0){
					for(BizEmployee2 employee : employeelist){
						logger.info("调度员手机号："+employee.getPhone());
						/**发送短信给材料调度员**/
						BizPhoneMsg p = new BizPhoneMsg();
						p.setId(null);
						p.setReceivePhone(employee.getPhone().trim());
						p.setReceiveEmployeeId(employee.getId());
						p.setMsgContent("订单（"+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()
								+"-"+order.getCustomerPhone()+"），项目经理（"+order.getManagerRealName()+"-"+order.getManagerPhone()+"），项目经理已复尺（"+html+"），请及时登录系统查看详情。");
						p.setMsgGenerateDatetime(DateUtils.addDate(new Date(), 0));
						p.setMsgTosendDatetime(null);
						p.setMsgSendedDatetime(null);
						p.setMsgStatus("0");
						p.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_300101);
						p.setRelatedBusinessIdInt(Integer.valueOf(order.getId()));
						p.setRelatedBusinessIdVarchar("");
						bizPhoneMsgService.save(p);
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 平开门保存
	 */
	@ResponseBody
	@RequestMapping(value={"flatSubmitData",""})
	public String flatSubmitData(HttpServletRequest request,String planInstallDate,String[] photos,String[] position,String[] inOutOpen,
			String[] packageCover,String[] openDirection,String[] width,
			String[] height,String[] thickness,String size,String orderID) throws NumberFormatException, ParseException, IOException{
		logger.info("订单编号："+orderID+"\t加一项总个数："+size+"\t预计安装日期："+planInstallDate);
		Map<Integer,String> map = new HashMap<Integer,String>();
		RecheckScaleBill bill = null;
		// 获取项目经理
		Manager manager = SessionUtils.getManagerSession(request);
		String result = "0";
		if(orderID != null){
			//根据orderId和上传的类型查询最新的一条记录 判断是否大于5分钟
			Date createDate = checksizeService.findByOrderIdAndType(Integer.parseInt(orderID),ConstantUtils.COMPLEX_CONTENT_MAP_4);
			if(createDate != null){
				if(createDate.getTime()+300*1000 > new Date().getTime()){
					return "error";
				}
			}
			//获取编号规则：复尺单的编号规则：FC+四位年月日+四位流水号（FC201611090001）
			String number = bizSeiralnumService.getDateSequence(ConstantUtils.APP_RECHECK_STRING);
			int idKey = recheckScaleBillService.insert(Integer.valueOf(orderID),planInstallDate,number,ConstantUtils.COMPLEX_CONTENT_MAP_4,manager.getId());
			bill = recheckScaleBillService.getByID(idKey);
			if(idKey > 0){
				int num = 0;
				if(StringUtils.isNotBlank(size)){
					
					for(String selected : size.split(",")){
						map.put(num, selected);
						num ++;
					}
				}
				
				if(null != size){
					for(Entry<Integer, String> entry : map.entrySet()){
						for(int i = 0; i < size.split(",").length ;i++){
							if(entry.getKey() == i){
								if(request.getParameter("packageCover"+entry.getValue()) == null && request.getParameter("inOutOpen"+entry.getValue()) == null && request.getParameter("openDirection"+entry.getValue()) == null){
									logger.info("获取复尺位置："+position[i]+"\t包套方式、内外开向、锁开方向："+0);
									logger.info("宽度："+width[i]+"\t高度："+height[i]+"\t厚度："+thickness[i]);
									result = recheckScaleBillFlatOpenDoorService.insertDoor(idKey,position[i],"0","0","0",width[i],height[i],thickness[i],manager.getId());
								}else if(request.getParameter("packageCover"+entry.getValue()) != null && request.getParameter("inOutOpen"+entry.getValue()) == null && request.getParameter("openDirection"+entry.getValue()) == null){
									logger.info("获取复尺位置："+position[i]+"\t内外开向、锁开方向："+0+"\t包套方式："+request.getParameter("packageCover"+i));
									logger.info("宽度："+width[i]+"\t高度："+height[i]+"\t厚度："+thickness[i]);
									result = recheckScaleBillFlatOpenDoorService.insertDoor(idKey,position[i],request.getParameter("packageCover"+entry.getValue()),"0","0",width[i],height[i],thickness[i],manager.getId());
								}else if(request.getParameter("packageCover"+entry.getValue()) != null && request.getParameter("inOutOpen"+entry.getValue()) != null && request.getParameter("openDirection"+entry.getValue()) == null){
									logger.info("获取复尺位置："+position[i]+"\t锁开方向："+0+"\t包套方式："+request.getParameter("packageCover"+entry.getValue())+"\t内外开向："+request.getParameter("inOutOpen"+entry.getValue()));
									logger.info("宽度："+width[i]+"\t高度："+height[i]+"\t厚度："+thickness[i]);
									result = recheckScaleBillFlatOpenDoorService.insertDoor(idKey,position[i],request.getParameter("packageCover"+entry.getValue()),request.getParameter("inOutOpen"+entry.getValue()),"0",width[i],height[i],thickness[i],manager.getId());
								}else if(request.getParameter("packageCover"+entry.getValue()) != null && request.getParameter("inOutOpen"+entry.getValue()) == null && request.getParameter("openDirection"+entry.getValue()) != null){
									logger.info("获取复尺位置："+position[i]+"\t内外开向："+0+"\t包套方式："+request.getParameter("packageCover"+entry.getValue())+"\t锁开方向："+request.getParameter("openDirection"+entry.getValue()));
									logger.info("宽度："+width[i]+"\t高度："+height[i]+"\t厚度："+thickness[i]);
									result = recheckScaleBillFlatOpenDoorService.insertDoor(idKey,position[i],request.getParameter("packageCover"+entry.getValue()),"0",request.getParameter("openDirection"+entry.getValue()),width[i],height[i],thickness[i],manager.getId());
								}else if(request.getParameter("packageCover"+entry.getValue()) == null && request.getParameter("inOutOpen"+entry.getValue()) != null && request.getParameter("openDirection"+entry.getValue()) != null){
									logger.info("获取复尺位置："+position[i]+"\t包套方式："+0+"\t内外开向："+request.getParameter("inOutOpen"+entry.getValue())+"\t锁开方向："+request.getParameter("openDirection"+entry.getValue()));
									logger.info("宽度："+width[i]+"\t高度："+height[i]+"\t厚度："+thickness[i]);
									result = recheckScaleBillFlatOpenDoorService.insertDoor(idKey,position[i],"0",request.getParameter("inOutOpen"+entry.getValue()),request.getParameter("openDirection"+entry.getValue()),width[i],height[i],thickness[i],manager.getId());
								}else if(request.getParameter("packageCover"+entry.getValue()) == null && request.getParameter("inOutOpen"+entry.getValue()) == null && request.getParameter("openDirection"+entry.getValue()) != null){
									logger.info("获取复尺位置："+position[i]+"\t包套方式、内外开向："+0+"\t锁开方向："+request.getParameter("openDirection"+entry.getValue()));
									logger.info("宽度："+width[i]+"\t高度："+height[i]+"\t厚度："+thickness[i]);
									result = recheckScaleBillFlatOpenDoorService.insertDoor(idKey,position[i],"0","0",request.getParameter("openDirection"+entry.getValue()),width[i],height[i],thickness[i],manager.getId());
								}else if(request.getParameter("packageCover"+entry.getValue()) == null && request.getParameter("inOutOpen"+entry.getValue()) != null && request.getParameter("openDirection"+entry.getValue()) == null){
									logger.info("获取复尺位置："+position[i]+"\t包套方式、锁开方向："+0+"\t内外开向："+request.getParameter("inOutOpen"+i));
									logger.info("宽度："+width[i]+"\t高度："+height[i]+"\t厚度："+thickness[i]);
									result = recheckScaleBillFlatOpenDoorService.insertDoor(idKey,position[i],"0",request.getParameter("inOutOpen"+entry.getValue()),"0",width[i],height[i],thickness[i],manager.getId());
								}else{
									logger.info("获取复尺位置："+position[i]+"\t内外开向："+request.getParameter("inOutOpen"+entry.getValue())+"\t包套方式："+request.getParameter("packageCover"+entry.getValue())+"\t锁开方向："+request.getParameter("openDirection"+entry.getValue()));
									logger.info("宽度："+width[i]+"\t高度："+height[i]+"\t厚度："+thickness[i]);
									result = recheckScaleBillFlatOpenDoorService.insertDoor(idKey,position[i],request.getParameter("packageCover"+entry.getValue()),request.getParameter("inOutOpen"+entry.getValue()),request.getParameter("openDirection"+entry.getValue()),width[i],height[i],thickness[i],manager.getId());
								}
							}
						}
					}
				}
				//公用图片保存
				if(photos != null && photos.length > 0){
					String pathName = PicRootName.NewRecheckPath();
					String rootPath = request.getSession().getServletContext().getRealPath("/");
					for (String pic : photos) {
						//生成UUID
						String uuid = UUID.randomUUID().toString().replaceAll("-", "");
						File filePath = new File(rootPath + pathName + DateUtils.getDate1());
						//判断该文件是否存在
						if(!filePath.exists()){
							filePath.mkdirs();
						}
						String picUrl = pathName + DateUtils.getDate1() + "/" + uuid + ".jpeg";
						String fullPath = filePath + filePath.separator + uuid + ".jpeg";
						logger.info("完整路径："+fullPath);
						Base64Util.generateImage(pic, fullPath.toString());//base64解析成图片
						
						result = businessPicService.insertPhotos(pic,ConstantUtils.FLAT_OPEN_DOOR_KEY,idKey,picUrl);
					}
				}
			}else{
				result = "1";
			}
		}
		
		NewChecksizeOrder order = null;
		if(StringUtils.isNotBlank(orderID)){
			order = newChecksizeService.getByID(Integer.valueOf(orderID));
		}
		
		String html = "";
		if(null!=bill && null != bill.getType()){
			if(bill.getType().equals("1")){
				html += "套口";
			}else if(bill.getType().equals("2")){
				html += "窗帘";
			}else if(bill.getType().equals("3")){
				html += "推拉门";
			}else if(bill.getType().equals("4")){
				html += "平开门";
			}else if(bill.getType().equals("5")){
				html += "马桶";
			}else if(bill.getType().equals("6")){
				html += "浴室柜";
			}
		}
		
		List<Integer> list = new ArrayList<Integer>();
		if(order != null){
			BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(order.getStoreId().toString(), "9");
			List<BizEmployee2> employeelist = null;
			if(null != bizMessagegroup ){
				String[] str = bizMessagegroup.getEmployees().split(",");
				for(String id: str){
					list.add(Integer.valueOf(id));
				}
				employeelist = bizEmployeeService2.getById(list);
				if(list.size() > 0 && employeelist.size() > 0){
					for(BizEmployee2 employee : employeelist){
						logger.info("调度员手机号："+employee.getPhone());
						/**发送短信给材料调度员**/
						BizPhoneMsg p = new BizPhoneMsg();
						p.setId(null);
						p.setReceivePhone(employee.getPhone().trim());
						p.setReceiveEmployeeId(employee.getId());
						p.setMsgContent("订单（"+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()
								+"-"+order.getCustomerPhone()+"），项目经理（"+order.getManagerRealName()+"-"+order.getManagerPhone()+"），项目经理已复尺（"+html+"），请及时登录系统查看详情。");
						p.setMsgGenerateDatetime(DateUtils.addDate(new Date(), 0));
						p.setMsgTosendDatetime(null);
						p.setMsgSendedDatetime(null);
						p.setMsgStatus("0");
						p.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_300101);
						p.setRelatedBusinessIdInt(Integer.valueOf(order.getId()));
						p.setRelatedBusinessIdVarchar("");
						bizPhoneMsgService.save(p);
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 马桶保存
	 */
	@ResponseBody
	@RequestMapping(value={"toiletSubmitData",""})
	public String toiletSubmitData(HttpServletRequest request,Model model,String planInstallDate,String[] photos,
			String[] position,String[] closestoolHoleSize,String size,String orderID) throws NumberFormatException, ParseException, IOException{
		String result = "0";
		Map<Integer,String> map = new HashMap<Integer,String>();
		RecheckScaleBill bill = null;
		logger.info("预计安装时间："+planInstallDate+"\t订单编号："+orderID);
		// 获取项目经理
		Manager manager = SessionUtils.getManagerSession(request);
		if(orderID != null){
			//根据orderId和上传的类型查询最新的一条记录 判断是否大于5分钟
			Date createDate = checksizeService.findByOrderIdAndType(Integer.parseInt(orderID),ConstantUtils.COMPLEX_CONTENT_MAP_5);
			if(createDate != null){
				if(createDate.getTime()+300*1000 > new Date().getTime()){
					return "error";
				}
			}
			//获取编号规则：复尺单的编号规则：FC+四位年月日+四位流水号（FC201611090001）
			String number = bizSeiralnumService.getDateSequence(ConstantUtils.APP_RECHECK_STRING);
			int idKey = recheckScaleBillService.insert(Integer.valueOf(orderID),planInstallDate,number,ConstantUtils.COMPLEX_CONTENT_MAP_5,manager.getId());
			bill = recheckScaleBillService.getByID(idKey);
			if(idKey > 0){
				int num = 0;
				if(StringUtils.isNotBlank(size)){
					
					for(String selected : size.split(",")){
						map.put(num, selected);
						num ++;
					}
				}
				
				if(null != size){
					//for(Entry<Integer, String> entry : map.entrySet()){
						for(int i = 0; i < size.split(",").length ;i++){
							//if(entry.getKey() == i){
								logger.info("获取复尺位置："+position[i]+"\t马桶孔距："+closestoolHoleSize[i]);
								result = recheckScaleBillToiletService.insertToilet(idKey,position[i],closestoolHoleSize[i],manager.getId());
							//}
						}
					//}
				}
				//公用图片保存
				if(photos != null && photos.length > 0){
					String pathName = PicRootName.NewRecheckPath();
					String rootPath = request.getSession().getServletContext().getRealPath("/");
					for (String pic : photos) {
						//生成UUID
						String uuid = UUID.randomUUID().toString().replaceAll("-", "");
						File filePath = new File(rootPath + pathName + DateUtils.getDate1());
						//判断该文件是否存在
						if(!filePath.exists()){
							filePath.mkdirs();
						}
						String picUrl = pathName + DateUtils.getDate1() + "/" + uuid + ".jpeg";
						String fullPath = filePath + filePath.separator + uuid + ".jpeg";
						logger.info("完整路径："+fullPath);
						Base64Util.generateImage(pic, fullPath.toString());//base64解析成图片
						
						result = businessPicService.insertPhotos(pic,ConstantUtils.CLOSE_TOOL_KEY,idKey,picUrl);
					}
				}
			}else{
				result = "1";
			}
		}
		
		NewChecksizeOrder order = null;
		if(StringUtils.isNotBlank(orderID)){
			order = newChecksizeService.getByID(Integer.valueOf(orderID));
		}
		String html = "";
		if(null!=bill && null != bill.getType()){
			if(bill.getType().equals("1")){
				html += "套口";
			}else if(bill.getType().equals("2")){
				html += "窗帘";
			}else if(bill.getType().equals("3")){
				html += "推拉门";
			}else if(bill.getType().equals("4")){
				html += "平开门";
			}else if(bill.getType().equals("5")){
				html += "马桶";
			}else if(bill.getType().equals("6")){
				html += "浴室柜";
			}
		}
		
		List<Integer> list = new ArrayList<Integer>();
		if(order != null){
			BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(order.getStoreId().toString(), "9");
			List<BizEmployee2> employeelist = null;
			if(null != bizMessagegroup ){
				String[] str = bizMessagegroup.getEmployees().split(",");
				for(String id: str){
					list.add(Integer.valueOf(id));
				}
				employeelist = bizEmployeeService2.getById(list);
				if(list.size() > 0 && employeelist.size() > 0){
					for(BizEmployee2 employee : employeelist){
						logger.info("调度员手机号："+employee.getPhone());
						/**发送短信给材料调度员**/
						BizPhoneMsg p = new BizPhoneMsg();
						p.setId(null);
						p.setReceivePhone(employee.getPhone().trim());
						p.setReceiveEmployeeId(employee.getId());
						p.setMsgContent("订单（"+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()
								+"-"+order.getCustomerPhone()+"），项目经理（"+order.getManagerRealName()+"-"+order.getManagerPhone()+"），项目经理已复尺（"+html+"），请及时登录系统查看详情。");
						p.setMsgGenerateDatetime(DateUtils.addDate(new Date(), 0));
						p.setMsgTosendDatetime(null);
						p.setMsgSendedDatetime(null);
						p.setMsgStatus("0");
						p.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_300101);
						p.setRelatedBusinessIdInt(Integer.valueOf(order.getId()));
						p.setRelatedBusinessIdVarchar("");
						bizPhoneMsgService.save(p);
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 浴室柜保存
	 */
	@ResponseBody
	@RequestMapping(value={"roomSubmitData",""})
	public String roomSubmitData(HttpServletRequest request,Model model,String planInstallDate,String[] photos,
			String[] position,String[] bathroomCabinetModel,String size,String orderID) throws NumberFormatException, ParseException, IOException{
		String result = "0";
		Map<Integer,String> map = new HashMap<Integer,String>();
		RecheckScaleBill bill = null;
		logger.info("预计安装时间："+planInstallDate+"\t订单编号："+orderID);
		// 获取项目经理
		Manager manager = SessionUtils.getManagerSession(request);
		if(orderID != null){
			//根据orderId和上传的类型查询最新的一条记录 判断是否大于5分钟
			Date createDate = checksizeService.findByOrderIdAndType(Integer.parseInt(orderID),ConstantUtils.COMPLEX_CONTENT_MAP_6);
			if(createDate != null){
				if(createDate.getTime()+300*1000 > new Date().getTime()){
					return "error";
				}
			}
			//获取编号规则：复尺单的编号规则：FC+四位年月日+四位流水号（FC201611090001）
			String number = bizSeiralnumService.getDateSequence(ConstantUtils.APP_RECHECK_STRING);
			int idKey = recheckScaleBillService.insert(Integer.valueOf(orderID),planInstallDate,number,ConstantUtils.COMPLEX_CONTENT_MAP_6,manager.getId());
			bill = recheckScaleBillService.getByID(idKey);
			if(idKey > 0){
				int num = 0;
				if(StringUtils.isNotBlank(size)){
					
					for(String selected : size.split(",")){
						map.put(num, selected);
						num ++;
					}
				}
				
				if(null != size){
					logger.info("加一项的个数："+size);
					//for(Entry<Integer, String> entry : map.entrySet()){//有单选按钮需要这行
					for(int i = 0; i < size.split(",").length ;i++){
						//if(entry.getKey() == i){
							logger.info("获取复尺位置："+position[i]+"\t浴室柜型号："+bathroomCabinetModel[i]);
							result = recheckScaleBillRoomCabinetService.insertToilet(idKey,position[i],bathroomCabinetModel[i],manager.getId());
						//}
					}
					//}
				}
				//公用图片保存
				if(photos != null && photos.length > 0){
					String pathName = PicRootName.NewRecheckPath();
					String rootPath = request.getSession().getServletContext().getRealPath("/");
					for (String pic : photos) {
						//生成UUID
						String uuid = UUID.randomUUID().toString().replaceAll("-", "");
						File filePath = new File(rootPath + pathName + DateUtils.getDate1());
						//判断该文件是否存在
						if(!filePath.exists()){
							filePath.mkdirs();
						}
						String picUrl = pathName + DateUtils.getDate1() + "/" + uuid + ".jpeg";
						String fullPath = filePath + filePath.separator + uuid + ".jpeg";
						logger.info("完整路径："+fullPath);
						Base64Util.generateImage(pic, fullPath.toString());//base64解析成图片
						
						result = businessPicService.insertPhotos(pic,ConstantUtils.ROOM_CABINET_KEY,idKey,picUrl);
					}
				}
			}else{
				result = "1";
			}
		}
		
		NewChecksizeOrder order = null;
		if(StringUtils.isNotBlank(orderID)){
			order = newChecksizeService.getByID(Integer.valueOf(orderID));
		}
		String html = "";
		if(null!=bill && null != bill.getType()){
			if(bill.getType().equals("1")){
				html += "套口";
			}else if(bill.getType().equals("2")){
				html += "窗帘";
			}else if(bill.getType().equals("3")){
				html += "推拉门";
			}else if(bill.getType().equals("4")){
				html += "平开门";
			}else if(bill.getType().equals("5")){
				html += "马桶";
			}else if(bill.getType().equals("6")){
				html += "浴室柜";
			}
		}
		
		List<Integer> list = new ArrayList<Integer>();
		if(order != null){
			BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(order.getStoreId().toString(), "9");
			List<BizEmployee2> employeelist = null;
			if(null != bizMessagegroup ){
				String[] str = bizMessagegroup.getEmployees().split(",");
				for(String id: str){
					list.add(Integer.valueOf(id));
				}
				employeelist = bizEmployeeService2.getById(list);
				if(list.size() > 0 && employeelist.size() > 0){
					for(BizEmployee2 employee : employeelist){
						logger.info("调度员手机号："+employee.getPhone());
						/**发送短信给材料调度员**/
						BizPhoneMsg p = new BizPhoneMsg();
						p.setId(null);
						p.setReceivePhone(employee.getPhone().trim());
						p.setReceiveEmployeeId(employee.getId());
						p.setMsgContent("订单（"+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()
								+"-"+order.getCustomerPhone()+"），项目经理（"+order.getManagerRealName()+"-"+order.getManagerPhone()+"），项目经理已复尺（"+html+"），请及时登录系统查看详情。");
						p.setMsgGenerateDatetime(DateUtils.addDate(new Date(), 0));
						p.setMsgTosendDatetime(null);
						p.setMsgSendedDatetime(null);
						p.setMsgStatus("0");
						p.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_300101);
						p.setRelatedBusinessIdInt(Integer.valueOf(order.getId()));
						p.setRelatedBusinessIdVarchar("");
						bizPhoneMsgService.save(p);
					}
				}
			}
		}
		
		return result;
	}
	
	/****
	 * 复尺记录
	 ****/
	@RequestMapping(value={"recheckRecord",""})
	public String recheckRecord(RecheckScaleBill recheckScaleBill,HttpServletRequest request,Model model,String orderID){
		logger.info("获取订单编号："+orderID);
		
		//根据订单编号查询该订单复尺的所有内容
		List<RecheckScaleBill> scaleBillList = recheckScaleBillService.queryListByOrderID(
				Integer.valueOf(orderID));
		
		model.addAttribute("orderID", orderID);
		model.addAttribute("scaleBillList", scaleBillList);
		return "mobile/modules/Manager/progressMain/newChecksize/recheckRecordList";
	}
	
}
