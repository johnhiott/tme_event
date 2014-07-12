package tme.transactthis.com.couponquest.model.inmar;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.HttpResponseCache;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.security.auth.callback.Callback;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import tme.transactthis.com.couponquest.model.inmar.vo.Coupon;
import tme.transactthis.com.couponquest.model.inmar.vo.OfferParams;

/**
 * Created by trey on 7/12/14.
 * Copyright 2014 MindMine LLC.
 */
public class InmarApi {

    public static InmarApi mInstance;

    private static final String API_URL = "https://tme-ice.test.dpn.inmar.com";

    private RestAdapter mRestAdapter;
    private InmarServices mServices;

    public static InmarApi getInstance(){
        return mInstance;
    }

    public static void create(Context applicationContext, long cacheSize){
        mInstance =  new InmarApi(applicationContext, cacheSize);
    }

    private InmarApi(Context applicationContext, long cacheSize){
        OkHttpClient okHttpClient = new OkHttpClient();
        HttpResponseCache cache = null;

        try {
            cache = new HttpResponseCache(applicationContext.getCacheDir(), cacheSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        okHttpClient.setResponseCache(cache);

        Gson gson = new GsonBuilder()
                //.registerTypeAdapter(Date.class, new DateAdapter())
                .create();

        mRestAdapter = new RestAdapter.Builder()
                .setClient(new OkClient(okHttpClient))
                .setConverter(new GsonConverter(gson))
                .setServer(API_URL)
                .build();

        mRestAdapter.create(InmarServices.class);
    }

    public void login(String username, String password){

    }

    public void register(String username, String password, String email){

    }

    public void getOffers(OfferParams params, retrofit.Callback<List<Coupon>> callback){
        mServices.getOffers(
                 params.skip
                ,params.clipped
                , params.keywords
                , params.limit
                , params.categories
                , params.sort
                , params.redeemed
                , params.expired
                , params.includeClipped
                ,callback
        );

    }


}
