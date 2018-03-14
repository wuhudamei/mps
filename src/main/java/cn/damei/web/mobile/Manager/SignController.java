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

	@RequestMapping(value = { "signIndex", "" })
	public String signIndex(HttpServletRequest request, Model model)
			throws ParseException {





		Manager manager = SessionUtils.getManagerSession(request);

		List<OrderSignVo> list = signService.orderByManagerId(manager.getId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfyyyyMM = new SimpleDateFormat("yyyyMM");
		int currentMonth = Integer.valueOf(sdfyyyyMM.format(new Date()));
		int jzMonth = 0;
		int jgMonth = 0;
		int currentMonthDays = getDaysOfMonth(new Date());
		for (OrderSignVo osv : list) {
			String jzCheckTimeStr = signService.getCheckTimeByOrderIdAndNode(
					osv.getId(), "6");
			String jgCheckTimeStr = signService.getCheckTimeByOrderIdAndNode(
					osv.getId(), "9");
			Date ActualStartDate = osv.getActualStartDate();
			int startWorkMonth = Integer.valueOf(sdfyyyyMM.format(ActualStartDate));
			int startWorkDate = ActualStartDate.getDate();
			int jzShouldSignDays = 0;
			int jgShouldSignDays = 0;
			int jzShouldSignTimes = 0;
			int jgShouldSignTimes = 0;

			if (startWorkMonth < currentMonth) {

				if (null == jzCheckTimeStr||(jzCheckTimeStr!=null&&jzCheckTimeStr.equals(""))) {

					if(jgCheckTimeStr==null||(jgCheckTimeStr!=null&&jgCheckTimeStr.equals(""))){
						jzShouldSignDays = currentMonthDays;
					}else{
						jgShouldSignDays = currentMonthDays;
					}
				} else {
					jzMonth = Integer.valueOf(sdfyyyyMM.format(sdf.parse(jzCheckTimeStr)));


					if (jzMonth > currentMonth) {
						jzShouldSignDays = currentMonthDays;
					} else if (jzMonth == currentMonth) {



						jzShouldSignDays = sdf.parse(jzCheckTimeStr).getDate();
						if(jgCheckTimeStr==null||(jgCheckTimeStr!=null&jgCheckTimeStr.equals(""))){
							jgShouldSignDays = currentMonthDays
									- sdf.parse(jzCheckTimeStr).getDate() + 1;
						}else{

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
			} else if (startWorkMonth == currentMonth) {

				if (null == jzCheckTimeStr||(jzCheckTimeStr!=null&&jzCheckTimeStr.equals(""))) {

					if(jgCheckTimeStr==null||(jgCheckTimeStr!=null&&jgCheckTimeStr.equals(""))){
						jzShouldSignDays = currentMonthDays- startWorkDate + 1;
					}else{
						jgShouldSignDays = currentMonthDays- startWorkDate + 1;
					}
				} else {

					jzMonth = Integer.valueOf(sdfyyyyMM.format(sdf.parse(jzCheckTimeStr)));

					if (jzMonth > currentMonth) {
						jzShouldSignDays = currentMonthDays - startWorkDate + 1;
					} else {
						jzShouldSignDays = sdf.parse(jzCheckTimeStr).getDate()
								- startWorkDate;
						if(jgCheckTimeStr==null||(jgCheckTimeStr!=null&jgCheckTimeStr.equals(""))){
							jgShouldSignDays = currentMonthDays
									- sdf.parse(jzCheckTimeStr).getDate() + 1;
						}else{

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

			if(null==bp){
				bp = signService.getMaxMonthCnfgByStoreIdAndEffectMonth(
						effectMonth, storeId);
			}
			int jzCycleDays = 0;
			int jgCycleDays = 0;
			if(bp!=null){
				jzCycleDays = Integer.valueOf(bp.getSignCycleDaysBasicwork());
				jgCycleDays = Integer.valueOf(bp.getSignCycleDaysComplete());

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


			Map<String, String> map = new HashMap<String,String>();
			map.put("orderId", osv.getId().toString());
			map.put("managerId", osv.getItemManagerId().toString());
			map.put("signStep", "10");
			osv.setJzAlreadySignTimes(signService.getAlreadySignTimesByMap(map));
			map.put("signStep", "20");
			osv.setJgAlreadySignTimes(signService.getAlreadySignTimesByMap(map));

		}

		model.addAttribute("signList", list);

		return "mobile/modules/Manager/sign";
	}


	public static int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}


	@RequestMapping(value = { "searchOrder", "" })
	public @ResponseBody
	List<OrderSignVo> searchOrder(HttpServletRequest request, Model model) {
		Manager manager = SessionUtils.getManagerSession(request);
		List<OrderSignVo> list = signService.orderByManagerId(manager.getId());
		return list;
	}


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


	@RequestMapping(value = { "getAddress", "" })
	public @ResponseBody
	JSONArray getAddress(OrderSignVo order, HttpServletRequest request,
                         Model model) {

		OrderSignVo signVo = signService.get(order.getId());
		String[] split = signVo.getMapAddress().split(",");

		return JSONArray.fromObject(split);
	}


	@RequestMapping(value = { "uploadPhoto", "" })
	public String uploadPhoto(HttpServletRequest request, Model model,
							  String orderId) {


		model.addAttribute("orderId", orderId);
		return "mobile/modules/Manager/upload_photo";
	}


	@RequestMapping(value = "savePhoto", method = RequestMethod.POST)
	public @ResponseBody
	List<String> savePhoto(HttpServletRequest req,
						   @RequestParam(value = "pic", required = false) MultipartFile[] pic,
						   String orderId) throws IllegalStateException, IOException {
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) req;


		String pathRoot = request.getSession().getServletContext()
				.getRealPath("");
		String path = "";
		List<String> listImagePath = new ArrayList<String>();
		for (MultipartFile mf : pic) {
			if (!mf.isEmpty()) {


				String contentType = mf.getContentType();

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



































































	@RequestMapping(value = "sign")
	public @ResponseBody
	String sign(SignDetail signDetail, Model model, HttpServletRequest request)
			throws Exception {
		String flag = "error";


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

		if (signDistance > 1000  ) {
			sign.setIsValid("0");
		} else {
			sign.setIsValid("1");
		}

		String checkDateTime = signService.getCheckTimeByOrderIdAndNode(signDetail.getOrderId(),"6");

		String jgCheckDateTime = signService.getCheckTimeByOrderIdAndNode(signDetail.getOrderId(),"9");

		if (null == checkDateTime||(checkDateTime!=null&&checkDateTime.equals(""))) {
			if(jgCheckDateTime==null||(jgCheckDateTime!=null&&jgCheckDateTime.equals(""))){
				sign.setSignStep("10");
			}else{
				sign.setSignStep("20");
			}
		} else {
			if(isToday(checkDateTime, "yyyy-MM-dd mm:HH:ss")){
				sign.setSignStep("10");
			}else{
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd mm:HH:ss");

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

			List<String> logList = signService.findDistributeLogByOrderId(signDetail.getOrderId());
			String data = DateUtils.getDate();
			if(logList.size() > 0){
				for (String log: logList) {
					if(!log.equals(data)){

						String isValid = signService.getIsValidByOrderIdAndManagerIdAndSignDate(map);
						if(null==isValid||(isValid!=null&&isValid.equals(""))){
							signService.insertDayOrderBySignDetail(sign);
						}else if(isValid.equals("0")){
							if(sign.getIsValid().equals("1")){
								signService.updateDayOrderBySignDetail(sign);
							}
						}
					}
				}
			}else{
				String isValid = signService.getIsValidByOrderIdAndManagerIdAndSignDate(map);
				if(null==isValid||(isValid!=null&&isValid.equals(""))){
					signService.insertDayOrderBySignDetail(sign);
				}else if(isValid.equals("0")){
					if(sign.getIsValid().equals("1")){
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


	public static boolean isToday(String str, String formatStr)
			throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date date = format.parse(str);





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
