package com.example.sink_to.wanzixi_zyone.presenter;


import com.example.sink_to.wanzixi_zyone.data.model.IModel;
import com.example.sink_to.wanzixi_zyone.data.model.IModelImpl;
import com.example.sink_to.wanzixi_zyone.presenter.contract.IRecommandContract;

public class IPresenterImpl implements IRecommandContract.IPresenter {
    IRecommandContract.IView iView;
    private IModel iModel;


    public IPresenterImpl(IRecommandContract.IView iView){
        this.iView = iView;
        iModel = new IModelImpl();
        iView.setPresenter(this);

    }
    @Override
    public void start() {

    }

    @Override
    public void requestData() {

        iModel.setData(new IModel.setOnShowDataLisenter() {
            @Override
            public void onShowData(String responseString) {
               iView.showData(responseString);

            }
        });
    }
}
