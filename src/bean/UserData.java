package bean;

public class UserData {

	
	
	private int id;
	private String username;
	private String password;
	private int age;
	private String city;
	private String email;
	
	
	public UserData() {
		super();
	}
	public UserData(int id, String username, String password, int age, String city, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.age = age;
		this.city = city;
		this.email = email;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "UserData [id=" + id + ", username=" + username + ", password=" + password + ", age=" + age + ", city="
				+ city + ", email=" + email + "]";
	}
	
}
