package tme.transactthis.com.couponquest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import tme.transactthis.com.couponquest.model.ICoupon;

/**
 * Created by Chris Ollenburg on 7/12/14.
 */
public class CouponDetailActivity extends Activity {
    private static final String TAG = CouponDetailActivity.class.getSimpleName();

    private ICoupon mCoupon;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_detail_activity);

//        Intent intent = getIntent();
//        mCoupon  = (ICoupon) intent.getSerializableExtra(getString(R.string.COUPON_KEY));
    }

}
