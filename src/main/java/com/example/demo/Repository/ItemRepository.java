package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	public abstract List<Item> findByNameLike(String key);
}
