package jdbc.functions;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.util.Scanner;

public class SpringJdbcTest {
	static DriverManagerDataSource datasource=new DriverManagerDataSource();
	static JdbcTemplate template=null;

	static void establishConnection() {

		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/MyDatabase");
		datasource.setUsername("root");
		datasource.setPassword("Abichickoo@03");
	}
	
	static void insertData(int deptno,String name,String loc) {

		template = new JdbcTemplate(datasource);
		String query="INSERT INTO departments(deptno,name,location) values(?,?,?)";
		int result=template.update(query,deptno,name,loc);
		if(result>0) {
			System.out.println(result + "row(s) has been inserted successfully");
		}	
	}
	
	static void updateDeptLoc(String oldval,String newval) {

		template = new JdbcTemplate(datasource);
		String query="UPDATE departments "
				+ "SET location = ?"
				+ "WHERE location = ?";
		int result=template.update(query,oldval,newval);
		if(result>0) {
			System.out.println("The Location has been updated successfully");
		}	
	}

	static void deleteDeptLoc(int deptnum) {
		template = new JdbcTemplate(datasource);
		String query="DELETE FROM departments "
				+ "WHERE deptno= ?";
		int result=template.update(query,deptnum);
		if(result>0) {
			System.out.println("The Location has been deleted successfully");
		}	
	}

	public static void main(String[] args) {

		establishConnection();
		Scanner scan= new Scanner(System.in);

		do {
			System.out.println();
			System.out.println("1.Insert Data\n 2.Update Data\n 3. Delete Data\n4.Exit");
			System.out.println("Enter Your Choice [1-4]");
			int choice= scan.nextInt();
			switch(choice) {
			case 1:
				System.out.println("Enter the Department ID: ");
				int deptno = scan.nextInt();
				System.out.println("Enter the Department Name: ");
				String name = scan.next();
				System.out.println("Enter the Department Location: ");
				String location=scan.next();
				insertData(deptno,name,location);
				break;

			case 2:
				System.out.println("Enter the Old Department Location: ");
				String oldval = scan.next();
				System.out.println("Enter the  New Department Location: ");
				String newval = scan.next();
				updateDeptLoc(oldval,newval);
				break;

			case 3:
				System.out.println("Enter the Department ID: ");
				int deptnum = scan.nextInt();
				deleteDeptLoc(deptnum);
				break;

			case 4:
				System.out.println("Thanks For Using SpringJdbc");
				return;

			default:
				System.out.println("Invalid Choice");
			}

		}while(true);

	}
}