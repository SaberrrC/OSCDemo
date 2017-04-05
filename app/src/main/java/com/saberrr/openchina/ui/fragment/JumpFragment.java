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
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.saberrr.openchina.R;
import com.saberrr.openchina.faces.DisplayRules;
import com.saberrr.openchina.faces.FaceBean;
import com.saberrr.openchina.gloab.AppApplication;
import com.saberrr.openchina.ui.activity.ShowActivity;
import com.saberrr.openchina.ui.adapter.interfaces.FacesPagerAdapter;
import com.saberrr.openchina.ui.view.FlowLayout;
import com.saberrr.openchina.utils.DensityUtil;
import com.saberrr.openchina.utils.ThreadUtils;
import com.saberrr.openchina.utils.ToastUtils;
import com.saberrr.openchina.utils.Utils;
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

public class JumpFragment extends BaseFragment {
    public static final String TOPIC_TEXT = "#输入软件名#";
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
    public static final int REQUEST_CODE = 100;
    private FacesPagerAdapter mFacesPagerAdapter;
    private              List<String> images         = new ArrayList<>();
    private static final int          MAX_TEXT_COUNT = 140;
    private              String       TAG            = "JumpFragment";

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
    }

    @Override
    public Object getData() {
        return "";
    }

    private void initToolbar() {
        setToolbarIconOnClickListener(new ShowActivity.OnClickListener() {
            @Override
            public void onClick() {
                ThreadUtils.runSub(new Runnable() {
                    @Override
                    public void run() {
                       /* String cookie = SpUtil.getString(getContext(), Constant.COOKIE, "");
                        Map<String, String> map = new HashMap<>();
                        map.put("cookie", cookie);
                        //RequestBody body = new FormBody.Builder()
                        //        .add("keep_login", "1")
                        //        .add("username", "18801931441")
                        //        .add("pwd", "110201.liuji")
                        //        .add("content",mEtContent.getText().toString())
                        //        .build();
                        File file = new File(images.get(0));
                        Headers head = new Headers();

                        RequestBody body2 = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM)//传送的类型
//                                                                .add("keep_login", "1")
//                                                                .add("username", "18801931441")
//                                                                .add("pwd", "110201.liuji")
//                                                                .add("content",mEtContent.getText().toString())
                                .addPart(head,body)
                                .addFormDataPart("resource", file.getName(), MultipartBody.create(MediaType.parse("application/octet-stream"), file))
                                .build();
                        //                        String json = JsonCacheManager.getInstance().getXML(Urls.SEND_JUMP, map, body);
                        //                        SendJumpBean dataBean = JsonCacheManager.getInstance().getDataBean(Urls.SEND_JUMP, map, body, SendJumpBean.class);
                        String json2 =  JsonCacheManager.getInstance().getXML(Urls.SEND_JUMP, map, body2);
                        Log.d(TAG, "run: ========================" + json2);
                        ToastUtils.showToast(json2);
*/
                        /*try {
                            //创建一个要上传的图片
                            File file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "p33.jpg");
                            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
                            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)//传送的类型
                                    .addFormDataPart("resource", file.getName(), MultipartBody.create(MediaType.parse("application/octet-stream"), file)).build();
                            Request request = new Request.Builder().addHeader("cookie", mCookie).post(body).url("http://www.oschina.net/action/apiv2/resource_image").build();

                            Response response = okHttpClient.newCall(request).execute();
                            System.out.println(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            String cookie = SpUtil.getString(getContext(), Constant.COOKIE, "");
                            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
                            RequestBody body = new FormBody.Builder().add("images", "FDB9880DDB8FB5EC").add("content", "今天非要让我送她回家,兄弟们怎么办?").build();
                            Request request = new Request.Builder().url("http://www.oschina.net/action/apiv2/tweet").addHeader("cookie", cookie).post(body).build();
                            Response response = okHttpClient.newCall(request).execute();
                            System.out.println(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/


                    }
                });


            }
        });
    }

    @OnClick({R.id.tv_count, R.id.iv_pic, R.id.iv_at, R.id.iv_topic, R.id.iv_face, R.id.tv_qq, R.id.tv_emoji, R.id.iv_del})
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
                mLlFaces.setVisibility(View.GONE);
                break;
            case R.id.iv_topic:
                setHintKeyboardexception();
                mLlFaces.setVisibility(View.GONE);
                int index = mEtContent.getSelectionStart();
                Editable editable = mEtContent.getText();
                editable.insert(index, TOPIC_TEXT);
                mEtContent.setSelection(index + 1 + TOPIC_TEXT.indexOf("#"), index + TOPIC_TEXT.lastIndexOf("#"));
                //                mEtContent.setSelection(index + 1, index + 6);
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
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 图片选择结果回调
        int screenWith = Utils.getScreenWith();
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            images.clear();
            images.addAll(pathList);
            mFlImg.removeAllViews();
            for (int i = 0; i < images.size(); i++) {
                final View view = LayoutInflater.from(AppApplication.appContext).inflate(R.layout.item_image_selected, null, false);
                ImageView imageView = (ImageView) view.findViewById(R.id.iv_img);
                ImageView iv_del = (ImageView) view.findViewById(R.id.iv_del);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mFlImg.removeView(view);
                    }
                });
                ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                layoutParams.width = screenWith / 3;
                layoutParams.height = screenWith / 3;
                view.setLayoutParams(layoutParams);
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
