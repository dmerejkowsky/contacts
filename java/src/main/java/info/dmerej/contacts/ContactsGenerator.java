package info.dmerej.contacts;

import java.sql.Connection;

public class ContactsGenerator {
    private final Connection connection;

    public ContactsGenerator(Connection connection) {
        this.connection = connection;
    }

    public void insertManyContacts(int numContacts) {
        System.out.format("Inserting %d contacts ...", numContacts);
        // TODO
        // At the end of this call, the db should contain exactly `numContacts` contacts,
        // from email-1@domain.tld to email-{numContacts}@domain.tld
        System.out.println("done");
    }

}
