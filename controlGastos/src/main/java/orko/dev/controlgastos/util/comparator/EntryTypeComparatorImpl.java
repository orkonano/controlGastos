package orko.dev.controlgastos.util.comparator;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import orko.dev.controlgastos.model.EntryType;

@Component
public class EntryTypeComparatorImpl implements Comparator<EntryType> {

	@Override
	public int compare(EntryType o1, EntryType o2) {
		switch (o1) {
			case OPEN_BALANCE:
				switch (o2) {
				case OPEN_BALANCE:
					return 0;
				case SAVING:
					return -1;
				case INGRESS:
					return -1;
				case LOSS:
					return -1;
			}
			case INGRESS:
				switch (o2) {
				case OPEN_BALANCE:
					return 1;
				case SAVING:
					return -1;
				case INGRESS:
					return 0;
				case LOSS:
					return -1;
				}
			case SAVING:
				switch (o2) {
					case OPEN_BALANCE:
						return 1;
					case SAVING:
						return 0;
					case INGRESS:
						return 1;
					case LOSS:
						return -1;
					}
			case LOSS:
				switch (o2) {
					case OPEN_BALANCE:
						return 1;
					case SAVING:
						return 1;
					case INGRESS:
						return 1;
					case LOSS:
						return 0;
					}
			default: return 0;
		}
	}

}
