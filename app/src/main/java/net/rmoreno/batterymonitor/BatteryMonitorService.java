package net.rmoreno.batterymonitor;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static android.content.Context.NOTIFICATION_SERVICE;

public final class BatteryMonitorService  {

//    private static final String TAG = "BatteryMonitorService";
//    static final String ACTION_TIMER = BatteryMonitorService.class.getName()
//            + ".action.TIMER";
//    static final String ACTION_START = BatteryMonitorService.class.getName()
//            + ".action.START";
//    static final String ACTION_STOP = BatteryMonitorService.class.getName()
//            + ".action.STOP";
//    static final String ACTION_SET_PERIOD = BatteryMonitorService.class.getName()
//            + ".action.SET_PERIOD";
//    static final String EXTRA_PERIOD = BatteryMonitorService.class.getName()
//            + ".extra.PERIOD";
//    private static final int NOTIFICATION_ID = 1;
//
//    // 30 sec. For first time lag for starting service.
//    private static final long FIRST_TIME_LAG = 30 * 1000;
//
//    public BatteryMonitorService() {
//        super(TAG);
//    }
//
//    @Override
//    protected final void onHandleIntent(Intent intent) {
//        if (BuildConfig.DEBUG) {
//            Log.d(TAG, "onHandleIntent in action = " + intent.getAction());
//        }
//
//        if (ACTION_START.equals(intent.getAction())) {
//            recordBatteryState();
//            final long monitorPeriod = ((BatteryMonitorApplication) getApplication())
//                    .getMonitorPeriod();
//            if (BuildConfig.DEBUG) {
//                Log.d(TAG, "Monitoring period retrieved. " + monitorPeriod);
//            }
//            startTimer(monitorPeriod);
//            startNotification();
//            ((BatteryMonitorApplication) getApplication())
//                    .setMonitorStatus(true);
//
//        } else if (ACTION_STOP.equals(intent.getAction())) {
//            stopTimer();
//            stopNotification();
//            ((BatteryMonitorApplication) getApplication())
//                    .setMonitorStatus(false);
//        } else if (ACTION_TIMER.equals(intent.getAction())) {
//            recordBatteryState();
//        } else if (ACTION_SET_PERIOD.equals(intent.getAction())) {
//            setMonitoringPeriod(intent);
//        }
//
//        stopSelf();
//    }
//
//    private void setMonitoringPeriod(Intent intent) {
//        final long period = intent.getLongExtra(EXTRA_PERIOD,
//                Util.DEFAULT_PERIOD);
//        if (BuildConfig.DEBUG) {
//            Log.d(TAG, "setMonitoringPeriod period = " + period);
//        }
//        ((BatteryMonitorApplication) getApplication())
//                .setMonitoringPeriod(period);
//    }
//
//    private void recordBatteryState() {
//        final int batteryLevel = Util
//                .getCurrentBatteryLevel(getApplicationContext());
//        if (BuildConfig.DEBUG) {
//            Log.d(TAG, "recordBatteryState batteryLevel = " + batteryLevel);
//        }
//        File file = new File(Util.FILE_PATH);
//        try {
//            OutputStream stream = new BufferedOutputStream(
//                    new FileOutputStream(file, true));
//            OutputStreamWriter streamWriter = new OutputStreamWriter(stream,
//                    "UTF-8");
//
//            // yyyy/MM/dd,HH:mm:ss,bat
//            StringBuilder builder = new StringBuilder()
//                    .append(new SimpleDateFormat("yyyy/MM/dd','HH:mm:ss", Locale.US)
//                            .format(new Date(System.currentTimeMillis())))
//                    .append(",").append(batteryLevel).append("\n");
//
//            streamWriter.append(builder.toString());
//            builder = null;
//            streamWriter.close();
//            stream.close();
//        } catch (IOException e) {
//            if (BuildConfig.DEBUG) {
//                Log.w(TAG, "IOException", e);
//            }
//        }
//    }
//
//    private void startTimer(final long period) {
//
//        Log.d(TAG, "startTimer called.");
//        PendingIntent operation = getPendingIntentForAlarmManager();
//
//        // set the pending intent to AlarmManager with repeating mode.
//        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
//                FIRST_TIME_LAG, period, operation);
//    }
//
//    private void startNotification() {
//        Log.d(TAG, "startNotification called");
//        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        Notification notification = new Notification(
//                R.drawable.ic_stat_monitoring, getString(R.string.app_name),
//                System.currentTimeMillis());
//        notification.flags |= Notification.FLAG_ONGOING_EVENT;
//        PendingIntent pendingIntent = PendingIntent.getActivity(
//                getApplicationContext(), NOTIFICATION_ID, new Intent(
//                        getApplicationContext(), BatteryMonitorActivity.class),
//                0);
//        notification.setLatestEventInfo(getApplicationContext(),
//                getString(R.string.app_name),
//                getString(R.string.notification_description), pendingIntent);
//        manager.notify(NOTIFICATION_ID, notification);
//    }
//
//    private void stopTimer() {
//        Log.d(TAG, "stopTimer called.");
//        PendingIntent operation = getPendingIntentForAlarmManager();
//
//        AlarmManager manager = (AlarmManager) this
//                .getSystemService(Context.ALARM_SERVICE);
//        manager.cancel(operation);
//    }
//
//    private void stopNotification() {
//        Log.d(TAG, "stopNotification called");
//        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        manager.cancel(NOTIFICATION_ID);
//    }
//
//    private PendingIntent getPendingIntentForAlarmManager() {
//        Intent startService = new Intent(getApplicationContext(),
//                BatteryMonitorService.class);
//        startService.setAction(ACTION_TIMER);
//        return PendingIntent.getService(this, 0, startService,
//                PendingIntent.FLAG_CANCEL_CURRENT);
//    }

}