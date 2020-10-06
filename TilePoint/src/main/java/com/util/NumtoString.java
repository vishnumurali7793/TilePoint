package com.util;

public class NumtoString {

	static String[] unitdo = { "", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine",
			" Ten", " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen", " Sixteen", " Seventeen", " Eighteen",
			" Nineteen" };
	static String[] tens = { "", "Ten", " Twenty", " Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty",
			" Ninety" };
	static String[] digit = { "", " Hundred", " Thousand", " Lakh", " Crore" };
	static int r;

	static // Count the number of digits in the input number
	int numberCount(int num) {
		int cnt = 0;

		while (num > 0) {
			r = num % 10;
			cnt++;
			num = num / 10;
		}

		return cnt;
	}

	static // Function for Conversion of two digit

	String twonum(int numq) {
		int numr, nq;
		String ltr = "";

		nq = numq / 10;
		numr = numq % 10;

		if (numq > 19) {
			ltr = ltr + tens[nq] + unitdo[numr];
		} else {
			ltr = ltr + unitdo[numq];
		}

		return ltr;
	}

	static // Function for Conversion of three digit

	String threenum(int numq) {
		int numr, nq;
		String ltr = "";

		nq = numq / 100;
		numr = numq % 100;

		if (numr == 0) {
			ltr = ltr + unitdo[nq] + digit[1];
		} else {
			ltr = ltr + unitdo[nq] + digit[1] + " and" + twonum(numr);
		}
		return ltr;

	}

	public static String convertAmountToWord(Double numberfrmuser) throws Exception {

		// Defining variables q is quotient, r is remainder

		int len, q = 0, r = 0;
		String ltr = " ";
		String Str = "Rupees";

		String arr[] = (numberfrmuser + "").split("\\.");
		int num = Integer.parseInt(arr[0] + "");
		if (num <= 0)

			Str = "Zero or Negative number not for conversion";

		while (num > 0) {

			len = numberCount(num);

			// Take the length of the number and do letter conversion

			switch (len)

			{
			case 8:
				q = num / 10000000;
				r = num % 10000000;
				ltr = twonum(q);
				Str = Str + ltr + digit[4];
				num = r;
				break;

			case 7:
			case 6:
				q = num / 100000;
				r = num % 100000;
				ltr = twonum(q);
				Str = Str + ltr + digit[3];
				num = r;
				break;

			case 5:
			case 4:

				q = num / 1000;
				r = num % 1000;
				ltr = twonum(q);
				Str = Str + ltr + digit[2];
				num = r;
				break;

			case 3:

				if (len == 3)
					r = num;
				ltr = threenum(r);
				Str = Str + ltr;
				num = 0;
				break;

			case 2:

				ltr = twonum(num);
				Str = Str + ltr;
				num = 0;
				break;

			case 1:
				Str = Str + unitdo[num];
				num = 0;
				break;
			default:

				num = 0;
				Str = "Exceeding Crore....No conversion";
				System.exit(1);

			}
			if (num == 0)
				Str = Str + " Only";
		}

		return Str;

	}

	public static void main(String[] arg) {
		try {
			System.out.println(convertAmountToWord(123548.54));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
