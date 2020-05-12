package com.example.shopgroc.utility;

public class Constant {
    public static class DataType{
        public static final String BUNDLE = "bundle";
        public static final String PRODUCT = "product";
    }

    public static class SharedPrefKey{
        public static final String PREF_NAME = "shopGroc_api";
        public static final String USER_ID="userId";
        public static final String USER_NAME="name";
        public static final String USER_EMAIL="email";
        public static final String USER_PHONE="phone";
        public static final String USER_ADDRESS="address";
    }
    public static class Messege{
        public static final String EMPTY_EMAIL_ERROR = "Email must not be empty!";
        public static final String EMPTY_PASSWORD_ERROR = "Password must not be empty!";
    }
}
