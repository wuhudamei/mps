package cn.damei.common.savePhoto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by joseph on 2017/7/25.
 * 获取图片路径
 */

@Service
public class GetBusinessPhoto {


    @Autowired
    private GetBusinessPhotosDao photosDao;


    /**
     * 获取公共图片工具类(不用判断npe)
     * @param relatedBusinessId
     * @param relatedBusinessType
     * @return
     */
    public  List<String> getBusinessPhoto(String relatedBusinessId, String relatedBusinessType) {


     List<String> photos =   photosDao.getBusinessPhotos(relatedBusinessId, relatedBusinessType);


     return photos;
    }

}
