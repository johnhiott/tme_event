package tme.transactthis.com.couponquest.model;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

import tme.transactthis.com.couponquest.model.vo.Coupon;
import tme.transactthis.com.couponquest.model.vo.UserInfo;

/**
 * Created by trey on 7/12/14.
 * Copyright 2014 MindMine LLC.
 */
public class QuestManager {

    private static QuestManager mInstance;
    public static QuestManager getInstance(){
        if(mInstance == null){
            mInstance = new QuestManager();
        }

        return  mInstance;
    }

    public void getStoreForBeaconId(String id, FindCallback<ParseObject> callback){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Store");
        query.whereEqualTo("beaconId", id);
        query.findInBackground(callback);
    }

    public void saveCoupon(final Coupon coupon, final SaveCallback callback){
        final UserInfo info = (UserInfo)ParseUser.getCurrentUser().get("userInfo");

        if(info == null){
            saveCoupon(new UserInfo(), coupon, callback);
        } else {
            info.fetchInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject parseObject, ParseException e) {
                    saveCoupon(info, coupon, callback);
                }
            });
        }

    }



    private void saveCoupon(UserInfo info, ICoupon coupon, SaveCallback callback){
        List coupons = info.getCouponList();
        coupons.add(coupon.getParseObject());
        info.setCouponList(coupons);
        info.saveInBackground();
        ParseUser.getCurrentUser().put("userInfo", info);
        ParseUser.getCurrentUser().saveInBackground(callback);
    }

    public void addCouponToUser(Coupon coupon, SaveCallback callback){

        UserInfo info = (UserInfo)ParseUser.getCurrentUser().get("userInfo");
        if(info == null){
            info = new UserInfo();
        }
        List coupons = info.getCouponList();
        coupons.add(coupon.getParseObject());
        info.setCouponList(coupons);
        info.saveInBackground();
        ParseUser.getCurrentUser().put("userInfo", info);
        ParseUser.getCurrentUser().saveInBackground(callback);

    }


}
