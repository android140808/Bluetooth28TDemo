package com.avater.myapplication.service;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.avater.myapplication.AppContext;
import com.avater.myapplication.utils.BluetoothCommandConstant;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;


/**
 * Created by Avater on 2018/8/9.
 * 28T蓝牙Service 重构
 */

public class BlueTooth28TService extends Service {

    private static final String TAG = BlueTooth28TService.class.getSimpleName();
    private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothGatt mBluetoothGatt;
    private UUID UUID_CONFIG_DESCRIPTOR = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    private UUID UUID_SERVICE_BASE = UUID.fromString("00006006-0000-1000-8000-00805f9b34fb");
    private UUID UUID_CHARACTERISTIC_8003 = UUID.fromString("00008003-0000-1000-8000-00805f9b34fb");
    private UUID UUID_CHARACTERISTIC_8004 = UUID.fromString("00008004-0000-1000-8000-00805f9b34fb");
    private UUID UUID_CHARACTERISTIC_8005 = UUID.fromString("00008005-0000-1000-8000-00805f9b34fb");
    private UUID UUID_SERVICE_EXTEND = UUID.fromString("00007006-0000-1000-8000-00805f9b34fb");
    private UUID UUID_CHARACTERISTIC_8002 = UUID.fromString("00008002-0000-1000-8000-00805f9b34fb");
    public LinkedList<NotificationInfo> notifications = new LinkedList<>();
    private boolean isSupportRemoteService = false;                                                 // 是否支持远程服务
    private boolean is8003Server7006 = true;
    private final IBinder mBinder = new MyBinder();
    private boolean isConnect = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "服务创建");
        BluetoothManager manager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        if (manager != null) {
            mBluetoothAdapter = manager.getAdapter();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.setPriority(1000);
        AppContext.INSTANCE.getContext().registerReceiver(mGattUpdataReceiver, intentFilter);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /**
     * 获取Service实例
     */
    public class MyBinder extends Binder {
        public BlueTooth28TService getService() {
            return BlueTooth28TService.this;
        }
    }


    private BluetoothGattCallback gattCallback = new BluetoothGattCallback() {
        // 连接：状态回调
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            Log.e(TAG, "==>>onConnectionStateChange( ): status = " + status + ", newState = " + newState);
            if (newState == 0) {//断开连接的状态
                Log.e(TAG, "断开连接的状态");
                isConnect = false;
                if (mBluetoothGatt != null) {
                    mBluetoothGatt.close();
                    mBluetoothGatt.disconnect();
                }
                BluetoothDevice bluetoothDevice = gatt.getDevice();
                try {
                    Method removeBondMethod = BluetoothDevice.class.getMethod("removeBond");
                    removeBondMethod.invoke(bluetoothDevice);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ((status == 133) && (newState == 2)) {//连接失败的回调
                Log.e(TAG, "连接失败的回调");
                isConnect = false;
                // TODO: 2018/11/21  继续连接
            } else if (status == 8 && newState == 0) {

            } else if (newState == 2 && status == 0) {//已经连接
                Log.e(TAG, "==>>1.已经连接");
                isConnect = true;
                mBluetoothGatt.discoverServices();
            }
        }

        // 连接：发现服务
        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            Log.e(TAG, "==>>onServicesDiscovered( )");
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.e(TAG, "==>>2.已发现服务 （onServicesDiscovered，准备检查并打开监听）");
                setNotifications();
                openNotification(mBluetoothGatt, notifications);
            } else {
                Log.e(TAG, "==>>onServicesDiscovered,有异常...!!!");
            }
        }

        // 读回调
        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            Log.e(TAG, "==>>onCharacteristicRead(系统返回读回调)");
        }

        // 发送：写回调
        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            Log.e(TAG, "==>>onCharacteristicWrite( )");
        }

        // 接收：设备返回数据到手机
        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            Log.e(TAG, "==>>onCharacteristicChanged( )");
        }

        // 连接：连接完成
        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            Log.e(TAG, "==>>onDescriptorRead( )");
            Log.e(TAG, "==>>onDescriptorRead( ) ==>>4、连接完毕 （onDescriptorRead，发送Discovered广播）");
            if (UUID_CONFIG_DESCRIPTOR.equals(descriptor.getUuid())) {

            }
        }

        // 连接：打开监听回调
        @Override
        public void onDescriptorWrite(BluetoothGatt bluetoothgatt, BluetoothGattDescriptor descriptor, int status) {
            Log.e(TAG, "==>>onDescriptorWrite( )");
            if (notifications != null && notifications.size() > 0) {
                NotificationInfo info = notifications.removeFirst();
                if (info.service.equals(UUID_SERVICE_BASE)) {
                    if (info.characteristic.equals(BluetoothCommandConstant.UUID_CHARACTERISTIC_8002)) {
                        Log.e(TAG, "| 已监听 : 8002(6006) （MAC : " + mBluetoothGatt.getDevice().getAddress() + "）");
                    } else if (info.characteristic.equals(BluetoothCommandConstant.UUID_CHARACTERISTIC_8004)) {
                        Log.e(TAG, "| 已监听 : 8004(6006)（MAC : " + mBluetoothGatt.getDevice().getAddress() + "）");
                    } else if (info.characteristic.equals(UUID_CHARACTERISTIC_8005)) {
                        Log.e(TAG, "| 已监听 : 8005(6006) （MAC : " + mBluetoothGatt.getDevice().getAddress() + "）");
                    } else if (info.service.equals(BluetoothCommandConstant.UUID_SERVICE_EXTEND)) {
                        Log.e(TAG, "| 已监听 : 8004(7006) （MAC : " + mBluetoothGatt.getDevice().getAddress() + "）");
                    }
                    if (notifications.size() > 0) {
                        openNotification(mBluetoothGatt, notifications);
                    } else {
                        Log.e("BluetoothLeService", "**************************************************");
                        Log.e(TAG, "==>>3、已打开所有监听 （准备readDescriptor）");
                        mBluetoothGatt.readDescriptor(descriptor);
                    }
                }
            } else {
                Log.e(TAG, "");
                if (mBluetoothGatt != null) {
                    mBluetoothGatt.close();
                    mBluetoothGatt.disconnect();
                    try {
                        BluetoothDevice bluetoothDevice = mBluetoothGatt.getDevice();
                        Method removeBondMethod = BluetoothDevice.class.getMethod("removeBond");
                        removeBondMethod.invoke(bluetoothDevice);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    public boolean connect(String macAddress) {
        BluetoothDevice bluetoothDevice = null;
        Log.d("connect--", "--");
        try {
            Log.d("connect--", "--根据Mac在蓝牙适配器中获取该对象");
            bluetoothDevice = mBluetoothAdapter.getRemoteDevice(macAddress);
        } catch (IllegalArgumentException e) {
            Log.d("connect--", "--根据Mac在蓝牙出现异常");
            e.printStackTrace();
            return false;
        }
        if (mBluetoothAdapter == null || bluetoothDevice == null) {
            Log.d("connect--", "--根据Mac获取蓝牙对象失败，返回");
            return false;
        } else {
            Log.d("connect--", "--根据Mac在蓝牙获取成功！！");
            if (bluetoothDevice != null && !TextUtils.isEmpty(macAddress)) {
                Log.d("connect--", "--");
                Set<BluetoothDevice> bluetoothDeviceSet = mBluetoothAdapter.getBondedDevices();
                boolean isPair = false;
                for (BluetoothDevice pb : bluetoothDeviceSet) {
                    Log.e(TAG, "--------deviceName : " + pb.getName());
                    if (pb.getAddress().equals(macAddress)) {
                        Log.d(TAG, "--------pb.getAddress() = " + pb.getAddress() + ", macAddress = " + macAddress + "");
                        Log.d(TAG, "--------deviceName : 需要进行配对！= " + pb.getName());
                        isPair = true;
                    }
                }
//                if (bluetoothDevice.getBondState() == BluetoothDevice.BOND_NONE) {
//                    Log.e("connect--", "--进行配对");
//                    try {
//                        Method method = BluetoothDevice.class.getMethod("createBond");
//                        method.invoke(bluetoothDevice);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (!isPair && Build.VERSION.SDK_INT >= 19) {
//                    Log.e("connect--", "--进行配对");
//                    bluetoothDevice.createBond();
//                }
                mBluetoothGatt = bluetoothDevice.connectGatt(BlueTooth28TService.this, Build.VERSION.SDK_INT < 19, gattCallback);
                return true;
            } else {
                Log.d("connect--", "--");
                return false;
            }
        }
    }


    private final BroadcastReceiver mGattUpdataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                if (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1) == BluetoothAdapter.STATE_OFF) {
                    Log.e(TAG, "蓝牙被人为断开了，不进行任何操作");
                    isConnect = false;
                }
            }
        }
    };

    private void openNotification(BluetoothGatt bluetoothGatt, LinkedList<NotificationInfo> notifications) {
        try {
            UUID serverUUID = notifications.getFirst().service;
            UUID characteristicUUID = notifications.getFirst().characteristic;
            BluetoothGattService service = bluetoothGatt.getService(serverUUID);
            BluetoothGattCharacteristic characteristic = service.getCharacteristic(characteristicUUID);
            bluetoothGatt.setCharacteristicNotification(characteristic, true);
            BluetoothGattDescriptor descriptor = characteristic.getDescriptor(BluetoothCommandConstant.UUID_CONFIG_DESCRIPTOR);
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            bluetoothGatt.writeDescriptor(descriptor);
        } catch (Exception e) {
            e.printStackTrace();
            /*mBluetoothGatt.disconnect();
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter != null) {
                LogUtil.i(TAG, "蓝牙连接打开监听有异常，关闭蓝牙中...");
                bluetoothAdapter.disable();
            }*/
        }
    }

    /**
     * 设置要开启的监听
     */
    public void setNotifications() {
        notifications.clear();
        checkDeviceSupportService();
        UUID the8003ServiceUUID = is8003Server7006 ? UUID_SERVICE_EXTEND : UUID_SERVICE_BASE;
        notifications.addLast(new NotificationInfo(UUID_SERVICE_BASE, UUID_CHARACTERISTIC_8002));
        if (isSupportRemoteService) {
            notifications.addLast(new NotificationInfo(the8003ServiceUUID, UUID_CHARACTERISTIC_8004));
        }
//        if (isSupport8005TransparentPassage) {
//            notifications.addLast(new NotificationInfo(UUID_SERVICE_BASE, UUID_CHARACTERISTIC_8005));
//        }
    }

    /**
     * 检查设备支持的服务
     */
    private void checkDeviceSupportService() {
        List<BluetoothGattService> services = mBluetoothGatt.getServices();
        if (services != null && services.size() > 0) {
            Log.e(TAG, "BluetoothLeService  **************************************");
            Log.e(TAG, "BluetoothLeService  | 检查监听 ");
            for (BluetoothGattService s : services) {
                Log.e(TAG, "BluetoothLeService | 服务 （" + s.getUuid().toString() + "）");
                List<BluetoothGattCharacteristic> characteristics = s.getCharacteristics();
                //6006 蓝牙服务
                if (s.getUuid().toString().equals(UUID_SERVICE_BASE.toString())) {
                    if (checkIsContainCharacteristic(characteristics, UUID_CHARACTERISTIC_8003)) {
                        Log.e(TAG, "BluetoothLeService | 支持 : 8003/8004(6006) 远程通道 ");
                        isSupportRemoteService = true;
                        is8003Server7006 = false;
                    } else if (checkIsContainCharacteristic(characteristics, UUID_CHARACTERISTIC_8005)) {
                        Log.e(TAG, "BluetoothLeService | 支持 : 8005(6006) 透传通道 ");
                    }
                }
            }
            Log.e(TAG, "BluetoothLeService | 打开监听");
        }
    }

    private boolean checkIsContainCharacteristic(List<BluetoothGattCharacteristic> characteristicList, UUID uuid) {
        if (characteristicList != null && characteristicList.size() > 0) {
            for (BluetoothGattCharacteristic characteristic : characteristicList) {
                if (characteristic.getUuid().toString().equals(uuid.toString())) {
                    return true;
                }
            }
        }
        return false;
    }

    public class NotificationInfo {
        public UUID service;
        public UUID characteristic;

        NotificationInfo(UUID service, UUID characteristic) {
            this.service = service;
            this.characteristic = characteristic;
        }
    }
}