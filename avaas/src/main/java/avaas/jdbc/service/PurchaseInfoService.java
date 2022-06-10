//package avaas.jdbc.service;
//
//import java.util.List;
//
//import avaas.jdbc.exception.PurchaseInfoNotFoundException;
//import avaas.jdbc.model.PurchaseInfo;
//
//public interface PurchaseInfoService {
//
//	PurchaseInfo getPurchaseInfoById(int id) throws PurchaseInfoNotFoundException;
//
//    List<PurchaseInfo> getAllPurchaseInfos();
//
//    PurchaseInfo updatePurchaseInfo(int id, PurchaseInfo purchaseInfo) throws PurchaseInfoNotFoundException;
//
//    PurchaseInfo savePurchaseInfo(PurchaseInfo purchaseInfo);
//
//    void deletePurchaseInfo(int id) throws PurchaseInfoNotFoundException;
//}