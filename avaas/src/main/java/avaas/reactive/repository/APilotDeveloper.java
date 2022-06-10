package avaas.reactive.repository;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

public class APilotDeveloper {
	
public String brand;
	
	public APilotDeveloper() {
		//Does Nothing
	}
	
	public APilotDeveloper(String brand) {
		super();
		this.brand = brand;
	}
	
	private static APilotDeveloper from(Row row) {
		return new APilotDeveloper(row.getString("brand"));
	}
	
	public static Multi<APilotDeveloper> findAll(MySQLPool client) {
		return client.query("SELECT brand FROM apilot_developer ORDER BY brand ASC").execute()
				.onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
				.onItem().transform(APilotDeveloper::from);
	}
	
	public static Uni<APilotDeveloper> findById(MySQLPool client, String brand) {
		return client.preparedQuery("SELECT brand FROM apilot_developer WHERE brand = ?").execute(Tuple.of(brand))
				.onItem().transform(RowSet::iterator)
				.onItem().transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
	}
	
	public Uni<Boolean> save(MySQLPool client) {
		return client.preparedQuery("INSERT INTO apilot_developer(brand) VALUES (?)").execute(Tuple.of(brand)).onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
	}
	
	
	public static Uni<Boolean> delete(MySQLPool client, String brand) {
		return client.preparedQuery("DELETE FROM apilot_developer WHERE brand = ?").execute(Tuple.of(brand)).onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
	}
	
	public static Uni<Boolean> updateBrand(MySQLPool client, String brand) {
		return client.preparedQuery("UPDATE apilot_developer SET brand = ? WHERE brand = ?").execute(Tuple.of(brand))
						.onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1 );
	}
}
