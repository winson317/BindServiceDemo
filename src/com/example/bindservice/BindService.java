package com.example.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BindService extends Service {
	
	private int count;
	private boolean quit;
	
	private MyBinder binder = new MyBinder(); //����onBinder���������صĶ���
	//ͨ���̳�Binder��ʵ��IBinder��
	public class MyBinder extends Binder
	{
		public int getCount() {
			// TODO Auto-generated method stub
			return count;  //��ȡService������״̬: count
		}
	}
	
	//����ʵ�ֵķ������󶨸�Serviceʱ�ص��÷���
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("Service is Binded");
		return binder;  //����IBinder����
	}

	//Service������ʱ�ص��÷���
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		System.out.println("Service is Created");
		
		//����һ���̣߳���̬���޸�count ��״ֵ̬
		new Thread()
		{
			@Override
			public void run() 
			{
				while (!quit)
				{
					try
					{
						Thread.sleep(1000);
					}
					catch (InterruptedException e)
					{
						
					}
					count++;
				}
			}
			
		}.start();
	}

	//Service���Ͽ�����ʱ�ص��÷���
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("Service is Unbinded");
		return true;
	}
	
	//Service���ر�֮ǰ�ص��÷���
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.quit = true;
		System.out.println("Service is Destroyed");
	}
}
