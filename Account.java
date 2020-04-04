package MyClass;

//账户
public class Account {

	protected double balance;// 存款

	public Account(double init) {
		balance = init;
	}

	public Account() {
		this(0);
	}
	
	//返回账户余额
	public double getBalance() {
		return balance;
	}
	
	//存款
	public void deposit(double amt) {
		balance += amt;
		System.out.println("成功存款" + amt + "元");
	}
	
	//取款
	public void withdraw(double amt) throws OverdraftException {

		if (amt > balance) {
			System.out.println("取款失败");
			throw new OverdraftException("账户已透支", amt - balance);
		} else {
			balance -= amt;
			System.out.println("成功提款" + amt + "元");
		}
	}

	public static void main(String[] args) {
		Account a = new Account(100);
		CheckingAccount ca = new CheckingAccount(100, 100);

		try {
			a.withdraw(400);
			a.deposit(10);
		} catch (OverdraftException e) {
			System.out.println(e.getMessage());
		}

		try {
			ca.deposit(100);
			ca.withdraw(1234.556);
		} catch (OverdraftException e) {
			System.out.println(e.getMessage());
		}
	}
}

//支票账户，具有透支额度
class CheckingAccount extends Account {

	protected double overdraftProtection;// 透支额度

	public CheckingAccount(double init) {
		this(init, 0);
	}

	public CheckingAccount(double init, double protect) {
		super(init);
		overdraftProtection = protect;
	}

	//取款
	@Override
	public void withdraw(double amt) throws OverdraftException {

		if (amt > balance + overdraftProtection) {
			System.out.println("取款失败");
			throw new OverdraftException("支票账户已透支", amt - balance - overdraftProtection);
		} else {
			balance -= amt;
			System.out.println("成功提款" + amt + "元");
		}
	}
}

//透支异常
class OverdraftException extends Exception {

	private Double deficit;// 赤字

	public OverdraftException() {

	}

	public OverdraftException(String msg, double deficit) {
		super(msg);

		this.deficit = deficit;
	}
	
	
	public double getDeficit() {
		System.out.println("已透支" + deficit + "元");
		return deficit;
	}

	@Override
	public String getMessage() {
		return super.getMessage() + deficit.toString() + "元";
	}
}
