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

	RateBLL test = new RateBLL();

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
			System.out.println(test.getRate(subject1));
			System.out.println(test.getRate(subject2));
			System.out.println(test.getRate(subject3));
			System.out.println(test.getRate(subject4));
			System.out.println(test.getRate(subject5));
			System.out.println(test.getRate(subject6));
			System.out.println(test.getRate(subject7));
			System.out.println(test.getRate(subject8));
		} catch (RateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Exception Test (Invalid credit score)
	@Test(expected = RateException.class)
	public void invaidTest() throws RateException {
		int subject9 = 555;
		int subject10 = 0;
		test.getRate(subject9);
		test.getRate(subject10);
	}

}
