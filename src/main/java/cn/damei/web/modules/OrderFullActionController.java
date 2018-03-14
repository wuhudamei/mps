
package cn.damei.web.modules;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.BusinessProblemConstantUtil;
import cn.damei.common.constantUtils.BusinessUrgeConstantUtil;
import cn.damei.common.constantUtils.PurchaseConstantUtil;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizPrePmSettleFin;
import cn.damei.entity.modules.Order;
import cn.damei.service.modules.OrderService;
import cn.damei.entity.modules.OrderFullAction;
import cn.damei.service.modules.ExportExcelOrderFullActionService;
import cn.damei.service.modules.OrderFullActionService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "${adminPath}/selectFullOrder/selectFullOrder/")
public class OrderFullActionController extends BaseController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderFullActionService orderFullActionService;
    @Autowired
    private ExportExcelOrderFullActionService exportExcelOrderFullActionService;

    @RequiresPermissions("selectFullOrder:selectFullOrder:view")
    @RequestMapping(value = "select")
    public String getOrder(@ModelAttribute("order") Order order) {
        orderService.getOrderByNuAndStoreId(order);

        return "modules/orderFullAction/orderFullActionNew";
    }

    @ResponseBody
    @RequestMapping(value = "budget")
    public OrderFullAction budget(String orderId) {

        return orderFullActionService.getBudget(orderId);
    }



    @ResponseBody
    @RequestMapping(value = "pqcDetail")
    public List<Map<String, Object>> pqcDetail(String orderId) {

        Map<String, Object> map = new HashMap<>();

        map.put("orderId", orderId);


        List<Map<String, Object>> mapList = orderFullActionService.pqcOrderFullQuery(map);
        return mapList;

    }



    @ResponseBody
    @RequestMapping(value = "orderDetail")
    public Map<String, Object> orderDetail(String storeId, String orderNumber) {

        Map<String, Object> map = new HashMap<>();

        map.put("orderNumber", orderNumber);

        map.put("storeId", storeId);

        Map<String, Object> list = orderFullActionService.orderDetail(map);

        return list;

    }


    @ResponseBody
    @RequestMapping(value = "purchaseDetail")
    public List<Map<String, Object>> purchaseDetail(String orderId) {

        Map<String, Object> map = new HashMap<>();

        map.put("orderId", orderId);

        map.put("purchaseStatus", "purchase_auxiliary_status");

        map.put("delFlag", PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0);

        map.put("businesType", BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_2);

        map.put("operateType", BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_1);

        List<Map<String, Object>> list = orderFullActionService.purchaseDetail(map);
        return list;

    }



    @ResponseBody
    @RequestMapping(value = "installDetail")
    public List<Map<String, Object>> installDetail(String orderId) {

        Map<String, Object> map = new HashMap<>();

        map.put("orderId", orderId);

        map.put("businessType", BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_1);

        List<Map<String, Object>> list = orderFullActionService.installDetail(map);
        return list;

    }



    @RequestMapping(value = "disclosureStarts")
    @ResponseBody
    public Map<String, Object> disclosureStarts(String orderId) {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("orderId", orderId);

        Map<String, Object> disclosureStarts = orderFullActionService.disclosureStartsQuery(map);

        return disclosureStarts;
    }



    @RequestMapping(value = "exportExcel")
    public void exportExcel(String orderId, HttpServletResponse response) throws Exception {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");

        HSSFWorkbook excel = exportExcelOrderFullActionService.exportExcel(orderId);
        ServletOutputStream out = null;
        try {
            response.setContentType("application/binary;charset=utf-8");
            String headerStr = new String(("订单全动作查询-" + sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");
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



    @RequestMapping(value = "projectChangeBill")
    @ResponseBody
    public List<Map<String, Object>> projectChangeBillQuery(String orderId) {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("orderId", orderId);

        List<Map<String, Object>> list = orderFullActionService.projectChangeBillQuery(map);

        return list;
    }


    @RequestMapping(value = "queryPrePmSettleFinance")
    @ResponseBody
    public List<BizPrePmSettleFin> queryPrePmSettleFinance(String orderId) {
        return orderFullActionService.queryPrePmSettleFinance(orderId);
    }
}