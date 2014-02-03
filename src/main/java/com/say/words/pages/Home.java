package com.say.words.pages;


import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.PageLoaded;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;

import com.say.words.components.SpeechUtils;

/**
 * @author smitap
 *
 */
public class Home {

    @Component
    private SpeechUtils speechUtils;
    
    @InjectComponent
    private Form takeInputForm;
    
    @InjectComponent
    private Zone outputZone;
    
    @Inject
    private Request request;
    
    @Property
    private String inputText;
    
    @Property
    private String outputTxt;
	
    @PageLoaded
	void afterRender()
	{
	   try {
			speechUtils.speak("Hello User."); 
			speechUtils.speak("I will read what you write. Check it out...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	    	
	}
	
	public Object onSuccessFromTakeInputForm()
	{
		if(inputText!=null)
		{
			try {
				speechUtils.speak(inputText);
			} catch (Exception e) {
				e.printStackTrace();
			}
			outputTxt = inputText;
		}
		
		return request.isXHR() ? outputZone.getBody() : null;
	}
}
