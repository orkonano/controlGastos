package orko.dev.controlgastos.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.converter.RooConversionService;

import orko.dev.controlgastos.model.BankAccount;
import orko.dev.controlgastos.model.Entry;
import orko.dev.controlgastos.model.security.Principal;

/**
 * A central place to register application converters and formatters. 
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}
	
	  public Converter<Entry, String> getEntryToStringConverter() {
	        return new org.springframework.core.convert.converter.Converter<orko.dev.controlgastos.model.Entry, java.lang.String>() {
	            public String convert(Entry entry) {
	                return entry.getDescription();
	            }
	        };
	    }
	    
	
	

	public Converter<BankAccount, String> getBankAccountToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<orko.dev.controlgastos.model.BankAccount, java.lang.String>() {
            public String convert(BankAccount bankAccount) {
                return bankAccount.getDescription();
            }
        };
    }

	

	public Converter<Principal, String> getPrincipalToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<orko.dev.controlgastos.model.security.Principal, java.lang.String>() {
            public String convert(Principal principal) {
                return new StringBuilder().append(principal.getUsername()).toString();
            }
        };
    }
}
