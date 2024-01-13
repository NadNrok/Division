package ua.com.fm.division;

import java.util.Arrays;
import java.util.Objects;

public class Division {

	private final int dividend;
	private final int divisor;
	private final char[] numbers;
	private final int result;
	private final int finalRemainder;

	public Division(int dividend, int divisor) {
		if (divisor == 0) {
			throw new IllegalArgumentException("0 can not be selected as a divisor");
		}
		this.dividend = dividend;
		this.divisor = divisor;

		numbers = String.valueOf(Math.abs(dividend)).toCharArray();
		result = dividend / divisor;
		finalRemainder = dividend % divisor;
	}

	public void performDivision() {
		int currentRemainder = finalRemainder;

		if (currentRemainder == 0 || Math.abs(divisor) > Math.abs(dividend)) {
			printDivisionHeader();
			printLastRemainder(currentRemainder,
					getDigitLength(Math.abs(divisor)) - getDigitLength(currentRemainder) + 1);
		} else {
			int dividendDigit = Character.getNumericValue(numbers[0]);
			int digitCount = 1;

			while (dividendDigit < Math.abs(divisor)) {
				dividendDigit = dividendDigit * 10 + Character.getNumericValue(numbers[digitCount++]);
			}

			currentRemainder = dividendDigit % divisor * 10;
			printDivisionHeader();
			performMainDivision(currentRemainder, digitCount);
		}
	}

	private void performMainDivision(int currentRemainder, int digitCount) {
		int spaces = 0;

		for (int i = 1; i < getDigitLength(Math.abs(result)); i++) {
			int partialDividend = calculatePartialDividend(currentRemainder, digitCount);
			printPartialDividend(spaces, partialDividend);

			currentRemainder = partialDividend % divisor;

			int quotientDigit = partialDividend - currentRemainder;
			printQuotient(spaces, quotientDigit, partialDividend);

			spaces += getAbsoluteLengthDifference(partialDividend, quotientDigit);
			printHyphen(spaces, quotientDigit);

			currentRemainder *= 10;
			spaces++;
			digitCount++;
		}

		printLastRemainder(currentRemainder / 10, spaces + 1);
	}

	private int calculatePartialDividend(int currentRemainder, int digitCount) {
		int dividendDigit = Character.getNumericValue(numbers[digitCount]);
		return dividendDigit + currentRemainder;
	}

	private void printPartialDividend(int spaces, int partialDividend) {
		spacesPrintOut(spaces);
		System.out.println("_" + partialDividend);
	}

	private void printQuotient(int spaces, int quotientDigit, int partialDividend) {
		spacesPrintOut(spaces + (getDigitLength(quotientDigit) == 1 ? getDigitLength(partialDividend) : 1));
		System.out.println(quotientDigit);
	}

	private void printHyphen(int spaces, int quotient) {
		spacesPrintOut(spaces);
		System.out.print(" ");
		hyphenPrintOut(quotient);
		System.out.println();
	}

	private void printLastRemainder(int currentRemainder, int digitCount) {
		spacesPrintOut(digitCount);
		System.out.println(currentRemainder);
	}

	private void printDivisionHeader() {
		printDividendLine();
		printDivisorLine();
		printResultLine();
	}

	private void printDividendLine() {
		if (dividend < 0) {
			System.out.print(dividend);
		} else {
			System.out.print("_" + dividend);
		}

		if (Math.abs(divisor) > Math.abs(dividend)) {
			spacesPrintOut(getAbsoluteLengthDifference(dividend, divisor));
		}

		System.out.println("|" + divisor);
	}

	private void printDivisorLine() {
		if (divisor > 0) {
			System.out.print(" " + divisor);
		} else {
			System.out.print(divisor);
		}

		if (Math.abs(dividend) > Math.abs(divisor)) {
			spacesPrintOut(getAbsoluteLengthDifference(dividend, divisor));
		}

		System.out.print("|");

		if (getDigitLength(result) < getDigitLength(divisor)) {
			hyphenPrintOut(divisor);
		} else {
			hyphenPrintOut(result);
		}

		System.out.println();
	}

	private void printResultLine() {
		System.out.print(" ");
		hyphenPrintOut(Math.abs(divisor));

		if (Math.abs(dividend) > Math.abs(divisor)) {
			spacesPrintOut(getAbsoluteLengthDifference(dividend, divisor));
		}

		System.out.println("|" + result);
	}

	private void hyphenPrintOut(int condition) {
		for (int i = 0; i < getDigitLength(condition); i++) {
			System.out.print('-');
		}
	}

	private void spacesPrintOut(int num) {
		for (int i = 0; i < num; i++) {
			System.out.print(" ");
		}
	}

	private int getDigitLength(int digit) {
		return String.valueOf(digit).length();
	}

	private int getAbsoluteLengthDifference(int number1, int number2) {
		return Math.abs(getDigitLength(Math.abs(number1)) - getDigitLength(Math.abs(number2)));
	}

	@Override
	public String toString() {
		return "Divider{" + "dividend=" + dividend + ", divisor=" + divisor + ", numbers=" + Arrays.toString(numbers)
				+ ", result=" + result + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Division divider = (Division) o;

		return dividend == divider.dividend && divisor == divider.divisor && result == divider.result
				&& Arrays.equals(numbers, divider.numbers);
	}

	@Override
	public int hashCode() {
		int hashcodeResult = Objects.hash(dividend, divisor, result);
		hashcodeResult = 31 * hashcodeResult + Arrays.hashCode(numbers);

		return hashcodeResult;
	}
}
