package com.coinmarket.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coinmarket.POJO.Datum;
import com.coinmarket.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.DatumViewHolder> {

    private List<Datum> valuePairList;
    Context context;
    private LayoutInflater mInflater;

    public MainAdapter(Context context) {
        this.context = context;
    }

    void addALL(List<Datum> results) {
        this.valuePairList = results;
        notifyDataSetChanged();
    }


    class DatumViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        private final ImageView imageView;
        private final TextView textViewName, textViewSymbol, textViewPrice, textViewHR1, textViewHR24, textViewD7;


        public DatumViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            textViewName = mView.findViewById(R.id.name);
            imageView = mView.findViewById(R.id.image);
            textViewSymbol = mView.findViewById(R.id.symbol);
            textViewPrice = mView.findViewById(R.id.price);
            textViewHR1 = mView.findViewById(R.id.hr1);
            textViewHR24 = mView.findViewById(R.id.hr24);
            textViewD7 = mView.findViewById(R.id.d7);
        }

    }

    @NonNull
    @Override
    public DatumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.custom_row, parent, false);
        return new DatumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatumViewHolder holder, int position) {
        holder.textViewName.setText(valuePairList.get(position).getName());
        holder.textViewSymbol.setText(valuePairList.get(position).getSymbol());
        holder.textViewPrice.setText("$ " + String.format("%.3f", valuePairList.get(position).getQuote().getUSD().getPrice()));
        holder.textViewHR1.setText("1hr: " + String.format("%.2f", valuePairList.get(position).getQuote().getUSD().getPercentChange1h()));
        holder.textViewHR1.setText("24hr: " + String.format("%.2f", valuePairList.get(position).getQuote().getUSD().getPercentChange24h()));
        holder.textViewHR1.setText("7d: " + String.format("%.2f", valuePairList.get(position).getQuote().getUSD().getPercentChange7d()));
    }


    @Override
    public int getItemCount() {
        if (valuePairList == null)
            return 0;
        else return valuePairList.size();
    }
}
