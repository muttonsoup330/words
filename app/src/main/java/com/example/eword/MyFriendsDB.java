package com.example.eword;

import android.net.Uri;

public class MyFriendsDB {
    public static final String AUTHORITY="com.example.eword";
    public static final String DATABASE_NAME="mydb";
    public static final int DATABASE_VERSION=1;
    public static final String TABLE_NAME="words";
    public static final Uri CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/words");
    public static final int WORDS=1;
    public static final int WORDS_ID=2;
    public static final String CONTENT_TYPE="vnd.android.cursor.dir/mydb.words.all";
    public static final String CONTENT_ITEM_TYPE="vnd.android.cursor.dir/mydb.words.item";
    public static final String ID="_id";
    public static final String WORD="word";
    public static final String MEAN="mean";
    public static final String EGG="egg";
}