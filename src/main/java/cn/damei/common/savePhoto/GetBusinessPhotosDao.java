package cn.damei.common.savePhoto;

import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;


@MyBatisDao
public interface GetBusinessPhotosDao {


    List<String> getBusinessPhotos(String relatedBusinessId, String relatedBusinessType);
}
