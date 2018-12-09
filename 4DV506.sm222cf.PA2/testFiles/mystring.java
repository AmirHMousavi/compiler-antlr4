class MyString{
    public static void main(String[] a){
        {
            System.out.println(new StringEditor().removeSpace("Hello World And Happy Coding"));
            System.out.println(new StringEditor().containsChar("Hello World And Happy Coding", 'd'));
            System.out.println(new StringEditor().containsChar("Hello World And Happy Coding", 'b'));
        }
    }
}

class StringEditor {
    String removeSpace(String str) {
        String toReturn;
        int n;
        int sz;
        char c;

        toReturn = "";
        sz = str.length();
        n = 0;
        while ( n < sz ) {
            c = str.charAt(n);
            if ( c == ' ') {

            } else {
                toReturn = toReturn + c;
            }
            n = n+1;
        }

        return toReturn;
    }

    boolean containsChar(String str, char c) {
        int n;
        int sz;
        char c1;

        boolean toReturn;

        toReturn = false;

        sz = str.length();
        n = 0;
        while ( n < sz ) {
            c1 = str.charAt(n);
            if ( c1 == c) {
                toReturn = true;
                break;
            }
            n = n+1;
        }

        return toReturn;
    }
}
