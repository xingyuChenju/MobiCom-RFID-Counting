package exp;

import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by Belief on 2017/12/18.
 * 说明：
 */
public class BasicBlastWrite {
    private static double begin,end;
    private static AlienClass1Reader reader;
    private static PrintWriter pw;

    public static void main(String[] args) throws AlienReaderException, InterruptedException, FileNotFoundException {
        pw=new PrintWriter("src/exp/blastwrite_16");
        AlienUtil.initReader(AlienUtil.IP);
        reader = new AlienClass1Reader();
        reader.setConnection(AlienUtil.IP, 23);
        reader.open();
        //AlienUtil.writeOpSet(reader);
        reader.setTimeOutMilliseconds(60000);
        //reader.setAcquireG2Ops("1 87 3 0 FF FF FF FF FF FF BB BB");
        reader.setAcquireG2Ops("1 87 3 0 BB AA BB AA");

        double sum=0;
        int count=0;
        for(count=0;count<20;count++) {
            reader.setAcquireG2Selects(1);
            reader.g2Wake();
            reader.setAcquireG2Selects(0);
            begin = System.currentTimeMillis();
            doTask();
            end = System.currentTimeMillis();
            pw.println(end-begin);
            System.out.println("时间: " + (end - begin));
            sum+=(end-begin);
            //Thread.sleep(1000);
        }
        System.out.println("平均时间: " + sum/count);
        pw.close();
        reader.close();
    }

    public static void doTask() throws AlienReaderException {
            String s=reader.doReaderCommand("to");
    }

}