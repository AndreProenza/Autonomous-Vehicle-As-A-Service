package avaas;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

public class Employee {
	
	public int userId;
	public String role;
	
	public Employee() {
		//Does nothing
	}
 	
	public Employee(int userId, String role) {
		super();
		this.userId = userId;
		this.role = role;
	}
	
	private static Employee from(Row row) {
		return new Employee(row.getInteger("userId"), row.getString("role"));
	}
	
	public static Multi<Employee> findAll(MySQLPool client) {
		return client.query("SELECT userId, role FROM employee").execute()
				.onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
				.onItem().transform(Employee::from);
	}
	
	public static Uni<Employee> findById(MySQLPool client, Integer userId) {
		return client.preparedQuery("SELECT userId, role FROM employee WHERE userId = ?").execute(Tuple.of(userId))
				.onItem().transform(RowSet::iterator)
				.onItem().transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
	}
	
	public Uni<Boolean> save(MySQLPool client) {
		return client.preparedQuery("INSERT INTO employee(userId, role) VALUES (?, ?)").execute(Tuple.of(userId, role)).onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
	}
	
	
	public static Uni<Boolean> delete(MySQLPool client, Integer userId) {
		return client.preparedQuery("DELETE FROM employee WHERE userId = ?").execute(Tuple.of(userId)).onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
	}
	
	public static Uni<Boolean> updateRole(MySQLPool client, Integer userId, String role) {
		return client.preparedQuery("UPDATE employee SET role = ? WHERE userId = ?").execute(Tuple.of(role,userId))
						.onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1 );
	}
}
