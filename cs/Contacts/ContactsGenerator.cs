namespace Contacts;

public class ContactsGenerator
{
  public ContactsGenerator()
  {
  }

  public List<Contact> GenerateContacts(int count)
  {
    // TODO: generate `count` contacts without
    // filling up memory - maybe using IEnemerable ?
    Contact c1 = new("name-1", "email-1@domain.tld");
    Contact c2 = new("name-2", "email-2@domain.tld");
    Contact c3 = new("name-3", "email-3@domain.tld");
    return new List<Contact> { c1, c2 };
  }
}