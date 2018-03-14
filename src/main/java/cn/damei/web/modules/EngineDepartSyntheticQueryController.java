package cn.damei.web.modules;

import cn.damei.common.constantUtils.OrderConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.EngineDepartEntity;
import cn.damei.entity.modules.EngineDepartSyntheticQueryEntity;
import cn.damei.service.modules.EngineDepartSyntheticQueryService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by joseph on 2017/4/7.
 * 后台工程部综合查询
 */
@Controller
@RequestMapping(value = "${adminPath}/engineDepartSyntheticQuery")
public class EngineDepartSyntheticQueryController extends BaseController {


    @Autowired
    private EngineDepartSyntheticQueryService service;

    @ModelAttribute
    public EngineDepartSyntheticQueryEntity get(@RequestParam(required = false) String id) {
        EngineDepartSyntheticQueryEntity entity = null;
        if (id != null) {
            entity = service.get(id);
        }
        if (entity == null) {
            entity = new EngineDepartSyntheticQueryEntity();
        }
        return entity;
    }

    @RequiresPermissions("enginedepartsyntheticquery:enginedepartsyntheticquery:view")
    @RequestMapping(value = {"prelist"})
    public String list(EngineDepartSyntheticQueryEntity engineDepartSyntheticQueryEntity, HttpServletRequest request, HttpServletResponse response, Model model) {

        return "modules/bizenginedepartsyntheticquery/bizenginedepartsyntheticquery";
    }

    @RequiresPermissions("enginedepartsyntheticquery:enginedepartsyntheticquery:view")
    @RequestMapping(value = {"list"})
    public String loadList(EngineDepartSyntheticQueryEntity engineDepartSyntheticQueryEntity, HttpServletRequest request, HttpServletResponse response, Model model) {

        if (null != engineDepartSyntheticQueryEntity.getQueryDate() && !engineDepartSyntheticQueryEntity.getQueryDate().trim().equals("")) {
            try {
                engineDepartSyntheticQueryEntity.setQueryDateForJsp(new SimpleDateFormat("yyyy-MM-dd").parse(engineDepartSyntheticQueryEntity.getQueryDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        engineDepartSyntheticQueryEntity.setOrderApplyCompleteStatus(OrderConstantUtil.ORDER_STATUS_APPLY_COMPLETION_300);
        engineDepartSyntheticQueryEntity.setOrderDiscloseStatus(OrderConstantUtil.ORDER_STATUS_DISCLOSE_130);
        engineDepartSyntheticQueryEntity.setOrderConfirmStartStatus(OrderConstantUtil.ORDER_STATUS_CONFIRM_START_200);
        engineDepartSyntheticQueryEntity.setOrderDistributeManagerStatus(OrderConstantUtil.ORDER_STATUS_DISTRIBUTE_MANAGER_120);
        engineDepartSyntheticQueryEntity.setOrderInspectionCheckPassStatus(OrderConstantUtil.ORDER_STATUS_INSPECTION_CHECK_PASS_320);
        Page<EngineDepartSyntheticQueryEntity> page = service.findPage(new Page<EngineDepartSyntheticQueryEntity>(request, response), engineDepartSyntheticQueryEntity);
        model.addAttribute("entity", engineDepartSyntheticQueryEntity);
        model.addAttribute("page", page);

        return "modules/bizenginedepartsyntheticquery/bizenginedepartsyntheticquery";
    }

    @RequestMapping(value = "exportInspectorFineMoneyDetailToExcel")
    public void exportInspectorFineMoneyDetailToExcel(HttpServletResponse response, EngineDepartSyntheticQueryEntity engineDepartSyntheticQueryEntity, HttpServletRequest request) {

        String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());


        HSSFWorkbook excel = service.exportExcel(engineDepartSyntheticQueryEntity);


        ServletOutputStream out = null;

        try {

            response.setContentType("application/binary;charset=UTF-8");
            String excelHead = new String(("工程部统计数据表-" + engineDepartSyntheticQueryEntity.getQueryDate()).getBytes(), "ISO8859-1");

            response.setHeader("Content-disposition", "attachment; filename=" + excelHead + ".xls");
            out = response.getOutputStream();
            excel.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(out != null){
                	out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }


    @RequiresPermissions("enginedepartsyntheticquery:enginedepartsyntheticquery:view")
    @RequestMapping(value = {"smallstatisticsPrelist"})
    public String smallstatisticsPrelist(EngineDepartSyntheticQueryEntity engineDepartSyntheticQueryEntity, HttpServletRequest request, HttpServletResponse response, Model model) {


        return "modules/bizenginedepartsyntheticquery/bizenginedepartsyntheticquery2";
    }

    @RequiresPermissions("enginedepartsyntheticquery:enginedepartsyntheticquery:view")
    @RequestMapping(value = {"smallStatisticsList"})
    public String smallStatisticsList(EngineDepartSyntheticQueryEntity engineDepartSyntheticQueryEntity, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
    	//把开始时间的时分秒初始化为 00：00：00 
    	//接单的时间的时分秒都是00:00:00
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String format = sdf.format(engineDepartSyntheticQueryEntity.getStart());
    	Date parse = sdf.parse(format);
        engineDepartSyntheticQueryEntity.setStartDate(DateUtils.formatDate(parse, "yyyy-MM-dd HH:mm:ss"));
        engineDepartSyntheticQueryEntity.setEndDate(DateUtils.formatDate(engineDepartSyntheticQueryEntity.getEnd(), "yyyy-MM-dd HH:mm:ss"));
        List<Map<String, Object>> list = service.selectEngineDepartSyntheticList(engineDepartSyntheticQueryEntity);
        model.addAttribute("list", list);
        model.addAttribute("entity", engineDepartSyntheticQueryEntity);

        return "modules/bizenginedepartsyntheticquery/bizenginedepartsyntheticquery2";
    }


    @RequestMapping(value = "exportExcel")
    public void exportExcel(HttpServletResponse response, EngineDepartSyntheticQueryEntity engineDepartSyntheticQueryEntity, HttpServletRequest request) {

        engineDepartSyntheticQueryEntity.setStartDate(DateUtils.formatDate(engineDepartSyntheticQueryEntity.getStart(), "yyyy-MM-dd HH:mm:ss"));
        engineDepartSyntheticQueryEntity.setEndDate(DateUtils.formatDate(engineDepartSyntheticQueryEntity.getEnd(), "yyyy-MM-dd HH:mm:ss"));

        HSSFWorkbook excel = service.exportExcel2(engineDepartSyntheticQueryEntity);


        ServletOutputStream out = null;

        try {

            response.setContentType("application/binary;charset=UTF-8");
            String excelHead = new String(("工程统计日报查询表-" + DateUtils.formatDate(engineDepartSyntheticQueryEntity.getStart(), "yyyy-MM-dd") + "-" + DateUtils.formatDate(engineDepartSyntheticQueryEntity.getEnd(), "yyyy-MM-dd")).getBytes(), "ISO8859-1");

            response.setHeader("Content-disposition", "attachment; filename=" + excelHead + ".xls");
            out = response.getOutputStream();
            excel.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
               if(out != null){
            	   out.flush();
                   out.close();
               }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }


    @RequiresPermissions("pqcSyntheticQuery:pqcSyntheticQuery:view")
    @RequestMapping(value = "pqcSyntheticQuery")
    public String pqcSyntheticQuery(EngineDepartSyntheticQueryEntity engineDepartSyntheticQueryEntity, HttpServletRequest request, HttpServletResponse response, Model model) {

        if (null != engineDepartSyntheticQueryEntity.getPqcQueryDate()) {
            engineDepartSyntheticQueryEntity.setQueryDate(DateUtils.formatDate(engineDepartSyntheticQueryEntity.getPqcQueryDate(), "yyyy-MM-dd"));

            model.addAttribute("entity", engineDepartSyntheticQueryEntity);
            model.addAttribute("list", service.pqcSyntheticQuery(engineDepartSyntheticQueryEntity));

        }


        return "modules/bizenginedepartsyntheticquery/pqcSyntheticQuery";
    }


    /**
     * 2017-08-17  质检各种数据查询
     *
     * @param storeId,projectMode,startDate,endDate
     * @return 综合经理星级数据
     */


    @SuppressWarnings("JavadocReference")
    @RequiresPermissions("managerStarSyntheticQueryPre:managerStarSyntheticQueryPre:view")
    @RequestMapping(value = "managerStarSyntheticQueryPre")
    public String managerStarSyntheticQueryPre(EngineDepartSyntheticQueryEntity engineDepartSyntheticQueryEntity, Model model) {

        return "syntheticQueryAbove/pqcAboveSyntheticQuery/managerStarSyntheticQuery";
    }
    /**
     * 2017-08-17  质检各种数据查询
     *
     * @param storeId,projectMode,startDate,endDate
     * @return 综合经理星级数据
     */


    /**
     * @param engineDepartSyntheticQueryEntity
     * @param model
     * @return
     */
    @SuppressWarnings("JavadocReference")
    @RequiresPermissions("managerStarSyntheticQueryPre:managerStarSyntheticQueryPre:view")
    @RequestMapping(value = "managerStarSyntheticQuery")
    public String managerStarSyntheticQuery(EngineDepartSyntheticQueryEntity engineDepartSyntheticQueryEntity, Model model) {

        //根据门店+工程模式 和时间段 查询质检工作的统计数据


        List<EngineDepartEntity> engineDepartEntities = service.findManagerStarGroupByEngineDepartByStoreIdAndProjectMode(engineDepartSyntheticQueryEntity.getStoreId().toString(), engineDepartSyntheticQueryEntity.getProjectMode());


        model.addAttribute("list", engineDepartEntities);
        return "syntheticQueryAbove/pqcAboveSyntheticQuery/managerStarSyntheticQuery";
    }


    /**
     * 根据门店和模式 查到大区及 大区下的工种信息
     *
     * @param engineDepartSyntheticQueryEntity
     * @param model
     * @return
     */
    @SuppressWarnings("JavadocReference")
    @RequiresPermissions("workerTypeInfoPre:workerTypeInfoPre:view")
    @RequestMapping(value = "workerTypeInfoPre")
    public String workerTypeInfoPre() {

        return "syntheticQueryAbove/pqcAboveSyntheticQuery/workerTypeCountSyntheticQuery";
    }

    /**
     * 根据门店和模式 查到大区及 大区下的工种信息
     *
     * @param engineDepartSyntheticQueryEntity
     * @param model
     * @return
     */
    @SuppressWarnings("JavadocReference")
    @RequiresPermissions("workerTypeInfoPre:workerTypeInfoPre:view")
    @RequestMapping(value = "workerTypeInfo")
    public String workerTypeInfo(EngineDepartSyntheticQueryEntity engineDepartSyntheticQueryEntity, Model model) {

        //根据门店+工程模式 和时间段 查询工人工种情况


        List<EngineDepartEntity> engineDepartEntities = service.findWorkerTypeGroupByEngineDepartByStoreIdAndProjectMode(engineDepartSyntheticQueryEntity.getStoreId().toString(), engineDepartSyntheticQueryEntity.getProjectMode());

        model.addAttribute("workerList", service.findWorkerTypeList());
        model.addAttribute("list", engineDepartEntities);
        return "syntheticQueryAbove/pqcAboveSyntheticQuery/workerTypeCountSyntheticQuery";
    }


    /**
     * 根据门店和模式 查到大区及 大区下的质检统计数据
     *
     * @param engineDepartSyntheticQueryEntity
     * @param model
     * @return
     */
    @SuppressWarnings("JavadocReference")
    @RequiresPermissions("pqcAboveSyntheticQueryPre:pqcAboveSyntheticQueryPre:view")
    @RequestMapping(value = "pqcAboveSyntheticQueryPre")
    public String pqcAboveSyntheticQueryPre() {

        return "syntheticQueryAbove/pqcAboveSyntheticQuery/pqcSyntheticAboveQuery";
    }

    /**
     * 根据门店和模式 查到大区及 大区下的质检统计数据
     *
     * @param engineDepartSyntheticQueryEntity
     * @param model
     * @return
     */
    @SuppressWarnings("JavadocReference")
    @RequiresPermissions("pqcAboveSyntheticQueryPre:pqcAboveSyntheticQueryPre:view")
    @RequestMapping(value = "pqcAboveSyntheticQuery")
    public String pqcAboveSyntheticQueryPre(EngineDepartSyntheticQueryEntity engineDepartSyntheticQueryEntity, Model model) {

        //根据门店+工程模式 和时间段 查询工人工种情况


       Map<String, Object> map = service.pqcSyntheticWorkQuery(engineDepartSyntheticQueryEntity);

        model.addAttribute("workerList", service.findWorkerTypeList());
        model.addAttribute("entity", engineDepartSyntheticQueryEntity);
        model.addAttribute("map", map);
        return "syntheticQueryAbove/pqcAboveSyntheticQuery/pqcSyntheticAboveQuery";
    }


}
