package cn.damei.web.modules;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.service.modules.BizPhoneMsgService;
import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.HttpConnection;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.GroupSendMessage;
import cn.damei.service.modules.OrderTaskpackageService;

public class QuartZJobTaskPackageController {

	private static Logger logger = LoggerFactory.getLogger(QuartZJobTaskPackageController.class);

	@Autowired
	private OrderTaskpackageService orderTaskpackageService;

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;

	@SuppressWarnings("static-access")
	public void execute() throws UnsupportedEncodingException {
		logger.info("---------------------QuartZ开始-------------");

		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Date beforeDate = DateUtils.addDate(new Date(), -1);
		
		List<GroupSendMessage> orderList = orderTaskpackageService.getByNewDate(sd.format(beforeDate));
		Map<String, String> mapGroup = new HashMap<String, String>();
		Map<String, String> manager = new HashMap<String, String>();
		if (orderList.size() > 0) {
			for (GroupSendMessage tp : orderList) {
				HttpConnection send = new HttpConnection();

				Integer orderTaskPackageId = null;
				if (StringUtils.isNotBlank(tp.getOrderTaskPackageId())){
					orderTaskPackageId = Integer.valueOf(tp.getOrderTaskPackageId());
				}


				if (tp.getEmpType().equals(ConstantUtils.EMP_TYPE_GROUPLEAD)) {
					mapGroup.clear();

					if (StringUtils.isNotBlank(tp.getItemManagerId().toString())) {
						BizEmployee2 managerPhone = bizEmployeeService2.get(tp.getItemManagerId());
						logger.info("(2：工人组长，3：项目经理)员工类型：" + tp.getEmpType() + "\t 姓名：" + tp.getRealName() + "\t 手机号："+ tp.getPhone());
						if(null != managerPhone){
							String groupLeadMessage = tp.getCommunityName() + "-" + tp.getBuildNumber() + "-"
									+ tp.getBuildUnit() + "-" + tp.getBuildRoom() + "-" + tp.getCustomerName() + "-" + tp.getPackageName()
									+ "将在"+DateUtils.formatDate(beforeDate) + "开工，" + "项目经理：" + managerPhone.getRealname()
									+ "-" + managerPhone.getPhone() + "，请知晓。";

							bizPhoneMsgService.sendMessage(tp.getGroupId(), tp.getPhone(),
									groupLeadMessage, SendMsgBusinessType.SEND_MSG_BUSINESS_TYPE_200805, orderTaskPackageId);

						}else{
							logger.info("工人组长端数据有误！");
						}
					}
				}


				if (tp.getEmpType().equals(ConstantUtils.EMP_TYPE_MANAGER)) {
					manager.clear();
					if (StringUtils.isNotBlank(tp.getGroupId().toString())) {
						BizEmployee2 workerPhone = bizEmployeeService2.get(tp.getGroupId());
						
						logger.info("(2：工人组长，3：项目经理)员工类型：" + tp.getEmpType() + "\t 姓名：" + tp.getRealName() + "\t 手机号：" + tp.getPhone());
						if(null != workerPhone){
							String managerMessage = tp.getCommunityName() + "-" + tp.getBuildNumber() + "-"
									+ tp.getBuildUnit() + "-" + tp.getBuildRoom() + "-" + tp.getCustomerName() + "-" + tp.getPackageName() + "将在"
									+ DateUtils.formatDate(beforeDate, "yyyy-MM-dd HH:mm:ss") + "开工，" + "组长："
									+ workerPhone.getRealname() + "-" + workerPhone.getPhone() + "，请知晓。";

							bizPhoneMsgService.sendMessage(tp.getItemManagerId(), tp.getPhone(),
									managerMessage, SendMsgBusinessType.SEND_MSG_BUSINESS_TYPE_200806, orderTaskPackageId);

						}else{
							logger.info("项目经理端数据有误！");
						}
					}
				}
			}
		}

		logger.info("---------------------QuartZ结束-------------");
	}
}
