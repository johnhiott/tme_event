package tme.transactthis.com.couponquest;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.parse.FindCallback;
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

    private List<ICoupon> mCoupons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = this;

        //TODO: this will actually be a query to parse
        ParseUser user = ParseUser.getCurrentUser();
        user.fetchInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                final UserInfo info = (UserInfo)parseObject.getParseObject("userInfo");
                info.fetchInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject parseObject, ParseException e) {
                        ParseObject.fetchAllInBackground(info.getCouponList(), new FindCallback<tme.transactthis.com.couponquest.model.vo.Coupon>() {
                            @Override
                            public void done(List<tme.transactthis.com.couponquest.model.vo.Coupon> coupons, ParseException e) {
                                setAdapter(info);
                            }
                        });

                    }
                });

            }
        });

       ActionBar ab= getActionBar();
       ab.setTitle("   Trey Robinson");
       ab.setIcon(R.drawable.trey_small);
       ab.setDisplayHomeAsUpEnabled(true);
       ab.setHomeAsUpIndicator(R.drawable.transparent_for_trey);

    }

    public void setAdapter(UserInfo userInfo){
        mCoupons = (List<ICoupon>)(List<?>)userInfo.getCouponList();
        CouponAdapter couponAdapter = new CouponAdapter(this, mCoupons );
        setListAdapter( couponAdapter );
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        ICoupon coupon = mCoupons.get(position);
        CouponDetailActivity.startDetailActivity(this, coupon.getShortDescription(), coupon.getValue(), coupon.getDescription(), coupon.getTerms());
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
        if(id == android.R.id.home){
            //@TODO Load profile.
            Log.d("Going home", "i want to go home");
        }
        return super.onOptionsItemSelected(item);
    }

    public void tmpClick(View v){
        Intent intent = new Intent(this, QuestViewActivity.class);
        startActivity(intent);
    }

}
