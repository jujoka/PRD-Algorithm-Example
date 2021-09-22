import java.text.DecimalFormat;

public class PRNG {
    DecimalFormat df = new DecimalFormat("#.#");

    private int failedAttempts = 0;
    private float C;

    public PRNG() {

    }

    public PRNG(float probability) {
        this.C = CFromP(probability);
    }

    public void setConstant(float probability) {
        this.C = CFromP(probability);
    }

    public boolean next(boolean printRollInformation) {
        float P = this.C * (this.failedAttempts + 1);
        double roll = Math.random();
        if (printRollInformation) System.out.println("P=" + df.format(P * 100) + "%\t(rolled " +
                df.format(roll * 100) + ")");
        if (roll <= P) {
            // Proc
            this.failedAttempts = 0;
            return true;
        } else {
            // No proc
            this.failedAttempts++;
            return false;
        }
    }

    private float CFromP(float P) {
        float CUpper = P;
        float CLower = 0;
        float CMid = 0;

        float p1 = 0;
        float p2 = 1;

        while (true) {
            CMid = (CUpper + CLower) / 2;
            p1 = PFromC(CMid);

            if (Math.abs(p1 - p2) <= 0) break;

            if (p1 > P)
                CUpper = CMid;
            else
                CLower = CMid;

            p2 = p1;
        }

        return CMid;
    }

    private float PFromC(float C) {
        float pOnN = 0;
        float pByN = 0;
        float sumPByN = 0;

        int maxFails = (int) (Math.ceil(1 / C));

        for (int n = 1; n <= maxFails; n++) {
            pOnN = Math.min(1, n * C) * (1 - pByN);
            pByN = pByN + pOnN;
            sumPByN = sumPByN + n * pOnN;
        }

        return 1 / sumPByN;
    }
}
