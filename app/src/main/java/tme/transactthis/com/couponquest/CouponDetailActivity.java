package tme.transactthis.com.couponquest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import tme.transactthis.com.couponquest.model.ICoupon;

/**
 * Created by Chris Ollenburg on 7/12/14.
 */
public class CouponDetailActivity extends Activity {
    private static final String TAG = CouponDetailActivity.class.getSimpleName();

    @InjectView(R.id.coupon_title_tv)
    TextView couponTitle;
    @InjectView(R.id.coupon_dollar_amount_tv)
    TextView couponDollarAmount;
    @InjectView(R.id.coupon_detail_tv)
    TextView couponDetail;
    @InjectView(R.id.coupon_disclaimer_tv)
    TextView couponDisclaimer;


    private ICoupon mCoupon;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_detail_activity);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        mCoupon  = (ICoupon) intent.getSerializableExtra(getString(R.string.COUPON_KEY));

        couponTitle.setText(mCoupon.getShortDescription());
        couponDollarAmount.setText(mCoupon.getCategoryValue());
        couponDetail.setText(mCoupon.getDescription());
        couponDisclaimer.setText(mCoupon.getTerms());
    }
}
