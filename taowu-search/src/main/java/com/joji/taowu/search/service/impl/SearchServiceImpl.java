package com.joji.taowu.search.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joji.taowu.common.entity.Product;
import com.joji.taowu.common.param.ProductSearchParam;
import com.joji.taowu.common.utils.R;
import com.joji.taowu.search.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 搜索服务业务实现层
 * */
@Slf4j
@Service
public class SearchServiceImpl implements SearchService {



    @Resource
    private RestHighLevelClient client;


    @Override
    public R search(ProductSearchParam productSearchParam) throws JsonProcessingException {
        SearchRequest searchRequest = new SearchRequest("product");

        if (StringUtils.isEmpty(productSearchParam.getSearch())){
            //如果为null,查询全部
            searchRequest.source().query(QueryBuilders.matchAllQuery());
        }else{
            //不为空 all字段进行搜索
            searchRequest.source().query(QueryBuilders.matchQuery("all",productSearchParam.getSearch()));
        }

        //设置分页参数
        searchRequest.source().from(productSearchParam.getFrom());
        searchRequest.source().size(productSearchParam.getSize());

        SearchResponse response = null;
        try {
            response = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }

        //结果集解析
        //获取集中的结果
        SearchHits hits = response.getHits();
        //获取符合的数量
        long total = hits.getTotalHits().value;

        SearchHit[] items = hits.getHits();

        List<Product> productList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for (SearchHit item : items) {
            //获取单挑json数据
            String json = item.getSourceAsString();
            Product product = objectMapper.readValue(json, Product.class);
            productList.add(product);
        }

        return R.ok(null,productList,total);
    }
}
