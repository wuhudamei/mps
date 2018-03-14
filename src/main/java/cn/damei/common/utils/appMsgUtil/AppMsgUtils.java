package cn.damei.common.utils.appMsgUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by joseph on 2017/7/31.
 */
@Service
public class AppMsgUtils {






    @Autowired
    private AppMsgUtilDao msgUtilDao;


    /**
     * 保存消息内容,并返回主键
     * @param entity
     * @return
     */
    public  Integer saveMsgContent(AppMsgEntity entity){


        return  msgUtilDao.saveAppMsgContent(entity);

    }




      /**
     * 根据业务id和类型查询是否插入 (1为插入,0为没插入)
     * @param relatedBusinessType
     * @param relatedBusinessId
     * @return
     */
      public  int checkIsExistByTypeAndId(final String relatedBusinessType,final String relatedBusinessId){

        return msgUtilDao.checkIsExistByTypeAndId(relatedBusinessType,relatedBusinessId);

    }


    public    void batchSaveAppMsgContent(List<AppMsgEntity> list){


          msgUtilDao.batchSaveAppMsgContent(list);
    }


}
