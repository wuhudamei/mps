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
            bizMaterialsChoiceBill.setStoreId(projectOrderList.getStoreId());// 查询当前用户的门店
            List<DropModel> engineListWithUserConditions = DropUtils.getEngineListWithUserConditions(); // 查询当前用户的所在区域
            bizMaterialsChoiceBill.setEngineDepartId(engineListWithUserConditions.get(0).getValue());
            // bizMaterialsChoiceBill.setProjectMode(projectOrderList.getProjectMode());
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
			/*List<String> orderId5 = new ArrayList<>();
			List<String> orderId6 = new ArrayList<>();*/
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
                    // 图纸
                    if (string.equals("1")) {
                        orderId = projectOrderListService.findDrawing(projectOrderList);
                        all.add(orderId);
                    }
                    // 附件
                    if (string.equals("2")) {
                        orderId2 = projectOrderListService.findAttached(projectOrderList);
                        all.add(orderId2);
                    }
                    // 墙地砖
                    if (string.equals("3")) {
                        // 查询已经墙地砖的订单号
                        // 查询所有的订单号
                        // 相减得到没有墙地砖的订单ID
                        orderId3 = projectOrderListService.findwallAndFloor(projectOrderList);
                        all.add(orderId3);
						/*
						 * List<String> temp =
						 * projectOrderListService.findOrderIds(list);
						 */

                    }
                    // 主材安装项
                    if (string.equals("4")) {
                        orderId4 = projectOrderListService.findMaterial(projectOrderList);
                        all.add(orderId4);
                    }
                    // 墙地砖变更订单
                    // if (string.equals("5")) {
                    // orderId5 =
                    // projectOrderListService.isdealedwallfloor(projectOrderList);
                    // Set<String> setweiyi = new HashSet<String>();
                    // for (String string1 : orderId5) {
                    // setweiyi.add(string1);
                    // }
                    // orderId5 = new ArrayList<String>();
                    // Iterator<String> it = setweiyi.iterator();
                    // for (int i = 0; i < setweiyi.size(); i++) {
                    // String name = (String) it.next();
                    // if (StringUtils.isEmpty(name)) {
                    // it.remove();
                    // i--;
                    // } else {
                    // orderId5.add(name);
                    //
                    // }
                    // }
                    //
                    // all.add(orderId5);
                    // }
                    // 安装项变更的订单
                    // if (string.equals("6")) {
                    // orderId6 =
                    // projectOrderListService.isdealedmainmaterial(projectOrderList);
                    // Set<String> setweiyi = new HashSet<String>();
                    // for (String string1 : orderId6) {
                    // setweiyi.add(string1);
                    // }
                    // orderId6 = new ArrayList<String>();
                    // Iterator<String> it = setweiyi.iterator();
                    //
                    // for (int i = 0; i < setweiyi.size(); i++) {
                    // String name = (String) it.next();
                    // if (StringUtils.isEmpty(name)) {
                    // it.remove();
                    // i--;
                    // } else {
                    // orderId6.add(name);
                    // }
                    // }
                    //
                    // all.add(orderId6);
                    // }
                    // if (string.equals("1")) {
                    // orderId7 =
                    // projectOrderListService.findDrawing(projectOrderList);
                    // all.add(orderId7);
                    // }

                }
            }

            if (all != null && all.size() > 1) {
                // List<String> one = (List<String>) all.get(1);
                // temp = one;
                // for (int i = 0; i < all.size(); i++) {
                //
                // List<String> two = (List<String>) all.get(i);
                // one.retainAll(two);
                //
                // }
                // if (temp.size() == 0) {
                // temp.add("不存在的！");
                // }
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
        // 如果是1就代表有选材清单
        Page<ProjectOrderList> findPage = null;
        if (!StringUtils.isEmpty(projectOrderList.getIsMaterial()) && projectOrderList.getIsMaterial().equals("1")) {
            findPage = projectOrderListService.findPage(new Page<ProjectOrderList>(request, response), projectOrderList);
        } else {
            findPage = projectOrderListService.findNotMaterialPage(new Page<ProjectOrderList>(request, response), projectOrderList);
        }
        BizMaterialsChoiceBill bizMaterialsChoiceBill = new BizMaterialsChoiceBill();
        String storeId = UserUtils.getUser().getStoreId();
        if (!StringUtils.isEmpty(storeId)) {
            bizMaterialsChoiceBill.setStoreId(projectOrderList.getStoreId());// 查询当前用户的门店
            List<DropModel> engineListWithUserConditions = DropUtils.getEngineListWithUserConditions(); // 查询当前用户的所在区域
            bizMaterialsChoiceBill.setEngineDepartId(engineListWithUserConditions.get(0).getValue());
            // bizMaterialsChoiceBill.setProjectMode(projectOrderList.getProjectMode());
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

    /**
     * 查看墙地砖
     *
     * @return
     */
    @RequestMapping(value = "lookWallAndFloor")
    public String lookWallAndFloor(Integer orderId, Model model, String flag) {
        // 根据订单ID查询选材单ID
        BizMaterialsChoiceBill findChoiceBillId = projectOrderListService.findChoiceBillId(orderId);
        List<BizMaterialsChoiceBillItem> materialsChoiceList = null;
        if (findChoiceBillId != null && findChoiceBillId.getId() != null) {
            // 2.选材清单--墙地砖信息
            if (flag == null) {
                BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem = new BizMaterialsChoiceBillItem();
                bizMaterialsChoiceBillItem.setMaterialsChoiceBillId(findChoiceBillId.getId());
                materialsChoiceList = projectOrderListService.findMaterialsChoice(findChoiceBillId.getId());

            } else {
                // 2.选材清单--全部信息
                BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem = new BizMaterialsChoiceBillItem();
                bizMaterialsChoiceBillItem.setMaterialsChoiceBillId(findChoiceBillId.getId());
                materialsChoiceList = bizMaterialsChoiceBillItemService.findList(bizMaterialsChoiceBillItem);
            }

        }
        model.addAttribute("materialsChoiceList", materialsChoiceList);
        model.addAttribute("bizMaterialsChoiceBill", findChoiceBillId);
        return "modules/bizmaterialschoicebill/bizMaterialsChoiceBillDetails";
    }

    /**
     * 主材安装项查看墙地砖改选材清单状态
     *
     * @return
     */
    @RequestMapping(value = "updateWallStatus")
    public String updateWallStatus(Integer orderId, Order order, Model model, String flag) {
        // 根据订单ID查询选材单ID
        BizMaterialsChoiceBill findChoiceBillId = projectOrderListService.findChoiceBillId(orderId);
        List<BizMaterialsChoiceBillItem> materialsChoiceList = null;
        if (findChoiceBillId != null && findChoiceBillId.getId() != null) {
            projectOrderListService.updateStatusMain(WallfloorConstant.WAll_FLOOR_ISTRUE6, orderId);
            // 2.选材清单--墙地砖信息
            if (flag == null) {
                BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem = new BizMaterialsChoiceBillItem();
                bizMaterialsChoiceBillItem.setMaterialsChoiceBillId(findChoiceBillId.getId());
                materialsChoiceList = projectOrderListService.findMaterialsChoice(findChoiceBillId.getId());

            } else {
                // 2.选材清单--全部信息
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

    /**
     * 查看墙地砖
     *
     * @return
     */
    @RequestMapping(value = "lookWallAndFloorz")
    public String queryWallAndFloorz(Integer orderId, String orderNumber, Model model, String flag) {
        // 根据订单ID查询选材单ID
        BizMaterialsChoiceBill findChoiceBillId = projectOrderListService.findChoiceBillId(orderId);
        List<BizMaterialsChoiceBillItem> materialsChoiceList = null;
        if (findChoiceBillId != null && findChoiceBillId.getId() != null) {
            // 2.选材清单--墙地砖信息
            if (flag == null) {
                BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem = new BizMaterialsChoiceBillItem();
                bizMaterialsChoiceBillItem.setMaterialsChoiceBillId(findChoiceBillId.getId());
                materialsChoiceList = projectOrderListService.findMaterialsChoicez(findChoiceBillId.getId());
                // BizOrderMainMate bizOrderMainMate = new BizOrderMainMate();
                // bizOrderMainMate.setOrderId(orderId);
                // List<BizOrderMainMate> findList =
                // bizOrderMainMateService.findList(bizOrderMainMate);
                // System.err.println(findList.size());
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
                // 2.选材清单--全部信息
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

    /**
     * 主材安装项
     *
     * @return
     */

    @RequiresPermissions("projectOrderList:projectOrderList:edit")
    @RequestMapping(value = "materialInstallItem")
    public String materialInstallItem(Order order, Model model, String flag) {
        // Sasiki ----->: 修改
        List<Order> findList = orderService.findList(order);
        if (null != order.getOrderId()) {
            // Sasiki 修改 是根据orderid 查询对应的安装项, 回显
            List<OrderInstallItemVo> planStatus = null;
            List<OrderInstallItemVo> installItems = null;
            List<OrderInstallItemVo> installItemByStoreId = null;
            // 该门店下工程模式的所有安装项(模板)
            installItemByStoreId = orderService.findInstallItemByStoreId(order);
            // 该订单下的所有安装项
            installItems = orderService.findInstallItemByOrderId(order.getOrderId());

            // ORDERSTATUS:如果订单状态大于等于200
            if (Integer.parseInt(order.getOrderStatusNumber()) >= 200) {
                // ORDERSTATUS----->: 要判断订单下安装项计划的状态是否为 2,3 4如果为2 3 4 不允许修改,
                // 为1时,可以修改

                // 1: 查询该订单下的安装项的状态
                planStatus = orderService.findOrderInstallItemPlanStatus(order.getOrderId());
                if (null != planStatus && planStatus.size() > 0)
                    for (OrderInstallItemVo orderInstallItemVo : planStatus) {

                        // -------------------------------------
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
                        // -------------------------------------
                        if (orderInstallItemVo.getStatus().equals("2") || orderInstallItemVo.getStatus().equals("3") || orderInstallItemVo.getStatus().equals("4") || orderInstallItemVo.getStatus().equals("6") || orderInstallItemVo.getStatus().equals("310") || orderInstallItemVo.getStatus().equals("320") || orderInstallItemVo.getStatus().equals("330")) {
                            // 2: 如果安装项计划中的安装项状态为 2 或者3 4,不允许修改

                            h: for (OrderInstallItemVo vo : installItems) {
                                if (vo.getId().equals(orderInstallItemVo.getId())) {

                                    for (OrderInstallItemVo v : installItemByStoreId) {
                                        // 把这个安装项加个标识
                                        if (v.getProjectInstallItemId().equals(vo.getProjectInstallItemId())) {
                                            v.setStatus("1");
                                            // 安装模式标识
                                            if (orderInstallItemVo.getStatus().equals("3") || orderInstallItemVo.getStatus().equals("4") || orderInstallItemVo.getStatus().equals("310") || orderInstallItemVo.getStatus().equals("320") || orderInstallItemVo.getStatus().equals("330")) {

                                                v.setInstallModeFlag("1");// 为不可修改
                                            }
                                            break h;

                                        }

                                    }

                                }

                            }

                        } else {
                            // 2--->3: 如果为1 可以修改

                        }
                    }

            }
            // ORDERSTATUS:如果订单状态小于200 或者 安装项为1 允许随便改
            // Naruto1 集合
            List<OrderInstallItemVo> naruto = new ArrayList<OrderInstallItemVo>();

            // 进行笛卡尔积比较

            // 该订单下的安装项
            for (OrderInstallItemVo itemVo : installItems) {
                // Naruto1---->:用于判断 是否存在 模板中已经删除的,但删除前该订单已经申请了的安装项
                // 总得模板
                for (OrderInstallItemVo orderInstallItemVo : installItemByStoreId) {

                    if (itemVo.getProjectInstallItemId().equals(orderInstallItemVo.getProjectInstallItemId())) {
                        // 如果一样 标记1
                        orderInstallItemVo.setIsChoosed("1");
                        itemVo.setIsChoosed("1");

                        orderInstallItemVo.setInstallMode(itemVo.getInstallMode());

                        // 如果该安装项有复尺单不能修改
                        Integer checksizeCount = orderService.queryCheckStatus(order.getOrderId(), itemVo.getId());
                        if (null != checksizeCount && checksizeCount > 0) {
                            orderInstallItemVo.setStatus("1");
                        }

                        break;

                    } else {
                        // 不一样, 也不能覆盖之前一样的
                        if (null == orderInstallItemVo.getIsChoosed() || !orderInstallItemVo.getIsChoosed().equals("1")) {

                            // 不一样 标记0
                            orderInstallItemVo.setIsChoosed("0");

                        }

                    }

                }

                // 如果遍历一圈,还没有把该订单下的安装项设置成 isChoosed =="1" 那么表示Naruto1 成立
                if (null == itemVo.getIsChoosed()) {
                    itemVo.setIsChoosed("1");

                    // 如果该安装项的计划中是已转给供应商,已验收, 则不可修改, 不然可修改
                    List<OrderInstallItemVo> status = orderService.findOrderInstallItemPlanStatus(order.getOrderId());

                    if (status.size() > 0) {

                        for (OrderInstallItemVo orderInstallItemVo : status) {
                            if (itemVo.getId().equals(orderInstallItemVo.getId())) {

                                if (Integer.parseInt(orderInstallItemVo.getStatus()) > 1) {
                                    itemVo.setStatus("1");
                                    // 安装模式标识
                                    if (orderInstallItemVo.getStatus().equals("3") || orderInstallItemVo.getStatus().equals("4") || orderInstallItemVo.getStatus().equals("310") || orderInstallItemVo.getStatus().equals("320") || orderInstallItemVo.getStatus().equals("330")) {

                                        itemVo.setInstallModeFlag("1");// 为不可修改
                                    }
                                    break;
                                } else {
                                    itemVo.setStatus("0");
                                    break;
                                }


                            }

                        }
                    }
                    //如果该安装项有复尺单不能修改
                    Integer checksizeCount = orderService.queryCheckStatus(order.getOrderId(), itemVo.getId());
                    if (null != checksizeCount && checksizeCount > 0) {
                        itemVo.setStatus("1");
                    }
                    naruto.add(itemVo);
                }

            }

            // Naruto

            if (naruto.size() > 0) {
                for (OrderInstallItemVo orderInstallItemVo : naruto) {
//                    查询安装项的状态
                    OrderInstallItemVo status = orderService.findInstallStatus(orderInstallItemVo.getId());
                    orderInstallItemVo.setInstallStatus(status.getStatus());
                    orderInstallItemVo.setCreateDate(status.getCreateDate());
                    installItemByStoreId.add(orderInstallItemVo);
                }
            }
            // 以供回显
            model.addAttribute("installItemList", installItemByStoreId);

        } else {
            // 新增
        }
        String isxin = projectOrderListService.queryDealedmainmaterial(order.getOrderNumber());
        model.addAttribute("order", findList.get(0));
        model.addAttribute("flag", flag);
        model.addAttribute("isxin", isxin);
        // model.addAttribute("order", order);
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
            // 删除所有, 同时保存页面上的所有安装项
            orderService.deleteAllInstallItem(order.getOrderId());
            orderService.deleteAllInstallItemPlan(order.getOrderId());
        }

        // flag 1是订单列表 2是工程订单列表
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

    /**
     * 动态加载安装模式
     *
     * @param id
     * @param model
     * @param request
     * @param reponse
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "findInstallModel")
    public String findInstallModel(String id, Model model, HttpServletRequest request, HttpServletResponse reponse) {

        String intallMode = projectOrderListService.findInstallModel(id);
        return intallMode;
    }

}
