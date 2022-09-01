package com.cs.mall.common.utils;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import com.cs.mall.config.ElasticSearchConfig;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author Caosen
 * @Date 2022/8/15 14:14
 * @Version 1.0
 */

@Component
@Slf4j
public class ElasticSearchUtils {
    @Autowired
    private ElasticsearchClient client;


    public void createindices() throws IOException {
        CreateIndexRequest ouldindex = new CreateIndexRequest.Builder().index("ouldindex").build();
        CreateIndexResponse createIndexResponse = client.indices().create(ouldindex);
        log.info("是否创建成功：{}", createIndexResponse.acknowledged());
    }

}
