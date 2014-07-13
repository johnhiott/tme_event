package tme.transactthis.com.couponquest;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import tme.transactthis.com.couponquest.model.inmar.InmarApi;
import tme.transactthis.com.couponquest.model.inmar.vo.Coupon;


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
                CouponAdapter couponAdapter = new CouponAdapter( context, mCoupons );
                setListAdapter( couponAdapter );
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("FAILURE", "FAILURE");
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        Bundle args = new Bundle();
        args.putSerializable( getString(R.string.COUPON_KEY), mCoupons.get(position) );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main, menu );
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
}
