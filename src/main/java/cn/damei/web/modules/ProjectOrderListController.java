package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.WallfloorConstant;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizMaterialsChoiceBill;
import cn.damei.service.modules.BizMaterialsChoiceBillService;
import cn.damei.entity.modules.BizMaterialsChoiceBillItem;
import cn.damei.service.modules.BizMaterialsChoiceBillItemService;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.Order;
import cn.damei.entity.modules.OrderInstallItemVo;
import cn.damei.service.modules.OrderService;
import cn.damei.service.modules.BizOrderMainMateService;
import cn.damei.entity.modules.ProjectOrderList;
import cn.damei.service.modules.ProjectOrderListService;
import cn.damei.common.utils.DropUtils;
import cn.damei.common.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/projectOrderList")
public class ProjectOrderListController {
    @Autowired
    private ProjectOrderListService projectOrderListService;
    @Autowired
    private BizMaterialsChoiceBillService bizMaterialsChoiceBillService;
    @Autowired
    private BizOrderMainMateService bizOrderMainMateService;

    @ModelAttribute
    public ProjectOrderList get(String id) {
        ProjectOrderList projectOrderList = null;
        if (id != null) {
            return projectOrderList;
        } else {
            ProjectOrderList pl = new ProjectOrderList();
            return pl;
        }
    }

    @RequestMapping(value = "preList")
    public String preList(ProjectOrderList projectOrderList, Model model) {
        BizMaterialsChoiceBill bizMaterialsChoiceBill = new BizMaterialsChoiceBill();
        String storeId = UserUtils.getUser().getStoreId();
        if (!StringUtils.isEmpty(storeId)) {
            bizMaterialsChoiceBill.setStoreId(projectOrderList.getStoreId());
            List<DropModel> engineListWithUserConditions = DropUtils.getEngineListWithUserConditions();
            bizMaterialsChoiceBill.setEngineDepartId(engineListWithUserConditions.get(0).getValue());

        }
        BizMaterialsChoiceBill findChoiceBillCount = null;
        findChoiceBillCount = projectOrderListService.findChoiceBillCount(bizMaterialsChoiceBill);

        model.addAttribute("findChoiceBillCount", findChoiceBillCount);
        return "/modules/projectorderlist/projectorderlist";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "list")
    public String list(ProjectOrderList projectOrderList, Model model, HttpServletRequest request, HttpServletResponse response) {
        List<String> orderIds = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        if (projectOrderList.getFlag() != null || projectOrderList.getIsMaterial() != null) {
            List<String> flag = projectOrderList.getFlag();
            List<String> orderId = new ArrayList<>();
            List<String> orderId2 = new ArrayList<>();
            List<String> orderId3 = new ArrayList<>();
            List<String> orderId4 = new ArrayList<>();

            List<String> orderId7 = new ArrayList<>();
            List<String> orderId8 = new ArrayList<>();
            List<Object> all = new ArrayList<>();

            if (!StringUtils.isEmpty(projectOrderList.getIsMaterial()) && (projectOrderList.getFlag() == null)) {
                orderId7 = projectOrderListService.queryinOrderNot56(projectOrderList);
                all.add(orderId7);
            }
            if (projectOrderList.getFlag() != null) {
                for (String string : flag) {
                    if (!StringUtils.isEmpty(projectOrderList.getIsMaterial()) && string != null && string.equals("5")) {
                        orderId7 = projectOrderListService.queryinOrder2(projectOrderList);
                        all.add(orderId7);
                    } else if (!StringUtils.isEmpty(projectOrderList.getIsMaterial()) && string != null && string.equals("6")) {
                        orderId8 = projectOrderListService.queryinOrder3(projectOrderList);
                        all.add(orderId8);
                    }
                }
            }

            if (projectOrderList.getFlag() != null) {
                for (String string : flag) {

                    if (string.equals("1")) {
                        orderId = projectOrderListService.findDrawing(projectOrderList);
                        all.add(orderId);
                    }

                    if (string.equals("2")) {
                        orderId2 = projectOrderListService.findAttached(projectOrderList);
                        all.add(orderId2);
                    }

                    if (string.equals("3")) {



                        orderId3 = projectOrderListService.findwallAndFloor(projectOrderList);
                        all.add(orderId3);


                    }

                    if (string.equals("4")) {
                        orderId4 = projectOrderListService.findMaterial(projectOrderList);
                        all.add(orderId4);
                    }




















































                }
            }

            if (all != null && all.size() > 1) {











                Set<String> setweiyi = new HashSet<String>();
                for (int i = 0; i < all.size(); i++) {
                    List<String> two = (List<String>) all.get(i);
                    for (String string : two) {
                        setweiyi.add(string);
                    }
                }
                for (String string : setweiyi) {
                    orderIds.add(string);
                }

            } else {
                List<String> one = (List<String>) all.get(0);
                temp = one;
            }

        }
        if (temp != null && temp.size() > 0) {

            projectOrderList.setOrderIds(temp);
        }
        if (orderIds != null && orderIds.size() > 0) {
            projectOrderList.setOrderIds(orderIds);

        }

        Page<ProjectOrderList> findPage = null;
        if (!StringUtils.isEmpty(projectOrderList.getIsMaterial()) && projectOrderList.getIsMaterial().equals("1")) {
            findPage = projectOrderListService.findPage(new Page<ProjectOrderList>(request, response), projectOrderList);
        } else {
            findPage = projectOrderListService.findNotMaterialPage(new Page<ProjectOrderList>(request, response), projectOrderList);
        }
        BizMaterialsChoiceBill bizMaterialsChoiceBill = new BizMaterialsChoiceBill();
        String storeId = UserUtils.getUser().getStoreId();
        if (!StringUtils.isEmpty(storeId)) {
            bizMaterialsChoiceBill.setStoreId(projectOrderList.getStoreId());
            List<DropModel> engineListWithUserConditions = DropUtils.getEngineListWithUserConditions();
            bizMaterialsChoiceBill.setEngineDepartId(engineListWithUserConditions.get(0).getValue());

        } else {
            bizMaterialsChoiceBill.setStoreId(projectOrderList.getStoreId());
            bizMaterialsChoiceBill.setEngineDepartId(projectOrderList.getEngineDepartId());
            bizMaterialsChoiceBill.setProjectMode(projectOrderList.getProjectMode());

        }
        BizMaterialsChoiceBill findChoiceBillCount = null;
        findChoiceBillCount = projectOrderListService.findChoiceBillCount(bizMaterialsChoiceBill);
        model.addAttribute("findChoiceBillCount", findChoiceBillCount);
        model.addAttribute("page", findPage);

        return "/modules/projectorderlist/projectorderlist";
    }

    @Autowired
    private BizMaterialsChoiceBillItemService bizMaterialsChoiceBillItemService;


    @RequestMapping(value = "lookWallAndFloor")
    public String lookWallAndFloor(Integer orderId, Model model, String flag) {

        BizMaterialsChoiceBill findChoiceBillId = projectOrderListService.findChoiceBillId(orderId);
        List<BizMaterialsChoiceBillItem> materialsChoiceList = null;
        if (findChoiceBillId != null && findChoiceBillId.getId() != null) {

            if (flag == null) {
                BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem = new BizMaterialsChoiceBillItem();
                bizMaterialsChoiceBillItem.setMaterialsChoiceBillId(findChoiceBillId.getId());
                materialsChoiceList = projectOrderListService.findMaterialsChoice(findChoiceBillId.getId());

            } else {

                BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem = new BizMaterialsChoiceBillItem();
                bizMaterialsChoiceBillItem.setMaterialsChoiceBillId(findChoiceBillId.getId());
                materialsChoiceList = bizMaterialsChoiceBillItemService.findList(bizMaterialsChoiceBillItem);
            }

        }
        model.addAttribute("materialsChoiceList", materialsChoiceList);
        model.addAttribute("bizMaterialsChoiceBill", findChoiceBillId);
        return "modules/bizmaterialschoicebill/bizMaterialsChoiceBillDetails";
    }


    @RequestMapping(value = "updateWallStatus")
    public String updateWallStatus(Integer orderId, Order order, Model model, String flag) {

        BizMaterialsChoiceBill findChoiceBillId = projectOrderListService.findChoiceBillId(orderId);
        List<BizMaterialsChoiceBillItem> materialsChoiceList = null;
        if (findChoiceBillId != null && findChoiceBillId.getId() != null) {
            projectOrderListService.updateStatusMain(WallfloorConstant.WAll_FLOOR_ISTRUE6, orderId);

            if (flag == null) {
                BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem = new BizMaterialsChoiceBillItem();
                bizMaterialsChoiceBillItem.setMaterialsChoiceBillId(findChoiceBillId.getId());
                materialsChoiceList = projectOrderListService.findMaterialsChoice(findChoiceBillId.getId());

            } else {

                BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem = new BizMaterialsChoiceBillItem();
                bizMaterialsChoiceBillItem.setMaterialsChoiceBillId(findChoiceBillId.getId());
                materialsChoiceList = bizMaterialsChoiceBillItemService.findList(bizMaterialsChoiceBillItem);
            }
        }
        model.addAttribute("materialsChoiceList", materialsChoiceList);
        model.addAttribute("bizMaterialsChoiceBill", findChoiceBillId);
        model.addAttribute("order", order);
        return "modules/bizmaterialschoicebill/bizMaterialsChoiceBillDetails";
    }


    @RequestMapping(value = "lookWallAndFloorz")
    public String queryWallAndFloorz(Integer orderId, String orderNumber, Model model, String flag) {

        BizMaterialsChoiceBill findChoiceBillId = projectOrderListService.findChoiceBillId(orderId);
        List<BizMaterialsChoiceBillItem> materialsChoiceList = null;
        if (findChoiceBillId != null && findChoiceBillId.getId() != null) {

            if (flag == null) {
                BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem = new BizMaterialsChoiceBillItem();
                bizMaterialsChoiceBillItem.setMaterialsChoiceBillId(findChoiceBillId.getId());
                materialsChoiceList = projectOrderListService.findMaterialsChoicez(findChoiceBillId.getId());





                if (null != findChoiceBillId && !StringUtils.isEmpty(findChoiceBillId.getOrderNumber())) {
                    String isxin = projectOrderListService.queryDealedwallfloor(findChoiceBillId.getOrderNumber());
                    if (!StringUtils.isEmpty(isxin)) {
                        if (isxin.equals("5")) {
                            findChoiceBillId.setStatus("1");
                        } else {
                            findChoiceBillId.setStatus("0");

                        }
                    }
                }
                model.addAttribute("materialsChoiceList", materialsChoiceList);
                model.addAttribute("bizMaterialsChoiceBill", findChoiceBillId);
                return "modules/bizmaterialschoicebill/bizMaterialsChoiceWallfoll";
            } else {

                BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem = new BizMaterialsChoiceBillItem();
                bizMaterialsChoiceBillItem.setMaterialsChoiceBillId(findChoiceBillId.getId());
                materialsChoiceList = bizMaterialsChoiceBillItemService.findList(bizMaterialsChoiceBillItem);
            }

        }
        model.addAttribute("materialsChoiceList", materialsChoiceList);
        model.addAttribute("bizMaterialsChoiceBill", findChoiceBillId);
        return "modules/bizmaterialschoicebill/bizMaterialsChoiceBillDetails";
    }

    @Autowired
    private OrderService orderService;



    @RequiresPermissions("projectOrderList:projectOrderList:edit")
    @RequestMapping(value = "materialInstallItem")
    public String materialInstallItem(Order order, Model model, String flag) {

        List<Order> findList = orderService.findList(order);
        if (null != order.getOrderId()) {

            List<OrderInstallItemVo> planStatus = null;
            List<OrderInstallItemVo> installItems = null;
            List<OrderInstallItemVo> installItemByStoreId = null;

            installItemByStoreId = orderService.findInstallItemByStoreId(order);

            installItems = orderService.findInstallItemByOrderId(order.getOrderId());


            if (Integer.parseInt(order.getOrderStatusNumber()) >= 200) {




                planStatus = orderService.findOrderInstallItemPlanStatus(order.getOrderId());
                if (null != planStatus && planStatus.size() > 0)
                    for (OrderInstallItemVo orderInstallItemVo : planStatus) {


                        g: for (OrderInstallItemVo vo : installItems) {
                            if (vo.getId().equals(orderInstallItemVo.getId())) {
                                for (OrderInstallItemVo v : installItemByStoreId) {
                                    if (v.getProjectInstallItemId().equals(vo.getProjectInstallItemId())) {
                                        v.setCreateDate(orderInstallItemVo.getCreateDate());
                                        v.setInstallStatus(orderInstallItemVo.getStatus());
                                        break g;

                                    }

                                }

                            }

                        }

                        if (orderInstallItemVo.getStatus().equals("2") || orderInstallItemVo.getStatus().equals("3") || orderInstallItemVo.getStatus().equals("4") || orderInstallItemVo.getStatus().equals("6") || orderInstallItemVo.getStatus().equals("310") || orderInstallItemVo.getStatus().equals("320") || orderInstallItemVo.getStatus().equals("330")) {


                            h: for (OrderInstallItemVo vo : installItems) {
                                if (vo.getId().equals(orderInstallItemVo.getId())) {

                                    for (OrderInstallItemVo v : installItemByStoreId) {

                                        if (v.getProjectInstallItemId().equals(vo.getProjectInstallItemId())) {
                                            v.setStatus("1");

                                            if (orderInstallItemVo.getStatus().equals("3") || orderInstallItemVo.getStatus().equals("4") || orderInstallItemVo.getStatus().equals("310") || orderInstallItemVo.getStatus().equals("320") || orderInstallItemVo.getStatus().equals("330")) {

                                                v.setInstallModeFlag("1");
                                            }
                                            break h;

                                        }

                                    }

                                }

                            }

                        } else {


                        }
                    }

            }


            List<OrderInstallItemVo> naruto = new ArrayList<OrderInstallItemVo>();




            for (OrderInstallItemVo itemVo : installItems) {


                for (OrderInstallItemVo orderInstallItemVo : installItemByStoreId) {

                    if (itemVo.getProjectInstallItemId().equals(orderInstallItemVo.getProjectInstallItemId())) {

                        orderInstallItemVo.setIsChoosed("1");
                        itemVo.setIsChoosed("1");

                        orderInstallItemVo.setInstallMode(itemVo.getInstallMode());


                        Integer checksizeCount = orderService.queryCheckStatus(order.getOrderId(), itemVo.getId());
                        if (null != checksizeCount && checksizeCount > 0) {
                            orderInstallItemVo.setStatus("1");
                        }

                        break;

                    } else {

                        if (null == orderInstallItemVo.getIsChoosed() || !orderInstallItemVo.getIsChoosed().equals("1")) {


                            orderInstallItemVo.setIsChoosed("0");

                        }

                    }

                }


                if (null == itemVo.getIsChoosed()) {
                    itemVo.setIsChoosed("1");


                    List<OrderInstallItemVo> status = orderService.findOrderInstallItemPlanStatus(order.getOrderId());

                    if (status.size() > 0) {

                        for (OrderInstallItemVo orderInstallItemVo : status) {
                            if (itemVo.getId().equals(orderInstallItemVo.getId())) {

                                if (Integer.parseInt(orderInstallItemVo.getStatus()) > 1) {
                                    itemVo.setStatus("1");

                                    if (orderInstallItemVo.getStatus().equals("3") || orderInstallItemVo.getStatus().equals("4") || orderInstallItemVo.getStatus().equals("310") || orderInstallItemVo.getStatus().equals("320") || orderInstallItemVo.getStatus().equals("330")) {

                                        itemVo.setInstallModeFlag("1");
                                    }
                                    break;
                                } else {
                                    itemVo.setStatus("0");
                                    break;
                                }


                            }

                        }
                    }

                    Integer checksizeCount = orderService.queryCheckStatus(order.getOrderId(), itemVo.getId());
                    if (null != checksizeCount && checksizeCount > 0) {
                        itemVo.setStatus("1");
                    }
                    naruto.add(itemVo);
                }

            }



            if (naruto.size() > 0) {
                for (OrderInstallItemVo orderInstallItemVo : naruto) {

                    OrderInstallItemVo status = orderService.findInstallStatus(orderInstallItemVo.getId());
                    orderInstallItemVo.setInstallStatus(status.getStatus());
                    orderInstallItemVo.setCreateDate(status.getCreateDate());
                    installItemByStoreId.add(orderInstallItemVo);
                }
            }

            model.addAttribute("installItemList", installItemByStoreId);

        } else {

        }
        String isxin = projectOrderListService.queryDealedmainmaterial(order.getOrderNumber());
        model.addAttribute("order", findList.get(0));
        model.addAttribute("flag", flag);
        model.addAttribute("isxin", isxin);

        if (isxin == null) {
            model.addAttribute("isxin", 6);
        }

        return "/modules/projectorderlist/installModel";
    }

    @RequestMapping(value = "save")
    public String save(String installItemIds, String installMode, Order order, String flag) {

        if(StringUtils.isNotBlank(installMode) && StringUtils.isNotBlank(installItemIds)){

            int length = installMode.length();
            installMode = installMode.substring(0, length - 1);
            String[] installModesplit = installMode.split(",");
            int length2 = installItemIds.length();
            installItemIds = installItemIds.substring(0, length2 - 1);
            String[] installItemIdssplit = installItemIds.split(",");

            projectOrderListService.save(order, installItemIdssplit, installModesplit);
        }else{

            orderService.deleteAllInstallItem(order.getOrderId());
            orderService.deleteAllInstallItemPlan(order.getOrderId());
        }


        if (flag != null) {
            if (flag.equals("1")) {
                return "forward:" + Global.getAdminPath() + "/order/order/list1";
            }
            if (flag.equals("2")) {
                return "redirect:" + Global.getAdminPath() + "/projectOrderList/list";
            }
        }
        return null;
    }


    @ResponseBody
    @RequestMapping(value = "findInstallModel")
    public String findInstallModel(String id, Model model, HttpServletRequest request, HttpServletResponse reponse) {

        String intallMode = projectOrderListService.findInstallModel(id);
        return intallMode;
    }

}
