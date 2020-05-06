package com.eglobal.bo.api.zip.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class HtmlServiceImplTest {


//	public void testPaginado() {
//		float p =   5;
//		float a= 26;
//		float g= a/p;
//	       List<Integer> pagArray = Stream.iterate(0, i -> i + 1)
//	        .filter(i -> i % 5 == 0)
//	        .skip(1)
//	        .limit((int)Math.ceil(g))
//	        .collect(Collectors.toList());
//	        System.out.println(pagArray);
//	       
//	       List<Integer> pag = Stream.iterate(0, i -> i + 1)
//	   	        .filter(i -> i % 1 == 0)
//	   	        .skip(1)
//	   	        .limit((int)Math.ceil(g))
//	   	        .collect(Collectors.toList());
//	       System.out.println(pag);
//	       return ;
//	}
	@Test
	public <T> void testCrealista() {
		float p = 33.0f / 5.0f;
		System.out.println(p);
		List<Integer> pagArray = Stream.iterate(0, i -> i + 1).filter(i -> i % 5 == 0).skip(1).limit((int) Math.ceil(p))
				.collect(Collectors.toList());

		List<List<Integer>> list = getPages(pagArray);

		assertNotNull("Se ha creado la lista", list);
	}

	private <T> List<List<T>> getPages(List<T> pagArray) {
		Collection<T> c = new ArrayList<T>();
		c.addAll((Collection<? extends T>) pagArray);

		int pageSize = 5;

		List<T> list = new ArrayList<T>(c);

		if (pageSize <= 0 || pageSize > list.size())
			pageSize = list.size();
		int numPages = (int) Math.ceil((double) list.size() / (double) pageSize);
		List<List<T>> pages = new ArrayList<List<T>>(numPages);
		for (int pageNum = 0; pageNum < numPages;)
			pages.add(list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size())));

		return pages;

	}

}
