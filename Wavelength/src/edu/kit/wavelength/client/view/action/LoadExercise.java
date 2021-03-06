package edu.kit.wavelength.client.view.action;

import edu.kit.wavelength.client.view.App;
import edu.kit.wavelength.client.view.exercise.Exercise;
import edu.kit.wavelength.client.view.exercise.RedexExercise;


/**
 * This class changes the view from standard input to exercise view to display
 * the selected exercise.
 */
public class LoadExercise implements Action {

	private Exercise exercise;

	private static App app = App.get();
	
	@Override
	public void run() {
		Control.wipe();
		
		if (exercise instanceof RedexExercise) {
			RedexExercise redexEx = (RedexExercise) exercise;
			redexEx.reset();
		}
		
		if (exercise.hasPredefinitions()) {
			app.editor().write(exercise.getPredefinitions());
		}
		
		app.exerciseDescriptionLabel().getElement().setInnerHTML(exercise.getTask());
		app.solutionArea().getElement().setInnerHTML(exercise.getSolution());
		
		app.editorExercisePanel().setWidgetHidden(app.exercisePanel(), false);
		app.solutionArea().setVisible(false);
		app.loadExercisePopup().hide();
	}

	/**
	 * Gets the selected exercise to load. 
	 * @return Exercise
	 */
	public Exercise getExercise() {
		return exercise;
	}

	/**
	 * Sets the selected exercise to load.
	 * @param exercise
	 */
	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}
	
}
