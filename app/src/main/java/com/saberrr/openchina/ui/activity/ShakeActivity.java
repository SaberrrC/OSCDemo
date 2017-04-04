package com.saberrr.openchina.ui.activity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Service;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;
import com.saberrr.openchina.R;

public class ShakeActivity extends AppCompatActivity {

    private AudioManager audioManager;//管理音频的类
    private SoundPool soundPool;//声明一个SoundPool
    private int music, music2;//；来设置suondID
    private ShakeListener mShakeListener=null;
    public static long GrouplastUpdateTime;
    private TextView MyTextView;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        MyTextView=(TextView)findViewById(R.id.myTextView);
        mShakeListener = new ShakeListener(this);
        setListeners();
        initState();

    }
    //添加摇一摇声音
    private void initState() {
        soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);//第一个参数为同时播放数据流的最大个数，第二数据流类型，第三为声音质量
        music = soundPool.load(this, R.raw.shake_sound_male, 1); //把你的声音素材放到res/raw里，第2个参数即为资源文件，第3个为音乐的优先级
        music2 = soundPool.load(this, R.raw.shake_sound, 1);
    }
    //添加摇一摇监听
    private void setListeners() {
        mShakeListener.setOnShakeListener(new ShakeListener.OnShakeListener() {
            public void onShake() {
                System.out.println("<<<<<<"+"Group");
                long currentUpdateTime = System.currentTimeMillis();
                long timeInterval = currentUpdateTime - GrouplastUpdateTime;
                //设置每三秒可摇一次
                if (timeInterval < 3000)
                    return;
                GrouplastUpdateTime = currentUpdateTime;
//                手机震动效果
//                Vibrator vVi = (Vibrator) MyActivity.this
//                        .getSystemService(Service.VIBRATOR_SERVICE);
//                vVi.vibrate(400);
                //播放音频，可以对左右音量分别设置，可以设置优先级，循环次数以及速率
                //速率最高2，最低0.5，正常为1
                float volumeNum = (float)getVolume()/7;
                int streamID = soundPool.play(music, 1, 1, 0, 0, (float)1.4);
                soundPool.setVolume(streamID, volumeNum, volumeNum);
//                soundPool.play(music, 1, 1, 0, 0, 1);//播放声音
                Thread thread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            Thread.sleep(2000);
                            float volumeNum = (float)getVolume()/7;
                            int streamID = soundPool.play(music2, 1, 1, 0, 0, (float)1.4);
                            soundPool.setVolume(streamID, volumeNum, volumeNum);//播放声音
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
                MyTextView.setText("Hello World!!!");

            }
        });
    }
    //获得当前系统音量 0~7
    private int getVolume(){
        int volume = -1;
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        volume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
        Log.i("STREAM_RING", "" + volume);
        return volume;
    }

    static class ShakeListener implements SensorEventListener {
        // 速度阈值，当摇晃速度达到这值后产生作用
        private static final int SPEED_SHRESHOLD = 3000;
        // 两次检测的时间间隔
        private static final int UPTATE_INTERVAL_TIME = 70;
        // 传感器管理器
        private SensorManager sensorManager;
        // 传感器
        private Sensor sensor;
        // 重力感应监听器
        private OnShakeListener onShakeListener;
        // 上下文
        private Context mContext;
        // 手机上一个位置时重力感应坐标
        private float lastX;
        private float lastY;
        private float lastZ;
        // 上次检测时间
        private long lastUpdateTime;

        // 构造器
        public ShakeListener(Context c) {
            // 获得监听对象
            mContext = c;
            start();
        }

        // 开始
        public void start() {
            // 获得传感器管理器
            sensorManager = (SensorManager) mContext
                    .getSystemService(Context.SENSOR_SERVICE);
            if (sensorManager != null) {
                // 获得重力传感器
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            }
            // 注册
            if (sensor != null) {
                sensorManager.registerListener(this, sensor,
                        SensorManager.SENSOR_DELAY_GAME);
            }

        }

        // 停止检测
        public void stop() {
            sensorManager.unregisterListener(this);
        }

        // 设置重力感应监听器
        public void setOnShakeListener(OnShakeListener listener) {
            onShakeListener = listener;
        }

        // 重力感应器感应获得变化数据
        public void onSensorChanged(SensorEvent event) {
            // 现在检测时间
            long currentUpdateTime = System.currentTimeMillis();
            // 两次检测的时间间隔
            long timeInterval = currentUpdateTime - lastUpdateTime;
            // 判断是否达到了检测时间间隔
            if (timeInterval < UPTATE_INTERVAL_TIME)
                return;
            // 现在的时间变成last时间
            lastUpdateTime = currentUpdateTime;

            // 获得x,y,z坐标
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // 获得x,y,z的变化值
            float deltaX = x - lastX;
            float deltaY = y - lastY;
            float deltaZ = z - lastZ;

            // 将现在的坐标变成last坐标
            lastX = x;
            lastY = y;
            lastZ = z;

            double speed = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ
                    * deltaZ)
                    / timeInterval * 10000;
            Log.v("thelog", "===========log===================");
            // 达到速度阀值，发出提示
            if (speed >= SPEED_SHRESHOLD) {
                onShakeListener.onShake();
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        // 摇晃监听接口
         public interface OnShakeListener {
             void onShake();
        }

    }
}
