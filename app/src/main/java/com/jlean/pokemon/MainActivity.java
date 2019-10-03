package com.jlean.pokemon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.github.ybq.android.spinkit.SpinKitView;
import com.jlean.pokemon.adapters.PokeAdapter;
import com.jlean.pokemon.model.PokeResponse;
import com.jlean.pokemon.model.Pokemon;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final int QR_READ = 3546 ;
    private Retrofit retrofit;
    private static final String TAG = "Res";
    private PokeAdapter pokeAdaptar;

    int limit = 21;
    int offset = 0;
    boolean canLoad = true;
    private SpinKitView loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       ActionBar actionBar = getSupportActionBar();
       actionBar.setTitle("Pokedex");
        loader = findViewById(R.id.spin_kit);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecyclerView rvPokemons = findViewById(R.id.rv_pokemons);

        pokeAdaptar = new PokeAdapter(this);
        rvPokemons.setAdapter(pokeAdaptar);

        rvPokemons.setHasFixedSize(true);

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rvPokemons.setLayoutManager(gridLayoutManager);

        rvPokemons.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                Log.i(TAG, "onScrolled: " + dy);

                if(dy > 0){

                    int visibleCount = gridLayoutManager.getChildCount();//Cuanta los items en pantalla
                    int pastVisibleCount = gridLayoutManager.findFirstVisibleItemPosition();//Sirve para contar los elementos que ya pasaron
                    int totalItem = gridLayoutManager.getItemCount();//Items totales

                    Log.i(TAG, "vistos: "+ visibleCount);
                    Log.i(TAG, "totales: "+ totalItem);
                    Log.i(TAG, "los que pasaron: "+ pastVisibleCount);
                    if(canLoad){
                        if((visibleCount + pastVisibleCount) >= totalItem){
                            offset += limit;
                            getPokemons(offset);
                        }
                    }
                }
            }
        });

        getPokemons(offset);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.qr_reader:
                readQR();
                return true;
                default:
                    return super.onOptionsItemSelected(item);

        }





    }

    private void readQR() {
        Intent i = new Intent(this, QRReaderActivity.class);
        startActivityForResult(i, QR_READ);
    }

    private void getPokemons(int offset){

        canLoad = false;
        PokeService service = retrofit.create(PokeService.class);
        Call<PokeResponse> pokeResponseCall = service.getPokemons(offset, limit);

        pokeResponseCall.enqueue(new Callback<PokeResponse>() {


            @Override
            public void onResponse(Call<PokeResponse> call, Response<PokeResponse> response) {
                if(response.isSuccessful()){
                    PokeResponse pokeResponse = response.body();
                    ArrayList<Pokemon> pokemons = pokeResponse.getResults();
                    Log.i(TAG, pokemons.toString());
                    pokeAdaptar.addPokemons(pokemons);
                    loader.setVisibility(View.INVISIBLE);
                    canLoad= true;
                }else{
                    Log.i(TAG, "Error: " + response.errorBody());
                    canLoad = true;
                }
            }

            @Override
            public void onFailure(Call<PokeResponse> call, Throwable t) {
                canLoad= true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == QR_READ){
            if(resultCode == RESULT_OK){
                getPokemonInfo(data.getStringExtra(getString(R.string.qrcode)));

            }else if(resultCode == RESULT_CANCELED){

            }
        }
    }

    private void getPokemonInfo(String qrcode) {
        String qrdecoded = new String(Base64.decode(qrcode,Base64.NO_WRAP));
        Log.i("patos", "getPokemonInfo"+qrdecoded);
        String [] pokedata = qrdecoded.split(";");
        if(pokedata.length == 2){
            String name = pokedata[0];
            int id = Integer.parseInt(pokedata[1]);
            Intent intent = new Intent(this, PokeActivity.class);/*redirige a la otra vista*/


            intent.putExtra(this.getString(R.string.pokemon_aidi), id);
            intent.putExtra(this.getString(R.string.pokemon_name), name);

            startActivity(intent);/*Ativa la interfaz*/
        }
    }
}
