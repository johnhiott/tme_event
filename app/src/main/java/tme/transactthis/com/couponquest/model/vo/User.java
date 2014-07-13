package tme.transactthis.com.couponquest.model.vo;

import com.parse.ParseClassName;
import com.parse.ParseUser;

import java.util.List;

import tme.transactthis.com.couponquest.model.inmar.vo.Coupon;

/**
 * Created by trey on 7/12/14.
 * Copyright 2014 MindMine LLC.
 */
@ParseClassName("_User")
public class User extends ParseUser {

    private static final String KEY_LEVEL_LIST = "levelList";
    private static final String KEY_COUPON_LIST ="couponList";

    public void setLevelList(List<Level> levelList){
        put(KEY_LEVEL_LIST, levelList);
    }

    public List<Level> getLevelList(){
        return getList(KEY_LEVEL_LIST);
    }

    public void setCouponList(List<Coupon> couponList){
        put(KEY_COUPON_LIST, couponList);
    }

    public List<Coupon> getCouponList(){
        return getList(KEY_COUPON_LIST);
    }

}
