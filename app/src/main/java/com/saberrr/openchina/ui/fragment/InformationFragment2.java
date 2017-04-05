//package com.saberrr.openchina.ui.fragment;
//
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.saberrr.openchina.R;
//import com.saberrr.openchina.bean.InformationBodyBean;
//import com.saberrr.openchina.bean.InformationHearBean;
//import com.saberrr.openchina.manager.cacheManager.JsonCacheManager;
//import com.saberrr.openchina.net.Urls;
//import com.saberrr.openchina.ui.adapter.RecyclerAdapter;
//import com.saberrr.openchina.ui.adapter.interfaces.BodyType;
//import com.saberrr.openchina.ui.adapter.interfaces.FootType;
//import com.saberrr.openchina.ui.adapter.interfaces.ItemType;
//import com.saberrr.openchina.utils.ThreadUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by 丁银晨 on 2017/4/1.
// */
//
//public class InformationFragment2 extends BaseFragment {
//
//    private RecyclerView  mRecyclerView;
//    private List<ItemType>            datas = new ArrayList<>();
////    private List<InformationBodyBean.ResultBean.ItemsBean> bodyDatas = new ArrayList<>();
////    private List<InformationBodyBean> bodys = new ArrayList<>();
//    private ImageView           mImageView;
//    private TextView            mTextView;
////    private TestNormalAdapter   mTestNormalAdapter;
//
//
//
//    @Override
//    protected boolean needRefresh() {
//        return false;
//    }
//
//    @Override
//    public View createView() {
//        View view = View.inflate(getContext(), R.layout.fragmeng_information2, null);
//
//        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_information);
//        init();
//        return view;
//    }
//
//    private void init() {
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        mRecyclerView.setAdapter(new RecyclerAdapter(datas,getContext()));
//    }
//
//
//
//    @Override
//    public Object getData() {
////        String nextPageToken = "";
////        if (bodys == null || bodys.size()==0){
////
////        }else {
////            nextPageToken = bodys.get(0).getResult().getNextPageToken();
////        }
//
//
//        InformationHearBean informationHearBean = JsonCacheManager.getInstance().getDataBean(Urls.BANNER, InformationHearBean.class);
//        InformationBodyBean informationBodyBean = JsonCacheManager.getInstance().getDataBean(Urls.NEWS, InformationBodyBean.class);
//        if (informationHearBean == null || informationBodyBean == null) {
//            return null;
//        }
////        bodys.add(0,informationBodyBean);
//        //List<InformationHearBean.ResultBean.ItemsBean> itemsBeanList = informationHearBean.getResult().getItems();
//        List<InformationBodyBean.ResultBean.ItemsBean> badyBeanList = informationBodyBean.getResult().getItems();
//        datas.add(informationHearBean);
//        datas.add(new BodyType() {
//            @Override
//            public int hashCode() {
//                return super.hashCode();
//            }
//        });
//        datas.add(new FootType() {
//            @Override
//            public int hashCode() {
//                return super.hashCode();
//            }
//        });
////        bodyDatas.addAll(badyBeanList);
//        ThreadUtils.runMain(new Runnable() {
//            @Override
//            public void run() {
//                mRecyclerView.requestLayout();
//
//            }
//        });
//
//        return datas;
//    }
//
//
////    private class TestNormalAdapter extends PagerAdapter {
////
//////        @Override
//////        public View getView(ViewGroup container, final int position) {
//////
//////
////////            mImageView.setOnClickListener(new View.OnClickListener() {
////////                @Override
////////                public void onClick(View v) {
////////
////////                    ToastUtils.showToast("我被点击了"+position);
////////                }
////////            });
//////
//////            return view;
//////        }
////
////        @Override
////        public void destroyItem(ViewGroup container, int position, Object object) {
////            container.removeView((View) object);
////        }
////
////        @Override
////        public boolean isViewFromObject(View view, Object object) {
////            return view == object;
////        }
////
////        @Override
////        public Object instantiateItem(ViewGroup container, final int position) {
////            View view = View.inflate(container.getContext(), R.layout.viewpager_item_information, null);
////            mImageView = (ImageView) view.findViewById(R.id.iv_item_viewpager_information);
////            mTextView = (TextView) view.findViewById(R.id.tv_item_viewpager_information);
////            mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
////            Glide.with(AppApplication.appContext).load(datas.get(position).getImg()).into(mImageView);
////            mTextView.setText(datas.get(position).getName());
////            mTextView.setTextColor(Color.WHITE);
////           view.setOnClickListener(new View.OnClickListener() {
////               @Override
////               public void onClick(View v) {
////                   ToastUtils.showToast("我被点击了"+position);
////               }
////           });
////            container.addView(view);
////            return view;
////        }
////
////        @Override
////        public int getCount() {
////            return datas.size();
////        }
////    }
//}
