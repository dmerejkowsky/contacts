namespace Contacts;

public class Program
{
  private readonly ContactsGenerator _generator;

  public Program()
  {
    _generator = new ContactsGenerator();
  }

  static int Main(string[] args)
  {
    if (args.Length == 0)
    {
      Console.WriteLine("Not enough arguments");
      return 1;
    }

    int count = 0;
    string firstArg = args[0];
    var ok = int.TryParse(firstArg, out count);
    if (!ok)
    {
      Console.WriteLine($"Could not parse '{firstArg}' as an integer");
      return 1;
    }

    var app = new Program();
    app.Run(count);
    return 0;
  }

  private Database GetMigratedDatabase()
  {
    string dbPath = "contacts.sqlite3";
    if (!File.Exists(dbPath))
    {
      var database = new Database(dbPath);
      database.Migrate();
      return database;
    }
    else
    {
      return new Database(dbPath);
    }
  }

  private void Run(int count)
  {
    var database = GetMigratedDatabase();

    using (database)
    {
      InsertContacts(database, count);
      LookupContact(database, count);
    }
  }



  private void InsertContacts(Database database, int count)
  {
    Console.WriteLine($"Inserting {count} contacts ...");
    // TODO
    // At the end of this call, the db should contain exactly `numContacts` contacts,
    // from email-1@domain.tld to email-{numContacts}@domain.tld
    Console.WriteLine("Done");
  }

  private void LookupContact(Database database, int count)
  {
    // Note: make sure this is the *last* inserted contact
    string email = $"contact-{count}@domain.tld";

    var before = DateTime.Now.TimeOfDay;
    var contact = database.LookupContact(email);
    var after = DateTime.Now.TimeOfDay;
    var span = after - before;
    Console.WriteLine($"Query took {span.Seconds}s {span.Milliseconds}ms");

    if (contact == null) 
    {
      throw new Exception("Contact not found");
    }
  }
}