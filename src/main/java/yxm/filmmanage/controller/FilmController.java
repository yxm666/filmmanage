package yxm.filmmanage.controller;

import yxm.filmmanage.entity.Film;
import yxm.filmmanage.util.FileSaveUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import yxm.filmmanage.service.FilmService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/film")
public class FilmController {
    @Autowired
    private FilmService filmService;

    /**
     * 查看影片界面
     * @param model
     * @return
     */
    @RequestMapping("/film.do")
    public String findAll(Model model, Film film){
        //调用的是多条件的查询的方法，如果不传查询条件，则查询出所有
        List<Film> list = filmService.findExample(film);
        model.addAttribute("list",list);
        return "film";
    }

    /**
     * 查询影片
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    @ResponseBody
    public Film findById(Integer id){
        return filmService.findById(id);
    }

    /**
     * 更新影片信息
     * @param film
     * @return
     */
    @RequestMapping("/update.do")
    @ResponseBody
    public Film update(Film film) {
        return filmService.update(film);
    }

    /**
     * 删除影片
     * @param id
     * @return
     */
    @RequestMapping("/delete.do")
    @ResponseBody
    public int delete(Integer id){
        return filmService.delete(id);
    }

    /**
     * 添加影片
     * @param film
     * @return
     */
    @RequestMapping("/add.do")
    @ResponseBody
    public String add(Film film, @RequestParam("file") MultipartFile[] file) {
        try {
            FileSaveUtil fileSaveUtil = new FileSaveUtil(film,file);
        }catch (Exception e){
            e.printStackTrace();
        }
        filmService.add(film);
        return "redirect:/film/film.do";
    }

    /**
     * 多条件查询影片
     * @param film
     * @return
     */
    @RequestMapping("/findExample.do")
    @ResponseBody
    public List<Film> findExample(Film film){
        return filmService.findExample(film);
    }

    /**
     * 观看影片
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("watchMovie.do")
    public String watchMovie(Model model,Integer id){
        Film film = filmService.findById(id);
        model.addAttribute("film",film);
        return "watchMovie";
    }
}
