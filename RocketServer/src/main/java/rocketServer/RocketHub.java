package rocketServer;

import java.io.IOException;

import exceptions.RateException;
import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;

public class RocketHub extends Hub {

	private RateBLL RateBLL = new RateBLL();

	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		System.out.println("Message Received by Hub");

		if (message instanceof LoanRequest) {
			resetOutput();

			LoanRequest lq = (LoanRequest) message;

			// TODO - RocketHub.messageReceived

			// You will have to:
			// Determine the rate with the given credit score (call
			// RateBLL.getRate)
			// If exception, show error message, stop processing
			// If no exception, continue
			// Determine if payment, call RateBLL.getPayment
			//
			// you should update lq, and then send lq back to the caller(s)
			
			try {

				double r = rocketBase.RateBLL.getRate(lq.getiCreditScore())/1200;
				double n = lq.getiTerm();
				double p = lq.getdAmount()-lq.getiDownPayment();
				double f = 0;
				boolean t = false;
				double PMT = rocketBase.RateBLL.getPayment(r, n, p, f, t);
				lq.setdPayment(Math.abs(PMT));				

			} catch (RateException e) {
				sendToAll(e);
			}

			sendToAll(lq);
		}
	}
}
