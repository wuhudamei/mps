package cn.damei.web.modules;

import cn.damei.Quartz.OrderComplaintMsgAndPhoneQuartz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@Controller
@RequestMapping(value="${adminPath}/test666")
public class TestQuartsController {

    @Autowired
    private OrderComplaintMsgAndPhoneQuartz quartz;



    @RequestMapping(value="")
    public void test(HttpServletResponse response) throws IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
       String result = quartz.execute2();
        response.getWriter().write("执行完毕     " +  result);


    }
}
