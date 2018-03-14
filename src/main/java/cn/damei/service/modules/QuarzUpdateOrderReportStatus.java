package cn.damei.service.modules;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BizOrderReportConstantUtil;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.HttpConnection;
import cn.damei.common.utils.JsonUtils;
import cn.damei.common.utils.KeyAuthenticateUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.dao.modules.BizOrderReportDao;
import cn.damei.entity.modules.BizOrderReport;

/**
 * Created by joseph on 2017/5/10. 定时从微信端获取返单状态信息信息 (根据手机和客户姓名更新返单) 晚上11点
 * (12点定时器较多)
 */
@Transactional
@Service
public class QuarzUpdateOrderReportStatus {


	private Logger logger = LoggerFactory.getLogger(QuarzUpdateOrderReportStatus.class);
	private String remoteUrl = "/CustomerAPI/GetInStoreCustomerInfo";
	private String remoteUrlForBigOrder = "/CustomerAPI/GetBigOrderCustomerInfo";
	@Autowired
	private BizOrderReportDao orderReportDao;
	@Autowired
	BizOrderReportService bizOrderReportService;

	/**
	 * 已进店和已签订单 (已进店已签单, 已进店未签单)
	 */
	public void execute() {
		if (logger.isDebugEnabled()) {
			logger.info("返单定时任务开始查询中...........");
			logger.info("返单定时任务开始查询中...........");
			logger.info("返单定时任务开始查询中...........");
			logger.info("返单定时任务开始查询中...........");
			logger.info("返单定时任务开始查询中...........");

		}

		// 1:查询顾客信息
		// 2:查询批量更新状态为不是该状态的返单信息
		Date date = new Date();

		String startTime = DateFormatUtils.format(date, "yyyy-MM-dd");

		String endTime = DateFormatUtils.format(DateUtils.addDate(date, +1), "yyyy-MM-dd");

		Map<String, String> params = new HashMap<String, String>();

		params.put("startTime", startTime);
		params.put("endTime", endTime);
		String[] paramArr = new String[] { "startTime:" + startTime, "endTime:" + endTime };
		params.put("key", KeyAuthenticateUtils.getKey(paramArr, BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY));

		try {
			Map<String, Object> map = httpRequest(remoteUrl, params);
			Map<String, Object> bigOrderMap = httpRequest(remoteUrlForBigOrder, params);
			bigMapFunction(bigOrderMap);

			if (null != map.get("code") && ("1").equals(map.get("code").toString())) {
				Object object = map.get("data");
				JSONArray jsonArray = JSONArray.fromObject(object);

				List<Map<String, Object>> mapListJson = (List<Map<String, Object>>) jsonArray;
				for (int i = 0; i < mapListJson.size(); i++) {
					Map<String, Object> obj = mapListJson.get(i);
					BizOrderReport report = new BizOrderReport();
					for (Map.Entry<String, Object> entry : obj.entrySet()) {

						if (entry.getKey().equals("customerName")) {
							report.setCustomerName(entry.getValue().toString());

						}
						if (entry.getKey().equals("customerMobile")) {
							report.setCustomerPhone(entry.getValue().toString());

						}
						if (entry.getKey().equals("inTime")) {
							try {
								report.setInstoreDatetime(DateUtils.parseDate(entry.getValue().toString()));

							} catch (Exception e) {

								e.printStackTrace();
								logger.error("定时器出错");
							}
						}
					}
					if (null != report.getCustomerPhone()) {
						report.setReportStatus(BizOrderReportConstantUtil.REPORT_STATUS_30);

						/**
						 * id和状态
						 */
						BizOrderReport orderReport = orderReportDao.selectReportInfoByNameAndPhone(report);

						if (null != orderReport && (orderReport.getReportStatus().equals("10") || orderReport.getReportStatus().equals("25"))) {
							// 批量更新符合条件的客户
							report.setId(orderReport.getId());
							orderReportDao.batchUpdateOrderReportStatusByQuarz(report);
							bizOrderReportService.saveBizBusinessStatusLog(report);

						}

					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		if (logger.isDebugEnabled()) {
			logger.info("返单定时任务执行完毕...........");
			logger.info("返单定时任务执行完毕...........");
			logger.info("返单定时任务执行完毕...........");
			logger.info("返单定时任务执行完毕...........");
			logger.info("返单定时任务执行完毕...........");

		}

	}

	/**
	 * 请求接口，返回结果解析
	 * 
	 * @param requestURL
	 * @param params
	 * @return
	 */
	private Map<String, Object> httpRequest(String requestURL, Map<String, String> params) {
		String post = null;
		try {
			post = HttpConnection.post(PicRootName.getConfigValue("remote_interface_url") + requestURL, params);
		} catch (Exception e) {
			logger.error("返单有效性验证接口调用异常！！！" + post);
			logger.error("接口地址：" + requestURL);
			logger.error(e.getMessage());
		}

		// json结果 解析
		return JsonUtils.parseJSON2Map(post);
	}

//	public void errorSendMessage(String content) {
//		Map<String, String> mapGroup = new HashMap<String, String>();
//
//		mapGroup.put("source", "1");
//		mapGroup.put("content", content);
//		mapGroup.put("mobile", "18610507472");
//		mapGroup.put("sendtime", "");
//
//		try {
//			HttpConnection send = new HttpConnection();
//			send.post(PATH, mapGroup);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//
//			logger.error("定时获取客户信息发送短信失败");
//
//		}
//
//	}

	public void bigMapFunction(Map<String, Object> bigOrderMap) {

		if (null != bigOrderMap.get("code") && ("1").equals(bigOrderMap.get("code").toString())) {
			Object object = bigOrderMap.get("data");
			JSONArray jsonArray = JSONArray.fromObject(object);

			List<Map<String, Object>> mapListJson = (List<Map<String, Object>>) jsonArray;
			for (int i = 0; i < mapListJson.size(); i++) {
				Map<String, Object> obj = mapListJson.get(i);
				BizOrderReport report = new BizOrderReport();
				for (Map.Entry<String, Object> entry : obj.entrySet()) {

					if (entry.getKey().equals("customerName")) {
						report.setCustomerName(entry.getValue().toString());

					}
					if (entry.getKey().equals("customerMobile")) {
						report.setCustomerPhone(entry.getValue().toString());

					}
					if (entry.getKey().equals("bigTime")) {
						try {
							report.setSignBillDatetime(DateUtils.parseDate(entry.getValue().toString()));

						} catch (Exception e) {

							e.printStackTrace();
							logger.error("定时器出错");
//							errorSendMessage("晚上11点定时获取客户新店信息时, inTime转换失败" + "客户姓名:" + report.getCustomerName() + "客户手机:" + report.getCustomerPhone() + "bigTime 为:" + entry.getValue());
						}
					}
					if (entry.getKey().equals("orderno")) {
						try {
							report.setOrderNumber(entry.getValue().toString());
						} catch (Exception e) {

							e.printStackTrace();
							logger.error("定时器出错");
//							errorSendMessage("错误原因:orderno报错");
						}
					}
				}

				if (null != report.getCustomerName()) {
					report.setReportStatus(BizOrderReportConstantUtil.REPORT_STATUS_40);
					BizOrderReport orderReport = orderReportDao.selectReportInfoByNameAndPhone(report);

					if (null != orderReport && (orderReport.getReportStatus().equals("10") || orderReport.getReportStatus().equals("25") || orderReport.getReportStatus().equals("30"))) {
						// 批量更新符合条件的客户
						report.setId(orderReport.getId());
						orderReportDao.batchUpdateOrderReportStatusByQuarzTwo(report);
						bizOrderReportService.saveBizBusinessStatusLog(report);

					}

				}

			}

		}

	}

}
