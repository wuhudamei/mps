package cn.damei.common.savePhoto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class GetBusinessPhoto {


    @Autowired
    private GetBusinessPhotosDao photosDao;



    public  List<String> getBusinessPhoto(String relatedBusinessId, String relatedBusinessType) {


     List<String> photos =   photosDao.getBusinessPhotos(relatedBusinessId, relatedBusinessType);


     return photos;
    }

}
