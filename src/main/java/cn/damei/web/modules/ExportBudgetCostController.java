package cn.damei.web.modules;

import cn.mdni.commons.excel.export.ExportSingleSheetHelper;
import cn.mdni.commons.file.UploadCategory;
import cn.mdni.commons.view.ViewDownLoad;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.ExportBudgetCost;
import cn.damei.service.modules.ExportBudgetCostService;
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
import java.util.Calendar;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="${adminPath}/export/ExportBudgetCost")
public class ExportBudgetCostController {
    @Autowired
    private ExportBudgetCostService exportBudgetCostService;

    @Autowired
    private BizEmployeeService2 bizEmployeeService2;


    @RequestMapping(value = "/openExportBudgetCost")
    public String openExportBudgetCost(ExportBudgetCost exportBudgetCost, HttpServletRequest request, HttpServletResponse response, Model model){
        User user = UserUtils.getUser();

        if(null==exportBudgetCost.getStoreId()){
            if(null!=user.getStoreId()){
                exportBudgetCost.setStoreId(user.getStoreId());
            }
        }
        if(StringUtils.isBlank(user.getStoreId())){
            model.addAttribute("storeDropEnable", true);
        }

        if(StringUtils.isBlank(exportBudgetCost.getProjectMode())){
            if(null != user.getEmpId()){
                BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
                if(StringUtils.isBlank(be.getProjectMode())){
                    model.addAttribute("gongcheng", true);
                }else{
                    if(be.getProjectMode().equals("3")){
                        model.addAttribute("gongcheng", true);
                    }else{
                        exportBudgetCost.setProjectMode(be.getProjectMode());
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
                        exportBudgetCost.setProjectMode(be.getProjectMode());
                    }
                }
            }else{
                model.addAttribute("gongcheng", true);
            }
        }
        Calendar calendar = Calendar.getInstance();
        exportBudgetCost.setEndDate(calendar.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        exportBudgetCost.setStartDate(calendar.getTime());
        return "modules/export/exportBudgetCost";
    }
    @RequestMapping(value = "/exportBudgetCost")
    public ModelAndView exportBudgetCost(String storeId, String projectMode, String startDate, String endDate, HttpServletRequest request, HttpServletResponse response) throws Exception{
        UploadCategory uploadCategory = UploadCategory.EXCLE;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = "资金预算表"+startDate+"至"+endDate+".xls";
        String root = request.getSession().getServletContext().getRealPath("/");
        String uploadUrl = root+ PicRootName.getConfigValue(ConstantUtils.UPLOAD);
        String filePath = cn.mdni.commons.file.FileUtils.saveFilePath(uploadCategory, uploadUrl, fileName);
        ExportBudgetCost exportBudgetCost = new ExportBudgetCost();
        exportBudgetCost.setStoreId(storeId);
        exportBudgetCost.setProjectMode(projectMode);
        exportBudgetCost.setStartDate(sdf.parse(startDate));
        exportBudgetCost.setEndDate(sdf.parse(endDate));
        List<ExportBudgetCost> list = exportBudgetCostService.findList(exportBudgetCost);

        ExportSingleSheetHelper<Map<String, Object>> ex = new ExportSingleSheetHelper<Map<String, Object>>(filePath);
        ex.setColSpanTarget("$");
        ex.darwRowColSpan(0,new String[]{"$","$","$","项目基本信息","$","$","$","实际成本合计","$","$","$","已支付成本","$","$","$","未付金额","$","$","$","本月预算"});
        ex.darwRowColSpan(1,new String[]{"订单编号","客户姓名","转施工日期","完工日期","材料","施工费","工程管理费","总成本","材料","施工费","工程管理费","总成本","材料","施工费","工程管理费","未支付合计","材料","施工费","工程管理费","未支付合计"});
         int index = 2;
        for(ExportBudgetCost info : list){
           if(info != null){
               ex.darwRowColSpan(index,new String[]{info.getOrderNumber(),info.getCustomerName(),sdf.format(info.getGetOrderDatetime()),
                       sdf.format(info.getActualEndDate()),String.valueOf(info.getActualMaterialsAmount()),String.valueOf(info.getActualConstructionAmount()),
                       String.valueOf(info.getActualPmAmount()),"",String.valueOf(info.getPaidMaterialsAmount()),String.valueOf(info.getPaidConstructionAmount()),
                       String.valueOf(info.getPaidPmAmount()),"","","","","","","","",""});
           }
            index++;
        }
        ex.build(true);
        ViewDownLoad viewDownLoad = new ViewDownLoad(new File(filePath),null);
        return new ModelAndView(viewDownLoad);
    }
}
