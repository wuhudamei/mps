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


	public void execute() {
		if (logger.isDebugEnabled()) {
			logger.info("返单定时任务开始查询中...........");
			logger.info("返单定时任务开始查询中...........");
			logger.info("返单定时任务开始查询中...........");
			logger.info("返单定时任务开始查询中...........");
			logger.info("返单定时任务开始查询中...........");

		}



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


						BizOrderReport orderReport = orderReportDao.selectReportInfoByNameAndPhone(report);

						if (null != orderReport && (orderReport.getReportStatus().equals("10") || orderReport.getReportStatus().equals("25"))) {

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


	private Map<String, Object> httpRequest(String requestURL, Map<String, String> params) {
		String post = null;
		try {
			post = HttpConnection.post(PicRootName.getConfigValue("remote_interface_url") + requestURL, params);
		} catch (Exception e) {
			logger.error("返单有效性验证接口调用异常！！！" + post);
			logger.error("接口地址：" + requestURL);
			logger.error(e.getMessage());
		}


		return JsonUtils.parseJSON2Map(post);
	}





















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

						}
					}
					if (entry.getKey().equals("orderno")) {
						try {
							report.setOrderNumber(entry.getValue().toString());
						} catch (Exception e) {

							e.printStackTrace();
							logger.error("定时器出错");

						}
					}
				}

				if (null != report.getCustomerName()) {
					report.setReportStatus(BizOrderReportConstantUtil.REPORT_STATUS_40);
					BizOrderReport orderReport = orderReportDao.selectReportInfoByNameAndPhone(report);

					if (null != orderReport && (orderReport.getReportStatus().equals("10") || orderReport.getReportStatus().equals("25") || orderReport.getReportStatus().equals("30"))) {

						report.setId(orderReport.getId());
						orderReportDao.batchUpdateOrderReportStatusByQuarzTwo(report);
						bizOrderReportService.saveBizBusinessStatusLog(report);

					}

				}

			}

		}

	}

}
