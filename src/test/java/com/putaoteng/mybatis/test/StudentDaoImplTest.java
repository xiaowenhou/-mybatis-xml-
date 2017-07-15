package com.putaoteng.mybatis.test;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.putaoteng.mybatis.model.Student;
import com.putaoteng.mybatis.service.StudentDaoImpl;
import com.putaoteng.mybatis.utils.Log;
import com.putaoteng.mybatis.utils.LogLevel;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class StudentDaoImplTest {
	private StudentDaoImpl studentDaoImpl = null; 
	
	@Before
	public void setUp(){
		String configPath = "sqlMapConfig.xml";
	
		this.studentDaoImpl = new StudentDaoImpl(configPath);
	}
	
	//增加用户测试
	@Test
	public void testAddStudent() {
		Student student = new Student();
		long studentId=0;
		student.setId(1);
		student.setName("zhangsan");
		student.setQqNumber(123456789);
		student.setProfession("java");
		student.setJoinDate("2017年1月1日");
		student.setSchool("清华大学");
		student.setOnlineNumber("9527");
		student.setDailyLink("www.baidu.com");
		student.setDesire("牛鼻");
		student.setMsgSource("知乎");
		student.setBrother("大师兄");
		student.setCreateAt(20170715133333L);
		student.setUpdateAt(20170715133356L);
		studentId = studentDaoImpl.addStudent(student);
		System.out.println(studentId);
		
		Student student2 = new Student();
		student2.setId(2);
		student2.setName("lisi");
		student2.setQqNumber(123456789);
		student2.setProfession("qianduan");
		student2.setJoinDate("2017年1月1日");
		student2.setSchool("上海交通大学");
		student2.setOnlineNumber("9527");
		student2.setDailyLink("www.baidu.com");
		student2.setDesire("牛鼻");
		student2.setMsgSource("知乎");
		student2.setBrother("二师兄兄");
		student2.setCreateAt(20170715133333L);
		student2.setUpdateAt(20170715133356L);
		studentId = studentDaoImpl.addStudent(student2);
		System.out.println(studentId);
		
		Student student3 = new Student();
		student3.setId(3);
		student3.setName("王五");
		student3.setQqNumber(123456789);
		student3.setProfession("qianduan");
		student3.setJoinDate("2017年8月1日");
		student3.setSchool("北京大学");
		student3.setOnlineNumber("9527");
		student3.setDailyLink("www.baidu.com");
		student3.setDesire("牛鼻");
		student3.setMsgSource("知乎");
		student3.setBrother("小师弟");
		student3.setCreateAt(20170715133333L);
		student3.setUpdateAt(20170715133356L);
		studentId = studentDaoImpl.addStudent(student3);
		System.out.println(studentId);
		
		testQueryStudentAll();
	}
	
	//删除用户测试
	@Test
	public void testDeleteStudent() {
		boolean result = studentDaoImpl.deleteStudent(3);
		
		Assert.assertTrue(result);
		
		testQueryStudentAll();
	}
	
	//更新用户测试
	@Test
	public void testUpdateStudent() {
		Student student = studentDaoImpl.queryStudentById(2);
		
		student.setName("zzzzzzzz");
		student.setSchool("xxxxxxxx");
		
		boolean result = studentDaoImpl.updateStudent(student);
		Assert.assertTrue(result);
		
		testQueryStudentAll();
	}
	
	//按用户ID查询测试
	@Test
	public void testQueryUserById() {
		Student student = studentDaoImpl.queryStudentById(1);
		System.out.println(student);
		
		//测试日志输出情况
		Log.loggerCreate(LogLevel.INFO, "Hello, Java");
	}
	
	//按用户姓名查询测试
	@Test
	public void testQueryStudentByName() {
		String name = "zhangsan";
		List<Student> list = studentDaoImpl.queryStudentByName(name);

		for (Student student : list) {
			System.out.println(student.toString());
		}
	}
	
	//查询所有用户测试
	@Test
	public void testQueryStudentAll() {
		List<Student> list = studentDaoImpl.queryStudentAll();

		for (Student student : list) {
			System.out.println(student.toString());
		}
	}
	
	@Test
	public void testCountTableLength() {
		String field = "id";
		int result = studentDaoImpl.countTableLength(field);
		
		System.out.println(result);
	}
	
	@Test
	public void testClearTable() {
		studentDaoImpl.clearTable ();
		
		testQueryStudentAll();
	}

}
