package cn.damei.common.utils.appMsgUtil;

import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;



@MyBatisDao
public interface AppMsgUtilDao {



    Integer saveAppMsgContent(AppMsgEntity entity);



    int checkIsExistByTypeAndId(final String relatedBusinessType,final String relatedBusinessId);





    void batchSaveAppMsgContent(List<AppMsgEntity> list);
}
