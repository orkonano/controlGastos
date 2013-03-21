package orko.dev.controlgastos.util.map;

import java.util.Map;

public interface ICollectionToMapTransform<T, M> {
	
	public void insertIntoMap(Map<T,M> map, M object);

}
