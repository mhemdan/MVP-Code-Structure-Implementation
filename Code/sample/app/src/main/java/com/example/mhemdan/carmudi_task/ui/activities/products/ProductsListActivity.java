package com.example.mhemdan.carmudi_task.ui.activities.products;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.cocosw.bottomsheet.BottomSheet;
import com.example.mhemdan.carmudi_task.R;
import com.example.mhemdan.carmudi_task.adapter.ProductListAdapter;
import com.example.mhemdan.carmudi_task.models.Product;
import com.example.mhemdan.carmudi_task.ui.base.BaseActivity;
import com.example.mhemdan.carmudi_task.util.Constants;
import com.example.mhemdan.carmudi_task.util.Util;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;

/**
 * Created by mhemdan on 11/20/15.
 */
public class ProductsListActivity extends BaseActivity implements ProductsListView, SwipeRefreshLayout.OnRefreshListener, OnMoreListener {
    private ProductsListPresenter productsListPresenter;
    private SuperRecyclerView productRecyclerView;
    private int pageNumber = 1;
    private ProductListAdapter productListAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureToolbar();
        setupRecycleView();
        productsListPresenter = new ProductsListPresenterImpl(this);
        if(Util.getInstance(this).isOnline()) {
            productsListPresenter.getProducts(pageNumber);
        }else{
            submitProducts(getCache());
        }
    }

    @Override
    public void submitProducts(ArrayList<Product> productArrayList) {
        productRecyclerView.hideMoreProgress();
        productListAdapter.insertItems(productArrayList);
    }
    private void configureToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.product_list));
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_sort:
                        setBottomSheet();
                        break;
                }
                return false;
            }
        });
    }
    private void setupRecycleView(){
        productRecyclerView = (SuperRecyclerView) findViewById(R.id.product_list);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        productRecyclerView.setLoadingMore(true);
        productRecyclerView.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light);
        productRecyclerView.setupMoreListener(this, pageNumber);
        productRecyclerView.setRefreshListener(this);
        productRecyclerView.setLayoutManager(llm);
        productListAdapter = new ProductListAdapter(this);
        productRecyclerView.setAdapter(productListAdapter);

    }

    @Override
    public void onRefresh() {
        if(Util.getInstance(this).isOnline()) {
            productRecyclerView.hideMoreProgress();
            productsListPresenter.disableShowProgress();
            productListAdapter.clear();
            pageNumber = 1;
            productsListPresenter.getProducts(pageNumber);
        }
    }

    @Override
    public void onMoreAsked(int i, int i1, int i2) {
        if(Util.getInstance(this).isOnline()) {
            productsListPresenter.disableShowProgress();
            pageNumber++;
            productsListPresenter.getProducts(pageNumber);
        }
    }

    @Override
    public void showProgress() {
//        super.showProgress();
    }

    @Override
    protected void onPause() {
        cache(productListAdapter.getCurrentDisplayedProducts());
        super.onPause();
    }

    private void setBottomSheet(){
        new BottomSheet.Builder(this).title(getResources().getString(R.string.sort)).sheet(R.menu.bottom_sheet_menu).listener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case R.id.oldest:
                        productListAdapter.clearWithNotify();
                        productsListPresenter.sortProducts(Constants.OLD);
                        break;
                    case R.id.newest:
                        productListAdapter.clearWithNotify();
                        productsListPresenter.sortProducts(Constants.NEW);
                        break;
                    case R.id.high_price:
                        productListAdapter.clearWithNotify();
                        productsListPresenter.sortProducts(Constants.PRICE_HIGH);
                        break;
                    case R.id.low_price:
                        productListAdapter.clearWithNotify();
                        productsListPresenter.sortProducts(Constants.PRICE_LOW);
                        break;
                    case R.id.high_mileage:
                        productListAdapter.clearWithNotify();
                        productsListPresenter.sortProducts(Constants.MILEAGE_HIGH);
                        break;
                    case R.id.low_mileage:
                        productListAdapter.clearWithNotify();
                        productsListPresenter.sortProducts(Constants.MILEAGE_LOW);
                        break;
                }
            }
        }).show();
    }
}
