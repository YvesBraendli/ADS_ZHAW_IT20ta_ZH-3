package ch.zhaw.ads;

import java.util.*;
import java.lang.reflect.*;

public class Storage {
	public static boolean generationalGCActiv = false; // in Aufgabe 2 verwendet
	public static StringBuffer log = new StringBuffer();
	private static List<Collectable> root;
	private static List<Collectable> youngHeap;
	private static List<Collectable> oldHeap;
	public static boolean youngGenerationOnly = true;


	static {
		clear();
	}
   
	public static void clear() {
		root = new LinkedList<Collectable>();
		youngHeap = new LinkedList<Collectable>();
		// oldHeap erst in Aufgabe 2 verwenden!
		oldHeap = new LinkedList<Collectable>();
	}
   
	/* add  root object */
	public static void addRoot(Collectable obj) {
		root.add(obj);
	}
   
	// create a collectable object of class cls
	public static Collectable _new(String cls, Object arg) {
		Collectable obj = null;
		try {
			// create an object and call constructor
			Constructor cst = Class.forName(getPackage() + cls).getConstructor(new Class[] { arg.getClass()});
			obj = (Collectable) cst.newInstance(new Object[] {arg});
			// add object to heap
			youngHeap.add(obj);
			log.append("New: " + obj.toString() + "\n");
		} catch (Exception ex) {
			log.append("error creating object " + cls + "\n");
		}
		return (Collectable) obj;
	}

	private static String getPackage() {
		Package pack = Storage.class.getPackage();
		if (pack != null) {
			return pack.getName() + ".";
		} else {
			return "";
		}
	}

	/* remove object from heap */
	public static void delete(Collectable obj) {
		if (youngHeap.remove(obj)) {
			if (generationalGCActiv) {
				log.append("Delete young heap: " + obj.toString() + "\n");
			} else {
				log.append("Delete heap: " + obj.toString() + "\n");
			}
		} else if (oldHeap.remove(obj)) {
			log.append("Delete old heap: " + obj.toString() + "\n");
		} else {
			log.append(
					"error trying to delete not existing object " + obj.toString()
					+ "\n");
		}
	}
 
	/* get all root objects */
	public static Iterable<Collectable> getRoot() {
		return new LinkedList<Collectable>(root);
	}

	/* get young heap */
	public static Iterable<Collectable> getYoungHeap() {
		return new LinkedList<Collectable>(youngHeap);
	}

	/* get old heap */
	public static Iterable<Collectable> getOldHeap() {
		return new LinkedList<Collectable>(oldHeap);
	}

	/* get heap */
	public static Iterable<Collectable> getHeap() {
		return new LinkedList<Collectable>(youngHeap);
	}

	/* get references to collectables of an object */
	public static Iterable<Collectable> getRefs(Collectable obj) {
		// get all fields of an object
		Field[] fields = obj.getClass().getFields();
		List<Collectable> fieldList = new LinkedList<Collectable>();
		for (int i = 0; i < fields.length; i++) {
			try {
				Object o = fields[i].get(obj);   
				if (o instanceof Collectable) {
					fieldList.add((Collectable) o);
				}
			} catch (Exception ex) {}
		}
		return fieldList;
	}  

	/* dump an iterator */
	public static void dump(String s, Iterable itr) {
		log.append(s); 
		for (Object o: itr) {
			log.append(" " + o.toString());
		}
		log.append("\n");
	}

	public static String getLog() {
		return log.toString();
	}
 
	private static void mark(Collectable cObject) {
		// to be done Aufgabe 13.1
	}

	private static void sweep() {
		// to be done Aufgabe 13.1 und Aufgabe 13.2
	}

	public static void gc() {
		if (generationalGCActiv) {
			if (youngGenerationOnly) {
				log.append("\nCollector start young generation only\n");
			} else {
				log.append("\nCollector start young and old generation\n");
			}
		}
		else {
			log.append("\nCollector start\n");
		}

		// to be done Aufgabe 13.1 und Aufgabe 13.2

		log.append("Collector end\n");
	}

}