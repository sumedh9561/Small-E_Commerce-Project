package com.masai.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.FeedbackException;
import com.masai.model.Customer;
import com.masai.model.Feedback;
import com.masai.repository.CustomerRepository;
import com.masai.repository.feedbackDao;


//import com.tripy.repositary.IFeedBackRepository;

@Service
public class IFeedBackDaoImpl implements IFeedbackServer {
	@Autowired
	private feedbackDao feedDao;
	@Autowired
	private CustomerRepository customerepo;
	
//	@Autowired
//	private feedbackDao ifeedbackRepo;

	@Override
	public Feedback addFeedback(Feedback feedback) throws FeedbackException {
//		// TODO Auto-generated method stub
	 Optional<Customer> customer= customerepo.findById(feedback.getCustomerId());
	 if(customer.isPresent()) {
			Feedback f = feedDao.save(feedback);
		 System.out.print("helllo");
			return f;
	 }
	 	throw new FeedbackException("user not found of this id");
//		return null;
	}

	@Override
	public Feedback findbyfeedbackid(Integer feedbackid) {
		// TODO Auto-generated method stub
		Feedback op = feedDao.findById(feedbackid).orElseThrow(()->new IllegalArgumentException("feedback not found..!!"));
		return op;
	}




	@Override
	public List<Feedback> viewallfeedbacks() throws FeedbackException {
		// TODO Auto-generated method stub
		Optional<List<Feedback>> fedOptional = Optional.of(feedDao.findAll());
		if (fedOptional.isPresent()) {

			return fedOptional.get();

		}
		throw new FeedbackException("No feedbacks found!");
		
	}

	@Override
	public Feedback delteById(Integer id) throws FeedbackException {
		// TODO Auto-generated method stub
		Optional<Feedback> fedOptional = feedDao.findById(id);
		if (fedOptional.isPresent()) {
			 feedDao.deleteById(id);
			return fedOptional.get();
//			return fedOptional.get();
			

		}
		throw new FeedbackException("No feedbacks found!");
//		return feedback;
	}

	@Override
	public List<Feedback> findbycustomerid(Integer Customerid) throws FeedbackException {
		Optional<Customer> customers= customerepo.findById(Customerid);
		if(customers.isPresent()) {
			//Optional<Feedback> f1=feedDao.findById(customers.get().getCustomerId());
			//return f1.get();
		return	feedDao.findByCustomerId(Customerid);
			
	}
//		return new Feedback();
	throw new FeedbackException("no Data Found");
//		
//	}
	}
	
	

}
