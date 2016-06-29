package com.suixin.vz.turing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.suixin.vy.ui.R;
import com.suixin.vz.bean.ChatMessage;
import com.suixin.vz.bean.ChatMessage.Type;
import com.suixin.vz.utils.HttpUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentLoging extends Fragment {

    private ListView mMsgs;

    private ChatMessageAdapter mAdapter;

    private List<ChatMessage> mDatas;

    private EditText mInputMsg;

    private Button mSendMsg;
    
    private Activity activity;

    private View view;
    public void onAttach(android.app.Activity activity) {
        this.activity = activity;
        super.onAttach(activity);
    }

    
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            ChatMessage fromMessge = (ChatMessage) msg.obj;
            mDatas.add(fromMessge);
            mAdapter.notifyDataSetChanged();
            mMsgs.setSelection(mDatas.size() - 1);
        };

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_im,
                container, false);
        this.view = view;
     // 实例化发现中的控件
        initDatas();
        initListener();
        initLv_find(inflater, container);
        return view;
    }

    private void initLv_find(LayoutInflater inflater, ViewGroup container) {
        mMsgs = (ListView) view.findViewById(R.id.id_listview_msgs);
        mInputMsg = (EditText) view.findViewById(R.id.id_input_msg);
        mSendMsg = (Button) view.findViewById(R.id.id_send_msg);
    }

    private void initListener() {
        mSendMsg.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                final String toMsg = mInputMsg.getText().toString();
                if (TextUtils.isEmpty(toMsg)) {
                    Toast.makeText(activity, "输入不能为空",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                ChatMessage toMessage = new ChatMessage();
                toMessage.setDate(new Date());
                toMessage.setMsg(toMsg);
                toMessage.setType(Type.OUTCOMING);
                mDatas.add(toMessage);
                mAdapter.notifyDataSetChanged();
                mMsgs.setSelection(mDatas.size() - 1);

                mInputMsg.setText("");

                new Thread() {
                    public void run() {
                        ChatMessage fromMessage = HttpUtils.sendMessage(toMsg);
                        Message m = Message.obtain();
                        m.obj = fromMessage;
                        mHandler.sendMessage(m);
                    };
                }.start();

            }
        });
    }

    private void initDatas() {
        mDatas = new ArrayList<ChatMessage>();
        mDatas.add(new ChatMessage("您好", Type.INCOMING, new Date()));
        mAdapter = new ChatMessageAdapter(activity, mDatas);
        mMsgs.setAdapter(mAdapter);
    }

}
