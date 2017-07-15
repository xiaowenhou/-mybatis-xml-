package com.putaoteng.mybatis.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.putaoteng.mybatis.dao.StudentDao;
import com.putaoteng.mybatis.model.Student;

public class StudentDaoImpl implements StudentDao {
	private SqlSessionFactory sqlSessionFactory = null;
	
	public StudentDaoImpl(String configPath){
		initFactory(configPath);
	}
	
	private void initFactory(String resource){
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public long addStudent(Student student) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = sqlSession.insert("addStudent", student);
		sqlSession.commit();
		sqlSession.close();
		
		return result==1 ? student.getId() : 0;
	}

	public boolean deleteStudent(long id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = sqlSession.delete("deleteStudent", id);
		sqlSession.commit();
		sqlSession.close();
		
		return result==1? true : false;
	}

	public boolean updateStudent(Student student) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = sqlSession.update("updateStudent", student);
		sqlSession.commit();
		sqlSession.close();
		
		return result==1? true : false;
	}

	
	public int countTableLength(String field) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int result = sqlSession.selectOne("countTableLength", field);
		
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	public Student queryStudentById(long id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Student Student = (Student)sqlSession.selectOne("queryStudentById", id);
		sqlSession.close();
		return Student;
	}

	public List<Student> queryStudentAll() {
		List<Student> list = new ArrayList<Student>();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		list = sqlSession.selectList("queryStudentAll");
		sqlSession.close();
		return list;
	}

	public List<Student> queryStudentByName(String name) {
		List<Student> list = new ArrayList<Student>();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		list = sqlSession.selectList("queryStudentByName", name);
		sqlSession.close();
		return list;
	}

	public void clearTable() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("clearTable");
		return ;
	}
}
