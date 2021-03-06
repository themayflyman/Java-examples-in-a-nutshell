package ObjectsClassesAndInterfaces;

/* A class that represents a person's mailing address. */
public class MailingAddress {
    private String name, streetAddress, city, state, zipCode;

    public MailingAddress(String name, String streetAddress, String city, String state, String zipCode) {
        this.name = name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String toString() {
        return "name: " + name + '\n' +
                "street address: " + streetAddress + '\n' +
                "city: " + city + '\n' +
                "state: " + state + '\n' +
                "ZIP code: " + zipCode + '\n';
    }

    /* A test program */
    public static class Test {
        public static void main(String[] args) {
            MailingAddress m = new MailingAddress("Pete", "somewhere",
                                                  "NY", "somewhere", "11111");
            System.out.println(m);
        }
    }
}
