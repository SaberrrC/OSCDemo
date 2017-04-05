package com.saberrr.openchina.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.SelectedImageBean;
import com.saberrr.openchina.faces.DisplayRules;
import com.saberrr.openchina.faces.FaceBean;
import com.saberrr.openchina.gloab.AppApplication;
import com.saberrr.openchina.presenter.JumpPresenter;
import com.saberrr.openchina.presenter.JumpPresenterImpl;
import com.saberrr.openchina.ui.activity.ShowActivity;
import com.saberrr.openchina.ui.adapter.FinalBaseAdapter;
import com.saberrr.openchina.ui.adapter.interfaces.FacesPagerAdapter;
import com.saberrr.openchina.ui.view.FlowLayout;
import com.saberrr.openchina.utils.DensityUtil;
import com.saberrr.openchina.utils.ToastUtils;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Saberrr on 2017-04-02.
 */

public class JumpFragment extends BaseFragment implements JumpView {
    @BindView(R.id.tv_content)
    EditText       mEtContent;
    @BindView(R.id.vp_faces)
    ViewPager      mVpFaces;
    @BindView(R.id.ll_faces)
    LinearLayout   mLlFaces;
    @BindView(R.id.gv_img)
    FlowLayout     mGvImg;
    private JumpPresenter mJumpPresenter;
    public static final int            REQUEST_CODE = 100;
    private             String         TAG          = "JumpFragment";
    private             List<FaceBean> mDatas       = new ArrayList<>();
    private FacesPagerAdapter mFacesPagerAdapter;
    private List<SelectedImageBean> images = new ArrayList<>();
    private FinalBaseAdapter mImageAdapter;
//    private int dp_5 =

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = creatViewFromId(R.layout.fragment_jumponejump);
        ButterKnife.bind(this, view);
        initView();
        setHintKeyboard(true);

        mJumpPresenter = new JumpPresenterImpl(this);
        initToolbar();
        return view;
    }

    private void initView() {
        List<FaceBean> allByType0 = DisplayRules.getAllByType(0);
        List<FaceBean> allByType1 = DisplayRules.getAllByType(1);
        mFacesPagerAdapter = new FacesPagerAdapter(allByType0, allByType1);
        mVpFaces.setAdapter(mFacesPagerAdapter);
        mEtContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLlFaces.setVisibility(View.GONE);
            }
        });
        mFacesPagerAdapter.setOnClickListener(new FacesPagerAdapter.OnClickListener() {
            @Override
            public void onClick(FaceBean faceBean) {
                int index = mEtContent.getSelectionStart();
                Editable editable = mEtContent.getText();
                //设置图片
                Drawable drawable = getResources().getDrawable(faceBean.resId);
                drawable.setBounds(0, 0, DensityUtil.dip2px(25), DensityUtil.dip2px(25));
                Spannable msp = new SpannableString(faceBean.emojiStr);
                msp.setSpan(new ImageSpan(drawable), 0, faceBean.emojiStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                editable.insert(index, msp);
                ToastUtils.showToast(faceBean.toString());
            }
        });
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
                mVpFaces.setCurrentItem(0);
                break;
            case R.id.tv_emoji:
                mVpFaces.setCurrentItem(1);
                break;
            case R.id.iv_del:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 图片选择结果回调
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            for (String path : pathList) {
                ImageView imageView = new ImageView(getContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                /*imageView.setPadding(, 5, 5, 5);
                imageView.setBackgroundColor(Color.RED);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(200,200));
                imageView.setImageBitmap(bmp);

                GridLayout.Spec rowSpec = GridLayout.spec(i / 3);
                GridLayout.Spec columnSpec = GridLayout.spec(i % 3);
                GridLayout.LayoutParams paramsGl = new GridLayout.LayoutParams(rowSpec,columnSpec);

                contentImageGrid.addView(imageView, paramsGl);*/
            }
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
