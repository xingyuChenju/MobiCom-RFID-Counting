package exp;

import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.tags.Tag;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by Belief on 2018/6/24.
 * 说明：
 */
public class BasicRead {
    private static double begin,end;
    private static AlienClass1Reader reader;
    private static PrintWriter pw;
    private static Tag[] tagList;
    //E20032E2EBBCA03124758280

    public static void main(String[] args) throws AlienReaderException, InterruptedException, FileNotFoundException {
        pw=new PrintWriter("src/exp/select");
        AlienUtil.initReader(AlienUtil.IP);
        reader = new AlienClass1Reader();
        reader.setConnection(AlienUtil.IP, 23);
        reader.open();
        //reader.setAcqG2Mask(1, 112, 16, "81 28");
       //reader.setAcqG2Mask(3, 0, 16, "EC BA");
        Tag[] tagList = reader.getCustomTagList();
        System.out.println("标签总数：" + tagList.length);
        String[] masks = AlienUtil.epc2Mask(tagList);
        reader.setAcquireG2Count(0);

        double sum=0;int i=0;int size=1;
        for(i=0;i<100;i++) {
            //reader.setAcquireG2Selects(1);

            //reader.g2Wake();
            //reader.setAcquireG2Selects(0);
            begin = System.currentTimeMillis();
                reader.doReaderCommand("AcqG2Mask="+masks[0]);
                //reader.g2Wake(1);
            reader.getCustomTagList();
            //size=reader.getCustomTagList().length;
            end = System.currentTimeMillis();
            System.out.println(end-begin);
            pw.println((end-begin)/size);
            sum+=(end-begin)/size;
        }
        System.out.println(sum/i);
        pw.close();
        reader.close();
    }

    public static void doTask() throws AlienReaderException {
        Tag[] tagList = reader.getCustomTagList();
        System.out.println("标签总数：" + tagList.length);
    }
}
