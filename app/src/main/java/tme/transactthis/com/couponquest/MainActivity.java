package tme.transactthis.com.couponquest;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.GetCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import tme.transactthis.com.couponquest.model.ICoupon;
import tme.transactthis.com.couponquest.model.QuestManager;
import tme.transactthis.com.couponquest.model.inmar.InmarApi;
import tme.transactthis.com.couponquest.model.inmar.vo.Coupon;
import tme.transactthis.com.couponquest.model.vo.UserInfo;


public class MainActivity extends ListActivity {

    private List<Coupon> mCoupons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = this;

        InmarApi.getInstance().getOffers( new Callback<List<Coupon>>() {
            @Override
            public void success(List<Coupon> couponResponse, Response response) {
                mCoupons = couponResponse;
                CouponAdapter couponAdapter = new CouponAdapter( context, couponResponse );
                setListAdapter( couponAdapter );
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("FAILURE", "FAILURE");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        final ICoupon coupon = mCoupons.get(position);

//        QuestManager.getInstance().saveCoupon((tme.transactthis.com.couponquest.model.vo.Coupon)coupon.getParseObject() , new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                Log.d("Sup son", "yay");
//            }
//        });


    }



}
