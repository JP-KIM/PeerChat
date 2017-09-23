package com.jpstudio.peerchat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

/**
 * Created by jungpyokim on 2017. 9. 22..
 */
public class ChatListAdapter extends RealmBaseAdapter<ChatItem> {

    private static class ViewHolder {
        ImageView profileImageView;
        TextView titleTextView;
        TextView lastchatTextView;
    }

    private ArrayList<ChatItem> chatItemList = new ArrayList<ChatItem>() ;

    public ChatListAdapter(Context context, OrderedRealmCollection<ChatItem> realmResults) {
        super(context, realmResults);
    }

    @Override
    public int getCount() {
        return chatItemList.size() ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_chatlist, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.profileImageView = (ImageView) convertView.findViewById(R.id.imageView_profile);
            viewHolder.titleTextView = (TextView) convertView.findViewById(R.id.textView_title);
            viewHolder.lastchatTextView = (TextView) convertView.findViewById(R.id.textView_lastchat);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (chatItemList != null) {
            ChatItem listViewItem = chatItemList.get(position);

            if (listViewItem.getProfile() != null) {
                //viewHolder.profileImageView.setImageDrawable(listViewItem.getIcon());
            }
            viewHolder.titleTextView.setText(listViewItem.getTitle());
            viewHolder.lastchatTextView.setText(listViewItem.getLastchat());
        }
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ChatItem getItem(int position) {
        return chatItemList.get(position);
    }

    public void addItem(String profile, String title, String desc) {
        ChatItem item = new ChatItem();

        item.setProfile(profile);
        item.setTitle(title);
        item.setLastchat(desc);

        chatItemList.add(item);
    }
}
