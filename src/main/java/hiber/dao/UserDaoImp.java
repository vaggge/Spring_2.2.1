package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public User getUserByCar(String carName, int series) {
      Session session = sessionFactory.getCurrentSession();
      Query<User> query = session.createQuery("SELECT u FROM User u JOIN u.car с WHERE с.name = ?1 and с.series = ?2", User.class);
      query.setParameter(1, carName);
      query.setParameter(2, series);
      return query.uniqueResult();
   }

}
