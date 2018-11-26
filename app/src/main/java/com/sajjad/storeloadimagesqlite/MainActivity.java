package com.sajjad.storeloadimagesqlite;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.sajjad.storeloadimagesqlite.RecyclerPackage.UserModel;
import com.sajjad.storeloadimagesqlite.RecyclerPackage.UserRecyclerAdapter;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_GALLERY = 1010;
    EditText userName, address;
    ImageView userImage;
    byte[] imageBytes;
    Toolbar supportToolbar;
    RecyclerView userRecycler;
    UserRecyclerAdapter userRecyclerAdapter;
    DBHelper dbHelper;
    List<UserModel> userModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        supportToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(supportToolbar);
        //
        userImage = findViewById(R.id.person_image);
        userName = findViewById(R.id.user_name);
        address = findViewById(R.id.address);
        //

        userRecycler = findViewById(R.id.recycler_view);
        userRecycler.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DBHelper(this);

    }

    public void AddData(View view) {
        imageBytes = getImageBytes();
        dbHelper.InserData(userName.getText().toString(), address.getText().toString(), imageBytes);
    }

    @NonNull
    private byte[] getImageBytes() {
        Bitmap bitmap = ((BitmapDrawable) userImage.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    public void LoadData(View view) {
        userModelList = dbHelper.GetAll();
        userRecyclerAdapter = new UserRecyclerAdapter(this, userModelList);
        userRecycler.setAdapter(userRecyclerAdapter);
    }

    public void LoadImage(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK) {
            if (data != null) {
                userImage.setImageURI(data.getData());
            }
        }
    }
}