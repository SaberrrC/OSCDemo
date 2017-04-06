package com.saberrr.openchina.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.MoveNewBean;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MoveDetailActivity extends AppCompatActivity {
    private static final String TAG = "MoveDetailActivity";
    @BindView(R.id.tv_txt)
    TextView mTvTxt;
    @BindView(R.id.iv_good)
    ImageView mIvGood;
    @BindView(R.id.iv_commend)
    ImageView mIvCommend;
    @BindView(R.id.iv_icon)
    CircleImageView mIvIcon;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.t_zan)
    Button mTZan;
    @BindView(R.id.bt_common)
    Button mBtCommon;
    @BindView(R.id.lv_zan)
    ListView mLvZan;
    @BindView(R.id.lv_common)
    ListView mLvCommon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_detail);
        ButterKnife.bind(this);

        MoveNewBean.ResultBean.ItemsBean bean = (MoveNewBean.ResultBean.ItemsBean) getIntent().getSerializableExtra("bean");

        mTvName.setText(bean.getAuthor().getName());

        String content = bean.getContent();
        Spanned spanned = Html.fromHtml(content);
        SpannableString msg = new SpannableString(spanned);

        mTvTxt.setText(msg);

        Glide.with(this).load(bean.getAuthor().getPortrait()).asBitmap().into(mIvIcon);


        //时间
        String pubDate = bean.getPubDate();
        long parseTime = parseTime(pubDate);
        Log.i(TAG, "ShowView: parseTime = " + parseTime);
        long endTime = parseTime(getSystemTime());
        Log.i(TAG, "ShowView: endTime = " + endTime);
        long time = endTime - parseTime;
        int m = (int) (time / 1000 / 60);
        Log.i(TAG, "ShowView: m" + m);

        if (m < 3) {
            mTvDate.setText("刚刚");
        } else if (m < 60) {
            mTvDate.setText(m + "分钟前");
        } else if (m / 60 < 24) {
            long h = m / 60;
            mTvDate.setText(h + "小时前");
        } else if (m / 60 / 24 < 30) {
            int d = m / 60 / 24;
            mTvDate.setText(d + "天前");
        }
        mTZan.setText("赞（" + bean.getLikeCount() + "）");
        mBtCommon.setText("评论（" + bean.getCommentCount() + "）");

        final MoveNewBean.ResultBean.ItemsBean.AboutBean about = bean.getAbout();

    }

    public long parseTime(String date) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(date);
        String[] split = m.replaceAll(" ").trim().split(" ");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4]), Integer.parseInt(split[5]));

        return calendar.getTimeInMillis();
    }

    public String getSystemTime() {
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        return retStrFormatNowDate;
    }
}
