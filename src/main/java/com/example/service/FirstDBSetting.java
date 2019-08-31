package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Product;
import com.example.persistence.ProductMapper;

@SuppressWarnings("unused")
@Service
public class FirstDBSetting {

	@Transactional
	public void firstInsert(ProductMapper mapper) {
		String[] bedroom= {"bed", "single_bed", "loft_bed", "bed_side_table", "bed_cover"};
		String[] storage = {"wardrobe", "chest", "dresser"};
		String[] lighting = {"table_lamp", "floor_lamp", "spotlight"};
		String[] decoration = {"clock", "mirror"};
		String[] living = {"living", "sofa"};
		// bedroom
		for (int i = 0; i < bedroom.length; i++) {
			Product p = new Product();
			p.setCategoryId("1");
			p.setDetails("bed room interior");
			p.setPrice(5400);
			p.setStockQuantity(10);
			int imagecount = 0;
			if (bedroom[i].equals("bed")) { imagecount = 12; }
			if (bedroom[i].equals("single_bed")) { imagecount = 10; }
			if (bedroom[i].equals("loft_bed")) { imagecount = 8; }
			if (bedroom[i].equals("bed_side_table")) { imagecount = 16; }
			if (bedroom[i].equals("bed_cover")) { imagecount = 10; }
			for (int j = 1; j <= imagecount; j++) {
				String imagename = "0";
				if (j < 10) { imagename = "00"+j; 
				} else {imagename = "0"+j; }
				p.setName(bedroom[i]+imagename);
				p.setImage(imagename+"_"+bedroom[i]);
				
				mapper.firstInsert(p);
				System.out.println("success");
			}
		}
		// storage
		for (int i = 0; i < storage.length; i++) {
			Product p = new Product();
			p.setCategoryId("9");
			p.setDetails("storage interior");
			p.setPrice(3000);
			p.setStockQuantity(5);
			int imagecount = 12;
			if (storage[i].equals("dresser")) { imagecount = 5; }
			for (int j = 1; j <= imagecount; j++) {
				String imagename = "0";
				if (j < 10) { imagename = "00"+j; 
				} else {imagename = "0"+j; }
				p.setName(storage[i]+imagename);
				p.setImage(imagename+"_"+storage[i]);
				
				mapper.firstInsert(p);
				System.out.println("success");
			}
		}
		// lighting
		for (int i = 0; i < lighting.length; i++) {
			Product p = new Product();
			p.setCategoryId("8");
			p.setDetails("lighting interior");
			p.setPrice(1800);
			p.setStockQuantity(3);
			int imagecount = 0;
			if (lighting[i].equals("table_lamp")) { imagecount = 17; }
			if (lighting[i].equals("floor_lamp")) { imagecount = 10; }
			if (lighting[i].equals("spotlight")) { imagecount = 4; }
			for (int j = 1; j <= imagecount; j++) {
				String imagename = "0";
				if (j < 10) { imagename = "00"+j; 
				} else {imagename = "0"+j; }
				p.setName(lighting[i]+imagename);
				p.setImage(imagename+"_"+lighting[i]);
				
				mapper.firstInsert(p);
				System.out.println("success");
			}
		}
		// kitchen
		for (int i = 0; i < 1; i++) {
			Product p = new Product();
			p.setCategoryId("2");
			p.setDetails("kitchen interior");
			p.setPrice(200);
			p.setStockQuantity(6);
			int imagecount = 13;
			for (int j = 1; j <= imagecount; j++) {
				String imagename = "0";
				if (j < 10) { imagename = "00"+j; 
				} else {imagename = "0"+j; }
				p.setName("table"+imagename);
				p.setImage(imagename+"_"+"table");
				
				mapper.firstInsert(p);
				System.out.println("success");
			}
		}
		// bathroom
		for (int i = 0; i < 1; i++) {
			Product p = new Product();
			p.setCategoryId("3");
			p.setDetails("bathroom interior");
			p.setPrice(2300);
			p.setStockQuantity(5);
			int imagecount = 20;
			for (int j = 1; j <= imagecount; j++) {
				String imagename = "0";
				if (j < 10) { imagename = "00"+j; 
				} else {imagename = "0"+j; }
				p.setName("bath"+imagename);
				p.setImage(imagename+"_"+"bath");
				
				mapper.firstInsert(p);
				System.out.println("success");
			}
		}
		// dining
		for (int i = 0; i < 1; i++) {
			Product p = new Product();
			p.setCategoryId("6");
			p.setDetails("dining interior");
			p.setPrice(4400);
			p.setStockQuantity(7);
			int imagecount = 25;
			for (int j = 1; j <= imagecount; j++) {
				String imagename = "0";
				if (j < 10) { imagename = "00"+j; 
				} else {imagename = "0"+j; }
				p.setName("dining"+imagename);
				p.setImage(imagename+"_"+"dining");
				
				mapper.firstInsert(p);
				System.out.println("success");
			}
		}
		// decorations
		for (int i = 0; i < decoration.length; i++) {
			Product p = new Product();
			p.setCategoryId("5");
			p.setDetails("decorations interior");
			p.setPrice(1500);
			p.setStockQuantity(11);
			int imagecount = 12;
			if (decoration[i].equals("mirror")) { imagecount = 15; }
			for (int j = 1; j <= imagecount; j++) {
				String imagename = "0";
				if (j < 10) { imagename = "00"+j; 
				} else {imagename = "0"+j; }
				p.setName(decoration[i]+imagename);
				p.setImage(imagename+"_"+decoration[i]);
				
				mapper.firstInsert(p);
				System.out.println("success");
			}
		}		
		// living
		for (int i = 0; i < living.length; i++) {
			Product p = new Product();
			p.setCategoryId("7");
			p.setDetails("living interior");
			p.setPrice(6000);
			p.setStockQuantity(11);
			int imagecount = 20;
			if (living[i].equals("sofa")) { imagecount = 16; }
			for (int j = 1; j <= imagecount; j++) {
				String imagename = "0";
				if (j < 10) { imagename = "00"+j; 
				} else {imagename = "0"+j; }
				p.setName(living[i]+imagename);
				p.setImage(imagename+"_"+living[i]);
				
				mapper.firstInsert(p);
				System.out.println("success");
			}
		}		

	}
	
}
