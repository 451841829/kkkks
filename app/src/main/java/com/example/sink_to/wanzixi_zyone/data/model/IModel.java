package com.example.sink_to.wanzixi_zyone.data.model;



public interface IModel {
    void setData(setOnShowDataLisenter lisenter);

    interface setOnShowDataLisenter{
        void onShowData(String responseString);
    }
}
