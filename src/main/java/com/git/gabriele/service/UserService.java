//package com.git.gabriele.service;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.transaction.Transactional;
//import javax.ws.rs.BadRequestException;
//import javax.ws.rs.GET;
//
//import com.git.gabriele.controller.UserResource;
//import com.git.gabriele.model.User;
//
//@ApplicationScoped
//public class UserService extends UserResource{
//	
//	@Transactional
//	@GET
//    public String findCheese(String user) {
//        if(User.getEntityManager() == null) { 
//            throw new BadRequestException();
//        }
//		return user;
//        
//        	
//    }
////	public  userRemove (Long idRemove) {
////		User user = User.findById(idRemove);
////		if(user!= null) {
////			user.setName(idRemove.getName());
////			user.setAge(idRemove.getAge());
////			return userRemove.ok().build();
////		}
////		
////		return Response.status(Response.Status.NOT_FOUND).build();
////	}
//}
