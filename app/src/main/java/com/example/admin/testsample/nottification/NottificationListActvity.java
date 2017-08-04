package com.example.admin.testsample.nottification;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.admin.testsample.AppController;
import com.example.admin.testsample.R;
import com.example.admin.testsample.recyclerview.EndlessRecyclerView;
import com.example.admin.testsample.retrofit.ApiClient;
import com.example.admin.testsample.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;

public class NottificationListActvity extends AppCompatActivity {
    @InjectView(R.id.activity_qutation_list_actvity)
    RelativeLayout activity_qutation_list_actvity;
    private List<NotificationList> nottificationList = new ArrayList<>();
    NottificationListAdapter nottificationListAdapter;
    private EndlessRecyclerView recyclerViewNottificationList;
    private SwipeRefreshLayout refreshLayout;

    private int selectedItemPosition = -1;
    private int limit = 30;
    private int offset = 1;
    private boolean hasReachedTop = false;
    private EndlessRecyclerView.PaginationListener paginationListener = new EndlessRecyclerView.PaginationListener() {
        @Override
        public void onReachedBottom() {
            getNottificationList();
        }

        @Override
        public void onReachedTop() {
            hasReachedTop = true;
        }
    };
    ProgressBar progressBarFooter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nottification_list_actvity);
        ButterKnife.inject(this);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        recyclerViewNottificationList = (EndlessRecyclerView) findViewById(R.id.recycler_nottification);
        progressBarFooter = (ProgressBar) findViewById(R.id.progressBarFooter);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_contest);
        refreshLayout.setRefreshing(false);
        setUpListeners();
        String user_id = AppController.getString(getApplicationContext(), "user_id");
        if (user_id != null) {

                getNottificationList();

        }


    }

    private void setUpListeners() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewNottificationList.setLayoutManager(layoutManager);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                refreshLayout.setRefreshing(true);
                offset = 1;
                getNottificationList();
            }
        });

        recyclerViewNottificationList.addPaginationListener(paginationListener);


    }


    public void getNottificationList
            () {
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(NottificationListActvity.this);
        if (offset == 1) {


            progressDialog.setMessage("loading");
            progressDialog.show();
        } else {
            progressBarFooter.setVisibility(View.VISIBLE);
        }
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        String acess_token = AppController.getString(getApplicationContext(), "acess_token");
        Log.e("acedse: ", acess_token);
        Call<NottificationResponse> call = apiService.NottificationList("header"+ acess_token, offset);
        call.enqueue(new Callback<NottificationResponse>() {
            @Override
            public void onResponse(Call<NottificationResponse> call, retrofit2.Response<NottificationResponse> response) {


                refreshLayout.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    if (!response.body().getNotificationList().isEmpty()) {
                        List<NotificationList> notificationLists = response.body().getNotificationList();
                        if (offset == 1) {
                            nottificationList = notificationLists;
                            updateEndlessRecyclerView();
                            progressDialog.hide();
                        } else {
                            progressBarFooter.setVisibility(View.VISIBLE);
                            for (NotificationList itemModel : notificationLists) {
                                nottificationList.add(itemModel);
                            }

//                                adapter.addToList(contestItemsList);
                        }
                        if (notificationLists.size() < limit) {
                            recyclerViewNottificationList.setHaveMoreItem(false);
                        } else {
                            recyclerViewNottificationList.setHaveMoreItem(true);
                        }
                        nottificationListAdapter.notifyDataSetChanged();
                        offset = offset + 1;

                        Log.i("tag", nottificationList.toString());
                    } else {
                        recyclerViewNottificationList.setHaveMoreItem(false);
                        //GlobalMethods.showToast(ContestListActivity.this, getString(R.string.sorry_no_contests), 1);
                    }

                } else {
                    // GlobalMethods.showToast(ContestListActivity.this, getString(R.string.try_again), 1);
                    finish();
                    recyclerViewNottificationList.setHaveMoreItem(false);
                }

                //  progressDialog.dismiss();
                progressBarFooter.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<NottificationResponse> call, Throwable t) {
                // Log error here since request failed
                progressDialog.dismiss();

            }
        });
    }


    private void updateEndlessRecyclerView() {
        nottificationListAdapter = new NottificationListAdapter(nottificationList, R.layout.item_nottification_list, getApplicationContext());
        recyclerViewNottificationList.setAdapter(nottificationListAdapter);
        // progressDialog.dismiss();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
