package cn.damei.common.ProjectIssueUtil.Purchase;

import cn.damei.common.utils.DateUtils;
import cn.damei.entity.mobile.Inspector.ReCheckCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
@Transactional(readOnly = true)
public class PurchaseCodeUtils {



    @Autowired
    private    PurchaseCodeDao dao;






    @Transactional(readOnly = false)
    public      String qcBillCode() {


        StringBuilder builder = new StringBuilder();
        Date date =new Date();

        ReCheckCode code1 = dao.getCode();

        if (null == code1) {
            dao.saveCode();
            code1 = dao.getCode();
        }


        if(!DateUtils.isSameDay(date,code1.getGenerateTime())){

            code1.setGenerateTime(date);
            code1.setLastSeiralnum(1);

        }else{
            code1.setLastSeiralnum((code1.getLastSeiralnum() + 1));
            code1.setGenerateTime(date);
        }


        dao.updateCode(code1);
        String code = String.valueOf(code1.getLastSeiralnum());
        builder.append(code1.getBussinessType());
        builder.append(new SimpleDateFormat("yyyyMMdd").format(date));


        if (code.length() == 1) {

            builder.append("000").append(code);

        } else if (code.length() == 2) {

            builder.append("00").append(code);
        } else if (code.length() == 3) {
            builder.append("0").append(code);
        } else if (code.length() >= 4) {
            builder.append(code);
        }



        return builder.toString();

    }

}
