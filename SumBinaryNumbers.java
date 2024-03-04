public class SumBinaryNumbers {
    public static void main(String[] args) {
        int  number1 = 10;
        int number2 = 11;
        int[] binary1 = convertToBinary(number1);
        int[] binary2 = convertToBinary(number2);
        int maxLength = Math.max(binary1.length, binary2.length);
        int[] sum = new int[maxLength + 1];
        int carry = 0;
        for (int i = 0; i < maxLength; i++) {
            int digit1 = i < binary1.length ? binary1[i] : 0;
            int digit2 = i < binary2.length ? binary2[i] : 0;
            int tempSum = digit1 + digit2 + carry;
            if (tempSum == 2) {
                sum[i] = 0;
                carry = 1;
            } else {
                sum[i] = tempSum;
                carry = 0;
            }
        }
        if (carry != 0) {
            sum[maxLength] = carry;
        }
        for (int i = sum.length - 1; i >= 0; i--) {
            System.out.print(sum[i]);
        }
    }
    private static int[] convertToBinary (int number){
        int count = 0;
        int n = number;
        while (n > 0) {
            n = n / 10;
            count++;
        }
        n = number;
        int[] binary = new int[count];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = n % 10;
            n = n / 10;
        }
        return binary;
    }
}