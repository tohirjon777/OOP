import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ReadAccounts {
    private BufferedReader reader;
    private String url;

    public ReadAccounts(String url) {
        this.url = url;
    }

    public LinkedList<String> getFirstNames() {
        LinkedList<String> firstNames = new LinkedList<>();
        try {
            reader = new BufferedReader(new FileReader(url));
            String line;
            boolean firstLine = true; 
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                firstNames.add(parts[0]);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return firstNames;
    }

    public LinkedList<String> getLastNames() {
        LinkedList<String> lastNames = new LinkedList<>();
        try {
            reader = new BufferedReader(new FileReader(url));
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                lastNames.add(parts[1]);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastNames;
    }

    public LinkedList<Integer> getAccounts() {
        LinkedList<Integer> accounts = new LinkedList<>();
        try {
            reader = new BufferedReader(new FileReader(url));
            String line;
            boolean firstLine = true; 
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                accounts.add(Integer.parseInt(parts[2]));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public LinkedList<Integer> getBalances() {
        LinkedList<Integer> balances = new LinkedList<>();
        try {
            reader = new BufferedReader(new FileReader(url));
            String line;
            boolean firstLine = true; 
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                balances.add(Integer.parseInt(parts[3]));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return balances;
    }
}
