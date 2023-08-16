import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.tags.Tag;

import java.io.File;

// This class shows how to set the SL flag.

public class MReaders {
    static AlienClass1Reader reader;
    static String path = ".\\data2203302\\basic";
    //    static  int atten = 60;
    public static void main(String[] args) throws Exception {
//        for(int attention = 40;attention<50;attention = attention+10) {
        String path1 = path + "\\";
        File file = new File(path1);
        if (!file.exists())
            file.mkdirs();

            AlienUtil.initReader(AlienUtil.IP);
            reader = new AlienClass1Reader();
            reader.setConnection(AlienUtil.IP, 23);
            reader.open();

            setFlag2B("0");

            try {

                    readTags("0",0);

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                reader.close();
            }
    }

    public static void setFlag2A(String antenna,int atten)throws Exception{

//        for (int iii = 0;iii<10;iii++)
//        for(int i=15;i>0;i--) {
//        reader.doReaderCommand("freq="+1);
        reader.setAcquireG2Count(0);
        reader.setRFAttenuation(atten);
//        reader.setAcqG2MaskAntenna(antenna);
        reader.setAntennaSequence(antenna);
        reader.setAcquireG2Session(2);
        reader.setAcquireG2Selects(1);
        reader.setAcqG2Mask(2, 80, 0, "");
        reader.doReaderCommand("AcqG2QMax = " + 14);
        reader.doReaderCommand("AcqG2Q = 7");
        reader.setAcquireTime(0);
//        reader.setAcqG2Mask(1, 32, 32, "20 10 31 00");
        reader.setAcqG2MaskAction("AB");
        reader.getCustomTagList();
    }
    public static void setFlag2B(String antenna)throws Exception{

        reader.setAcquireG2Count(0);
        reader.setRFAttenuation(0);
        reader.setAcqG2SL("ALL");
//        reader.setAcqG2MaskAntenna(antenna);
        reader.setAntennaSequence(antenna);
        reader.setAcquireG2Session(2);
        reader.setAcquireG2Selects(1);
        reader.setAcqG2Mask(1, 32, 0, "");
        reader.doReaderCommand("AcqG2QMax = " + 14);
        reader.doReaderCommand("AcqG2Q = 7");
        reader.setAcquireTime(0);
//        reader.setAcqG2Mask(1, 32, 32, "20 10 31 00");
        reader.setAcqG2MaskAction("BA");
        reader.getCustomTagList();
    }

    public static void setSL(String antenna)throws Exception{

        reader.setAcquireG2Count(0);
        reader.setRFAttenuation(0);
        reader.setAcqG2SL("SL");
//        reader.setAcqG2MaskAntenna(antenna);
        reader.setAntennaSequence(antenna);
        reader.setAcquireG2Session(2);
        reader.setAcquireG2Selects(1);
        reader.setAcqG2Mask(1, 32, 0, "");
        reader.doReaderCommand("AcqG2QMax = " + 14);
        reader.doReaderCommand("AcqG2Q = 7");
        reader.setAcquireTime(0);
//        reader.setAcqG2Mask(1, 32, 32, "20 10 31 00");
        reader.setAcqG2MaskAction("BA");
        reader.getCustomTagList();
    }

    public static void readTags(String antenna,int atten)throws Exception{
//        for (int iii = 0;iii<10;iii++)
//        for(int i=15;i>0;i--) {
//        reader.doReaderCommand("freq="+1);
        reader.setAcquireG2Count(1);
        reader.setRFAttenuation(atten);
        reader.setAntennaSequence(antenna);
        reader.setAcquireG2Session(2);
        reader.setAcquireG2Selects(1);
        reader.setAcqG2Mask(2, 80, 0, "");
        reader.doReaderCommand("AcqG2QMax = " + 15);
        reader.doReaderCommand("AcqG2Q = 7");
        reader.setAcquireTime(0);
//        reader.setAcqG2Mask(1, 32, 32, "20 10 31 00");
        reader.setAcquireG2Target("B");
        reader.setAcqG2SL("nSL");
//        reader.setAcqG2SL("SL");
//        reader.setAcqG2SL("SL");
        Tag[] tags = reader.getCustomTagList();
        if (tags != null) {
            System.out.println(tags.length+"---------------"+"i"+"----------------");
           for (int i=0; i<tags.length; i++) {
                Tag tag = tags[i];
                System.out.println("ID:" + tag.getTagID()+ "  RSSI:"+tag.getRSSI());
            }
        }
        else{
//                writer.write(0+"\n");
            System.out.println("----------------Null----------------");
        }
    }

}

