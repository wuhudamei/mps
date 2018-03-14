/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

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
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.CheckNode;
import cn.damei.service.modules.CheckNodeService;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 约检节点设置Controller
 *
 * @author 梅浩
 * @version 2016-10-26
 */
@Controller
@RequestMapping(value = "${adminPath}/checknode/checkNode")
public class CheckNodeController extends BaseController {

    @Autowired
    private CheckNodeService checkNodeService;
    @Autowired
    private BizEmployeeService2 bizEmployeeService2;

    @ModelAttribute
    public CheckNode get(@RequestParam(required = false) String id) {
        CheckNode entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = checkNodeService.get(Integer.parseInt(id));
        }
        if (entity == null) {
            entity = new CheckNode();
        }
        return entity;
    }

    @RequiresPermissions("checknode:checkNode:view")
    @RequestMapping(value = {"list", ""})
    public String list(CheckNode checkNode, HttpServletRequest request, HttpServletResponse response, Model model) {

        User user = UserUtils.getUser();
        //过滤门店
        if (null == checkNode.getStoreId()) {
            if (null != user.getStoreId()) {
                checkNode.setStoreId(Integer.valueOf(user.getStoreId()));
            }
        }
        if (StringUtils.isBlank(user.getStoreId())) {
            model.addAttribute("storeDropEnable", true);
        }
        //过滤工程模式
        /*if (StringUtils.isBlank(checkNode.getProjectMode())) {
            if (null != user.getEmpId()) {
                BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
                if (StringUtils.isBlank(be.getProjectMode())) {
                    model.addAttribute("gongcheng", true);
                } else {
                    if (be.getProjectMode().equals("3")) {
                        model.addAttribute("gongcheng", true);
                    } else {
                        checkNode.setProjectMode(be.getProjectMode());
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
                        checkNode.setProjectMode(be.getProjectMode());
                    }
                }
            } else {
                model.addAttribute("gongcheng", true);
            }
        }*/

        if (null != user.getEmpId()) {
            BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
            if (StringUtils.isBlank(be.getProjectMode())) {
                model.addAttribute("gongcheng", true);
            } else {
                if (be.getProjectMode().equals("3")) {
                    model.addAttribute("gongcheng", true);
                } else {
                    checkNode.setProjectMode(be.getProjectMode());
                }
            }
        } else {
            model.addAttribute("gongcheng", true);
        }
        return "modules/checknode/checkNodeList";
    }

    @RequiresPermissions("checknode:checkNode:view")
    @RequestMapping(value = {"checknodeList", ""})
    public String checknodeList(CheckNode checkNode, HttpServletRequest request, HttpServletResponse response, Model model) {

        User user = UserUtils.getUser();
        //过滤门店
        if (null == checkNode.getStoreId()) {
            if (null != user.getStoreId()) {
                checkNode.setStoreId(Integer.valueOf(user.getStoreId()));
            }
        }
        if (StringUtils.isBlank(user.getStoreId())) {
            model.addAttribute("storeDropEnable", true);
        }
        //过滤工程模式
        /*if (StringUtils.isBlank(checkNode.getProjectMode())) {
            if (null != user.getEmpId()) {
                BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
                if (StringUtils.isBlank(be.getProjectMode())) {
                    model.addAttribute("gongcheng", true);
                } else {
                    if (be.getProjectMode().equals("3")) {
                        model.addAttribute("gongcheng", true);
                    } else {
                        checkNode.setProjectMode(be.getProjectMode());
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
                        checkNode.setProjectMode(be.getProjectMode());
                    }
                }
            } else {
                model.addAttribute("gongcheng", true);
            }
        }*/

        if (null != user.getEmpId()) {
            BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
            if (StringUtils.isBlank(be.getProjectMode())) {
                model.addAttribute("gongcheng", true);
            } else {
                if (be.getProjectMode().equals("3")) {
                    model.addAttribute("gongcheng", true);
                } else {
                    checkNode.setProjectMode(be.getProjectMode());
                }
            }
        } else {
            model.addAttribute("gongcheng", true);
        }

        Page<CheckNode> page = checkNodeService.findPage(new Page<CheckNode>(request, response), checkNode);
        model.addAttribute("page", page);
        return "modules/checknode/checkNodeList";
    }

    @RequiresPermissions("checknode:checkNode:view")
    @RequestMapping(value = "form")
    public String form(CheckNode checkNode, Model model) {
//		//默认查询北京门店
//		if(null!=checkNode.getStoreId()){
//			//修改  jsp ajax 动态加载节点
//			
//		}else{
//			//默认北京门店
//			List<CheckNode> consList = checkNodeService.findConsList(2);
//			model.addAttribute("consList", consList);
//		}
        model.addAttribute("readOnly", UserUtils.getUser().getProjectMode());
        model.addAttribute("checkNode", checkNode);
        return "modules/checknode/checkNodeForm";
    }

    @RequiresPermissions("checknode:checkNode:edit")
    @RequestMapping(value = "save")
    public String save(CheckNode checkNode, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, checkNode)) {
            return form(checkNode, model);
        }
        checkNodeService.save(checkNode);
        addMessage(redirectAttributes, "保存约检节点成功");
        return "redirect:" + Global.getAdminPath() + "/checknode/checkNode/checknodeList?repage";
    }

    @RequiresPermissions("checknode:checkNode:edit")
    @RequestMapping(value = "delete")
    public String delete(CheckNode checkNode, RedirectAttributes redirectAttributes) {






        if (checkNode.getStatus().equals("1")) {

            Integer count =checkNodeService.checkIsOkForDelete(checkNode.getId());
           if( null!=count&&count>0){

               addMessage(redirectAttributes, "启用约检节点失败,同门店同模式下不能有多个基装约检节点");

            }else{
               checkNodeService.delete(checkNode);
               addMessage(redirectAttributes, "启用约检节点成功");
           }



        } else {
            checkNodeService.delete(checkNode);

            addMessage(redirectAttributes, "停用约检节点成功");


        }

        return "redirect:" + Global.getAdminPath() + "/checknode/checkNode/checknodeList?repage&storeId="+checkNode.getStoreId()+"&projectMode="+checkNode.getProjectMode();
    }

    /**
     * 根据门店动态加载约检节点
     */
    @RequestMapping(value = "findConsByStoreId")
    public @ResponseBody
    List<CheckNode> findConsByStoreId(String storeId, String projectMode) {
        CheckNode node = new CheckNode();
        if (null != storeId && storeId != "") {
            node.setStoreId(Integer.valueOf(storeId));
        }
        node.setProjectMode(projectMode);
        //节点list
        List<CheckNode> consList = checkNodeService.findConsList(node);

        if (null != consList && consList.size() > 0) {


            return consList;
        } else {

            return null;
        }

    }

    /**
     * 根据门店和工程模式查询约检节点信息
     *
     * @param storeId
     * @param projectMode
     * @return hyh
     */
    @RequestMapping(value = "findCheckNodeByStoreIdAndProjectMode")
    public @ResponseBody
    List<DropModel> findCheckNodeByStoreIdAndProjectMode(String storeId, String projectMode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeid", storeId);
        map.put("projectMode", projectMode);
        return checkNodeService.queryNodeListByStoreId(map);
    }


    /**
     * 根据门店和工程模式查询是否可以选择基装节点
     *
     * @param storeId
     * @param projectMode
     * @return hyh
     */
    @RequestMapping(value = "checkIsOkForBasicNode")
    public @ResponseBody
    String checkIsOkForBasicNode(String storeId, String projectMode) {
        String isOk = checkNodeService.checkIsOkForBasicNode(storeId, projectMode);
        return isOk;
    }


}