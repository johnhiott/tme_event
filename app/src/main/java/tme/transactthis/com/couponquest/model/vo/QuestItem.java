package tme.transactthis.com.couponquest.model.vo;

import com.parse.ParseClassName;
import com.parse.ParseObject;


/**
 * Created by trey on 7/12/14.
 * Copyright 2014 MindMine LLC.
 */
@ParseClassName("QuestItem")
public class QuestItem extends ParseObject {

    public static final String KEY_COUPON = "coupon";
    public static final String KEY_ISUNLOCKED = "isUnlocked";

    public Coupon getCoupon(){
        return (Coupon)getParseObject(KEY_COUPON);
    }

    public void setCoupon(Coupon coupon){
        put(KEY_COUPON, coupon);
    }

    public Boolean isUnlocked(){
        return getBoolean(KEY_ISUNLOCKED);
    }

    public void setIsUnlocked(Boolean isUnlocked){
        put(KEY_ISUNLOCKED,  isUnlocked);
    }

}
