import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.tags.Tag;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;


public class GroundTruth {
    static AlienClass1Reader reader;
    static String path = ".\\data2203302\\basic";
    static String dataName = "BRead";
    static Writer writer;
    static int prior = 0;
    static int nums =0;
    //    static  int atten = 60;
    public static void main(String[] args) throws Exception {
//        for(int attention = 40;attention<50;attention = attention+10) {
        String path1 = path + "\\";
        File file = new File(path1);
        if (!file.exists())
            file.mkdirs();
        for (int jjj = 0; jjj < 10; jjj++) {
            writer = new FileWriter(path1 + dataName + jjj + ".txt", true);
            AlienUtil.initReader(AlienUtil.IP);
            long time1 = System.currentTimeMillis();
            reader = new AlienClass1Reader();
            reader.setConnection(AlienUtil.IP, 23);
            reader.open();
//            reader.setRFAttenuation(0);
//                setFlag2B("0");
            setFlag2B("0");
//            setFlag2B("3");
            writer.write(-1+" "+-1+" "+-1+" "+System.currentTimeMillis()+"\n");
            try {
//                    readTags("0", attention);
//                    setFlag2A("0",attention+5);
//                    setFlag2A("2",attention+5);
//                    readTags("2", attention);
//                    readTags("0", 60);
//                readTags("0", 60);
//                readTags("0", 50);
//                readTags("0", 40);
                readTags("0", 30);
//                readTags("0", 20);
//                readTags("0", 20);
                readTags("0", 0);
//                    readTags("3", 50);
//                    Thread.sleep(100);
//                    readTags("3", 20);
//                    Thread.sleep(100);
//                    readTags("3", 10);
//                    Thread.sleep(100);
//                    readTags("3", 0);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                reader.close();
            }
            writer.flush();
            writer.close();
        }
        System.out.println(nums);
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

//        for (int iii = 0;iii<10;iii++)
//        for(int i=15;i>0;i--) {
//        reader.doReaderCommand("freq="+1);
        reader.setAcquireG2Count(0);
        reader.setRFAttenuation(0);
//        reader.setAcqG2MaskAntenna(antenna);
        reader.setAntennaSequence(antenna);
        reader.setAcquireG2Session(2);
        reader.setAcquireG2Selects(1);
        reader.setAcqG2Mask(2, 80, 0, "");
        reader.doReaderCommand("AcqG2QMax = " + 14);
        reader.doReaderCommand("AcqG2Q = 7");
        reader.setAcquireTime(0);
//        reader.setAcqG2Mask(1, 32, 32, "20 10 31 00");
        reader.setAcqG2MaskAction("BA");
        reader.getCustomTagList();
    }

    public static void setSL2B(String antenna)throws Exception{

//        for (int iii = 0;iii<10;iii++)
//        for(int i=15;i>0;i--) {
//        reader.doReaderCommand("freq="+1);
        reader.setAcquireG2Count(0);
        reader.setRFAttenuation(0);
//        reader.setAcqG2MaskAntenna(antenna);
        reader.setAntennaSequence(antenna);
        reader.setAcquireG2Session(2);
        reader.setAcqG2SL("SL");
        reader.setAcquireG2Selects(1);
        reader.setAcqG2Mask(2, 80, 0, "");
        reader.doReaderCommand("AcqG2QMax = " + 15);
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
        reader.setAcqG2MaskAction("-A");
        reader.setAcquireG2Target("B");
        Tag[] tags = reader.getCustomTagList();
        if (tags != null) {
            System.out.println(tags.length+"---------------"+"i"+"----------------");
            writer.write(antenna+" "+atten+" "+tags.length+" "+System.currentTimeMillis()+"\n");
        }
        else{
//                writer.write(0+"\n");
            System.out.println("----------------Null----------------");
        }
    }
    public static void maskBit(String mask,AlienClass1Reader reader,String antenna) throws AlienReaderException {
        reader.clearTagList();
        reader.setRFAttenuations(0, 0, 0);
        reader.setAcquireG2Cycles(1);
        reader.setAntennaSequence(antenna);
        reader.setAcquireG2Selects(1);
        reader.setAcquireG2Count(1);
        reader.setAcquireTime(0);
        reader.setAcquireG2Session(2);
//        reader.setAcqG2Mask(1,126,2,mask);
        reader.setG2MaskBits(0,mask);
        reader.setAcqG2MaskAction("BA");
        reader.setAcquireG2Target("B");
        Tag[] tagList = reader.getCustomTagList();
        if (tagList == null) {
            System.out.println("No Tags Found");
        } else {
            System.out.println("Tag(s) found:"+tagList.length);
            for (int i=0; i<tagList.length; i++) {
//                Tag tag = tagList[i];
//                System.out.println("ID:" + tag.getTagID()+ "  RSSI:"+tag.getRSSI());
            }
            nums += tagList.length;
        }
        System.out.println("-----------");
        System.out.println();
    }

}
