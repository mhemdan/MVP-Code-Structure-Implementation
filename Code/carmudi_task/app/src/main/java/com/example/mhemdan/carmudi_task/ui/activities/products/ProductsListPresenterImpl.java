package com.example.mhemdan.carmudi_task.ui.activities.products;

import com.example.mhemdan.carmudi_task.Api.ApiCallBack;
import com.example.mhemdan.carmudi_task.Api.ApiClient;
import com.example.mhemdan.carmudi_task.models.Product;
import com.example.mhemdan.carmudi_task.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mhemdan on 11/20/15.
 */
public class ProductsListPresenterImpl implements ProductsListPresenter ,ApiCallBack {
    private ProductsListView productsListView;
    private boolean isViewAttached = true;
    private ApiClient  apiClient;
    private final int PAGE_LIMIT = 10;
    private boolean showProgress = true;
    public ProductsListPresenterImpl(ProductsListView productsListView){
        this.productsListView = productsListView;
    }
    @Override
    public void getProducts(int pageNumber) {
        if(showProgress) {
            this.productsListView.showProgress();
        }
        apiClient = ApiClient.getInstance();
        apiClient.getProducts(pageNumber,this);
    }

    @Override
    public void sortProducts(String sortType) {
        this.productsListView.showProgress();
        apiClient.getProductsWithSort(sortType,this);
    }

    @Override
    public void disableShowProgress() {
        showProgress = false;
    }

    @Override
    public void viewIsNotAttached() {
        isViewAttached = false;
    }

    @Override
    public void onSuccess(Object object) {
        this.productsListView.hideProgress();
        JSONObject response = (JSONObject) object;
        try {
           this.productsListView.submitProducts(
                   getProducts(response.
                           getJSONObject(Constants.META_DATA).
                           getJSONArray(Constants.RESULTS)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Object object) {
        this.productsListView.hideProgress();
        this.productsListView.showError("Something went Wrong Please Check your connectivity and try again!");
    }
    private ArrayList<Product> getProducts(JSONArray jsonArray) throws JSONException {
        ArrayList<Product> productArrayList = new ArrayList<>();
        Product temp;
        for(int i =0;i<jsonArray.length();i++){
            JSONObject dataJsonObject = jsonArray.getJSONObject(0).getJSONObject(Constants.DATA);
            temp = new Product();
            temp.setName(dataJsonObject.getString(Constants.NAME));
            temp.setBrand(dataJsonObject.getString(Constants.BRAND));
            temp.setPrice(dataJsonObject.getString(Constants.PRICE));
            temp.setImageUrl(jsonArray.getJSONObject(0).getJSONArray(Constants.IMAGES).
                    getJSONObject(0).getString(Constants.URL));
            productArrayList.add(temp);
        }
        return productArrayList;
    }
}
