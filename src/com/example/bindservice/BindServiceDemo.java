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
	BindService.MyBinder binder; //保持所启动的Service的IBinder对象
	//定义一个ServiceConnection对象
	private ServiceConnection conn = new ServiceConnection() 
	{
		//当该Activity与Service断开链接时回调该方法
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			System.out.println("Service Disconnected");
		}
		
		//当该Activity与Service连接成功时回调该方法
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			System.out.println("Service is Connected");
			binder = (BindService.MyBinder) service; //获取Service的onBind方法所返回的MyBinder对象
		}
	};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        bind = (Button)findViewById(R.id.bind);
        unbind = (Button)findViewById(R.id.unbind);
        getServiceStatus = (Button)findViewById(R.id.getServiceStatus);
        
        final Intent intent = new Intent(); //创建启动service的Intent
        intent.setAction("com.example.service.BIND_SERVICE"); //为Intent设置Action属性
        bind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View source) {
				// TODO Auto-generated method stub
				bindService(intent, conn, Service.BIND_AUTO_CREATE); //绑定指定Service
			}
		});
        
        unbind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View source) {
				// TODO Auto-generated method stub
				unbindService(conn); //解除绑定Service
			}
		});
        
        getServiceStatus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View source) {
				//获取、并显示Service的count值
				Toast.makeText(BindServiceDemo.this, "Service的count值为：" + binder.getCount(), Toast.LENGTH_LONG).show();
			}
		});
        
    }

}
