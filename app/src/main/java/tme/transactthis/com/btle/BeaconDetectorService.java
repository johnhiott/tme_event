package tme.transactthis.com.btle;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.radiusnetworks.ibeacon.IBeaconConsumer;
import com.radiusnetworks.ibeacon.IBeaconManager;
import com.radiusnetworks.ibeacon.MonitorNotifier;
import com.radiusnetworks.ibeacon.Region;


// We really only need this if we want to show notifications.

public class BeaconDetectorService extends Service implements IBeaconConsumer {

	private IBeaconManager iBeaconManager = IBeaconManager.getInstanceForApplication(this);

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_STICKY;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		iBeaconManager.bind(this);

		final Handler handler = new Handler();
		final Runnable runnable = new Runnable() {

			@Override
			public void run() {
				stopSelf();
			}
		};
		handler.postDelayed(runnable, 10000);
	}

	@Override
	public void onDestroy() {
		iBeaconManager.unBind(this);
		super.onDestroy();
	}

	@Override
	public void onIBeaconServiceConnect() {
		iBeaconManager.setMonitorNotifier(new MonitorNotifier() {
			@Override
			public void didEnterRegion(Region region) {
				Log.e("BeaconDetectorService", "didEnterRegion");
				generateNotification(BeaconDetectorService.this, region.getUniqueId()
						+ ": just saw this iBeacon for the first time");
			}

			@Override
			public void didExitRegion(Region region) {
				Log.e("BeaconDetectorService", "didExitRegion");
				generateNotification(BeaconDetectorService.this, region.getUniqueId() + ": is no longer visible");
			}

			@Override
			public void didDetermineStateForRegion(int state, Region region) {
				Log.e("BeaconDetectorService", "didDetermineStateForRegion:" + state);
			}

		});

		try {
			iBeaconManager.startMonitoringBeaconsInRegion(new Region("myMonitoringUniqueId", null, null, null));
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Issues a notification to inform the user that server has sent a message.
	 */
	private static void generateNotification(Context context, String message) {

        // TODO figure out what kind of message or call back to send here
//		Intent launchIntent = new Intent(context, MonitoringActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
//				| Intent.FLAG_ACTIVITY_SINGLE_TOP);
//
//		((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(
//				0,
//				new NotificationCompat.Builder(context).setWhen(System.currentTimeMillis())
//						.setSmallIcon(R.drawable.ic_launcher).setTicker(message)
//						.setContentTitle(context.getString(R.string.app_name)).setContentText(message)
//						.setContentIntent(PendingIntent.getActivity(context, 0, launchIntent, 0)).setAutoCancel(true)
//						.build());

	}

}