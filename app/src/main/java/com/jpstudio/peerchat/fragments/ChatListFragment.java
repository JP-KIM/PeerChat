package com.jpstudio.peerchat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jpstudio.peerchat.ChatItem;
import com.jpstudio.peerchat.ChatListAdapter;
import com.jpstudio.peerchat.R;

import org.jivesoftware.smack.chat.Chat;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by jungpyokim on 2017. 9. 22..
 */
public class ChatListFragment extends Fragment {

    private ListView listview;
    private TextView textView_noList;
    private ChatListAdapter chatListAdapter;

    private Realm realm;

    public static ChatListFragment newInstance() {
        ChatListFragment fragment = new ChatListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ChatListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chatlist, container, false);

        listview = (ListView) rootView.findViewById(R.id.listView);
        textView_noList = (TextView) rootView.findViewById(R.id.textView_noList);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        Realm.init(getContext());
        realm = Realm.getDefaultInstance();

        RealmResults<ChatItem> chatitems = realm.where(ChatItem.class).findAll();
/*
        // HERE WILL GO CRASH :(
        chatListAdapter = new ChatListAdapter(getContext(), chatitems);

        if (chatListAdapter.getCount() == 0) {
            listview.setVisibility(View.GONE);
            textView_noList.setVisibility(View.VISIBLE);
            textView_noList.setText("NO CHAT LIST");
        } else {
            textView_noList.setVisibility(View.GONE);
            listview.setVisibility(View.VISIBLE);
            listview.setAdapter(chatListAdapter);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
        }
        */
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);


        /*switch (requestCode) {
            case Constants.RequestCode.REQUEST_CODE_NAME_INPUT: {
                if (resultCode == Activity.RESULT_OK) {
                    String text = data.getStringExtra(KeySets.KEY_NAME_INPUT);
                    if (!TextUtils.isEmpty(text)) {
                        textResult.setText(text);
                    }
                }
                break;
            }
        }*/

        Toast.makeText(getActivity(), "ChatListFragment.onActivityResult", Toast.LENGTH_LONG).show();
    }
}
