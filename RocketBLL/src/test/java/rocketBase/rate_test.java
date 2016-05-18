package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;

public class rate_test {

	// TODO - RocketBLL rate_test
	// Check to see if a known credit score returns a known interest rate

	// TODO - RocketBLL rate_test
	// Check to see if a RateException is thrown if there are no rates for a
	// given
	// credit score

	RateBLL rateBill = new RateBLL();

	@Test
	public void vaildTest() {
		int subject1 = 600;
		int subject2 = 622;
		int subject3 = 650;
		int subject4 = 666;
		int subject5 = 700;
		int subject6 = 720;
		int subject7 = 800;
		int subject8 = 888;	
			

		try {
			
//			assertTrue(RateBLL.getRate(subject1)== 5);
//			assertTrue(RateBLL.getRate(subject2)== 5);
//			assertTrue(RateBLL.getRate(subject3)== 4.5);
//			assertTrue(RateBLL.getRate(subject4)== 4.5);
//			assertTrue(RateBLL.getRate(subject5)== 4);
//			assertTrue(RateBLL.getRate(subject6)== 4);
//			assertTrue(RateBLL.getRate(subject7)== 3.5);
//			assertTrue(RateBLL.getRate(subject8)== 3.5);						
			System.out.println("The interetset rate of subject1 is"+RateBLL.getRate(subject1));
			System.out.println("The interetset rate of subject2 is"+RateBLL.getRate(subject2));
			System.out.println("The interetset rate of subject3 is"+RateBLL.getRate(subject3));
			System.out.println("The interetset rate of subject4 is"+RateBLL.getRate(subject4));
			System.out.println("The interetset rate of subject5 is"+RateBLL.getRate(subject5));
			System.out.println("The interetset rate of subject6 is"+RateBLL.getRate(subject6));
			System.out.println("The interetset rate of subject7 is"+RateBLL.getRate(subject7));
			System.out.println("The interetset rate of subject8 is"+RateBLL.getRate(subject8));
			
		} catch (RateException e) {
			e.printStackTrace();
		}
	}

	// Exception Test (Invalid credit score)
	@Test(expected = RateException.class)
	public void invaidTest() throws RateException {
		int subject9 = 555;
		int subject10 = 0;
		RateBLL.getRate(subject9);
		RateBLL.getRate(subject10);
	}
	
	@Test
	public void getPaymentTest(){
		// p: present value
		// f: future value
		// n: number of periods
		// y: payment (in each period)
		// r: rate
		//public static double getPayment(double r, double n, double p, double f, boolean t)
		
		double payment1 = RateBLL.getPayment(0.04/12,360,300000,0,false);		
		double rounded1 = -1*(Math.round(payment1*100.0)/100.0);
		
		double payment2 = RateBLL.getPayment(0.04/12,360,300000,0,true);		
		double rounded2 = -1*(Math.round(payment2*100.0)/100.0);
		
		System.out.println("getPayment Test1:" + rounded1);		
		System.out.println("getPayment Test2:" + rounded2);		
		//assertTrue(rounded1== 1432.25);
		//assertTrue(rounded2== 833.33);
	}

}
