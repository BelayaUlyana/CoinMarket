package com.coinmarket.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coinmarket.R;
import com.coinmarket.listPOJO.Datum;
import com.coinmarket.listPOJO.Quote;
import com.coinmarket.listPOJO.USD;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.DatumViewHolder> {

    public interface ItemClickListener {
        void click(int pos, Datum datum, Quote quote);
    }

    private List<Datum> datumList;
    private Context context;
    private ItemClickListener listener;

    MainAdapter(Context context, ItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    void addALL(List<Datum> valuePair) {
        this.datumList = valuePair;
        notifyDataSetChanged();
    }


    class DatumViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        private final ImageView imageView;
        private final TextView textViewName, textViewSymbol, textViewPrice, textViewHR1, textViewHR24, textViewD7;


        DatumViewHolder(View itemView) {
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
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.custom_row, parent, false);
        return new DatumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatumViewHolder holder, final int position) {
        String imageUrl = "https://res.cloudinary.com/dxi90ksom/image/upload/";
        USD usd = datumList.get(position).getQuote().getUSD();
        holder.textViewName.setText(datumList.get(position).getName());
        holder.textViewSymbol.setText(datumList.get(position).getSymbol());
        holder.textViewPrice.setText(usd.getPriceStr());
        holder.textViewHR1.setText(new SpannableStringBuilder("1hr: ").append(usd.getStrPercentChange1h()));
        holder.textViewHR24.setText(new SpannableStringBuilder("24hr: ").append(usd.getStrPercentChange24h()));
        holder.textViewD7.setText(new SpannableStringBuilder("1d: ").append(usd.getStrPercentChange7d()));
        Glide.with(holder.imageView.getContext())
                .load(imageUrl.concat(datumList.get(position).getSymbol().toLowerCase()).concat(".png"))
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.ic_mtrl_chip_checked_circle)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "MainAdapter quote = " + datumList.get(position).getQuote());
                listener.click(position, datumList.get(position), datumList.get(position).getQuote());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (datumList == null)
            return 0;
        else return datumList.size();
    }
}
