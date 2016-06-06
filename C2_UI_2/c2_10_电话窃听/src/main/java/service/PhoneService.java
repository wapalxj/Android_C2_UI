package service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.IOException;

public class PhoneService extends Service {
    private MediaRecorder mRecorder;
    private String mFileName="/mnt/adcard/vero.3gp";
    private TelephonyManager tm;
    private MyPhoneStateListener listener;
    public PhoneService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i("PhoneService","onCreate");
        tm= (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        listener=new MyPhoneStateListener();
        tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    private class  MyPhoneStateListener extends PhoneStateListener{

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            //电话状态改变监听
            switch (state){
                case  TelephonyManager.CALL_STATE_IDLE:
                    //电话空闲：即正常待机
                    if (mRecorder!=null){
                        stopRecording();
                    }
                    break;
                case  TelephonyManager.CALL_STATE_OFFHOOK:
                    //电话接通状态
                    startRecording();
                    break;
                case  TelephonyManager.CALL_STATE_RINGING:
                    //电话正在响
                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tm.listen(listener,PhoneStateListener.LISTEN_NONE);
    }

    private void startRecording(){
        mRecorder=new MediaRecorder();
        //指定一个源：MIC：来自手机麦克风,VOICE_CALL：录制讲话的所有声音（对方讲话和自己讲话，美国法律不支持，中国可以）
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //输出数据的格式
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        //保存的位置
        mRecorder.setOutputFile(mFileName);
        //使用什么解码器
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mRecorder.prepare();
            mRecorder.start();
            Log.e("startRecording","prepare()----startingggggggggggggg");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("startRecording","prepare()----failed");
        }
    }
    private void stopRecording(){
        mRecorder.stop();
        mRecorder.release();
        mRecorder=null;
    }
}
