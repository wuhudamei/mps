package cn.damei.web.modules;

import cn.mdni.commons.excel.export.ExportSingleSheetHelper;
import cn.mdni.commons.file.UploadCategory;
import cn.mdni.commons.view.ViewDownLoad;
import cn.damei.common.persistence.Page;
import cn.damei.common.config.Global;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.Order2;
import cn.damei.entity.modules.BizProjectProgressQueryRuleConfig;
import cn.damei.entity.modules.BizProjectProgressSummaryData;
import cn.damei.service.modules.BizProjectProgressSummaryDataService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 订单工程进度大看板Controller
 * Created by hyh on 2017/12/8.
 */
@Controller
@RequestMapping(value = "${adminPath}/projectprogressboning/bizProjectProgressSummaryData")
public class BizProjectProgressSummaryDataController extends BaseController {

    @Autowired
    private BizProjectProgressSummaryDataService bizProjectProgressSummaryDataService;

    /**
     * 订单工程进度大看板
     * @param bizProjectProgressSummaryData
     * @param model
     * @return
     */
    @RequiresPermissions("bizProjectProgressSummaryData:bizProjectProgressSummaryData:view")
    @RequestMapping(value = "/preList")
    public  String preList(BizProjectProgressSummaryData bizProjectProgressSummaryData, Model model){
        //门店和工程模式权限控制
        bizProjectProgressSummaryDataService.queryStoreIdAndProjectMode(bizProjectProgressSummaryData,model);
        //获取表头配置
        List<BizProjectProgressQueryRuleConfig> list = bizProjectProgressSummaryDataService.queryRuleConfig();
        //获取table的表头和数据配置对象
        Map<String,Object> map = bizProjectProgressSummaryDataService.queryTableConfig(list);
        model.addAttribute("map",map);
        return "modules/projectprogressboning/bizProjectProgressSummaryDataList";
    }

    /**
     * 订单工程进度大看板
     * @param bizProjectProgressSummaryData
     * @param model
     * @return
     */
    @RequiresPermissions("bizProjectProgressSummaryData:bizProjectProgressSummaryData:view")
    @RequestMapping(value = "/list")
    public  String list(BizProjectProgressSummaryData bizProjectProgressSummaryData, HttpServletRequest request, HttpServletResponse response, Model model){
        //门店和工程模式权限控制
        bizProjectProgressSummaryDataService.queryStoreIdAndProjectMode(bizProjectProgressSummaryData,model);
        //获取表头配置
        List<BizProjectProgressQueryRuleConfig> list = bizProjectProgressSummaryDataService.queryRuleConfig();
        //获取table的表头和数据配置对象
        Map<String,Object> map = bizProjectProgressSummaryDataService.queryTableConfig(list);
        //订单工程进度大看板数据
        Page<BizProjectProgressSummaryData> page = bizProjectProgressSummaryDataService.findPage(new Page<BizProjectProgressSummaryData>(request, response), bizProjectProgressSummaryData);
        model.addAttribute("map",map);
        model.addAttribute("page",page);
        return "modules/projectprogressboning/bizProjectProgressSummaryDataList";
    }

    /**
     * 订单工程进度大看板--导出
     * @param bizProjectProgressSummaryData
     * @param request
     * @return
     * @throws Exception
     */
    @RequiresPermissions("bizProjectProgressSummaryData:bizProjectProgressSummaryData:edit")
    @RequestMapping(value = "/export")
    public ModelAndView exportBudgetCost(BizProjectProgressSummaryData bizProjectProgressSummaryData, HttpServletRequest request) throws Exception{
        UploadCategory uploadCategory = UploadCategory.EXCLE;
        String fileName = "工程进度大看板"+ DateUtils.getDate("yyyyMMddHHmmss")+".xls";
        String root = request.getSession().getServletContext().getRealPath("/");
        String uploadUrl = root+ PicRootName.getConfigValue(ConstantUtils.UPLOAD);
        String filePath = cn.mdni.commons.file.FileUtils.saveFilePath(uploadCategory, uploadUrl, fileName);
        ExportSingleSheetHelper<Map<String, Object>> ex = new ExportSingleSheetHelper<Map<String, Object>>(filePath);
        bizProjectProgressSummaryDataService.exportProjectProgressSummaryData(bizProjectProgressSummaryData, ex);
        ex.build(true);
        ViewDownLoad viewDownLoad = new ViewDownLoad(new File(filePath),null);
        return new ModelAndView(viewDownLoad);
    }


    /**
     * 根据订单Id更新大看板数据
     * @param orderId
     * @param
     * @return
     */
    @RequestMapping(value = "editOrderNode")
    public @ResponseBody String editOrderNode(Integer orderId) {
        String result = "0";
		try {
			updateBizProjectProgressSummaryData(orderId);
		}catch (Exception e){
			result = "1";
		}
        return result;
    }
    @RequestMapping(value = "updateAll")
    public String updateAll(){
        List<Order2> list = bizProjectProgressSummaryDataService.queryOrderByCondition2();
        if(CollectionUtils.isNotEmpty(list)){
            for (Order2 order : list){
                try {
                    bizProjectProgressSummaryDataService.updateBizProjectProgressSummaryData(order.getId());
                } catch (Exception e) {
                    bizProjectProgressSummaryDataService.updateErrorStatus(order.getId());
                    e.printStackTrace();
                }
            }
        }
        return "redirect:" + Global.getAdminPath() + "/projectprogressboning/bizProjectProgressSummaryData/list?repage";
    }

    public void updateBizProjectProgressSummaryData(Integer orderId) {
        try {
            bizProjectProgressSummaryDataService.updateBizProjectProgressSummaryData(orderId);
        } catch (Exception e) {
            bizProjectProgressSummaryDataService.updateErrorStatus(orderId);
            throw e;
        }
    }



}