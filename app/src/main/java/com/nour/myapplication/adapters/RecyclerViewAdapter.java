package com.nour.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nour.myapplication.R;
import com.nour.myapplication.models.ResultModel;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context ;
    List<ResultModel>AdapterList ;

    public RecyclerViewAdapter(Context applicationContext, List<ResultModel> AdapterList) {
        this.context = applicationContext;
        this.AdapterList = AdapterList ;
    }
    public void setCountriesList (List<ResultModel>AdapterList ) {
        this.AdapterList = AdapterList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.countries_list,parent ,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
       holder.textView1.setText(AdapterList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        if(AdapterList != null){
            return AdapterList.size();
        }
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView1 ;


        public ViewHolder(View itemView) {
            super(itemView);
            textView1 = (TextView)itemView.findViewById(R.id.tvCounry);
        }

    } }