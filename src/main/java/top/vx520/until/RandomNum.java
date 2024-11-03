package top.vx520.until;

import java.util.Random;

//随机生成数字工具类
public class RandomNum {

    //leng为指定的长度
    public static int getRanNum(int leng){
        Random random=new Random();
        int num=0;
        for (int i = 0; i < leng; i++) {
            int temp = random.nextInt(10);
            num+=temp;
            if (i==leng-1){
                break;
            }
            num*=10;
        }

        return num;

    }

}
