import com.alien.enterpriseRFID.reader.AlienClass1Reader;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

// This class estimates the number of tags trough Average Time Durations (ATD).

public class CountingDemo {
    static AlienClass1Reader reader;
    static String PATH = ".\\data230815";
    static Writer writer;
    public static void main(String[] args) throws Exception {
        reader = AlienUtil.initReader(AlienUtil.IP);
        ATDMethods.reader = reader;
        ATDMethods demo = new ATDMethods(0.1,0.1);
        reader.open();
        demo.roughEstimator(0);
        demo.bestQ();
        double averageTimeDuration = demo.getTimeDurations(0);
        System.out.println("Q: "+demo.getQ());
        System.out.println("ATD: "+averageTimeDuration);
        System.out.println("Number of Tags: "+demo.getTagNumber(averageTimeDuration));
        File file = new File(PATH);
        if (!file.exists())
            file.mkdirs();
        writer = new FileWriter(PATH+"\\ATD.txt",true);
        writer.write(demo.getTagNumber(averageTimeDuration)+"\n");
        writer.flush();
        writer.close();
        reader.close();
    }
}
