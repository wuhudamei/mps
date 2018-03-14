package cn.damei.web.mobile.Manager;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.AppOrder;
import cn.damei.entity.mobile.Manager.OrderSignVo;
import cn.damei.entity.mobile.Manager.SignDetail;
import cn.damei.service.mobile.Manager.SignService;
import cn.damei.entity.modules.BizPmAttendCnfg;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 梅浩 meihao@zzhyun.cn:
 * @version 创建时间：2016年9月22日 下午7:38:30 类说明
 */
@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class SignController {

	@Autowired
	private SignService signService;
	public static void main(String[] args) {
		Date date = new Date();
		SimpleDateFormat sdfyyyyMM = new SimpleDateFormat("yyyyMM");
		System.out.println(sdfyyyyMM.format(date));
		System.out.println();
	}
	/**
	 * 签到首页 ,查询项目经理下的订单
	 *
	 * @param request
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = { "signIndex", "" })
	public String signIndex(HttpServletRequest request, Model model)
			throws ParseException {
		/* 原先签到代码开始 */
		/*
		 * // 1:取出登录经理信息 Manager manager =
		 * SessionUtils.getManagerSession(request); // 2:得到订单集合
		 * List<OrderSignVo> list =
		 * signService.orderByManagerId(manager.getId());
		 *
		 * // 3:是否已经签到 if(null!=list && list.size()>0){
		 *
		 * for (OrderSignVo orderSignVo : list) { // 4:根据订单得到最近时间的一个签到信息
		 * //SignDetail detail =
		 * signService.getSignDetailByOrderId(orderSignVo.getId());
		 *
		 * // 5-->8如果签到 if (orderSignVo.getCount()>0) { String now = new
		 * SimpleDateFormat("yyyy-MM-dd").format(new Date());
		 *
		 * String signDate =new
		 * SimpleDateFormat("yyyy-MM-dd").format(orderSignVo.getSignDate());
		 *
		 * // 6:,并且签到时间大于一天, 允许再次签到 if (!signDate.equals(now)) {
		 * orderSignVo.setSignFlag("timeAllowed"); } else { // 一天内 不允许再次签到
		 * orderSignVo.setSignFlag("timeForbidden"); }
		 *
		 * } else { // 5-->8:如果没有签到信息,允许签到
		 * orderSignVo.setSignFlag("timeAllowed"); } } }
		 *
		 * model.addAttribute("signList", list);
		 */

		/* 原先签到代码结束 */
		// 1:取出登录经理信息
		Manager manager = SessionUtils.getManagerSession(request);
		// 2:得到订单集合
		List<OrderSignVo> list = signService.orderByManagerId(manager.getId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfyyyyMM = new SimpleDateFormat("yyyyMM");
		int currentMonth = Integer.valueOf(sdfyyyyMM.format(new Date()));// 得到当前月
		int jzMonth = 0; // 基装验收月份
		int jgMonth = 0; // 竣工验收月份
		int currentMonthDays = getDaysOfMonth(new Date()); // 当前月的总天数
		for (OrderSignVo osv : list) {
			String jzCheckTimeStr = signService.getCheckTimeByOrderIdAndNode(
					osv.getId(), "6"); // 基装验收时间
			String jgCheckTimeStr = signService.getCheckTimeByOrderIdAndNode(
					osv.getId(), "9"); // 竣工验收时间
			Date ActualStartDate = osv.getActualStartDate(); // 实际开工时间
			int startWorkMonth = Integer.valueOf(sdfyyyyMM.format(ActualStartDate));// 开工月份
			int startWorkDate = ActualStartDate.getDate(); // 实际开工日期 --几号
			int jzShouldSignDays = 0; // 基装应签到天数
			int jgShouldSignDays = 0; // 竣工应签到天数
			int jzShouldSignTimes = 0; // 基装应签到次数
			int jgShouldSignTimes = 0; // 竣工应签到次数
			// 如果是以前月开工
			if (startWorkMonth < currentMonth) {
				// 如果上月开工且基装验收日期为空
				if (null == jzCheckTimeStr||(jzCheckTimeStr!=null&&jzCheckTimeStr.equals(""))) {
					//如果竣工验收日期也为空，则全月按基装算
					if(jgCheckTimeStr==null||(jgCheckTimeStr!=null&&jgCheckTimeStr.equals(""))){
						jzShouldSignDays = currentMonthDays;
					}else{ //如果基装验收日期为空，且竣工验收日期不为空，则全月按竣工算
						jgShouldSignDays = currentMonthDays;
					}
				} else {
					jzMonth = Integer.valueOf(sdfyyyyMM.format(sdf.parse(jzCheckTimeStr)));
					//jzMonth = sdf.parse(jzCheckTimeStr).getMonth() + 1;
					// 如果基装验收日期在后边月，则当前月的所有天数都为基装天数
					if (jzMonth > currentMonth) {
						jzShouldSignDays = currentMonthDays;
					} else if (jzMonth == currentMonth) {
						// 如果基装验收日期在本月，则从1号到基装验收日期为基装应签到天数；
						// 再判断竣工日期是在本月还是在下月，若在下月，则竣工应签到天数为，基装日期到月底;
						// 若在本月，则竣工应签到日期为基装验收日期到竣工验收日期；
						jzShouldSignDays = sdf.parse(jzCheckTimeStr).getDate();
						if(jgCheckTimeStr==null||(jgCheckTimeStr!=null&jgCheckTimeStr.equals(""))){
							jgShouldSignDays = currentMonthDays
									- sdf.parse(jzCheckTimeStr).getDate() + 1;
						}else{
							//jgMonth = sdf.parse(jgCheckTimeStr).getMonth() + 1; // 得到竣工月份
							jgMonth = Integer.valueOf(sdfyyyyMM.format(sdf.parse(jgCheckTimeStr)));
							if (jgMonth > currentMonth) {
								jgShouldSignDays = currentMonthDays
										- sdf.parse(jzCheckTimeStr).getDate() + 1;
							} else {
								jgShouldSignDays = sdf.parse(jgCheckTimeStr)
										.getDate()
										- sdf.parse(jzCheckTimeStr).getDate() + 1;
							}
						}

					}else{
						if(jgCheckTimeStr==null||(jgCheckTimeStr!=null&&jgCheckTimeStr.equals(""))){
							jgShouldSignDays = currentMonthDays;
						}else{
							jgShouldSignDays = sdf.parse(jgCheckTimeStr)
									.getDate();
						}
					}
				}
			} else if (startWorkMonth == currentMonth) { // 如果是当前月开工
				// 如果基装验收日期为空，则从开工日期到月底都是基装天数
				if (null == jzCheckTimeStr||(jzCheckTimeStr!=null&&jzCheckTimeStr.equals(""))) {
					//如果竣工验收日期也为空，则全月按基装算
					if(jgCheckTimeStr==null||(jgCheckTimeStr!=null&&jgCheckTimeStr.equals(""))){
						jzShouldSignDays = currentMonthDays- startWorkDate + 1;
					}else{ //如果基装验收日期为空，且竣工验收日期不为空，则全月按竣工算
						jgShouldSignDays = currentMonthDays- startWorkDate + 1;
					}
				} else {
					//jzMonth = sdf.parse(jzCheckTimeStr).getMonth() + 1;
					jzMonth = Integer.valueOf(sdfyyyyMM.format(sdf.parse(jzCheckTimeStr)));
					// 如果基装验收日期在后边月，则当前月的从开工日期到月底都是基装天数
					if (jzMonth > currentMonth) {
						jzShouldSignDays = currentMonthDays - startWorkDate + 1;
					} else { // 否则是当前月基装验收完成，基装应签到天数=基装验收日期-开工日期；
						jzShouldSignDays = sdf.parse(jzCheckTimeStr).getDate()
								- startWorkDate;
						if(jgCheckTimeStr==null||(jgCheckTimeStr!=null&jgCheckTimeStr.equals(""))){
							jgShouldSignDays = currentMonthDays
									- sdf.parse(jzCheckTimeStr).getDate() + 1;
						}else{
							// 如果竣工验收日期在下个月，则竣工应签到天数=这个月总天数-集装验收日期+1
							if (jgMonth > currentMonth) {
								jgShouldSignDays = currentMonthDays
										- sdf.parse(jzCheckTimeStr).getDate() + 1;
							} else {
								jgShouldSignDays = sdf.parse(jgCheckTimeStr)
										.getDate()
										- sdf.parse(jzCheckTimeStr).getDate() + 1;
							}
						}

					}
				}
			}
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");
			Date date = new Date();
			String effectMonth = sd.format(date);
			String storeId = osv.getStoreId();

			BizPmAttendCnfg bp = signService.getCnfgByStoreIdAndEffectMonth(
					effectMonth, storeId);
			//如果当前月份没有设置基装签到周期天数和主材签到周期天数，取当前月以前的最大月份的数据
			if(null==bp){
				bp = signService.getMaxMonthCnfgByStoreIdAndEffectMonth(
						effectMonth, storeId);
			}
			int jzCycleDays = 0;
			int jgCycleDays = 0;
			if(bp!=null){
				jzCycleDays = Integer.valueOf(bp.getSignCycleDaysBasicwork()); // 基装周期天数
				jgCycleDays = Integer.valueOf(bp.getSignCycleDaysComplete());  // 竣工签到周期天数
				// 得到应签到次数
				jzShouldSignTimes = jzShouldSignDays / jzCycleDays;
				jgShouldSignTimes = jgShouldSignDays / jgCycleDays;
				osv.setJgShouldSignTimes(jgShouldSignTimes+"");
				osv.setJzShouldSignTimes(jzShouldSignTimes+"");
			}else{
				osv.setJgShouldSignTimes("没有数据");
				osv.setJzShouldSignTimes("没有数据");
			}
			osv.setJzShouldSignDays(jzShouldSignDays+"");
			osv.setJgShouldSignDays(jgShouldSignDays+"");

			//查询已签到次数
			Map<String, String> map = new HashMap<String,String>();
			map.put("orderId", osv.getId().toString());
			map.put("managerId", osv.getItemManagerId().toString());
			map.put("signStep", "10"); //基装参数
			osv.setJzAlreadySignTimes(signService.getAlreadySignTimesByMap(map));
			map.put("signStep", "20"); //主材参数
			osv.setJgAlreadySignTimes(signService.getAlreadySignTimesByMap(map));
			// osv.setJzCheckTime(jzCheckTime);
		}

		model.addAttribute("signList", list);

		return "mobile/modules/Manager/sign";
	}

	// 得到某时间的月份的总天数
	public static int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 查询订单,签到页
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "searchOrder", "" })
	public @ResponseBody
	List<OrderSignVo> searchOrder(HttpServletRequest request, Model model) {
		Manager manager = SessionUtils.getManagerSession(request);
		List<OrderSignVo> list = signService.orderByManagerId(manager.getId());
		return list;
	}

	/**
	 * gps定位页面
	 *
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "signByGPS", "" })
	public String signByGPS(HttpServletRequest request, Model model,
							String orderId) {

		OrderSignVo signVo = signService.get(Integer.valueOf(orderId));
		String[] split = signVo.getMapAddress().split(",");
		model.addAttribute("lon", split[0]);
		model.addAttribute("lat", split[1]);
		model.addAttribute("orderId", orderId);
		return "mobile/modules/Manager/map2";
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
	public @ResponseBody
	JSONArray getAddress(OrderSignVo order, HttpServletRequest request,
                         Model model) {

		OrderSignVo signVo = signService.get(order.getId());
		String[] split = signVo.getMapAddress().split(",");

		return JSONArray.fromObject(split);
	}

	/**
	 * 到图片上传页面
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "uploadPhoto", "" })
	public String uploadPhoto(HttpServletRequest request, Model model,
							  String orderId) {

		// 接着把订单id带过去
		model.addAttribute("orderId", orderId);
		return "mobile/modules/Manager/upload_photo";
	}

	/**
	 * 上传图片,不做持久保存
	 *
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping(value = "savePhoto", method = RequestMethod.POST)
	public @ResponseBody
	List<String> savePhoto(HttpServletRequest req,
						   @RequestParam(value = "pic", required = false) MultipartFile[] pic,
						   String orderId) throws IllegalStateException, IOException {
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) req;

		// 获得物理路径webapp所在路径
		String pathRoot = request.getSession().getServletContext()
				.getRealPath("");
		String path = "";
		List<String> listImagePath = new ArrayList<String>();
		for (MultipartFile mf : pic) {
			if (!mf.isEmpty()) {

				// 获得文件类型（可以判断如果不是图片，禁止上传）
				String contentType = mf.getContentType();
				// 获得文件后缀名称
				String imageName = contentType.substring(contentType
						.indexOf("/") + 1);

				path = "/static/mobile/modules/Manager/css/photo/"
						+ mf.getOriginalFilename() + "." + imageName;
				mf.transferTo(new File(pathRoot + path));

				listImagePath.add(path);
			}
		}

		return listImagePath;
	}

	// /**
	// * 现场图片签到
	// *
	// * @param req
	// * @param sign
	// * @param model
	// * @param pic
	// * @return
	// */
	// @RequestMapping(value = "sign", method = RequestMethod.POST)
	// public @ResponseBody String submitPic(HttpServletRequest req, SignDetail
	// sign, Model model,
	// @RequestParam(required = false) MultipartFile[] pic) {
	// // 签到有两种 一个是上传图片签到, 一个是根据地址gps签到
	//
	// MultipartHttpServletRequest request = (MultipartHttpServletRequest) req;
	// // 签到时间
	// sign.setSignDate(new Date());
	// // 经理id
	// sign.setManagerId(SessionUtils.getManagerSession(request).getId());
	//
	// AppOrder order = signService.getCustomerInfoByOrderId(sign.getOrderId());
	// // 顾客信息 组成元素: 小区名-楼-单元-室-姓名
	// sign.setCustomerInfo(order.getCommunityName() + "-" +
	// order.getBuildNumber() + "-" + order.getBuildUnit() + "-"
	// + order.getBuildRoom() + "-" + order.getCustomerName());
	// /*// 保存该签到路径
	// StringBuilder sb = new StringBuilder();
	// if (null != pic && pic.length > 0)
	// for (MultipartFile p : pic) {
	// // 得到图片名称
	// String fileName = p.getOriginalFilename();
	//
	// // 图片完整路径
	// String path = "/mdn/static/mobile/modules/Manager/css/photo/" + fileName;
	//
	// // 保存属性
	// // 图片路径
	// sign.setSignType("1");
	// sb.append(path+"--");
	// }
	// else {
	// sign.setSignType("1");
	//
	// //经纬度
	// Double x =LngAndLatUtils.getLngAndLat(sign.getSignAddress()).get("lng");
	// Double y =LngAndLatUtils.getLngAndLat(sign.getSignAddress()).get("lat");
	// sign.setSignXy(x+","+y);
	//
	// }*/
	// //登录人姓名
	// sign.setSignType("1");
	//
	// //经纬度
	// Double x =LngAndLatUtils.getLngAndLat(sign.getSignAddress()).get("lng");
	// Double y =LngAndLatUtils.getLngAndLat(sign.getSignAddress()).get("lat");
	// sign.setSignXy(x+","+y);
	//
	// sign.setSignName(SessionUtils.getManagerSession(request).getRealname());
	// sign.setManagerName(sign.getSignName());
	// signService.signSuccess(sign);
	//
	// return "1";
	// }

	/**
	 * 项目经理端 开工签到
	 *
	 * @param signDetail
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "sign")
	public @ResponseBody
	String sign(SignDetail signDetail, Model model, HttpServletRequest request)
			throws Exception {
		String flag = "error";

		// 获取项目经理id
		Manager manager = SessionUtils.getManagerSession(request);
		Date date = new Date();
		double signDistance = signDetail.getSignDistance();
		SignDetail sign = new SignDetail();
		sign.setOrderId(signDetail.getOrderId());
		sign.setSignDate(date);
		sign.setSignAddress(signDetail.getSignAddress());
		sign.setSignDistance(signDistance);
		sign.setSignXy(signDetail.getLon() + "," + signDetail.getLat());
		sign.setSignType(ConstantUtils.SIGN_TYPE_MANAGER_START);
		sign.setManagerId(manager.getId());
		sign.setSignName(manager.getRealname());
		sign.setManagerName(manager.getRealname());
		// 判断签到距离是否超过1000米，超过为不合格，不超过为合格
		if (signDistance > 1000  ) {
			sign.setIsValid("0");
		} else {
			sign.setIsValid("1");
		}
		// 查出该订单的基装验收时间
		String checkDateTime = signService.getCheckTimeByOrderIdAndNode(signDetail.getOrderId(),"6");
		//竣工签到时间
		String jgCheckDateTime = signService.getCheckTimeByOrderIdAndNode(signDetail.getOrderId(),"9");
		// 如果验收时间为空，则此时段为基装时段，签到为基装签到
		if (null == checkDateTime||(checkDateTime!=null&&checkDateTime.equals(""))) {
			if(jgCheckDateTime==null||(jgCheckDateTime!=null&&jgCheckDateTime.equals(""))){
				sign.setSignStep("10"); // 基装签到
			}else{
				sign.setSignStep("20"); // 竣工签到
			}
		} else {
			if(isToday(checkDateTime, "yyyy-MM-dd mm:HH:ss")){  // 如果签到时间和基装验收时间是同一天，则按基装签到
				sign.setSignStep("10"); // 基装签到
			}else{
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd mm:HH:ss");
				//如果基装验收的日期>今天签到的时间，则为基装,否则为主材签到
				if(date.compareTo(sf.parse(checkDateTime))<=0){
					sign.setSignStep("10");
				}else{
					sign.setSignStep("20");
				}
			}
		}
		try {
			AppOrder order = signService.getCustomerInfoByOrderId(signDetail
					.getOrderId());
			sign.setCustomerInfo(order.getCommunityName() + "-"
					+ order.getBuildNumber() + "-" + order.getBuildUnit() + "-"
					+ order.getBuildRoom() + "-" + order.getCustomerName());
			signService.signSuccess(sign);
			Map<String, String> map = new HashMap<String,String>();
			map.put("orderId", signDetail.getOrderId().toString());
			map.put("managerId", manager.getId().toString());
			System.out.println(manager.getId().toString());
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			map.put("signDateTime", sf.format(date));
			sign.preInsert();
			//查询换项目经理的记录
			List<String> logList = signService.findDistributeLogByOrderId(signDetail.getOrderId());
			String data = DateUtils.getDate();
			if(logList.size() > 0){
				for (String log: logList) {
					if(!log.equals(data)){
						//查出这个date所在时间当天在biz_pm_attend_day_order表中是否已经有数据，如果没有不论合格不合格直接插入
						String isValid = signService.getIsValidByOrderIdAndManagerIdAndSignDate(map);
						if(null==isValid||(isValid!=null&&isValid.equals(""))){
							signService.insertDayOrderBySignDetail(sign);
						}else if(isValid.equals("0")){  //如果不合格，则需要更新数据
							if(sign.getIsValid().equals("1")){ //签到数据为合格时，更新biz_pm_attend_day_order数据
								signService.updateDayOrderBySignDetail(sign);
							}
						}
					}
				}
			}else{
				String isValid = signService.getIsValidByOrderIdAndManagerIdAndSignDate(map);
				if(null==isValid||(isValid!=null&&isValid.equals(""))){
					signService.insertDayOrderBySignDetail(sign);
				}else if(isValid.equals("0")){  //如果不合格，则需要更新数据
					if(sign.getIsValid().equals("1")){ //签到数据为合格时，更新biz_pm_attend_day_order数据
						signService.updateDayOrderBySignDetail(sign);
					}
				}
			}
			flag = "success";
		} catch (Exception e) {
            e.printStackTrace();
			flag = "error";
		}

		return flag;
	}

	// 判断日期是否为今天
	public static boolean isToday(String str, String formatStr)
			throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date date = format.parse(str);
//		try {
//			date = format.parse(str);
//		} catch (ParseException e) {
//			// logger.error("解析日期错误", e);
//		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		int year1 = c1.get(Calendar.YEAR);
		int month1 = c1.get(Calendar.MONTH) + 1;
		int day1 = c1.get(Calendar.DAY_OF_MONTH);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(new Date());
		int year2 = c2.get(Calendar.YEAR);
		int month2 = c2.get(Calendar.MONTH) + 1;
		int day2 = c2.get(Calendar.DAY_OF_MONTH);
		if (year1 == year2 && month1 == month2 && day1 == day2) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "signDetail")
	public String signDetail(SignDetail detail, Model model, HttpServletRequest request) {

		// 根据订单id 查询签到详情
        Manager manager = SessionUtils.getManagerSession(request);
		List<SignDetail> signDetail = signService
				.getSignDetailByOrderIdLimit(detail.getOrderId(),manager.getId());
		if (null != signDetail && signDetail.size() > 0) {
			model.addAttribute("list", signDetail);

		} else {

			model.addAttribute("error", "您还没有签到,请先签到");
		}

		return "mobile/modules/Manager/sign_details";
	}
}
