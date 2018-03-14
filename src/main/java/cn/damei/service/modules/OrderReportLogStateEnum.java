package cn.damei.service.modules;

import cn.damei.common.constantUtils.BizOrderReportConstantUtil;

/**
 * Created by joseph on 2017/5/15.
 */
public enum OrderReportLogStateEnum {

    ORDER_REPORT_LOG_STATE_20(Integer.parseInt(BizOrderReportConstantUtil.REPORT_STATUS_20),BizOrderReportConstantUtil.REPORT_STATUS_20_WORD ),


    ORDER_REPORT_LOG_STATE_25(Integer.parseInt(BizOrderReportConstantUtil.REPORT_STATUS_25),BizOrderReportConstantUtil.REPORT_STATUS_25_WORD ),
    ORDER_REPORT_LOG_STATE_26(Integer.parseInt(BizOrderReportConstantUtil.REPORT_STATUS_26),BizOrderReportConstantUtil.REPORT_STATUS_26_WORD ),
    ORDER_REPORT_LOG_STATE_30(Integer.parseInt(BizOrderReportConstantUtil.REPORT_STATUS_30),BizOrderReportConstantUtil.REPORT_STATUS_30_WORD ),
    ORDER_REPORT_LOG_STATE_40(Integer.parseInt(BizOrderReportConstantUtil.REPORT_STATUS_40),BizOrderReportConstantUtil.REPORT_STATUS_40_WORD ),
    ORDER_REPORT_LOG_STATE_50(Integer.parseInt(BizOrderReportConstantUtil.REPORT_STATUS_50),BizOrderReportConstantUtil.REPORT_STATUS_50_WORD ),
    ORDER_REPORT_LOG_STATE_55(Integer.parseInt(BizOrderReportConstantUtil.REPORT_STATUS_55),BizOrderReportConstantUtil.REPORT_STATUS_55_WORD ),
    ORDER_REPORT_LOG_STATE_90(Integer.parseInt(BizOrderReportConstantUtil.REPORT_STATUS_90),BizOrderReportConstantUtil.REPORT_STATUS_90_WORD );

    private int value;





    private String description;







    private OrderReportLogStateEnum(int value, String description) {

        this.value = value;

        this.description = description;

    }



    public int value() {

        return value;

    }

    public String description() {

        return description;

    }





    public static OrderReportLogStateEnum valueOf(int value) {

        for(OrderReportLogStateEnum type : OrderReportLogStateEnum.values()) {

            if(type.value() == value) {

                return type;

            }

        }

        return null;

    }
}
