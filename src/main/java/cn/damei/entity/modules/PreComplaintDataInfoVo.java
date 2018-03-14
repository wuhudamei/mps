package cn.damei.entity.modules;

import java.io.Serializable;


public class PreComplaintDataInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String typeId;
    private String itemId;
    private String itemDescribe;
    private String []photos;


    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemDescribe() {
        return itemDescribe;
    }

    public void setItemDescribe(String itemDescribe) {
        this.itemDescribe = itemDescribe;
    }

    public String[] getPhotos() {
        return photos;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }
}
