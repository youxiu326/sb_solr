package com.huarui;

import com.huarui.entity.DMember;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SolrBasicTest {

	@Autowired
	private SolrClient solrClient;


	/**
	 * 添加或修改
	 */
	@Test
	public void testSaveOrUpdate() {

		//da3bf73f-28b2-4963-9bfc-57f1d38da6b5 已经存在的id
		DMember member1 = new DMember("da3bf73f-28b2-4963-9bfc-57f1d38da6b5","李你比比",68,45D);

		DocumentObjectBinder binder = new DocumentObjectBinder();
		SolrInputDocument doc = binder.toSolrInputDocument(member1);
		try {
			solrClient.add(doc);
			solrClient.commit();
		} catch (SolrServerException | IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 根据id删除
	 */
	@Test
	public void testDelete(){
		try {
			solrClient.deleteById("7f833502-93d8-4bd9-bdcd-58c6ba64d2f4");
			solrClient.commit();
		} catch (SolrServerException |IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 删除所有
	 */
	@Test
	public void testDeleteAll(){
		try {
			//请勿轻易删除 数据来之不易
			//solrClient.deleteByQuery("*.*");
			solrClient.commit();
		} catch (SolrServerException |IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 批量添加
	 */
	@Test
	public void testInserBatch() {
		DMember member1 = new DMember(UUID.randomUUID().toString(),"谁怕谁",46,45D);
		DMember member2 = new DMember(UUID.randomUUID().toString(),"事实上 ",18,95D);
		DMember member3 = new DMember(UUID.randomUUID().toString(),"李笑傲",45,56D);
		DMember member4 = new DMember(UUID.randomUUID().toString(),"我输了",108,45D);
		DMember member5 = new DMember(UUID.randomUUID().toString(),"哈哈哈了",108,45D);
		DMember member6 = new DMember(UUID.randomUUID().toString(),"东方不败了",108,45D);
		DMember member7 = new DMember(UUID.randomUUID().toString(),"华山论剑",998,045D);

		List<DMember> document = Arrays.asList(member1,member2,member3,member4,member5,member6,member7);
		try {
			solrClient.addBeans(document);
			solrClient.commit();

		} catch (SolrServerException | IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 根据solrQuery查询
	 * @param <T>
	 * @return
     */
	public <T>List<T> findBySolrQuery(String solrQuery,Class<T>clazz){
		SolrQuery query = new SolrQuery();
		query.setQuery(solrQuery);

		try {
			QueryResponse response = solrClient.query(query);
			return response.getBeans(clazz);
		} catch (SolrServerException |IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
