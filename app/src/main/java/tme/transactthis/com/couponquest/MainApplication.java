package tme.transactthis.com.couponquest;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import tme.transactthis.com.couponquest.model.inmar.InmarApi;
import tme.transactthis.com.couponquest.model.inmar.vo.Coupon;

/**
 * Created by trey on 7/12/14.
 * Copyright 2014 MindMine LLC.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        InmarApi.create(getApplicationContext(), 5*1024*1024);
        Parse.initialize(this, "6Du1LJR8K9vGLGYSRlFG 9BYpv0IRxVhaHdxAObeW", "zoG34zCZxuaxnZhxHfVcuf4MUtnvckhqVmpvOnkO");

    }
}
