package com.huarui;

import com.huarui.entity.DMember;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SolrSearchTest {

    @Autowired
    private SolrClient solrClient;

    @Test
    public void testQuery() throws IOException, SolrServerException {
        Map<String, Object> result = new HashMap<>();
        String keywords = "笑傲";
        int page = 1;
        int rows = 10;

        SolrQuery solrQuery = new SolrQuery(); // 构造搜索条件
        solrQuery.setQuery("name:" + keywords); // 搜索关键词
        // ...

        // 设置分页
        solrQuery.setStart((Math.max(page, 1) - 1) * rows);
        solrQuery.setRows(rows);

        QueryResponse queryResponse = solrClient.query(solrQuery);
        //获得正常的查询结果
/*
        方法1
        SolrDocumentList documentList = queryResponse.getResults();
        List<DMember> list = new ArrayList<>();
        for (SolrDocument entries : documentList) {
            DMember dMember = new DMember();
            dMember.setId(entries.get("id").toString());
            dMember.setAge(Integer.parseInt(entries.get("age").toString()));
            dMember.setHeight(Double.parseDouble(entries.get("height").toString()));
            dMember.setName(entries.get("name").toString());
            list.add(dMember);
        }*/

        /*方法2*/
        List<DMember> list = queryResponse.getBeans(DMember.class);

        for (DMember dMember : list) {
            System.out.println(dMember.toString());
        }

    }


} 