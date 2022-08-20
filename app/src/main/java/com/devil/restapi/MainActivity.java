package com.devil.restapi;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button fetchData = findViewById(R.id.getData);
        fetchData.setOnClickListener(v -> {
            Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
            Call<Model> call = methods.getAllData();

            call.enqueue(new Callback<Model>() {

                @Override
                public void onResponse(@NonNull Call <Model> call, @NonNull Response <Model> response) {
                    Log.e(TAG,"Response code generated : "+response.code());

                    assert response.body() != null;
                    ArrayList<Model.data> data = response.body().getData();

                    for (Model.data data1 : data){
                        Log.e(TAG,"Response mails are: "+data1.getEmail());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Model> call, @NonNull Throwable t) {
                    Toast.makeText(MainActivity.this, "Failed Data: "+t, Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}