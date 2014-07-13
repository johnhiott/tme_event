package tme.transactthis.com.couponquest.model.inmar.vo;

import java.io.Serializable;
import com.parse.ParseObject;
import java.util.Date;

import tme.transactthis.com.couponquest.model.ICoupon;

public class Coupon implements ICoupon, Serializable{

    public int mdid;
    public String badge;
    public Category category;
    public String brand;
    public String description;
    public String shortDescription;
    public String imageUrl;
    public int value;
    public String terms;
    public String minPurchase;
    public InmarDate expirationDate;

    public int getMdid() {
        return mdid;
    }

    public String getBadge() {
        return badge;
    }

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getValue() {
        return value;
    }

    public String getTerms() {
        return terms;
    }

    public String getMinPurchase() {
        return minPurchase;
    }

    @Override
    public String getExpirationDate() {
        return expirationDate.iso;
    }

    @Override
    public String getCategoryId() {
        if (category != null) {
            return category.id;
        }
        return null;
    }

    @Override
    public String getCategoryName() {
        if (category != null) {
            return category.name;
        }
        return null;
    }

    @Override
    public int getCategoryActive() {
        if (category != null) {
            return category.active;
        }
        return 0;
    }

    @Override
    public int getCategoryValue() {
        if (category != null) {
            return category.value;
        }
        return 0;
    }

    @Override
    public String getCategoryGrocery() {
        if (category != null) {
            return category.grocery;
        }
        return null;
    }

    //epic hack
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
    public ParseObject getParseObject() {

        tme.transactthis.com.couponquest.model.vo.Coupon coupon = new tme.transactthis.com.couponquest.model.vo.Coupon();
        //ParseObject coupon = ParseObject.create("Coupon");
        coupon.put(KEY_MDID, mdid);
        coupon.put(KEY_BADGE, badge);
        coupon.put(KEY_BRAND, brand);
        coupon.put(KEY_DESCRIPTION, description);
        coupon.put(KEY_SHORT_DESCRIPTION, shortDescription);
        coupon.put(KEY_IMAGE_URL, imageUrl);
        coupon.put(KEY_VALUE, value);
        coupon.put(KEY_TERMS, terms);
        coupon.put(KEY_MIN_PURCHASE, minPurchase);
        coupon.put(KEY_EXPIRATION_DATE, expirationDate.iso);
        coupon.put(KEY_CAT_ID, getCategoryId());
        coupon.put(KEY_CAT_NAME, getCategoryName());
        coupon.put(KEY_CAT_ACTIVE, getCategoryActive());
        coupon.put(KEY_CAT_VALUE, getCategoryValue());
        //coupon.put(KEY_CAT_GROCERY, getCategoryGrocery());
        return coupon;
    }


}
