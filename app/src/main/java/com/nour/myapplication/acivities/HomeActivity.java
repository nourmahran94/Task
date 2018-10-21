package com.nour.myapplication.acivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.nour.myapplication.R;
import com.nour.myapplication.adapters.RecyclerViewAdapter;
import com.nour.myapplication.models.BigModel;
import com.nour.myapplication.models.ResultModel;
import com.nour.myapplication.network.ApiCilent;
import com.nour.myapplication.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    List<ResultModel> AdapterList ;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerAdapter ;
    private BigModel bigModel;
    private ProgressBar progressBar ;
    private FirebaseAuth mAuth ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        intView ();
        intCallBack ();

    }

    private void intView() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        findViewById(R.id.buttonLogOut).setOnClickListener(this);


        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerViewAdapter(getApplicationContext() , AdapterList);
        recyclerView.setAdapter(recyclerAdapter);

    }

    private void intCallBack() {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiCilent.getClient().create(ApiInterface.class);

        Call<BigModel> Call = apiInterface.getCategories();
        Call.enqueue(new Callback<BigModel>() {
            @Override
            public void onResponse(Call<BigModel> call, Response<BigModel>response) {
                AdapterList = new ArrayList<>();
                bigModel = response.body();
                AdapterList = bigModel.getBigModel().getCategoryModelArrayList();
                recyclerAdapter.setCountriesList(AdapterList);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<BigModel> call, Throwable t) {

            }  });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLogOut :
               finish();
                break;
        }

    }
}
