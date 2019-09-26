package com.example.pokeapp;

import com.example.pokeapp.model.PokeResponse;
import com.example.pokeapp.model.PokemonDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeService {
    @GET("pokemon")
    Call<PokeResponse> getPokemons(@Query("offset") int offset, @Query("limit") int limit);

    @GET("pokemon/{id}")
    Call<PokemonDetail> getPokemon(@Path("id") int id);

}
