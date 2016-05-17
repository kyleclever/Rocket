package rocketServer;

import java.io.IOException;

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
			// message.appendText(message + "\n");
			try {

				double r = rocketBase.RateBLL.getRate(lq.getiCreditScore());
				double n = lq.getiTerm();
				double p = lq.getdAmount();
				double f = p * Math.pow((1 + r / 100), n);
				boolean t = false;
				double totalRepayment = rocketBase.RateBLL.getPayment(r, n, p, f, t);
				lq.setdPayment(totalRepayment);

			} catch (Exception e) {
				// System.out.println("Error!");
				// message.appendText("Error-Invalid Input");
				sendToAll("Error");
			}

			sendToAll(lq);
		}
	}
}
