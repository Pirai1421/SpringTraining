package com.example.OracleDb.dao;

import com.example.OracleDb.Entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentdaoImpl implements Studentdao {
    private EntityManager entityManager;
    @Autowired
    public StudentdaoImpl(EntityManager entityManager) {
        this.entityManager=entityManager;
    }
    @Override
    @Transactional
    public void save(Student student){
        entityManager.persist(student);


    }
    public  Student findbyId(Integer id){
        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findall() {
        TypedQuery<Student> theQuery=entityManager.createQuery("FROM Student order by lastname",Student.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findbyLastName(String lastname) {
        TypedQuery<Student> theQuery=entityManager.createQuery("FROM Student where lastname=:p",Student.class);
        theQuery.setParameter("p",lastname);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public int update() {
        int numsofRows=entityManager.createQuery("update Student set lastname='soodan G' where firstname='pirai'").executeUpdate();

        return numsofRows;
    }

    @Transactional
    public void updates(Student s){
        entityManager.merge(s);
    }

    @Override
    @Transactional
    public void removes(int id) {
        Student s=entityManager.find(Student.class,id);
//        Student s2 = entityManager.merge(s);
        entityManager.remove(s);

    }
}
