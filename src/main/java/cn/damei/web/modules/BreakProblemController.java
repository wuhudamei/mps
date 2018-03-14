/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mdni.commons.excel.export.ExportSingleSheetHelper;
import cn.mdni.commons.file.UploadCategory;
import cn.mdni.commons.view.ViewDownLoad;
import com.google.common.collect.Maps;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.PicRootName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BreakProblem;
import cn.damei.service.modules.BreakProblemService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 违规问题统计Controller
 * @author wyb
 * @version 2016-10-31
 */
@Controller
@RequestMapping(value = "${adminPath}/pqc/breakProblem/breakProblem")
public class BreakProblemController extends BaseController {

	@Autowired
	private BreakProblemService breakProblemService;
	
	@ModelAttribute
	public BreakProblem get(@RequestParam(required=false) String id) {
		BreakProblem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = breakProblemService.get(id);
		}
		if (entity == null){
			entity = new BreakProblem();
		}
		return entity;
	}
	
	@RequiresPermissions("breakProblem:breakProblem:view")
	@RequestMapping(value = {"list", ""})
	public String list(BreakProblem breakProblem, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==breakProblem.getStoreId()){
			if(null!=user.getStoreId()){
				breakProblem.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(breakProblem.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				breakProblem.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				breakProblem.setProjectMode(user.getProjectMode());
			}
		}
		
		return "modules/PQC/breakProblem/breakProblemList";
	}
	@RequiresPermissions("breakProblem:breakProblem:view")
	@RequestMapping(value = {"breakProblemList", ""})
	public String breakProblemList(BreakProblem breakProblem, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==breakProblem.getStoreId()){
			if(null!=user.getStoreId()){
				breakProblem.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(breakProblem.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				breakProblem.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				breakProblem.setProjectMode(user.getProjectMode());
			}
		}
		
		Page<BreakProblem> page = breakProblemService.findPage(new Page<BreakProblem>(request, response), breakProblem); 
		model.addAttribute("page", page);
		return "modules/PQC/breakProblem/breakProblemList";
	}

	@RequiresPermissions("breakProblem:breakProblem:view")
	@RequestMapping(value = {"breakProblemDetails", ""})
	public String breakProblemDetails(BreakProblem breakProblem, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();
		//过滤门店
		if(null==breakProblem.getStoreId()){
			if(null!=user.getStoreId()){
				breakProblem.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(breakProblem.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				breakProblem.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				breakProblem.setProjectMode(user.getProjectMode());
			}
		}

		Page<BreakProblem> page = breakProblemService.findPage(new Page<BreakProblem>(request, response), breakProblem);
		if(null!=page.getList()){
			model.addAttribute("breakProblemZhu", page.getList().get(0));
		}


		Page<BreakProblem> breakProblemList = breakProblemService.queryBreakProblemList(new Page<BreakProblem>(request, response),breakProblem);
		model.addAttribute("page", breakProblemList);
		model.addAttribute("breakProblem", breakProblem);
		return "modules/PQC/breakProblem/breakProblemDetails";
	}

    /**
     * 导出违规问题统计
     * @param breakProblem
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
	@RequiresPermissions("breakProblem:breakProblem:view")
	@RequestMapping(value = "searchFormEx")
	public ModelAndView searchFormEx(BreakProblem breakProblem, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

		User user = UserUtils.getUser();
		//过滤门店
		if(null==breakProblem.getStoreId()){
			if(null!=user.getStoreId()){
				breakProblem.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(breakProblem.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				breakProblem.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				breakProblem.setProjectMode(user.getProjectMode());
			}
		}

		UploadCategory uploadCategory = UploadCategory.EXCLE;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = "违规问题统计"+ DateUtils.getDateTime1()+".xls";
		String root = request.getSession().getServletContext().getRealPath("/");
		String uploadUrl = root+PicRootName.getConfigValue(ConstantUtils.UPLOAD);
		String filePath = cn.mdni.commons.file.FileUtils.saveFilePath(uploadCategory, uploadUrl, fileName);


		//表头
		LinkedHashMap<String, String> headerMapper = Maps.newLinkedHashMap();
		headerMapper.put("storeName", "门店");
		headerMapper.put("projectModeName", "工程模式");
		headerMapper.put("qcCheckKindName", "检查分类");
		headerMapper.put("qcCheckItemName","检查项");
		headerMapper.put("breakDescribe", "违规形式");
		headerMapper.put("breakTimes", "出现次数");

		List<BreakProblem> list = breakProblemService.findList(breakProblem);

		ExportSingleSheetHelper exportSingleSheetHelper = new ExportSingleSheetHelper(filePath, headerMapper, list);
		exportSingleSheetHelper.build();

		ViewDownLoad viewDownLoad = new ViewDownLoad(new File(filePath),null);
		model.addAttribute("breakProblem", breakProblem);
		return new ModelAndView(viewDownLoad);
	}

	@RequestMapping(value = "exportDetailed")
	public ModelAndView exportDetailed(BreakProblem breakProblem, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

		User user = UserUtils.getUser();
		//过滤门店
		if(null==breakProblem.getStoreId()){
			if(null!=user.getStoreId()){
				breakProblem.setStoreId(user.getStoreId());
			}
		}
		//过滤工程模式
        if(StringUtils.isNotBlank(user.getProjectMode()) && !user.getProjectMode().equals("3")){
            breakProblem.setProjectMode(user.getProjectMode());
        }

		UploadCategory uploadCategory = UploadCategory.EXCLE;
		String fileName = "违规问题统计明细"+ DateUtils.getDateTime1()+".xls";
		String root = request.getSession().getServletContext().getRealPath("/");
		String uploadUrl = root+PicRootName.getConfigValue(ConstantUtils.UPLOAD);
		String filePath = cn.mdni.commons.file.FileUtils.saveFilePath(uploadCategory, uploadUrl, fileName);


		Page<BreakProblem> page = breakProblemService.findPage(new Page<BreakProblem>(request, response), breakProblem);
        BreakProblem breakProblemzuzhuang=null;
		if(null!=page.getList()){
			model.addAttribute("breakProblemZhu", page.getList().get(0));
            breakProblemzuzhuang = page.getList().get(0);
        }


		List<BreakProblem> list = breakProblemService.queryBreakProblemListbreakProblem(breakProblem,breakProblemzuzhuang);

        ExportSingleSheetHelper<Map<String, Object>> ex = new ExportSingleSheetHelper<Map<String, Object>>(filePath);
        ex.setColSpanTarget("$");
        ex.darwRowColSpan(0,new String[]{"门店","$","$","$","$","$","$","$","$",breakProblemzuzhuang.getStoreName()});
        ex.darwRowColSpan(1,new String[]{"检查项分类","$","$","$","$","$","$","$","$",breakProblemzuzhuang.getQcCheckKindName()});
        ex.darwRowColSpan(2,new String[]{"检查项","$","$","$","$","$","$","$","$",breakProblemzuzhuang.getQcCheckItemName()});
        ex.darwRowColSpan(3,new String[]{"违规形式","$","$","$","$","$","$","$","$",breakProblemzuzhuang.getBreakDescribe()});
        ex.darwRowColSpan(4,new String[]{"序号","质检员提交报告时间","工程模式","区域","小区","客户","项目经理","质检员","责任-项目经理","责任-工人组长"});

        int index = 5;
        int num = 1;
        for(BreakProblem info : list){
            if(info != null&&null!=info.getCustomerName()){
                //序号
                String numIndex = String.valueOf(num);
                //质检员提交报告时间
                String quCreateDateString = info.getQuCreateDateString();
                //门店
               // String storeName = info.getStoreName();
                //工程模式
                String projectModeName = info.getProjectModeName();
                //区域
                String enginDepartName = info.getEnginDepartName();
                //小区
                String customerAddr = info.getCustomerAddr();
                //客户
                String customerName = info.getCustomerName();
                //项目经理
                String itemManager = info.getItemManager();
                //质检员
                String orderInspector = info.getOrderInspector();
                //责任-项目经理
                String mnagerPerson = (null==info.getMnagerPerson())?"":info.getMnagerPerson();
                //责任-工人组长
                String workerGroupName = (null==info.getWorkerGroupName())?"":info.getWorkerGroupName();

                ex.darwRowColSpan(index,new String[]{numIndex,quCreateDateString,projectModeName,enginDepartName,customerAddr,customerName,itemManager,orderInspector,mnagerPerson,workerGroupName});
            }
            index++;
            num++;
        }

        ex.build(true);
        ViewDownLoad viewDownLoad = new ViewDownLoad(new File(filePath),null);
		return new ModelAndView(viewDownLoad);
	}




	
	

}