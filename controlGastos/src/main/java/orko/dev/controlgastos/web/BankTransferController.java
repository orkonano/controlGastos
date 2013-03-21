package orko.dev.controlgastos.web;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import orko.dev.controlgastos.model.BankTransfer;

@RequestMapping("/banktransfers")
@Controller
@RooWebScaffold(path = "banktransfers", formBackingObject = BankTransfer.class)
public class BankTransferController {
}
