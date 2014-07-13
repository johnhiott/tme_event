package tme.transactthis.com.couponquest;

import android.app.Activity;
import android.app.ListActivity;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import tme.transactthis.com.couponquest.model.ICoupon;
import tme.transactthis.com.couponquest.model.inmar.InmarApi;
import tme.transactthis.com.couponquest.model.inmar.vo.Coupon;
import tme.transactthis.com.couponquest.model.vo.Quest;
import tme.transactthis.com.couponquest.model.vo.QuestItem;
import tme.transactthis.com.couponquest.model.vo.Store;
import tme.transactthis.com.couponquest.model.vo.UserInfo;

public class QuestViewActivity extends ListActivity implements NfcAdapter.ReaderCallback {
    private static final String TAG = NFCTestActivity.class.getSimpleName();
    private List<ICoupon> mCoupons;
    private List<QuestItem> mQuestItems;
    private ArrayList<Integer> mNumList;
    private CouponAdapter mCouponAdapter;

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quest_view_activity);

        NfcManager nm = (NfcManager) getSystemService(NFC_SERVICE);
        NfcAdapter na = nm.getDefaultAdapter();
        na.enableReaderMode(this, this, NfcAdapter.FLAG_READER_NFC_A|NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK, null);
        createQuest();
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Quest");
//
//        query.findInBackground(new FindCallback<ParseObject>() {
//            public void done(List<ParseObject> questList, ParseException e) {
//                if (e == null) {
//                    if (questList.size() == 0){
//                        //createQuest();
//                    } else {
//
//                    }
//
//                } else {
//                    Log.d("score", "Error: " + e.getMessage());
//                }
//            }
//        });



    }


    @Override
    public void onTagDiscovered(Tag tag) {
        final ImageView image = mCouponAdapter.unlockNext();
        if(image != null)
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Animation fadeOut = new AlphaAnimation(1, 0);
                    fadeOut.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            image.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
                    fadeOut.setDuration(1000);
                    image.startAnimation(fadeOut);
                }
            });

        final View view = mCouponAdapter.unlockNextView();
        if(image != null)
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Animation slide = AnimationUtils.loadAnimation(QuestViewActivity.this,
                            R.anim.slide_right);
                    slide.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            view.setVisibility(View.GONE);
                            mCouponAdapter.setNextUnlocked();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    view.startAnimation(slide);
                }
            });
    }

    public void createQuest(){

        Integer[] nums = {0,1,2,3,4,5,6,7,8,9};
        mNumList = new ArrayList<Integer>(Arrays.asList(nums));
        Collections.shuffle(mNumList);

        InmarApi.getInstance().getOffers( new Callback<List<Coupon>>() {
            @Override
            public void success(List<Coupon> couponResponse, Response response) {
                mCoupons = (List<ICoupon>)(List<?>) couponResponse.subList(5, 8);

                 mCouponAdapter = new CouponAdapter(QuestViewActivity.this, mCoupons);
                setListAdapter( mCouponAdapter );

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("FAILURE", "FAILURE");
            }
        });
    }
}
