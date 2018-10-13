package com.huarui.configuration;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

    @Bean
    public SolrClient solrClient(){
        //TODO test_core 是solr中的core,solr的域->在 managed-schema 文件中配置
        SolrClient solrClient = new HttpSolrClient.Builder("http://180.76.96.218:8983/solr/test_core").build();
        return solrClient;
    }

} 