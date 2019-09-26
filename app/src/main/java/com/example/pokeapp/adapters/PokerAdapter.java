package com.example.pokeapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokeapp.PokeActivity;
import com.example.pokeapp.R;
import com.example.pokeapp.model.Pokemon;

import java.util.ArrayList;

public class PokerAdapter extends RecyclerView.Adapter<PokerAdapter.ViewHolder> implements ItemClickListener {
    private Context context;
    private ArrayList<Pokemon> pokemons;

    public PokerAdapter(Context context, ArrayList<Pokemon> pokemons) {
        this.context = context;
        this.pokemons = pokemons;
    }

    public PokerAdapter(Context context) {
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
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pokemon.getId()+".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.pokeImg);

    }

    @Override
    public int getItemCount() {
        return pokemons != null ? pokemons.size():0;
    }

    public void addPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons.addAll(pokemons);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view, int position) {
        Intent itent = new Intent(context, PokeActivity.class);
        Pokemon pokemon = pokemons.get(position);
        itent.putExtra(context.getString(R.string.pokemon_id), pokemon.getId());
        context.startActivity(itent);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private ImageView pokeImg;
        private TextView pokeName;
        private ItemClickListener listener;
        public ViewHolder(@NonNull View itemView, ItemClickListener listener) {
            super(itemView);
            this.listener = listener;
            pokeImg = itemView.findViewById(R.id.poke_img);
            pokeName= itemView.findViewById(R.id.poke_name);
            pokeImg.setOnClickListener(this);
            pokeName.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getLayoutPosition());
        }
    }


}
