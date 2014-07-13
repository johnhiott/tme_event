package tme.transactthis.com.couponquest.model.vo;

import com.parse.ParseObject;

/**
 * Created by trey on 7/12/14.
 * Copyright 2014 MindMine LLC.
 */
public class Store extends ParseObject {

    private static final String KEY_STORENAME = "storeName";
    private static final String KEY_IMAGE_NAME = "imageName";
    private static final String KEY_BEACON_ID = "beaconId";


    public String getStoreName(){
        return getString(KEY_STORENAME);
    }

    public String getImageName(){
        return getString(KEY_IMAGE_NAME);
    }

    public String getBeaconId(){
        return getString(KEY_BEACON_ID);
    }
}
