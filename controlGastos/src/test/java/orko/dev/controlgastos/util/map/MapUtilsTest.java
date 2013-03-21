package orko.dev.controlgastos.util.map;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
public class MapUtilsTest {

	@Autowired
	private MapUtils mapUtils;

	@Test
	public void test() {
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		Map<Integer,Integer> map = mapUtils.createMap(list, new ICollectionToMapTransform<Integer, Integer>() {

			@Override
			public void insertIntoMap(Map<Integer, Integer> map, Integer object) {
				map.put(object, object*2);
			}
		
		});
		
		Assert.assertEquals((Integer)map.get(1), (Integer)2);
		Assert.assertEquals((Integer)map.get(2), (Integer)4);
		Assert.assertEquals((Integer)map.get(3), (Integer)6);
	}

}
