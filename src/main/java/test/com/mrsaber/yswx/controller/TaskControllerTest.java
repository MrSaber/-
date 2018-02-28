package test.com.mrsaber.yswx.controller;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.mrsaber.yswx.model.Flow;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * TaskController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>一月 31, 2018</pre>
 */
public class TaskControllerTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: add()
     */
    @Test
    public void testAdd() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String context = sdf.format(new Date());
        String suffix = String.valueOf(System.currentTimeMillis() / 1000);
        System.out.println("WX" + context + suffix);
        System.out.println(suffix.substring(suffix.length() - 4));
    }

    @Test
    public void name() throws Exception {
/*        String[] args ={
                "食堂工作人员发布维修任务", //0
                "食堂管理员已同意维修任务", //1
                "食堂管理员已拒绝维修任务", //2
                "维修单位报价",          //3
                "食堂管理员选定维修单位",  //4
                "分管领导同意维修方案",   //5
                "分管领导不同意维修方案",  //6
                "主任同意维修方案",       //7
                "主任不同意维修方案",        //8
                "处长同意维修方案",         //9
                "处长不同意维修方案",        //10
                "维修单位施工",            //11
                "维修单位发起验收",         //12
                "食堂管理员验收",          //13
                "任务完成",                //14
                "任务失败"                 //15
        };
        ArrayList<String> current_progress = new ArrayList<>();
        switch (14)
        {
            case 1: //任务发布
                current_progress.add(args[0]);
                break;
            case 3: //管理员同意维修任务
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                break;
            case 4: //管理员不同意维修任务
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[15]);
                break;
            case 11: //管理员选定单位
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                break;
            case 5://分管领导同意
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                current_progress.add(args[5]);
                break;
            case 6://分管领导拒绝
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                current_progress.add(args[6]);
                current_progress.add(args[15]);
                break;
            case 7://主任同意
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                current_progress.add(args[5]);
                current_progress.add(args[7]);
                break;
            case 8://主任拒绝
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                current_progress.add(args[5]);
                current_progress.add(args[8]);
                current_progress.add(args[15]);
                break;
            case 9://处长同意
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                current_progress.add(args[5]);
                current_progress.add(args[7]);
                current_progress.add(args[9]);
                break;
            case 10://处长拒绝
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                current_progress.add(args[5]);
                current_progress.add(args[7]);
                current_progress.add(args[10]);
                current_progress.add(args[15]);
                break;
            case 12://维修单位施工
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                current_progress.add(args[5]);
                current_progress.add(args[7]);
                current_progress.add(args[9]);
                current_progress.add(args[11]);
                break;
            case 13://维修单位发起验收
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                current_progress.add(args[5]);
                current_progress.add(args[7]);
                current_progress.add(args[9]);
                current_progress.add(args[11]);
                current_progress.add(args[12]);
                break;
            case 14://管理员验收
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                current_progress.add(args[5]);
                current_progress.add(args[7]);
                current_progress.add(args[9]);
                current_progress.add(args[11]);
                current_progress.add(args[12]);
                current_progress.add(args[13]);
                break;
            case 15://任务完成
                current_progress.add(args[0]);
                current_progress.add(args[1]);
                current_progress.add(args[3]);
                current_progress.add(args[4]);
                current_progress.add(args[5]);
                current_progress.add(args[7]);
                current_progress.add(args[9]);
                current_progress.add(args[11]);
                current_progress.add(args[12]);
                current_progress.add(args[13]);
                current_progress.add(args[14]);
                break;
        }
        Iterator<String> it = current_progress.iterator();
        while (it.hasNext())
            System.out.println(it.next());*/
    }

    @Test
    public void testSMS() throws Exception {
        try {
            SmsSingleSender sender = new   SmsSingleSender(1400067830, "2ed790e2de88019badcc3b0c20122040");
            SmsSingleSenderResult result = sender.send(0, "86", "13040546822", "【MS】你好，我是张艺！！！", "", "123");
            System.out.print(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int[][] maps  =new int[9][9];//{{0,0,0,0},{0,1,1,1},{0,1,1,1},{0,1,1,1}};
    @Test
    public void testEightQueen()
    {
        settleQueen(0);
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                System.out.print(maps[i][j]);
            }
            System.out.println();
        }


    }

    public static boolean settleQueen(int x)
    {
        //行数超过8，说明已经找到8皇后问题的解
        if(x==8)
            return true;
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
                maps[i][j]=0; //清空当前行，已排除脏数据情况
            if(check(x,i))
            {
                maps[x][i]=1;
                if(settleQueen(x+1))
                    return true; //如果下层返回true，说明底层已有解决方案，无需继续循环！
            }
        }
        return false;
    }


    //XXX 判断某坐标左上侧，上侧，右上侧是否满足要求
    public static boolean check(int x,int y)
    {
        int b = y-x;
        int c =y+x;
        for(int i=0;i<x;i++)
        {
            if(maps[i][y]==1)
                return false;
            if(i-b>=0&&maps[i-b][i]==1)
                return false;
            if(c-i>=0&&maps[c-i][i]==1)
                return false;
        }
        return true;
    }
}


