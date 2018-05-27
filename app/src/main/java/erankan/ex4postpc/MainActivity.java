package erankan.ex4postpc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity implements ImageAdapter.ItemClickListener{

    public static final String IMGUR_URL = "https://api.imgur.com";
    public static final String CLIENT_ID = "9b8b5da5dcc3d0f";
    public static final String ALBUM_ID = "Q0g2qW2";
    public static final int COLS = 1;

    private ArrayList<MyImage> myImages;
    private ImageAdapter adapter;
    private Retrofit retrofit;
    Retrofit.Builder retro_builder;
    OkHttpClient.Builder http_builder;
    Button myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = setRecycle();
        myImages = new ArrayList<>();
        adapter = new ImageAdapter(this, myImages);
        setAdapter(recyclerView);
        myButton = findViewById(R.id.imagesButton);
        openAlbum();
    }


    public RecyclerView setRecycle(){
        RecyclerView rv = findViewById(R.id.recycler);
        rv.setLayoutManager(new GridLayoutManager(this, COLS));
        return rv;
    }

    public void setAdapter(RecyclerView recyclerView){
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }


    private void openAlbum(){
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setConnection();
                ImgurImage imgurImage = retrofit.create(ImgurImage.class);
                Call<AlbumData> dataCall = imgurImage.getAlbumImages(ALBUM_ID);
                dataCall.enqueue(new Callback<AlbumData>() {
                    @Override
                    public void onResponse(Call<AlbumData> call, Response<AlbumData> response) {
                        AlbumData data = response.body();
                        if (data != null){
                            for (AlbumData.Image image : data.data.images){
                                myImages.add(new MyImage(image.link));
                            }
                            adapter.notifyDataSetChanged();
                            myButton.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<AlbumData> call, Throwable t) {
                    }
                });
            }
        });
    }

    public void onItemClick(View view, int position){}

    public interface ImgurImage{
        @Headers("Authorization: Client-ID " + CLIENT_ID)
        @GET("/3/album/{albumHash}")
        Call<AlbumData> getAlbumImages (@Path("albumHash") String albumHash);
    }

    public void setConnection(){
        retro_builder = new Retrofit.Builder().baseUrl(IMGUR_URL).addConverterFactory(GsonConverterFactory.create());
        http_builder = new OkHttpClient.Builder();
        retrofit = retro_builder.client(http_builder.build()).build();
    }
}
