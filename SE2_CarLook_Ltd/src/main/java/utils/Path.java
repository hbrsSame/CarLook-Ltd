package utils;

import com.vaadin.server.VaadinService;

public class Path {

    public final static String BASEPATH = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
}
