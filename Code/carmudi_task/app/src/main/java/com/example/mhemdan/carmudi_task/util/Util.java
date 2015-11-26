package com.example.mhemdan.carmudi_task.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by mhemdan on 11/20/15.
 */
public class Util {
    private Context context;
    private static Util util;
    public static Util getInstance(Context context){
        if(util==null){
            util = new Util(context);
            return util;
        }else{
            return util;
        }
    }
    private Util(Context context){
       this.context = context;
    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
