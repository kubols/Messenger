package pl.edu.uksw.prir.messenger;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

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

public class RspHandler  {
	private byte[] rsp = null;

	public synchronized boolean handleResponse(byte[] rsp) {
		this.rsp = rsp;
		this.notify();
		return true;
	}

	public synchronized void waitForResponse() {
		while(this.rsp == null) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}

		System.out.println(new String(this.rsp));


	}
}
