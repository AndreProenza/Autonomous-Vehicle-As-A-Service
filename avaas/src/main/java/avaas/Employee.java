package avaas;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

public class Employee {
	
	public int userId;
	public String responsible;
	
	public Employee() {
		//Does nothing
	}
 	
	public Employee(int userId, String responsible) {
		super();
		this.userId = userId;
		this.responsible = responsible;
	}
	
	private static Employee from(Row row) {
		return new Employee(row.getInteger("userId"), row.getString("responsible"));
	}
	
	public static Multi<Employee> findAll(MySQLPool client) {
		return client.query("SELECT userId, responsible FROM employee").execute()
				.onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
				.onItem().transform(Employee::from);
	}
	
	public static Uni<Employee> findById(MySQLPool client, Integer userId) {
		return client.preparedQuery("SELECT userId, responsible FROM employee WHERE userId = ?").execute(Tuple.of(userId))
				.onItem().transform(RowSet::iterator)
				.onItem().transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
	}
	
	public Uni<Boolean> save(MySQLPool client) {
		return client.preparedQuery("INSERT INTO employee(userId, responsible) VALUES (?, ?)").execute(Tuple.of(userId, responsible)).onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
	}
	
	
	public static Uni<Boolean> delete(MySQLPool client, Integer userId) {
		return client.preparedQuery("DELETE FROM employee WHERE userId = ?").execute(Tuple.of(userId)).onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
	}
	
	public static Uni<Boolean> updateResponsible(MySQLPool client, Integer userId, String responsible) {
		return client.preparedQuery("UPDATE employee SET responsible = ? WHERE userId = ?").execute(Tuple.of(responsible,userId))
						.onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1 );
	}
}
