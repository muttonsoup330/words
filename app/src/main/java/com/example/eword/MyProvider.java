package com.example.eword;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyProvider extends ContentProvider {
    public static final int WORDS_DIR=0;
    public static final int WORDS_ITEM=1;
    public static final String AUTHORITY="com.example.mydbdemo.provider";
    public static UriMatcher uriMatcher;
    public dbHelper dbHelper;
    private static final String DB_NAME="mydb";
    static {
        uriMatcher=new UriMatcher((UriMatcher.NO_MATCH));
        uriMatcher.addURI(AUTHORITY,"words",WORDS_DIR);
        uriMatcher.addURI(AUTHORITY,"words/#",WORDS_ITEM);
    }
    public MyProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        int rows=0;
        switch (uriMatcher.match(uri)){
            case WORDS_DIR:
                rows=db.delete("words",selection,selectionArgs);
                break;
            case WORDS_ITEM:
                String wordsId=uri.getPathSegments().get(1);
                rows=db.delete("words","id=?",new String[]{wordsId});
                break;
            default:
                break;
        }
        return rows;
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch (uriMatcher.match(uri)){
            case WORDS_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.mydbdemo.provider.words";
            case WORDS_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.mydbdemo.provider.words";
        }
        return null;
        // throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Uri uriReturn=null;
        switch (uriMatcher.match(uri)){
            case WORDS_DIR:
            case WORDS_ITEM:
                long newWordsId=db.insert("words",null,values);
                uriReturn=Uri.parse("content://"+AUTHORITY+"/words/"+newWordsId);
                break;
            default:
                break;
        }
        return uriReturn;

        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        dbHelper=new dbHelper(this.getContext(),DB_NAME,null,2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=null;
        switch (uriMatcher.match(uri)){
            case WORDS_DIR:
                cursor=db.query("words",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case WORDS_ITEM:
                String  wordsId=uri.getPathSegments().get(1);
                cursor=db.query("words",projection,"id=?",new String[]{wordsId},null,null,sortOrder);
                break;
            default:
                break;
        }

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        int rows=0;
        switch (uriMatcher.match(uri)){
            case WORDS_DIR:
                rows=db.update("words",values,selection,selectionArgs);
                break;
            case WORDS_ITEM:
                String wordsId=uri.getPathSegments().get(1);
                rows=db.update("words",values,"id=?",new String[]{wordsId});
                break;
            default:
                break;
        }
        return rows;
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}