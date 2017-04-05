//package com.saberrr.openchina.ui.adapter;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.support.v4.view.PagerAdapter;
//import android.support.v7.widget.RecyclerView;
//import android.util.SparseArray;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.jude.rollviewpager.RollPagerView;
//import com.jude.rollviewpager.hintview.ColorPointHintView;
//import com.saberrr.openchina.R;
//import com.saberrr.openchina.bean.InformationHearBean;
//import com.saberrr.openchina.gloab.AppApplication;
//import com.saberrr.openchina.ui.adapter.interfaces.BodyType;
//import com.saberrr.openchina.ui.adapter.interfaces.FootType;
//import com.saberrr.openchina.ui.adapter.interfaces.HeadType;
//import com.saberrr.openchina.ui.adapter.interfaces.ItemType;
//import com.saberrr.openchina.utils.ToastUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by 丁银晨 on 2017/4/1.
// */
//
//public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyViewHolder> {
//    public List<ItemType> mDatas = new ArrayList<>();
//    private RollPagerView     mRollPagerView;
//    private TestNormalAdapter mTestNormalAdapter;
//    private Context mContext;
//
//    public RecyclerAdapter(List<ItemType> datas, Context context) {
//        mDatas = datas;
//        mContext = context;
//    }
//
//    @Override
//    public RecyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = null;
//        switch (viewType) {
//            case HEADTYPE:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_head, parent, false);
//                //                TextView textView1 = new TextView(parent.getContext());
//                //                textView1.setText("我是头");
//                //                view = textView1;
//                break;
//            case BODYTYPE:
//                //                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img_sel, parent, false);
//                TextView textView2 = new TextView(parent.getContext());
//                textView2.setText("我是身");
//                view = textView2;
//                break;
//            case FOOTTYPE:
//                TextView textView = new TextView(parent.getContext());
//                textView.setText("我是脚");
//                view = textView;
//                break;
//        }
//
//        return new RecyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(RecyViewHolder holder, int position) {
//        switch (getItemViewType(position)) {
//            case HEADTYPE:
//                mRollPagerView = (RollPagerView) holder.getViewById(R.id.rollPagerView_information);
//
//                mTestNormalAdapter = new TestNormalAdapter(mDatas.get(position));
//                mRollPagerView.setAdapter(mTestNormalAdapter);
//                mRollPagerView.setPlayDelay(2000);
//                //设置透明度
//                mRollPagerView.setAnimationDurtion(500);
//
//                mRollPagerView.setAdapter(mTestNormalAdapter);
//
//                mRollPagerView.setHintView(new ColorPointHintView(mContext, Color.GREEN, Color.WHITE));
//                mRollPagerView.setHintPadding(20, 20, 20, 20);
//
//
//                break;
//            case BODYTYPE:
//
//                //                LinearLayout llTitle = (LinearLayout) holder.getViewById(R.id.ll_title);
//                //                TextView tvTitle = (TextView) holder.getViewById(R.id.tv_title);
//                //                TextView tvDescription = (TextView) holder.getViewById(R.id.tv_description);
//                //                TextView tvTime = (TextView) holder.getViewById(R.id.tv_time);
//                //                ImageView ivInfoComment = (ImageView) holder.getViewById(R.id.iv_info_comment);
//                //                TextView tvCommentCount = (TextView) holder.getViewById(R.id.tv_comment_count);
//
//
//                break;
//            case FOOTTYPE:
//
//                break;
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return mDatas.size();
//    }
//
//    public static final int HEADTYPE = 0;
//    public static final int BODYTYPE = 1;
//    public static final int FOOTTYPE = 2;
//
//    @Override
//    public int getItemViewType(int position) {
//        if (mDatas.get(position) instanceof HeadType) {
//            return HEADTYPE;
//        }
//        if (mDatas.get(position) instanceof BodyType) {
//            return BODYTYPE;
//        }
//        if (mDatas.get(position) instanceof FootType) {
//            return FOOTTYPE;
//        }
//        return BODYTYPE;
//    }
//
//    class RecyViewHolder extends RecyclerView.ViewHolder {
//
//        public RecyViewHolder(View itemView) {
//            super(itemView);
//        }
//
//        SparseArray<View> mViews = new SparseArray<>();
//
//        public View getViewById(int id) {
//            View view = mViews.get(id);
//            if (view == null) {
//                view = itemView.findViewById(id);
//                mViews.put(id, view);
//            }
//            return view;
//        }
//    }
//
//    private class TestNormalAdapter extends PagerAdapter {
//
//
//        private InformationHearBean itemType;
//
//        public TestNormalAdapter(ItemType itemType) {
//            this.itemType = (InformationHearBean) itemType;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, final int position) {
//            List<InformationHearBean.ResultBean.ItemsBean> itemsBeen = itemType.getResult().getItems();
//            View view = View.inflate(container.getContext(), R.layout.viewpager_item_information, null);
//            ImageView mImageView = (ImageView) view.findViewById(R.id.iv_item_viewpager_information);
//            TextView mTextView = (TextView) view.findViewById(R.id.tv_item_viewpager_information);
//            mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            Glide.with(AppApplication.appContext).load(itemsBeen.get(position).getImg()).into(mImageView);
//            mTextView.setText(itemsBeen.get(position).getName());
//            mTextView.setTextColor(Color.WHITE);
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ToastUtils.showToast("我被点击了" + position);
//                }
//            });
//            container.addView(view);
//            return view;
//        }
//
//        @Override
//        public int getCount() {
//            return itemType.getResult().getItems().size();
//        }
//    }
//}
