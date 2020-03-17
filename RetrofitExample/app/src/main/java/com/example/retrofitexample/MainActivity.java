package com.example.retrofitexample;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerPhotos;
    private PhotoAdapter mPhotoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerPhotos = findViewById(R.id.recycler_photos);
        mPhotoAdapter = new PhotoAdapter(this);
        mRecyclerPhotos.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerPhotos.setAdapter(mPhotoAdapter);

        DataService service = APIClient.getInstance().create(DataService.class);

        Call<List<Photo>> call = service.getAllPhotos();

        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                Toast.makeText(MainActivity.this, "SUCCESS " + response.body().size(), Toast.LENGTH_SHORT).show();
                mPhotoAdapter.setPhotos(response.body());
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "FAILURE " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
