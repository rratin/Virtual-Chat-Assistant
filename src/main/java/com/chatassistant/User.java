package com.chatassistant;

public class User {
	private String email;
    private String password;
    private int id;
    private String fullname;
    boolean isActive;

    

	public User() {
        super();
    }

    public User(String email, String password, int id, String fullname) {
        super();
        
        this.email = email;
        this.password = password;
        this.id = id;
        this.fullname= fullname;
    }
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
	


}
