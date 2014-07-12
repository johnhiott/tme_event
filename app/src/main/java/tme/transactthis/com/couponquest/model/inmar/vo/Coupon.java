package tme.transactthis.com.couponquest.model.inmar.vo;

import java.util.Date;

/**
 * Created by trey on 7/12/14.
 * Copyright 2014 MindMine LLC.
 */
public class Coupon {

    public int mdid;
    public String badge;
    public boolean clipped;
    public Date redeemedDate;
    public Category category;
    public String brand;
    public String description;
    public String shortDescription;
    public String imageUrl;
    public int value;
    public String terms;
    public String minPurchase;
    public String offerSortValue;
    public InmarDate expirationDate;
    public InmarDate clipStartDate;
    public InmarDate clipEndDate;
  

}
