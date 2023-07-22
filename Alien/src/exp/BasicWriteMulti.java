package exp;

import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.tags.Tag;

/**
 * Created by Belief on 2018/6/24.
 * 说明：
 */
public class BasicWriteMulti {
    private static long begin, end;
    private static AlienClass1Reader reader;
    //1 32 80 00 E2 01 40 42 51 72 51 40 00

    public static void main(String[] args) throws AlienReaderException {
        AlienUtil.initReader(AlienUtil.IP);
        reader = new AlienClass1Reader();
        reader.setConnection(AlienUtil.IP, 23);
        reader.open();
        writeOpSet();

        Tag[] tagList = reader.getCustomTagList();
        System.out.println("标签总数：" + tagList.length);
        //得到所有标签的Mask
        String[] masks = AlienUtil.epc2Mask(tagList);

        //reader.setProgUserData("FF FF FF FF FF FF FF FF");

        int sum=0;
        int count=0;
        for(count=0;count<10;count++) {
            begin = System.currentTimeMillis();
                doTask(masks);
            end = System.currentTimeMillis();
            System.out.println("时间: " + (end - begin));
            sum+=end-begin;
        }
        System.out.println("平均时间: " + sum/count);
        reader.close();
    }

    public static void writeOpSet() throws AlienReaderException {
        //写操作相关设置
        reader.setProgAntenna(0);
        reader.setProgUserData("FF FF");
        reader.setProgUserDataInc("OFF");
        reader.setProgUserDataIncCount(-1);
        reader.setProgDataUnit("Word");
        reader.setProgBlockSize(0);
        reader.setProgAttempts(1);
        reader.doReaderCommand("ProgSuccessFormat=1");
        reader.setProgSingulate(0);
    }

    public static void doTask(String[] masks) throws AlienReaderException {
        if (masks == null || masks.length == 0) return;
        int fail = 0, success = 0;
        for (int i = 0; i < masks.length; i++) {
            reader.doReaderCommand("AcqG2Mask="+masks[i]);
            //reader.setAcqG2Mask(1, 32, 80, "00 E2 01 40 42 51 72 51 40 00");
            try {
                if(i%2==0) reader.doReaderCommand("G2write=3,0,00 00");
                else reader.doReaderCommand("G2write=3,0,FF FF");
                //reader.programUser();
                success++;
            } catch (Exception e) {
                //String a=reader.getInfo();
                fail++;
            }
        }
        System.out.println("成功次数：" + success+"    失败次数：" + fail);
    }

}