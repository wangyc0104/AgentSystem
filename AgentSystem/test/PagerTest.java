import static org.junit.Assert.*;

import org.agent.common.PageSupport;
import org.junit.Test;


public class PagerTest {

	@Test
	public void test() {
		PageSupport pager = new PageSupport();
		pager.setPageSize(3);
		pager.setTotalCount(76);
		pager.setPage(5);
		
		System.out.println(pager.getPageCount()+"页\n");
		
		for(Integer i:pager.getPrevPages())
			System.out.println(i);
		
		System.out.println("////////这是第5个测试输出行////////");
		
		for(Integer i:pager.getNextPages())
			System.out.println(i);
	}

}
