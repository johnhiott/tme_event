package tme.transactthis.com.couponquest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.NumberFormat;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import tme.transactthis.com.couponquest.model.ICoupon;

/**
 * Created by Chris Ollenburg on 7/12/14.
 */
public class CouponDetailActivity extends Activity {
    private static final String COUPON_SHORT_DESC_KEY = "COUPON_SHORT_DESC_KEY";
    private static final String COUPON_VALUE_KEY = "COUPON_CATEGORY_VALUE_KEY";
    private static final String COUPON_DESCRIPTION_KEY = "COUPON_DESCRIPTION_KEY";
    private static final String COUPON_TERMS_KEY = "COUPON_TERMS_KEY";

    private static final String TAG = CouponDetailActivity.class.getSimpleName();

    @InjectView(R.id.coupon_title_tv)
    TextView couponTitle;
    @InjectView(R.id.coupon_dollar_amount_tv)
    TextView couponDollarAmount;
    @InjectView(R.id.coupon_detail_tv)
    TextView couponDetail;
    @InjectView(R.id.coupon_disclaimer_tv)
    TextView couponDisclaimer;

    public static void startDetailActivity(Activity caller, String shortDesc, int value, String description, String terms)
    {
        Intent intent = new Intent(caller, CouponDetailActivity.class);
        intent.putExtra(COUPON_SHORT_DESC_KEY, shortDesc);
        intent.putExtra(COUPON_VALUE_KEY, value);
        intent.putExtra(COUPON_DESCRIPTION_KEY, description);
        intent.putExtra(COUPON_TERMS_KEY, terms);
        caller.startActivity(intent);
    }

    //Takes the dollar value as cents and prints out a string
    public String convertToDollars(int value)
    {
        int dollars = value/100;
        int cents = value%100;
        String temp = String.valueOf(dollars) + "." + String.valueOf(cents);
        Double dub = Double.parseDouble(temp);
        String money = NumberFormat.getCurrencyInstance().format(dub);
        return money;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_detail_activity);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        String shortDesc = intent.getStringExtra(COUPON_SHORT_DESC_KEY);
        int intCategoryValue = intent.getIntExtra(COUPON_VALUE_KEY, 0);
        String categoryValue = convertToDollars(intCategoryValue);
        String description = intent.getStringExtra(COUPON_DESCRIPTION_KEY);
        String terms = intent.getStringExtra(COUPON_TERMS_KEY);

        couponTitle.setText(shortDesc);
        couponDollarAmount.setText(categoryValue);
        couponDetail.setText(description);
        couponDisclaimer.setText(terms);
    }
}
