package EmailHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelperModel {
    private final String accountsDirectory;
    private List<String> accountNames;
    private final ArrayList<Account> accounts;
    
    public HelperModel() {
        accountsDirectory = "accounts/";
        accounts = new ArrayList<>();

        inventoryAccounts();

        //UI space constraints allow a max of four accounts.
        int maxElements = 4;
        int size = accountNames.size();
        if (size > maxElements) {
            size = maxElements;
        }
        
        Account anAccount = null;
        for (int i = 0; i < size; i++) {
            try {
                anAccount = new Account(accountNames.get(i));
            }
            catch(IOException ioe) {
                System.out.println(ioe.getMessage());
            }
            accounts.add(anAccount);
        }
    }
    
    private void inventoryAccounts() {
        File accountsPath = new File(accountsDirectory);
        String accountNamesArr[] = accountsPath.list();
        List<String> accountList = Arrays.asList(accountNamesArr);
        setAccountNames(accountList);
    }
    
    private void setAccountNames(List<String> accountNames) {
        this.accountNames = new ArrayList<>(accountNames);
    }
    
    public ArrayList<Account> getAccounts() {
        return this.accounts;
    }
    
    public Account getOneAccount(int index) {
        return this.accounts.get(index);
    }
    
}

