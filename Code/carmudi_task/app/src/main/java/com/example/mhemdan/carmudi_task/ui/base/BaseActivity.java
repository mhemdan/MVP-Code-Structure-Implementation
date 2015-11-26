package com.example.mhemdan.carmudi_task.ui.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mhemdan.carmudi_task.R;
import com.example.mhemdan.carmudi_task.models.Product;
import com.example.mhemdan.carmudi_task.util.Constants;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;
import java.util.Objects;

import io.paperdb.Paper;

/**
 * Created by mhemdan on 11/20/15.
 */
public class BaseActivity extends FragmentActivity implements BaseInterface {
    private Dialog pd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createCustomProgressDialog();
        Paper.init(this);
    }

    @Override
    public void showProgress() {
        if(pd.isShowing()){
            pd.dismiss();
        }
        pd.show();
    }

    @Override
    public void hideProgress() {
        if(this!=null&&pd != null&&pd.isShowing())
            pd.dismiss();
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();
    }
    /**
     * method to create loader while requesting data from api
     * @return void
     */
    private void createCustomProgressDialog(){
        pd = new Dialog(this);
        pd.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {  if (keyCode == KeyEvent.KEYCODE_BACK) {
                finish();
                dialog.dismiss();
            }
                return true;
            }
        });
        pd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ProgressWheel wheel = new ProgressWheel(this);
        wheel.setBarColor(Color.BLUE);

        LinearLayout container = new LinearLayout(this);
        container.addView(wheel);
        pd.setContentView(R.layout.progress_dialog);
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        pd.setCancelable(false);
    }
    public void cache(Object object){
        Paper.book().write(Constants.CACHED_LIST, object);
    }
    public ArrayList<Product> getCache(){
        return Paper.book().read(Constants.CACHED_LIST,new ArrayList<Product>());
    }
}
