class Odds{
    public static void main(String[] a){
        System.out.println(new MaxOdd().SumOdds(100));
    }
}

class MaxOdd {

    int SumOdds(int total){

        int sum;
        int nextOdd;
        sum = 0;
        nextOdd = 1;

        while(sum < total) {
            if(total-nextOdd < 85) //break when nextOdd reaches 15
            break;
            sum = sum + nextOdd;
            nextOdd = nextOdd + 2;
        }

        return sum ;
    }

}
