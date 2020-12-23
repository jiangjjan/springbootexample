package exec;

import java.util.Scanner;

public class TestZipStr {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            StringConcat ss = new StringConcat(str);
            System.out.println(ss.getResult());
        }
        sc.close();
    }
    static class StringConcat {
        String con;
        char lastAppendChar;
        char newChar;
        int repeatNum = 0;
        boolean isFirstRepeat = true;
        StringBuilder sb = new StringBuilder();;

        StringConcat() {
        }
        StringConcat(String s) {
            con = s;
        }

        public String getResult() {
            for (char c : con.toCharArray()) {
                this.accept(c);
            }
            return sb.toString();
        }

        public int accept(char newChar) {
            this.newChar = newChar;
            // is first run
            if (sb.length() == 0)
                sb.append(newChar);
            else if (lastAppendChar == newChar)
                isEqueals();
            else
                notEqueals();

            this.lastAppendChar = newChar;
            return sb.length();
        }

        private void notEqueals() {
            repeatNum = 0;
            isFirstRepeat = true;
            sb.append(newChar);
        }

        private void isEqueals() {
            if (isFirstRepeat) {
                sb.append(repeatNum);
            }
            isFirstRepeat = false;
            String lastNumber = String.valueOf(repeatNum);
            repeatNum++;
            sb.replace(sb.lastIndexOf(lastNumber),sb.lastIndexOf(lastNumber)+lastNumber.length(),String.valueOf(repeatNum));
        }

    }
}
