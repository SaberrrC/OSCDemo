package com.saberrr.openchina.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.saberrr.openchina.R;
import com.saberrr.openchina.gloab.AppApplication;
import com.saberrr.openchina.presenter.JumpPresenter;
import com.saberrr.openchina.presenter.JumpPresenterImpl;
import com.saberrr.openchina.ui.activity.ShowActivity;
import com.saberrr.openchina.utils.ToastUtils;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Saberrr on 2017-04-02.
 */

public class JumpFragment extends BaseFragment implements JumpView {
    private JumpPresenter mJumpPresenter;
    public static final int REQUEST_CODE = 100;
    private String TAG = "JumpFragment";

    @Override
    protected boolean needRefresh() {
        return false;
    }
    @Override
    public View createView() {
        View view = creatViewFromId(R.layout.fragment_jumponejump);
        ButterKnife.bind(this, view);
        mJumpPresenter = new JumpPresenterImpl(this);
        initToolbar();
        return view;
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
