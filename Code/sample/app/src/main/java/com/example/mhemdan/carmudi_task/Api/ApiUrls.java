package com.example.mhemdan.carmudi_task.Api;

/**
 * Created by mohammed on 6/30/15.
 */
public interface ApiUrls {
//    public final String BASE_URL = "http://zojol.com:80/services";
    String BASE_URL ="https://www.carmudi.ae/api/cars";
    String GET_PRODUCTS = "/page:{page_number}/maxitems:30";
    String SORT_PRODUCTS = "/sort:{type}";
//    https://www.carmudi.ae/api/cars/page:1/maxitems:10
//    https://www.carmudi.ae/api/cars/sort:oldest
}
