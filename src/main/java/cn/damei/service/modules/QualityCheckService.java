package cn.damei.service.modules;

import cn.damei.dao.modules.QualityCheckDao;
import cn.damei.entity.modules.QualityCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QualityCheckService {

    @Autowired
    private QualityCheckDao qualityCheckDao;


    public List<QualityCheck> findQualityCheck(String orderno){
        try{
            return this.qualityCheckDao.findQualityCheck(orderno);
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public List<QualityCheck> findRepeatQualityCheck(String orderno){
        try{
            return this.qualityCheckDao.findRepeatQualityCheck(orderno);
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

}
