package cn.damei.web.modules;
import cn.mdni.commons.excel.export.ExportSingleSheetHelper;
import cn.mdni.commons.file.UploadCategory;
import cn.mdni.commons.view.ViewDownLoad;
import com.google.common.collect.Maps;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.ExportEvalInfo;
import cn.damei.service.modules.ExportEvalInfoService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 评价分数导出Controller
 * Created by hyh on 2017/11/29.
 */
@Controller
@RequestMapping(value="${adminPath}/evaluate/exportEvalInfo")
public class ExportEvalInfoController {

    @Autowired
    private ExportEvalInfoService exportEvalInfoService;
    @Autowired
    private BizEmployeeService2 bizEmployeeService2;

    /**
     * 打开评价分数导出页面
     * @param exportEvalInfo
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/openExportEvalInfo")
    public String openExportEvalInfo(ExportEvalInfo exportEvalInfo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        User user = UserUtils.getUser();
        //过滤门店
        if(null==exportEvalInfo.getStoreId()){
            if(null!=user.getStoreId()){
                exportEvalInfo.setStoreId(user.getStoreId());
            }
        }
        if(StringUtils.isBlank(user.getStoreId())){
            model.addAttribute("storeDropEnable", true);
        }
        //过滤工程模式
        if(StringUtils.isBlank(exportEvalInfo.getProjectMode())){
            if(null != user.getEmpId()){
                BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
                if(StringUtils.isBlank(be.getProjectMode())){
                    model.addAttribute("gongcheng", true);
                }else{
                    if(be.getProjectMode().equals("3")){
                        model.addAttribute("gongcheng", true);
                    }else{
                        exportEvalInfo.setProjectMode(be.getProjectMode());
                    }
                }
            }else{
                model.addAttribute("gongcheng", true);
            }
        }else{
            if(null != user.getEmpId()){
                BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
                if(StringUtils.isBlank(be.getProjectMode())){
                    model.addAttribute("gongcheng", true);
                }else{
                    if(be.getProjectMode().equals("3")){
                        model.addAttribute("gongcheng", true);
                    }else{
                        exportEvalInfo.setProjectMode(be.getProjectMode());
                    }
                }
            }else{
                model.addAttribute("gongcheng", true);
            }
        }
        Calendar calendar = Calendar.getInstance();
        exportEvalInfo.setEndDate(calendar.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        exportEvalInfo.setStartDate(calendar.getTime());
        return "modules/export/exportEvalInfo";
    }

    /**
     * 导出评价分数
     * @param exportEvalInfo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportEvalInfo")
    public ModelAndView exportEvalInfo(String storeId,String projectMode, String startDate,String endDate,String evalTargetType,HttpServletRequest request, HttpServletResponse response) throws Exception{

        UploadCategory uploadCategory = UploadCategory.EXCLE;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = "评价信息"+startDate+"至"+endDate+".xls";
        String root = request.getSession().getServletContext().getRealPath("/");
        String uploadUrl = root+PicRootName.getConfigValue(ConstantUtils.UPLOAD);
        String filePath = cn.mdni.commons.file.FileUtils.saveFilePath(uploadCategory, uploadUrl, fileName);

        ExportEvalInfo exportEvalInfo = new ExportEvalInfo();
        exportEvalInfo.setStoreId(storeId);
        exportEvalInfo.setProjectMode(projectMode);
        exportEvalInfo.setStartDate(sdf.parse(startDate));
        exportEvalInfo.setEndDate(sdf.parse(endDate));
        exportEvalInfo.setEvalTargetType(evalTargetType);
        //表头
        LinkedHashMap<String, String> headerMapper = Maps.newLinkedHashMap();
        headerMapper.put("storeName", "门店");
        headerMapper.put("projectModeName", "工程模式");
        headerMapper.put("enginDepartName", "区域");
        headerMapper.put("gradeDate", "评价时间");
        headerMapper.put("groupRealName", "工人姓名");
        headerMapper.put("customerMessage", "客户信息");
        headerMapper.put("customerName", "客户");
        headerMapper.put("packageName", "任务包名称");
        headerMapper.put("evalRoleTypeName", "评价类型");
        headerMapper.put("indexName", "评价指标");
        headerMapper.put("gradtotalScore", "评价分数");
        headerMapper.put("evaltotalScore", "评价总分");

        List<ExportEvalInfo> list = exportEvalInfoService.findList(exportEvalInfo);

        ExportSingleSheetHelper exportSingleSheetHelper = new ExportSingleSheetHelper(filePath, headerMapper, list);
        exportSingleSheetHelper.build();

        ViewDownLoad viewDownLoad = new ViewDownLoad(new File(filePath),null);
        return new ModelAndView(viewDownLoad);
    }
}
