package com.example.webapp;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;

@Route("")
public class PersonView extends VerticalLayout {

	public PersonView(PersonService service) {
		GridCrud<Person> crud = new GridCrud<>(Person.class, new HorizontalSplitCrudLayout());
		crud.setCrudListener(service);
		crud.getCrudFormFactory().setVisibleProperties("name");
		crud.setSizeFull();
		add(crud);
		setSizeFull();
	}
	
}
