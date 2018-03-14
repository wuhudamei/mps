package cn.damei.web.api;

import com.google.common.collect.Maps;
import cn.damei.common.constantUtils.BizOrderReportConstantUtil;
import cn.damei.common.utils.KeyAuthenticateUtils2;
import cn.damei.service.modules.OrderService2;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.List;
/**
 * <dl>
 * <dd>描述:查询全国已竣工订单接口</dd>
 * <dd>公司: 智装</dd>
 * <dd>创建时间：2017-10-16</dd>
 * <dd>创建人：wangdan</dd>
 * </dl>
 */
@Controller
@RequestMapping(value = "${adminPath}/api/completeCheck")
public class CompleteCheckController {

    @Autowired
    private OrderService2 orderService2;


    /**
     *
     * 查询已竣工订单数据
     * @return
     */
    @RequestMapping(value ="/findOrder",
            method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody Object findOrder(){
        Map<String, Object> map = Maps.newHashMap();
        List<Map<String ,String>> getFindList = this.orderService2.findOrder();
        if(getFindList == null || getFindList.size() == 0){
            map.put("code", "1");
            map.put("message", "不存在有效数据");
            return JSONObject.fromObject(map).toString();
        }
        map.put("code", "1");
        map.put("message", "成功");
        map.put("data",getFindList);
        return JSONObject.fromObject(map).toString();
    }


    /**
     *
     * 查询每天已竣工订单数据
     * @param actualEndDate
     * @return
     */
    @RequestMapping(value ="/orderByActualEndDate",
            method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody Object orderByActualEndDate(@RequestParam String actualEndDate,
                                                       String key){
        Map<String, Object> map = Maps.newHashMap();
        if (actualEndDate == null || key == null ) {
            map.put("code", "0");
            map.put("message", "必填参数为空");
            return JSONObject.fromObject(map).toString();
        }
        String[] paramArr = new String[] { "actualEndDate=" + actualEndDate};
        String key1 = KeyAuthenticateUtils2.getKey(paramArr, BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
        if(!key1.equals(key)){
            map.put("code", "0");
            map.put("message", "签名认证失败");
            return JSONObject.fromObject(map).toString();
        }
        List<Map<String ,String>> getFindList = this.orderService2.orderByActualEndDate(actualEndDate);
        if(getFindList == null || getFindList.size() == 0){
            map.put("code", "1");
            map.put("message", "不存在有效数据");
            return JSONObject.fromObject(map).toString();
        }
        map.put("code", "1");
        map.put("message", "成功");
        map.put("data",getFindList);
        return JSONObject.fromObject(map).toString();
    }

}
