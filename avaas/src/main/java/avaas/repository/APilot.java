package avaas.repository;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

public class APilot {
	
	public int id;
	public String brand;
	public String model;
	
	public APilot() {
		//Does nothing
	}
 	
	public APilot(int id, String brand, String model) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
	}
	
	private static APilot from(Row row) {
		return new APilot(row.getInteger("id"), row.getString("brand"), row.getString("model"));
	}
	
	public static Multi<APilot> findAll(MySQLPool client) {
		return client.query("SELECT id, brand, model FROM apilot ORDER BY brand ASC").execute()
				.onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
				.onItem().transform(APilot::from);
	}
	
	public static Uni<APilot> findById(MySQLPool client, Integer id) {
		return client.preparedQuery("SELECT id, brand, model FROM apilot WHERE id = ?").execute(Tuple.of(id))
				.onItem().transform(RowSet::iterator)
				.onItem().transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
	}
	
	public Uni<Boolean> save(MySQLPool client) {
		return client.preparedQuery("INSERT INTO apilot(id, brand, model) VALUES (?, ?, ?)").execute(Tuple.of(id, brand, model)).onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
	}
	
	
	public static Uni<Boolean> delete(MySQLPool client, Integer id) {
		return client.preparedQuery("DELETE FROM apilot WHERE id = ?").execute(Tuple.of(id)).onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
	}
	
	public static Uni<Boolean> updateBrand(MySQLPool client, Integer id, String brand) {
		return client.preparedQuery("UPDATE apilot SET brand = ? WHERE id = ?").execute(Tuple.of(brand,id))
						.onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1 );
	}
	
	public static Uni<Boolean> updateModel(MySQLPool client, Integer id, String model) {
		return client.preparedQuery("UPDATE apilot SET model = ? WHERE id = ?").execute(Tuple.of(model,id))
						.onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1 );
	}
}
