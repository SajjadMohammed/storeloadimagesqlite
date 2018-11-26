package com.sajjad.storeloadimagesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sajjad.storeloadimagesqlite.RecyclerPackage.UserModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "UserDataDB.db";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase sqLiteDatabase;
    private final Context context;

    DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        this.context = context;
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        copyDataBase();
    }

    private void copyDataBase() {
        this.getReadableDatabase();
        try {
            File file=new File(DB_PATH);
            if (!file.exists()) {
                copyDBFile();
                close();
            }
        } catch (IOException mIOException) {
            throw new Error("ErrorCopyingDataBase");
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = context.getAssets().open(DB_NAME);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = mInput.read(buffer)) > 0) {
            mOutput.write(buffer, 0, length);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    List<UserModel> GetAll() {
        sqLiteDatabase = getReadableDatabase();
        List<UserModel> eNumbersTableList = new ArrayList<>();
        Cursor c = sqLiteDatabase.query
                ("UserInfo", null, null, null, null, null, null);
        while (c.moveToNext()) {
            UserModel eNumbersTable = new UserModel(c.getString(1), c.getString(2), c.getBlob(3));
            eNumbersTableList.add(eNumbersTable);
        }
        c.close();
        sqLiteDatabase.close();
        return eNumbersTableList;
    }

    void InserData(String userName, String userAddress, byte[] userImage) {
        SQLiteDatabase dbb = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserName", userName);
        contentValues.put("UserAddress", userAddress);
        contentValues.put("UserImage", userImage);
        dbb.insert("UserInfo", null, contentValues);
    }

    @Override
    public synchronized void close() {
        if (sqLiteDatabase != null)
            sqLiteDatabase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}