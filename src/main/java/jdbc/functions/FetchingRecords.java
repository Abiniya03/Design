package jdbc.functions;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class FetchingRecords {
	static DriverManagerDataSource datasource=new DriverManagerDataSource();
	static JdbcTemplate template=null;

	static void establishConnection() {

		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/MyDatabase");
		datasource.setUsername("root");
		datasource.setPassword("Abichickoo@03");
		template = new JdbcTemplate(datasource);

	}

	static void fetchAllRecords() {
		String select_all ="SELECT * FROM departments";
		//RowMapper is an Anonymous Function//
		RowMapper<Departments> rowmapper = new RowMapper<Departments>() {

			public Departments mapRow(ResultSet rs,int rowNum) throws SQLException{
				Integer deptno = rs.getInt("deptno");
				String name = rs.getString("name");
				String location = rs.getString("location");
				return new Departments(deptno,name,location);
			}
		};

		List<Departments> dept = template.query(select_all, rowmapper);
		System.out.println("Dept No\t\tDept Name\t\t Location");
		for(Departments d:dept) {
			System.out.println(d.deptno +"\t\t"+d.name+"\t\t"+d.location);
		}
	}

	public static void main(String[] args) {
		establishConnection();
		fetchAllRecords();
	}
}