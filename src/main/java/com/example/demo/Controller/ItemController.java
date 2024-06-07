package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Account;
import com.example.demo.Entity.Item;
import com.example.demo.Repository.ItemRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ItemController {
	
	@Autowired//インスタンスを生成
	private HttpSession session;
	
	@Autowired
	private Account account;
	
	@Autowired
	ItemRepository itemRepository;
	
	
	@GetMapping({ "/", "/logout" })
	public String log() {
		session.invalidate();

		return "login";
	}
//名前だけをセット
	@PostMapping("/login")
	public String login(
			@RequestParam("name") String name,
			Model mpodel) {

		account.setName(name);

		return "redirect:/items";
	}

	@GetMapping("/items")
	public String Index(
			@RequestParam(name = "key", required = false) String key,
			Model m) {
		List<Item> list =null;
		
		if(key==null) {
			list = itemRepository.findAll();
		}else if(key !=null) {
			list = itemRepository.findByNameLike("%"+key+"%");
		}
		
		

		m.addAttribute("list", list);
		return "items";
	}

	@GetMapping("/items/add")
	public String creat(Model m) {
		return "addItem";
	}

	@PostMapping("/items/add")
	public String store(
			@RequestParam(name = "categoryId", required = false) Integer categoryId,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "price", required = false) Integer price,
			Model m) {
		Item item = new Item(categoryId, name, price);
		itemRepository.saveAndFlush(item);

		return "redirect:/items";
	}

	@GetMapping("/items/{id}/edit")
	public String update(
			@PathVariable(name = "id") Integer id,
			@RequestParam(name = "categoryId", required = false) Integer categoryId,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "price", required = false) Integer price,
			Model m) {
		Item item = new Item(id, categoryId, name, price);
		itemRepository.saveAndFlush(item);

		return "redirect:/items";
	}

	@PostMapping("/items/{id}/delete")
	public String delete(
			@PathVariable(name = "id") Integer id,
			@RequestParam(name = "categoryId", required = false) Integer categoryId,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "price", required = false) Integer price,
			Model m) {
		Item item = new Item(id, categoryId, name, price);
		itemRepository.delete(item);

		return "redirect:/items";
	}
}