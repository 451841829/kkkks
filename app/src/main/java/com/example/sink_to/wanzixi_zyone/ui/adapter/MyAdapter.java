package com.example.sink_to.wanzixi_zyone.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sink_to.wanzixi_zyone.R;
import com.example.sink_to.wanzixi_zyone.data.bean.MyData;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sink_to on 2018/1/17.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private List<MyData.ResultBean.ListBean> list ;
    private Context mContext;
    private final static int ITEM_ONE = 1;
    private final static int ITEM_TWO = 2;

    public MyAdapter(List<MyData.ResultBean.ListBean> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view;
        RecyclerView.ViewHolder holder;
        if (viewType == ITEM_ONE) {
            view = inflater.inflate(R.layout.recy_item_one, parent, false);
            holder = new HolderOne(view);
        } else {
            view = inflater.inflate(R.layout.recy_item_two, parent, false);
            holder = new HolderTwo(view);
        }
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyData.ResultBean.ListBean listBean = list.get(position);
        if (holder instanceof HolderOne) {
            ((HolderOne) holder).mTextOne.setText(listBean.getContenttype());
            Picasso.with(mContext).load(listBean.getCoverurl()).into(((HolderOne) holder).mImageOne);
            ((HolderOne)holder).itemView.setTag(position);
        }else{
            ((HolderTwo)holder).mTextTwo.setText(listBean.getContenttype());
            Picasso.with(mContext).load(listBean.getCoverurl()).into(((HolderTwo) holder).mImageTwo);
            ((HolderTwo)holder).itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return ITEM_ONE;
        } else {
            return ITEM_TWO;
        }
    }


    class HolderOne extends RecyclerView.ViewHolder {

        private TextView mTextOne;
        private ImageView mImageOne;

        public HolderOne(View itemView) {
            super(itemView);
            mTextOne = itemView.findViewById(R.id.Recy_One_Text);
            mImageOne = itemView.findViewById(R.id.Recy_One_Image);
        }
    }


    class HolderTwo extends RecyclerView.ViewHolder {
        private TextView mTextTwo;
        private ImageView mImageTwo;

        public HolderTwo(View itemView) {
            super(itemView);
            mImageTwo = itemView.findViewById(R.id.Recy_Two_Image);
            mTextTwo = itemView.findViewById(R.id.Recy_Two_Text);
        }
    }


    public interface OnItemClick {
        void setOnItem(View v, int position);
    }

    private OnItemClick onitem;


    @Override
    public void onClick(View v) {
        if (onitem != null) {
            onitem.setOnItem(v, (Integer) v.getTag());
        }
    }

    public void setOnItemListener(OnItemClick item) {
        this.onitem = item;
    }

}
