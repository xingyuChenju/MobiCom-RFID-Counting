import com.alien.enterpriseRFID.reader.AlienClass1Reader;

import java.util.ArrayList;

public class CountingDemo {
    static AlienClass1Reader reader;
    public static void main(String[] args) throws Exception {
        reader = AlienUtil.initReader(AlienUtil.IP);
        ATDMethods.reader = reader;
        ATDMethods demo = new ATDMethods(0.1,0.1);
        reader.open();
        demo.roughEstimator('0');
        demo.bestQ();
        double averageTimeDuration = demo.getTimeDurations(0);
        System.out.println(demo.getQ());
        System.out.println(averageTimeDuration);
        System.out.println(demo.getTagNumber(averageTimeDuration));
        reader.close();
    }
}
