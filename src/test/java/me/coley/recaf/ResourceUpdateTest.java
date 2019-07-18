package me.coley.recaf;

import me.coley.recaf.util.EmptyResource;
import me.coley.recaf.workspace.JavaResource;
import me.coley.recaf.workspace.Workspace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the listening map used in {@link me.coley.recaf.workspace.JavaResource}.
 *
 * @author Matt
 */
public class ResourceUpdateTest extends Base {
	private JavaResource resource;
	private Workspace workspace;

	@BeforeEach
	public void setup() {
		resource = new EmptyResource();
		workspace = new Workspace(resource);
	}

	@Test
	public void testClassPut() {
		String valueToPut = "Test";
		// Record values put in the map
		Set<String> putted = new HashSet<>();
		resource.getClasses().getPutListeners().add((name, code) -> putted.add(name));
		// Put value in map
		resource.getClasses().put(valueToPut, new byte[0]);
		// Assert value listener has been fired
		assertTrue(putted.contains(valueToPut));
		assertTrue(resource.getDirtyClasses().contains(valueToPut));
	}

	@Test
	public void testResourcePut() {
		String valueToPut = "Test";
		// Record values put in the map
		Set<String> putted = new HashSet<>();
		resource.getResources().getPutListeners().add((name, code) -> putted.add(name));
		// Put value in map
		resource.getResources().put(valueToPut, new byte[0]);
		// Assert value listener has been fired
		assertTrue(putted.contains(valueToPut));
		assertTrue(resource.getDirtyResources().contains(valueToPut));
	}

	@Test
	public void testClassPutAll() {
		String valueToPut1 = "Test1";
		String valueToPut2 = "Test2";
		Map<String, byte[]> map = new HashMap<>();
		map.put(valueToPut1, new byte[0]);
		map.put(valueToPut2, new byte[0]);
		// Record values put in the map
		Set<String> putted = new HashSet<>();
		resource.getClasses().getPutListeners().add((name, code) -> putted.add(name));
		// Put value in map
		resource.getClasses().putAll(map);
		// Assert value listener has been fired
		assertTrue(putted.contains(valueToPut1));
		assertTrue(putted.contains(valueToPut2));
		assertTrue(resource.getDirtyClasses().contains(valueToPut1));
		assertTrue(resource.getDirtyClasses().contains(valueToPut2));

	}

	@Test
	public void testResourcePutAll() {
		String valueToPut1 = "Test1";
		String valueToPut2 = "Test2";
		Map<String, byte[]> map = new HashMap<>();
		map.put(valueToPut1, new byte[0]);
		map.put(valueToPut2, new byte[0]);
		// Record values put in the map
		Set<String> putted = new HashSet<>();
		resource.getResources().getPutListeners().add((name, code) -> putted.add(name));
		// Put value in map
		resource.getResources().putAll(map);
		// Assert value listener has been fired
		assertTrue(putted.contains(valueToPut1));
		assertTrue(putted.contains(valueToPut2));
		assertTrue(resource.getDirtyResources().contains(valueToPut1));
		assertTrue(resource.getDirtyResources().contains(valueToPut2));
	}

	@Test
	public void testClassRemove() {
		String valueToRemove = "Test";
		// Record values removed from the map
		Set<Object> removed = new HashSet<>();
		resource.getClasses().getRemoveListeners().add(key -> removed.add(key));
		// Remove value from map
		resource.getClasses().remove(valueToRemove);
		// Assert value listener has been fired
		assertTrue(removed.contains(valueToRemove));
	}

	@Test
	public void testResourceRemove() {
		String valueToRemove = "Test";
		// Record values removed from the map
		Set<Object> removed = new HashSet<>();
		resource.getResources().getRemoveListeners().add(key -> removed.add(key));
		// Remove value from map
		resource.getResources().remove(valueToRemove);
		// Assert value listener has been fired
		assertTrue(removed.contains(valueToRemove));
	}
}
