#Bank Design

###Opening a New Account
![New Account Diagram](https://raw.github.com/usc-csci201-fall2013/team20/master/docs/InteractionDiagrams/BankNew.png?token=3290221__eyJzY29wZSI6IlJhd0Jsb2I6dXNjLWNzY2kyMDEtZmFsbDIwMTMvdGVhbTIwL21hc3Rlci9kb2NzL0ludGVyYWN0aW9uRGlhZ3JhbXMvQmFua05ldy5wbmciLCJleHBpcmVzIjoxMzg2MDkyNjg1fQ%3D%3D--08b1310e1d4e1c95829357f684c05de51e6230d5)
###Depositing into an Account
![Bank Deposit Diagram](https://raw.github.com/usc-csci201-fall2013/team20/master/docs/InteractionDiagrams/BankDeposit.png?token=3290221__eyJzY29wZSI6IlJhd0Jsb2I6dXNjLWNzY2kyMDEtZmFsbDIwMTMvdGVhbTIwL21hc3Rlci9kb2NzL0ludGVyYWN0aW9uRGlhZ3JhbXMvQmFua0RlcG9zaXQucG5nIiwiZXhwaXJlcyI6MTM4NjA5MjU3NH0%3D--42197ac00d98717dbd9adaf2d848b92965607a07)
###Withdrawing from an Account
![Bank Withdrawal Diagram](https://raw.github.com/usc-csci201-fall2013/team20/master/docs/InteractionDiagrams/BankWidthdraw.png?token=3290221__eyJzY29wZSI6IlJhd0Jsb2I6dXNjLWNzY2kyMDEtZmFsbDIwMTMvdGVhbTIwL21hc3Rlci9kb2NzL0ludGVyYWN0aW9uRGlhZ3JhbXMvQmFua1dpZHRoZHJhdy5wbmciLCJleHBpcmVzIjoxMzg2MDkyNzM1fQ%3D%3D--e83ca819d6a1b9b7e62753ac996307193988dc1d)
###Processing a New Loan
![New Loan Diagram](https://raw.github.com/usc-csci201-fall2013/team20/master/docs/InteractionDiagrams/BankLoan.png?token=3290221__eyJzY29wZSI6IlJhd0Jsb2I6dXNjLWNzY2kyMDEtZmFsbDIwMTMvdGVhbTIwL21hc3Rlci9kb2NzL0ludGVyYWN0aW9uRGlhZ3JhbXMvQmFua0xvYW4ucG5nIiwiZXhwaXJlcyI6MTM4NjA5MjY2Mn0%3D--94847223cb14e1aa42b336f28288983c712e9717)
###Paying Off a Loan
![Paying a Loan Diagram](https://raw.github.com/usc-csci201-fall2013/team20/master/docs/InteractionDiagrams/BankPayLoan.png?token=3290221__eyJzY29wZSI6IlJhd0Jsb2I6dXNjLWNzY2kyMDEtZmFsbDIwMTMvdGVhbTIwL21hc3Rlci9kb2NzL0ludGVyYWN0aW9uRGlhZ3JhbXMvQmFua1BheUxvYW4ucG5nIiwiZXhwaXJlcyI6MTM4NjA5Mjc3MX0%3D--1f72a5f8f15afa5e5173b1c1a9fc729df2c15af0)

# Bank (Manager) Agent

<pre><code>
int vault = 10000;
int final vaultMinimum = 1000;
//Initialize restaurant accounts
</code></pre>

# BankCustomer Agent

<pre><code>
//people request loans when desired cash is less than account balance
</code></pre>

### Data

<pre><code>
enum CustomerDesire {none, withdraw, deposit, wantLoan, closeLoan, openAccount, robBank}

TellerAgent myTeller;
GuardAgent guard1;

int cash;
int accountNum;
int accountBalance;
int desiredLoanAmount;
int desiredCashAmount;
int depositAmount;
int loan; 	
CustomerDesire desire = none;
</code></pre>

### Messages

<pre><code>
HereIsYourMoney(int amount) {
	cash += amount;
}

HereIsNewAccount (int accountNum) {
	accountNums.add(accountNum);
}

Bankrupt() {
	guard1.LeavingBank(myTeller);
	//animate leaving bank
}

InsufficentFunds(){
	guard1.LeavingBank(myTeller);
	//animate leaving bank...maybe go get more money somehow?
}

DepositReceived() {
	guard1.LeavingBank(myTeller);
	//animate leaving bank
}

YourLoanWasApproved() {
	desire = withdraw;
}

YourLoanWasDenied(int amount) {
	//decide whether or not to request another loan
}

LoanClosed() {
guard1.LeavingBank(myTeller);
	//animate leaving bank
}	

CaughtYou() {
}

GotAway() {
	cash += vault;
}
</code></pre>
	
### Scheduler

<pre><code>
if (desire == withdraw)
	WithdrawCash();

if (desire == deposit)
	DepositCash();

if (desire == wantLoan)
	RequestLoan();

if (desire == closeLoan)
	PayOffLoan();

if (desire == openAccount)
	OpenAccount();

if (desire == robBank)
	RobBank();
</code></pre>

### Actions

<pre><code>
WithdrawCash() {
	myTeller.INeedMoney(desiredCashAmount,accountNum);
}

DepositCash () {
	myTeller.HereIsMyDeposit(depositAmount, accountNum);
}

RequestLoan() {
	desiredLoanAmount = 10*desiredCashAmount;
	myTeller.INeedALoan(desiredLoanAmount, accountNum);
}

PayOffLoan() {
	cash -= loan;
	myTeller.PayingOffLoan(loan, accountNum);
}

OpenAccount () {
	myTeller.WantNewAccount(this);
}

RobBank () {
	//Animation();
	guard1.RobbingBank(this);
}
</code></pre>

# Teller
### Data

<pre><code>
enum AccountState {neutral, new, waiting, depositing, withdrawing, requestingLoan, closingLoan, loanApproved, loanDenied, closingLoan}

class Account {	
	CustomerAgent customer;
	int accountNum; 		//the hash key
	double loan = 0;
	double balance = 0;
	double creditScore = 0;
	double processingMoney = 0;
	AccountState state = new;
}

List<Account> accounts;
BankAgent myBank;
LoanOfficerAgent myLoaner;
int final balanceMinimum = 5;
</code></pre>

### Messages

<pre><code>
WantNewAccount (BankCustomerAgent cust1) {
	accounts.add(new Account(cust1));
}

RestaurantDepositMoney(int accountNum) {
	Account correct = FindAccount(accountNum);
	accounts.remove(correct);
}

INeedMoney(int desiredAmount, int accountNum) {
	Account correct = FindAccount (accountNum);
	correct.processingMoney = desiredAmount;
	correct.state = withdrawing;
}

HereIsMyDeposit(int amount, int accountNum) {
	Account correct = FindAccount (accountNum);
	correct.processingMoney = amount;
	correct.state = depositing;
}

INeedALoan(int desiredLoan, int accountNum) {
	Account correct = FindAccount (accountNum);
	correct.processingMoney = desiredLoan;
	correct.state = requestingLoan;
}

PayingOffLoan(int loan, int accountNum) {
	Account correct = FindAccount (accountNum);
	correct.processingMoney = loan;
	correct.state = closingLoan;
}

ThisLoanApproved(Account account1) {
	account1.state = loanApproved;
}

ThisLoanDenied (Account account1, int possibleLoan) {
	account1.state = loanDenied;
	account1.processingMoney = possibleLoan;
}
</code></pre>

### Scheduler

<pre><code>
If (∃ Account ∈  accounts | Account.state == new)
	OpenAccount(Account);

If (∃ Account ∈  accounts | Account.state == withdrawing)
	WithdrawMoney(Account);

If (∃ Account ∈  accounts | Account.state == depositing)
	DepositMoney(Account);

If (∃ Account ∈  accounts | Account.state == requestingLoan)
	RequestLoan(Account);

If (∃ Account ∈  accounts | Account.state == closingLoan)
	CloseLoan(Account);

If (∃ Account ∈  accounts | Account.state == loanApproved)
	ApproveLoan(Account);

If (∃ Account ∈  accounts | Account.state == loanDenied)
	DenyLoan(Account);

If (∃ Account ∈  accounts | Account.state == closingLoan)
	CloseLoan(Account)
</code></pre>

### Actions

<pre><code>
FindAccount (int accountNum) {
	
}

OpenAccount (Account account1) {
	//generate key (accountNum)
	account1.customer.HereIsNewAccount(account1.accountNum);
	account1.state = neutral;
}

WithdrawMoney(Account account1) {
	if (account1.balance > (account1.processingMoney + balanceMinimum) && (account1.processingMoney < (vault - vaultMinimum))) {
		myBank.vault -= account1.desiredAmount;
		account1.state = neutral;
		account1.customer.HereIsYourMoney(account1.transferringMoney);
	}
	else if (account.processingMoney > (vault-vaultMinimum))
		account1.customer.Bankrupt();
	else
		account1.customer.InsufficentFunds();

	account1.processingMoney = 0;
	account1.state = neutral;
}

DepositMoney(Account account1) {
	vault += account1.processingMoney;
	account1.balance +=  account1.processingMoney;
	account1.creditScore += account1.processingMoney/10;	//every time you deposit money, your credit goes up…the bank can trust that you have money
	account1.customer.DepositReceived();
	account1.state = neutral;
}

RequestLoan (Account account1) {
	myLoaner.IsLoanApproved(account1, this);
	account1.state = waiting;
}

CloseLoan (Account account1) {	
	account1.creditScore += account1.loan/10;
	account1.loan = 0;
	account1.state = neutral;
	account1.customer.LoanClosed();
}

ApproveLoan (Account account1) {
	account1.state = neutral;
	account1.loan = account1.processingMoney;
	account1.balance += account1.loan;
	account1.customer.YourLoanWasApproved();
}

DenyLoan (Account account1) {
	account1.state = neutral;	
	account1.customer.YourLoanWasDenied(account1.processingMoney);	//loan denied, but given your credit score you can have a loan of size (processingMoney)
}
</code></pre>

# Loan Officer
### Data

<pre><code>
enum LoanState {requesting, open, closed}
class Loan {
	Account account1;
	LoanState state = requesting;
	Teller teller1;
}
List<Loan> loans;
</code></pre>

### Messages

<pre><code>
IsLoanApproved(Account account1, Teller t1) {
	loans.add( new Loan(account1, t1));
}
</code></pre>


### Scheduler

<pre><code>
If (∃ Loan ∈  loans | Loan.state == requesting)
	ProcessLoan(Loan);
</code></pre>

### Actions

<pre><code>
ProcessLoan (Loan loan1) {

	int loanAmount = loan1.account1.processingMoney;
	if (vault <=  vaultMinimum + loanAmount) {
		loan1.teller1.ThisLoanDenied(loan1.account1, 0);
	}

	if (loanAmount > ((loan1.account1.creditScore * 10);		//You are allowed a loan 10 times your credit score
		loan1. teller1.ThisLoanApproved(loan1.account1);

	else
		loan1.teller1.ThisLoanDenied(loan1.account1, loan1.account1.creditScore*10); 
}
</code></pre>

# Guard

### Data

<pre><code>
List<CustomerAgent> customers;
List<CustomerAgent> robbers;
class MyTeller {
	enum tellerState {available, busy};
	TellerAgent tell1;
}
List<MyTeller> tellers;		//initialized at beginning
</code></pre>

### Messages

<pre><code>
RobbingBank(CustomerAgent cust1) {
	robbers.add(cust1);
}

ArrivedAtBank(CustomerAgent c1) {
	customers.add(c1);
}

LeavingBank (TellerAgent t1) {
	MyTeller correct = findTeller(t1);
	correct.state = available;
}
</code></pre>

### Scheduler

<pre><code>
If (∃ CustomerAgent ∈  robbers) 
	catchRobber(CustomerAgent);

If (∃ CustomerAgent ∈  customers)
assignToTeller(CustomerAgent); 
</code></pre>

### Actions

<pre><code>
findTeller (TellerAgent t1) {

}

catchRobber(CustomerAgent robber1) {
//95% chance Robber is caught, 5% he gets away;
	if (caught)
		robber1.CaughtYou();
	if (!caught)
		robber1.GotAway();	
}

assignToTeller(CustomerAgent cust1) {
	for (MyTeller teller1: tellers) {
		if (teller1.state = available) {
			cust1.GoToTeller(teller1.tell1);
			teller1.state = busy;
		}
	}
}
</code></pre>