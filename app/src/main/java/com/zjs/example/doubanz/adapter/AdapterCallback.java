package com.zjs.example.doubanz.adapter;

/**
 * Created by zzx on 18-1-19.
 */

public interface AdapterCallback<Data> {

    void update(Data data, RecyclerAdapter.ViewHolder<Data> holder);
}
