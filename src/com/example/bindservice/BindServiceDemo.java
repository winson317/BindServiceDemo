package com.example.bindservice;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.startservice.R;

public class BindServiceDemo extends Activity {
	
	Button bind, unbind, getServiceStatus;
	BindService.MyBinder binder; //������������Service��IBinder����
	//����һ��ServiceConnection����
	private ServiceConnection conn = new ServiceConnection() 
	{
		//����Activity��Service�Ͽ�����ʱ�ص��÷���
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			System.out.println("Service Disconnected");
		}
		
		//����Activity��Service���ӳɹ�ʱ�ص��÷���
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			System.out.println("Service is Connected");
			binder = (BindService.MyBinder) service; //��ȡService��onBind���������ص�MyBinder����
		}
	};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        bind = (Button)findViewById(R.id.bind);
        unbind = (Button)findViewById(R.id.unbind);
        getServiceStatus = (Button)findViewById(R.id.getServiceStatus);
        
        final Intent intent = new Intent(); //��������service��Intent
        intent.setAction("com.example.service.BIND_SERVICE"); //ΪIntent����Action����
        bind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View source) {
				// TODO Auto-generated method stub
				bindService(intent, conn, Service.BIND_AUTO_CREATE); //��ָ��Service
			}
		});
        
        unbind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View source) {
				// TODO Auto-generated method stub
				unbindService(conn); //�����Service
			}
		});
        
        getServiceStatus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View source) {
				//��ȡ������ʾService��countֵ
				Toast.makeText(BindServiceDemo.this, "Service��countֵΪ��" + binder.getCount(), Toast.LENGTH_LONG).show();
			}
		});
        
    }

}
