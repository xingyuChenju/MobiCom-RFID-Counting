import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

// This class estimates the number of tags trough Average Time Durations (ATD).

public class CountingDemo {
    static AlienClass1Reader reader;
    static String PATH = ".\\data230817";
    static Writer writer;
    static Writer writer1;
    public static void main(String[] args) throws Exception {
        reader = AlienUtil.initReader(AlienUtil.IP);

        ATDMethods.reader = reader;
        ATDMethods demo = new ATDMethods(0.1,0.1);
        reader.open();
        demo.roughEstimator(0);
        demo.bestQ(); // Obtain best parameter Q
        long time = System.currentTimeMillis();
        double averageTimeDuration = demo.getTimeDurations(0); // Measure ATD
        time = System.currentTimeMillis()-time;
        System.out.println("Q: "+demo.getQ());
        System.out.println("ATD: "+averageTimeDuration);
        System.out.println("Number of Tags: "+demo.getTagNumber(averageTimeDuration));
        System.out.println("Time: "+ time);
        File file = new File(PATH);
        if (!file.exists())
            file.mkdirs();
        writer = new FileWriter(PATH+"\\ATD.txt",true);
        writer1 = new FileWriter(PATH+"\\time2.txt",true);
        writer.write(demo.getTagNumber(averageTimeDuration)+"\n");
        writer1.write(time+"\n");
        writer.flush();
        writer1.flush();
        writer.close();
        writer1.close();
        reader.close();
    }
}
