package com.example.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.domain.Comment;
import com.example.domain.Page;
import com.example.domain.Product;
import com.example.web.form.CommentForm;
import com.example.web.form.SearchForm;

public interface ProductMapper {

	public List<Product> getFirstList();
	
	public Product getProduct(String id);

	public List<Product> getList(@Param("option")SearchForm option, @Param("page")Page page);
	
	public String getTotalCount(@Param("option")SearchForm option);
	
	public List<Comment> getCommentList(String pid);
	
	public List<String> getOrderDetailList(String pid);
	
	public void insertComment(@Param("c")CommentForm comment);
	
	public void deleteComment(String cid);
	
	public void firstInsert(Product p);
	
	public Comment selectComment(String cid);
	
	public void modifyComment(@Param("c")Comment comment);
}
