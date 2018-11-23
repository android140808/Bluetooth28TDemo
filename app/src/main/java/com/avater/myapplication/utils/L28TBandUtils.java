package com.avater.myapplication.utils;

import android.content.Context;

import com.avater.myapplication.eventbus.DeviceState;
import com.avater.myapplication.service.BlueTooth28TService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;


public enum L28TBandUtils {
    ISTANCE;
    private Context mContext;
    private BlueTooth28TService mService;
    private ORDER mCurrentOrder;

    public void init(Context context, BlueTooth28TService service) {
        mContext = context;
        mService = service;
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDeviceState(DeviceState state) {
        Logger.d("", "设备状态 == " + state.state);
        switch (state.state) {
            case -1://已经断连了
                mService.connect();
                break;
            case 0://正在连接

                break;
            case 1://已经连接上了，可以发生数据了
                sendOrderToDevice(mCurrentOrder);
                break;
        }
    }

    public void setBindOver() {
        mCurrentOrder = null;
    }


    public void sendOrderToDevice(ORDER orderType) {
        if (mService == null) {
            return;
        }
        if (orderType == null) {
            return;
        }
        byte bytes[] = null;
        switch (orderType) {
            case GET_DEVICE_SOFT_VERSION:
                mCurrentOrder = ORDER.GET_DEVICE_SOFT_VERSION;
                bytes = new byte[]{0x6E, 0x01, (byte) 0x03, 0x03, (byte) 0x8F}; // 获取软件版本
                break;
            case GET_DEVICE_ID:
                mCurrentOrder = ORDER.GET_DEVICE_ID;
                bytes = new byte[]{0x6E, 0x01, 0x04, 0x01, (byte) 0x8F};// 获取ID
                break;
            case SET_USER_INFO:
                mCurrentOrder = ORDER.SET_USER_INFO;
                bytes = new byte[]{0x6E, 0x01, 0x12, 0x00, 0x07, (byte) 0xBA,
                        0x08, 0x02, (byte) 0xAA, 0x1B, 0x58, (byte) 0x8F};
                break;
            case SET_TIME:
                mCurrentOrder = ORDER.SET_TIME;
                bytes = getTime();
                break;
            case SET_DEVICE_UID:
                mCurrentOrder = ORDER.SET_DEVICE_UID;
                int userId = 1234;
                NumberUtils.intToByteArray(userId);
                bytes = new byte[]{0x6e, 0x01, (byte) 0x4a, 0x01, 0x00, 0x00, 0x00, 0x00, (byte) 0x8f};
                bytes[4] = NumberUtils.intToByteArray(userId)[3];
                bytes[5] = NumberUtils.intToByteArray(userId)[2];
                bytes[6] = NumberUtils.intToByteArray(userId)[1];
                bytes[7] = NumberUtils.intToByteArray(userId)[0];
                break;
        }
        mService.sendSmallDatas(bytes);
    }

    private byte[] getTime() {
        byte abyte0[] = new byte[]{(byte) 0x6E, 0x01, (byte) 0x12, (byte) 0x00, (byte) 0xBE, 0x07, 0x01, 0x0B, 0x5A, 0x0C, 0x30, (byte) 0x8F};
//        Calendar calendar = Calendar.getInstance();
//        int i = calendar.get(Calendar.FEBRUARY);
//        byte abyte0[] = new byte[11];
//        abyte0[0] = 110;
//        abyte0[1] = 1;
//        abyte0[2] = 21;
//        abyte0[3] = (byte) i;
//        abyte0[4] = (byte) (i >> 8);
//        abyte0[5] = (byte) (1 + calendar.get(Calendar.MARCH));
//        abyte0[6] = (byte) calendar.get(Calendar.DATE);
//        abyte0[7] = (byte) calendar.get(Calendar.DECEMBER);
//        abyte0[8] = (byte) calendar.get(Calendar.MINUTE);
//        abyte0[9] = (byte) calendar.get(Calendar.SECOND);
//        abyte0[10] = -113;
        return abyte0;
    }

    public enum ORDER {
        GET_DEVICE_FIREWARE_VERSION,
        GET_DEVICE_SOFT_VERSION,
        GET_DEVICE_ID,
        SET_USER_INFO,
        SET_TIME,
        SET_UNIT,
        SET_GOAL,
        SET_DEVICE_UID,
    }

    public void onDestory() {
        EventBus.getDefault().unregister(this);
    }


}
