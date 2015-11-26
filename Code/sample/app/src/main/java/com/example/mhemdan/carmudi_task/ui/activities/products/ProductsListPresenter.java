package com.example.mhemdan.carmudi_task.ui.activities.products;

/**
 * Created by mhemdan on 11/20/15.
 */
public interface ProductsListPresenter {
    void getProducts(int pageNumber);
    void sortProducts(String sortType);
    void disableShowProgress();
    void viewIsNotAttached();
}
