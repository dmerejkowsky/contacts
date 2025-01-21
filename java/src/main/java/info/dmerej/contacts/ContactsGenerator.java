package info.dmerej.contacts;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ContactsGenerator {
  public Stream<Contact> generateContacts(int count) {
    // TODO: generate a *lot* of contacts instead of just 3
    Contact[] contacts = new Contact[]{
      new Contact("name-1", "email-1@domain.tld"),
      new Contact("name-2", "email-2@domain.tld"),
      new Contact("name-3", "email-3@domain.tld"),
    };
    return Arrays.stream(contacts);
  }
}
