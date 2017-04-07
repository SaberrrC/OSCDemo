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
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.jumponejump.SendJumpFirstImgBean;
import com.saberrr.openchina.faces.DisplayRules;
import com.saberrr.openchina.faces.FaceBean;
import com.saberrr.openchina.gloab.AppApplication;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.activity.ShowActivity;
import com.saberrr.openchina.ui.adapter.interfaces.FacesPagerAdapter;
import com.saberrr.openchina.ui.view.FlowLayout;
import com.saberrr.openchina.utils.Constant;
import com.saberrr.openchina.utils.DensityUtil;
import com.saberrr.openchina.utils.GsonTools;
import com.saberrr.openchina.utils.SpUtil;
import com.saberrr.openchina.utils.StringUtils;
import com.saberrr.openchina.utils.ThreadUtils;
import com.saberrr.openchina.utils.ToastUtils;
import com.saberrr.openchina.utils.Utils;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;
import static com.saberrr.openchina.R.id.iv_del;

/**
 * Created by Saberrr on 2017-04-02.
 */

public class JumpFragment extends BaseFragment {
    public static final String TOPIC_TEXT = "#输入软件名#";
    public static final String RESOURCE   = "resource";
    public static final String COOKIE     = "cookie";
    public static final String TOKEN      = "token";
    public static final String CONTENT    = "content";
    public static final String IMAGES     = "images";
    @BindView(R.id.tv_content)
    EditText     mEtContent;
    @BindView(R.id.vp_faces)
    ViewPager    mVpFaces;
    @BindView(R.id.ll_faces)
    LinearLayout mLlFaces;
    @BindView(R.id.fl_img)
    FlowLayout   mFlImg;
    @BindView(R.id.tv_count)
    TextView     mTvCount;
    public static final int REQUEST_CODE    = 100;
    public static final int REQUEST_CODE_AT = 101;
    @BindView(R.id.iv_del)
    ImageView mIvDel;
    private FacesPagerAdapter mFacesPagerAdapter;
    private              List<String> images         = new ArrayList<>();
    private static final int          MAX_TEXT_COUNT = 140;
    private              String       mCookie        = "";
    private              String       token          = "";
    private              int          screenWith     = Utils.getScreenWith();
    private static final String       TAG            = "JumpFragment";

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
        mEtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > MAX_TEXT_COUNT) {
                    mTvCount.setText("-" + (s.length() - MAX_TEXT_COUNT) + "");
                    mTvCount.setVisibility(View.VISIBLE);
                } else {
                    mTvCount.setVisibility(View.INVISIBLE);
                    mTvCount.setEnabled(false);
                }
            }
        });
        //键盘事件
        mEtContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    //如果发送事件
                    sendContentImages();
                    return true;
                }

                return false;
            }
        });
    }


    private void initImages() {
        String imagePaths = SpUtil.getString(getContext(), Constant.JUMP_IMAGES, "");
        String[] paths = imagePaths.split("##%##");
        List<String> lst = new ArrayList<>();
        for (String path : paths) {
            if (StringUtils.isImgUrl(path)) {
                lst.add(path);
            }
        }
        addImage(lst);
    }

    @Override
    public Object getData() {
        return "";
    }

    @Override
    public void onStart() {
        super.onStart();
        //        EventBus.getDefault().register(this);
        initText();
        initImages();
    }

    private void initText() {
        String text = SpUtil.getString(getContext(), Constant.JUMP_TEXT, "");
        mEtContent.setText(text);
        mEtContent.setSelection(text.length());
    }

    private void initToolbar() {
        setToolbarIconOnClickListener(new ShowActivity.OnClickListener() {
            @Override
            public void onClick() {
                sendContentImages();
            }
        });
    }

    private void sendContentImages() {
        mCookie = SpUtil.getString(getContext(), Constant.COOKIE, "");
        if (TextUtils.isEmpty(mCookie)) {
            ShowActivity.startFragment(LoginFragment.class, null);
        }
        ThreadUtils.runSub(new Runnable() {
            @Override
            public void run() {
                /**
                 * POST /action/apiv2/tweet HTTP/1.1\\r\\n
                 *
                 * 文字  application/x-www-form-urlencoded; charset=UTF-8
                 */
                String content = mEtContent.getText().toString();
                String cookie = SpUtil.getString(getContext(), Constant.COOKIE, "");

                if (TextUtils.isEmpty(cookie)) {
                    ShowActivity.startFragmentWithTitle(LoginFragment.class, null, "登录");
                    return;
                }
                if (TextUtils.isEmpty(content)) {
                    ToastUtils.showToast("内容不能为空");
                    return;
                }
                if (images.size() == 0) { //纯文字
                    try {
                        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
                        RequestBody body = new FormBody.Builder().add(CONTENT, content).build();
                        Request request = new Request.Builder().url(Urls.SEND_JUMP_TEXT)//纯文字地址
                                .addHeader(COOKIE, mCookie).post(body).build();
                        Response response = okHttpClient.newCall(request).execute();
                        String string = response.body().string();
                        System.out.println(string);
                    } catch (IOException e) {
                        e.printStackTrace();
                        ToastUtils.showToast("发送失败");
                    }
                } else {//文字加图片
                    for (int i = 0; i < images.size(); i++) {
                        ToastUtils.showToast("正在发送第" + (i + 1) + "张");
                        if (i == 0) {
                            try {
                                //创建一个要上传的图片
                                File file = new File(images.get(i));
                                OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
                                RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)//传送的类型
                                        .addFormDataPart(RESOURCE, file.getName(), MultipartBody.create(MediaType.parse("application/octet-stream"), file)).build();
                                Request request = new Request.Builder().addHeader(COOKIE, mCookie).post(body).url(Urls.SEND_JUMP_IMAGE).build();
                                Response response = okHttpClient.newCall(request).execute();
                                String json = response.body().string();
                                System.out.println(json);
                                SendJumpFirstImgBean sendJumpFirstImgBean = GsonTools.changeGsonToBean(json, SendJumpFirstImgBean.class);
                                //拿到token
                                token = sendJumpFirstImgBean.getResult().getToken();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else { //第二张
                            try {
                                //创建一个要上传的图片
                                File file = new File(images.get(i));
                                OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
                                //
                                RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)//传送的类型
                                        .addFormDataPart(RESOURCE, file.getName(), MultipartBody.create(MediaType.parse("application/octet-stream"), file)).addFormDataPart(TOKEN, token).build();
                                Request request = new Request.Builder().addHeader(COOKIE, mCookie).post(body).url(Urls.SEND_JUMP_IMAGE).build();
                                Response response = okHttpClient.newCall(request).execute();
                                String json = response.body().string();
                                System.out.println(json);
                                SendJumpFirstImgBean sendJumpFirstImgBean = GsonTools.changeGsonToBean(json, SendJumpFirstImgBean.class);
                                //拿到token
                                token = sendJumpFirstImgBean.getResult().getToken();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    //发文字
                    try {
                        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
                        RequestBody body = new FormBody.Builder().add(IMAGES, token).add(CONTENT, content).build();
                        Request request = new Request.Builder().url("http://www.oschina.net/action/apiv2/tweet").addHeader(COOKIE, mCookie).post(body).build();
                        Response response = okHttpClient.newCall(request).execute();
                        String string = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                        ToastUtils.showToast("发送失败");
                    }
                    ToastUtils.showToast("发送成功");
                    mEtContent.getEditableText().clear();
                    mFlImg.removeAllViews();
                    images.clear();
                    getActivity().finish();
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        SpUtil.saveString(getContext(), Constant.JUMP_TEXT, mEtContent.getText().toString());
    }

    @OnClick({R.id.tv_count, R.id.iv_pic, R.id.iv_at, R.id.iv_topic, R.id.iv_face, R.id.tv_qq, R.id.tv_emoji, iv_del})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_count:
                Editable editable_count = mEtContent.getText();
                editable_count.clear();
                break;
            case R.id.iv_pic:
                mLlFaces.setVisibility(View.GONE);
                // 跳转到图片选择器
                ImgSelActivity.startActivity(this, config, REQUEST_CODE);
                break;
            case R.id.iv_at:
                ShowActivity.startFragmentWithTitle(AtFragment.class, null, "");
                mLlFaces.setVisibility(View.GONE);
                break;
            case R.id.iv_topic:
                setHintKeyboardexception();
                mLlFaces.setVisibility(View.GONE);
                int index = mEtContent.getSelectionStart();
                Editable editable = mEtContent.getText();
                editable.insert(index, TOPIC_TEXT);
                mEtContent.setSelection(index + 1 + TOPIC_TEXT.indexOf("#"), index + TOPIC_TEXT.lastIndexOf("#"));
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
                int keyCode = KeyEvent.KEYCODE_DEL;
                KeyEvent keyEventDown = new KeyEvent(KeyEvent.ACTION_DOWN, keyCode);
                KeyEvent keyEventUp = new KeyEvent(KeyEvent.ACTION_UP, keyCode);
                mEtContent.onKeyDown(keyCode, keyEventDown);
                mEtContent.onKeyUp(keyCode, keyEventUp);
                mIvDel.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mEtContent.getText().clear();
                        return false;
                    }
                });
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 图片选择结果回调
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            setImage(data);
        }
    }

    private void setImage(Intent data) {
        List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
        addImage(pathList);
        savePath();
    }

    private void savePath() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < images.size(); i++) {
            if (i == images.size() - 1) {
                sb.append(images.get(i));
            } else {
                sb.append(images.get(i) + "##%##");
            }
        }
        SpUtil.saveString(getContext(), Constant.JUMP_IMAGES, sb.toString());
    }

    private void addImage(List<String> pathList) {
        images.clear();
        images.addAll(pathList);
        mFlImg.removeAllViews();
        for (int i = 0; i < images.size(); i++) {
            final View view = LayoutInflater.from(AppApplication.appContext).inflate(R.layout.item_image_selected, null, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_img);
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.width = screenWith / 3;
            layoutParams.height = screenWith / 3;
            view.setLayoutParams(layoutParams);
            final String path = images.get(i);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFlImg.removeView(v);
                    for (int i1 = 0; i1 < images.size(); i1++) {

                        if (path == images.get(i1)) {
                            images.remove(i1--);
                        }
                    }
                    savePath();
                }
            });
            Utils.loadImage(images.get(i), imageView);
            mFlImg.addView(view);
        }
        if (images.size() < 9) {
            final View view = LayoutInflater.from(AppApplication.appContext).inflate(R.layout.item_image_selected, null, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_img);
            ImageView iv_del = (ImageView) view.findViewById(R.id.iv_del);
            iv_del.setVisibility(View.GONE);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImgSelActivity.startActivity(JumpFragment.this, config, REQUEST_CODE);
                }
            });
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.width = screenWith / 3;
            layoutParams.height = screenWith / 3;
            view.setLayoutParams(layoutParams);
            Utils.loadImage(R.mipmap.ic_tweet_add, imageView);
            mFlImg.addView(view);
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
            .rememberSelected(false)
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        //        EventBus.getDefault().unregister(this);
    }
}
