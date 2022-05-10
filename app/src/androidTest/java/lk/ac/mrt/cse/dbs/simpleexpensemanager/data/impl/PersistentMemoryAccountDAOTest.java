package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import static lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType.EXPENSE;
import static org.junit.Assert.*;

import android.app.Activity;
import androidx.test.core.app.ApplicationProvider;

import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.ui.DbHelper;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.ui.MainActivity;

public class PersistentMemoryAccountDAOTest {

    @Test
    public void addAccount() throws InvalidAccountException {
        String accountNo="12678A";
        String bank = "commercial";
        String accountHolder = "prathu";
        Double balance = 1200.0;
        Account acc= new Account(accountNo,bank,accountHolder,balance);
        DbHelper db = new DbHelper(ApplicationProvider.getApplicationContext());

        PersistentMemoryAccountDAO testacc= new PersistentMemoryAccountDAO(db);
        testacc.addAccount(acc,db);
        Account output= testacc.getAccount(accountNo);
        //PersistentMemoryAccountDAO testacc1= new PersistentMemoryAccountDAO(db);

        List<String> Accounts = testacc.getAccountNumbersList();

        assertEquals(Accounts.contains(accountNo),true);

    }
    @Test
    public void addTransaction() {

        DbHelper db = new DbHelper(ApplicationProvider.getApplicationContext());
        PersistentMemoryTransactionDAO test= new PersistentMemoryTransactionDAO(db);
        test.logTransaction("2020-05-18", "12678A", EXPENSE, 200.0,db);
        List <Transaction> transactions=test.getPaginatedTransactionLogs(1);
        Transaction transaction = transactions.get(0);
        assertEquals(transaction.getAccountNo(),"12678A");

    }

//     @Test
//     public void getAccount() throws InvalidAccountException {
//         String accountNo="125A";

//         DbHelper db = new DbHelper(ApplicationProvider.getApplicationContext());
//         PersistentMemoryAccountDAO testAccDAO= new PersistentMemoryAccountDAO(db);

//         Account output= testAccDAO.getAccount(accountNo);
//         assertEquals(accountNo,output.getAccountNo());
//     }
}
