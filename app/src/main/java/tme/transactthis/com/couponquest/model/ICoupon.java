package tme.transactthis.com.couponquest.model;

import com.parse.ParseObject;

/**
 * Created by trey on 7/12/14.
 * Copyright 2014 MindMine LLC.
 */
public interface ICoupon {

    public int getMdid();
    public String getBadge();
    public String getExpirationDate();
    public String getBrand();
    public String getDescription();
    public String getShortDescription();
    public String getImageUrl();
    public int getValue();
    public String getTerms();
    public String getMinPurchase();
    public String getCategoryId();
    public String getCategoryName();
    public int getCategoryActive();
    public int getCategoryValue();
    public String getCategoryGrocery();
    public ParseObject getParseObject();

}
