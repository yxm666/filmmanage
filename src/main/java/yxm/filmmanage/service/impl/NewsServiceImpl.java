package yxm.filmmanage.service.impl;

import yxm.filmmanage.entity.News;
import yxm.filmmanage.jpa.NewsJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import yxm.filmmanage.service.NewsService;

import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsJpa newsJpa;


    /**
     * 添加新闻
     * @param news
     * @return
     */
    @Override
    public int add(News news) {
        newsJpa.save(news);
        return 1;
    }

    /**
     * 更新新闻信息
     * @param news
     * @return
     */
    @Override
    public News update(News news) {
        return newsJpa.save(news);
    }

    /**
     * 根据id删除新闻
     * @param id
     * @return
     */
    @Override
    public int delete(Integer id) {
        newsJpa.deleteById(id);
        return 1;
    }

    /**
     * 根据id查找新闻
     * @param id
     * @return
     */
    @Override
    public News findById(Integer id) {
        //使用getOne()返回的是代理对象，无法直接操作，会出现hibernate lazyxxx  no session 的错误
        //在测试方法上加入@Transactional注解可以解决报错的问题
        // News u = newsJpa.getOne(id);
        //  发现可以使用findById()，先调用findById()返回封装后的对象，然后使用get()方法，返回实体对象。
        Optional<News> newsOptional = newsJpa.findById(id);
        // 在Optional类中有很多内置的方法，其中isPresen()方法返回Optional对象是否为null的结果，
        // 如果当前Optional对象有值，则返回true，否则返回false，
        // 当结果有值时，然后调用它的get()方法，会返回一个<T>类型的对象，即我们要查询的实例结果。
        if(newsOptional.isPresent()){
            return newsOptional.get();
        }
        return null;
    }

    /**
     * 查看所有新闻
     * @return
     */
    @Override
    public List<News> findall() {
        return newsJpa.findAll();
    }

    /**
     * 多条件查询新闻
     * @param news
     * @return
     */
    @Override
    public List<News> findExample(News news) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("author",ExampleMatcher.GenericPropertyMatchers.contains())      //作者设置模糊查询
                .withMatcher("title",ExampleMatcher.GenericPropertyMatchers.contains())       //标题设置模糊查询
                .withMatcher("creatDate",ExampleMatcher.GenericPropertyMatchers.contains());  //时间设置模糊查询
        Example<News> newsExample = Example.of(news,matcher);
        return newsJpa.findAll(newsExample);
    }
}
