package cn.damei.web.modules;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.utils.DateUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.ProblemItemReport;
import cn.damei.service.modules.ProblemItemReportService;


@Controller
@RequestMapping(value = "${adminPath}/ordercomplan/problemItemReport")
public class ProblemItemReportController extends BaseController {

	@Autowired
	private ProblemItemReportService problemItemReportService;


	@RequestMapping(value = "/queryItemReport")
	@RequiresPermissions("ordercomplan:problemItemReport:view")
	public String queryItemReport(ProblemItemReport problemItemReport, Model model) {
		List<ProblemItemReport> itemList = problemItemReportService.queryList(problemItemReport);
		model.addAttribute("itemList", itemList);
		return "modules/ordercomplan/problemItemReportList";

	}


	@RequestMapping(value = "/excelExportItem")
	@RequiresPermissions("ordercomplan:problemItemReport:view")
	public String excelExportItem(ProblemItemReport problemItemReport, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<ProblemItemReport> itemList = problemItemReportService.queryList(problemItemReport);

		try {

			HSSFWorkbook wb = new HSSFWorkbook();
			problemItemReportService.excelExportItem(problemItemReport, itemList, wb);
			String fileName = "投诉事项报表" + DateUtils.getDate("yyyyMMddHHmmss") + ".xls";

			OutputStream output = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=" + fileName);
			response.setContentType("application/msexcel");
			wb.write(output);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("cn.damei.web.modules.ProblemItemReportController.excelExportItem:这个方法error了!!!是reeor不是warning");
		}

		return null;
	}
}
