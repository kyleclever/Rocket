package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {

	
	@Test
	public void test() {
		//TODO - RocketDAL rate_test
		//		Check to see if a known credit score returns a known interest rate
		
		//TODO - RocketDAL rate_test
		//		Check to see if a RateException is thrown if there are no rates for a given
		//		credit score
		
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		System.out.println ("Rates size: " + rates.size());
		assert(rates.size() > 0);
			
		for (RateDomainModel r : rates){
			System.out.println("The Interestrate is "+ r.getdInterestRate());
		}
		assert(1==1);
	}

}
