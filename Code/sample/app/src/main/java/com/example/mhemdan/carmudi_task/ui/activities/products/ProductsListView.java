package com.example.mhemdan.carmudi_task.ui.activities.products;

import com.example.mhemdan.carmudi_task.models.Product;

import java.util.ArrayList;

/**
 * Created by mhemdan on 11/20/15.
 */
public interface ProductsListView {
    void showProgress();
    void hideProgress();
    void showError(String errorMessage);
    void submitProducts(ArrayList<Product> productArrayList);
}
