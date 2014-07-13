package tme.transactthis.com.couponquest.model.vo;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

import tme.transactthis.com.couponquest.model.ICoupon;
import tme.transactthis.com.couponquest.model.inmar.vo.InmarDate;

/**
 * Created by trey on 7/12/14.
 * Copyright 2014 MindMine LLC.
 */
@ParseClassName("Coupon")
public class Coupon extends ParseObject implements ICoupon {

    private static final String KEY_MDID = "mdid";
    private static final String KEY_BADGE = "badge";
    private static final String KEY_BRAND = "brand";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_SHORT_DESCRIPTION = "shortDescription";
    private static final String KEY_IMAGE_URL = "imageUrl";
    private static final String KEY_VALUE = "value";
    private static final String KEY_TERMS = "terms";
    private static final String KEY_MIN_PURCHASE = "minPurchase";
    private static final String KEY_EXPIRATION_DATE = "expirationDate";
    private static final String KEY_CAT_ID = "categoryId";
    private static final String KEY_CAT_NAME = "categoryName";
    private static final String KEY_CAT_ACTIVE = "categoryActive";
    private static final String KEY_CAT_VALUE = "categoryValue";
    private static final String KEY_CAT_GROCERY = "categoryGrocery";

    @Override
    public int getMdid() {
        return getInt(KEY_MDID);
    }

    @Override
    public String getBadge() {
        return getString(KEY_BADGE);
    }

    @Override
    public String getBrand() {
        return getString(KEY_BRAND);
    }

    @Override
    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    @Override
    public String getShortDescription() {
        return getString(KEY_SHORT_DESCRIPTION);
    }

    @Override
    public String getImageUrl() {
        return getString(KEY_IMAGE_URL);
    }

    @Override
    public int getValue() {
        return getInt(KEY_VALUE);
    }

    @Override
    public String getTerms() {
        return getString(KEY_TERMS);
    }

    @Override
    public String getMinPurchase() {
        return getString(KEY_MIN_PURCHASE);
    }

    @Override
    public String getExpirationDate() {
        return getString(KEY_EXPIRATION_DATE);
    }

    @Override
    public String getCategoryId() {
        return getString(KEY_CAT_ID);
    }

    @Override
    public String getCategoryName() {
        return getString(KEY_CAT_NAME);
    }

    @Override
    public int getCategoryActive() {
        return getInt(KEY_CAT_ACTIVE);
    }

    @Override
    public int getCategoryValue() {
        return getInt(KEY_CAT_VALUE);
    }

    @Override
    public String getCategoryGrocery() {
        return getString(KEY_CAT_GROCERY);
    }

    @Override
    public ParseObject getParseObject() {
        return this;
    }

    @Override
    public Boolean isLocked() {
        return false;
    }

    @Override
    public void setLocked(Boolean locked) {

    }
}
