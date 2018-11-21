package com.avater.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.avater.myapplication.interfaces.ScanDeviceInterface;
import com.avater.myapplication.service.Bluetooth28TUtils;
import com.avater.myapplication.utils.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Test28TActivity extends Activity implements ScanDeviceInterface, AdapterView.OnItemClickListener {

    @BindView(R.id.tittle)
    RelativeLayout tittle;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.btn_bind)
    Button btnBind;
    @BindView(R.id.btn_stop)
    Button btnStop;
    private List<Device> list;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test28_t);
        ButterKnife.bind(this);
        Bluetooth28TUtils.INSTANCE.initContext(this);
        Bluetooth28TUtils.INSTANCE.setScanDeviceListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.BLUETOOTH_PRIVILEGED}, 20170922);
                startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 416);
            }
        }
        Bluetooth28TUtils.INSTANCE.setScanDeviceFilter("BASIC");
        list = new ArrayList<>();
        adapter = new MyAdapter();
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }

    @OnClick({R.id.btn_search, R.id.btn_bind, R.id.btn_stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                Bluetooth28TUtils.INSTANCE.startScan();
                break;
            case R.id.btn_bind:
                Bluetooth28TUtils.INSTANCE.sendtSmallDatas(new byte[]{0x6E, 0x01, 0x03, 0x02, (byte) 0x8F});
                break;
            case R.id.btn_stop:
                Bluetooth28TUtils.INSTANCE.stopScan();
                break;
        }
    }

    @Override
    public void getDevice(String deviceName, String deviceAddress) {
        Logger.d("", "deviceName = " + deviceName + ",deviceAddress = " + deviceAddress);
        list.add(new Device(deviceName, deviceAddress));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Device device = list.get(position);
        Bluetooth28TUtils.INSTANCE.connectDevice(device.address);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(Test28TActivity.this).inflate(R.layout.text_items, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Device device = list.get(position);
            holder.name.setText(device.name + "");
            holder.address.setText(device.address + "");
            return convertView;
        }

    }

    class ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.address)
        TextView address;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private class Device {
        String name;
        String address;

        public Device(String name, String address) {
            this.name = name;
            this.address = address;
        }
    }
}
