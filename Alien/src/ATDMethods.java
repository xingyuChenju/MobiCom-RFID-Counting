import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.tags.Tag;

import java.util.ArrayList;

import static java.lang.Math.*;
import static java.lang.Math.pow;

public class ATDMethods {
    public int Q;
    private double alpha,beta,m,t,n;
    static AlienClass1Reader reader;
    ATDMethods(double alpha, double beta) {
        this.alpha = alpha;
        this.beta = beta;
    }
    static double[] T = {1.2, 4.2, 1};
    public int bestQ() {
        double tmin =-1;
        int Qmin =-1;
        double mmin =-1;
        for(int i=4;i<=15;i++) {
            getNumberOfRounds(i);
            if (tmin<0) {
                tmin = t;
                Qmin = i;
                mmin = m;
            }
            else {
                if (tmin>t) {
                    tmin = t;
                    Qmin = i;
                    mmin = m;
                }
            }
        }
        this.t = tmin;
        this.Q = Qmin;
        this.m = mmin;
        return Qmin;
    }
    private double norminv(double p){

        double LOW = 0.02425;
        double HIGH = 0.97575;

        double a[] = { -3.969683028665376e+01, 2.209460984245205e+02,
                -2.759285104469687e+02, 1.383577518672690e+02,
                -3.066479806614716e+01, 2.506628277459239e+00 };

        double b[] = { -5.447609879822406e+01, 1.615858368580409e+02,
                -1.556989798598866e+02, 6.680131188771972e+01,
                -1.328068155288572e+01 };

        double c[] = { -7.784894002430293e-03, -3.223964580411365e-01,
                -2.400758277161838e+00, -2.549732539343734e+00,
                4.374664141464968e+00, 2.938163982698783e+00 };

        double d[] = { 7.784695709041462e-03, 3.224671290700398e-01,
                2.445134137142996e+00, 3.754408661907416e+00 };

        double q, r;

        if (p < LOW) {
            q = Math.sqrt(-2 * Math.log(p));
            return (((((c[0] * q + c[1]) * q + c[2]) * q + c[3]) * q + c[4])
                    * q + c[5])
                    /((((d[0] * q + d[1]) * q + d[2]) * q + d[3]) * q + 1);
        } else if (p > HIGH) {
            q = Math.sqrt(-2 * Math.log(1 - p));
            return -(((((c[0] * q + c[1]) * q + c[2]) * q + c[3]) * q + c[4])
                    * q + c[5])
                    /((((d[0] * q + d[1]) * q + d[2]) * q + d[3]) * q + 1);
        } else {
            q = p - 0.5;
            r = q * q;
            return (((((a[0] * r + a[1]) * r + a[2]) * r + a[3]) * r + a[4])
                    * r + a[5])
                    * q
                    /(((((b[0] * r + b[1]) * r + b[2]) * r + b[3]) * r + b[4])
                    * r + 1);
        }
    }
    public double getNumberOfRounds(int i){
        double f,q0,q1,q2,et,es,vt,p,c;
        f = Math.pow(2,i);
        q0 = Math.pow((1 - 1 / f), n);
        q1 = n / f * Math.pow((1 - 1 / f), (n - 1));
        q2 = 1 - q1 - q0;
        et = (T[0] * q0 + T[1] * q1 + T[2] * q2) / q1;
        es = q0 * T[0] + q1 * T[1] + q2 * T[2];
        vt = Math.pow(es, 2) * (1 - q1) / (Math.pow(q1, 2));
        c = -norminv((beta) / 2);
        p = 1 / (-((T[0] - T[2]) * (f - 1)) / Math.pow(n, 2) - (f * T[2] * Math.pow(1 - 1 / f, 1 - n)) / Math.pow(n, 2) - (f * T[2] * log(1 - 1 / f) * pow(1 - 1 / f, 1 - n)) / n);
        m = ceil(pow(c, 2) * pow(p, 2) * vt / (pow(alpha, 2) * pow(n, 2)));
        t = m * et;
        return m;
    }
    public int getTagNumber(double averagetime){
        int nfinal = 1;
        double q0, q1, q2, ET, f,diff,diff1;
        f = Math.pow(2,Q+1);
        diff = 1000;
        for(int n=(int) pow(2,Q+1);n<4000;n++){
            q0 = Math.pow((1-1/f),n);
            q1 = n/f*Math.pow((1-1/f),(n-1));
            q2 = 1 - q1 - q0;
            ET = (T[0]*q0+T[1]*q1+T[2]*q2)/q1;
            diff1 = Math.abs(ET-averagetime);
            if (diff1<diff) {
                diff = diff1;
                nfinal = n;
            }
          }
//        if (nfinal==(int) pow(2,Q+2)) {
//            for (int n = (int) pow(2, Q + 1); n < 4000; n++) {
//                q0 = Math.pow((1 - 1 / f), n);
//                q1 = n / f * Math.pow((1 - 1 / f), (n - 1));
//                q2 = 1 - q1 - q0;
//                ET = (T[0] * q0 + T[1] * q1 + T[2] * q2) / q1;
//                diff1 = Math.abs(ET - averagetime);
//                if (diff1 < diff) {
//                    diff = diff1;
//                    nfinal = n;
//                }
//            }
//        }
        return nfinal;
    }
    public double getM(){
        return this.m;
    }
    public int getQ(){
        return this.Q;
    }
    public void setQ(int w){
        Q = w;
    }
    public void addN(){
        this.n = this.n*2;
    }
    public double roughEstimator(int atten){
        int[] nums = new int[16];
        for (int Q = 0;Q<16;Q++){
            try {
                reader.setRFAttenuation(atten);
                reader.setAcquireG2Count(1);
                reader.setAcquireG2Session(2);
                reader.setAcquireG2Selects(2);
                reader.setAcqG2Mask(2, 80, 0, "");
                reader.doReaderCommand("AcqG2QMax = " + Q);
                reader.doReaderCommand("AcqG2Q = 7");
                reader.setAcquireTime(100);
                reader.setAcqG2MaskAction("BA");
                reader.setAcquireG2Target("B");
                Tag[] tags = reader.getCustomTagList();
                if (tags != null) {
//                    num+=tags.length;
//                    System.out.println(tags.length+"---------------"+"i"+"----------------");
//                    if (tags.length>=2){
//                        return Q+2;
//                    }
                    nums[Q] = tags.length;
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        for (int Q = 0;Q<16;Q++) {if (nums[Q]>15) { this.n = Math.pow(2,Q);
            return this.n;
        }
        }
        return this.n;
    }
    public double getTimeDurations(int atten)throws Exception {
        ArrayList<Double> diffs = new ArrayList<>();
//        int count = 0;
        while (diffs.size() < m) {
            reader.setRFAttenuation(atten);
            reader.setAcquireG2Count(1);
            reader.setAcquireG2Session(2);
            reader.setAcquireG2Selects(4);
            reader.setAcqG2Mask(2, 80, 0, "");
            reader.doReaderCommand("AcqG2QMax = " + getQ());
            reader.doReaderCommand("AcqG2Q = 7");
            reader.setAcquireTime(150);
//        reader.setAcqG2Mask(1, 32, 32, "20 10 31 00");
            reader.setAcqG2MaskAction("BA");
            reader.setAcquireG2Target("B");
            Tag[] tags = reader.getCustomTagList();
            if (tags != null) {
//                num+=tags.length;
//                System.out.println(tags.length + "---------------" + "i" + "----------------");
                if (tags.length >= 3) {
                    for (int i = 0; i < tags.length - 2; i++)
                        diffs.add((double) (tags[i + 1].getDiscoverTime() - tags[i].getDiscoverTime()));
                }else{
                    diffs.clear();
                    setQ(getQ()+1);
                }
            }
        }
        double sum = 0;
        for (double time:diffs) { sum = sum+time;}
        if (sum/diffs.size()<6){
            diffs.clear();
            setQ(getQ()-1);
            while (diffs.size() < m) {
//            CountingDemo.atdMethods.getQ();
//            if (count>=3) {
//                count=0;
//                Q =Q+1;
//                CountingDemo.atdMethods.setQ(Q);
//                CountingDemo.atdMethods.addN();
//                m = CountingDemo.atdMethods.getNumberOfRounds(Q);
//                diffs.clear();
//            }
                reader.setRFAttenuation(atten);
                reader.setAcquireG2Count(1);
                reader.setAcquireG2Session(2);
                reader.setAcquireG2Selects(4);
                reader.setAcqG2Mask(2, 80, 0, "");
                reader.doReaderCommand("AcqG2QMax = " + getQ());
                reader.doReaderCommand("AcqG2Q = 7");
                reader.setAcquireTime(150);
//        reader.setAcqG2Mask(1, 32, 32, "20 10 31 00");
                reader.setAcqG2MaskAction("BA");
                reader.setAcquireG2Target("B");
                Tag[] tags = reader.getCustomTagList();
                if (tags != null) {
//                num+=tags.length;
//                    System.out.println(tags.length + "---------------" + "i" + "----------------");
                    if (tags.length >= 2) {
                        for (int i = 0; i < tags.length - 2; i++)
                            diffs.add((double) (tags[i + 1].getDiscoverTime() - tags[i].getDiscoverTime()));
                    }
                }
            }
        }
        double atd = 0;
        for (int i = 0;i<diffs.size();i++){
//            System.out.println(diffs.get(i));
            atd = atd+diffs.get(i);
        }
        atd = atd/ diffs.size();
        diffs.clear();
        return atd;
    }
    public void setSL2B()throws Exception{
        reader.setAcquireG2Count(0);
        reader.setRFAttenuation(0);
        reader.setAntennaSequence("0");
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
    public void setSL2A(int atten)throws Exception{

//        for (int iii = 0;iii<10;iii++)
//        for(int i=15;i>0;i--) {
//        reader.doReaderCommand("freq="+1);
        reader.setAcquireG2Count(0);
//        reader.setAcqG2MaskAntenna(antenna);
        reader.setAntennaSequence("0");
        reader.setRFAttenuation(atten);
        reader.setAcquireG2Session(2);
        reader.setAcquireG2Selects(1);
        reader.setAcqG2Mask(2, 80, 0, "");
        reader.doReaderCommand("AcqG2QMax = " + 14);
        reader.doReaderCommand("AcqG2Q = 7");
        reader.setAcquireTime(0);
//        reader.setAcqG2Mask(1, 32, 32, "20 10 31 00");
        reader.setAcqG2MaskAction("A-");
        reader.getCustomTagList();
    }
}

