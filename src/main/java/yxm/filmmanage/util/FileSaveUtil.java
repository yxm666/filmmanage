package yxm.filmmanage.util;

import yxm.filmmanage.entity.Film;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件保存
 */
public class FileSaveUtil {
    public FileSaveUtil(Film film, MultipartFile[] files) throws IllegalStateException, IOException {
        String name = null;
        // 设置保存地址
        String dir = "E:\\static\\";
        File file = new File(dir);
        if (!file.exists()){
            file.mkdir();
        }
        if (!files[0].isEmpty()){
            name = UUID.randomUUID().toString().replaceAll("-", "") + "." + FilenameUtils.getExtension(files[0].getOriginalFilename());
            film.setCoverImg(name);
            files[0].transferTo(new File(dir + name));
        }
        if (!files[1].isEmpty()){
            name = UUID.randomUUID().toString().replaceAll("-", "") + "." + FilenameUtils.getExtension(files[1].getOriginalFilename());
            film.setMovie(name);
            files[1].transferTo(new File(dir + name));
        }
    }
}
