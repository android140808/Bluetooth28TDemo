package com.avater.myapplication.service;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.TextUtils;


import com.avater.myapplication.interfaces.ScanDeviceInterface;
import com.avater.myapplication.utils.L28TBandUtils;
import com.avater.myapplication.utils.Logger;

import java.util.LinkedList;


/**
 * Created by Avatar on 2018/8/14.
 */

public enum Bluetooth28TUtils {
    INSTANCE;
    private Context mContext;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothManager mBluetoothManager;
    private BlueTooth28TService mBlueTooth28TService;
    private ScanDeviceInterface mInterface;
    private boolean mFilter;
    private String mFilterName;
    private ServiceConnection mConnection28T = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBlueTooth28TService = ((BlueTooth28TService.MyBinder) service).getService();
            L28TBandUtils.ISTANCE.init(mContext, mBlueTooth28TService);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    /**
     * 需要发送的命令集合
     */
    private LinkedList<Leaf28TBean> sendDatas = new LinkedList<>();

    public void addLeaf28T(Leaf28TBean bean) {
        sendDatas.addLast(bean);
    }

    public void initContext(Context mContext) {
        this.mContext = mContext;
        if (mBlueTooth28TService == null) {
            Intent service = new Intent(mContext, BlueTooth28TService.class);
            mContext.bindService(service, mConnection28T, Context.BIND_AUTO_CREATE);
        }
        mBluetoothManager = (BluetoothManager) this.mContext.getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManager.getAdapter();
    }

    public void connectDevice(String address) {
        if (mBlueTooth28TService != null) {
            mBlueTooth28TService.connect(address);
        }
    }

    public void setScanDeviceListener(ScanDeviceInterface sdi) {
        mInterface = sdi;
    }

    public void startScan() {
        mBluetoothAdapter.startLeScan(mLeCallBack);
    }

    public void stopScan() {
        mBluetoothAdapter.stopLeScan(mLeCallBack);
    }

    public void setScanDeviceFilter(String filter) {
        mFilter = true;
        mFilterName = filter;
    }

    private BluetoothAdapter.LeScanCallback mLeCallBack = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            String name = device.getName();
            String address = device.getAddress();
            if (name != null && !TextUtils.isEmpty(name) && address != null && !TextUtils.isEmpty(address)) {
                Logger.d("", "设备名：" + name + ", mac：" + address);
                if (mInterface != null) {
                    if (mFilter) {
                        if (mFilterName != null && !TextUtils.isEmpty(mFilterName)) {
                            if (name.contains(mFilterName)) {
                                mInterface.getDevice(name, address);
                            }
                        }
                    } else {
                        mInterface.getDevice(name, address);
                    }
                }
            }
        }
    };

    public void sendtSmallDatas(byte[] data) {
        mBlueTooth28TService.sendSmallDatas(data);
    }


    public void onDestroy() {

    }

}
