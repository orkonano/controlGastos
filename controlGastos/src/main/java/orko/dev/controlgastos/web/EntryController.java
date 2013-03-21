package orko.dev.controlgastos.web;

import java.util.Arrays;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import orko.dev.controlgastos.model.Entry;
import orko.dev.controlgastos.model.EntryType;

@RequestMapping("/entrys")
@Controller
@RooWebScaffold(path = "entrys", formBackingObject = Entry.class)
public class EntryController {

	void populateEditForm(Model uiModel, Entry entry) {
		uiModel.addAttribute("entry", entry);
		uiModel.addAttribute("entrys", entryService.findEntryByAttributable(false));
		uiModel.addAttribute("entrytypes", Arrays.asList(EntryType.values()));
	}

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Entry());
        return "entrys/create";
    }
}
