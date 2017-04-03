package com.saberrr.openchina.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.saberrr.openchina.R;
import com.saberrr.openchina.gloab.AppApplication;
import com.saberrr.openchina.presenter.JumpPresenter;
import com.saberrr.openchina.presenter.JumpPresenterImpl;
import com.saberrr.openchina.ui.view.FlowLayout;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Saberrr on 2017-04-02.
 */

public class JumpFragment extends BaseFragment implements JumpView {

    @BindView(R.id.tv_content)
    TextView   mTvContent;
    @BindView(R.id.fl_images)
    FlowLayout mFlImages;
    @BindView(R.id.iv_pic)
    ImageView  mIvPic;
    @BindView(R.id.iv_at)
    ImageView  mIvAt;
    @BindView(R.id.iv_topic)
    ImageView  mIvTopic;
    @BindView(R.id.iv_face)
    ImageView  mIvFace;
    private JumpPresenter mJumpPresenter;
    private static final int REQUEST_CODE = 100;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = creatViewFromId(R.layout.fragment_jumponejump);
        ButterKnife.bind(this, view);
        mJumpPresenter = new JumpPresenterImpl(this);
        return view;
    }

    @Override
    public Object getData() {

        return "";
    }

    //获取数据后的回调
    @Override
    public void onInit(List list) {

    }


    @OnClick({R.id.tv_content, R.id.fl_images, R.id.iv_pic, R.id.iv_at, R.id.iv_topic, R.id.iv_face})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_content:
                break;
            case R.id.fl_images:
                break;
            case R.id.iv_pic:
                initPic();
                // 跳转到图片选择器
                ImgSelActivity.startActivity(this, config, REQUEST_CODE);
                break;
            case R.id.iv_at:
                break;
            case R.id.iv_topic:
                break;
            case R.id.iv_face:
                break;
        }
    }

    private void initPic() {


    }

    // 自定义图片加载器
    private ImageLoader  loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            // TODO 在这边可以自定义图片加载库来加载ImageView，例如Glide、Picasso、ImageLoader等
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
            .statusBarColor(Color.parseColor("#24cf5f"))
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
