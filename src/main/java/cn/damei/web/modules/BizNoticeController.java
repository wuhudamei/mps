
package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.common.utils.DateUtils;
import org.apache.commons.lang.StringEscapeUtils;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizNoticeVo;
import cn.damei.service.modules.BizNoticeReceiverService;
import cn.damei.entity.modules.BizNoticeViewLog;
import cn.damei.entity.modules.BizNoticeViewLog2;
import cn.damei.service.modules.BizNoticeViewLogService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

import net.sf.json.JSONArray;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizNotice;
import cn.damei.service.modules.BizNoticeService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;


@Controller
@RequestMapping(value = "${adminPath}/notice/bizNotice")
public class BizNoticeController extends BaseController {

	@Autowired
	private BizNoticeService bizNoticeService;

	@Autowired
	private BizNoticeReceiverService bizNoticeReceiverService;

	@Autowired
	private BizNoticeViewLogService bizNoticeViewLogService;

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@ModelAttribute
	public BizNotice get(@RequestParam(required=false) Integer id) {
		BizNotice entity = null;
		if (id != null){
			entity = bizNoticeService.get(id);
		}
		if (entity == null){
			entity = new BizNotice();
		}
		return entity;
	}
	
	@RequiresPermissions("notice:bizNotice:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizNoticeVo bizNoticeVo, Model model) {

		if(bizNoticeVo.getStoreId() == null){
			String storeId = UserUtils.getUser().getStoreId();
			if(StringUtils.isBlank(storeId)){
				bizNoticeVo.setStoreId(null);
			}else{
				bizNoticeVo.setStoreId(Integer.parseInt(storeId));
			}
		}
		if(org.apache.commons.lang3.StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}


		User user = UserUtils.getUser();
		if(StringUtils.isNoneBlank(user.getEmpId())){
			BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
			if(ConstantUtils.PROJECT_MODE_1.equals(be.getProjectMode()) || ConstantUtils.PROJECT_MODE_2.equals(be.getProjectMode())){
				bizNoticeVo.setProjectMode(be.getProjectMode());
				model.addAttribute("projectModeEnable", true);
			}else{
				bizNoticeVo.setProjectMode(be.getProjectMode());
			}
		}

		return "modules/notice/bizNoticeList";
	}

	@RequiresPermissions("notice:bizNotice:view")
	@RequestMapping(value = {"loadList", ""})
	public String loadList(BizNoticeVo bizNoticeVo, HttpServletRequest request, HttpServletResponse response, Model model) {

		if(bizNoticeVo.getStoreId() == null){
			String storeId = UserUtils.getUser().getStoreId();
			if(StringUtils.isBlank(storeId)){
				bizNoticeVo.setStoreId(null);
			}else{
				bizNoticeVo.setStoreId(Integer.parseInt(storeId));
			}
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}


		User user = UserUtils.getUser();
		if(StringUtils.isNoneBlank(user.getEmpId())){
			BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
			if(ConstantUtils.PROJECT_MODE_1.equals(be.getProjectMode()) || ConstantUtils.PROJECT_MODE_2.equals(be.getProjectMode())){
				bizNoticeVo.setProjectMode(be.getProjectMode());
				model.addAttribute("projectModeEnable", true);
			}else{

			}
		}

		bizNoticeVo.setNowDate(new Date());
		Page<BizNoticeVo> page = bizNoticeService.findNoticePage(new Page<BizNoticeVo>(request, response), bizNoticeVo);
		model.addAttribute("page", page);
		return "modules/notice/bizNoticeList";
	}

	@RequiresPermissions("notice:bizNotice:view")
	@RequestMapping(value = "form")
	public String form(BizNotice bizNotice, Model model) {

		if(bizNotice.getStoreId() == null){
			String storeId = UserUtils.getUser().getStoreId();
			if(StringUtils.isBlank(storeId)){
				bizNotice.setStoreId(null);
			}else{
				bizNotice.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		if(bizNotice.getId() != null){
			bizNotice = bizNoticeService.queryNoticeById(bizNotice.getId());
			if(bizNotice != null){
				List<DropModel> employeeList = bizNoticeReceiverService.queryNoticeReceiverEmployee(bizNotice.getId());
				model.addAttribute("employeeList", employeeList);
			}
		}


		User user = UserUtils.getUser();
		if(StringUtils.isNoneBlank(user.getEmpId())){
			BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
			if(ConstantUtils.PROJECT_MODE_1.equals(be.getProjectMode()) || ConstantUtils.PROJECT_MODE_2.equals(be.getProjectMode())){
				bizNotice.setProjectMode(be.getProjectMode());
				model.addAttribute("projectModeEnable", true);
			}else{
				bizNotice.setProjectMode(be.getProjectMode());
			}
		}

		model.addAttribute("bizNotice", bizNotice);
		return "modules/notice/bizNoticeForm";
	}

	@RequiresPermissions("notice:bizNotice:edit")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(BizNotice bizNotice, RedirectAttributes redirectAttributes) {
		bizNoticeService.save(bizNotice);
		addMessage(redirectAttributes, "保存公告成功");
		return "redirect:"+Global.getAdminPath()+"/notice/bizNotice/loadList?repage";
	}
	
	@RequiresPermissions("notice:bizNotice:edit")
	@RequestMapping(value = "delete")
	public String delete(Integer id, RedirectAttributes redirectAttributes) {
		bizNoticeService.delete(id);
		addMessage(redirectAttributes, "删除公告成功");
		return "redirect:"+Global.getAdminPath()+"/notice/bizNotice/loadList?repage";
	}

	@RequiresPermissions("notice:bizNotice:edit")
	@RequestMapping(value = "revoked")
	public String revoked(Integer id, RedirectAttributes redirectAttributes) {
		bizNoticeService.revoked(id);
		addMessage(redirectAttributes, "删除公告成功");
		return "redirect:"+Global.getAdminPath()+"/notice/bizNotice/loadList?repage";
	}

	@RequestMapping(value = "detail")
	public String detail(Integer id, Model model) {
		BizNotice bizNotice = bizNoticeService.get(id);
		bizNotice.setNoticeContent(StringEscapeUtils.unescapeHtml(bizNotice.getNoticeContent()));
		model.addAttribute("bizNotice", bizNotice);
		return "modules/notice/bizNoticeDetail";
	}

	@RequestMapping(value = "yesRead")
	public String yesRead(Integer id, Model model) {
		List<BizNoticeViewLog> bizNoticeViewLogList = bizNoticeViewLogService.queryNoticeViewLogByNoticeId(id);
		model.addAttribute("bizNoticeViewLogList", bizNoticeViewLogList);
		return "modules/notice/bizNoticeYesReadList";
	}

	@RequestMapping(value = "noRead")
	public String noRead(Integer id, Model model) {
		List<String> list = bizNoticeReceiverService.queryNoticeReceiverByNoticeId(id);
		model.addAttribute("list", list);
		return "modules/notice/bizNoticeNoReadList";
	}
	@RequestMapping(value="noReadAndyesRead")
	public String noReadAndyesRead(Integer id, Model model){


		String sum = bizNoticeViewLogService.findNoticeSum(id);

		String yessum = bizNoticeViewLogService.findYesNoticeSum(id);
		model.addAttribute("yesRead", yessum);
		model.addAttribute("noRead", sum);
		model.addAttribute("id", id);
		return "modules/notice/bizNoticeYesReadList";
	}

	@RequestMapping(value="ajaxData")
	public @ResponseBody String ajaxData(String flag,Integer id){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		

		if("1".equals(flag)){
			List<BizNoticeViewLog> yesRead = bizNoticeViewLogService.queryNoticeViewLogByNoticeId(id);
			List<BizNoticeViewLog2> yesRead2 = new ArrayList<>();
			for (BizNoticeViewLog bizNoticeViewLog : yesRead) {
				BizNoticeViewLog2 bv = new BizNoticeViewLog2();
				bv.setRealName(bizNoticeViewLog.getRealName());
				bv.setViewDatetime(sf.format(bizNoticeViewLog.getViewDatetime()));
				yesRead2.add(bv);
			}
			
			List<BizNoticeViewLog2> noRead = new ArrayList<>();
			List<String> list = bizNoticeReceiverService.queryNoticeReceiverByNoticeId(id);
			for (String string : list) {
				BizNoticeViewLog2 bn = new BizNoticeViewLog2();
				bn.setRealName(string);
				noRead.add(bn);
			}
			noRead.addAll(yesRead2);
			String string = JSONArray.fromObject(noRead).toString();
			return string;
		}
		if("2".equals(flag)){
			List<BizNoticeViewLog2> noRead = new ArrayList<>();
			List<String> list = bizNoticeReceiverService.queryNoticeReceiverByNoticeId(id);
			for (String string : list) {
				BizNoticeViewLog2 bn = new BizNoticeViewLog2();
				bn.setRealName(string);
				noRead.add(bn);
			}
			String string = JSONArray.fromObject(noRead).toString();
			return string;
		}
		if("3".equals(flag)){
			List<BizNoticeViewLog> bizNoticeViewLogList = bizNoticeViewLogService.queryNoticeViewLogByNoticeId(id);
			List<BizNoticeViewLog2> yesRead2 = new ArrayList<>();
			for (BizNoticeViewLog bizNoticeViewLog : bizNoticeViewLogList) {
				BizNoticeViewLog2 bv = new BizNoticeViewLog2();
				bv.setRealName(bizNoticeViewLog.getRealName());
				bv.setViewDatetime(sf.format(bizNoticeViewLog.getViewDatetime()));
				yesRead2.add(bv);
			}
			String string = JSONArray.fromObject(yesRead2).toString();
			return string;
		}
		return null;
	}
	
	
	

	@RequestMapping(value = "/uploadFile1")
	public void uploadFile(HttpServletRequest request, HttpServletResponse response) {
		BufferedOutputStream bos = null;

		try{
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			MultipartFile mulFile = multipartHttpServletRequest.getFile("upload");
			Properties props = new Properties();
			props.load(BizNoticeController.class.getClassLoader().getResourceAsStream("config.properties"));
			String picUrlBase = props.getProperty("app_pic_url_base");

			String date = DateUtils.getDateTime1();
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");

			File filePath = new File(request.getSession().getServletContext().getRealPath("") + "/upload/pic/pc/ueditor/notice/" + date);
			if(!filePath.exists() && !filePath.isDirectory()){
				filePath.mkdirs();
			}
			String url = request.getSession().getServletContext().getRealPath("") + ConstantUtils.UPLOAD_CKEDITOR + date +"/"+uuid + ".jpeg";
			bos = new BufferedOutputStream(new FileOutputStream(new File(url)));
			bos.write(mulFile.getBytes());
			String callback =request.getParameter("CKEditorFuncNum");
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/JavaScript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction("+ callback + ",'" + picUrlBase + ConstantUtils.UPLOAD_CKEDITOR + date +"/" + uuid + ".jpeg" + "','')");
			out.println("</script>");
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try{
				bos.flush();
				bos.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}

	}
}