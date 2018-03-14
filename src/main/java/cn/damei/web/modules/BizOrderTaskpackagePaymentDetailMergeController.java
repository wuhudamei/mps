
package cn.damei.web.modules;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import cn.damei.common.config.Global;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.ExportSWIFTExcel;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentSummary;
import cn.damei.service.modules.BizOrderTaskpackagePaymentSummaryService;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailMerge;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailMergeTxtVo;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailMergeVo;
import cn.damei.service.modules.BizOrderTaskpackagePaymentDetailMergeService;


@Controller
@RequestMapping(value = "${adminPath}/ordertaskpaymentdetailmerge/bizOrderTaskpackagePaymentDetailMerge")
public class BizOrderTaskpackagePaymentDetailMergeController extends BaseController {

	@Autowired
	private BizOrderTaskpackagePaymentDetailMergeService bizOrderTaskpackagePaymentDetailMergeService;
	@Autowired
	private BizOrderTaskpackagePaymentSummaryService bizOrderTaskpackagePaymentSummaryService;
	
	@ModelAttribute
	public BizOrderTaskpackagePaymentDetailMerge get(@RequestParam(required=false) Integer id) {
		BizOrderTaskpackagePaymentDetailMerge entity = null;
		if (id != null){
			entity = bizOrderTaskpackagePaymentDetailMergeService.get(id);
		}
		if (entity == null){
			entity = new BizOrderTaskpackagePaymentDetailMerge();
		}
		return entity;
	}
	
	
	
	
	
	@RequestMapping(value = "checkPaymentSummary")
	public @ResponseBody String checkPaymentSummary(Integer id){
	   String result="0";	
	   BizOrderTaskpackagePaymentSummary bizOrderTaskpackagePaymentSummary =  bizOrderTaskpackagePaymentSummaryService.get(id);
	   if(bizOrderTaskpackagePaymentSummary.getStatus().equals(ConstantUtils.SUMMARY_STATUS_100)){
		   result="1";
	   }
	   return result;
	}
	
	@RequiresPermissions("ordertaskpaymentdetailmerge:bizOrderTaskpackagePaymentDetailMerge:view")
	@RequestMapping(value = {"list", ""})
	public String list(Integer summaryId, Model model) {
		List<BizOrderTaskpackagePaymentDetailMergeVo> list = bizOrderTaskpackagePaymentDetailMergeService.queryPaymentDetailMergeBySummaryId(summaryId);
		model.addAttribute("list", list);
		model.addAttribute("summaryId", summaryId);
		return "modules/ordertaskpaymentdetailmerge/bizOrderTaskpackagePaymentDetailMergeList";
	}

	@RequiresPermissions("ordertaskpaymentdetailmerge:bizOrderTaskpackagePaymentDetailMerge:view")
	@RequestMapping(value = "form")
	public String form(BizOrderTaskpackagePaymentDetailMerge bizOrderTaskpackagePaymentDetailMerge, Model model) {
		model.addAttribute("bizOrderTaskpackagePaymentDetailMerge", bizOrderTaskpackagePaymentDetailMerge);
		return "modules/ordertaskpaymentdetailmerge/bizOrderTaskpackagePaymentDetailMergeForm";
	}

	@RequiresPermissions("ordertaskpaymentdetailmerge:bizOrderTaskpackagePaymentDetailMerge:edit")
	@RequestMapping(value = "save")
	public String save(BizOrderTaskpackagePaymentDetailMerge bizOrderTaskpackagePaymentDetailMerge, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizOrderTaskpackagePaymentDetailMerge)){
			return form(bizOrderTaskpackagePaymentDetailMerge, model);
		}
		bizOrderTaskpackagePaymentDetailMergeService.save(bizOrderTaskpackagePaymentDetailMerge);
		addMessage(redirectAttributes, "保存付款单明细合并成功");
		return "redirect:"+Global.getAdminPath()+"/ordertaskpaymentdetailmerge/bizOrderTaskpackagePaymentDetailMerge/?repage";
	}
	
	@RequiresPermissions("ordertaskpaymentdetailmerge:bizOrderTaskpackagePaymentDetailMerge:edit")
	@RequestMapping(value = "delete")
	public String delete(BizOrderTaskpackagePaymentDetailMerge bizOrderTaskpackagePaymentDetailMerge, RedirectAttributes redirectAttributes) {
		bizOrderTaskpackagePaymentDetailMergeService.delete(bizOrderTaskpackagePaymentDetailMerge);
		addMessage(redirectAttributes, "删除付款单明细合并成功");
		return "redirect:"+Global.getAdminPath()+"/ordertaskpaymentdetailmerge/bizOrderTaskpackagePaymentDetailMerge/?repage";
	}
	
	@RequiresPermissions("ordertaskpaymentdetailmerge:bizOrderTaskpackagePaymentDetailMerge:edit")
	@RequestMapping(value = "confirmPayment")
	public String confirmPayment(Integer[] ids, Integer summaryId, RedirectAttributes redirectAttributes) {
		bizOrderTaskpackagePaymentDetailMergeService.confirmPayment(summaryId, ids);
		addMessage(redirectAttributes, "确认已付款成功");
		return "redirect:"+Global.getAdminPath()+"/ordertaskpaymentdetailmerge/bizOrderTaskpackagePaymentDetailMerge/list?summaryId="+summaryId;
	}
	
	@RequestMapping(value="exportTxt")
	public void exportProjectExcel(Integer summaryId, HttpServletResponse response){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		String txt = bizOrderTaskpackagePaymentDetailMergeService.queryPaymentDetailMergeForTxt(summaryId);
		ServletOutputStream out = null;
		try {  
			response.setContentType("application/binary;charset=utf-8"); 
			String headerStr =new String(("导出徽商银行txt格式文件"+sf.format(new Date())).getBytes("gbk"), "ISO8859-1");
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".txt");
			out = response.getOutputStream();

			IOUtils.write(txt, out, "gbk");
		} catch (IOException ex) {  
            ex.printStackTrace();  
		}finally{
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value="exportExcel")
	public void exportExcel(Integer summaryId, HttpServletResponse response){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		HSSFWorkbook excel = bizOrderTaskpackagePaymentDetailMergeService.queryPaymentDetailMergeForExcel(summaryId);
		ServletOutputStream out= null;
		try {  
			response.setContentType("application/binary;charset=utf-8"); 
			String headerStr =new String(("导出中国银行excel格式文件"+sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");
			out = response.getOutputStream();    
			excel.write(out);
		} catch (IOException ex) {  
            ex.printStackTrace();  
		}finally{
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value="SWIFTExportExcel")
	public void SWIFTExportExcel(Integer summaryId,HttpServletResponse response){
		List<BizOrderTaskpackagePaymentDetailMergeTxtVo> list = bizOrderTaskpackagePaymentDetailMergeService.exportChinaCiticBank(summaryId);
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		ExportSWIFTExcel exp = new ExportSWIFTExcel();
		HSSFWorkbook excel = exp.ExportSWIFTExcel(list);

		ServletOutputStream out= null;
		try {  
			response.setContentType("application/binary;charset=utf-8"); 
			String headerStr =new String(("对外支付---支付").getBytes("utf-8"), "ISO8859-1");
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");
			out = response.getOutputStream();    
			excel.write(out);
		} catch (IOException ex) {  
            ex.printStackTrace();  
		}finally{
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
}