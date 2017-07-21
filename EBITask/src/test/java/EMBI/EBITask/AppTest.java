package EMBI.EBITask;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}
	
	public void testFormatList() {
		App app = new App();
		List<String> actualOutput = app.formatList("A00000, A0001, ERR000111, ERR000112, ERR000113, ERR000115, ERR000116, ERR100114, ERR200000001, ERR200000002, ERR200000003, DRR2110012, SRR211001, ABCDEFG1");
		List<String> expectedOutput = new ArrayList<>();
		expectedOutput.add("A00000");
		expectedOutput.add("A0001");
		expectedOutput.add("ABCDEFG1");
		expectedOutput.add("DRR2110012");
		expectedOutput.add("ERR000111-ERR000113");
		expectedOutput.add("ERR000115-ERR000116");
		expectedOutput.add("ERR100114");
		expectedOutput.add("ERR200000001-ERR200000003");
		expectedOutput.add("SRR211001");
		assertEquals(expectedOutput, actualOutput);
	}
	public void testAreAllDigits() {
		assertEquals(true, App.areAllDigits("00001"));
		assertEquals(false, App.areAllDigits("ABCD001"));
	}
}
