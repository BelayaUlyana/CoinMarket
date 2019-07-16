
package com.coinmarket.listPOJO;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class USD implements Parcelable {

    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("volume_24h")
    @Expose
    private Double volume24h;
    @SerializedName("percent_change_1h")
    @Expose
    private Double percentChange1h;
    @SerializedName("percent_change_24h")
    @Expose
    private Double percentChange24h;
    @SerializedName("percent_change_7d")
    @Expose
    private Double percentChange7d;
    @SerializedName("market_cap")
    @Expose
    private Double marketCap;
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;

    protected USD(Parcel in) {
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readDouble();
        }
        if (in.readByte() == 0) {
            volume24h = null;
        } else {
            volume24h = in.readDouble();
        }
        if (in.readByte() == 0) {
            percentChange1h = null;
        } else {
            percentChange1h = in.readDouble();
        }
        if (in.readByte() == 0) {
            percentChange24h = null;
        } else {
            percentChange24h = in.readDouble();
        }
        if (in.readByte() == 0) {
            percentChange7d = null;
        } else {
            percentChange7d = in.readDouble();
        }
        if (in.readByte() == 0) {
            marketCap = null;
        } else {
            marketCap = in.readDouble();
        }
        lastUpdated = in.readString();
    }

    public static final Creator<USD> CREATOR = new Creator<USD>() {
        @Override
        public USD createFromParcel(Parcel in) {
            return new USD(in);
        }

        @Override
        public USD[] newArray(int size) {
            return new USD[size];
        }
    };

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getVolume24h() {
        return volume24h;
    }

    public void setVolume24h(Double volume24h) {
        this.volume24h = volume24h;
    }

    public Double getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(Double percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public Double getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(Double percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public Double getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(Double percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public Double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Double marketCap) {
        this.marketCap = marketCap;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (price == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(price);
        }
        if (volume24h == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(volume24h);
        }
        if (percentChange1h == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(percentChange1h);
        }
        if (percentChange24h == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(percentChange24h);
        }
        if (percentChange7d == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(percentChange7d);
        }
        if (marketCap == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(marketCap);
        }
        parcel.writeString(lastUpdated);
    }


}
