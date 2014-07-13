package tme.transactthis.com.couponquest;

import android.app.Activity;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by ChrisOllenburg on 7/12/14.
 */
public class NFCTestActivity extends Activity implements NfcAdapter.ReaderCallback {
    private static final String TAG = NFCTestActivity.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nfc_test);

        NfcManager nm = (NfcManager) getSystemService(NFC_SERVICE);
        NfcAdapter na = nm.getDefaultAdapter();
        na.enableReaderMode(this, this, NfcAdapter.FLAG_READER_NFC_A|NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK, null);
    }

    @Override
    public void onTagDiscovered(Tag tag) {
        NfcA aTag = NfcA.get(tag);
        Log.e(TAG, "**************************Discovered a TAG**************************" + tag.getId());
    }
}
