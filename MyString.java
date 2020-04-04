package MyClass;

public class MyString implements IStringBuffer {

	private int length;
	private int capacity;

	private final float times = 3.5f;

	public char[] value;

	public MyString() {
		capacity = 20;
		value = new char[capacity];
	}

	public MyString(String s) {
		this();

		// 扩容
		if (s.length() > capacity) {
			capacity = (int) (s.length() * times);
			value = new char[capacity];
		}

		char[] copy = s.toCharArray();
		System.arraycopy(copy, 0, value, 0, s.length());

		length = s.length();
	}

	public void append(char c) {
		this.insert(length, String.valueOf(c));
	}

	public void append(String str) {
		this.insert(length, str);
	}

	public void delete(int start) {
		this.delete(start, length - 1);
	}

	public void delete(int start, int end) {
		int len = length - end - 1;

		System.arraycopy(value, end + 1, value, start, len);

		length -= (end - start + 1);
	}

	public void insert(int pos, char b) {
		this.insert(pos, String.valueOf(b));
	}

	public void insert(int pos, String b) {

		// 边界判断
		if (pos < 0 || pos > length)
			return;

		// 扩容
		if (length + b.length() > capacity) {
			capacity = (int) ((length + b.length()) * times);
			char[] new_value = new char[capacity];
			System.arraycopy(value, 0, new_value, 0, length);
			value = new_value;
		}

		System.arraycopy(value, pos, value, pos + b.length(), length - pos);
		System.arraycopy(b.toCharArray(), 0, value, pos, b.length());

		length += b.length();
	}

	public int length() {
		return length;
	}

	public void reverse() {
		int mid = length / 2;
		for (int i = 0; i <= mid; i++) {
			char temp = value[i];
			value[i] = value[length - i - 1];
			value[length - i - 1] = temp;
		}
	}

	public String toString() {

		char[] temp = new char[length];

		System.arraycopy(value, 0, temp, 0, length);

		return new String(temp);
	}

	public static void main(String[] args) {
		MyString str = new MyString();
		StringBuffer sb = new StringBuffer();
		String s = new String("abcdefghij");

		long start = System.currentTimeMillis();

		for (int i = 0; i < 10000000; i++)
			sb.append(s);

		long end = System.currentTimeMillis();
		System.out.println(end - start + "ms");

		start = System.currentTimeMillis();

		for (int i = 0; i < 10000000; i++)
			str.append(s);

		end = System.currentTimeMillis();
		System.out.println(end - start + "ms");

	}
}

interface IStringBuffer {
	public void append(String str); // 追加字符串

	public void append(char c); // 追加字符

	public void insert(int pos, char b); // 指定位置插入字符

	public void insert(int pos, String b); // 指定位置插入字符串

	public void delete(int start); // 从开始位置删除剩下的

	public void delete(int start, int end); // 从开始位置删除结束位置-1

	public void reverse(); // 反转

	public int length(); // 返回长度
}