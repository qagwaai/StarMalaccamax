package com.qagwaai.starmalaccamax.client.core.mvp.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.qagwaai.starmalaccamax.client.core.mvp.resources.css.MyStylesCss;
import com.qagwaai.starmalaccamax.client.core.mvp.resources.images.MyImages;

public interface MyBundle extends ClientBundle
{
    @Source("css/MyStylesCss.css")
    public MyStylesCss css();
    
    public MyImages images();
}
