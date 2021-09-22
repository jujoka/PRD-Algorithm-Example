import java.text.DecimalFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.###");
        HashMap<String, Float> dotaChances = new HashMap<>();
        dotaChances.put("Ranged Bash", 0.1f);
        dotaChances.put("PA Crit", 0.15f);
        dotaChances.put("Radiance", 0.17f);
        dotaChances.put("Bash", 0.25f);
        dotaChances.put("Spirit Breaker Bash Talent", 0.27f);
        dotaChances.put("Maelstorm", 0.30f);
        dotaChances.put("Butterfly", 0.35f);
        dotaChances.put("Chaos Strike", 0.333f);
        dotaChances.put("PA Evasion", 0.50f);
        dotaChances.put("Monkey King Bar", 0.80f);

        ArrayList<Float> chances = new ArrayList<Float>();

        Scanner s = new Scanner(System.in);

        System.out.println("Select a probability to simulate:");

        int n = 0;
        for(HashMap.Entry<String, Float> entry : dotaChances.entrySet()) {
            n++;
            String key = entry.getKey();
            Float value = entry.getValue();
            chances.add(entry.getValue());

            System.out.println("[" + n + "] " + key + " | Chance: " + value);
        }
        System.out.println("[" + (n + 1) + "] Custom");

        System.out.print("Input: ");
        int selection = s.nextInt();

        PRNG prng = new PRNG();

        if (selection == n + 1) {
            System.out.println("Probability (number between 0 and 1, use your locales default decimal separator , or .): ");
            float chanceInput = s.nextFloat();
            prng.setConstant(chanceInput);
        } else {
            prng.setConstant(chances.get(selection - 1));
        }

        System.out.print("Input number of attempts: ");
        int attempts = s.nextInt();

        int count = 0;
        float attemptSum = 0;
        float totalProcs = 0;

        for (int i = 0; i < attempts; i++) {
            count++;
            attemptSum++;
            if (prng.next(true)) {
                System.out.println("[PROC] after " + count + " attempts");
                totalProcs++;
                count = 0;
            }
        }
        if (totalProcs != 0)
            System.out.println("Avg attempts to proc: " + attemptSum / totalProcs +
                    "\nEstimated probability: " + df.format((totalProcs / attemptSum) * 100) + "%");
        else
            System.out.println("No procs.");
    }
}
