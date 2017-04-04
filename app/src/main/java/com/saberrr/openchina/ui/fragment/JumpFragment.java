package com.saberrr.openchina.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.saberrr.openchina.R;
import com.saberrr.openchina.faces.DisplayRules;
import com.saberrr.openchina.faces.FaceBean;
import com.saberrr.openchina.gloab.AppApplication;
import com.saberrr.openchina.presenter.JumpPresenter;
import com.saberrr.openchina.presenter.JumpPresenterImpl;
import com.saberrr.openchina.ui.activity.ShowActivity;
import com.saberrr.openchina.ui.adapter.interfaces.FacesPagerAdapter;
import com.saberrr.openchina.ui.view.FlowLayout;
import com.saberrr.openchina.utils.ToastUtils;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Saberrr on 2017-04-02.
 */

public class JumpFragment extends BaseFragment implements JumpView {
    @BindView(R.id.tv_content)
    EditText     mEtContent;
    @BindView(R.id.fl_images)
    FlowLayout   mFlImages;
    @BindView(R.id.vp_faces)
    ViewPager    mVpFaces;
    @BindView(R.id.ll_faces)
    LinearLayout mLlFaces;
    private JumpPresenter mJumpPresenter;
    public static final int            REQUEST_CODE = 100;
    private             String         TAG          = "JumpFragment";
    private             List<FaceBean> mDatas       = new ArrayList<>();
    private FacesPagerAdapter mFacesPagerAdapter;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = creatViewFromId(R.layout.fragment_jumponejump);
        ButterKnife.bind(this, view);
        initView();
        mJumpPresenter = new JumpPresenterImpl(this);
        initToolbar();
        return view;
    }

    private void initView() {
        List<FaceBean> allByType = DisplayRules.getAllByType(0);
        mDatas.addAll(allByType);

        mFacesPagerAdapter = new FacesPagerAdapter(mDatas);
        mVpFaces.setAdapter(mFacesPagerAdapter);
    }

    @Override
    public Object getData() {

        return "";
    }

    private void initToolbar() {
        setToolbarIconOnClickListener(new ShowActivity.OnClickListener() {
            @Override
            public void onClick() {
                ToastUtils.showToast("6666");
            }
        });
        setOnQueryTextListener(new ShowActivity.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit() {
                ToastUtils.showToast("正在搜索中");
                return false;
            }

            @Override
            public boolean onQueryTextChange() {
                return false;
            }
        });
    }

    @OnClick({R.id.tv_count, R.id.iv_pic, R.id.iv_at, R.id.iv_topic, R.id.iv_face, R.id.tv_qq, R.id.tv_emoji, R.id.iv_del})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_count:
                break;
            case R.id.iv_pic:
                mLlFaces.setVisibility(View.GONE);
                // 跳转到图片选择器
                ImgSelActivity.startActivity(this, config, REQUEST_CODE);
                break;
            case R.id.iv_at:
                mLlFaces.setVisibility(View.GONE);
                break;
            case R.id.iv_topic:
                mLlFaces.setVisibility(View.GONE);
                break;
            case R.id.iv_face:
                mLlFaces.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_qq:
                break;
            case R.id.tv_emoji:
                break;
            case R.id.iv_del:
                break;
        }
    }

    // 自定义图片加载器
    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            Glide.with(AppApplication.appContext).load(path).into(imageView);
        }
    };

    // 自由配置选项
    private ImgSelConfig config = new ImgSelConfig.Builder(AppApplication.appContext, loader)
            // 是否多选, 默认true
            .multiSelect(true)
            // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
            .rememberSelected(true)
            // “确定”按钮背景色
            .btnBgColor(Color.TRANSPARENT)
            // “确定”按钮文字颜色
            .btnTextColor(Color.WHITE)
            // 使用沉浸式状态栏
            //            .statusBarColor(Color.parseColor("#24cf5f"))
            // 返回图标ResId
            .backResId(R.mipmap.btn_back_normal)
            // 标题
            .title("图片")
            // 标题文字颜色
            .titleColor(Color.WHITE)
            // TitleBar背景色
            .titleBgColor(Color.parseColor("#24cf5f"))
            // 裁剪大小。needCrop为true的时候配置
            .cropSize(1, 1, 200, 200).needCrop(true)
            // 第一个是否显示相机，默认true
            .needCamera(true)
            // 最大选择图片数量，默认9
            .maxNum(9).build();

}
