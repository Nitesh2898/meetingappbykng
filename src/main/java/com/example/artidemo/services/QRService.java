//package com.example.artidemo.services;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.artidemo.model.QRCode;
//import com.example.artidemo.repositories.QRRepository;
//
//@Service
//public class QRService {
//	
//	@Autowired
//	private QRRepository qrRepository;
//	
//	public QRCode save(QRCode entity)
//	{
//		return qrRepository.save(entity);
//		
//	}
//	
////	public QRCode getQRById(long id){
////		return qrRepository.findOne(id);
////	}
////	
////	public List<QRCode> getAllQR(){
////		return qrRepository.findAll();
////	}
//	
//	public void delete(long id)
//	{
//		qrRepository.delete(id);
//	}
//}
