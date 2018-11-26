package com.sajjad.storeloadimagesqlite.RecyclerPackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.sajjad.storeloadimagesqlite.R;

import java.util.List;

public class UserRecyclerAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<UserModel> userModelList;

    public UserRecyclerAdapter(Context context, List<UserModel> userModelList) {
        this.context = context;
        this.userModelList = userModelList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, viewGroup, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        UserHolder userHolder = (UserHolder) viewHolder;

        userHolder.userView.setText(userModelList.get(i).getUserName());
        userHolder.addressView.setText(userModelList.get(i).getAddress());
        new LoadImageAsync(userHolder.progressBar,userHolder.userImage).execute(userModelList.get(i).getUserImage());
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public class  LoadImageAsync extends AsyncTask<byte[], String, Bitmap> {

        ImageView UserImage;
        ProgressBar progressBar;

        LoadImageAsync(ProgressBar progressBar, ImageView userImage) {
            UserImage = userImage;
            this.progressBar=progressBar;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setIndeterminate(true);
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(byte[]... bytes) {
            Bitmap bitmap;
            bitmap = BitmapFactory.decodeByteArray(bytes[0], 0, bytes[0].length);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            UserImage.setImageBitmap(bitmap);
            progressBar.setIndeterminate(false);
            super.onPostExecute(bitmap);
        }
    }
}