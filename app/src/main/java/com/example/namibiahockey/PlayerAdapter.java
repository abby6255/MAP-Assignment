package com.example.namibiahockey;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private List<Player> playerList;

    public PlayerAdapter(List<Player> playerList) {
        this.playerList = playerList;
    }

    public void updateData(List<Player> newPlayers) {
        this.playerList = newPlayers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_player, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = playerList.get(position);
        holder.tvName.setText(player.getName());
        holder.tvAge.setText(String.valueOf(player.getAge()));
        holder.tvPosition.setText(player.getPosition());
        holder.tvContact.setText(player.getContact());
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAge, tvPosition, tvContact;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvPosition = itemView.findViewById(R.id.tvPosition);
            tvContact = itemView.findViewById(R.id.tvContact);
        }
    }
}