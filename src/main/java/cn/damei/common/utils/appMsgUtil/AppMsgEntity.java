package cn.damei.common.utils.appMsgUtil;

import cn.damei.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public final class AppMsgEntity implements Serializable {

    private static final long serialVersionUID = 1L;

private Integer msgId;
    private String msgTitle;
    private Date msgDateTime;
    private String msgContent;
    private String msgType;
    private String relatedBusinessType;
    private String relatedBusinessId;
    private Integer employeeId;


    public String getMsgTitle()

    {

        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public Date getMsgDateTime() {
        return msgDateTime;
    }

    public void setMsgDateTime(Date msgDateTime) {
        this.msgDateTime = msgDateTime;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getRelatedBusinessType() {
        return relatedBusinessType;
    }

    public void setRelatedBusinessType(String relatedBusinessType) {
        this.relatedBusinessType = relatedBusinessType;
    }

    public String getRelatedBusinessId() {
        return relatedBusinessId;
    }

    public void setRelatedBusinessId(String relatedBusinessId) {
        this.relatedBusinessId = relatedBusinessId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppMsgEntity that = (AppMsgEntity) o;
        return Objects.equals(msgTitle, that.msgTitle) &&
                Objects.equals(msgDateTime, that.msgDateTime) &&
                Objects.equals(msgContent, that.msgContent) &&
                Objects.equals(msgType, that.msgType) &&
                Objects.equals(relatedBusinessType, that.relatedBusinessType) &&
                Objects.equals(relatedBusinessId, that.relatedBusinessId) &&
                Objects.equals(employeeId, that.employeeId)&&
                Objects.equals(msgId, that.msgId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(msgTitle, msgDateTime, msgContent, msgType, relatedBusinessType, relatedBusinessId, employeeId,msgId);
    }
}
