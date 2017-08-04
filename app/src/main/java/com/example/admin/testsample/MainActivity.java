package com.example.admin.testsample;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.admin.testsample.recyclerview.RecyclerItemClickListener;
import com.example.admin.testsample.recyclerview.SimpleDividerItemDecoration;
import com.example.admin.testsample.retrofit.ApiClient;
import com.example.admin.testsample.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvCategories;
    private List<Category> categoryList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvCategories = (RecyclerView) findViewById(R.id.rv_catogries);
        getCategories();
        rvCategories.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {



            }
        }));
    }
    public void getCategories
            () {

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);


        progressDialog.setMessage("loading");
        progressDialog.show();
        progressDialog.setCancelable(false);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<CategoriesModel> call = apiService.CategoriesList();
        call.enqueue(new Callback<CategoriesModel>() {
            @Override
            public void onResponse(Call<CategoriesModel> call, retrofit2.Response<CategoriesModel> response) {
                progressDialog.hide();
                categoryList = response.body().getCategory();
                rvCategories.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rvCategories.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
                rvCategories.setAdapter(new CategoryListAdapter(categoryList, R.layout.item_categories_list, getApplicationContext()));


            }

            @Override
            public void onFailure(Call<CategoriesModel> call, Throwable t) {
                // Log error here since request failed
                //progressDialog.dismiss();

            }
        });
    }
}
