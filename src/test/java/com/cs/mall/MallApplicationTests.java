package com.cs.mall;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import com.cs.mall.common.utils.ElasticSearchUtils;
import com.cs.mall.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class MallApplicationTests {


    @Autowired
    private ElasticsearchClient client;

    /**
     * 创建索引
     * 创建索引时，必须是小写，否则创建报错
     * @throws IOException
     */
    @Test
    void createIndex() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder().index("ouldindex").build();
        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest);
        System.out.println("是否成功："+createIndexResponse.acknowledged());
    }

    //添加一条记录
//    @Test
//    void insertIndex() throws IOException {
//        Product product = new Product("efg", "Bag", 42);
//        client.index(builder -> builder
//        .index("products")
//        .id(product.getId())
//        .document(product));
//    }

    //查询方法1
    //相当于先构建查询语句，然后用另一个去执行
    @Test
    void querytest() throws IOException {

        TermQuery query = QueryBuilders.term()
                .field("name")
                .value("bag")
                .build();

        SearchRequest request = new SearchRequest.Builder()
                .index("products")
                .query(query._toQuery())
                .build();
        SearchResponse<Product> search = client.search(request, Product.class);

        for (Hit<Product> hit : search.hits().hits()) {
            Product pd = hit.source();
            System.out.println(pd);
        }

        SearchResponse<Product> searchResponse = client.search(s -> s
        .index("products")
        .query(q -> q
                .term(t -> t
                    .field("name")
                    .value(v -> v.stringValue("bag"))
                )), Product.class);

        for (Hit<Product> hit: searchResponse.hits().hits()) {
            Product pd = hit.source();
            System.out.println(pd);
        }



    }





}
