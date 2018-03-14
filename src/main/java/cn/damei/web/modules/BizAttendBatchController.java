package cn.damei.web.modules;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizAttendBatch;
import cn.damei.entity.modules.BizAttendBill;
import cn.damei.service.modules.BizAttendBatchService;
import cn.damei.service.modules.BizAttendBillService;

@Controller
@RequestMapping(value = "${adminPath}/bizAttend/bizAttendBatch/")
public class BizAttendBatchController extends BaseController {

	@Autowired
	private BizAttendBatchService bizAttendBatchService;
	@Autowired
	private BizAttendBillService bizAttendBillService;

	@RequiresPermissions("bizAttend:bizAttendBatch:view")
	@RequestMapping(value = "list")
	public String list(@ModelAttribute BizAttendBatch bizAttendBatch, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		Page<BizAttendBatch> page = bizAttendBatchService.findPage(new Page<BizAttendBatch>(request, response),
				bizAttendBatch);
		model.addAttribute("page", page);
		return "modules/attend/bizAttendBatchList";
	}


	@RequiresPermissions("bizAttend:bizAttendBatch:edit")
	@RequestMapping(value = "updateStatus")
	public String updateStatus(String status, Integer id, String remarks) {

		if (id != null) {
			BizAttendBatch bizAttendBatch = bizAttendBatchService.get(id);

			BizAttendBatch bizAttendBatch2 = new BizAttendBatch();
			bizAttendBatch2.setStatus(status);
			bizAttendBatch2.setStatusDatetime(new Date());
			bizAttendBatch2.setId(bizAttendBatch.getId());

			bizAttendBatchService.save(bizAttendBatch2);
		}

		return "redirect:" + Global.getAdminPath() + "/bizAttend/bizAttendBatch/list?repage";
	}

	@RequiresPermissions("bizAttend:bizAttendBatch:edit")
	@RequestMapping(value = "invalid")
	@ResponseBody
	public String invalid(String status, Integer id, String remarks) {
		if (id != null) {

			BizAttendBatch bizAttendBatch = bizAttendBatchService.get(id);

			BizAttendBatch bizAttendBatch2 = new BizAttendBatch();
			bizAttendBatch2.setRemarks(remarks);
			bizAttendBatch2.setStatus(status);
			bizAttendBatch2.setStatusDatetime(new Date());
			bizAttendBatch2.setId(bizAttendBatch.getId());

			bizAttendBatchService.save(bizAttendBatch2);


			List<BizAttendBill> list = bizAttendBillService.findBizAttendBillListByBatchId(id);
			bizAttendBillService.updateBatchIds(list);
			return "1";
		}
		return "0";
	}


	@RequiresPermissions("bizAttend:bizAttendBatch:edit")
	@RequestMapping(value = "view")
	public String view(Integer id, Model model) {
		List<BizAttendBill> billList = bizAttendBillService.findBizAttendBillListByBatchId(id);
		model.addAttribute("billList", billList);
		return "modules/attend/bizAttendBatchView";
	}


	@RequiresPermissions("bizAttend:bizAttendBatch:edit")
	@RequestMapping(value = "exportExcel")
	public void exportExcel(Integer id, HttpServletResponse response) {

		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		HSSFWorkbook excel = bizAttendBatchService.exportExcel(id);
		ServletOutputStream out = null;
		try {
			response.setContentType("application/binary;charset=utf-8");
			String headerStr = new String(("项目经理考勤" + sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");
			response.setHeader("Content-disposition", "attachment; filename=" + headerStr + ".xls");
			out = response.getOutputStream();
			excel.write(out);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
