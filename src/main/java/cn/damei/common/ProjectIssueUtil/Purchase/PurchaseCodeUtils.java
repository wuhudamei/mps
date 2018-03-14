package cn.damei.common.ProjectIssueUtil.Purchase;

import cn.damei.common.utils.DateUtils;
import cn.damei.entity.mobile.Inspector.ReCheckCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by joseph on 2017/7/8.
 */
@Service
@Transactional(readOnly = true)
public class PurchaseCodeUtils {



    @Autowired
    private    PurchaseCodeDao dao;





    /**
     * 质检单编号
     *
     * @return
     */
    @Transactional(readOnly = false)
    public      String qcBillCode() {
        // 以ZJ开头

        StringBuilder builder = new StringBuilder();
        Date date =new Date();
        // num
        ReCheckCode code1 = dao.getCode();

        if (null == code1) {
            dao.saveCode();
            code1 = dao.getCode();
        }

        //如果不是同一天
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

        // 判断长度
        if (code.length() == 1) {

            builder.append("000").append(code);

        } else if (code.length() == 2) {
            // 拼接采购单编号
            builder.append("00").append(code);
        } else if (code.length() == 3) {
            builder.append("0").append(code);
        } else if (code.length() >= 4) {
            builder.append(code);
        }


        // 返回采购单编号
        return builder.toString();

    }

}
