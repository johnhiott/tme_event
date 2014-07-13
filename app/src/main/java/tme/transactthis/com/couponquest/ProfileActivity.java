package tme.transactthis.com.couponquest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ChrisOllenburg on 7/13/14.
 */
public class ProfileActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.gotowallet) void goToWallet() {
        // TODO Go To Wallet
    }
}
