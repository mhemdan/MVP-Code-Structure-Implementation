package com.example.mhemdan.carmudi_task.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mhemdan.carmudi_task.models.Product;
import com.squareup.picasso.Picasso;
import com.example.mhemdan.carmudi_task.R;

import java.util.ArrayList;

/**
 * Created by mhemdan on 11/20/15.
 */
public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>  {
    private Context context;
    private ArrayList<Product> productArrayList;
    public ProductListAdapter(Context context){
        this.context = context;
        this.productArrayList = new ArrayList<>();
    }
    public void insertItems(ArrayList<Product> newProductList){
        this.productArrayList.addAll(newProductList);
        this.notifyDataSetChanged();
        this.notifyItemRangeChanged(0, newProductList.size());
    }
    public void clear(){
        this.productArrayList.clear();
    }
    public void clearWithNotify(){
        int size = productArrayList.size();
        this.productArrayList.clear();
        this.notifyItemRangeRemoved(0, size);
    }
    public ArrayList<Product> getCurrentDisplayedProducts(){
        return this.productArrayList;
    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());
        ViewGroup agentView = ( ViewGroup ) mInflater.inflate (R.layout.product_cell, viewGroup, false );
        ProductViewHolder productViewHolder = new ProductViewHolder(agentView);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        holder.productName.setText(productArrayList.get(position).getName());
        holder.productPrice.setText(productArrayList.get(position).getPrice());
        holder.productBrand.setText(productArrayList.get(position).getBrand());
        Glide.with(context)
                .load(productArrayList.get(position).getImageUrl())
                .crossFade()
                .fitCenter()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.productImage);
//        Picasso.with(context).load(productArrayList.get(position).getImageUrl()).
//                placeholder(R.mipmap.ic_launcher).into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }




    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        private TextView productName,productPrice,productBrand;
        private ImageView productImage;
        public ProductViewHolder(View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.product_name);
            productBrand = (TextView) itemView.findViewById(R.id.product_brand);
            productPrice = (TextView) itemView.findViewById(R.id.product_price);
            productImage = (ImageView) itemView.findViewById(R.id.product_image);

        }
    }
}