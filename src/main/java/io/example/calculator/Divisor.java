package io.example.calculator;

public class Divisor {
    public static final String DIVIDED_ZERO_EXCEPTION_MESSAGE = "0으로 나눌 수 없습니다.";

    private final int value;

    private Divisor(int value) {
        validateDivisor(value);
        this.value = value;
    }

    public static Divisor from(int value) {
        return new Divisor(value);
    }

    private static void validateDivisor(int value) {
        if (isZeroDivisor(value)) {
            throw new IllegalArgumentException(DIVIDED_ZERO_EXCEPTION_MESSAGE);
        }
    }

    private static boolean isZeroDivisor(int divisor) {
        return divisor == 0;
    }

    public int toInt() {
        return value;
    }
}
