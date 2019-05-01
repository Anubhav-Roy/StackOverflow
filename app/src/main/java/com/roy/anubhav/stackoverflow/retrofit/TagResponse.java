package com.roy.anubhav.stackoverflow.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//Serialized JSON object for the tagResponse received
public class TagResponse {

    @SerializedName("items")
    private List<Tag> items;

    @SerializedName("has_more")
    private boolean has_more;

    @SerializedName("quota_max")
    private long quota_max;

    @SerializedName("quota_remaining")
    private long quota_remaining;

    public List<Tag> getItems() {
        return items;
    }

    public void setItems(List<Tag> items) {
        this.items = items;
    }

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public long getQuota_max() {
        return quota_max;
    }

    public void setQuota_max(long quota_max) {
        this.quota_max = quota_max;
    }

    public long getQuota_remaining() {
        return quota_remaining;
    }

    public void setQuota_remaining(long quota_remaining) {
        this.quota_remaining = quota_remaining;
    }
}
