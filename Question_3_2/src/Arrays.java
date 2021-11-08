class Arrays
{
    String [] arrayA= {
            "dog","cat","rat","fox","frog",
            "dinosaur","cow","bull"
    };

    String [] arrayB= {
            "dog", "dog", "cat","dog","frog","rat","turtle",
            "fox","fox","man","man","frog","man"
    };

    public void compare()
    {
        int indexB = 0;
        for (int indexA =0; indexA<arrayA.length;indexA++){
            int counter = 0;                                // reset biến counter sau mỗi lần đếm
            for(indexB=0; indexB<arrayB.length;indexB++){
                if(arrayA[indexA].equals(arrayB[indexB])){
                    counter++;
                }
            }// end for
            if(counter > 0){     // Đặt trong vòng for A và ngoài vòng for B
                System.out.println(arrayA[indexA] + "  " + counter);    // Sửa lỗi in ra "man" mà không phải tên con vật đúng
            }
        }// end for
    }//end compare()

    public static void main(String args[]){
        Arrays a = new Arrays();
        a.compare();
    }
}// end class Arrays
