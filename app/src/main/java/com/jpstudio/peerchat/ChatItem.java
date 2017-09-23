package com.jpstudio.peerchat;

import android.graphics.drawable.Drawable;

import io.realm.RealmObject;

/**
 * Created by jungpyokim on 2017. 9. 22..
 */
public class ChatItem extends RealmObject {

    private String profile;
    private String title;
    private String lastchat;

    public void setProfile(String profile) {
        this.profile = profile;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setLastchat(String lastchat) {
        this.lastchat = lastchat;
    }

    public String getProfile() {
        return this.profile;
    }
    public String getTitle() {
        return this.title;
    }
    public String getLastchat() {
        return this.lastchat;
    }

}
