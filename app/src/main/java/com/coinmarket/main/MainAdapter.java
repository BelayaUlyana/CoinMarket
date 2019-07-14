package com.coinmarket.main;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coinmarket.R;
import com.coinmarket.listPOJO.Datum;
import com.coinmarket.listPOJO.USD;

import java.text.DecimalFormat;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.DatumViewHolder> {

    private List<Datum> valuePairList;
    Context context;
    private LayoutInflater mInflater;

    public MainAdapter(Context context) {
        this.context = context;
    }

    void addALL(List<Datum> valuePair) {
        this.valuePairList = valuePair;
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
        String imageUrl = "https://res.cloudinary.com/dxi90ksom/image/upload/";
        USD usd = valuePairList.get(position).getQuote().getUSD();
        holder.textViewName.setText(valuePairList.get(position).getName());
        holder.textViewSymbol.setText(valuePairList.get(position).getSymbol());
        holder.textViewPrice.setText(decFormat("$#.###", usd.getPrice()));
        holder.textViewHR1.setText(setTextColor("hr1: ", "#.##%", usd.getPercentChange1h()));
        holder.textViewHR24.setText(setTextColor("hr24: ", "#.##%", usd.getPercentChange24h()));
        holder.textViewD7.setText(setTextColor("d7: ", "#.##%", usd.getPercentChange7d()));
        Glide.with(holder.imageView.getContext())
                .load(imageUrl.concat(valuePairList.get(position).getSymbol().toLowerCase()).concat(".png"))
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.ic_mtrl_chip_checked_circle)
                .into(holder.imageView);
    }

    SpannableString setTextColor(String str, String format, Double price) {
        SpannableString spannableString = new SpannableString(str.concat(decFormat(format, price)));
        if (price > 0) {
            spannableString.setSpan(new ForegroundColorSpan(Color.GREEN), str.length(), spannableString.length(), 0);
        } else {
            spannableString.setSpan(new ForegroundColorSpan(Color.RED), str.length(), spannableString.length(), 0);
        }
        return spannableString;
    }

    String decFormat(String format, Double price) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(price);
    }


    @Override
    public int getItemCount() {
        if (valuePairList == null)
            return 0;
        else return valuePairList.size();
    }
}
