package com.mgalal.payconiq.payconiqtest.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by fujitsu-lap on 25/08/2017.
 * Owner of repo
 */

public class Owner extends RealmObject {
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @PrimaryKey
    private long id;
    private String login;
    @SerializedName("avatar_url")
    private String avatar;
}
