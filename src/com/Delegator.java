package com;

import java.util.LinkedList;
import java.util.Observable;

public class Delegator extends Observable {
	
	private ArduinoSchnittstelle arduinoSchnittstelle;
	private boolean verbunden = false;
	
	private Thread t = null;
	
	private LinkedList<String> datenpool = new LinkedList<String>();
	
	public boolean verbinden()
	{
		System.out.println("Versuche Verbindung aufzubauen..");
		verbunden = arduinoSchnittstelle.initialize();
		return  verbunden;
	}
	
	public Delegator() {
		arduinoSchnittstelle = new ArduinoSchnittstelle(this);
		
		t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true)
				{
					synchronized (datenpool) {
						if (datenpool.size() > 0)
						{
							String data = datenpool.pop();
							arduinoSchnittstelle.senden(data);
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}	
					}
					
				}
			}
		});
		
		t.start();
	
	}
	
	public void senden(String daten)
	{
		if (verbunden)
		{
			synchronized (datenpool) {
				datenpool.add(daten+"\r\n");
			}
		}
	}
	
	
	
	public void empfangen(String input)
	{
		System.out.println(input);
		notifyObservers(input);
	}
	
	
	public void trennen()
	{
		if (verbunden)
		{
			arduinoSchnittstelle.close();
		}
	}

}
