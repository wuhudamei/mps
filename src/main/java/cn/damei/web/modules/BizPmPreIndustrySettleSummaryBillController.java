package cn.damei.web.modules;

import cn.mdni.commons.excel.export.ExportSingleSheetHelper;
import cn.mdni.commons.file.UploadCategory;
import cn.mdni.commons.view.ViewDownLoad;
import com.google.common.collect.Maps;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizPmPreIndustrySettleBill;
import cn.damei.entity.modules.BizPmPreIndustrySettleSummaryBill;
import cn.damei.service.modules.BizPmPreIndustrySettleSummaryBillService;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.common.utils.UserUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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


@Controller
@RequestMapping(value = "${adminPath}/bizPmPreIndustrySettleSummaryBill/bizPmPreIndustrySettleSummaryBill")
public class BizPmPreIndustrySettleSummaryBillController extends BaseController {

    @Autowired
    private BizPmPreIndustrySettleSummaryBillService bizPmPreIndustrySettleSummaryBillService;

    @Autowired
    private BizEmployeeService2 bizEmployeeService2;

    @RequestMapping(value = "queryPmMonthSettle")
    public String queryPmMonthSettle(BizPmPreIndustrySettleSummaryBill bizPmPreIndustrySettleSummaryBill,
                                     HttpServletRequest request, HttpServletResponse response, Model model) {

        if (bizPmPreIndustrySettleSummaryBill.getStoreId() == null) {
            String storeId = UserUtils.getUser().getStoreId();
            if (StringUtils.isBlank(storeId)) {
                bizPmPreIndustrySettleSummaryBill.setStoreId(null);
            } else {
                bizPmPreIndustrySettleSummaryBill.setStoreId(Integer.parseInt(storeId));
            }
        }
        if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
            model.addAttribute("storeDropEnable", true);
        } else {
            bizPmPreIndustrySettleSummaryBill.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
        }
        return "modules/bizPmPreIndustrySettleSummaryBill/pmPreIndustryMonthlySettleBillList";
    }


    @RequestMapping(value = "queryPmMonthSettleList")
    public String queryPmMonthSettleList(BizPmPreIndustrySettleSummaryBill bizPmPreIndustrySettleSummaryBill,
                                         HttpServletRequest request, HttpServletResponse response, Model model) {

        if (bizPmPreIndustrySettleSummaryBill.getStoreId() == null) {
            String storeId = UserUtils.getUser().getStoreId();
            if (StringUtils.isBlank(storeId)) {
                bizPmPreIndustrySettleSummaryBill.setStoreId(null);
            } else {
                bizPmPreIndustrySettleSummaryBill.setStoreId(Integer.parseInt(storeId));
            }
        }
        if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
            model.addAttribute("storeDropEnable", true);
        } else {
            bizPmPreIndustrySettleSummaryBill.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
        }

        if (bizPmPreIndustrySettleSummaryBill.getProjectMode() == null) {
            bizPmPreIndustrySettleSummaryBill.setProjectMode(4);
        }

        if (bizPmPreIndustrySettleSummaryBill.getEnginDepartId() == null) {
            if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
                List<Integer> list = bizEmployeeService2
                        .findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
                if (CollectionUtils.isNotEmpty(list)) {
                    bizPmPreIndustrySettleSummaryBill.setEnginDepartIds(list);
                } else {
                    bizPmPreIndustrySettleSummaryBill.setEnginDepartIds(null);
                }
            } else {
                bizPmPreIndustrySettleSummaryBill.setEnginDepartIds(null);
            }
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(bizPmPreIndustrySettleSummaryBill.getEnginDepartId());
            bizPmPreIndustrySettleSummaryBill.setEnginDepartIds(list);
        }
        Page<BizPmPreIndustrySettleSummaryBill> page = bizPmPreIndustrySettleSummaryBillService.findPage(new Page<BizPmPreIndustrySettleSummaryBill>(request, response), bizPmPreIndustrySettleSummaryBill);
        model.addAttribute("page", page);
        return "modules/bizPmPreIndustrySettleSummaryBill/pmPreIndustryMonthlySettleBillList";
    }

    @RequestMapping(value = "queryOrderMonthSettleList")
    public String queryOrderMonthSettleList(BizPmPreIndustrySettleSummaryBill bizPmPreIndustrySettleSummaryBill,
                                            HttpServletRequest request, HttpServletResponse response, Model model) {

        if (bizPmPreIndustrySettleSummaryBill.getStoreId() == null) {
            String storeId = UserUtils.getUser().getStoreId();
            if (StringUtils.isBlank(storeId)) {
                bizPmPreIndustrySettleSummaryBill.setStoreId(null);
            } else {
                bizPmPreIndustrySettleSummaryBill.setStoreId(Integer.parseInt(storeId));
            }
        }
        if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
            model.addAttribute("storeDropEnable", true);
        } else {
            bizPmPreIndustrySettleSummaryBill.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
        }
        Page<BizPmPreIndustrySettleSummaryBill> page = bizPmPreIndustrySettleSummaryBillService.findListByOrder(new Page<BizPmPreIndustrySettleSummaryBill>(request, response), bizPmPreIndustrySettleSummaryBill);
        model.addAttribute("page", page);
        return "modules/bizPmPreIndustrySettleSummaryBill/queryOrderMonthPreIndustrySettleList";
    }

    @RequestMapping(value = "querySettleBillInfo")
    public String querySettleBillInfo(Integer id, Integer settleBillType, String orderId, HttpServletRequest request, HttpServletResponse response, Model model) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        param.put("settleBillType", settleBillType);
        param.put("orderId", orderId);
        BizPmPreIndustrySettleBill settleBill = bizPmPreIndustrySettleSummaryBillService.queryPmPreIndustrySettleByParam(param);
        model.addAttribute("settleBill", settleBill);
        String result = null;
        if (settleBillType == 1) {
            result = "modules/bizPmPreIndustrySettleSummaryBill/pmPreIndustryMidwaySettleBill";
        } else if (settleBillType == 2) {
            result = "modules/bizPmPreIndustrySettleSummaryBill/PmPreIndustryCompleteSettleBill";
        }
        return result;
    }

    @RequestMapping(value = "/export")
    public ModelAndView export(String storeId, String settleMonth, String customerName, String pmEmployeeName, String createSettleMonthStartDate, String createSettleMonthEndDate,
                                         HttpServletRequest request, HttpServletResponse response) throws Exception {
        UploadCategory uploadCategory = UploadCategory.EXCLE;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = "订单月度结算.xls";
        String root = request.getSession().getServletContext().getRealPath("/");
        String uploadUrl = root + PicRootName.getConfigValue(ConstantUtils.UPLOAD);
        String filePath = cn.mdni.commons.file.FileUtils.saveFilePath(uploadCategory, uploadUrl, fileName);

        LinkedHashMap<String, String> headerMapper = Maps.newLinkedHashMap();
        headerMapper.put("storeName", "门店");
        headerMapper.put("createDate", "生成月度结算时间");
        headerMapper.put("settleMonth", "月度");
        headerMapper.put("orderNumber", "订单号");
        headerMapper.put("customerName", "客户");
        headerMapper.put("pmEmployeeName", "项目经理");
        headerMapper.put("midwayRealSettleAmount", "中期结算合计金额");
        headerMapper.put("completeRealSettleAmount", "竣工结算合计金额");
        headerMapper.put("realSettleAmount", "实际结算合计");

        BizPmPreIndustrySettleSummaryBill bizPmPreIndustrySettleSummaryBill = new BizPmPreIndustrySettleSummaryBill();
        if (StringUtils.isNotBlank(storeId)) {
            bizPmPreIndustrySettleSummaryBill.setStoreId(Integer.valueOf(storeId));
        }
        bizPmPreIndustrySettleSummaryBill.setSettleMonth(settleMonth);
        bizPmPreIndustrySettleSummaryBill.setCustomerName(customerName);
        bizPmPreIndustrySettleSummaryBill.setPmEmployeeName(pmEmployeeName);
        if (StringUtils.isNotBlank(createSettleMonthStartDate)) {
            bizPmPreIndustrySettleSummaryBill.setCreateSettleMonthStartDate(sdf.parse(createSettleMonthStartDate));
        }
        if (StringUtils.isNotBlank(createSettleMonthEndDate)) {
            bizPmPreIndustrySettleSummaryBill.setCreateSettleMonthEndDate(sdf.parse(createSettleMonthEndDate));
        }

        List<BizPmPreIndustrySettleSummaryBill> list = bizPmPreIndustrySettleSummaryBillService.findListByOrder(bizPmPreIndustrySettleSummaryBill);
        ExportSingleSheetHelper exportSingleSheetHelper = new ExportSingleSheetHelper(filePath, headerMapper, list);
        exportSingleSheetHelper.build();

        ViewDownLoad viewDownLoad = new ViewDownLoad(new File(filePath), null);
        return new ModelAndView(viewDownLoad);
    }

}
