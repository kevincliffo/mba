package com.android.mba;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.io.InputStream;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private Context cntx;
    private List<Team> teams;

    public TeamAdapter(Context cntx, List<Team> teams) {
        this.cntx = cntx;
        this.teams = teams;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(cntx);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder teamViewHolder, int position) {
        Team team = teams.get(position);

        teamViewHolder.tvTeamName.setText(team.getName());
        teamViewHolder.tvCoach.setText(team.getCoach());
        teamViewHolder.tvLocation.setText(team.getLocation());
        teamViewHolder.tvPosition.setText(String.valueOf(team.getPosition()));

        new DownloadImageFromInternet((teamViewHolder.imageView))
                .execute(team.getImagePath());
       //teamViewHolder.imageView.setImageDrawable(cntx.getResources().getDrawable(team.getImage_id()));
    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
            Toast.makeText(cntx, "Please wait, it may take a few minute...", Toast.LENGTH_SHORT).show();
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);


            } catch (Exception e) {
                String message = e.getMessage();
                String stacktrace = e.getLocalizedMessage();
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    class TeamViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView tvTeamName;
        TextView tvCoach;
        TextView tvLocation;
        TextView tvPosition;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvTeamName = itemView.findViewById(R.id.tvTeamName);
            tvCoach = itemView.findViewById(R.id.tvCoach);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvPosition = itemView.findViewById(R.id.tvPosition);
        }
    }
}
