package com.example.admin.testsample;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.testsample.Database.IchDao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity  {
    private RecyclerView rvMenus;
    private List<Category> menuList = new ArrayList<>();
    IchDao dao;

    CartListAdapter adapter;

    TextView tv_total, tv_count,tv_location,tv_forgot,tv_signup_ifnot;
    ArrayList<Category> product, sumtotal, totalCount;

    ArrayList<String> menuIdList;
    ArrayList<Double> menuTotal, menuTotalOption;
    ArrayList<Category> sumtotalOption;
    LinearLayout liCheckout;

    int totalCartCount;
    double total_price;


    Dialog mBottomSheetDialog;
    private EditText inputEmail, inputPassword;
    private Button btnSignUp,viewchechoutbtn;

    private View view;
    private String totalAMount;
    private Paint p = new Paint();
    RelativeLayout root;

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    String regId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("ICH Cart");

 /*       session = new SessionManager(getApplicationContext());
        rvMenus = (RecyclerView) findViewById(R.id.rv_menus);
        tv_count = (TextView) findViewById(R.id.tv_count);
        tv_total = (TextView) findViewById(R.id.totalAmount);
        tv_location = (TextView)findViewById(R.id.tv_location);
        tv_location.setOnClickListener(this);
        liCheckout = (LinearLayout) findViewById(R.id.licheckout);
        viewchechoutbtn=(Button)findViewById(R.id.viewchechoutbtn);
        root=(RelativeLayout)findViewById(R.id.activity_cart);*/



        dao = new IchDao(CartActivity.this);

        menuIdList = new ArrayList<>();
        menuTotal = new ArrayList<>();
        menuTotalOption = new ArrayList<>();


       /* totalCount = dao.getCart();
        for (Menu i : totalCount) {
            menuIdList.add(i.getMenuId());

        }
        String count = String.valueOf(menuIdList.size());
        DecimalFormat df = new DecimalFormat("####0.00");
        sumtotal = dao.getTotalPrice();
        for (Menu i : sumtotal) {

            menuTotal.add(Double.valueOf(i.getTotalcount()));

        }
        int i;
        double sum = 0;
        for (i = 0; i < menuTotal.size(); i++)
            sum += menuTotal.get(i);
        tv_total.setText(String.valueOf(df.format(sum)));
        //  tv_total.setText(String.valueOf(dao.getTotal()));
        tv_count.setText(count);*/


        showCart();




       /* rvMenus.addOnItemTouchListener(new RecyclerItemClickListener(CartActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Dialog dialog=new Dialog(CartActivity.this);
                String menupic=product.get(position).getImagePath();

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.item_menu_list);

                ImageView addImage=(ImageView)dialog.findViewById (R.id.addImage);


                ImageView menuimage=(ImageView)dialog.findViewById (R.id.menuimage);

                LinearLayout lititle=(LinearLayout)dialog.findViewById(R.id.lititle);

                TextView menudescription=(TextView)dialog.findViewById(R.id.menudescription);

                LinearLayout licountandprice=(LinearLayout)dialog.findViewById(R.id.licountandprice);

                Picasso.with(CartActivity.this).load(menupic).fit().centerCrop().into(menuimage);
                addImage.setVisibility(View.GONE);

                 lititle.setVisibility(View.GONE);

                menudescription.setVisibility(View.GONE);

                licountandprice.setVisibility(View.GONE);

                dialog.show();




            }
        }));*/

    }

    private void showCart() {
      //  product = dao.getCartMenus();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvMenus.setLayoutManager(layoutManager);
        /*rvMenus.setAdapter(new MenuListAdapter(menuList, R.layout.item_menu_list, getContext()));
        MenuListAdapter menu = new MenuListAdapter(menuList, R.layout.item_categories_list, getContext());*/

        adapter = new CartListAdapter("cartActivity",dao,product, R.layout.activity_nottification_list_actvity, getApplicationContext(), new CartListAdapter.ListAdapterListener() {
            @Override
            public void onClickAtOKButton(int position) {

           /*     String itemCount = product.get(position).getCount();
                String menuid = product.get(position).getMenuId();
                String menu_price = product.get(position).getMenuPrice();
                String menu_option = product.get(position).getMenuOptionSelection();
                String menuname = product.get(position).getMenuName();
                String menupic = product.get(position).getImagePath();
                String menutype = product.get(position).getMenuType();*/
                DecimalFormat df = new DecimalFormat("####0.00");



            }
        });
        rvMenus.setAdapter(adapter);
    }






   /* private void updateCart() {
        tv_total.setText("");
        tv_count.setText("");
        menuIdList.clear();
        menuTotal.clear();
        menuTotalOption.clear();

        totalCount = dao.getCart();
        for (Menu i : totalCount) {
            menuIdList.add(i.getMenuId());

        }
        String count = String.valueOf(menuIdList.size());
        DecimalFormat df = new DecimalFormat("####0.00");
        sumtotal = dao.getTotalPrice();
        for (Menu i : sumtotal) {

            menuTotal.add(Double.valueOf(i.getTotalcount()));

        }
        int i;
        double sum = 0;
        for (i = 0; i < menuTotal.size(); i++)
            sum += menuTotal.get(i);


        sumtotalOption = dao.getTotalPriceOption();
        for (OptionJson j : sumtotalOption) {

            menuTotalOption.add(Double.valueOf(j.getOption_total()));

        }
        int j;
        double sumOption = 0;
        for (j = 0; j < menuTotalOption.size(); j++)
            sumOption += menuTotalOption.get(j);

        totalAMount=String.valueOf(df.format(sum + sumOption));
        tv_total.setText(String.valueOf(df.format(sum + sumOption)));
        //  tv_total.setText(String.valueOf(dao.getTotal()));
        tv_count.setText(count);
    }*/


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
