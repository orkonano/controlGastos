package orko.dev.controlgastos.web.displayTag.decorator;

import java.math.BigDecimal;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Map;

import orko.dev.controlgastos.model.BudgetEntry;
import orko.dev.controlgastos.model.Entry;
import orko.dev.controlgastos.model.EntryType;
import orko.dev.controlgastos.structs.BudgetEntryExecuted;
import orko.dev.decorator.TotalTableDecorator;
import orko.dev.decorator.util.Grouper;
import orko.dev.decorator.util.GrouperType;
import orko.dev.decorator.util.Totalization;

import com.hp.gagawa.java.elements.A;

@Grouper(propertyEvaluationName="grouperProperty",propertyTypeName="grouperTypeProperty")
public class BudgetListDecorator extends TotalTableDecorator {
	
	private Format formato = NumberFormat.getCurrencyInstance();
	private Format formatoPorcentaje = NumberFormat.getPercentInstance();
	private Map<Long,BudgetEntry> mapBudgetEntries;
	private Map<Long,BudgetEntryExecuted> mapBudgetEntriesExecuted;
	
	public String getGrouperProperty(){
		Entry entry = (Entry) this.getCurrentRowObject();
		return entry.getEntryType().equals(EntryType.OPEN_BALANCE)?EntryType.INGRESS.toString():entry.getEntryType().toString();
	}
	
	public GrouperType getGrouperTypeProperty(){
		Entry entry = (Entry) this.getCurrentRowObject();
		return entry.isNegativeResult()?GrouperType.SUBTRACT:GrouperType.ADD;
	}
	
	
	public Map<Long, BudgetEntry> getMapBudgetEntries() {
		if (mapBudgetEntries == null){
			mapBudgetEntries = (Map<Long, BudgetEntry>) this.getPageContext().getRequest().getAttribute("budgetEntries");
		}
		return mapBudgetEntries;
	}



	public Map<Long, BudgetEntryExecuted> getMapBudgetEntriesExecuted() {
		if (mapBudgetEntriesExecuted == null){
			mapBudgetEntriesExecuted = (Map<Long, BudgetEntryExecuted>) this.getPageContext().getRequest().getAttribute("budgetEntriesExecuted");
		}
		return mapBudgetEntriesExecuted;
	}



	@Totalization(owner=false,propertyTotalLinked="presupuestadoAmount")
	public String getPresupuestado(){
		Entry entry = (Entry) this.getCurrentRowObject();
		BigDecimal amount = this.getPresupuestadoAmount();
		if (amount != null){
			BudgetEntry be = this.getMapBudgetEntries().get(entry.getId());
			A link = new A();
			link.setHref("../budgetentrys/"+be.getId());
			link.appendText(formato.format(amount).toString());
			return link.write();
		}else{
			return "---";
		}
	}
	
	public BigDecimal getPresupuestadoAmount(){
		Entry entry = (Entry) this.getCurrentRowObject();
		if (this.getMapBudgetEntries().containsKey(entry.getId())){
			BudgetEntry be = this.getMapBudgetEntries().get(entry.getId());
			return be.getAmount();
		}
		return null;
	}
	
	@Totalization(owner=false,propertyTotalLinked="ejecutadoAmount")
	public String getEjecutado(){
		Entry entry = (Entry) this.getCurrentRowObject();
		BigDecimal amount = this.getEjecutadoAmount();
		if (amount != null){
			BudgetEntryExecuted bee = getMapBudgetEntriesExecuted().get(entry.getId());
			A link = new A();
			link.setHref("../economicfacts?budgetId="+bee.getBudget().getId()+"&entryId="+bee.getEntry().getId());
			link.appendText(formato.format(amount).toString());
			return link.write();
		}else{
			return "---";
		}
	}
	
	public BigDecimal getEjecutadoAmount(){
		Entry entry = (Entry) this.getCurrentRowObject();
		if (getMapBudgetEntriesExecuted().containsKey(entry.getId())){
			BudgetEntryExecuted bee = getMapBudgetEntriesExecuted().get(entry.getId());
			return bee.getAmount();
		}else{
			return null;
		}
	}
	
	public String getPorcentajeEjecutado(){
		Entry entry = (Entry) this.getCurrentRowObject();
		BigDecimal presupuestado = this.getMapBudgetEntries().containsKey(entry.getId())?this.getMapBudgetEntries().get(entry.getId()).getAmount():BigDecimal.ZERO;
		BigDecimal ejecutado = getMapBudgetEntriesExecuted().containsKey(entry.getId())?getMapBudgetEntriesExecuted().get(entry.getId()).getAmount():BigDecimal.ZERO;
		if (presupuestado.doubleValue() != 0){
			return formatoPorcentaje.format(ejecutado.doubleValue()/presupuestado.doubleValue()).toString();
		}else{
			return "---";
		}
	}

}
