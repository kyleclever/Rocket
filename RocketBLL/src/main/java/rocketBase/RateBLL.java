package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	private static double OneRate;

	static double getRate(int GivenCreditScore) throws RateException{
		// TODO - RocketBLL RateBLL.getRate - make sure you throw any exception

		// Call RateDAL.getAllRates... this returns an array of rates
		// write the code that will search the rates to determine the
		// interest rate for the given credit score
		// hints: you have to sort the rates... you can do this by using
		// a comparator... or by using an OrderBy statement in the HQL

		// exception.. if so, send the exception back to the client

		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();

		if (GivenCreditScore < rates.get(0).getiMinCreditScore()) {
		 System.out.println("Sorry, you have a too low credit score");
		 throw new RateException(null);
		 }
		
		

		for (RateDomainModel r : rates) {
			if (GivenCreditScore >= r.getiMinCreditScore()) {
				OneRate = r.getdInterestRate();
			}
		}
	
	// TODO - RocketBLL RateBLL.getRate
	// obviously this should be changed to return the determined rate
		return OneRate;
	}

	

	// TODO - RocketBLL RateBLL.getPayment
	// how to use:
	// https://poi.apache.org/apidocs/org/apache/poi/ss/formula/functions/FinanceLib.html
	public static double getPayment(double r, double n, double p, double f, boolean t) {
		return FinanceLib.pmt(r, n, p, f, t);
	}
}
