package br.com.wellington.jplay2D.test.window;

import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

import br.com.wellington.jplay2D.window.Window;

public class TestWindow {

	@Test
	void testErroCriar2Instancias() {
		Window.create(800, 600);
		try {
			Window.create(800, 600);
			fail();
		} catch (Exception ex) {

		}

	}

}
