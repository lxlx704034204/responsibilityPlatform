package com.orbit.actions;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.json.annotations.JSON;

/**
 * <code>Set welcome message.</code>
 */

public class HelloAction extends ActionSupport {

    @JSON
    public String execute() throws Exception {
        setMessage(getText(MESSAGE));
        return SUCCESS;
    }

    /**
     * Provide default valuie for Message property.
     */
    public static final String MESSAGE = "hello.message";

    /**
     * Field for Message property.
     */
    private String message;

    /**
     * Return Message property.
     *
     * @return Message property
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set Message property.
     *
     * @param message Text to display on HelloWorld page.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}