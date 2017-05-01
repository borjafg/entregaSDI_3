package uo.sdi.menu.util;

import java.util.List;

import alb.util.console.Console;
import uo.sdi.dto.CategoryDTO;
import uo.sdi.dto.TaskDTO;

public class Printer {

    private static final String TAB = "   ";

    public static void printCategoriesList(List<CategoryDTO> categories,
	    String header) {

	Console.println(header + ":");
	Console.println();

	String infoCategory;
	CategoryDTO categ;

	for (int indice = 0; indice < categories.size(); indice++) {
	    categ = categories.get(indice);

	    infoCategory = TAB + "--> ID: %d, nombre: %s, ";
	    infoCategory += "creada: %3$td/%3$tm/%3$tY\n";

	    Console.printf(infoCategory, categ.getId(), categ.getName(),
		    categ.getCreated());

	    if ((indice + 1) < categories.size()) {
		Console.println();
	    }
	}
    }

    public static void printTasksList(List<TaskDTO> tasks, String header) {
	Console.println(header + ":");
	Console.println();

	String infoTarea;
	TaskDTO task;

	for (int indice = 0; indice < tasks.size(); indice++) {
	    task = tasks.get(indice);

	    infoTarea = TAB + "--> ID: %d, título: %s, comentarios: %s\n";

	    infoTarea += TAB + TAB + "==> Creada: %4$td/%4$tm/%4$tY\n";

	    if (task.getPlanned() != null) {
		infoTarea += TAB + TAB + "==> Planeada: %5$td/%5$tm/%5$tY\n";
	    }

	    else {
		infoTarea += TAB + TAB + "==> Sin fecha planeada\n";
	    }

	    if (task.getFinished() != null) {
		infoTarea += TAB + TAB + "==> Finalizada: %6$td/%6$tm/%6$tY\n";
	    }

	    else {
		infoTarea += TAB + TAB + "==> Sin finalizar\n";
	    }

	    Console.printf(infoTarea, task.getId(), task.getTitle(),
		    task.getComments(), task.getCreated(), task.getPlanned(),
		    task.getFinished());

	    if ((indice + 1) < tasks.size()) {
		Console.println();
	    }
	}
    }

}