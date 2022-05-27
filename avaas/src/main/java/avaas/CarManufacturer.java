package avaas;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

public class CarManufacturer {
	
	public String brand;
	
	public CarManufacturer() {
		//Does Nothing
	}
	
	public CarManufacturer(String brand) {
		super();
		this.brand = brand;
	}
	
	private static CarManufacturer from(Row row) {
		return new CarManufacturer(row.getString("brand"));
	}
	
	public static Multi<CarManufacturer> findAll(MySQLPool client) {
		return client.query("SELECT brand FROM car_manufacturer ORDER BY brand ASC").execute()
				.onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
				.onItem().transform(CarManufacturer::from);
	}
	
	public static Uni<CarManufacturer> findById(MySQLPool client, String brand) {
		return client.preparedQuery("SELECT brand FROM car_manufacturer WHERE brand = ?").execute(Tuple.of(brand))
				.onItem().transform(RowSet::iterator)
				.onItem().transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
	}
	
	public Uni<Boolean> save(MySQLPool client) {
		return client.preparedQuery("INSERT INTO car_manufacturer(brand) VALUES (?)").execute(Tuple.of(brand)).onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
	}
	
	
	public static Uni<Boolean> delete(MySQLPool client, String brand) {
		return client.preparedQuery("DELETE FROM car_manufacturer WHERE brand = ?").execute(Tuple.of(brand)).onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
	}
	
	public static Uni<Boolean> updateBrand(MySQLPool client, String brand) {
		return client.preparedQuery("UPDATE car_manufacturer SET brand = ? WHERE brand = ?").execute(Tuple.of(brand))
						.onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1 );
	}

}
