/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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

/**
 * 订单全动作查询
 * 2017-5-22
 */
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


    /**
     * 梅浩:质检部分
     *
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "pqcDetail")
    public List<Map<String, Object>> pqcDetail(String orderId) {

        Map<String, Object> map = new HashMap<>();

        map.put("orderId", orderId);


        List<Map<String, Object>> mapList = orderFullActionService.pqcOrderFullQuery(map);
        return mapList;

    }


    /**
     * wyb:订单部分
     *
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "orderDetail")
    public Map<String, Object> orderDetail(String storeId, String orderNumber) {

        Map<String, Object> map = new HashMap<>();
        //订单id
        map.put("orderNumber", orderNumber);
        //门店id
        map.put("storeId", storeId);

        Map<String, Object> list = orderFullActionService.orderDetail(map);

        return list;

    }

    /**
     * wyb:材料部分
     *
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "purchaseDetail")
    public List<Map<String, Object>> purchaseDetail(String orderId) {

        Map<String, Object> map = new HashMap<>();
        //订单id
        map.put("orderId", orderId);
        //采购单状态  字典表类型
        map.put("purchaseStatus", "purchase_auxiliary_status");
        //是否删除 标记
        map.put("delFlag", PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0);
        //催促信息  墙地砖催促
        map.put("businesType", BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_2);
        //催促信息  催促
        map.put("operateType", BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_1);

        List<Map<String, Object>> list = orderFullActionService.purchaseDetail(map);
        return list;

    }


    /**
     * wyb:安装阶段
     *
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "installDetail")
    public List<Map<String, Object>> installDetail(String orderId) {

        Map<String, Object> map = new HashMap<>();
        //订单id
        map.put("orderId", orderId);
        //问题上报  主材安装申请
        map.put("businessType", BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_1);

        List<Map<String, Object>> list = orderFullActionService.installDetail(map);
        return list;

    }


    /**
     * 交底开工
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "disclosureStarts")
    @ResponseBody
    public Map<String, Object> disclosureStarts(String orderId) {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("orderId", orderId);

        Map<String, Object> disclosureStarts = orderFullActionService.disclosureStartsQuery(map);

        return disclosureStarts;
    }


    /**
     * 导出
     *
     * @param order
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "exportExcel")
    public void exportExcel(String orderId, HttpServletResponse response) throws Exception {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");

        HSSFWorkbook excel = exportExcelOrderFullActionService.exportExcel(orderId);
        ServletOutputStream out = null;//创建一个输出流对象
        try {
            response.setContentType("application/binary;charset=utf-8");
            String headerStr = new String(("订单全动作查询-" + sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");//headerString为中文时转码
            response.setHeader("Content-disposition", "attachment; filename=" + headerStr + ".xls");//filename是下载的xls的名
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


    /**
     * 基装变更
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "projectChangeBill")
    @ResponseBody
    public List<Map<String, Object>> projectChangeBillQuery(String orderId) {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("orderId", orderId);

        List<Map<String, Object>> list = orderFullActionService.projectChangeBillQuery(map);

        return list;
    }

    /**
     * hyh:基装款项：款项
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "queryPrePmSettleFinance")
    @ResponseBody
    public List<BizPrePmSettleFin> queryPrePmSettleFinance(String orderId) {
        return orderFullActionService.queryPrePmSettleFinance(orderId);
    }
}