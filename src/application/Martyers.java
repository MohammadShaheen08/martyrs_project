package application;


import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Martyers {

	private String name;
	private int age;
	private LocalDate dod;
	private String gender;
	static int count = 0;

	public Martyers(String name, int age, LocalDate localDate, String gender) {
		this.age = age;
		this.dod = localDate;
		this.gender = gender;
		this.name = name;
		count++;

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Martyers other = (Martyers) obj;
		return Objects.equals(name, other.name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDate getDod() {
		return dod;
	}

	public void setDod(LocalDate dod) {
		this.dod = dod;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "[Name " + name + ", Age" + age + ", date of death" + dod + "gender" + gender + "]";
	}

}
