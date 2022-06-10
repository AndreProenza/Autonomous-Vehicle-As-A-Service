//package avaas.jdbc.service.impl;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.inject.Inject;
//import javax.transaction.Transactional;
//
//import avaas.jdbc.exception.PurchaseInfoNotFoundException;
//import avaas.jdbc.model.PurchaseInfo;
//import avaas.jdbc.repository.PurchaseInfoRepository;
//import avaas.jdbc.service.PurchaseInfoService;
//
//import java.util.List;
//
//@ApplicationScoped
//public class PurchaseInfoImplementation implements PurchaseInfoService {
//
//    private final PurchaseInfoRepository purchaseInfoRepository;
//
//    @Inject
//    public PurchaseInfoImplementation(PurchaseInfoRepository purchaseInfoRepository) {
//        this.purchaseInfoRepository = purchaseInfoRepository;
//    }
//
//    @Override
//    public PurchaseInfo getPurchaseInfoById(int id) throws PurchaseInfoNotFoundException {
//        return purchaseInfoRepository.findByIdOptional((long) id).orElseThrow(() -> new PurchaseInfoNotFoundException("There purchase info doesn't exist"));
//    }
//
//    @Override
//    public List<PurchaseInfo> getAllPurchaseInfos() {
//        return purchaseInfoRepository.listAll();
//    }
//
//    @Transactional
//    @Override
//    public PurchaseInfo updatePurchaseInfo(int id, PurchaseInfo purchaseInfo) throws PurchaseInfoNotFoundException {
//    	PurchaseInfo existingPurchaseInfo = getPurchaseInfoById(id);
//    	existingPurchaseInfo.setUserId(purchaseInfo.getUserId());
//    	existingPurchaseInfo.setAvId(purchaseInfo.getAvId());
//    	existingPurchaseInfo.setApilotId(purchaseInfo.getApilotId());
//        purchaseInfoRepository.persist(existingPurchaseInfo);
//        return existingPurchaseInfo;
//    }
//
//    @Transactional
//    @Override
//    public PurchaseInfo savePurchaseInfo(PurchaseInfo purchaseInfo) {
//    	purchaseInfoRepository.persistAndFlush(purchaseInfo);
//        return purchaseInfo;
//    }
//
//    @Transactional
//    @Override
//    public void deletePurchaseInfo(int id) throws PurchaseInfoNotFoundException {
//    	purchaseInfoRepository.delete(getPurchaseInfoById(id));
//    }
//}