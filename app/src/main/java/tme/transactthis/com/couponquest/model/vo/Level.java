package tme.transactthis.com.couponquest.model.vo;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by trey on 7/12/14.
 * Copyright 2014 MindMine LLC.
 */
@ParseClassName("Level")
public class Level extends ParseObject{

    private static final String KEY_STORE = "store";
    private static final String KEY_LEVEL = "level";

    public void setLevelValue(int value){
        put(KEY_LEVEL, value);
    }

    public int getLevelValue(){
        return getInt(KEY_LEVEL);
    }

    public void setStore(Store store){
        put(KEY_STORE, store);
    }

    public Store getStore(){
        return (Store)getParseObject(KEY_STORE);
    }

}
