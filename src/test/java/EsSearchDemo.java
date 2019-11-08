import com.springdemo.elasticsearchdemo.SpringElasticSearchApplication;
import com.springdemo.elasticsearchdemo.dao.ItemRepository;
import com.springdemo.elasticsearchdemo.entity.Item;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/11/8 14:16
 */
//        Spring Data 的另一个强大功能，是根据方法名称自动实现功能。
//
//        比如：你的方法名叫做：findByTitle，那么它就知道你是根据title查询，然后自动帮你完成，无需写实现类。
//
//        当然，方法名称要符合一定的约定：
//        Keyword	Sample
//        And	findByNameAndPrice
//        Or	findByNameOrPrice
//        Is	findByName
//        Not	findByNameNot
//        Between	findByPriceBetween
//        LessThanEqual	findByPriceLessThan
//        GreaterThanEqual	findByPriceGreaterThan
//        Before	findByPriceBefore
//        After	findByPriceAfter
//        Like	findByNameLike
//        StartingWith	findByNameStartingWith
//        EndingWith	findByNameEndingWith
//        Contains/Containing	findByNameContaining
//        In	findByNameIn(Collection<String>names)
//        NotIn	findByNameNotIn(Collection<String>names)
//        Near	findByStoreNear
//        True	findByAvailableTrue
//        False	findByAvailableFalse
//        OrderBy	findByAvailableTrueOrderByNameDesc

//分词规则很重要，要看重分词规则
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringElasticSearchApplication.class)
public class EsSearchDemo {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ItemRepository itemRepository;



    @Test
    public void queryByPriceBetween()
    {
        List<Item> list = this.itemRepository.findByPriceBetween(1454.34, 1478.34);
        for (Item item : list) {
            System.out.println("item = " + item);
        }
    }


    @Test
    public void queryByTitle()
    {
        List<Item> list = this.itemRepository.findByTitle("小米prd");
        for (Item item : list) {
            System.out.println("item = " + item);
        }
    }



    /**
     * matchQuery:会将搜索词分词，再与目标查询字段进行匹配，若分词中的任意一个词与目标字段匹配上，则可查询到。
     */
    @Test
    public void testMatchQuery(){
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("brand", "abcdf"));
        // 搜索，获取结果
        Page<Item> items = this.itemRepository.search(queryBuilder.build());
        // 总条数
        long total = items.getTotalElements();
        System.out.println("total = " + total);
        for (Item item : items) {
            System.out.println(item);
        }
    }

    /**
     * @termQuery:不会对搜索词进行分词处理，而是作为一个整体与目标字段进行匹配，若完全匹配，则可查询到。
     */
    @Test
    public void testTermQuery(){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //精确查询
        builder.withQuery(QueryBuilders.termQuery("brand","abcdf"));
        // 查找
        Page<Item> page = this.itemRepository.search(builder.build());

        for(Item item:page){
            System.out.println(item);
        }
    }


    /**
     * @Description:布尔查询
     */
    @Test
    public void testBooleanQuery(){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();

        builder.withQuery(
                QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("isdel",true))
                        .must(QueryBuilders.matchQuery("brand","1"))
        );

        // 查找
        Page<Item> page = this.itemRepository.search(builder.build());
        for(Item item:page){
            System.out.println(item);
        }
    }


    /**
     * @Description:分词模糊查询
     */
    @Test
    public void testFuzzyQuery(){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.fuzzyQuery("brand","abcdf"));
        Page<Item> page = this.itemRepository.search(builder.build());
        for(Item item:page){
            System.out.println(item);
        }

    }
}
