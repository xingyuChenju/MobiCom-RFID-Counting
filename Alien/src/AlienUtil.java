import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.tags.Tag;


public class AlienUtil {

    public static final String IP ="192.168.1.100";

    public static AlienClass1Reader initReader(String ip) throws AlienReaderException {
        AlienClass1Reader reader = new AlienClass1Reader();
        reader.setConnection(ip, 23);
        reader.open();
        reader.setReaderFunction("Alien");
        reader.setTimeOutMilliseconds(100000);
        reader.setPersistTime(-1);
//        reader.setAcquireMode("INVENTORY");
        reader.setAcquireTime(0);
        reader.setTagListCustomFormat("ID:%k,  distime:${MSEC1}");
        //reader.setTagListCustomFormat("ID:%k,  readnum:%r,  RSSI:%m");
        reader.setTagListFormat(AlienClass1Reader.CUSTOM_FORMAT);
        reader.setAntennaSequence("0");
        reader.setRFAttenuations(0, 0, 0);
        reader.setAcquireG2Session(0);
        reader.setAcqG2SL("ALL");//SL  nSL  ALL
        reader.setAcqG2Mask(1, 32, 0, "");
        //100: 4  8    260: 7 9
        reader.setAcquireG2Q(4);
        reader.doReaderCommand("AcqG2QMax = 10");
        reader.doReaderCommand("AcqG2Q = 0");
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
//        reader.doReaderCommand("freq="+1);
        reader.close();
        return  reader;
    }

    public static void main(String[] args){
        try {
            initReader(IP);
        } catch (AlienReaderException e) {
            e.printStackTrace();
        }
    }

}
