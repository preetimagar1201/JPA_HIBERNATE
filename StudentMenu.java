package com.ty.HIBERNATE.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.HIBERNATE.bean.Student1;

public class StudentMenu {

	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws InvalidChoiceException 
	{
		
	System.out.println("==================Welcome to student-Block==================");
	
	while(true)
	{
	System.out.println("Enter Your Choice: ");
	System.out.println("\n1.Insert student data\n2.View Students\n3.View Student\n4.Update Student\n5.Delete student Data ");
	int ch = sc.nextInt();
	try {
		switch (ch) {
		case 1:insertstudent();
			break;
		case 2 : ViewStudents();
			break;
		case 3 : ViewStudent();
		break;
		case 4 : updateStudent();
		break;
		case 5: modified();
		break;
		}
	} 
	
	catch (Exception e) 
	{
		throw new InvalidChoiceException("Invalid Input");
		//e.printStackTrace();
	}
	}
	
}

private static void modified() throws InvaildIdException {
	try {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the id to be Delete: ");
		int sid = sc.nextInt();
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("stu");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		transaction.begin();

		String delete = "Delete from Student1 where sid = :sid";
		
		Query query = manager.createQuery(delete);
		
	query.setParameter("sid", sid);
		
		int result = query.executeUpdate();
		transaction.commit();
		
	}
	catch (Exception e) 
	{
		throw new InvaildIdException("Enter proper Id");
		//e.printStackTrace();
	}
	}

private static void updateStudent() {
	try {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("stu");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		transaction.begin();
		int sid =0;
		String update="";
		System.out.println("Enter your Selection\n1.UpdateName\n2.Updatemarks\n3.updateAge");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:	System.out.print("Enter id: ");
		 sid = sc.nextInt();
			System.out.print("Enter the name: ");
			String sname = sc.next().trim().toLowerCase();
		
			 update = "update Student1 set sname = :sname  where sid = :sid";
			Query query = manager.createQuery(update);
			query.setParameter("sname", sname);
			query.setParameter("sid", sid);
			 query.executeUpdate();
			transaction.commit();
			break;
		
		case 2:
			   System.out.print("Enter sid: ");
				sid= sc.nextInt();
			System.out.print("Enter the marks to update: ");
			int marks=sc.nextInt();
         
			 update = "update Student1 set marks = :marks  where sid = :sid";
			Query queryS= manager.createQuery(update);
			queryS.setParameter("marks", marks);
			queryS.setParameter("sid", sid);
			 queryS.executeUpdate();
			transaction.commit();
			break;
			
		case 3:
			System.out.print("Enter sid : ");
			sid = sc.nextInt();
			System.out.print("Enter new age : ");
			int age = sc.nextInt();
			
			 update = "update Student1 set age = :age  where sid = :sid";
			Query query1 = manager.createQuery(update);
			query1.setParameter("age", age);
			query1.setParameter("sid", sid);
			 query1.executeUpdate();
			transaction.commit();
			break;	
		default:
			break;
		}

		
	}
	catch (Exception e) 
	{
		// TODO: handle exception
		e.printStackTrace();
	}	
	}

private static void ViewStudent() {
		
	try {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("stu");
		EntityManager manager = factory.createEntityManager();
		System.out.print("Enter the sid to be search: ");
		int sid = sc.nextInt();
		String find = "from Student1 where sid = :sid";

		Query query = manager.createQuery(find);
		
		query.setParameter("sid", sid);
		
		List<Student1> list = query.getResultList();

		Student1 e1 = (Student1) query.getSingleResult();
		System.out.println(e1);
	
	} 
	catch (Exception e) 
	{
		// TODO: handle exception
		e.printStackTrace();
	}
	}

private static void ViewStudents() {
	try {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("stu");
		EntityManager manager = factory.createEntityManager();

		String findAll = "from Student1";

		Query query = manager.createQuery(findAll);
		
		List<Student1> list = query.getResultList();

		for (Student1 student : list)
		{
			System.out.println(student);
		}
	} 
	catch (Exception e) 
	{
		// TODO: handle exception
		e.printStackTrace();
	}
		
	}

private static void insertstudent() {
	// TODO Auto-generated method stub
	try {
		boolean b = false;
			while(!b) {
				
			
			Student1 student = new Student1();
			
			System.out.print("Enter the sid: ");
			int sid = sc.nextInt(); 
			
			System.out.print("Enter the student Name: ");
			String name = sc.next().toLowerCase().trim();
			
			System.out.print("Enter the Student marks ");
			int marks= sc.nextInt();
			
			System.out.print("Enter the age ");
			int age = sc.nextInt();
			
			student.setSid(sid);
			student.setSname(name);
			
			student.setMarks(marks);
			student.setAge(age);
			
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("stu");
			EntityManager manager = factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			
			
			manager.persist(student);
			transaction.commit();
			
			System.out.println("If you want to continue: y/Y");
			char c= sc.next().charAt(0);
			if (c == 'y' || c=='Y')
			{
				b= false;
			}
			else
				b= true;
			
			}
		}
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	System.out.println("==================The END !!!==================");
}
}
