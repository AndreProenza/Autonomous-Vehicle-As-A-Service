package avaas.reactive.repository;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

public class PurchaseInfo {
	
	public int id;
	public int userId;
	public Integer avId;
	public Integer apilotId; 
	
	public PurchaseInfo() {
		//Does nothing
	}
	
	public PurchaseInfo(int id, int userId, Integer avId, Integer apilotId) {
		super();
		this.id = id;
		this.userId = userId;
		this.avId = avId;
		this.apilotId = apilotId;
	}
	

	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public Integer getAvId() {
		return avId;
	}

	public Integer getApilotId() {
		return apilotId;
	}
	

	public void setId(int id) {
		this.id = id;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setAvId(Integer avId) {
		this.avId = avId;
	}

	public void setApilotId(Integer apilotId) {
		this.apilotId = apilotId;
	}

	@Override
	public String toString() {
		return "PurchaseInfo [id = " + id + ", userId = " + userId + ", avId = " + avId + ", apilotId = " 
	+ apilotId + "]";
	}
	

	private static PurchaseInfo from(Row row) {
		return new PurchaseInfo(row.getInteger("id"), row.getInteger("user_id"), row.getInteger("av_id"), row.getInteger("apilot_id"));
	}
	
	public static Multi<PurchaseInfo> findAll(MySQLPool client) {
		return client.query("SELECT id, user_id, av_id, apilot_id FROM purchase_info").execute()
				.onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
				.onItem().transform(PurchaseInfo::from);
	}
	
	public static Uni<PurchaseInfo> findById(MySQLPool client, Integer id) {
		return client.preparedQuery("SELECT id, user_id, av_id, apilot_id FROM purchase_info WHERE id = ?").execute(Tuple.of(id))
				.onItem().transform(RowSet::iterator)
				.onItem().transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
	}
	
	
	public Uni<Boolean> save(MySQLPool client) {
		return client.preparedQuery("INSERT INTO purchase_info(id, user_id, av_id, apilot_id) VALUES (?, ?, ?, ?)").execute(Tuple.of(id, userId, avId, 0)).onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
	}
	
	
	public static Uni<Boolean> delete(MySQLPool client, Integer id) {
		return client.preparedQuery("DELETE FROM purchase_info WHERE id = ?").execute(Tuple.of(id)).onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
	}
	
	public static Uni<Boolean> updateUser(MySQLPool client, Integer id, Integer userId) {
		return client.preparedQuery("UPDATE purchase_info SET user_id = ? WHERE id = ?").execute(Tuple.of(userId,id))
						.onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1 );
	}
	
	public static Uni<Boolean> updateAV(MySQLPool client, Integer id, Integer avId) {
		return client.preparedQuery("UPDATE purchase_info SET av_id = ? WHERE id = ?").execute(Tuple.of(avId,id))
						.onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1 );
	}
	
	public static Uni<Boolean> sellAV(MySQLPool client, Integer id) {
		return client.preparedQuery("UPDATE purchase_info SET av_id = NULL WHERE id = ?").execute(Tuple.of(id))
						.onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1 );
	}
	
	
	public static Uni<Boolean> selectAPilot(MySQLPool client, Integer id, Integer apilotId) {
		return client.preparedQuery("UPDATE purchase_info SET apilot_id = ? WHERE id = ?").execute(Tuple.of(apilotId,id))
						.onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1 );
	}
	
	public static Uni<Boolean> unselectAPilot(MySQLPool client, Integer id) {
		return client.preparedQuery("UPDATE purchase_info SET apilot_id = NULL WHERE id = ?").execute(Tuple.of(id))
						.onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1 );
	}

}
