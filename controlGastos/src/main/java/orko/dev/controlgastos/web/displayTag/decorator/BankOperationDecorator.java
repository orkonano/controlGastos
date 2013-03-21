package orko.dev.controlgastos.web.displayTag.decorator;

import java.text.Format;
import java.text.NumberFormat;

import org.displaytag.decorator.TableDecorator;
import org.hamcrest.core.IsInstanceOf;

import orko.dev.controlgastos.model.BankAccount;
import orko.dev.controlgastos.model.BankTransfer;
import orko.dev.controlgastos.model.EconomicFact;
import orko.dev.controlgastos.model.interfaces.BankOperation;

import com.hp.gagawa.java.elements.A;

public class BankOperationDecorator extends TableDecorator {

	private Format formatoImporte = NumberFormat.getCurrencyInstance();
	
	public String getAmount(){
		BankOperation bankOperation = (BankOperation) this.getCurrentRowObject();
		A link = new A();
		link.setHref("../economicfacts/"+bankOperation.getId());
		link.appendText(formatoImporte.format(this.isNegativeResult(bankOperation)?bankOperation.getAmount().negate():bankOperation.getAmount()).toString());
		return link.write();
	}
	
	private String getUrl(BankOperation bankOperation){
		if (bankOperation instanceof EconomicFact){
			return "../economicfacts/";
		}else{
			return "../banktransfers/";
		}
	}
	
	private boolean isNegativeResult(BankOperation bankOperation){
		if (bankOperation instanceof EconomicFact){
			return ((EconomicFact)bankOperation).getEntry().isNegativeResult();
		}else{
			BankAccount baOrigen = (BankAccount) this.getPageContext().getRequest().getAttribute("bankaccount");
			return !((BankTransfer)bankOperation).isIngress(baOrigen);
		}
	}
	

}
