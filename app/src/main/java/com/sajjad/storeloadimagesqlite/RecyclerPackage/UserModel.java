package com.sajjad.storeloadimagesqlite.RecyclerPackage;

public class UserModel {
    private String UserName;
    private String Address;
    private byte[] UserImage;

    public UserModel(String userName, String address,  byte[] userImage) {
        UserName = userName;
        Address = address;
        UserImage=userImage;
    }

    String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public byte[] getUserImage() {
        return UserImage;
    }

    public void setUserImage(byte[] userImage) {
        UserImage = userImage;
    }
}
