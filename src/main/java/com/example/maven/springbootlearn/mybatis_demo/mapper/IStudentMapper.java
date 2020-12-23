package com.example.maven.springbootlearn.mybatis_demo.mapper;

import java.util.List;

import com.example.maven.springbootlearn.mybatis_demo.entity.Student;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IStudentMapper{
    List<Student> selectStudent(Student student);

	int insertStudent(Student s);
}