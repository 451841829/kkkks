package com.example.sink_to.wanzixi_zyone.presenter.contract;


import com.example.sink_to.wanzixi_zyone.ui.activity.BaseView;
import com.example.sink_to.wanzixi_zyone.presenter.BasePresenter;

public interface IRecommandContract {
    interface IPresenter extends BasePresenter {
        void requestData();

    }
    interface IView<IPresenter> extends BaseView<IPresenter> {
        void showData(String responseString);

    }
    

}
