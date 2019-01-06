package com.rest.Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.rest.Entity.Pendiente;

public class PendienteDaoImpl implements IPendienteDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Pendiente findById(long id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Pendiente pendiente = new Pendiente();
		try {
			pendiente = (Pendiente) session.get(Pendiente.class, id);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();

		}
		// TODO Auto-generated method stub
		return pendiente;
	}

	public Pendiente findByName(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	public void savePendiente(Pendiente pendiente) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (pendiente != null) {
			try {
				session.save(pendiente);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}
		}
	}

	public Pendiente updatePendiente(Pendiente pendiente) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (pendiente != null) {
			try {
				session.update(pendiente);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}
		}
		return pendiente;
	}

	public void deletePendienteById(long id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Pendiente pendiente = new Pendiente();
		try {
			pendiente = (Pendiente) session.get(Pendiente.class, id);
			session.delete(pendiente);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();

		}
  	}
    @SuppressWarnings("unchecked")
	public List<Pendiente> findAllPendiente() {
    	List<Pendiente> pendiente=new ArrayList<Pendiente>();
    	Session session= sessionFactory.openSession();
    	pendiente=session.createQuery("From com.rest.Entity.Pendiente").list();
    	return pendiente;
	}

	public void deleteAllCustomers() {
		// TODO Auto-generated method stub

	}

}
