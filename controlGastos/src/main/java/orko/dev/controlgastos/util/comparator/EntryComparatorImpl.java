package orko.dev.controlgastos.util.comparator;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orko.dev.controlgastos.model.Entry;

@Component
public class EntryComparatorImpl implements Comparator<Entry> {
	
	@Autowired
	private EntryTypeComparatorImpl entryTypeComparator;
	@Autowired
	private TreeComparatorImpl<Integer> treeComparator;

	@Override
	public int compare(Entry o1, Entry o2) {
		int ret = entryTypeComparator.compare(o1.getEntryType(), o2.getEntryType());
		if (ret == 0){
			ret = treeComparator.compare(o1, o2);
		}
		return ret;
	}

}
