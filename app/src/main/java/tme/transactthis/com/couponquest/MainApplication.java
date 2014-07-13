package tme.transactthis.com.couponquest;

import android.app.Application;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.ParseException;

import tme.transactthis.com.couponquest.model.inmar.InmarApi;
import tme.transactthis.com.couponquest.model.vo.Coupon;
import tme.transactthis.com.couponquest.model.vo.Level;
import tme.transactthis.com.couponquest.model.vo.Quest;
import tme.transactthis.com.couponquest.model.vo.QuestItem;
import tme.transactthis.com.couponquest.model.vo.Store;
import tme.transactthis.com.couponquest.model.vo.UserInfo;

/**
 * Created by trey on 7/12/14.
 * Copyright 2014 MindMine LLC.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        InmarApi.create(getApplicationContext(), 5*1024*1024);
        Parse.initialize(this, "6Du1LJR8K9vGLGYSRlFG9BYpv0IRxVhaHdxAObeW", "zoG34zCZxuaxnZhxHfVcuf4MUtnvckhqVmpvOnkO");

        ParseObject.registerSubclass(Coupon.class);
        ParseObject.registerSubclass(UserInfo.class);
        ParseObject.registerSubclass(Store.class);
        ParseObject.registerSubclass(QuestItem.class);
        ParseObject.registerSubclass(Quest.class);
        ParseObject.registerSubclass(Level.class);


//        ParseUser user = new ParseUser();
//        user.setUsername("transactthis");
//        user.setPassword("transactthis");
//        user.setEmail("user@transactthis.com");

        ParseUser.logInInBackground("transactthis", "transactthis", new LogInCallback() {
            public void done(ParseUser user, com.parse.ParseException e) {
                if (user != null) {


                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                }
            }
        });



    }
}
