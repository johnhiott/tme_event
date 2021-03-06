package tme.transactthis.com.couponquest.model.vo;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trey on 7/12/14.
 * Copyright 2014 MindMine LLC.
 */
@ParseClassName("UserInfo")
public class UserInfo extends ParseObject {

    private static final String KEY_LEVEL_LIST = "levelList";
    private static final String KEY_COUPON_LIST ="couponList";
    private static final String KEY_QUEST_LIST ="questList";

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
        List<Coupon> coupons = getList(KEY_COUPON_LIST);
        if (coupons == null) {
            coupons = new ArrayList<Coupon>();
        }
        return coupons;
    }

    public void setQuestList(List<Quest> questList){
        put(KEY_QUEST_LIST, questList);
    }

    public List<Quest> getQuestList(){
        return getList(KEY_QUEST_LIST);
    }

}
