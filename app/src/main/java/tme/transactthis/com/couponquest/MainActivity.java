package tme.transactthis.com.couponquest;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconConsumer;
import com.radiusnetworks.ibeacon.IBeaconManager;
import com.radiusnetworks.ibeacon.MonitorNotifier;
import com.radiusnetworks.ibeacon.RangeNotifier;
import com.radiusnetworks.ibeacon.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tme.transactthis.com.btle.BeaconServiceUtility;
import tme.transactthis.com.couponquest.model.ICoupon;
import tme.transactthis.com.couponquest.model.vo.UserInfo;


public class MainActivity extends ListActivity implements IBeaconConsumer {

    private List<ICoupon> mCoupons;

    private BeaconServiceUtility beaconUtil = null;
    private IBeaconManager iBeaconManager = IBeaconManager.getInstanceForApplication(this);

    private Intent questActivityIntent;
    private Boolean questMode;
    private MenuItem questMenuItem;

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

        beaconUtil = new BeaconServiceUtility(this);
        questActivityIntent = new Intent(this, QuestViewActivity.class);

        questMode = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        beaconUtil.onStart(iBeaconManager, this);
    }

    @Override
    protected void onStop() {
        beaconUtil.onStop(iBeaconManager, this);
        super.onStop();
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
        questMenuItem = (MenuItem) menu.findItem(R.id.quest_action);
        questMenuItem.setVisible(questMode);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.quest_action) {
            Intent intent = new Intent(this, QuestViewActivity.class);
            startActivity(intent);
        }
        if(id == android.R.id.home){
            Log.d("Going home", "i want to go home");
            ProfileActivity.startProfileActivity(this);
        }
        return super.onOptionsItemSelected(item);
    }

    public void tmpClick(View v){
        Intent intent = new Intent(this, QuestViewActivity.class);
        startActivity(intent);
    }

    @Override
    public void onIBeaconServiceConnect() {
        iBeaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<IBeacon> iBeacons, Region region) {
                for (IBeacon beacon : iBeacons) {
                    if (beacon.getMinor() == 15
                        && beacon.getMajor() == 1
                        && beacon.getProximityUuid().equalsIgnoreCase("1341bef5-56f1-4f75-8972-fe35a422aecc")
                        && beacon.getProximity() == 1
                        && !questMode) {
                        questMode = true;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                invalidateOptionsMenu();
                                startActivity(questActivityIntent);
                            }
                        });
                    }
                }
            }

        });

        iBeaconManager.setMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                Log.e("BeaconDetectorService", "didEnterRegion");
                // logStatus("I just saw an iBeacon for the first time!");
            }

            @Override
            public void didExitRegion(Region region) {
                Log.e("BeaconDetectorService", "didExitRegion");
                // logStatus("I no longer see an iBeacon");
            }

            @Override
            public void didDetermineStateForRegion(int state, Region region) {
                Log.e("BeaconDetectorService", "didDetermineStateForRegion");
                // logStatus("I have just switched from seeing/not seeing iBeacons: " + state);
            }

        });

        try {
            iBeaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            iBeaconManager.startMonitoringBeaconsInRegion(new Region("myMonitoringUniqueId", null, null, null));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
