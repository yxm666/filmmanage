package yxm.filmmanage.controller;

import yxm.filmmanage.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import yxm.filmmanage.service.NewsService;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    /**
     * 查看新闻界面
     * @param model
     * @return
     */
    @RequestMapping("/news.do")
    public String findAll(Model model, News news){
        //调用的是多条件的查询的方法，如果不传查询条件，则查询出所有
        List<News> list = newsService.findExample(news);
        model.addAttribute("list",list);
        return "news";
    }

    /**
     * 查询新闻
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    @ResponseBody
    public News findById(Integer id){
        return newsService.findById(id);
    }

    /**
     * 更新新闻信息
     * @param news
     * @return
     */
    @RequestMapping("/update.do")
    @ResponseBody
    public News update(News news) {
        return newsService.update(news);
    }

    /**
     * 删除新闻
     * @param id
     * @return
     */
    @RequestMapping("/delete.do")
    @ResponseBody
    public int delete(Integer id){
        return newsService.delete(id);
    }

    /**
     * 添加新闻
     * @param news
     * @return
     */
    @RequestMapping("/add.do")
    @ResponseBody
    public int add(News news) {
        return newsService.add(news);
    }

    /**
     * 普通用户浏览新闻列表
     * @param model
     * @return
     */
    @RequestMapping("/newsList.do")
    public String findAlls(Model model){
        List<News> list = newsService.findall();
        model.addAttribute("list",list);
        return "newsList";
    }

    /**
     * 用户点击新闻进行查看
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/lookNews.do")
    public String lookNews(Model model ,Integer id){
        News news = newsService.findById(id);
        model.addAttribute("news",news);
        return "lookNews";
    }

    /**
     * 多条件查询新闻
     * @param news
     * @return
     */
    @RequestMapping("/findExample.do")
    @ResponseBody
    public List<News> findExample(News news){
        return newsService.findExample(news);
    }


}
