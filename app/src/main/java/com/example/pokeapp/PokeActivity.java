package com.example.pokeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.pokeapp.model.PokemonDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PokeActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private final String TAG = getClass().getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        if(intent!=null){
            int id = intent.getIntExtra(getString(R.string.pokemon_id), 0);
            if(id>0){
                getPokemon(id);
            }
        }
    }

    private void getPokemon(int id) {
        PokeService service = retrofit.create(PokeService.class);
        Call<PokemonDetail> pokeResponseCall = service.getPokemon(id);
        pokeResponseCall.enqueue(new Callback<PokemonDetail>() {
            @Override
            public void onResponse(Call<PokemonDetail> call, Response<PokemonDetail> response) {
                if(response.isSuccessful()){
                    String pokeResponse = response.toString();

                        Log.i("patos",pokeResponse);




                }
            }

            @Override
            public void onFailure(Call<PokemonDetail> call, Throwable t) {

            }
        });


    }
}
