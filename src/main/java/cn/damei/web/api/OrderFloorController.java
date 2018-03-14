package cn.damei.web.api;

import cn.damei.common.constantUtils.BizOrderReportConstantUtil;
import cn.damei.common.utils.KeyAuthenticateUtils2;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.OrderFloor;
import cn.damei.service.modules.OrderFloorService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "${adminPath}/api/orderFloor")
public class OrderFloorController extends BaseController {

    @Autowired
    private OrderFloorService orderFloorService;

    @RequestMapping(value = "findOrderFloorByOrderNumber", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody Object findOrderFloorByOrderNumber(@RequestParam String orderNumber, String key){
        Map<String, Object> map = new HashMap<String, Object>();
        if (orderNumber == null || key == null) {
            map.put("code", "0");
            map.put("message", "必填参数为空");
            return JSONObject.fromObject(map).toString();
        }
        String[] paramArr = new String[] { "orderNumber=" + orderNumber };
        String myKey = KeyAuthenticateUtils2.getKey(paramArr, BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
        if (!myKey.equals(key)) {
            map.put("code", "0");
            map.put("message", "签名认证失败");
            return JSONObject.fromObject(map).toString();
        }

       OrderFloor orderFloor = orderFloorService.findOrderFloorByOrderNumber(orderNumber);
       if(orderFloor == null){
           map.put("code", "0");
           map.put("message", "不存在有效数据");
           return JSONObject.fromObject(map).toString();
       }

        map.put("code", "1");
        map.put("message", "成功");
        map.put("orderFloor",orderFloor);
        return JSONObject.fromObject(map).toString();
    }
}
