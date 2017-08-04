package com.example.admin.testsample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.testsample.Database.IchDao;

import java.util.ArrayList;

/**
 * Created by admin on 6/28/2017.
 */
public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    private ArrayList<Category> catList = new ArrayList<>();
    private int rowLayout;
    private Context context;
    int count = 0;
    String total;
    private String isfrom;
    int currentcount;

    IchDao dao;
    double total_price = 0.00;
    ArrayList<Category> sumtotalOption;
    private ArrayList<Double>  menuTotalOption;

    private CartListAdapter.ListAdapterListener mListener;

    public interface ListAdapterListener { // create an interface
        void onClickAtOKButton(int position); // create callback function
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_menu_name;
       // ImageView menu_image;
        TextView tv_description;
        TextView tv_price;
        TextView tv_plus;
        TextView tv_minus;
        TextView tv_count;
        TextView tv_menuTotal;
        ImageView add_image;
        RecyclerView rv_menuOption;
        ImageView vegImage;

        public ViewHolder(View v) {
            super(v);

          /*  tv_menu_name = (TextView) v.findViewById(R.id.menuTitle);
            tv_description = (TextView) v.findViewById(R.id.menudescription);*/





        }
    }

    public CartListAdapter(String isfrom,IchDao dao,ArrayList<Category> nottificationList, int rowLayout, Context context, CartListAdapter.ListAdapterListener mListener) {
        this.catList = nottificationList;
        this.rowLayout = rowLayout;
        this.context = context;
        this.mListener = mListener;
        this.dao=dao;
        this.isfrom=isfrom;

    }

    @Override
    public CartListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        menuTotalOption=new ArrayList<>();
        return new CartListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartListAdapter.ViewHolder holder, final int position) {
        // For setting the material Textdrawable


      //  holder.tv_menu_name.setText(catList.get(position).getMenuName());
        // holder.tv_description.setText(catList.get(position).getMenuDescription());


      /*  String menuId=catList.get(position).getMenuId();
        String typeFood=catList.get(position).getMenuType();
        if (typeFood.equals("0"))
        {
            holder.vegImage.setBackgroundResource(R.drawable.green);
        }
        if (typeFood.equals("1"))
        {
            holder.vegImage.setBackgroundResource(R.drawable.red);
        }
      */



        holder.add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                mListener.onClickAtOKButton(position);

                //  catList.get(position).setMealtimeId("0");

                notifyItemChanged(position);

            }
        });

    }


    @Override
    public int getItemCount() {
        if (catList != null) {
            return catList.size();
        }

        return 0;
    }

    public void removeItem(int position) {
        catList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, catList.size());
    }
}