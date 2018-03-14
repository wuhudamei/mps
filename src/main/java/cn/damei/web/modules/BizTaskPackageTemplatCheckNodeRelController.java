
package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.mapper.JsonMapper;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.service.modules.CheckNodeService;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import cn.damei.entity.modules.BizTaskPackageTemplat;
import cn.damei.service.modules.BizTaskPackageTemplatService;
import cn.damei.entity.modules.BizTaskPackageTemplatCheckNodeRel;
import cn.damei.service.modules.BizTaskPackageTemplatCheckNodeRelService;


@Controller
@RequestMapping(value = "${adminPath}/taskpackagetemplatchecknoderel/bizTaskPackageTemplatCheckNodeRel")
public class BizTaskPackageTemplatCheckNodeRelController extends BaseController {

    @Autowired
    private BizTaskPackageTemplatCheckNodeRelService bizTaskPackageTemplatCheckNodeRelService;
    @Autowired
    private CheckNodeService checkNodeService;

    @Autowired
    private BizEmployeeService2 bizEmployeeService2;


    @ModelAttribute
    public BizTaskPackageTemplatCheckNodeRel get(@RequestParam(required = false) Integer id) {
        BizTaskPackageTemplatCheckNodeRel entity = null;
        if (id != null) {
            entity = bizTaskPackageTemplatCheckNodeRelService.get(id);
        }
        if (entity == null) {
            entity = new BizTaskPackageTemplatCheckNodeRel();
        }
        return entity;
    }

    @RequiresPermissions("taskpackagetemplatchecknoderel:bizTaskPackageTemplatCheckNodeRel:view")
    @RequestMapping(value = "listPage")
    public String listPage(BizTaskPackageTemplatCheckNodeRel bizTaskPackageTemplatCheckNodeRel, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (UserUtils.getUser().getStoreId() != null) {

            bizTaskPackageTemplatCheckNodeRel.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
        } else if (bizTaskPackageTemplatCheckNodeRel.getStoreId() != null) {

            if (bizTaskPackageTemplatCheckNodeRel.getStoreId().equals(1)) {

                bizTaskPackageTemplatCheckNodeRel.setStoreId(null);
            }
        }
        return "modules/taskpackagetemplatchecknoderel/bizTaskPackageTemplatCheckNodeRelList";
    }

    @RequiresPermissions("taskpackagetemplatchecknoderel:bizTaskPackageTemplatCheckNodeRel:view")
    @RequestMapping(value = {"list", ""})
    public String list(BizTaskPackageTemplatCheckNodeRel bizTaskPackageTemplatCheckNodeRel, HttpServletRequest request, HttpServletResponse response, Model model) {
        User user = UserUtils.getUser();

        if (null == bizTaskPackageTemplatCheckNodeRel.getStoreId()) {
            if (null != user.getStoreId()) {
                bizTaskPackageTemplatCheckNodeRel.setStoreId(Integer.valueOf(user.getStoreId()));
            }
        }
        if (StringUtils.isBlank(user.getStoreId())) {
            model.addAttribute("storeDropEnable", true);
        }

        if (null == bizTaskPackageTemplatCheckNodeRel.getProjectMode()) {
            if (null != user.getEmpId()) {
                BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
                if (StringUtils.isBlank(be.getProjectMode())) {
                    model.addAttribute("gongcheng", true);
                } else {
                    if (be.getProjectMode().equals("3")) {
                        model.addAttribute("gongcheng", true);
                    } else {
                        bizTaskPackageTemplatCheckNodeRel.setProjectMode(Integer.parseInt(be.getProjectMode()));
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
                        bizTaskPackageTemplatCheckNodeRel.setProjectMode(Integer.parseInt(be.getProjectMode()));
                    }
                }
            } else {
                model.addAttribute("gongcheng", true);
            }
        }
        Page<BizTaskPackageTemplatCheckNodeRel> page = bizTaskPackageTemplatCheckNodeRelService.findPage(new Page<BizTaskPackageTemplatCheckNodeRel>(request, response), bizTaskPackageTemplatCheckNodeRel);
        model.addAttribute("page", page);
        return "modules/taskpackagetemplatchecknoderel/bizTaskPackageTemplatCheckNodeRelList";
    }

    @RequiresPermissions("taskpackagetemplatchecknoderel:bizTaskPackageTemplatCheckNodeRel:view")
    @RequestMapping(value = "form")
    public String form(BizTaskPackageTemplatCheckNodeRel bizTaskPackageTemplatCheckNodeRel, Model model) {
        User user = UserUtils.getUser();

        if (null == bizTaskPackageTemplatCheckNodeRel.getStoreId()) {
            if (null != user.getStoreId()) {
                bizTaskPackageTemplatCheckNodeRel.setStoreId(Integer.valueOf(user.getStoreId()));
            }
        }
        if (StringUtils.isBlank(user.getStoreId())) {
            model.addAttribute("storeDropEnable", true);
        }

        if (null == bizTaskPackageTemplatCheckNodeRel.getProjectMode()) {
            if (null != user.getEmpId()) {
                BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
                if (StringUtils.isBlank(be.getProjectMode())) {
                    model.addAttribute("gongcheng", true);
                } else {
                    if (be.getProjectMode().equals("3")) {
                        model.addAttribute("gongcheng", true);
                    } else {
                        bizTaskPackageTemplatCheckNodeRel.setProjectMode(Integer.parseInt(be.getProjectMode()));
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
                        bizTaskPackageTemplatCheckNodeRel.setProjectMode(Integer.parseInt(be.getProjectMode()));
                    }
                }
            } else {
                model.addAttribute("gongcheng", true);
            }
        }
        model.addAttribute("bizTaskPackageTemplatCheckNodeRel", bizTaskPackageTemplatCheckNodeRel);
        return "modules/taskpackagetemplatchecknoderel/bizTaskPackageTemplatCheckNodeRelForm";
    }


    @RequestMapping(value = "nodeListByStoreId")
    public @ResponseBody
    String nodeListByStoreId(String storeid, String projectMode, HttpServletRequest request, HttpServletResponse response,
                             Model model) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeid", storeid);
        map.put("projectMode", projectMode);
        List<DropModel> list = checkNodeService.queryNodeListByStoreId(map);
        return JsonMapper.getInstance().toJson(list);
    }


    @RequestMapping(value = "updateStatus")
    public String updateStatus(Integer id, String status) {

        bizTaskPackageTemplatCheckNodeRelService.updateStatus(id, status);

        return "redirect:" + Global.getAdminPath() + "/taskpackagetemplatchecknoderel/bizTaskPackageTemplatCheckNodeRel/list";
    }


    @RequestMapping(value = "addTaskpackageNodeRel")
    public String addTaskpackageNodeRel(BizTaskPackageTemplatCheckNodeRel bizTaskPackageTemplatCheckNodeRel, HttpServletRequest request, HttpServletResponse response, Model model) {
        User user = UserUtils.getUser();

        if (null == bizTaskPackageTemplatCheckNodeRel.getStoreId()) {
            if (null != user.getStoreId()) {
                bizTaskPackageTemplatCheckNodeRel.setStoreId(Integer.valueOf(user.getStoreId()));
            }
        }
        if (StringUtils.isBlank(user.getStoreId())) {
            model.addAttribute("storeDropEnable", true);
        }

        if (null == bizTaskPackageTemplatCheckNodeRel.getProjectMode()) {
            if (null != user.getEmpId()) {
                BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
                if (StringUtils.isBlank(be.getProjectMode())) {
                    model.addAttribute("gongcheng", true);
                } else {
                    if (be.getProjectMode().equals("3")) {
                        model.addAttribute("gongcheng", true);
                    } else {
                        bizTaskPackageTemplatCheckNodeRel.setProjectMode(Integer.parseInt(be.getProjectMode()));
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
                        bizTaskPackageTemplatCheckNodeRel.setProjectMode(Integer.parseInt(be.getProjectMode()));
                    }
                }
            } else {
                model.addAttribute("gongcheng", true);
            }
        }
        model.addAttribute("bizTaskPackageTemplatCheckNodeRel", bizTaskPackageTemplatCheckNodeRel);
        return "modules/taskpackagetemplatchecknoderel/addNode";
    }

    @Autowired
    private BizTaskPackageTemplatService bizTaskPackageTemplatService;

    @RequestMapping(value = "taskpackageList")
    public @ResponseBody
    String taskpackageList(String storeid, String projectMode) {
        List<BizTaskPackageTemplat> list = bizTaskPackageTemplatService.queryByStoreId(Integer.parseInt(storeid), Integer.parseInt(projectMode));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeid", storeid);
        map.put("projectMode", projectMode);
        List<DropModel> list1 = checkNodeService.queryNodeListByStoreId(map);
        List<List> list2 = new ArrayList<List>();
        list2.add(list);
        list2.add(list1);
        return JsonMapper.getInstance().toJson(list2);
    }

    @RequestMapping(value = "add")
    public String add(String[] ids, String[] qcCheckNodeIds, String storeId, HttpServletRequest request, HttpServletResponse response) {
        if (ids != null && ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                BizTaskPackageTemplatCheckNodeRel nodeRel = new BizTaskPackageTemplatCheckNodeRel();
                String status = request.getParameter("status" + i);
                nodeRel.setStatus(status);
                nodeRel.setQcCheckNodeId(Integer.parseInt(qcCheckNodeIds[i]));
                nodeRel.setTaskPackageId(Integer.parseInt(ids[i]));
                nodeRel.setStoreId(Integer.parseInt(storeId));
                bizTaskPackageTemplatCheckNodeRelService.insert(nodeRel);
            }
        }

        return "redirect:" + Global.getAdminPath() + "/taskpackagetemplatchecknoderel/bizTaskPackageTemplatCheckNodeRel/?repage";
    }

    @RequiresPermissions("taskpackagetemplatchecknoderel:bizTaskPackageTemplatCheckNodeRel:edit")
    @RequestMapping(value = "save")
    public String save(BizTaskPackageTemplatCheckNodeRel bizTaskPackageTemplatCheckNodeRel, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, bizTaskPackageTemplatCheckNodeRel)) {
            return form(bizTaskPackageTemplatCheckNodeRel, model);
        }
        bizTaskPackageTemplatCheckNodeRelService.save(bizTaskPackageTemplatCheckNodeRel);
        addMessage(redirectAttributes, "保存付款单付款尾款节点设置成功");
        return "redirect:" + Global.getAdminPath() + "/taskpackagetemplatchecknoderel/bizTaskPackageTemplatCheckNodeRel/?repage";
    }

    @RequiresPermissions("taskpackagetemplatchecknoderel:bizTaskPackageTemplatCheckNodeRel:edit")
    @RequestMapping(value = "delete")
    public String delete(BizTaskPackageTemplatCheckNodeRel bizTaskPackageTemplatCheckNodeRel, RedirectAttributes redirectAttributes) {
        bizTaskPackageTemplatCheckNodeRelService.delete(bizTaskPackageTemplatCheckNodeRel);
        addMessage(redirectAttributes, "删除付款单付款尾款节点设置成功");
        return "redirect:" + Global.getAdminPath() + "/taskpackagetemplatchecknoderel/bizTaskPackageTemplatCheckNodeRel/?repage";
    }

}