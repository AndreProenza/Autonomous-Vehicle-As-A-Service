//package avaas.jdbc.model;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.validation.constraints.Min;
//
//@Entity
//@Table(name = "purchase_info")
//public class PurchaseInfo {
//
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(name="user_id", nullable = false)
//    @Min(1)
//    private int userId;
//
//    @Column(name="av_id", nullable = true)
//    @Min(1)
//    private int avId;
//
//    @Column(name="apilot_id", nullable = true)
//    @Min(1)
//    private int apilotId;
//    
//    
//    public PurchaseInfo() {
//    	//Does nothing
//    }
//    
//    
//    public PurchaseInfo(int id, @Min(1) int userId, @Min(1) int avId, @Min(1) int apilotId) {
//		super();
//		this.id = id;
//		this.userId = userId;
//		this.avId = avId;
//		this.apilotId = apilotId;
//	}
//    
//    
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public int getUserId() {
//		return userId;
//	}
//
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}
//
//	public int getAvId() {
//		return avId;
//	}
//
//	public void setAvId(int avId) {
//		this.avId = avId;
//	}
//
//	public int getApilotId() {
//		return apilotId;
//	}
//
//	public void setApilotId(int apilotId) {
//		this.apilotId = apilotId;
//	}
//
//	@Override
//	public String toString() {
//		return "PurchaseInfo [id = " + id + ", userId = " + userId + ", avId = " + avId + ", apilotId = " + apilotId + "]";
//	}
//    
//}