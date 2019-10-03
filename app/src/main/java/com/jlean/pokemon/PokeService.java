package com.jlean.pokemon;

import com.jlean.pokemon.model.PokeResponse;
import com.jlean.pokemon.model.SinglePokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeService {

    @GET("pokemon")
    Call<PokeResponse> getPokemons(@Query("offset") int offset, @Query("limit") int limit);

    @GET("pokemon/{aidi}")
    Call<SinglePokemonResponse> getPokemon(@Path("aidi") int aidi);

}
