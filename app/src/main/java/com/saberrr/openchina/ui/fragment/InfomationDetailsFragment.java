package com.saberrr.openchina.ui.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.comprehensivebean.CommentBean;
import com.saberrr.openchina.bean.comprehensivebean.CommentHead;
import com.saberrr.openchina.bean.comprehensivebean.InfomationDetailBean;
import com.saberrr.openchina.bean.comprehensivebean.TitleBean;
import com.saberrr.openchina.manager.cacheManager.JsonCacheManager;
import com.saberrr.openchina.net.HttpServiceApi;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.activity.ShowActivity;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;
import com.saberrr.openchina.ui.view.MyScrollView;
import com.saberrr.openchina.ui.view.MyWebView;
import com.saberrr.openchina.utils.Constant;
import com.saberrr.openchina.utils.SpUtil;
import com.saberrr.openchina.utils.StringUtils;
import com.saberrr.openchina.utils.ThreadUtils;
import com.saberrr.openchina.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by 丁银晨 on 2017/4/2.
 */

public class InfomationDetailsFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener, ShowActivity.OnClickListener, TextView.OnEditorActionListener {

    @BindView(R.id.recyclerView_informationDetail)
    RecyclerView mRecyclerViewInformationDetail;
    @BindView(R.id.tv_title_informationdetailwap)
    TextView     mTvTitleInformationdetailwap;
    @BindView(R.id.tv_time_informationdetailwap)
    TextView     mTvTimeInformationdetailwap;
    @BindView(R.id.wb_informationdetailwap)
    MyWebView    mWbInformationdetailwap;
    @BindView(R.id.et_bottom_comment)
    EditText     mEtBottomComment;
    @BindView(R.id.ll_bottom_comment)
    LinearLayout mLlBottomComment;
    @BindView(R.id.scrollView_detail)
    MyScrollView mScrollViewDetail;
    @BindView(R.id.ll_web)
    LinearLayout mLlWeb;
    private String mUrl;
    private String mCount;
    private int    mId;
    private List<Object> mDatas = new ArrayList();
    private FinalRecycleAdapter mFinalRecycleAdapter;
    private String              mType;
    private List<InfomationDetailBean> bean   = new ArrayList<>();
    private int                        isOpen = 1;
    private int mHeight;
    private int mScrollHeight;
    private int mPosition1;
    private int mHeight1;

    @Override
    protected boolean needRefresh() {
        return false;

    }

    @Override
    public View createView() {
        Bundle bundle = getArguments();
        mUrl = bundle.getString(Constant.BLOGDETAILSFRAGMENT.HREF);
        mCount = bundle.getString(Constant.BLOGDETAILSFRAGMENT.COMMENTCOUNT);
        mId = bundle.getInt(Constant.BLOGDETAILSFRAGMENT.ID);
        mType = bundle.getString(Constant.BLOGDETAILSFRAGMENT.TYPE);

        View view = View.inflate(getContext(), R.layout.fragment_blogdatails, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        HashMap<Class, Integer> map = new HashMap<>();
        //map.put(InfomationDetailBean.class, R.layout.informationdetailwap);
        map.put(TitleBean.class, R.layout.informationdetailtitle);
        map.put(InfomationDetailBean.ResultBean.AboutsBean.class, R.layout.informationdetailrecommend);
        map.put(CommentHead.class, R.layout.commenthead);
        map.put(CommentBean.ResultBean.ItemsBean.class, R.layout.comment);
        mFinalRecycleAdapter = new FinalRecycleAdapter(mDatas, map, this);
        mRecyclerViewInformationDetail.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewInformationDetail.setAdapter(mFinalRecycleAdapter);
        this.setToolbarIconOnClickListener(this);
        mEtBottomComment.setOnEditorActionListener(this);
        initComment();
    }

    private void initComment() {

        mScrollViewDetail.registerOnScrollViewScrollToBottom(new MyScrollView.OnScrollBottomListener() {
            @Override
            public void srollToBottom() {
                ObjectAnimator.ofFloat(mLlBottomComment, "TranslationY", mHeight, 0).setDuration(500).start();
                isOpen = 1;
            }
        });
        mScrollViewDetail.setOnScrollChangedListener1(new MyScrollView.OnScrollChangedListener1() {
            @Override
            public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
                //                System.out.println("int x"+ x +"int y"+ y +"int oldx"+ oldx +"int oldy"+ oldy );
                if (y - oldy > 0 && isOpen == 1) {
                    isOpen = 2;
                    animationUpData(x, y, oldx, oldy);

                }
                if (y - oldy < 0 && isOpen == 2) {
                    isOpen = 1;
                    animationUpData(x, y, oldx, oldy);
                }

            }
        });
    }

    private void animationUpData(int x, int y, int oldx, int oldy) {
        mHeight = mLlBottomComment.getHeight();
        ValueAnimator valueAnimator = new ValueAnimator();
        if (y - oldy > 0) {
            isOpen = 2;
            ObjectAnimator.ofFloat(mLlBottomComment, "TranslationY", 0, mHeight).setDuration(500).start();
        } else if (y - oldy <= 0) {
            isOpen = 1;
            ObjectAnimator.ofFloat(mLlBottomComment, "TranslationY", mHeight, 0).setDuration(500).start();

        }

    }

    private void initData() {

        mTvTitleInformationdetailwap.setText(bean.get(0).getResult().getTitle());
        mTvTimeInformationdetailwap.setText(bean.get(0).getTime() + "");

        WebSettings settings = mWbInformationdetailwap.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        mWbInformationdetailwap.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent ev) {

                ((WebView) v).requestDisallowInterceptTouchEvent(true);


                return false;
            }
        });
        mWbInformationdetailwap.setWebViewClient(new WebViewClient() {


        });
        mWbInformationdetailwap.loadDataWithBaseURL("", bean.get(0).getResult().getBody(), "text/html", "utf-8", "");
    }


    @Override
    public Object getData() {
        mDatas.clear();
        bean.clear();
        InfomationDetailBean infomationDetailBean = JsonCacheManager.getInstance().getDataBean(Urls.CONTENT + mId, InfomationDetailBean.class);
        CommentBean commentBean = JsonCacheManager.getInstance().getDataBean(Urls.COMMENT1 + mId + Urls.COMMENTTYEP + mType, CommentBean.class);

        if (infomationDetailBean == null||infomationDetailBean.getCode()==404) {
            return null;
        }
        List<InfomationDetailBean.ResultBean.AboutsBean> aboutsBeen = infomationDetailBean.getResult().getAbouts();
        final int commentCount = infomationDetailBean.getResult().getCommentCount();
        bean.add(infomationDetailBean);

        // mDatas.add(infomationDetailBean);
        if (aboutsBeen != null && aboutsBeen.size() > 0) {
            mDatas.add(new TitleBean());
            mDatas.addAll(aboutsBeen);
        }
        if (commentBean != null&&commentBean.getCode() != 404) {
            List<CommentBean.ResultBean.ItemsBean> itemsBeen = commentBean.getResult().getItems();

            if (itemsBeen != null && itemsBeen.size() > 0) {
                mDatas.add(new CommentHead());
                mDatas.addAll(itemsBeen);
            }
        }

        ThreadUtils.runMain(new Runnable() {
            @Override
            public void run() {
                initData();
                setCommentCount(commentCount+"");
                mFinalRecycleAdapter.notifyDataSetChanged();
            }
        });
        return mDatas;
    }


    @Override
    public void onBindViewHolder(FinalRecycleAdapter.ViewHolder holder, int position, Object itemData) {

        if (itemData instanceof TitleBean) {
            TextView title = (TextView) holder.getViewById(R.id.tv_informationdetailtitle);
            title.setText("相关内容");
        }
        if (itemData instanceof InfomationDetailBean.ResultBean.AboutsBean) {
            InfomationDetailBean.ResultBean.AboutsBean bean = (InfomationDetailBean.ResultBean.AboutsBean) itemData;

            RelativeLayout relativeLayoutInforecommend = (RelativeLayout) holder.getViewById(R.id.relativeLayout_inforecommend);
            TextView tvTitelInforecommend = (TextView) holder.getViewById(R.id.tv_titel_inforecommend);
            ImageView ivInforecommend = (ImageView) holder.getViewById(R.id.iv_inforecommend);
            TextView tvCountInforecommend = (TextView) holder.getViewById(R.id.tv_count_inforecommend);
            tvTitelInforecommend.setText(bean.getTitle());
            tvCountInforecommend.setText(bean.getCommentCount() + "");
        }
        if (itemData instanceof CommentHead) {
            mPosition1 = position;
            TextView title = (TextView) holder.getViewById(R.id.tv_commenthead);
            title.setText("评论");
        }
        if (itemData instanceof CommentBean.ResultBean.ItemsBean) {
            CommentBean.ResultBean.ItemsBean bean = (CommentBean.ResultBean.ItemsBean) itemData;
            final ImageView ivComment = (ImageView) holder.getViewById(R.id.iv_comment);
            TextView tvNameComment = (TextView) holder.getViewById(R.id.tv_name_comment);
            TextView tvTimeComment = (TextView) holder.getViewById(R.id.tv_time_comment);
            TextView tvCommentContent = (TextView) holder.getViewById(R.id.tv_comment_content);
            Glide.with(getContext()).load(bean.getAuthorPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivComment) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    ivComment.setImageDrawable(circularBitmapDrawable);
                }

            });
            tvNameComment.setText(bean.getAuthor());
            tvTimeComment.setText(StringUtils.friendly_time(bean.getPubDate()));
            tvCommentContent.setText(bean.getContent());

        }

    }

    @Override
    public void onClick() {
        int height = 0;

        mHeight1 = mLlWeb.getHeight();

        for (int i = 0; i < mPosition1; i++) {
            height += mRecyclerViewInformationDetail.getChildAt(i).getHeight();

        }
        int scrollHeight = height + mHeight1;
        mScrollViewDetail.smoothScrollTo(0, scrollHeight);
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            String content = mEtBottomComment.getText().toString().trim();
            if (TextUtils.isEmpty(content)) {
                ToastUtils.showToast("内容不能为空");
            } else {
                String cookie = SpUtil.getString(getContext(), Constant.COOKIE, "");
                if (TextUtils.isEmpty(cookie)) {
                    ShowActivity.startFragmentWithTitle(LoginFragment.class, null, "登录");
                } else {
                    HttpServiceApi httpServiceApi = new Retrofit.Builder().baseUrl(Urls.BASE_URL).build().create(HttpServiceApi.class);
                    httpServiceApi.comment(cookie, mId + "", mType, content).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            ToastUtils.showToast("发送成功");
                            mEtBottomComment.setText("");
                            mLoadingPager.showViewDely(1000);
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }

            }
            return true;
        }
        return true;
    }
}

