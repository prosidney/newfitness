package br.com.newfitness.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings({"unchecked","rawtypes"})
public abstract class AbstractDao<T> implements GenericDao<T>{
	protected static final Logger log =  Logger.getLogger(AbstractDao.class.getName()); 
	
	private final Class typeClass;
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	public List<T> findAll(){
		log.info("finding all " + typeClass.getName() + " instances");
		StringBuilder hql = new StringBuilder("SELECT obj FROM " + typeClass.getName() + " obj");
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery(hql.toString());
		
/*		return sessionFactory.getCurrentSession().createCriteria(typeClass).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();*/
		return (List<T>) query.list();
	}
	
	/** 
	 * @param argClass 
	 */  
	public AbstractDao(Class argClass) {  
		typeClass = argClass;  
	} 	

	public T save(T instance){
		log.info("inserindo dados na tabela " + typeClass.getName());
		sessionFactory.getCurrentSession().saveOrUpdate(instance);
		return instance;		
	}
	
	public List<T> save(List<T> instance){
		log.info("inserindo dados na tabela " + typeClass.getName());
		
		for (T t : instance) {
			sessionFactory.getCurrentSession().saveOrUpdate(t);
		}
		return instance;		
	}
	
	public T merge(T instance){
		log.info("inserindo dados na tabela " + typeClass.getName());
		sessionFactory.getCurrentSession().merge(instance);
		return instance;		
	}

	public void delete(T instance){
		log.info("Deletando dados na tabela " + typeClass.getName());
		sessionFactory.getCurrentSession().delete(instance);
	}
	
	public T find(Integer id) {
		StringBuilder hql = new StringBuilder("SELECT obj FROM " + typeClass.getName() + " obj WHERE obj.id = :id");
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery(hql.toString());
		query.setParameter("id", id);
		
		return (T) query.uniqueResult();
	}
}
