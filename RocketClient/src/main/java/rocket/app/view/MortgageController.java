package rocket.app.view;

import java.awt.TextField;

import eNums.eAction;
import exceptions.RateException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;

	// TODO - RocketClient.RocketMainController

	// Create private instance variables for:
	// TextBox - txtIncome
	// TextBox - txtExpenses
	// TextBox - txtCreditScore
	// TextBox - txtHouseCost
	// ComboBox - loan term... 15 year or 30 year
	// Labels - various labels for the controls
	// Button - button to calculate the loan payment
	// Label - to show error messages (exception throw, payment exception)

	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpenses;
	@FXML
	private TextField txtCreditScore;
	@FXML
	private TextField txtHouseCost;
	@FXML
	private ComboBox<String> cmbTerm;
	@FXML
	private Button loanCalculation;
	@FXML
	private Label ibiMortgagePaymentLabel;
	@FXML
	private Label incomeLabel;
	@FXML
	private Label creditScoreLabel;
	@FXML
	private Label termLabel;
	@FXML
	private Label houseCostLabel;
	@FXML
	private Label expensesLabel;
	@FXML
	private Label errorMessageLabel;

	public int term;

	ObservableList<String> termList = FXCollections.observableArrayList("15 years", "30 years");

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void initialize() {
		cmbTerm.setItems(termList);
	}

	// assign the value of terms
	@FXML
	private void termChoice() {
		if (cmbTerm.getValue().equals("15 years")) {
			// 15 years- repay 15*12 periods
			term = 15 * 12;
		} else {
			// 30 years- repay 30*12 periods
			term = 30 * 12;
		}
	}

	// TODO - RocketClient.RocketMainController
	// Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event) throws RateException {
		Object message = null;
		// TODO - RocketClient.RocketMainController

		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		// TODO - RocketClient.RocketMainController
		// set the loan request details... rate, term, amount, credit score,
		// downpayment
		// I've created you an instance of lq... execute the setters in lq

		int creditScore = Integer.parseInt(txtCreditScore.getText());
		double rate = RateBLL.getRate(creditScore);
		double presentValue = Double.parseDouble(txtHouseCost.getText());
		//double futureValue = presentValue * Math.pow((1 + rate / 100), term);

		// set rate
		lq.setdRate(rate);

		// set term
		lq.setiTerm(term);

		// set amount
		lq.setdAmount(presentValue);

		// set credit score
		lq.setiCreditScore(creditScore);

		// set iDownPayment
		//double dPayment = RateBLL.getPayment(rate, term, presentValue, futureValue, false);
		//lq.setiDownPayment(dPayment / term);

		a.setLoanRequest(lq);

		// send lq as a message to RocketHub
		mainApp.messageSend(lq);
	}

	public void HandleLoanRequestDetails(LoanRequest lRequest) {
		// TODO - RocketClient.HandleLoanRequestDetails
		// lRequest is an instance of LoanRequest.
		// after it's returned back from the server, the payment (dPayment)
		// should be calculated.
		// Display dPayment on the form, rounded to two decimal places

		double income = Double.parseDouble(txtIncome.getText());
		double expense = Double.parseDouble(txtExpenses.getText());
		double downPayment = lRequest.getiDownPayment();

		// determining if one can afford
		if (downPayment >= income * 0.28 || downPayment >= (income * 0.36 - expense)) {
			errorMessageLabel.setText("Oh no! House Cost TOO High!!!");
		} else {
			String text = Double.toString(downPayment);
			ibiMortgagePaymentLabel.setText(text);
		}

	}
}
