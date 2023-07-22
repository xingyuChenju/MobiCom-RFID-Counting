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
public class BasicReadMask {
    private static long begin,end;
    private static AlienClass1Reader reader;
    private static PrintWriter pw;
    private static Tag[] tagList;
    //E20032E2EBBCA03124758280

    public static void main(String[] args) throws AlienReaderException, InterruptedException, FileNotFoundException {
        pw=new PrintWriter("src/exp/mask_4_4");
        AlienUtil.initReader(AlienUtil.IP);
        reader = new AlienClass1Reader();
        reader.setConnection(AlienUtil.IP, 23);
        reader.open();
        //reader.setAcqG2Mask(1, 112, 16, "81 28");
       //reader.setAcqG2Mask(3, 0, 16, "EC BA");

        Tag[] tagList = reader.getCustomTagList();
        System.out.println("标签总数：" + tagList.length);
        //得到所有标签的Mask
        String[] masks = AlienUtil.epc2Mask(tagList);

        masks=new String[4];
        for(int i=0;i<4;i++) masks[i]="1, 32, 4, E2";

        reader.setAcquireG2Count(0);
        int sum=0;int i=0;
        for(i=0;i<100;i++) {
            begin = System.currentTimeMillis();
                //reader.setAcqG2Mask(1, 32, 96, "00 00 00 00 00 00 00 00 00 00 00 00");
            String str=String.join(" | ", Arrays.copyOfRange(masks,0,4));
            reader.doReaderCommand("AcqG2Mask="+str);
                //reader.doReaderCommand("AcqG2Mask="+masks[0]);
                //reader.g2Wake(1);
                reader.getCustomTagList();
            end = System.currentTimeMillis();
            pw.println((end-begin));
            sum+=end-begin;
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
