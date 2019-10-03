package com.jlean.pokemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jlean.pokemon.model.Abilities;
import com.jlean.pokemon.model.SinglePokemonResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PokeActivity extends AppCompatActivity {

    private static final String TAG = "pokeRes" ;
    private Retrofit retrofit;
    private Toolbar toolbar;
    private ImageView poke_img;
    private TextView namePoke;
    private LinearLayout contenedorAbilities;
    private TextView valueTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        poke_img = findViewById(R.id.poke_img);
        contenedorAbilities = (LinearLayout) findViewById(R.id.abilities_content);
        valueTV = new TextView(this);




        Intent intent = getIntent();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        if(intent != null){
            int aidi = intent.getIntExtra(getString(R.string.pokemon_aidi), 0);
            String name = intent.getStringExtra(getString(R.string.pokemon_name));

            if(aidi > 0){
                getSupportActionBar().setTitle(name);
                getPokemon(aidi);
            }
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getPokemon(int aidi) {
        Glide.with(this)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+aidi+".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(poke_img);
        PokeService service = retrofit.create(PokeService.class);
        Call<SinglePokemonResponse> pokeResponseCall = service.getPokemon(aidi);

        pokeResponseCall.enqueue(new Callback<SinglePokemonResponse>() {
            @Override
            public void onResponse(Call<SinglePokemonResponse> call, Response<SinglePokemonResponse> response) {
                if(response.isSuccessful()){
                    SinglePokemonResponse singlePokemonResponse = response.body();

                    for(int i=0; i<singlePokemonResponse.getAbilities().size();i++){
                        valueTV.setText(singlePokemonResponse.getAbilities().get(i).getAbility().getName());
                        valueTV.setLayoutParams(contenedorAbilities.getLayoutParams());
                        contenedorAbilities.addView(valueTV);
                        Log.i("infoPoke","abilities "+singlePokemonResponse.getAbilities().get(i).getAbility().getName());
                    }

                    Log.i("infoPoke","nombre "+singlePokemonResponse.getName());

                    Log.i("infoPoke","sprite "+singlePokemonResponse.getSprites().getBack_shiny());

                }else{
                    Log.i(TAG, "Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<SinglePokemonResponse> call, Throwable t) {


            }
        });
    }
}
