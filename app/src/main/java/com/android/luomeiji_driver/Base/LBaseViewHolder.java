package com.android.luomeiji_driver.Base;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;

public class LBaseViewHolder extends BaseViewHolder {
    public LBaseViewHolder(View view) {
        super(view);
    }

    public LBaseViewHolder setGlide(int viewId, String url) {
        ImageView imageView = getView(viewId);
        Glide.with(getConvertView()).load(url).into(imageView);
        return this;
    }

    public LBaseViewHolder setGlide(int viewId, int paht) {
        ImageView imageView = getView(viewId);
        Glide.with(getConvertView()).load(paht).into(imageView);
        return this;
    }

    public LBaseViewHolder setGlide(int viewId, int paht, RequestOptions requestOptions) {
        ImageView imageView = getView(viewId);
        Glide.with(getConvertView()).load(paht).apply(requestOptions).into(imageView);
        return this;
    }

    public LBaseViewHolder setGlide(int viewId, String ulr, RequestOptions requestOptions) {
        ImageView imageView = getView(viewId);
        Glide.with(getConvertView()).load(ulr).apply(requestOptions).into(imageView);
        return this;
    }
}
