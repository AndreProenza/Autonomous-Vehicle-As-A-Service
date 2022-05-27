package avaas;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

public class Av {
	
	public int id;
	public String brand;
	public String model;
	
	public Av() {
		//Does nothing
	}
 	
	public Av(int id, String brand, String model) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
	}
	
	private static Av from(Row row) {
		return new Av(row.getInteger("id"), row.getString("brand"), row.getString("model"));
	}
	
	public static Multi<Av> findAll(MySQLPool client) {
		return client.query("SELECT id, brand, model FROM av ORDER BY brand ASC").execute()
				.onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
				.onItem().transform(Av::from);
	}
	
	public static Uni<Av> findById(MySQLPool client, Integer id) {
		return client.preparedQuery("SELECT id, brand, model FROM av WHERE id = ?").execute(Tuple.of(id))
				.onItem().transform(RowSet::iterator)
				.onItem().transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
	}
	
	public Uni<Boolean> save(MySQLPool client) {
		return client.preparedQuery("INSERT INTO av(id, brand, model) VALUES (?, ?, ?)").execute(Tuple.of(id, brand, model)).onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
	}
	
	
	public static Uni<Boolean> delete(MySQLPool client, Integer id) {
		return client.preparedQuery("DELETE FROM av WHERE id = ?").execute(Tuple.of(id)).onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
	}
	
	public static Uni<Boolean> updateBrand(MySQLPool client, Integer id, String brand) {
		return client.preparedQuery("UPDATE av SET brand = ? WHERE id = ?").execute(Tuple.of(brand,id))
						.onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1 );
	}
	
	public static Uni<Boolean> updateModel(MySQLPool client, Integer id, String model) {
		return client.preparedQuery("UPDATE av SET model = ? WHERE id = ?").execute(Tuple.of(model,id))
						.onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1 );
	}
	
}
