package pl.edu.uksw.prir.messenger;

/**
 *
 * @author Wojciech Pokora
 * @author Jakub Pawlak
 * @author Patryk Szewczyk
 * @author Katarzyna Wiater
 * @author Agnieszka Musiał
 * @author Michał Darkowski
 *
 */
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;
import android.os.*;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.view.View;


public class EchoWorker implements Runnable {
	private List queue = new LinkedList();
	ServerDataEvent hData;
	public Context context;

	EchoWorker(Context mcontext){
		this.context = mcontext;
	}





	public void processData(NioServer server, SocketChannel socket, byte[] data, int count) {
		byte[] dataCopy = new byte[count];
		System.arraycopy(data, 0, dataCopy, 0, count);
		synchronized(queue) {
			queue.add(new ServerDataEvent(server, socket, dataCopy));
			queue.notify();
		}
	}

	public void run() {
		ServerDataEvent dataEvent;

		while(true) {
			// Wait for data to become available
			synchronized(queue) {
				while(queue.isEmpty()) {
					try {
						queue.wait();
					} catch (InterruptedException e) {
					}
				}
				dataEvent = (ServerDataEvent) queue.remove(0);
			}

			hData = dataEvent;
			new Handler(Looper.getMainLooper()).post(new Runnable() {
				@Override
				public void run() {

					Intent intent = new Intent();
					intent.putExtra("result", new String(hData.data));
					intent.setAction("pl.edu.uksw.prir.messenger.Chat");
					context.sendBroadcast(intent);
					Log.i("cos","wysylam broadcast");
				}
			});


			// Return to sender
			byte[] a = "cos Server odebral".getBytes();
			dataEvent.server.send(dataEvent.socket,a); // dataEvent.data);
		}
	}
}
