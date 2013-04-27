package org.yspr.yosoypuertoricomobil.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

	static {
		// Add 3 sample items.
		addItem(new DummyItem("Sobre Nosotros", "Sobre Nosotros"));
		addItem(new DummyItem("Somos Puerto Rico", "Somos Puerto rico"));
		addItem(new DummyItem("Nuestros Videos", "Nuestros Videos"));
		addItem(new DummyItem("Tu Aportaci�n", "Tu Aportaci�n"));
		addItem(new DummyItem("Sitio Web", "Sitio Web"));
		addItem(new DummyItem("Instagram", "Instagram"));
		addItem(new DummyItem("YouTube", "YouTube"));
		
		
	}

	private static void addItem(DummyItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class DummyItem {
		public String id;
		public String content;

		public DummyItem(String id, String content) {
			this.id = id;
			this.content = content;
		}

		@Override
		public String toString() {
			return content;
		}
	}
}
