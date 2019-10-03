package com.jlean.pokemon.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jlean.pokemon.PokeActivity;
import com.jlean.pokemon.R;
import com.jlean.pokemon.model.Pokemon;

import java.util.ArrayList;

public class PokeAdapter extends RecyclerView.Adapter<PokeAdapter.ViewHolder> implements ItemClickListener{


    private Context context;
    private ArrayList<Pokemon> pokemons;

    public PokeAdapter(Context context, ArrayList<Pokemon> pokemons) {
        this.context = context;
        this.pokemons = pokemons;
    }

    public PokeAdapter(Context context) {
        this.context = context;
        pokemons = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poke_item, parent, false);
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon pokemon = pokemons.get(position);

        holder.pokeName.setText(pokemon.getName());

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pokemon.getAidi()+".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.pokeImg);
    }

    @Override
    public int getItemCount() {
        //Esto es un if muy loco
        return pokemons != null ? pokemons.size() : 0;
    }

    public void addPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons.addAll(pokemons);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(context, PokeActivity.class);/*redirige a la otra vista*/

        Pokemon pokemon = pokemons.get(position);
        intent.putExtra(context.getString(R.string.pokemon_aidi), pokemon.getAidi());
        intent.putExtra(context.getString(R.string.pokemon_name), pokemon.getName());

        context.startActivity(intent);/*Ativa la interfaz*/

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView pokeImg;
        private TextView pokeName;


        //Se crea una variable del tipo de interfaz
        private ItemClickListener listener;

        public ViewHolder(@NonNull View itemView, ItemClickListener listener) {
            super(itemView);
            //Se igualan las interfaces para que poseean los mismos metodos
            this.listener = listener;

            pokeImg = itemView.findViewById(R.id.poke_img);
            pokeName = itemView.findViewById(R.id.poke_name);

            pokeImg.setOnClickListener(this);
            pokeName.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //Se llama el metodo por medio del "litener." que se refiere a la clase que existe en la misma clase
            listener.onClick(view, getLayoutPosition());
        }
    }
}

interface ItemClickListener{
    void onClick(View view, int position);

}








