package cn.damei.service.modules;

import cn.damei.dao.modules.DelaySheetStandBookDao;
import cn.damei.entity.modules.DelaySheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class DelaySheetStandBookService {
    @Autowired
    private DelaySheetStandBookDao delaySheetStandBookDao;


    public List<DelaySheet> findDelaySheet(String orderno){
        try{
            List<DelaySheet> list = delaySheetStandBookDao.findDelaySheet(orderno);
            return list;
        }catch (Exception e){
            return new ArrayList<>();
        }

    }
}
