package cn.damei.web.modules;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.mobile.Manager.BizOrderTaskpackageProcedure;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.OrderFloor2;
import cn.damei.service.modules.OrderFloorService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "${adminPath}/orderfloor/orderFloor")
public class OrderFloorController2 extends BaseController {

    @Autowired
    private OrderFloorService orderFloorService;

    @Autowired
    private BizEmployeeService2 bizEmployeeService2;

    @RequestMapping(value = "openOrderFloorPage")
    public String openOrderFloorPage(OrderFloor2 orderFloor, HttpServletRequest request, HttpServletResponse response, Model model){
        User user = UserUtils.getUser();

        if (null == orderFloor.getEnginDepartId()) {
            if (null != UserUtils.getUser().getEmpId()) {
                List<Integer> list = bizEmployeeService2
                        .findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
                if (list != null && list.size() > 0) {
                    orderFloor.setEnginDepartIds(list);
                } else {
                    orderFloor.setEnginDepartIds(null);
                }
            } else {
                orderFloor.setEnginDepartIds(null);
            }
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(orderFloor.getEnginDepartId());
            orderFloor.setEnginDepartIds(list);
        }

        if (null == orderFloor.getStoreId()) {
            if (null != user.getStoreId()) {
                orderFloor.setStoreId(Integer.valueOf(user.getStoreId()));
            }
        }
        if (StringUtils.isBlank(user.getStoreId())) {
            model.addAttribute("storeDropEnable", true);
        }

        if (StringUtils.isBlank(orderFloor.getProjectMode())) {
            if (null != user.getEmpId()) {
                BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
                if (StringUtils.isBlank(be.getProjectMode())) {
                    model.addAttribute("gongcheng", true);
                } else {
                    if (be.getProjectMode().equals("3")) {
                        model.addAttribute("gongcheng", true);
                    } else {
                        orderFloor.setProjectMode(be.getProjectMode());
                    }
                }
            } else {
                model.addAttribute("gongcheng", true);
            }
        } else {
            if (null != user.getEmpId()) {
                BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
                if (StringUtils.isBlank(be.getProjectMode())) {
                    model.addAttribute("gongcheng", true);
                } else {
                    if (be.getProjectMode().equals("3")) {
                        model.addAttribute("gongcheng", true);
                    } else {
                        orderFloor.setProjectMode(be.getProjectMode());
                    }
                }
            } else {
                model.addAttribute("gongcheng", true);
            }
        }
        model.addAttribute("orderFloor",orderFloor);
        return "modules/orderFloor/orderFloorList";
    }

    @RequestMapping(value = "queryOrderFloor")
    public String queryOrderFloor(OrderFloor2 orderFloor, HttpServletRequest request, HttpServletResponse response, Model model){
        User user = UserUtils.getUser();

        if (null == orderFloor.getEnginDepartId()) {
            if (null != UserUtils.getUser().getEmpId()) {
                List<Integer> list = bizEmployeeService2
                        .findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
                if (list != null && list.size() > 0) {
                    orderFloor.setEnginDepartIds(list);
                } else {
                    orderFloor.setEnginDepartIds(null);
                }
            } else {
                orderFloor.setEnginDepartIds(null);
            }
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(orderFloor.getEnginDepartId());
            orderFloor.setEnginDepartIds(list);
        }

        if (null == orderFloor.getStoreId()) {
            if (null != user.getStoreId()) {
                orderFloor.setStoreId(Integer.valueOf(user.getStoreId()));
            }
        }
        if (StringUtils.isBlank(user.getStoreId())) {
            model.addAttribute("storeDropEnable", true);
        }

        if (StringUtils.isBlank(orderFloor.getProjectMode())) {
            if (null != user.getEmpId()) {
                BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
                if (StringUtils.isBlank(be.getProjectMode())) {
                    model.addAttribute("gongcheng", true);
                } else {
                    if (be.getProjectMode().equals("3")) {
                        model.addAttribute("gongcheng", true);
                    } else {
                        orderFloor.setProjectMode(be.getProjectMode());
                    }
                }
            } else {
                model.addAttribute("gongcheng", true);
            }
        } else {
            if (null != user.getEmpId()) {
                BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
                if (StringUtils.isBlank(be.getProjectMode())) {
                    model.addAttribute("gongcheng", true);
                } else {
                    if (be.getProjectMode().equals("3")) {
                        model.addAttribute("gongcheng", true);
                    } else {
                        orderFloor.setProjectMode(be.getProjectMode());
                    }
                }
            } else {
                model.addAttribute("gongcheng", true);
            }
        }
        Page<OrderFloor2> page = orderFloorService.findPage(new Page<OrderFloor2>(request, response), orderFloor);
        model.addAttribute("page",page);
        model.addAttribute("orderFloor", orderFloor);
        return "modules/orderFloor/orderFloorList";
    }


    @RequestMapping(value="queryProduceInfoByParam")
    @ResponseBody
    public List<BizOrderTaskpackageProcedure> queryProduceInfoByParam(int taskpackageId,int type){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("taskpackageId",taskpackageId);
        List<String> procedureNos = new ArrayList<String>();
        if(type == 1){
            procedureNos.add("GX000041");
        }else if(type == 2){
            procedureNos.add("GX000268");
            procedureNos.add("GX000269");
            procedureNos.add("GX000267");
            procedureNos.add("GX000266");
            procedureNos.add("GX000264");
        }
        param.put("procedureNos",procedureNos);
        return orderFloorService.queryProduceInfoByParam(param);
    }
}
