package cn.damei.web.modules;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizOrderInstallAcceptanceDetail;
import cn.damei.entity.modules.BizOrderInstallPlanPic;
import cn.damei.service.modules.BizOrderInstallAcceptanceDetailService;
import cn.damei.service.modules.BizOrderInstallPlanPicService;

/*
 *	订单交底查询
 */
@Controller
@RequestMapping(value = "${adminPath}/bizorderinstallplanpic/bizOrderInstallPlanPic")
public class BizOrderInstallPlanPicController extends BaseController {

    @Autowired
    private BizOrderInstallAcceptanceDetailService bizOrderInstallDetailService;

    @Autowired
    private BizOrderInstallPlanPicService bizOrderInstallPlanPicService;

    @ModelAttribute
    public BizOrderInstallPlanPic get(@RequestParam(required = false) Integer id) {
        BizOrderInstallPlanPic entity = null;
        if (StringUtils.isNotBlank(id + "")) {

            entity = bizOrderInstallPlanPicService.get(id);
        }
        if (entity == null) {
            entity = new BizOrderInstallPlanPic();
        }
        return entity;
    }

    /**
     * 安装项验收明细查看图片
     **/
    @RequestMapping(value = {"installAcceptPhotos", ""})
    public String installAcceptPhotos(BizOrderInstallPlanPic bizOrderInstallPlanPic, HttpServletRequest request, HttpServletResponse response, Model model, String installID, String orderID) throws IOException {
        logger.info("安装项编号：" + installID + "\t订单编号：" + orderID);
        List<BizOrderInstallPlanPic> installPic = null;
        BizOrderInstallAcceptanceDetail install = null;
        if (null != installID && null != orderID) {
            install = bizOrderInstallDetailService.get(Integer.valueOf(orderID));
            installPic = bizOrderInstallPlanPicService.getByIdList(Integer.valueOf(installID));
        }

        model.addAttribute("install", install);
        model.addAttribute("baseUrl", PicRootName.picPrefixName());
        model.addAttribute("installPicList", installPic);
        for (BizOrderInstallPlanPic bizOrderInstallPlanPic2 : installPic) {
            System.err.println("zhangtotnwei_test:" + bizOrderInstallPlanPic2.getPicUrl());
        }
        // /upload/pic/manager/progress/acceptance/20171031/27d239fd356443169f6e5d92302754a4.jpeg
        return "modules/bizEnginInstall/installAcceptPhotos";
    }

    /**
     * ajax安装项验收明细查看图片
     **/
    @RequestMapping(value = "/ajaxinstallAcceptPhotos")
    @ResponseBody
    public Map<Object, Object> ajaxinstallAcceptPhotos(BizOrderInstallPlanPic bizOrderInstallPlanPic, HttpServletRequest request, HttpServletResponse response, Model model, String installID, String orderID) throws IOException {
        logger.info("安装项编号：" + installID + "\t订单编号：" + orderID);
        List<BizOrderInstallPlanPic> installPic = null;
        BizOrderInstallAcceptanceDetail install = null;
        if (null != installID && null != orderID) {
            install = bizOrderInstallDetailService.get(Integer.valueOf(orderID));
            installPic = bizOrderInstallPlanPicService.getByIdList(Integer.valueOf(installID));
        }
        Map<Object, Object> mapObject = new HashMap<Object, Object>();
        mapObject.put("mapObject", installPic);

        // /upload/pic/manager/progress/acceptance/20171031/27d239fd356443169f6e5d92302754a4.jpeg
        return mapObject;
    }

    @RequestMapping(value = {"installDetailPhotos", ""})
    public String installDetailPhotos(BizOrderInstallPlanPic bizOrderInstallPlanPic, HttpServletRequest request, HttpServletResponse response, Model model, String installID, String orderID) throws IOException {
        logger.info("安装项编号：" + installID + "\t订单编号：" + orderID);
        List<BizOrderInstallPlanPic> installPic = null;
        BizOrderInstallAcceptanceDetail install = null;
        if (null != installID && null != orderID) {
            install = bizOrderInstallDetailService.get(Integer.valueOf(orderID));
            installPic = bizOrderInstallPlanPicService.getByIdList(Integer.valueOf(installID));
        }

        model.addAttribute("install", install);
        model.addAttribute("baseUrl", PicRootName.picPrefixName());
        model.addAttribute("installPicList", installPic);
        return "modules/bizEnginInstall/installDetailPhotos";
    }

    @RequestMapping(value = {"installDateComparedPhotos", ""})
    public String installDateComparedPhotos(BizOrderInstallPlanPic bizOrderInstallPlanPic, HttpServletRequest request, HttpServletResponse response, Model model, String installID, String orderID) throws IOException {
        logger.info("安装项编号：" + installID + "\t订单编号：" + orderID);
        List<BizOrderInstallPlanPic> installPic = null;
        BizOrderInstallAcceptanceDetail install = null;
        if (null != installID && null != orderID) {
            install = bizOrderInstallDetailService.get(Integer.valueOf(orderID));
            installPic = bizOrderInstallPlanPicService.getByIdList(Integer.valueOf(installID));
        }

        model.addAttribute("install", install);
        model.addAttribute("baseUrl", PicRootName.picPrefixName());
        model.addAttribute("installPicList", installPic);
        return "modules/bizEnginInstall/installDetailPhotos";
    }
}
