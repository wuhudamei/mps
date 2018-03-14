package cn.damei.entity.mobile.Inspector;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by joseph on 2017/6/13.
 */
public class PqcOrderEntity  implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer orderId; //订单id
    private String customerAddressInfo;//客户地址信息  :小区:楼:单元:门牌号
    private String customerName;
    private String customerPhone;
    private String managerName;
    private String managerPhone;
    private String actualStartDate; //订单实际开工日期 (在mysql format)



    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCustomerAddressInfo() {
        return customerAddressInfo;
    }

    public void setCustomerAddressInfo(String customerAddressInfo) {
        this.customerAddressInfo = customerAddressInfo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(String actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PqcOrderEntity that = (PqcOrderEntity) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(customerAddressInfo, that.customerAddressInfo) &&
                Objects.equals(customerName, that.customerName) &&
                Objects.equals(customerPhone, that.customerPhone) &&
                Objects.equals(managerName, that.managerName) &&
                Objects.equals(managerPhone, that.managerPhone) &&
                Objects.equals(actualStartDate, that.actualStartDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerAddressInfo, customerName, customerPhone, managerName, managerPhone, actualStartDate);
    }


}
