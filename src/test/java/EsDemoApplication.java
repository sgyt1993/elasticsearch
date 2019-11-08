import com.springdemo.elasticsearchdemo.SpringElasticSearchApplication;
import com.springdemo.elasticsearchdemo.dao.ItemRepository;
import com.springdemo.elasticsearchdemo.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/11/7 8:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringElasticSearchApplication.class)
public class EsDemoApplication {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ItemRepository itemRepository;

    /**
     * 索引新增
     */
    @Test
    public void testCreateIndex() {
        elasticsearchTemplate.createIndex(Item.class);
    }


    /**
     * 索引删除
     */
    @Test
    public void testDeleteIndex() {
        elasticsearchTemplate.deleteIndex(Item.class);
    }


    /**
     * 文档新增
     */
    @Test
    public void insertDocument() {
        Item item = new Item(1L, "小米7", "手机", "红米", 1400.34, "www.baidu.com", false, new Date());
        itemRepository.save(item);
    }


    /**
     * 文档批量新增
     */
    @Test
    public void insertListDocument() {
        Item item1 = new Item(1L, "苹果1", "手机", "a", 1400.34, "www.baidu.com/123/1212", false, new Date());
        Item item2 = new Item(2L, "苹果2", "手机", "ab", 1478.34, "www.baidu.com", true, new Date());
        Item item3 = new Item(3L, "苹果12", "手机", "abc", 1400.34, "www.baidu.com/123/1212", false, new Date());
        Item item4 = new Item(4L, "苹果11", "手机", "abcd", 1478.34, "www.baidu.com", true, new Date());
        Item item5 = new Item(5L, "苹果133", "手机", "bc", 1400.34, "www.baidu.com/123/1212", false, new Date());
        Item item6 = new Item(6L, "苹", "手机", "bd", 1478.34, "www.baidu.com", true, new Date());
        Item item7 = new Item(7L, "果11", "手机", "ad", 1400.34, "www.baidu.com/123/1212", false, new Date());
        Item item8 = new Item(8L, "苹11", "手机", "ac", 1478.34, "www.baidu.com", true, new Date());
        Item item9 = new Item(9L, "果", "手机", "bc", 1400.34, "www.baidu.com/123/1212", false, new Date());
        Item item10 = new Item(10L, "苹12", "手机", "cd", 1478.34, "www.baidu.com", true, new Date());
        Item item11 = new Item(11L, "苹果11123", "手机", "abcdf", 1400.34, "www.baidu.com/123/1212", false, new Date());
        Item item12 = new Item(12L, "苹果1244444", "手机", "ff", 1478.34, "www.baidu.com", true, new Date());
        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);
        items.add(item7);
        items.add(item8);
        items.add(item9);
        items.add(item10);
        items.add(item11);
        items.add(item12);
        if (items.size() != 0) {
            itemRepository.saveAll(items);
        }
    }

    /**
     * 文档修改
     */
    @Test
    public void updateDocument() {
        Item item = new Item(1L, "苹果11", "手机", "红米", 1400.34, "www.baidu.com/123/1212", false, new Date());
        itemRepository.save(item);
    }

    /**
     * 删除文档
     */
    @Test
    public void deleteDocument() {
        itemRepository.deleteById(1l);
    }


    /**
     * 删除所有文档
     */
    @Test
    public void deleteAllDocument() {
        itemRepository.deleteAll();
    }

    /**
     * 文档所有查询
     */
    @Test
    public void query() {
        Iterable<Item> items = this.itemRepository.findAll();
        for (Item item : items) {
            System.out.println(item);
        }
    }

    /**
     * 文档查询
     */
    @Test
    public void queryById() {
        Item item = itemRepository.findById(1L).get();
        System.out.println(item);
    }

    /**
     * 查询所有并排序
     */
    @Test
    public void sortQueryAll() {
        // 查找所有
        //Iterable<Item> list = this.itemRepository.findAll();
        // 对某字段排序查找所有 Sort.by("price").descending() 降序
        // Sort.by("price").ascending():升序
        Iterable<Item> list = this.itemRepository.findAll(Sort.by("price").ascending());
        for (Item item : list) {
            System.out.println(item);
        }
    }


}
