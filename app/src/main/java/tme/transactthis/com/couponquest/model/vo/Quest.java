package tme.transactthis.com.couponquest.model.vo;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by trey on 7/12/14.
 * Copyright 2014 MindMine LLC.
 */
@ParseClassName("Quest")
public class Quest extends ParseObject {

    private static final String KEY_USER = "user";
    private static final String KEY_STORE = "store";
    private static final String KEY_QUEST_ITEMS = "questItems";


    public void setUser(UserInfo userInfo){
        put(KEY_USER, userInfo);
    }

    public UserInfo getUser(){
        return (UserInfo)getParseObject(KEY_USER);
    }

    public void setStore(Store store){
        put(KEY_STORE, store);
    }

    public Store getStore(){
        return (Store)getParseObject(KEY_STORE);
    }

    public List<QuestItem> getQuestItems(){
        return getList(KEY_QUEST_ITEMS);
    }

    public void setQuestItems(List<QuestItem> items){
        put(KEY_QUEST_ITEMS, items);
    }



}
