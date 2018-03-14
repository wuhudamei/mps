package cn.damei.web.mobile.Worker;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Worker.Worker;
import cn.damei.entity.modules.BizNotice;
import cn.damei.service.modules.BizNoticeService;
import cn.damei.entity.modules.BizNoticeViewLog;
import cn.damei.service.modules.BizNoticeViewLogService;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 进度通报
 * biz_node_plan
 * llp
 */
@Controller
@RequestMapping(value = "${adminPath}/app/worker/notice")
public class WorkNoticeController {
	private static Logger logger = LoggerFactory.getLogger(WorkNoticeController.class);

	@Autowired
	private BizNoticeService bizNoticeService;

	@Autowired
	private BizNoticeViewLogService bizNoticeViewLogService;

	@RequestMapping(value={"queryNoticePageList",""})
	public String queryNoticePageList(HttpServletRequest request, Model model) {
		Worker worker = SessionUtils.getWorkerSession(request);
		BizNotice notice = new BizNotice();
		notice.setNoticeStatus(ConstantUtils.NOTICE_STATUS_2);
		notice.setReceiverEmployeeId(worker.getId());
		Integer count = bizNoticeService.queryAppNoticePageListCount(notice);
		model.addAttribute("workerId", worker.getId());
		model.addAttribute("count", count);
		return "mobile/modules/Worker/notice/notice_index_list";
	}

	@RequestMapping(value={"queryLoadNoticePageList",""})
	public @ResponseBody Page<BizNotice> queryLoadNoticePageList(BizNotice bizNotice, HttpServletRequest request, HttpServletResponse response) {
		bizNotice.setNoticeStatus(ConstantUtils.NOTICE_STATUS_2);
		Page<BizNotice> page = bizNoticeService.findAppNoticePage(new Page<BizNotice>(request, response), bizNotice);
		return page;
	}

	@RequestMapping(value={"queryNoticePageListDetail",""})
	public String queryNoticePageListDetail(Integer id, Model model) {
		BizNotice notice = bizNoticeService.get(id);
		notice.setNoticeContent(StringEscapeUtils.unescapeHtml(notice.getNoticeContent()));
		model.addAttribute("notice", notice);
		return "mobile/modules/Worker/notice/notice_index_list_detail";
	}

	@RequestMapping(value={"saveNoticeLog",""})
	public void saveNoticeLog(HttpServletRequest request, Integer id) {
		try{
			Date date = new Date();
			Worker worker = SessionUtils.getWorkerSession(request);
			BizNoticeViewLog log = new BizNoticeViewLog();
			log.setNoticeId(id);
			log.setViewEmployeeId(worker.getId());
			BizNoticeViewLog noticeLog = bizNoticeViewLogService.queryByNoticeIdAndEmployeeId(log);
			if(noticeLog != null){
				noticeLog.setViewDatetime(date);
				bizNoticeViewLogService.update(noticeLog);
			}else{
				noticeLog = new BizNoticeViewLog();
				noticeLog.setNoticeId(id);
				noticeLog.setViewEmployeeId(worker.getId());
				noticeLog.setViewDatetime(date);
				noticeLog.setCreateDate(date);
				noticeLog.setUpdateDate(date);
				bizNoticeViewLogService.insert(noticeLog);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
