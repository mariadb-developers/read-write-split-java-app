package com.example.webapp;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.vaadin.crudui.crud.impl.GridCrud;

@Route("")
@PageTitle("MaxScale Demo")
public class PersonView extends VerticalLayout {

	public PersonView(PersonService service) {
		GridCrud<Person> crud = new GridCrud<>(Person.class, service);
		crud.getGrid().setColumns("name", "creditCardNumber", "writeServerId", "readServerId");
		crud.getCrudFormFactory().setVisibleProperties("name", "creditCardNumber");
		crud.setSizeFull();

		add(new H2("Person CRUD"), crud);
		setSizeFull();
	}

}
