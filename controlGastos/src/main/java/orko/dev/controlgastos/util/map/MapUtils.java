package orko.dev.controlgastos.util.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MapUtils {
	
	public <T,M> Map<T,M> createMap(Collection<M> collectionToMap, ICollectionToMapTransform<T,M> transform){
		Map<T,M> mapToReturn = new HashMap<T,M>();
		for (M m : collectionToMap) {
			transform.insertIntoMap(mapToReturn, m);
		}
		return mapToReturn;
	}
}
