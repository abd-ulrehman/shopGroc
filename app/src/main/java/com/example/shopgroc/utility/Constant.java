package com.example.shopgroc.utility;

public class Constant {
    public static class DataType{
        public static final String BUNDLE = "bundle";
        public static final String PRODUCT = "product";
        public static final String CART_ITEM = "cartItem";
        public static final String ORDER = "order";
    }

    public static class SharedPrefKey{
        public static final String PREF_NAME = "shopGroc_api";
        public static final String USER_ID="userId";
        public static final String USER_NAME="name";
        public static final String USER_EMAIL="email";
        public static final String USER_PHONE="phone";
        public static final String USER_ADDRESS="address";
        public static final String USER_IMAGE="userImage";

        public static final String PRODUCT_TITLE="productTitle";
        public static final String PRODUCT_PRICE="productPrice";
        public static final String PRODUCT_CATEGORY="productCategory";
        public static final String PRODUCT_DESCRIPTION="productDescription";
        public static final String PRODUCT_IMAGE="productImage";
    }
    public static class Messege{
        public static final String EMPTY_EMAIL_ERROR = "Email must not be empty!";
        public static final String EMPTY_PASSWORD_ERROR = "Password must not be empty!";
        public static final String PASSWORD_LENGTH_ERROR = "Password should have at least 6 characters";
        public static final String EMPTY_NAME_ERROR = "Name must not be empty!";
        public static final String EMPTY_PHONE_ERROR = "Phone must not be empty!";
        public static final String PHONE_FORMAT_ERROR = "Incorrect Phone!";
        public static final String PHONE_INCOMPLETE_ERROR = "Incomplete Phone!";
        public static final String EMPTY_ADDRESS_ERROR = "Address must not be empty!";
    }

    public static class DatabaseTableKey{
        public static final String USER_TABLE= "User";
        public static final String PRODUCT_TABLE= "Product";
        public static final String ORDER_TABLE = "Orders";
    }

    public static class DatabaseKey{
        public static final String USER_NAME="name";
        public static final String USER_EMAIL="email";
        public static final String USER_PHONE="phone";
        public static final String USER_ADDRESS="address";
        public static final String USER_IMAGE="image";

        public static final String ORDER_PRODUCTS="products";
        public static final String ORDER_DELIVERY_CHARGE="deliveryCharges";
        public static final String ORDER_DELIVERY_STATUS="deliveryStatus";
        public static final String ORDER_TIME="orderTime";
        public static final String ORDER_ORDER="orders";
        public static final String ORDER_LOCATION = "location";
        public static final String ORDER_NUMBER = "orderNumber";
        public static final String ORDER_ID = "id";


        public static final String ORDERED_PRODUCT_ID="productId";
        public static final String ORDERED_PRODUCT_QUANTITY="productQuantity";
        public static final String ORDERED_PRODUCT_NAME="productName";
        public static final String ORDERED_PRODUCT_PRICE="productPrice";

    }

    public static class DeliveryStatus{
        public static final int ORDER_PENDING=0;
        public static final int ORDER_COMPLETE=1;
        public static final int ORDER_CANCEL=2;

        public static final String ORDER_PENDING_STATUS="Pending";
        public static final String ORDER_COMPLETE_STATUS="complete";
        public static final String ORDER_CANCEL_STATUS="cancel";
    }

    public static class DateFormats{
        public static final String DATE_AT_TIME="dd/MM/yyyy 'at' hh:mm aa";
    }
    public static class Date{
        public static final String DATE = "EEE, MMM d, yyyy";
    }
}
