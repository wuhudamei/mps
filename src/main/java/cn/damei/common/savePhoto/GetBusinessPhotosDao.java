package cn.damei.common.savePhoto;

import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * Created by joseph on 2017/7/25.
 */
@MyBatisDao
public interface GetBusinessPhotosDao {


    List<String> getBusinessPhotos(String relatedBusinessId, String relatedBusinessType);
}
