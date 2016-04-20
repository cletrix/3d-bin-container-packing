package com.skjolberg.packing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.skjolberg.packing.Box;
import com.skjolberg.packing.Container;
import com.skjolberg.packing.Packager;

public class PackagerTest {

	@Test
	public void testStackingSquaresOnSquare() {
		
		List<Box> containers = new ArrayList<Box>();
		containers.add(new Box(10, 10, 1));
		Packager packager = new Packager(containers);
		
		List<Box> products = new ArrayList<Box>();

		products.add(new Box("A", 5, 5, 1));
		products.add(new Box("B", 5, 5, 1));
		products.add(new Box("C", 5, 5, 1));
		products.add(new Box("D", 5, 5, 1));
		
		Container fits = packager.pack(products);
		assertNotNull(fits);
		assertEquals(fits.getLevels().size(), 1);
	}
	
	@Test
	public void testStackingRectanglesOnSquare() {
		
		List<Box> containers = new ArrayList<Box>();
		containers.add(new Box(10, 10, 1));
		Packager packager = new Packager(containers);
		
		List<Box> products = new ArrayList<Box>();

		products.add(new Box("E", 5, 10, 1));
		products.add(new Box("F", 5, 10, 1));

		Container fits = packager.pack(products);
		assertNotNull(fits);
		assertEquals(fits.getLevels().size(), 1);
	}
	
	@Test
	public void testStackingRectanglesOnSquareRectangle() {
		
		List<Box> containers = new ArrayList<Box>();
		containers.add(new Box(10, 10, 1));
		Packager packager = new Packager(containers);
		
		List<Box> products = new ArrayList<Box>();

		products.add(new Box("J", 5, 10, 1));
		products.add(new Box("K", 5, 5, 1));
		products.add(new Box("L", 5, 5, 1));

		Container fits = packager.pack(products);
		assertNotNull(fits);
		assertEquals(fits.getLevels().size(), 1);
	}
	
	@Test
	public void testStackingRectanglesOnSquareRectangleVolumeFirst() {
		
		List<Dimension> containers = new ArrayList<Dimension>();
		containers.add(new Dimension(10, 10, 3));
		Packager packager = new Packager(containers);
		
		List<Box> products = new ArrayList<Box>();

		products.add(new Box("J", 6, 10, 2));
		products.add(new Box("L", 4, 10, 1));
		products.add(new Box("K", 4, 10, 2));

		Container fits = packager.pack(products);
		assertNotNull(fits);
		assertEquals(fits.getLevels().size(), 2);

		assertEquals(1, fits.getLevels().get(fits.getLevels().size() - 1).getHeight());
	}
	
	@Test
	public void testStackingBinary() {
		
		List<Box> containers = new ArrayList<Box>();
		containers.add(new Box(8, 8, 1));
		Packager packager = new Packager(containers);
		
		List<Box> products = new ArrayList<Box>();

		products.add(new Box("J", 4, 4, 1));
		
		for(int i = 0; i < 4; i++) {
			products.add(new Box("K", 2, 2, 1));
		}
		for(int i = 0; i < 16; i++) {
			products.add(new Box("K", 1, 1, 1));
		}
		
		Container fits = packager.pack(products);
		assertNotNull(fits);
		assertEquals(fits.getLevels().size(), 1);
	}


	@Test
	public void testStackingTooHigh() {
		
		List<Box> containers = new ArrayList<Box>();
		containers.add(new Box(10, 10, 5));
		Packager packager = new Packager(containers);
		
		List<Box> products = new ArrayList<Box>();

		products.add(new Box("J", 10, 10, 6));

		Container fits = packager.pack(products);
		assertNull(fits);
	}

	@Test
	public void testStackingTooHighLevel() {
		
		List<Box> containers = new ArrayList<Box>();
		containers.add(new Box(10, 10, 5));
		Packager packager = new Packager(containers);
		
		List<Box> products = new ArrayList<Box>();

		products.add(new Box("J", 10, 10, 5));

		products.add(new Box("J", 5, 10, 1));
		products.add(new Box("K", 5, 5, 1));
		products.add(new Box("L", 5, 5, 1));

		Container fits = packager.pack(products);
		assertNull(fits);
	}	
}
