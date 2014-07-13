package tme.transactthis.com.couponquest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import tme.transactthis.com.couponquest.model.inmar.vo.Coupon;

public class CouponDetailActivity extends Activity {

    private Coupon mCoupon;

    @Override
    public void onCreate(Bundle savedInstanceState){

        Intent intent = getIntent();
        mCoupon  = (Coupon) intent.getSerializableExtra( getString( R.string.COUPON_KEY ) );

    }

}
