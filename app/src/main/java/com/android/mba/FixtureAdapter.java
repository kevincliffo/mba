package com.android.mba;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FixtureAdapter extends RecyclerView.Adapter<FixtureAdapter.FixtureViewHolder> {

    private Context cntx;
    private List<Fixture> Fixtures;

    public FixtureAdapter(Context cntx, List<Fixture> Fixtures) {
        this.cntx = cntx;
        this.Fixtures = Fixtures;
    }

    @NonNull
    @Override
    public FixtureViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(cntx);
        View view = inflater.inflate(R.layout.fixture_layout, null);
        return new FixtureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FixtureViewHolder FixtureViewHolder, int position) {
        Fixture Fixture = Fixtures.get(position);

        FixtureViewHolder.tvTeamA.setText(Fixture.getTeamA());
        FixtureViewHolder.tvTeamB.setText(Fixture.getTeamB());
        FixtureViewHolder.tvGameDate.setText(Fixture.getGameDate());
        FixtureViewHolder.tvGameTime.setText(Fixture.getGameTime());
    }

    @Override
    public int getItemCount() {
        return Fixtures.size();
    }

    class FixtureViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvTeamA;
        TextView tvTeamB;
        TextView tvGameDate;
        TextView tvGameTime;

        public FixtureViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTeamA = itemView.findViewById(R.id.tvTeamA);
            tvTeamB = itemView.findViewById(R.id.tvTeamB);
            tvGameDate = itemView.findViewById(R.id.tvGameDate);
            tvGameTime = itemView.findViewById(R.id.tvGameTime);
        }
    }
}
