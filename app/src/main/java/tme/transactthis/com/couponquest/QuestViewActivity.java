package tme.transactthis.com.couponquest;

import android.app.Activity;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

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

public class QuestViewActivity extends Activity implements NfcAdapter.ReaderCallback {
    private static final String TAG = NFCTestActivity.class.getSimpleName();
    private List<ICoupon> mCoupons;
    private List<QuestItem> mQuestItems;
    private ArrayList<Integer> mNumList;

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NfcManager nm = (NfcManager) getSystemService(NFC_SERVICE);
        NfcAdapter na = nm.getDefaultAdapter();
        na.enableReaderMode(this, this, NfcAdapter.FLAG_READER_NFC_A|NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK, null);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Quest");

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> questList, ParseException e) {
                if (e == null) {
                    if (questList.size() == 0){
                        //createQuest();
                    } else {

                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

    }


    @Override
    public void onTagDiscovered(Tag tag) {

    }

    public void createQuest(){

        Integer[] nums = {0,1,2,3,4,5,6,7,8,9};
        mNumList = new ArrayList<Integer>(Arrays.asList(nums));
        Collections.shuffle(mNumList);

        InmarApi.getInstance().getOffers( new Callback<List<Coupon>>() {
            @Override
            public void success(List<Coupon> couponResponse, Response response) {
                mCoupons = (List<ICoupon>)(List<?>) couponResponse;

                for (int x = 0; x < 4; x++) {
                    int randomId = mNumList.get(x);
                    QuestItem questItem = new QuestItem();
                    questItem.setCoupon( (tme.transactthis.com.couponquest.model.vo.Coupon)mCoupons.get(randomId).getParseObject() );
                    mQuestItems.add(questItem);
                }

                Quest quest = new Quest();
                quest.setQuestItems(mQuestItems);
                quest.setStore(new Store());
                quest.setUser(new UserInfo());
                try {
                    quest.save();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("FAILURE", "FAILURE");
            }
        });
    }
}
