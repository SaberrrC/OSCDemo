package com.saberrr.openchina.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.saberrr.openchina.R;
import com.saberrr.openchina.bean.FriendInfoBean;
import com.saberrr.openchina.gloab.AppApplication;
import com.saberrr.openchina.manager.netmanager.JsonCacheManager;
import com.saberrr.openchina.net.Urls;
import com.saberrr.openchina.ui.adapter.FinalRecycleAdapter;
import com.saberrr.openchina.ui.view.ContactLayout;
import com.saberrr.openchina.utils.Constant;
import com.saberrr.openchina.utils.SpUtil;
import com.saberrr.openchina.utils.StringUtils;
import com.saberrr.openchina.utils.ThreadUtils;
import com.saberrr.openchina.utils.XmlUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Saberrr on 2017-04-06.
 */

public class AtFragment extends BaseFragment implements FinalRecycleAdapter.OnViewAttachListener {
    @BindView(R.id.et_at)
    EditText      mEtAt;
    @BindView(R.id.cl_at)
    ContactLayout mClAt;
    private List<Object> mInfos = new ArrayList<>();
    private FinalRecycleAdapter mFinalRecycleAdapter;
    private String                       TAG   = "AtFragment";
    private List<FriendInfoBean.Friends> names = new ArrayList<>();
    private TextView mRightTextView;

    @Override
    protected boolean needRefresh() {
        return false;
    }

    @Override
    public View createView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_at, null, false);
        ButterKnife.bind(this, view);
        mRightTextView = getRightTextView();
        mRightTextView.setVisibility(View.VISIBLE);
        mRightTextView.setText("确定");
        TextView leftTextView = getLeftTextView();
        leftTextView.setVisibility(View.VISIBLE);
        leftTextView.setText("选择@好友");

        mRightTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017-04-07
            }
        });
        Map<Class, Integer> map = FinalRecycleAdapter.getMap();
        map.put(FriendInfoBean.Friends.class, R.layout.list_item_contact);
        mFinalRecycleAdapter = new FinalRecycleAdapter(mInfos, map, this);
       /* List<String> names = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            names.add(i + "");
        }
        mFinalRecycleAdapter.setStringList(names);*/

        RecyclerView recyclerView = mClAt.getRecyclerView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setInitialPrefetchItemCount(15);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mFinalRecycleAdapter);
        return view;
    }

    @Override
    public Object getData() {
        String xml = JsonCacheManager.getInstance().getXML(Urls.ATFRIENDS + "uid=" + SpUtil.getString(getContext(), Constant.USERID, "") + "&relation=1&all=1");
        FriendInfoBean friendInfoBean = XmlUtils.toBean(FriendInfoBean.class, xml.getBytes());
        final List<FriendInfoBean.Friends> friends = friendInfoBean.getFriends();
        for (FriendInfoBean.Friends friend : friends) {
            friend.first = StringUtils.getFirst(getSpells(friend.getName()));
        }
        mInfos.addAll(friends);
        Collections.sort(mInfos, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                FriendInfoBean.Friends f1 = (FriendInfoBean.Friends) o1;
                FriendInfoBean.Friends f2 = (FriendInfoBean.Friends) o2;
                return f1.first.compareToIgnoreCase(f2.first);
            }
        });
        ThreadUtils.runMain(new Runnable() {
            @Override
            public void run() {
                List<String> names = new ArrayList<>();
                for (FriendInfoBean.Friends friend : friends) {
                    names.add(friend.getName());
                }
                mFinalRecycleAdapter.setStringList(names);
                mClAt.requestLayout();
                mFinalRecycleAdapter.notifyDataSetChanged();
            }
        });
        return friendInfoBean;
    }

    @Override
    public void onBindViewHolder(FinalRecycleAdapter.ViewHolder holder, int position, Object itemData) {
        final FriendInfoBean.Friends friend = (FriendInfoBean.Friends) itemData;
        TextView tvSection = (TextView) holder.getViewById(R.id.tv_section);
        String now = friend.first;
        if (position == 0) {
            tvSection.setVisibility(View.VISIBLE);
            tvSection.setText(now.toUpperCase());
        } else {
            FriendInfoBean.Friends lastFriend = (FriendInfoBean.Friends) mInfos.get(position - 1);
            String lastFirst = lastFriend.first;
            if (TextUtils.equals(now, lastFirst)) {
                tvSection.setVisibility(View.GONE);
            } else {
                tvSection.setVisibility(View.VISIBLE);
                tvSection.setText(now.toUpperCase());
            }
        }
        TextView tvUsername = (TextView) holder.getViewById(R.id.tv_username);
        final CheckBox cbCheck = (CheckBox) holder.getViewById(R.id.cb_check);
        ImageView ivIcon = (ImageView) holder.getViewById(R.id.iv_icon);
        tvUsername.setText(friend.getName());
        Glide.with(AppApplication.appContext).load(friend.getPortrait()).placeholder(R.mipmap.ic_noicon).into(ivIcon);

        View rootView = holder.getRootView();
        if (names.size() >= 14) {
            rootView.setOnClickListener(null);
            cbCheck.setEnabled(false);
        } else {
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cbCheck.setChecked(!friend.checked);
                    friend.checked = !friend.checked;
                    if (names.contains(friend)) {
                        names.remove(friend);
                    } else {
                        names.add(friend);
                    }
                    if (names.size() != 0) {
                        mRightTextView.setText("确定(" + names.size() + "/14)");
                    } else {
                        mRightTextView.setText("确定");
                    }

                }
            });
        }
    }

    static final int    GB_SP_DIFF      = 160;
    // 存放国标一级汉字不同读音的起始区位码
    static final int[]  secPosValueList = {1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027, 4086, 4390, 4558, 4684, 4925, 5249, 5600};
    // 存放国标一级汉字不同读音的起始区位码对应读音
    static final char[] firstLetter     = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'w', 'x', 'y', 'z'};

    public static String getSpells(String characters) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < characters.length(); i++) {

            char ch = characters.charAt(i);
            if ((ch >> 7) == 0) {
                // 判断是否为汉字，如果左移7为为0就不是汉字，否则是汉字
                return StringUtils.getFirst(characters);
            } else {
                char spell = getFirstLetter(ch);
                buffer.append(String.valueOf(spell));
            }
        }
        return StringUtils.getFirst(buffer.toString());
    }

    // 获取一个汉字的首字母
    public static Character getFirstLetter(char ch) {

        byte[] uniCode = null;
        try {
            uniCode = String.valueOf(ch).getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        if (uniCode[0] < 128 && uniCode[0] > 0) { // 非汉字
            return null;
        } else {
            return convert(uniCode);
        }
    }

    /**
     * 获取一个汉字的拼音首字母。 GB码两个字节分别减去160，转换成10进制码组合就可以得到区位码
     * 例如汉字“你”的GB码是0xC4/0xE3，分别减去0xA0（160）就是0x24/0x43
     * 0x24转成10进制就是36，0x43是67，那么它的区位码就是3667，在对照表中读音为‘n’
     */
    static char convert(byte[] bytes) {
        char result = '-';
        int secPosValue = 0;
        int i;
        for (i = 0; i < bytes.length; i++) {
            bytes[i] -= GB_SP_DIFF;
        }
        secPosValue = bytes[0] * 100 + bytes[1];
        for (i = 0; i < 23; i++) {
            if (secPosValue >= secPosValueList[i] && secPosValue < secPosValueList[i + 1]) {
                result = firstLetter[i];
                break;
            }
        }
        return result;
    }


}
