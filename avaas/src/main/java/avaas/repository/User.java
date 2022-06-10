package avaas.repository;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

public class User {
	
	public int id;
	public String name;
	public int age;
	
	public User() {
		//Does nothing
	}
 	
	public User(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
	

	@Override
	public String toString() {
		return "User [id = " + id + ", name = " + name + ", age = " + age + "]";
	}

	private static User from(Row row) {
		return new User(row.getInteger("id"), row.getString("name"), row.getInteger("age"));
	}
	
	public static Multi<User> findAll(MySQLPool client) {
		return client.query("SELECT id, name, age FROM user ORDER BY name ASC").execute()
				.onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
				.onItem().transform(User::from);
	}
	
	public static Uni<User> findById(MySQLPool client, Integer id) {
		return client.preparedQuery("SELECT id, name, age FROM user WHERE id = ?").execute(Tuple.of(id))
				.onItem().transform(RowSet::iterator)
				.onItem().transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
	}
	
	public Uni<Boolean> save(MySQLPool client) {
		return client.preparedQuery("INSERT INTO user(id, name, age) VALUES (?, ?, ?)").execute(Tuple.of(id, name, age)).onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
	}
	
	
	public static Uni<Boolean> delete(MySQLPool client, Integer id) {
		return client.preparedQuery("DELETE FROM user WHERE id = ?").execute(Tuple.of(id)).onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
	}
	
	public static Uni<Boolean> updateName(MySQLPool client, Integer id, String name) {
		return client.preparedQuery("UPDATE user SET name = ? WHERE id = ?").execute(Tuple.of(name,id))
						.onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1 );
	}
	
	public static Uni<Boolean> updateAge(MySQLPool client, Integer id, int age) {
		return client.preparedQuery("UPDATE user SET age = ? WHERE id = ?").execute(Tuple.of(age,id))
						.onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1 );
	}
}
