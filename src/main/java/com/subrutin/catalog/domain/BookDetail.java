package com.subrutin.catalog.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "book_detail")
public class BookDetail implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "settings")
	private String settings;
	
	@Column(name = "thumbnail")
	private String thumbnail;
	
	@OneToOne
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;
}
