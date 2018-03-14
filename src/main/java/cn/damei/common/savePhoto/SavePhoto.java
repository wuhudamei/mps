package cn.damei.common.savePhoto;

import cn.damei.common.utils.DateUtils;
import cn.damei.common.Base64Util;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * Created by joseph on 2017/7/24.
 */
@Service
public class SavePhoto {



    public  static  String savePic(HttpServletRequest request, String photo,String picPath) {


        String rootPath = request.getSession().getServletContext().getRealPath("");
        File filePath = new File(rootPath +picPath + DateUtils.getDate1());


        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        // String rootPath = RootName.SystemEnvironment(request);

        // 判断该文件是否存在
        if (!filePath.exists() && !filePath.isDirectory()) {
            filePath.mkdirs();
        }
        String filepath = filePath + filePath.separator + uuid + ".jpeg";
        Base64Util.generateImage(photo, filepath);

        String picpath =picPath + DateUtils.getDate1() + filePath.separator + uuid
                + ".jpeg";

        return picpath;


    }
}
