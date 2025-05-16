package EntityPackage;

public abstract class User {
    protected String id;
    protected String name;

    public User(String id, String name) {
        // Updated to allow only alphabets and spaces in the name
    	
        if (!name.matches("^[a-zA-Z]+(?: \\s[a-zA-Z]+)*$")) {
            throw new IllegalArgumentException("Name must contain only alphabets and spaces.");
        }        
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public abstract void showProfile();
}

