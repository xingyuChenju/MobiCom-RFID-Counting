package exp;

import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.tags.Tag;

/**
 * Created by Belief on 2018/6/24.
 * 说明：
 */
public class AlienUtil {

    public static final String IP ="192.168.1.102";

    public static AlienClass1Reader initReader(String ip) throws AlienReaderException {
        AlienClass1Reader reader = new AlienClass1Reader();
        reader.setConnection(ip, 23);
        reader.open();
        //String s=reader.getInfo();
        //reader.setReaderFunction("Alien");
        //设为定频count次数变很大
        //reader.doReaderCommand("freq=-1");
        reader.setTimeOutMilliseconds(60000);
        reader.setPersistTime(-1);
        reader.setAcquireMode("INVENTORY");
        reader.setAcquireTime(0);
        reader.setTagListCustomFormat("ID:%k,  readnum:%r");
        //reader.setTagListCustomFormat("ID:%k,  readnum:%r,  RSSI:%m");
        reader.setTagListFormat(AlienClass1Reader.CUSTOM_FORMAT);
        reader.setRFAttenuations(0, 0, 0);
        reader.setAcquireG2Session(2);
        reader.setAcqG2SL("ALL");//SL  nSL  ALL
        reader.setAcqG2Mask(1, 32, 0, "");
        //100: 4  8    260: 7 9
        reader.setAcquireG2Q(5);
        reader.doReaderCommand("AcqG2QMax = 7");
        reader.setAcquireG2OpsMode(AlienClass1Reader.OFF);
        reader.doReaderCommand("Set AcqG2AntennaCombine = OFF");
        reader.setAcquireG2OpsMode(AlienClass1Reader.OFF);

        reader.setRFAttenuations(0,0,0);
        reader.setAcquireG2Cycles(1);
        reader.setAntennaSequence("0");
        reader.setAcquireG2Selects(1);
        reader.setAcquireG2Count(1);
        reader.setAcqG2MaskAction("AB");
        reader.setAcquireG2Target("A");
        reader.close();
        return  reader;
    }

    public static String[] epc2Mask(Tag[] tagList){
        String[] masks=null;
        if(tagList==null || tagList.length==0) return masks;
        String epc; StringBuilder sb;
        masks=new String[tagList.length];
        for(int i=0;i<tagList.length;i++){
            epc=tagList[i].getTagID();
            sb=new StringBuilder(40);
            for(int j=0;j<epc.length();j+=2){
                sb.append(epc.substring(j,j+2)); sb.append(" "); }
            masks[i]="1, 32, "+(epc.length()<<2)+", "+sb.toString().trim();
        }
        return masks;
    }

    public static void writeOpSet(AlienClass1Reader reader) throws AlienReaderException {
        //写操作相关设置
        reader.setProgAntenna(0);
        reader.setProgUserDataInc("OFF");
        reader.setProgUserDataIncCount(-1);
        reader.setProgDataUnit("Block");
        reader.setProgBlockSize(0);
        reader.setProgAttempts(1);
        reader.doReaderCommand("ProgSuccessFormat=1");
        reader.setProgSingulate(0);
    }


    public static void main(String[] args){
        try {
            initReader(IP);
        } catch (AlienReaderException e) {
            e.printStackTrace();
        }
    }

}
