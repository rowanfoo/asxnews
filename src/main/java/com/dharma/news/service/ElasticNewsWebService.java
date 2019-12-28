package com.dharma.news.service;

import org.springframework.stereotype.Service;


@Service
/*
Used for search Elastic News
 */
public class ElasticNewsWebService {

//    @Autowired
//    ElasticsearchTemplate elasticsearchTemplate;
//
//
//    public List<News>  search (String search , String date ) {
////        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
//        NativeSearchQueryBuilder  searchQuery = new NativeSearchQueryBuilder();
//        NativeSearchQuery query = searchQuery.build().setPageable(PageRequest.of(0,1000));
//
//        QueryBuilder boolQueryBuilder = boolQuery()
//                .must(matchQuery("notes", search) )
//                .must(matchQuery("date", date ));
//        System.out.println("----------------------QUERY---------------1");
//        //searchQueryBuilder.withQuery(boolQueryBuilder);
//        System.out.println("----------------------QUERY---------------2");
//
//
////        SearchQuery searchQuery  = searchQueryBuilder.build();
//
////        System.out.println("----------------------QUERY---------------"+searchQuery.getQuery());
//        System.out.println("----------------------QUERY---------------"+query.getQuery());
//        List<News> articles = elasticsearchTemplate.queryForList( query, News.class);
//
//
//
//        return articles;
//    }
//


}
