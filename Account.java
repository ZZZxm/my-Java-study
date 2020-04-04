package MyClass;

//�˻�
public class Account {

	protected double balance;// ���

	public Account(double init) {
		balance = init;
	}

	public Account() {
		this(0);
	}
	
	//�����˻����
	public double getBalance() {
		return balance;
	}
	
	//���
	public void deposit(double amt) {
		balance += amt;
		System.out.println("�ɹ����" + amt + "Ԫ");
	}
	
	//ȡ��
	public void withdraw(double amt) throws OverdraftException {

		if (amt > balance) {
			System.out.println("ȡ��ʧ��");
			throw new OverdraftException("�˻���͸֧", amt - balance);
		} else {
			balance -= amt;
			System.out.println("�ɹ����" + amt + "Ԫ");
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

//֧Ʊ�˻�������͸֧���
class CheckingAccount extends Account {

	protected double overdraftProtection;// ͸֧���

	public CheckingAccount(double init) {
		this(init, 0);
	}

	public CheckingAccount(double init, double protect) {
		super(init);
		overdraftProtection = protect;
	}

	//ȡ��
	@Override
	public void withdraw(double amt) throws OverdraftException {

		if (amt > balance + overdraftProtection) {
			System.out.println("ȡ��ʧ��");
			throw new OverdraftException("֧Ʊ�˻���͸֧", amt - balance - overdraftProtection);
		} else {
			balance -= amt;
			System.out.println("�ɹ����" + amt + "Ԫ");
		}
	}
}

//͸֧�쳣
class OverdraftException extends Exception {

	private Double deficit;// ����

	public OverdraftException() {

	}

	public OverdraftException(String msg, double deficit) {
		super(msg);

		this.deficit = deficit;
	}
	
	
	public double getDeficit() {
		System.out.println("��͸֧" + deficit + "Ԫ");
		return deficit;
	}

	@Override
	public String getMessage() {
		return super.getMessage() + deficit.toString() + "Ԫ";
	}
}
