package com.example.admin.testsample.nottification;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.testsample.R;

import java.util.ArrayList;
import java.util.List;

/*import static com.bteem.samkara_serviceprovider.R.drawable.bell;
import static com.bteem.samkara_serviceprovider.R.drawable.circle;
import static com.bteem.samkara_serviceprovider.R.drawable.ic_bell;*/

/**
 * Created by admin on 2/11/2017.
 */

public class NottificationListAdapter extends RecyclerView.Adapter<NottificationListAdapter.ViewHolder> {

    private List<NotificationList> nottificationList = new ArrayList<>();
    private int rowLayout;
    private Context context;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNottificationMsg;
        RelativeLayout linearLayoutNottification;
        TextView textNotificationDate;
        TextView textJobTitle;

        ImageView img;

        public ViewHolder(View v) {
            super(v);

            textViewNottificationMsg = (TextView) v.findViewById(R.id.text_nottifiction_msg);
            textNotificationDate = (TextView) v.findViewById(R.id.text_notificationDate);
            linearLayoutNottification = (RelativeLayout) v.findViewById(R.id.liner_nottifiction);
            textJobTitle = (TextView) v.findViewById(R.id.text_jobTitle);

            img = (ImageView) v.findViewById(R.id.imageLogo_quot);


        }
    }

    public NottificationListAdapter(List<NotificationList> nottificationList, int rowLayout, Context context) {
        this.nottificationList = nottificationList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public NottificationListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                  int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new NottificationListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        // For setting the material Textdrawable

        String Notification_msg = nottificationList.get(position).getMessage();

        String Date = nottificationList.get(position).getNotificationDate();

        String jobTitle = nottificationList.get(position).getNotificationMeta().getJob_title();

        //  Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
        // String jobTitle = "JOB TITLE";

        holder.textJobTitle.setText(jobTitle);

        holder.textNotificationDate.setText(Date);
        // holder.textNotificationDate.setTextColor(R.color.colorPrimary);

        holder.textViewNottificationMsg.setText(Notification_msg);

       /* int color = generator.getRandomColor();
       holder.linearLayoutNottification.setBackgroundColor(color);*/





        String job_title = nottificationList.get(position).getNotificationMeta().getJob_title();
        // int  color = generator.getColor(job_title);

        if (job_title!=null) {
            String firstLetter = String.valueOf(job_title.charAt(0));



        /*TextDrawable drawable1 = TextDrawable.builder().beginConfig().fontSize(45)
                .useFont(Typeface.defaultFromStyle(Typeface.ITALIC))
                .withBorder(1).endConfig()
                .buildRoundRect(firstLetter.toUpperCase(), color,4
                ); // radius in px*/


        // holder.img.setImageResource(bell);
       /* holder.img.setBackgroundColor(R.drawable.circle);*/




       /* Drawable mDrawable = context.getResources().getDrawable(R.drawable.circle);
        mDrawable.setColorFilter(new
                PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY));*/

        //code to set data in textview as initCap
        String var = (String) holder.textJobTitle.getText();
        String output = var.substring(0,1).toUpperCase() + var.substring(1);
        holder.textJobTitle.setText(output);
        }


    }

    @Override
    public int getItemCount() {
        if (nottificationList != null) {
            return nottificationList.size();
        }

        return 0;
    }
}