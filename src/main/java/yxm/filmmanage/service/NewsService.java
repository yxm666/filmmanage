package yxm.filmmanage.service;

import yxm.filmmanage.entity.News;

import java.util.List;

public interface NewsService {
    /**
     * 添加新闻
     * @param news
     * @return
     */
    int add(News news);

    /**
     * 更新新闻信息
     * @param news
     * @return
     */
    News update(News news);

    /**
     * 根据id删除新闻
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 根据id查找新闻
     * @param id
     * @return
     */
    News findById(Integer id);

    /**
     * 查看所有新闻
     * @return
     */
    List<News> findall();

    /**
     * 多条件查询新闻
     * @param news
     * @return
     */
    List<News> findExample(News news);
}
