package tme.transactthis.com.couponquest.model.inmar;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.Callback;
import tme.transactthis.com.couponquest.model.inmar.vo.Coupon;

/**
 * Created by trey on 7/12/14.
 * Copyright 2014 MindMine LLC.
 */
public interface InmarServices {

        @GET("/offers")
        public void getOffers(@Query("skip") int skip
                , @Query("clipped") boolean clipped
                , @Query("keywords") String keywords
                , @Query("limit") int limit
                , @Query("categories") String categories
                , @Query("sort") String sort
                , @Query("redeemed") boolean redeemed
                , @Query("expired") boolean expired
                , @Query("includeClipped") boolean includeClipped
                , Callback<List<Coupon>> callback);

    @GET("/offers")
    public void getOffers(Callback<List<Coupon>> callback);

}
