package com.mysite.gde.client.blog.ui;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public final class HeaderView extends Composite {
    interface Binder extends UiBinder<HTMLPanel, HeaderView> {
    }

    static Binder BINDER = GWT.create(Binder.class);

    public HeaderView() {
        initWidget(BINDER.createAndBindUi(this));
    }
}
