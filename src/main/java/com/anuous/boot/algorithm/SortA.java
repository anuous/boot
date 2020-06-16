package com.anuous.boot.algorithm;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class SortA {

    //插入排序：将一个无序的数列 插入到一个有序的数列中，并使之保持有序。

    /**
     * 1、将数组分成已排序段 和 未排序段，初始化时，已排序段只有一个元素
     * 2、到未排序段中取一个元素插入已排序段中，并使之有序
     * 3、重复上述操作，只到未排序数据加完
     * @param arrays
     * @return
     */
    public static int[] insertSort(int[] arrays){
        int length = arrays.length;
        for (int i =1; i <length ; i++) {//O(n) 第一个数不用排序，我们把数组从i分开，0-i的认为为有序
            int data = arrays[i];
            int j = i-1;
            for(;j>=0;j--){//插入+移位 更优
                int obj = arrays[j];
                if(obj>data){
                    arrays[j+1]=obj;
                }else{
                  break;
                }
            }
            arrays[j+1]=data;
        }
        return arrays;
    }

    //希尔排序:插入排序的一种优化，把记录按着数组下标的一定增量进行分组，对每组数据直接使用插入排序算法排序，
    // 随着增量的逐渐减少，每组包含的关键词（数值）越来越多，当增量减到1时，整个文件被分成一组，算法便终止。

    /**
     *
     * @param arrays
     * @return
     */
    public static int[] shellSort(int [] arrays){
        int arrLength = arrays.length;
        if(arrLength<1){
            return arrays;
        }
        //初始化步长 gap
        for(int gap = arrLength/2;gap>0;gap=gap/2){
            //分段后 从第二个元素开始执行插入
            for(int i=gap;i<arrLength;i++){
                //从后往前遍历插入
                int temp = arrays[i];//待插入数据
                int j =i-gap;
                /*while(j>0 && temp<arrays[j]){
                    arrays[j+gap]=arrays[j];
                    j=j-gap;
                }*/
                for(;j>0;j=j-gap){
                    if(temp<arrays[j]){
                        arrays[j+gap]=arrays[j];
                    }else{
                        break;
                    }
                }
                arrays[j+gap]=temp;
            }
            for(int k = 0;k<arrLength;k++){
                System.out.print(arrays[k]+",");
            }
            System.out.println();
        }


        return arrays;
    }

    //二分排序（归并排序）

    public static void mergeSort(int [] arrays,int left,int right){
        if(left<right){//左边小于右边 则分割拆分 相等 则表示只有一个元素了 不用分割了
            int middle = (left+right)/2;
            mergeSort(arrays, left, middle);
            mergeSort(arrays, middle+1, right);
            merge(arrays,left,middle,right);
        }
    }

    /**
     * 归并 排序过程
     * @param arrays
     * @param left
     * @param middle
     * @param right
     */
    private static void merge(int[] arrays,int left,int middle,int right){
        //指定两个位置 左边数组开始位置 point1,右边数组开始位置 point2  开始处理数据的位置 loc
        int point1=left;
        int point2=middle+1;
        int loc =left;
        int[] temp =new int[arrays.length];

        //归并过程 将较小的数 放进临时数组中，
        while (point1<=middle && point2<=right){
            if(arrays[point1]<arrays[point2]){
                temp[loc++]= arrays[point1++];
            }else{
                temp[loc++]=arrays[point2++];
            }
        }
        //将左边未排序的元素 依次放到temp中
        while(point1<=middle){
            temp[loc++]=arrays[point1++];
        }
        //将右边未排序的元素 依次放到temp中
        while(point2<=right){
            temp[loc++]=arrays[point2++];
        }
        //将排好序的临时数组 赋值给源数组
        for(int i=left;i<left;i++){
            arrays[i]=temp[i];
        }


    }

    //从上往下 递进
    //===========================

    //选择排序

    /**
     * 选择排序的的思路和插入排序非常相似,分成已排序和未排序区间。选择排序每次会从未排序区间中找到最小
     * 的元素，将其放到已排序的区间的末尾。
     * 不会移动已排序元素位置，每次会惊喜比较交互
     *
     * @param arrays
     */
    public static int[] selectionSort(int [] arrays){
        int length = arrays.length;
        if (length<=0) {
            return arrays;
        }
        for (int i = 0; i < length; i++) {
            int minIndex =i;//每一轮比较 记住最小值位置  比较完之后在交换
            for (int j = i+1; j <length ; j++) {
                if(arrays[minIndex]>arrays[j]){
                    minIndex=j;
                }
            }
            int temp = arrays[minIndex];
            arrays[minIndex]=arrays[i];
            arrays[i]=temp;
        }
        for (int i = 0; i <length ; i++) {
            System.out.print(arrays[i]+",");
        }
        return arrays;
    }

    //======================================================

    //冒泡排序
    // 冒泡排序的 简单优化思路：所有元素已经有序则退出冒泡
    /**
     * 冒泡排序 只会操作相邻的两个元素，对相邻数据进行比较，不满足条件，则交换。一轮循环过后，至少有一个数会排在他因在的位置。
     * N轮之后，排序完成
     * a=a+b
     * b=a-b
     * a=a-b 完成交换
     * @param arrays
     */
    public static void bubbleSort(int[] arrays){
        for (int i = 0; i <arrays.length -1; i++) {
            boolean flag = false;
            //方式一
            /*for (int j = i+1; j <arrays.length ; j++) {
                if(arrays[i]<arrays[j]){
                    int temp =arrays[i];
                    arrays[i]=arrays[j];
                    arrays[j]=temp;
                    flag = true;
                }
            }*/
            //方式二
            for(int j=0;j<arrays.length-1-i;j++){
                if(arrays[j]>arrays[j+1]){
                    arrays[j]=arrays[j]+arrays[j+1];
                    arrays[j+1]=arrays[j]-arrays[j+1];
                    arrays[j]=arrays[j]-arrays[j+1];
                    flag=true;
                }
            }
            if(!flag){
                break;//简单优化点：如果不存在交互 则表示所有数据已经有序，退出循环
            }
            System.out.println(Arrays.toString(arrays));
        }

    }
    //快速排序
    /**
     *快速排序的优化点，优化基准数的取值
     * @param arrays
     * @param left 左边从哪里（位置）开始
     * @param right 右边从哪个位置开始
     */
    public static void quicklySort(int[] arrays,int left,int right){
        int base = arrays[left];//取左边第一个数为基准书
        int leftIndex = left; //左边开始的位置
        int rightIndex = right;//右边开始的位置
        while(leftIndex<rightIndex){ //终止条件 左边小于右边
            //从后往前找 比基准数小数据 找到交换
            while(leftIndex<rightIndex && arrays[rightIndex]>=base){//大于基准书 右边位置左移 继续寻找
                        rightIndex--;
            }
            //找到了 交换
            if(leftIndex<rightIndex){// 表示上述循环 是因为从右边到左边找到了比基准数小的值而终止
                int temp = arrays[rightIndex];
                arrays[rightIndex]= arrays[leftIndex];
                arrays[leftIndex] = temp;
                leftIndex ++; //基准数自身交换，下次可以不用再找了
            }
            //从前往后找 比基准数大的数据 找到交换
            while(leftIndex<rightIndex && arrays[leftIndex]<=base){//小于基准数 左边位置右移 继续寻找
                leftIndex ++;
            }
            if(leftIndex<rightIndex){
                int temp = arrays[leftIndex];
                arrays[leftIndex]=arrays[rightIndex];
                arrays[rightIndex]=temp;
                rightIndex --;
            }
        }
        if(left<leftIndex){
            quicklySort(arrays,left,leftIndex-1);
        }
        if(rightIndex<right){
            quicklySort(arrays,rightIndex+1,right);
        }
        System.out.println(Arrays.toString(arrays));
    }


    //从上往下 递进
    //========================
    //基数排序
public static void countSort(int[] arrays,int nim,int maxLength) throws IOException {
    int count[] = new int[maxLength+1];
    long start = System.currentTimeMillis();
    for (int i = 0; i <arrays.length ; i++) {
        count[arrays[i]]++;
    }
    System.out.println("排序所用时间长度："+(System.currentTimeMillis()-start)+"ms");
    long start2 = System.currentTimeMillis();
    FileWriter fw = new FileWriter("D://result.txt");
    for (int i = 0; i <count.length ; i++) {
        if(count[i]>0){
            for (int j = 0; j <count[i] ; j++) {
                fw.write(""+(double)i/100.0+"\n");
            }
        }
    }
    System.out.println("排序后数据输出所用时间长度："+(System.currentTimeMillis()-start2)+"ms");
    fw.close();
}
    //堆排序
    //==以上排序需要掌握==============
    //桶排序



    //排序算法分析的维度:时间复杂度、空间复杂度、比较次数&交换次数、稳定性

    //稳定排序：相同的两个元素、排完序之后，相对位置不变。
    //稳定排序有什么意义？应用场景有啥？

    //1,2,3,4,4,4,4,5,6,7,9,19,32,56

    public static void main(String[] args) throws Exception{
        int[] nums={7,3,9,2,6,4,56,32,5,19};
        /*SortA.insertSort(nums);
        for (int i = 0; i <nums.length ; i++) {
            System.out.println(nums[i]);
        }*/
        //SortA.shellSort(nums);
        //SortA.selectionSort(nums);
        //SortA.bubbleSort(nums);
        //SortA.quicklySort(nums,0,nums.length-1);
       /* int[] numstemp = new int[1000002];
        long readStarttime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("D://tmp.txt"))));
        String str =null;
        int i = 0;
        while ((str=br.readLine())!=null){
            double value = Double.valueOf(str);
            numstemp[i++]=(int)value*100;
        }
        System.out.println("读取数据花费时间=" + (System.currentTimeMillis()- readStarttime)+" ms");
        countSort(numstemp,0,1000002);*/
       BigDecimal inputMoney = new BigDecimal("0.575");
        System.out.println("inputMoney="+inputMoney);
        System.out.println("==========================");
        BigDecimal res = inputMoney.multiply(new BigDecimal(100)).setScale(0,RoundingMode.DOWN);
        System.out.println(res);
        System.out.println("============================");
        System.out.println("transmount="+res.toString());
    }
}
