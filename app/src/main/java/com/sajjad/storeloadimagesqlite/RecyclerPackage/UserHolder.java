package com.sajjad.storeloadimagesqlite.RecyclerPackage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sajjad.storeloadimagesqlite.R;

class UserHolder extends RecyclerView.ViewHolder {

    TextView userView,addressView;
    ImageView userImage;
    ProgressBar progressBar;

    UserHolder(View view) {
        super(view);

        userView=view.findViewById(R.id.user_name_text);
        addressView= view.findViewById(R.id.address_text);
        userImage= view.findViewById(R.id.person_image_view);
        progressBar=view.findViewById(R.id.progress_bar);
    }
}
